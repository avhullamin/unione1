package com.example.teamtilt.utils;

import com.example.teamtilt.models.Assignment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

public class AssignmentHelper extends DatabaseHelper {
    private static final String COLLECTION_NAME = "assignments";

    public AssignmentHelper() {
        super(COLLECTION_NAME);
    }

    // Create a new assignment
    public Task<Void> createAssignment(Assignment assignment) {
        return db.collection(COLLECTION_NAME)
                .document(assignment.getId())
                .set(assignment);
    }

    // Get an assignment by ID
    public Task<DocumentSnapshot> getAssignment(String assignmentId) {
        return db.collection(COLLECTION_NAME)
                .document(assignmentId)
                .get();
    }

    // Get all assignments for a course
    public Task<QuerySnapshot> getAssignmentsByCourse(String courseId) {
        return db.collection(COLLECTION_NAME)
                .whereEqualTo("courseId", courseId)
                .get();
    }

    // Get upcoming assignments
    public Task<QuerySnapshot> getUpcomingAssignments(String courseId) {
        return db.collection(COLLECTION_NAME)
                .whereEqualTo("courseId", courseId)
                .whereGreaterThan("dueDate", new Date())
                .get();
    }

    // Get past assignments
    public Task<QuerySnapshot> getPastAssignments(String courseId) {
        return db.collection(COLLECTION_NAME)
                .whereEqualTo("courseId", courseId)
                .whereLessThan("dueDate", new Date())
                .get();
    }

    // Update an assignment
    public Task<Void> updateAssignment(Assignment assignment) {
        return db.collection(COLLECTION_NAME)
                .document(assignment.getId())
                .set(assignment);
    }

    // Delete an assignment
    public Task<Void> deleteAssignment(String assignmentId) {
        return db.collection(COLLECTION_NAME)
                .document(assignmentId)
                .delete();
    }

    // Update assignment status
    public Task<Void> updateAssignmentStatus(String assignmentId, String status) {
        return db.collection(COLLECTION_NAME)
                .document(assignmentId)
                .update("status", status, "updatedAt", new Date());
    }

    // Add attachment to assignment
    public Task<Void> addAttachment(String assignmentId, String attachmentUrl) {
        return db.collection(COLLECTION_NAME)
                .document(assignmentId)
                .update("attachments", attachmentUrl, "updatedAt", new Date());
    }
} 