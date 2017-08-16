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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddUnit extends AppCompatActivity {
    final String FIREBASE_DATABASE = "https://my-project-1479543973833.firebaseio.com/";
    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase mFirebaseInstance;
    StorageReference storageReference = storage.getReferenceFromUrl("gs://my-project-1479543973833.appspot.com");
    int PICK_IMAGE_REQUEST = 1;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);

        final EditText harga_ = (EditText) findViewById(R.id.harga);
        final EditText lokasi_ = (EditText) findViewById(R.id.lokasi);
        Button pilih = (Button) findViewById(R.id.pilih);
        Button upload = (Button) findViewById(R.id.upload);


        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(filePath != null) {
                    final String UID = User.getUID();
                    mFirebaseInstance = FirebaseDatabase.getInstance();
                    final DatabaseReference referenceChild = mFirebaseInstance.getReference().child("Home").child(UID);
                    final DatabaseReference refPush = referenceChild.push();
                    final String keynum = refPush.getKey();
                    final StorageReference childref = storageReference.child("User").child(UID).child(keynum+".jpg");
                    UploadTask uploadTask = childref.putFile(filePath);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            String dl = downloadUrl.toString();
                            Toast.makeText(AddUnit.this, "Upload sukses", Toast.LENGTH_SHORT).show();
                            String harga = harga_.getText().toString().trim();
                            String lokasi = lokasi_.getText().toString().trim();

                            Model model = new Model();
                            model.setHarga(harga);
                            model.setLokasi(lokasi);
                            model.setUrl(dl);
//                            model.setUID_(UID);

//                            Model m = coba(dl, harga, lokasi, UID);
                            referenceChild.child(keynum).setValue(model);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddUnit.this, "Upload Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(AddUnit.this, "ambigu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = (ImageView)findViewById(R.id.gambar);
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

    public Model coba (String uri, String harga, String lokasi, String UID) {
        Model model = new Model();
//        model.setPrice(harga);
        model.setLokasi(lokasi);
        model.setUrl(uri);
        return model;
//        referenceChild.setValue(model);
    }
}
