package com.example.teamtilt.models;

import java.util.Date;
import java.util.List;

public class Notice {
    private String id;
    private String title;
    private String content;
    private String type; // GENERAL, ACADEMIC, EVENT, EMERGENCY
    private String authorId;
    private List<String> targetAudience; // ALL, STUDENTS, FACULTY, ADMIN
    private List<String> attachments;
    private Date createdAt;
    private Date updatedAt;
    private boolean isImportant;
    private Date expiryDate;

    public Notice() {
        // Required empty constructor for Firestore
    }

    public Notice(String id, String title, String content, String type, String authorId,
                 List<String> targetAudience, List<String> attachments, boolean isImportant) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.authorId = authorId;
        this.targetAudience = targetAudience;
        this.attachments = attachments;
        this.isImportant = isImportant;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAuthorId() { return authorId; }
    public void setAuthorId(String authorId) { this.authorId = authorId; }

    public List<String> getTargetAudience() { return targetAudience; }
    public void setTargetAudience(List<String> targetAudience) { this.targetAudience = targetAudience; }

    public List<String> getAttachments() { return attachments; }
    public void setAttachments(List<String> attachments) { this.attachments = attachments; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public boolean isImportant() { return isImportant; }
    public void setImportant(boolean important) { isImportant = important; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
} 