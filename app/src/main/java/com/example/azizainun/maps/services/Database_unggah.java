package com.example.azizainun.maps.services;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.storage.UploadTask;

/**
 * Created by aziza on 8/24/2017.
 */

public class Database_unggah {

    public interface OnUnggahDataListener {
        public void onStart();
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot);
        public void onFailed(@NonNull Exception e);
    }

    public void mUnggahData(UploadTask uploadTask, final OnUnggahDataListener listener) {
        listener.onStart();
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                listener.onSuccess(taskSnapshot);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFailed(e);
            }
        });
        /*FirebaseDatabase.getInstance().getReference().child(child).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });*/
    }
}
