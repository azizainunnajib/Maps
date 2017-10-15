package com.example.azizainun.maps;

import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddUnit5.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class AddUnit5 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    EditText peraturan;
    EditText deskripsi;
    MyTextView next7;
    MyTextView checkin;
    MyTextView checkout;

    public AddUnit5() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_unit6, container, false);
        peraturan = (EditText) view.findViewById(R.id.peraturan);
        deskripsi = (EditText) view.findViewById(R.id.deskripsi);
        next7 = (MyTextView) view.findViewById(R.id.next7);
        checkin = (MyTextView) view.findViewById(R.id.checkin);
        checkout = (MyTextView) view.findViewById(R.id.checkout);

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        checkin.setText( "" + selectedHour + ":" + selectedMinute);
                    }
                }, 24, 60, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        checkout.setText( "" + selectedHour + ":" + selectedMinute);
                    }
                }, 24, 60, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        if (peraturan.getText().equals("") && deskripsi.getText().equals("")) {
            peraturan.setText("-");
            deskripsi.setText("-");
        }
//        next7.setOnClickListener(this);
        next7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ti = checkin.getText().toString();
                String to = checkout.getText().toString();
                Model_Detail argument = getArguments().getParcelable("next5");
                argument.setPeraturan(peraturan.getText().toString());
                argument.setDeskripsi(deskripsi.getText().toString());
                argument.setCheckin(ti);
                argument.setCheckout(to);
                Bundle bundle = new Bundle();
                bundle.putParcelable("next6", argument);
                AddUnit6 fNext7 = new AddUnit6();
                fNext7.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.content_frame_next, fNext7).addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
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
