package com.example.teamtilt.models;

import java.util.Date;

public class Event {
    private String id;
    private String title;
    private String description;
    private Date date;
    private String location;
    private String organizer;
    private String type; // ACADEMIC, SOCIAL, SPORTS, etc.

    public Event() {
        // Required empty constructor for Firestore
    }

    public Event(String id, String title, String description, Date date, String location, String organizer, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.type = type;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
} 