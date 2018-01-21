package com.example.azizainun.maps;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.azizainun.maps.AddUnit.AddUnit0;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
        String UID = User.Userid;

        StorageReference pathstorage = storage.getReference().child("User/" + UID + "/profil.jpg");
        final ImageView image = (ImageView) provilView.findViewById(R.id.gambar_profil);

        pathstorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getContext())
                        .load(uri)
                        .asBitmap()
                        .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                        .into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                image.setImageResource(R.drawable.foto_person);
            }
        });
// Load the image using Glide

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