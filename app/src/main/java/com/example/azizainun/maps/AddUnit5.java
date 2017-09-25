package com.example.azizainun.maps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

import pugman.com.simplelocationgetter.SimpleLocationGetter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AddUnit5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUnit5 extends Fragment implements OnMapReadyCallback, SimpleLocationGetter.OnLocationGetListener,
        GoogleMap.OnMapClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MapView mapView;
    double Long;
    double Lat;
    GoogleMap Gmap;
//    LocationRequest mLocationRequest;
//    GoogleApiClient mGoogleApiClient;
    public AddUnit5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUnit5.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUnit5 newInstance(String param1, String param2) {
        AddUnit5 fragment = new AddUnit5();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
//            mGoogleApiClient.connect();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_unit5, container, false);
        SimpleLocationGetter getter = new SimpleLocationGetter(getActivity(), this);
        getter.getLastLocation();
        mapView = (MapView) view.findViewById(R.id.map_2);
        Button button = (Button) view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model_Detail argument = getArguments().getParcelable("next5");
                String k = Double.toString(Long);
                String l = Double.toString(Lat);
                argument.setLong(Long);
                argument.setLat(Lat);
                Toast.makeText(getContext(), k + " dan " + l, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putParcelable("next6", argument);
                AddUnitAkhir fNextakhir = new AddUnitAkhir();
                fNextakhir.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.content_frame_next, fNextakhir, "gomaps").addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        mapView.getMapAsync(this);
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap = googleMap;
//        Gmap.setOnMapLongClickListener(this);
        Gmap.setOnMapClickListener(this);
        Gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (Gmap != null) {
            Gmap.addMarker(new MarkerOptions().position(new LatLng(Lat, Long)));
            CameraPosition oke = CameraPosition.builder().target(new LatLng(Lat, Long)).zoom(15).build();
            Gmap.moveCamera(CameraUpdateFactory.newCameraPosition(oke));
        }
        if (getArguments() != null){
            if (getArguments().containsKey("Long")){
                Bundle bundle = new Bundle();
                Long = getArguments().getDouble("Long");
                Lat = getArguments().getDouble("Lat");
                String k = Double.toString(Long);
                String l = Double.toString(Lat);
                Gmap.addMarker(new MarkerOptions().position(new LatLng(Lat, Long)));
                CameraPosition oke = CameraPosition.builder().target(new LatLng(Lat, Long)).zoom(15).build();
                Gmap.moveCamera(CameraUpdateFactory.newCameraPosition(oke));
                Toast.makeText(getContext(), k + " dan " + l, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationReady(Location location) {
        Long = location.getLongitude();
        GoogleMap m = Gmap;
        Lat = location.getLatitude();
        if (Gmap != null) {
            Gmap.addMarker(new MarkerOptions().position(new LatLng(Lat, Long)));
            CameraPosition oke = CameraPosition.builder().target(new LatLng(Lat, Long)).zoom(15).build();
            Gmap.moveCamera(CameraUpdateFactory.newCameraPosition(oke));
            Log.d("LOCATION", "onLocationReady: lat=" + location.getLatitude() + " lon=" + location.getLongitude());
        }
    }

    @Override
    public void onError(String s) {
        Log.e("LOCATION", "Error: ");
    }

    /*@Override
    public void onMapLongClick(LatLng latLng) {
        double Lat = latLng.latitude;
        double Long = latLng.longitude;
        Gmap.addMarker(new MarkerOptions().position(new LatLng(Lat, Long))).setDraggable(true);
//        CameraPosition oke = CameraPosition.builder().target(new LatLng(Lat, Long)).zoom(15).build();
//        Gmap.moveCamera(CameraUpdateFactory.newCameraPosition(oke));
    }*/

    @Override
    public void onMapClick(LatLng latLng) {
        Bundle bundle = new Bundle();
        double k = Long;
        double l = Lat;
        bundle.putDouble("Long", Long);
        bundle.putDouble("Lat", Lat);
        AddUnitMaps addUnitMaps = new AddUnitMaps();
        addUnitMaps.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame_next, addUnitMaps, "maps").addToBackStack(null);
        ft.commit();
//        Intent i = new Intent(getContext(), MapsActivity.class);
//        i.putExtras(bundle);
//        startActivity(i);
    }
}