package com.example.azizainun.maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static com.example.azizainun.maps.User.mFirebaseInstance;

public class EditProfil extends AppCompatActivity {

    int PICK_IMAGE_REQUEST = 1342;
    Uri filePath;ImageView image;
    ProgressDialog progressDialog;
    String tera;
    EditText nama, email, rekening, handphone, deskripsi;
    String snama, semail, srekening, shandphone, sdeskripsi;

    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    protected StorageReference storageReference = storage.getReferenceFromUrl("gs://my-project-1479543973833.appspot.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Raleway-Light.ttf");
        nama = (EditText) findViewById(R.id.nama_editprofil1);
        email = (EditText) findViewById(R.id.email_editprofil1);
        rekening = (EditText) findViewById(R.id.rek_editprofil1);
        handphone = (EditText) findViewById(R.id.hp_editprofil1);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        nama.setTypeface(typeface);
        email.setTypeface(typeface);
        rekening.setTypeface(typeface);
        handphone.setTypeface(typeface);
        deskripsi.setTypeface(typeface);
        snama = nama.getText().toString();
        semail = email.getText().toString();
        shandphone = handphone.getText().toString();
        srekening = rekening.getText().toString();
        sdeskripsi = deskripsi.getText().toString();

        final String UID = User.getUID();
        final DatabaseReference pushProfil = mFirebaseInstance.getInstance().getReference().child("User/" + UID + "profil");

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
                    StorageReference childRef = storageReference.child("User/" +UID+ "/profil.jpg");
                    UploadTask uploadTask = childRef.putFile(filePath);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Model_profil model_profil = new Model_profil();
                            model_profil.setNama(snama);
                            model_profil.setEmail(semail);
                            model_profil.setHandphone(shandphone);
                            model_profil.setRekening(srekening);
                            model_profil.setDeskripsi(sdeskripsi);
                            pushProfil.setValue(model_profil);
                            progressDialog.dismiss();
                            Toast.makeText(EditProfil.this, "Sukses", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditProfil.this, "Upload Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(EditProfil.this, "Tidak ada yang disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });


        StorageReference pathstorage = storage.getReference().child("User/" +UID+ "/profil.jpg");
        image = (ImageView) findViewById(R.id.gambarprofil_editprofil);

// Load the image using Glide
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(pathstorage)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(image);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = (ImageView)findViewById(R.id.gambarprofil_editprofil);
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
