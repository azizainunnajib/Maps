package com.example.azizainun.maps.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.azizainun.maps.R;
import com.example.azizainun.maps.ui.AddUnit.AddUnit7;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    Bundle bundle;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bundle = getIntent().getExtras();
        Button oke = (Button) findViewById(R.id.floatingActionButton);
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putDouble("Long", latLng.longitude);
                bundle.putDouble("Lat", latLng.latitude);
                AddUnit7 addUnit5 = new AddUnit7();
                addUnit5.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.content_frame_next, addUnit5).addToBackStack(null);
                ft.commit();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        double Long = bundle.getDouble("Long");
        double Lat = bundle.getDouble("Lat");
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        CameraPosition oke = CameraPosition.builder().target(new LatLng(Lat, Long)).zoom(15).build();
        LatLng sydney = new LatLng(Lat, Long);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")).setDraggable(true);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(oke));
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerDragListener(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        latLng = marker.getPosition();
        CameraPosition oke = CameraPosition.builder().target(latLng).zoom(15).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(oke));
    }
}
