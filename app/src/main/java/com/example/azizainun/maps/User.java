package com.example.azizainun.maps;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by aziza on 7/30/2017.
 */

public class User {
    public static String FIREBASE_DATABASE = "https://my-project-1479543973833.firebaseio.com/";
    public static DatabaseReference mFirebaseDatabase;
    public static FirebaseDatabase mFirebaseInstance;
    public static FirebaseAuth mFirebaseAuth;
    public static FirebaseUser mFirebaseUser;
    public static String Userid;
    public static String getUID() {
        return Userid;
    }

    static void setUID() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        Userid = mFirebaseUser.getUid();
    }
}
