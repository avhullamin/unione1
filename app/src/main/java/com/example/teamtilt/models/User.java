package com.example.teamtilt.models;

public class User {
    private String uid;
    private String email;
    private String universityId;
    private String name;
    private String role; // STUDENT, FACULTY, ADMIN
    private String profilePhotoUrl;
    private String course;
    private String year;
    private String bio;

    public User() {
        // Required empty constructor for Firestore
    }

    public User(String uid, String email, String universityId, String name, String role) {
        this.uid = uid;
        this.email = email;
        this.universityId = universityId;
        this.name = name;
        this.role = role;
    }

    // Getters and Setters
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUniversityId() { return universityId; }
    public void setUniversityId(String universityId) { this.universityId = universityId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getProfilePhotoUrl() { return profilePhotoUrl; }
    public void setProfilePhotoUrl(String profilePhotoUrl) { this.profilePhotoUrl = profilePhotoUrl; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
} 