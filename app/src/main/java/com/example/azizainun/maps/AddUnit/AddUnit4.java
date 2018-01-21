package com.example.azizainun.maps.AddUnit;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.azizainun.maps.Model_Detail;
import com.example.azizainun.maps.MyTextView;
import com.example.azizainun.maps.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddUnit4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddUnit4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUnit4 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String sminimarket;
    String sangkot;
    String sojek;
    String satm;
    MyTextView next5;

    private OnFragmentInteractionListener mListener;

    public AddUnit4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUnit4.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUnit4 newInstance(String param1, String param2) {
        AddUnit4 fragment = new AddUnit4();
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
        View view = inflater.inflate(R.layout.fragment_add_unit4, container, false);

        MyTextView minimarket = (MyTextView) view.findViewById(R.id.minimarket);
        MyTextView angkot = (MyTextView) view.findViewById(R.id.angkot);
        MyTextView ojek = (MyTextView) view.findViewById(R.id.ojek_daring);
        MyTextView atm = (MyTextView) view.findViewById(R.id.atm);
        next5 = (MyTextView) view.findViewById(R.id.next5);

        minimarket.setOnClickListener(this);
        angkot.setOnClickListener(this);
        ojek.setOnClickListener(this);
        atm.setOnClickListener(this);
        next5.setOnClickListener(this);

        sminimarket = "";
        sangkot = "";
        sojek = "";
        satm = "";

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.getArguments().clear();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minimarket:
                if (v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                     sminimarket = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    sminimarket = "Minimarket";
                }
                break;
            case R.id.angkot:
                if (v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    sangkot = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    sangkot = "Angkutan Kota";
                }
                break;
            case R.id.ojek_daring:
                if (v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    sojek = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    sojek = "Ojek Daring";
                }
                break;
            case R.id.atm:
                if (v.isSelected()) {
                    v.setBackgroundResource(R.drawable.ripple_merah);
                    satm = "";
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.ripple_hijau);
                    v.setSelected(true);
                    satm = "ATM";
                }
                break;
            case R.id.next5:
                Next5(sminimarket, sangkot, sojek, satm);
                break;
        }
    }

    void Next5(String minimarket, String angkot, String ojek, String atm) {
        Model_Detail argument = getArguments().getParcelable("next4");
        argument.setMinimarket(minimarket);
        argument.setAngkot(angkot);
        argument.setOjek_daring(ojek);
        argument.setAtm(atm);

        Bundle bundle = new Bundle();
        bundle.putParcelable("next5", argument);
        /*Intent i = new Intent(getActivity(), MapsActivity.class);
        i.putExtras(bundle);
        startActivity(i);*/
        AddUnit5 fNext5 = new AddUnit5();
        fNext5.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame_next, fNext5, "gomaps").addToBackStack(null);
        ft.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
