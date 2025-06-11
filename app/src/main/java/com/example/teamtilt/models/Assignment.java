package com.example.teamtilt.models;

import java.util.Date;
import java.util.List;

public class Assignment {
    private String id;
    private String courseId;
    private String title;
    private String description;
    private Date dueDate;
    private int totalMarks;
    private List<String> attachments;
    private String status; // PUBLISHED, DRAFT, SUBMITTED, GRADED
    private Date createdAt;
    private Date updatedAt;

    public Assignment() {
        // Required empty constructor for Firestore
    }

    public Assignment(String id, String courseId, String title, String description, 
                     Date dueDate, int totalMarks, List<String> attachments) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.totalMarks = totalMarks;
        this.attachments = attachments;
        this.status = "PUBLISHED";
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public int getTotalMarks() { return totalMarks; }
    public void setTotalMarks(int totalMarks) { this.totalMarks = totalMarks; }

    public List<String> getAttachments() { return attachments; }
    public void setAttachments(List<String> attachments) { this.attachments = attachments; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
} 