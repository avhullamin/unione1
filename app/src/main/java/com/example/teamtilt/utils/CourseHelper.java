package com.example.teamtilt.utils;

import com.example.teamtilt.models.Course;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CourseHelper extends DatabaseHelper {
    private static final String COLLECTION_NAME = "courses";

    public CourseHelper() {
        super(COLLECTION_NAME);
    }

    // Create a new course
    public Task<Void> createCourse(Course course) {
        return db.collection(COLLECTION_NAME)
                .document(course.getId())
                .set(course);
    }

    // Get a course by ID
    public Task<DocumentSnapshot> getCourse(String courseId) {
        return db.collection(COLLECTION_NAME)
                .document(courseId)
                .get();
    }

    // Get all courses
    public Task<QuerySnapshot> getAllCourses() {
        return db.collection(COLLECTION_NAME)
                .get();
    }

    // Get courses by instructor
    public Task<QuerySnapshot> getCoursesByInstructor(String instructorId) {
        return db.collection(COLLECTION_NAME)
                .whereEqualTo("instructorId", instructorId)
                .get();
    }

    // Get courses by student
    public Task<QuerySnapshot> getCoursesByStudent(String studentId) {
        return db.collection(COLLECTION_NAME)
                .whereArrayContains("studentIds", studentId)
                .get();
    }

    // Update a course
    public Task<Void> updateCourse(Course course) {
        return db.collection(COLLECTION_NAME)
                .document(course.getId())
                .set(course);
    }

    // Delete a course
    public Task<Void> deleteCourse(String courseId) {
        return db.collection(COLLECTION_NAME)
                .document(courseId)
                .delete();
    }

    // Add a student to a course
    public Task<Void> addStudentToCourse(String courseId, String studentId) {
        return db.collection(COLLECTION_NAME)
                .document(courseId)
                .update("studentIds", List.of(studentId));
    }

    // Remove a student from a course
    public Task<Void> removeStudentFromCourse(String courseId, String studentId) {
        return db.collection(COLLECTION_NAME)
                .document(courseId)
                .update("studentIds", List.of(studentId));
    }
} 