package com.familyapp.services;

import com.familyapp.models.serviceModels.EventAddServModel;
import com.familyapp.models.viewModels.EventViewModel;

public interface EventService {
    void addEvent(EventAddServModel eventAddServiceModel);

    EventViewModel getEventById(String id);

    void deleteEventById(String id);

    String getAllByUser();

    String getAllByFamily();
}
