package com.example.azizainun.maps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingInstagram extends Fragment {


    public TrendingInstagram() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ins_tran_zit, container, false);
        new Database().mReadDataOnce("Trending/data", new Database.OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                for (DataSnapshot snapshot:data.getChildren()) {
                    String url= (String) snapshot.child("images/standard_resolution/url").getValue();
                    int id_location =  (int) snapshot.child("images/location/id").getValue();
                    int lat = (int) snapshot.child("images/location/latitude").getValue();
                    int longi = (int) snapshot.child("images/location/longitude").getValue();
                    String nama_location = (String) snapshot.child("images/location/name").getValue();
                    String username_ig = (String) snapshot.child("images/user/username").getValue();
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
