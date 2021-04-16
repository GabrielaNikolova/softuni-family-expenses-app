package com.familyapp.services.impl;

import com.familyapp.models.entities.Event;
import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.EventAddServModel;
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

        if (events.isEmpty()) {
            //return new ArrayList<>();
            return "";
        }

//        Map<String, List<EventViewModel>> userEvents = new HashMap<>();
//
//        events.forEach(e -> {
//            EventViewModel event = modelMapper.map(e, EventViewModel.class);
//            event.setAddedFrom(SecurityContextHolder.getContext().getAuthentication().getName());
//
//            if (userEvents.isEmpty()) {
//                List<EventViewModel> newEventList = new ArrayList<>();
//                userEvents.put("event", newEventList);
//            }
//
//            userEvents.get("event").add(event);
//        });

        List<EventViewModel> userEvents = events.stream()
                .map(e -> {
                    EventViewModel event = modelMapper.map(e, EventViewModel.class);
                    event.setAddedFrom(SecurityContextHolder.getContext().getAuthentication().getName());
                    event.setEventDate(e.getEventDate().format(DateTimeFormatter.ofPattern("ddMyyyy")));
                    return event;
                }).collect(Collectors.toList());

//                //.filter(e -> e.getCreatedOn().getMonth().equals(LocalDate.now().getMonth()))


        String eventsJson = gson.toJson(userEvents);


        System.out.println(eventsJson);

        return eventsJson;


    }
}
