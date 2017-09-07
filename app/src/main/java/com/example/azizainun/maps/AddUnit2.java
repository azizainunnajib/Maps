package com.example.azizainun.maps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
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
import static com.example.azizainun.maps.R.id.nama_editprofil0;
import static com.example.azizainun.maps.R.id.time;


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

    Button next3;

    MyTextView total_tamu_0;
    MyTextView total_kamar_0;
    MyTextView total_kasur_0;
    MyTextView kasur;

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

        total_tamu_0 = (MyTextView) view.findViewById(R.id.total_tamu_0);
        total_kamar_0 = (MyTextView) view.findViewById(R.id.total_kamar_0);
        total_kasur_0 = (MyTextView) view.findViewById(R.id.total_kasur_0);
        kasur = (MyTextView) view.findViewById(R.id.kasur);

        LinearLayout pilihan_tamu = (LinearLayout) view.findViewById(R.id.pilihan_tamu);
        LinearLayout pilihan_kamar = (LinearLayout) view.findViewById(R.id.pilihan_kamar);
        LinearLayout pilihan_kasur = (LinearLayout) view.findViewById(R.id.pilihan_kasur);
        LinearLayout jenis_kasur = (LinearLayout) view.findViewById(R.id.jenis_kasur);

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

        next3 = (Button) view.findViewById(R.id.next3);

        all.setOnClickListener(this);

        total_tamu.setOnClickListener(this);
        total_kamar.setOnClickListener(this);
        total_kasur.setOnClickListener(this);
        option_kasur.setOnClickListener(this);

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

        next3.setOnClickListener(this);

        total_tamu_0.addTextChangedListener(textWatcher);
        total_kamar_0.addTextChangedListener(textWatcher);
        total_kasur_0.addTextChangedListener(textWatcher);
        kasur.addTextChangedListener(textWatcher);

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

        switch (v.getId()) {
            case R.id.all:
                pilihan_tamu.setVisibility(View.GONE);
                pilihan_kamar.setVisibility(View.GONE);
                pilihan_kasur.setVisibility(View.GONE);
                jenis_kasur.setVisibility(View.GONE);
                break;
            case R.id.total_tamu:
                if(pilihan_tamu.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.VISIBLE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);

                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                }
                break;
            case R.id.total_kamar:
                if(pilihan_kamar.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.VISIBLE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                }
                break;
            case R.id.total_kasur:
                if(pilihan_kasur.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.VISIBLE);
                    jenis_kasur.setVisibility(View.GONE);
                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
                }
                break;
            case R.id.option_kasur:
                if(jenis_kasur.getVisibility() == View.GONE){
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.VISIBLE);
                } else {
                    pilihan_tamu.setVisibility(View.GONE);
                    pilihan_kamar.setVisibility(View.GONE);
                    pilihan_kasur.setVisibility(View.GONE);
                    jenis_kasur.setVisibility(View.GONE);
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
            case R.id.next3:
                Next3(Ttamu, Tkamar, Tkasur, Jkasur);
                break;
        }
    }

    public void Next3(String total_tamu_, String total_kamar, String total_kasur, String Jkasur) {
        Toast.makeText(getContext(), "oke", Toast.LENGTH_SHORT).show();
        Model_Detail argument = getArguments().getParcelable("next");
//        Model_Detail model_detail =(Model_Detail) argument.get("next");
        String k = argument.getTipe_tempat();
        argument.setLokasi(total_tamu_);
        Bundle bundle = new Bundle();
        bundle.putParcelable("next3", argument);
        AddUnit3 fNext3 = new AddUnit3();
        fNext3.setArguments(bundle);
        Model_Detail lm = argument;
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
        next3.setEnabled(!s1.trim().isEmpty() && !s2.trim().isEmpty() && !s3.trim().isEmpty() && !s4.trim().isEmpty());
    }
}
