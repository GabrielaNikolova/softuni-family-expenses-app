package com.familyapp.models.serviceModels;

import com.familyapp.models.entities.User;

import java.time.LocalDateTime;

public class EventAddServModel {
    private Long id;
    private String eventName;
    private LocalDateTime eventDate;
    private String location;
    private String belongTo;
    private String note;
    private User addedFrom;

    public EventAddServModel() {
    }


    public Long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public String getNote() {
        return note;
    }

    public User getAddedFrom() {
        return addedFrom;
    }

    public EventAddServModel setId(Long id) {
        this.id = id;
        return this;
    }

    public EventAddServModel setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventAddServModel setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public EventAddServModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public EventAddServModel setBelongTo(String belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public EventAddServModel setNote(String note) {
        this.note = note;
        return this;
    }

    public EventAddServModel setAddedFrom(User addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }
}
