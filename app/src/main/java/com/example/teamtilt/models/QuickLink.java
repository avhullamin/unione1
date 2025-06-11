package com.example.teamtilt.models;

public class QuickLink {
    private String id;
    private String title;
    private String description;
    private String iconUrl;
    private String targetScreen; // The screen to navigate to when clicked
    private int order; // For sorting quick links

    public QuickLink() {
        // Required empty constructor for Firestore
    }

    public QuickLink(String id, String title, String description, String iconUrl, String targetScreen, int order) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.iconUrl = iconUrl;
        this.targetScreen = targetScreen;
        this.order = order;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public String getTargetScreen() { return targetScreen; }
    public void setTargetScreen(String targetScreen) { this.targetScreen = targetScreen; }

    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
} 