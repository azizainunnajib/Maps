package com.example.azizainun.maps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;

import static com.example.azizainun.maps.R.id.cancel_action;
import static com.example.azizainun.maps.R.id.index_entity_types;
import static com.example.azizainun.maps.R.id.nama_editprofil0;
import static com.example.azizainun.maps.R.id.time;
import static com.example.azizainun.maps.R.id.total_toilet;
import static com.example.azizainun.maps.R.id.view_offset_helper;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


public class AddUnit2 extends Fragment implements View.OnClickListener {

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

    public AddUnit2() {
        // Required empty public constructor
    }

    String Ttamu;
    String Tkamar;
    String Tkasur;
    String Jkasur;
    String Ttoilet;

    MyTextView next3;

    MyTextView total_tamu_0;
    MyTextView total_kamar_0;
    MyTextView total_kasur_0;
    MyTextView kasur;
    MyTextView total_toilet_0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_unit2, container, false);

        ImageView all = (ImageView) view.findViewById(R.id.all);

        LinearLayout total_tamu = (LinearLayout) view.findViewById(R.id.total_tamu);
        LinearLayout total_kamar = (LinearLayout) view.findViewById(R.id.total_kamar);
        LinearLayout total_kasur = (LinearLayout) view.findViewById(R.id.total_kasur);
        LinearLayout option_kasur = (LinearLayout) view.findViewById(R.id.option_kasur);
        LinearLayout total_toilet = (LinearLayout) view.findViewById(R.id.total_toilet);

        total_tamu_0 = (MyTextView) view.findViewById(R.id.total_tamu_0);
        total_kamar_0 = (MyTextView) view.findViewById(R.id.total_kamar_0);
        total_kasur_0 = (MyTextView) view.findViewById(R.id.total_kasur_0);
        kasur = (MyTextView) view.findViewById(R.id.kasur);
        total_toilet_0 = (MyTextView) view.findViewById(R.id.total_toilet_0);

        LinearLayout pilihan_tamu = (LinearLayout) view.findViewById(R.id.pilihan_tamu);
        LinearLayout pilihan_kamar = (LinearLayout) view.findViewById(R.id.pilihan_kamar);
        LinearLayout pilihan_kasur = (LinearLayout) view.findViewById(R.id.pilihan_kasur);
        LinearLayout jenis_kasur = (LinearLayout) view.findViewById(R.id.jenis_kasur);
        LinearLayout pilihan_toilet = (LinearLayout) view.findViewById(R.id.pilihan_toilet);

        TextView total_tamu_1 = (TextView) view.findViewById(R.id.total_tamu_1);
        TextView total_tamu_2 = (TextView) view.findViewById(R.id.total_tamu_2);
        TextView total_tamu_3 = (TextView) view.findViewById(R.id.total_tamu_3);
        TextView total_tamu_4 = (TextView) view.findViewById(R.id.total_tamu_4);
        TextView total_tamu_5 = (TextView) view.findViewById(R.id.total_tamu_5);
        TextView total_tamu_6 = (TextView) view.findViewById(R.id.total_tamu_6);
        TextView total_tamu_7 = (TextView) view.findViewById(R.id.total_tamu_7);
        TextView total_tamu_8 = (TextView) view.findViewById(R.id.total_tamu_8);

        TextView total_kamar_1 = (TextView) view.findViewById(R.id.total_kamar_1);
        TextView total_kamar_2 = (TextView) view.findViewById(R.id.total_kamar_2);
        TextView total_kamar_3 = (TextView) view.findViewById(R.id.total_kamar_3);
        TextView total_kamar_4 = (TextView) view.findViewById(R.id.total_kamar_4);
        TextView total_kamar_5 = (TextView) view.findViewById(R.id.total_kamar_5);
        TextView total_kamar_6 = (TextView) view.findViewById(R.id.total_kamar_6);
        TextView total_kamar_7 = (TextView) view.findViewById(R.id.total_kamar_7);
        TextView total_kamar_8 = (TextView) view.findViewById(R.id.total_kamar_8);

        TextView total_kasur_1 = (TextView) view.findViewById(R.id.total_kasur_1);
        TextView total_kasur_2 = (TextView) view.findViewById(R.id.total_kasur_2);
        TextView total_kasur_3 = (TextView) view.findViewById(R.id.total_kasur_3);
        TextView total_kasur_4 = (TextView) view.findViewById(R.id.total_kasur_4);
        TextView total_kasur_5 = (TextView) view.findViewById(R.id.total_kasur_5);
        TextView total_kasur_6 = (TextView) view.findViewById(R.id.total_kasur_6);
        TextView total_kasur_7 = (TextView) view.findViewById(R.id.total_kasur_7);
        TextView total_kasur_8 = (TextView) view.findViewById(R.id.total_kasur_8);

        TextView jenis_kasur_1 = (TextView) view.findViewById(R.id.jenis_kasur_1);
        TextView jenis_kasur_2 = (TextView) view.findViewById(R.id.jenis_kasur_2);
        TextView jenis_kasur_3 = (TextView) view.findViewById(R.id.jenis_kasur_3);

        TextView total_toilet_1 = (TextView) view.findViewById(R.id.total_toilet_1);
        TextView total_toilet_2 = (TextView) view.findViewById(R.id.total_toilet_2);
        TextView total_toilet_3 = (TextView) view.findViewById(R.id.total_toilet_3);
        TextView total_toilet_4 = (TextView) view.findViewById(R.id.total_toilet_4);
        TextView total_toilet_5 = (TextView) view.findViewById(R.id.total_toilet_5);
        TextView total_toilet_6 = (TextView) view.findViewById(R.id.total_toilet_6);
        TextView total_toilet_7 = (TextView) view.findViewById(R.id.total_toilet_7);
        TextView total_toilet_8 = (TextView) view.findViewById(R.id.total_toilet_8);

        next3 = (MyTextView) view.findViewById(R.id.next3);

        all.setOnClickListener(this);

        total_tamu.setOnClickListener(this);
        total_kamar.setOnClickListener(this);
        total_kasur.setOnClickListener(this);
        option_kasur.setOnClickListener(this);
        total_toilet.setOnClickListener(this);

        total_tamu_1.setOnClickListener(this);
        total_tamu_2.setOnClickListener(this);
        total_tamu_3.setOnClickListener(this);
        total_tamu_4.setOnClickListener(this);
        total_tamu_5.setOnClickListener(this);
        total_tamu_6.setOnClickListener(this);
        total_tamu_7.setOnClickListener(this);
        total_tamu_8.setOnClickListener(this);

        total_kamar_1.setOnClickListener(this);
        total_kamar_2.setOnClickListener(this);
        total_kamar_3.setOnClickListener(this);
        total_kamar_4.setOnClickListener(this);
        total_kamar_5.setOnClickListener(this);
        total_kamar_6.setOnClickListener(this);
        total_kamar_7.setOnClickListener(this);
        total_kamar_8.setOnClickListener(this);

        total_kasur_1.setOnClickListener(this);
        total_kasur_2.setOnClickListener(this);
        total_kasur_3.setOnClickListener(this);
        total_kasur_4.setOnClickListener(this);
        total_kasur_5.setOnClickListener(this);
        total_kasur_6.setOnClickListener(this);
        total_kasur_7.setOnClickListener(this);
        total_kasur_8.setOnClickListener(this);

        jenis_kasur_1.setOnClickListener(this);
        jenis_kasur_2.setOnClickListener(this);
        jenis_kasur_3.setOnClickListener(this);

        total_toilet_1.setOnClickListener(this);
        total_toilet_2.setOnClickListener(this);
        total_toilet_3.setOnClickListener(this);
        total_toilet_4.setOnClickListener(this);
        total_toilet_5.setOnClickListener(this);
        total_toilet_6.setOnClickListener(this);
        total_toilet_7.setOnClickListener(this);
        total_toilet_8.setOnClickListener(this);


        next3.setOnClickListener(this);

        total_tamu_0.addTextChangedListener(textWatcher);
        total_kamar_0.addTextChangedListener(textWatcher);
        total_kasur_0.addTextChangedListener(textWatcher);
        kasur.addTextChangedListener(textWatcher);
        total_toilet_0.addTextChangedListener(textWatcher);

        Cek();

        return view;
    }

    @Override
    public void onClick(View v) {
       /* MyTextView total_tamu_0 = (MyTextView) getView().findViewById(R.id.total_tamu_0);
        MyTextView total_kamar_0 = (MyTextView) getView().findViewById(R.id.total_kamar_0);
        MyTextView total_kasur_0 = (MyTextView) getView().findViewById(R.id.total_kasur_0);
        MyTextView kasur = (MyTextView) getView().findViewById(R.id.kasur);*/

        LinearLayout pilihan_tamu = (LinearLayout) getView().findViewById(R.id.pilihan_tamu);
        LinearLayout pilihan_kamar = (LinearLayout) getView().findViewById(R.id.pilihan_kamar);
        LinearLayout pilihan_kasur = (LinearLayout) getView().findViewById(R.id.pilihan_kasur);
        LinearLayout jenis_kasur = (LinearLayout) getView().findViewById(R.id.jenis_kasur);
        LinearLayout pilihan_toilet = (LinearLayout) getView().findViewById(R.id.pilihan_toilet);

        switch (v.getId()) {
            case R.id.all:
                pilihan_tamu.setVisibility(View.GONE);
                pilihan_kamar.setVisibility(View.GONE);
                pilihan_kasur.setVisibility(View.GONE);
                jenis_kasur.setVisibility(View.GONE);
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_tamu:
                if(pilihan_tamu.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.VISIBLE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);

                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);
                }
                break;
            case R.id.total_kamar:
                if(pilihan_kamar.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.VISIBLE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);
                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);
                }
                break;
            case R.id.total_kasur:
                if(pilihan_kasur.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.VISIBLE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);
                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);
                }
                break;
            case R.id.option_kasur:
                if(jenis_kasur.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.VISIBLE);
                    pilihan_toilet.setVisibility(View.GONE);
                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);
                }
                break;
            case R.id.total_toilet:
                if(pilihan_toilet.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.VISIBLE);
                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                    pilihan_toilet.setVisibility(View.GONE);
                }
                break;
            case R.id.total_tamu_1:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_tamu_2:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_tamu_3:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_tamu_4:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_tamu_5:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_tamu_6:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_tamu_7:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_tamu_8:
                total_tamu_0.setText(((TextView) v).getText());
                Ttamu = total_tamu_0.getText().toString();
                pilihan_tamu.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_1:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_2:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_3:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_4:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_5:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_6:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_7:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kamar_8:
                total_kamar_0.setText(((TextView) v).getText());
                Tkamar = total_kamar_0.getText().toString();
                pilihan_kamar.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_1:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_2:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_3:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_4:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_5:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_6:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_7:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_kasur_8:
                total_kasur_0.setText(((TextView) v).getText());
                Tkasur = total_kasur_0.getText().toString();
                pilihan_kasur.setVisibility(View.GONE);
                break;
            case R.id.jenis_kasur_1:
                kasur.setText(((TextView) v).getText());
                Jkasur = kasur.getText().toString();
                jenis_kasur.setVisibility(View.GONE);
                break;
            case R.id.jenis_kasur_2:
                kasur.setText(((TextView) v).getText());
                Jkasur = kasur.getText().toString();
                jenis_kasur.setVisibility(View.GONE);
                break;
            case R.id.jenis_kasur_3:
                kasur.setText(((TextView) v).getText());
                Jkasur = kasur.getText().toString();
                jenis_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_1:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_2:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_3:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_4:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_5:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_6:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_7:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.total_toilet_8:
                total_toilet_0.setText(((TextView) v).getText());
                Ttoilet = total_toilet_0.getText().toString();
                pilihan_toilet.setVisibility(View.GONE);
                break;
            case R.id.next3:
                Next3(Ttamu, Tkamar, Tkasur, Jkasur, Ttoilet);
                break;
        }
    }

    public void Next3(String total_tamu, String total_kamar, String total_kasur, String Jkasur, String total_toilet) {
        Toast.makeText(getContext(), "oke", Toast.LENGTH_SHORT).show();
        Model_Detail argument = getArguments().getParcelable("next");

        total_tamu =total_tamu.substring(0,1).trim();
        total_kamar = total_kamar.substring(0,1).trim();
        total_kasur = total_kasur.substring(0,1).trim();
        total_toilet = total_toilet.substring(0,1).trim();

        int Ttamu = parseInt(total_tamu);
        int Tkamar = parseInt(total_kamar);
        int Tkasur = parseInt(total_kasur);
        int Ttoilet = parseInt(total_toilet);

        String k = argument.getTipe_tempat();
        argument.setTotal_tamu(Ttamu);
        argument.setTotal_kamar(Tkamar);
        argument.setTotal_kasur(Tkasur);
        argument.setJenis_kasur(Jkasur);
        argument.setTotal_toilet(Ttoilet);
        Bundle bundle = new Bundle();
        bundle.putParcelable("next3", argument);
        AddUnit3 fNext3 = new AddUnit3();
        fNext3.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame_next, fNext3).addToBackStack(null);
        ft.commit();
//        Model_Detail lm = argument;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.getArguments().clear();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void Cek(){
        String s1 = total_tamu_0.getText().toString();
        String s2 = total_kamar_0.getText().toString();
        String s3 = total_kasur_0.getText().toString();
        String s4 = kasur.getText().toString();
        String s5 = total_toilet_0.getText().toString();
        next3.setEnabled(!s1.trim().isEmpty() && !s2.trim().isEmpty() && !s3.trim().isEmpty()
                && !s4.trim().isEmpty() &&!s5.trim().isEmpty());
    }
}
