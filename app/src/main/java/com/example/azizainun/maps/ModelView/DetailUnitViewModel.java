package com.example.azizainun.maps.ModelView;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.azizainun.maps.Model.Model_Detail;
import com.example.azizainun.maps.services.Database;
import com.example.azizainun.maps.ui.DetailUnit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by aziza on 3/21/2018.
 */

public class DetailUnitViewModel extends ViewModel {
    private final Database liveData = new Database();

    private final LiveData<Model_Detail> hotStockLiveData =
            Transformations.map(liveData, new Deserializer());

    private class Deserializer implements Function<DataSnapshot, Model_Detail> {
        @Override
        public Model_Detail apply(DataSnapshot dataSnapshot) {
            return dataSnapshot.getValue(Model_Detail.class);
        }
    }

    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }

    /*public LiveData<Model_Detail> getdata() {
        new Database().mReadDataOnce("User/$UID/Tempat_sewa/$nama_tempat", new Database.OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
        return detail;
    }*/

}
