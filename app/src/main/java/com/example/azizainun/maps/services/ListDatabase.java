package com.example.azizainun.maps.services;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.azizainun.maps.Model.Model_Detail;
import com.example.azizainun.maps.Model.Model_data_ig2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.List;

/**
 * Created by aziza on 3/21/2018.
 */

public class ListDatabase {
    MutableLiveData<Model_Detail> model_detailLiveData = new MutableLiveData<>();
    public LiveData<Model_Detail> getModelDetail() {
        new Database().mReadDataOnce("User/$UID/Tempat_sewa/$nama_tempat", new Database.OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(final DataSnapshot data) {
                if (data != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Model_Detail receivedData = data.getValue(Model_Detail.class);
                            model_detailLiveData.postValue(receivedData);
                        }
                    }).start();
                } else {
                    model_detailLiveData.setValue(null);
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
        return model_detailLiveData;
    }
}
