package com.example.azizainun.maps.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.azizainun.maps.R;
import com.example.azizainun.maps.User;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by aziza on 6/20/2017.
 */
public class EditProfil1 extends AppCompatActivity {
    int PICK_IMAGE_REQUEST = 123;
    Uri filePath;
    ImageView gambar;
    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button choose_image = findViewById(R.id.choose_image);
        final String UID = User.getUID();
//        RelativeLayout pickPP = (RelativeLayout) findViewById(R.id.editgambar_editprofil);
        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST);
            }
        });

        StorageReference pathstorage = storage.getReference().child("User/" +UID+ "/profil.jpg");
        ImageView image = (ImageView) findViewById(R.id.image_upload);

// Load the image using Glide
        if (pathstorage.equals("")) {
            Glide.with(this)
                    .using(new FirebaseImageLoader())
                    .load(pathstorage)
                    .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                    .into(image);
        }
        ImageView gambar = (ImageView)findViewById(R.id.gambarprofil_editprofil);
    }

    protected void startGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image*//*");
//        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = findViewById(R.id.image_upload);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting image to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
