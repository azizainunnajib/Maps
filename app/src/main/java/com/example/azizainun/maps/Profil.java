package com.example.azizainun.maps;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profil extends Fragment implements View.OnClickListener {
    MyTextView nama_profil;
    MyTextView kelola_tempat_sewa;
    MyTextView setting;
    MyTextView bantuan;

    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View provilView = inflater.inflate(R.layout.activity_profil,container, false);

        RelativeLayout editProfil = (RelativeLayout) provilView.findViewById(R.id.profil);
        nama_profil = (MyTextView) provilView.findViewById(R.id.nama_profil);
        kelola_tempat_sewa = (MyTextView) provilView.findViewById(R.id.kelola_tempat);
        setting = (MyTextView) provilView.findViewById(R.id.setting);
        bantuan = (MyTextView) provilView.findViewById(R.id.bantuan);

        editProfil.setOnClickListener(this);
        nama_profil.setOnClickListener(this);
        kelola_tempat_sewa.setOnClickListener(this);
        setting.setOnClickListener(this);
        bantuan.setOnClickListener(this);


        StorageReference pathstorage = storage.getReference().child("images/image.jpg");
        ImageView image = (ImageView) provilView.findViewById(R.id.gambar_profil);

// Load the image using Glide
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(pathstorage)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(image);

//        penyewa.setText(User.getUID());
        return provilView;
    }
    /*public void editProfil (View v) {

    }*/
    @Override
    public void onClick (View v) {
        switch(v.getId()) {
            case R.id.profil:
                Toast.makeText(getContext(), "edit profil", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getContext(), EditProfil.class);
                startActivity(i1);
                break;
            case R.id.kelola_tempat:
                Intent i2 = new Intent(getContext(), AddUnit0.class);
                startActivity(i2);
                break;
            case R.id.setting:
                break;
            case R.id.bantuan:
                break;

        }


//        Fragment fragment = new EditProfil1();
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.content_frame, fragment).addToBackStack(null);
//        ft.commit();
    }
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        getActivity().setTitle("homeses");
//    }
}