package com.familyapp.services.impl;

import com.familyapp.models.entities.Event;
import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.EventAddServModel;
import com.familyapp.models.serviceModels.EventEditServModel;
import com.familyapp.models.viewModels.EventViewModel;
import com.familyapp.repositories.EventRepo;
import com.familyapp.services.EventService;
import com.familyapp.services.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public EventServiceImpl(EventRepo eventRepo, UserService userService, ModelMapper modelMapper, Gson gson) {
        this.eventRepo = eventRepo;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    @Transactional
    public void addEvent(EventAddServModel eventAddServiceModel) {
        Event newEvent = modelMapper.map(eventAddServiceModel, Event.class);
        User user = userService
                .findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        newEvent.setAddedFrom(user);
        eventRepo.save(newEvent);

        if (user.getEvents() == null) {
            List<Event> events = new ArrayList<>();
            events.add(newEvent);
            user.setEvents(events);
        } else {
            user.getEvents().add(newEvent);
        }
    }

    @Override
    public EventViewModel getEventById(String id) {
        Event event = eventRepo.findById(Long.valueOf(id)).orElseThrow(IllegalArgumentException::new);
        EventViewModel eventView = modelMapper.map(event, EventViewModel.class);
        eventView.setEventDate(event.getEventDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        eventView.setAddedFrom(SecurityContextHolder.getContext().getAuthentication().getName());
        return eventView;
    }

    @Override
    @Transactional
    public void deleteEventById(String id) {
        Optional<Event> event = eventRepo.findById(Long.valueOf(id));

        if (event.isPresent()) {
            userService.updateUserEvents(Long.valueOf(id));
            eventRepo.deleteById(Long.valueOf(id));
        }
    }

    @Override
    public String getAllByUser() {
        List<Event> events = eventRepo.findAllByAddedFrom_Username(SecurityContextHolder.getContext().getAuthentication().getName());
        return getEvents(events);
    }

    @Override
    public String getAllByFamily() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Event> events = eventRepo.findAllByAddedFrom_Family_Id(user.getFamily().getId());
        return getEvents(events);
    }

    @Override
    public void updateEvent(String id, EventEditServModel eventEditServModel) {

        Event event = eventRepo.findById(Long.valueOf(id)).orElseThrow(IllegalArgumentException::new);

        if (!event.getEventName().equals(eventEditServModel.getEventName()) && eventEditServModel.getEventName() != null) {
            event.setEventName(eventEditServModel.getEventName());
        }
        if (event.getEventDate() != (eventEditServModel.getEventDate()) && eventEditServModel.getEventDate() != null) {
            event.setEventDate(eventEditServModel.getEventDate());
        }
        if (!event.getLocation().equals(eventEditServModel.getLocation()) && eventEditServModel.getLocation() != null) {
            event.setLocation(eventEditServModel.getLocation());
        }
        if (!event.getBelongTo().equals(eventEditServModel.getBelongTo()) && eventEditServModel.getBelongTo() != null) {
            event.setBelongTo(eventEditServModel.getBelongTo());
        }
        if (!event.getNote().equals(eventEditServModel.getNote()) && eventEditServModel.getNote() != null) {
            event.setNote(eventEditServModel.getNote());
        }

        eventRepo.save(event);

    }

    public String getEvents(List<Event> events) {
        if (events.isEmpty()) {
            return gson.toJson(new ArrayList<>());
        }

        List<EventViewModel> eventsList = events.stream()
                .map(e -> {
                    EventViewModel event = modelMapper.map(e, EventViewModel.class);
                    event.setAddedFrom(SecurityContextHolder.getContext().getAuthentication().getName());
                    event.setEventDate(e.getEventDate().minusMonths(1).format(DateTimeFormatter.ofPattern("dMyyyy")));
                    return event;
                }).collect(Collectors.toList());

        return gson.toJson(eventsList);
    }
}
