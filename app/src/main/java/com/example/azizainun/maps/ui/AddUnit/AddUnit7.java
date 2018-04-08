package com.example.azizainun.maps.ui.AddUnit;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.azizainun.maps.Model.Model_Detail;
import com.example.azizainun.maps.ui.ui_custom.MyTextView;
import com.example.azizainun.maps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pugman.com.simplelocationgetter.SimpleLocationGetter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AddUnit7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUnit7 extends Fragment implements OnMapReadyCallback, SimpleLocationGetter.OnLocationGetListener,
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
    Bundle next5_;
    Model_Detail argument;
    Model_Detail argument1;
    String bkotakab;
//    LocationRequest mLocationRequest;
//    GoogleApiClient mGoogleApiClient;
    public AddUnit7() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUnit7.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUnit7 newInstance(String param1, String param2) {
        AddUnit7 fragment = new AddUnit7();
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
        final SimpleLocationGetter getter = new SimpleLocationGetter(getActivity(), this);
        getter.getLastLocation();
        mapView = (MapView) view.findViewById(R.id.map_2);
        final AutoCompleteTextView kotakab = (AutoCompleteTextView) view.findViewById(R.id.kotakab);
        String[] Skotakab = getActivity().getResources().getStringArray(R.array.kotakab);
        ArrayAdapter<String> Akotakab = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Skotakab);
        kotakab.setAdapter(Akotakab);
        /*
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(kotakab, InputMethodManager.SHOW_IMPLICIT);*/

        MyTextView next6= (MyTextView) view.findViewById(R.id.next6);
        argument = getArguments().getParcelable("next7");
        if (getArguments() != null && getArguments().containsKey("b") && getArguments().containsKey("b1")) {
            Bundle bundle = new Bundle();
            Bundle bundle2 = new Bundle();
            bundle2 = getArguments();
            bundle = bundle2.getBundle("b");
//            argument1 = getArguments().getParcelable("b1");
            argument1 = bundle2.getBundle("b1").getParcelable("nextakhir");
            kotakab.setText(bundle.getString("mkotkab"));
        }
        next6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bkotakab = kotakab.getText().toString();
                if (bkotakab.equals("")) {
                    Toast.makeText(getContext(), "Kota atau Kabupaten tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (getArguments() != null && getArguments().containsKey("b") && getArguments().containsKey("b1")){
                    Bundle bundle1 = new Bundle();
                    Bundle bundle = new Bundle();
                    Bundle bundle2 = new Bundle();
                    bundle2 = getArguments();
                    bundle1 = bundle2.getBundle("b1");
                    bundle = bundle2.getBundle("b");
                    Model_Detail m;
                    if (argument != null) {
                        m = argument;
                    } else if (bundle1.containsKey("nextakhir")){
                        m = bundle1.getParcelable("nextakhir");
                    } else {
                        m = argument;
                    }
                    if(bundle != null) {
                        Long = bundle.getDouble("Long");
                        Lat = bundle.getDouble("Lat");
                    } else if (bundle1 != null) {
                        Long = bundle.getDouble("Long");
                        Lat = bundle.getDouble("Lat");
                    }
                    Log.d("longitude", String.valueOf(Long));
                    String k = Double.toString(Long);
                    String l = Double.toString(Lat);
                    m.setLongi(Long);
                    m.setKotakab(bkotakab);
                    m.setLat(Lat);
                    Toast.makeText(getContext(), k + " dan " + l, Toast.LENGTH_SHORT).show();
                    Bundle bundle0 = new Bundle();
                    bundle0.putParcelable("nextakhir", m);
                    AddUnitAkhir fNext6 = new AddUnitAkhir();
                    fNext6.setArguments(bundle0);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.content_frame_next, fNext6, "gomaps").addToBackStack(null);
                    ft.commit();
                } else {
                    String k = Double.toString(Long);
                    String l = Double.toString(Lat);
                    argument.setLongi(Long);
                    argument.setLat(Lat);
                    argument.setKotakab(bkotakab);
                    Bundle bundle0 = new Bundle();
                    bundle0.putParcelable("nextakhir", argument);
                    AddUnitAkhir fNext6 = new AddUnitAkhir();
                    fNext6.setArguments(bundle0);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.content_frame_next, fNext6, "gomaps").addToBackStack(null);
                    ft.commit();

                }
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
        if (getArguments() != null && getArguments().containsKey("b")){
            Bundle bundle2 = new Bundle();
            Bundle bundle = new Bundle();
            bundle2 = getArguments();
            bundle = bundle2.getBundle("b");
            Long = bundle.getDouble("Long");
            Lat = bundle.getDouble("Lat");
            String k = Double.toString(Long);
            String l = Double.toString(Lat);
            Gmap.addMarker(new MarkerOptions().position(new LatLng(Lat, Long)));
            CameraPosition oke = CameraPosition.builder().target(new LatLng(Lat, Long)).zoom(15).build();
            Gmap.moveCamera(CameraUpdateFactory.newCameraPosition(oke));
            Toast.makeText(getContext(), k + " dan " + l, Toast.LENGTH_SHORT).show();
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
        Bundle bundle1 = new Bundle();
        double k = Long;
        double l = Lat;
        if (argument != null) {
            bundle1.putParcelable("nextakhir", argument);
        } else {
            bundle1.putParcelable("nextakhir", argument1);
        }
        AutoCompleteTextView kotakab = (AutoCompleteTextView) getView().findViewById(R.id.kotakab);
        bundle.putString("kotkab", kotakab.getText().toString());
        bundle.putDouble("Long", Long);
        bundle.putDouble("Lat", Lat);
        AddUnitMaps addUnitMaps = new AddUnitMaps();
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("b", bundle);
        bundle2.putBundle("b1", bundle1);
        addUnitMaps.setArguments(bundle2);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame_next, addUnitMaps, "maps").addToBackStack(null);
        ft.commit();
//        Intent i = new Intent(getContext(), MapsActivity.class);
//        i.putExtras(bundle);
//        startActivity(i);
    }
}