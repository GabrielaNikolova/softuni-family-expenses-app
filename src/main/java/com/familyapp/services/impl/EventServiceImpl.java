package com.familyapp.services.impl;

import com.familyapp.models.entities.Event;
import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.EventAddServModel;
import com.familyapp.models.viewModels.EventViewModel;
import com.familyapp.repositories.EventRepo;
import com.familyapp.services.EventService;
import com.familyapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepo eventRepo, UserService userService, ModelMapper modelMapper) {
        this.eventRepo = eventRepo;
        this.userService = userService;
        this.modelMapper = modelMapper;
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
}
