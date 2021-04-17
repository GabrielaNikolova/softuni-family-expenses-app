package com.familyapp.models.bindingModels;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class EventEditBindModel {

    private String eventName;
    private LocalDateTime eventDate;
    private String location;
    private String belongTo;
    private String note;

    public EventEditBindModel() {
    }

    @NotEmpty
    @Size(min = 3, max = 20, message = "Event name length must be between 3 and 20 characters")
    public String getEventName() {
        return eventName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Event date and time cannot be in the past")
    public LocalDateTime getEventDate() {
        return eventDate;
    }

    @NotEmpty
    @Size(min = 3, max = 30, message = "Event location length must be between 3 and 30 characters")
    public String getLocation() {
        return location;
    }

    @NotEmpty
    @Size(min = 3, max = 30, message = "Event owner length must be between 3 and 30 characters")
    public String getBelongTo() {
        return belongTo;
    }


    @Size(max = 200, message = "Event notes length cannot exceed 200 characters")
    public String getNote() {
        return note;
    }

    public EventEditBindModel setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventEditBindModel setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public EventEditBindModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public EventEditBindModel setBelongTo(String belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public EventEditBindModel setNote(String note) {
        this.note = note;
        return this;
    }
}
