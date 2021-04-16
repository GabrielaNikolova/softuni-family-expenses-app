package com.familyapp.models.viewModels;


public class EventViewModel {
    private Long id;
    private String eventName;
    private String eventDate;
    private String location;
    private String belongTo;
    private String note;
    private String addedFrom;


    public EventViewModel() {
    }


    public Long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
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

    public String getAddedFrom() {
        return addedFrom;
    }

    public EventViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public EventViewModel setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventViewModel setEventDate(String eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public EventViewModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public EventViewModel setBelongTo(String belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public EventViewModel setNote(String note) {
        this.note = note;
        return this;
    }

    public EventViewModel setAddedFrom(String addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }
}
