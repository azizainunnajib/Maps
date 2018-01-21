package com.example.azizainun.maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

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

        final String UID = User.getUID();
        final DatabaseReference pushProfil = mFirebaseInstance.getInstance().getReference().child("User/" + UID + "/profil");

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
                            snama = nama.getText().toString();
                            semail = email.getText().toString();
                            shandphone = handphone.getText().toString();
                            srekening = rekening.getText().toString();
                            sdeskripsi = deskripsi.getText().toString();
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
                    Model_profil model_profil = new Model_profil();
                    snama = nama.getText().toString();
                    semail = email.getText().toString();
                    shandphone = handphone.getText().toString();
                    srekening = rekening.getText().toString();
                    sdeskripsi = deskripsi.getText().toString();
                    model_profil.setNama(snama);
                    model_profil.setEmail(semail);
                    model_profil.setHandphone(shandphone);
                    model_profil.setRekening(srekening);
                    model_profil.setDeskripsi(sdeskripsi);
                    pushProfil.setValue(model_profil);
                    progressDialog.dismiss();
                    Toast.makeText(EditProfil.this, "Sukses", Toast.LENGTH_SHORT).show();
                }
            }
        });


        StorageReference pathstorage = storage.getReference().child("User/" +UID+ "/profil.jpg");
        image = (ImageView) findViewById(R.id.gambarprofil_editprofil);
        pathstorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext())
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
        Log.i("nama", "sampai1");
        new Database().mReadDataOnce("User/" + UID, new Database.OnGetDataListener() {
            @Override
            public void onStart() {
                Log.i("nama", "sampai2");
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                Log.i("nama", "sampai3");
                for (DataSnapshot snapshot:data.getChildren()) {
                    Model_profil item = snapshot.getValue(Model_profil.class);
                    nama.setText(item.getNama());
                    deskripsi.setText(item.getDeskripsi());
                    email.setText(item.getEmail());
                    handphone.setText(item.getHandphone());
                    rekening.setText(item.getRekening());
                    Log.i("nama", "sampai8");
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.i("nama", "sampai4");
            }
        });

// Load the image using Glide
        /*if (pathstorage.equals("")) {
            Glide.with(this)
                    .using(new FirebaseImageLoader())
                    .load(pathstorage)
                    .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                    .into(image);
        }*/
        Log.i("nama", "sampai5");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = findViewById(R.id.gambarprofil_editprofil);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting image to ImageView
                final ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                byte[] byteArray = stream.toByteArray();

                String encodeded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                byte[] decodedString = Base64.decode(encodeded, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
