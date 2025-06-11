package com.example.teamtilt.utils;

import com.example.teamtilt.models.Notice;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

public class NoticeHelper extends DatabaseHelper {
    private static final String COLLECTION_NAME = "notices";

    public NoticeHelper() {
        super(COLLECTION_NAME);
    }

    // Create a new notice
    public Task<Void> createNotice(Notice notice) {
        return db.collection(COLLECTION_NAME)
                .document(notice.getId())
                .set(notice);
    }

    // Get a notice by ID
    public Task<DocumentSnapshot> getNotice(String noticeId) {
        return db.collection(COLLECTION_NAME)
                .document(noticeId)
                .get();
    }

    // Get all notices
    public Task<QuerySnapshot> getAllNotices() {
        return db.collection(COLLECTION_NAME)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get();
    }

    // Get notices by type
    public Task<QuerySnapshot> getNoticesByType(String type) {
        return db.collection(COLLECTION_NAME)
                .whereEqualTo("type", type)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get();
    }

    // Get important notices
    public Task<QuerySnapshot> getImportantNotices() {
        return db.collection(COLLECTION_NAME)
                .whereEqualTo("isImportant", true)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get();
    }

    // Get active notices (not expired)
    public Task<QuerySnapshot> getActiveNotices() {
        return db.collection(COLLECTION_NAME)
                .whereGreaterThan("expiryDate", new Date())
                .orderBy("expiryDate", com.google.firebase.firestore.Query.Direction.ASCENDING)
                .get();
    }

    // Update a notice
    public Task<Void> updateNotice(Notice notice) {
        return db.collection(COLLECTION_NAME)
                .document(notice.getId())
                .set(notice);
    }

    // Delete a notice
    public Task<Void> deleteNotice(String noticeId) {
        return db.collection(COLLECTION_NAME)
                .document(noticeId)
                .delete();
    }

    // Mark notice as important
    public Task<Void> markAsImportant(String noticeId, boolean isImportant) {
        return db.collection(COLLECTION_NAME)
                .document(noticeId)
                .update("isImportant", isImportant, "updatedAt", new Date());
    }

    // Add attachment to notice
    public Task<Void> addAttachment(String noticeId, String attachmentUrl) {
        return db.collection(COLLECTION_NAME)
                .document(noticeId)
                .update("attachments", attachmentUrl, "updatedAt", new Date());
    }
} 