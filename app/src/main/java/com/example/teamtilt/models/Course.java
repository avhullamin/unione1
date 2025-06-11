package com.example.teamtilt.models;

import java.util.List;

public class Course {
    private String id;
    private String name;
    private String code;
    private String description;
    private String instructorId;
    private List<String> studentIds;
    private String semester;
    private int credits;
    private String department;

    public Course() {
        // Required empty constructor for Firestore
    }

    public Course(String id, String name, String code, String description, String instructorId, 
                 List<String> studentIds, String semester, int credits, String department) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.instructorId = instructorId;
        this.studentIds = studentIds;
        this.semester = semester;
        this.credits = credits;
        this.department = department;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructorId() { return instructorId; }
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; }

    public List<String> getStudentIds() { return studentIds; }
    public void setStudentIds(List<String> studentIds) { this.studentIds = studentIds; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
} 