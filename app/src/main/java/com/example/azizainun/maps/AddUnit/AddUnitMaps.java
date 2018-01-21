package com.example.azizainun.maps.AddUnit;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.azizainun.maps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddUnitMaps.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddUnitMaps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUnitMaps extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GoogleMap mMap;
    Bundle bundle2;
    Bundle bundle1;
    Bundle bundle;
    LatLng latLng;
    String kotakab;

    private OnFragmentInteractionListener mListener;

    public AddUnitMaps() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUnitMaps.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUnitMaps newInstance(String param1, String param2) {
        AddUnitMaps fragment = new AddUnitMaps();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_unit_maps, container, false);
        bundle2 = getArguments();
        bundle = bundle2.getBundle("b");
        bundle1 = bundle2.getBundle("b1");
        kotakab = bundle.getString("kotkab");

        Button oke = (Button) view.findViewById(R.id.floatingActionButton);
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Model_Detail m = getArguments().getParcelable("next5");
                bundle1.putParcelable("next5", m);*/
                Bundle bundle = new Bundle();
                boolean determiner = true;
                bundle.putDouble("Long", latLng.longitude);
                bundle.putDouble("Lat", latLng.latitude);
                bundle.putBoolean("determiner", determiner);
                bundle.putString("mkotkab", kotakab);
                AddUnit7 addUnit5 = new AddUnit7();
                Bundle bundle0 = new Bundle();
                bundle0.putBundle("b1", bundle1);
                bundle0.putBundle("b", bundle);
                addUnit5.setArguments(bundle0);
                /*addUnit5.setArguments(bundle);
                addUnit5.setArguments(bundle1);*/
                FragmentManager FM = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment rf = getActivity().getSupportFragmentManager().findFragmentByTag("maps");
//                Fragment rf1 = getActivity().getSupportFragmentManager().findFragmentByTag("gomaps");
                if (rf!= null) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(rf).commit();
//                    getActivity().getSupportFragmentManager().beginTransaction().remove(rf1).commit();
                }
                ft.replace(R.id.content_frame_next, addUnit5).addToBackStack(null);
                ft.commit();

//                getFragmentManager().popBackStack("maps", 0);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_3);
        mapFragment.getMapAsync(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
        mMap.setOnMarkerDragListener(this);
        latLng = new LatLng(Lat, Long);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
