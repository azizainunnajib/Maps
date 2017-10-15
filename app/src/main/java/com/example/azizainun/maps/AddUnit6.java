package com.example.azizainun.maps;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddUnit6.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddUnit6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUnit6 extends Fragment implements View.OnClickListener {
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Cek();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher textWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Ribu(s);
        }
    };

    private void Ribu(CharSequence s) {
        harga_.removeTextChangedListener(textWatcher1);

        try{
            String originalS = s.toString();

            int longval;
            if(originalS.contains(",")){
                originalS = originalS.replace(",","");
            }
            longval = Integer.parseInt(originalS);

            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();
            decimalFormat.applyPattern("#,###,###,###,###");
            String formatedS = decimalFormat.format(longval);

            harga_.setText(formatedS);
            harga_.setSelection(harga_.getText().length());
            harga_.addTextChangedListener(textWatcher1);
        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText handphone_;
    EditText nama_tempat_;
    EditText harga_;
    MyTextView next8;

    private OnFragmentInteractionListener mListener;

    public AddUnit6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUnit6.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUnit6 newInstance(String param1, String param2) {
        AddUnit6 fragment = new AddUnit6();
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
        View view = inflater.inflate(R.layout.fragment_add_unit7, container, false);
        handphone_ = (EditText) view.findViewById(R.id.handphone);
        nama_tempat_ = (EditText) view.findViewById(R.id.nama_tempat);
        harga_ = (EditText) view.findViewById(R.id.harga);
        next8 = (MyTextView) view.findViewById(R.id.next8);

        handphone_.addTextChangedListener(textWatcher);
        nama_tempat_.addTextChangedListener(textWatcher);
        harga_.addTextChangedListener(textWatcher);
        harga_.addTextChangedListener(textWatcher1);

        next8.setOnClickListener(this);

        /*String handphone = handphone_.getText().toString();
        String nama_tempat = nama_tempat_.getText().toString();
        String harga = harga_.getText().toString().trim();*/

        Cek();
        return view;
    }

    private void Cek() {
        String s1 = handphone_.getText().toString();
        String s2 = nama_tempat_.getText().toString();
        String s3 = harga_.getText().toString();
        next8.setEnabled(!s1.trim().isEmpty() && !s2.trim().isEmpty() && !s3.trim().isEmpty());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Model_Detail argument = getArguments().getParcelable("next6");
        argument.setHarga(harga_.getText().toString());
        argument.setHandphone(handphone_.getText().toString());
        argument.setJudul(nama_tempat_.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("next7", argument);
        AddUnit7 fNext8 = new AddUnit7();
        fNext8.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame_next, fNext8).addToBackStack(null);
        ft.commit();
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
