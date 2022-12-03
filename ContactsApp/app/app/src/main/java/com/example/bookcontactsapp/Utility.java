package com.example.bookcontactsapp;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utility {
    public static CollectionReference getCollectionReferenceForNotes(){
        return FirebaseFirestore.getInstance().collection("notes")
                .document().collection("my_notes");
    }
}
