package com.familyapp.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    private String eventName;
    private LocalDateTime eventDate;
    private String location;
    private String belongTo;
    private String note;
    private User addedFrom;


    public Event() {
    }


    @Column(name = "event_name", nullable = false)
    public String getEventName() {
        return eventName;
    }


    @Column(name = "event_date", nullable = false)
    public LocalDateTime getEventDate() {
        return eventDate;
    }


    @Column(name = "belong_to", nullable = false)
    public String getBelongTo() {
        return belongTo;
    }

    @Column(name = "note", nullable = false, columnDefinition = "TEXT")
    public String getNote() {
        return note;
    }

    @ManyToOne
    public User getAddedFrom() {
        return addedFrom;
    }


    @Column(name = "location", nullable = false)
    public String getLocation() {
        return location;
    }

    public Event setLocation(String location) {
        this.location = location;
        return this;
    }

    public Event setNote(String note) {
        this.note = note;
        return this;
    }

    public Event setBelongTo(String belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public Event setAddedFrom(User addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }

    public Event setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public Event setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
        return this;
    }
}
