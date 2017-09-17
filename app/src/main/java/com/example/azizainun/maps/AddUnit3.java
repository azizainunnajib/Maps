package com.example.azizainun.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.azizainun.maps.R.drawable.ripple_hijau;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AddUnit3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUnit3 extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String swifi;
    String sac;
    String stv;
    String sdapur;
    String skulkas;
    String sparkir;
    String sairpanas;
    String ssyariah;
    MyTextView next4;

//    private OnFragmentInteractionListener mListener;

    public AddUnit3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUnit3.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUnit3 newInstance(String param1, String param2) {
        AddUnit3 fragment = new AddUnit3();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_unit3, container, false);

        MyTextView wifi = (MyTextView) view.findViewById(R.id.wifi);
        MyTextView ac = (MyTextView) view.findViewById(R.id.AC);
        MyTextView tv = (MyTextView) view.findViewById(R.id.TV);
        MyTextView dapur = (MyTextView) view.findViewById(R.id.Dapur);
        MyTextView kulkas = (MyTextView) view.findViewById(R.id.Kulkas);
        MyTextView parkir = (MyTextView) view.findViewById(R.id.Parkir);
        MyTextView airpanas = (MyTextView) view.findViewById(R.id.Air_Panas);
        MyTextView syariah = (MyTextView) view.findViewById(R.id.syariah);
        next4 = (MyTextView) view.findViewById(R.id.next4);

        wifi.setOnClickListener(this);
        ac.setOnClickListener(this);
        tv.setOnClickListener(this);
        dapur.setOnClickListener(this);
        kulkas.setOnClickListener(this);
        parkir.setOnClickListener(this);
        airpanas.setOnClickListener(this);
        syariah.setOnClickListener(this);
        next4.setOnClickListener(this);

        return view;




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wifi:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    swifi = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    swifi = "Wifi";
                }
                break;
            case R.id.AC:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    sac = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    sac = "AC";
                }
                break;
            case R.id.TV:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    stv = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    stv = "TV";
                }
                break;
            case R.id.Dapur:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    sdapur = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    sdapur = "Dapur";
                }
                break;
            case R.id.Kulkas:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    skulkas = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    skulkas = "Kulkas";
                }
                break;
            case R.id.Parkir:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    sparkir = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    sparkir = "Parkir";
                }
                break;
            case R.id.Air_Panas:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    sairpanas = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    sairpanas = "Air Panas";
                }
                break;
            case R.id.syariah:
                if(v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    ssyariah = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    ssyariah = "Syariah";
                }
                break;
            case R.id.next4:
                Next4(swifi, sac, stv, sdapur, skulkas, sparkir, sairpanas, ssyariah);
                break;
        }
    }

    void Next4 (String swifi, String sac, String stv, String sdapur, String skulkas, String sparkir, String sairpanas, String ssyariah) {
        Model_Detail argument = getArguments().getParcelable("next3");
        if (!(swifi == null)){
            argument.setWifi(swifi);
        }
        if (!(sac == null)) {
            argument.setAc(sac);
        }
        if (!(stv == null)) {
            argument.setTv(stv);
        }
        if (!(sdapur == null)) {
            argument.setDapur(sdapur);
        }
        if (!(skulkas == null)) {
            argument.setKulkas(skulkas);
        }
        if (!(sparkir == null)) {
            argument.setParkir(sparkir);
        }
        if (!(sairpanas == null)) {
            argument.setAirpanas(sairpanas);
        }
        if (!(ssyariah == null)) {
            argument.setSyariah(ssyariah);
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("next4", argument);
        AddUnit4 fNext4 = new AddUnit4();
        fNext4.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame_next, fNext4).addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.getArguments().clear();
    }

    // TODO: Rename method, update argument and hook method into UI event
 /*   public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

/*    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

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
 /*   public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
