package com.example.azizainun.maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditProfil extends AppCompatActivity {

    int PICK_IMAGE_REQUEST = 1342;
    Uri filePath;
    ProgressDialog progressDialog;
    String tera;
    ImageView imageView;

    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    protected StorageReference storageReference = storage.getReferenceFromUrl("gs://my-project-1479543973833.appspot.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("uploading");
        RelativeLayout pickPP = (RelativeLayout)findViewById(R.id.editgambar_editprofil);
        pickPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST);
            }
        });

        TextView simpan = (TextView) findViewById(R.id.simpan_editprofil);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath !=null) {
                    progressDialog.show();
                    StorageReference childRef = storageReference.child("images/image.jpg");
                    UploadTask uploadTask = childRef.putFile(filePath);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditProfil.this, "Upload Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(EditProfil.this, "asgagaw", Toast.LENGTH_SHORT).show();
                }
            }
        });


        StorageReference pathstorage = storage.getReference().child("images/image.jpg");
        ImageView image = (ImageView) findViewById(R.id.gambarprofil_editprofil);

// Load the image using Glide
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(pathstorage)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(image);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView = (ImageView)findViewById(R.id.gambarprofil_editprofil);
        String a ="asdf";
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

    public String getURL() {
        return tera;
    }
}
