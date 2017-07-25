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
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profil extends Fragment implements View.OnClickListener {
    public ImageView imageProfil;
    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    protected StorageReference storageReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/my-project-1479543973833.appspot.com/o/images%2Fimage.jpg?alt=media&token=6073e4c4-5c6e-47e3-9427-90a1a99f151d");
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View provilView = inflater.inflate(R.layout.activity_profil,container, false);

        RelativeLayout editProfil = (RelativeLayout) provilView.findViewById(R.id.profil);
        editProfil.setOnClickListener(this);

        StorageReference pathstorage = storage.getReference().child("images/image.jpg");
        ImageView image = (ImageView) provilView.findViewById(R.id.gambar_profil);

// Load the image using Glide
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(pathstorage)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(image);
        return provilView;
    }

    /*public void editProfil (View v) {

    }*/
    @Override
    public void onClick (View v) {
        Toast.makeText(getContext(), "edit profil", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getContext(), EditProfil.class);
        startActivity(i);
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