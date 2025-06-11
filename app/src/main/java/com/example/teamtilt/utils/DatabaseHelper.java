package com.example.teamtilt.utils;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DatabaseHelper {
    protected final FirebaseFirestore db;
    protected final String collection;

    public DatabaseHelper(String collection) {
        this.db = FirebaseFirestore.getInstance();
        this.collection = collection;
    }

    protected Query getCollection() {
        return db.collection(collection);
    }

    protected Query getDocument(String documentId) {
        return db.collection(collection).document(documentId).getParent();
    }
} 