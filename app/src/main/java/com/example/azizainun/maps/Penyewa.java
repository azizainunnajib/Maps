package com.example.azizainun.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/*import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;*/
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by aziza on 6/23/2017.
 */
public class Penyewa extends Fragment {

    final String FIREBASE_DATABASE = "https://my-project-1479543973833.firebaseio.com/";

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    protected FirebaseAuth mFirebaseAuth;
    protected FirebaseUser mFirebaseUser;
    private String Userid;
    private EditText test1, test2;
    private int PICK_IMAGE_REQUEST = 1409;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final String TAG = Penyewa.class.getSimpleName();
        View view = inflater.inflate(R.layout.penyewa, container, false);
        final EditText test1 = (EditText) view.findViewById(R.id.test1);
        final EditText test2 = (EditText) view.findViewById(R.id.test2);
        Button upload = (Button)view.findViewById(R.id.upload__);
        Button pilih= (Button) view.findViewById(R.id.choose_images);
        final TextView hasil1 = (TextView) view.findViewById(R.id.hasil1);
        TextView hasil2 = (TextView) view.findViewById(R.id.hasil2);
        ImageView pilihgambar = (ImageView) view.findViewById(R.id.pilihgambar);
        final ImageView hasilgambar = (ImageView) view.findViewById(R.id.hasilgambar);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Stest1 = test1.getText().toString().trim();
                String Stest2 = test2.getText().toString().trim();

                final Model model = new Model();
                final Model cons = new Model();

                model.setPrice(Stest1);
                model.setLokasi(Stest2);
                mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseUser = mFirebaseAuth.getCurrentUser();
                Userid = mFirebaseUser.getUid();
                cons.setUID(Userid);
                String y = cons.getUID();
                DatabaseReference push = mFirebaseInstance.getReference().child("Home").child(y);
                push.setValue(model);
                String postId = push.getKey();
                mFirebaseInstance.getReference().child("Home").child(postId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Model sewa = dataSnapshot.getValue(Model.class);
                        System.out.println(sewa);
                        String klm = sewa.getLokasi();
                        hasil1.setText(klm);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST);
            }
        });

        Glide
                .with(view.getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/my-project-1479543973833.appspot.com/o/images%2Fimage.jpg?alt=media&token=6073e4c4-5c6e-47e3-9427-90a1a99f151d")
                .placeholder(R.drawable.sukses)
                .centerCrop()
                .crossFade()
                .dontAnimate()
                .into(hasilgambar);
        return view;
    }
}
