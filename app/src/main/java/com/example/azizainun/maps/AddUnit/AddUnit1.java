package com.example.azizainun.maps.AddUnit;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.azizainun.maps.Model_Detail;
import com.example.azizainun.maps.MyTextView;
import com.example.azizainun.maps.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddUnit1 extends Fragment implements View.OnClickListener {
    final String FIREBASE_DATABASE = "https://my-project-1479543973833.firebaseio.com/";
    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase mFirebaseInstance;
    StorageReference storageReference = storage.getReferenceFromUrl("gs://my-project-1479543973833.appspot.com");
    int PICK_IMAGE_REQUEST = 1;
    List<Uri> filePath;
    GridView gridView;
    ArrayList<Model_Detail> model_detailArrayList;
    int Imag [] = {R.drawable.sukses, R.drawable.ic_exit, R.drawable.ic_setting, R.drawable.ic_home, R.drawable.sukses, R.drawable.ic_setting, R.drawable.ic_home, R.drawable.sukses};
    String tipe_tempat;
    String tipe_bangunan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_add_unit1, container, false);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/Raleway-Light.ttf");
        MyTextView tv1 = (MyTextView) view.findViewById(R.id.textView1);
        MyTextView tv2 = (MyTextView) view.findViewById(R.id.textView2);
        MyTextView tv3 = (MyTextView) view.findViewById(R.id.textView3);
        String tv1s = tv1.getText().toString();
        String tv2s = tv2.getText().toString();
        String tv3s = tv3.getText().toString();
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        Button next2 = (Button) view.findViewById(R.id.next2);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        next2.setOnClickListener(this);
        next2.setTypeface(typeface);
        /*Button next = (Button) findViewById(R.id.next);
//        next.setOnClickListener(this);
        final EditText harga_ = (EditText) findViewById(R.id.harga);
        final EditText lokasi_ = (EditText) findViewById(R.id.lokasi);

        Button pilih = (Button) findViewById(R.id.pilih);
        Button upload = (Button) findViewById(R.id.upload);*/


        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String harga = harga_.getText().toString();
                Model model = new Model();
//                ArrayList<Model> model = new ArrayList<Model>();
                model.setLokasi(harga);
                Bundle bundle = new Bundle();
                bundle.putSerializable("next", model);

//                AddUnitAkhir fNext = new AddUnitAkhir();
                Fragment fNext = new AddUnitAkhir();
                fNext.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame_next, fNext).addToBackStack(null);
                ft.commit();

            }
        });

        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(AddUnit1.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(50)
                        .imageEngine(new GlideEngine())
                        .forResult(PICK_IMAGE_REQUEST);
                *//*Intent intent = new Intent();
                intent.setType("image");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST);*//*
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filePath != null) {
                    int i =0;
                    for (Uri temp : filePath) {
                        final String UID = User.getUID();
                        mFirebaseInstance = FirebaseDatabase.getInstance();
                        final DatabaseReference refHome = mFirebaseInstance.getReference().child("home").child(UID);
                        final DatabaseReference refUser = mFirebaseInstance.getReference().child("User").child(UID).child("1");
                        final DatabaseReference refPush = refHome.push();
                        final String keynum = refPush.getKey();
                        final StorageReference childref = storageReference.child("User").child(UID).child(i + ".jpg");
                        UploadTask uploadTask = childref.putFile(temp);
                        if (i==1) {
                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    String dl = downloadUrl.toString();
                                    ArrayList<String> setUrl = new ArrayList<>();
                                    setUrl.add(dl);
                                    String harga = harga_.getText().toString().trim();
                                    String lokasi = lokasi_.getText().toString().trim();
                                    Model model = new Model();
                                    model.setHarga(harga);
                                    model.setLokasi(lokasi);
                                    model.setUrl(setUrl);
                                    refHome.child(keynum).setValue(setUrl);
                                    refUser.setValue(setUrl);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddUnit1.this, "Upload Failed, Please Check your Internet" + e, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    String dl = downloadUrl.toString();
                                    ArrayList<String> setUrl = new ArrayList<>();
                                    setUrl.add(dl);
                                    String harga = harga_.getText().toString().trim();
                                    String lokasi = lokasi_.getText().toString().trim();
                                    Model model = new Model();
                                    model.setHarga(harga);
                                    model.setLokasi(lokasi);
                                    model.setUrl(setUrl);
                                    refUser.child(keynum).setValue(setUrl);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddUnit1.this, "Upload Failed, Please Check your Internet" + e, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        Toast.makeText(AddUnit1.this, "Upload sukses", Toast.LENGTH_SHORT).show();
                        i++;
                    }
                } else {
                    Toast.makeText(AddUnit1.this, "ambigu", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        return view;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Uri> mSelected;
        List<Bitmap> selectedImages = new ArrayList<>();
        GridView gridView = (GridView)findViewById(R.id.gridGallery);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            filePath = mSelected;
            for (Uri temp : mSelected) {
                String stringTemp = temp.toString();
                Bitmap bmp = compressImage(stringTemp);
                selectedImages.add(bmp);
            }
            GridView_Adapter adapter = new GridView_Adapter(AddUnit1.this, selectedImages);
            gridView.setAdapter(adapter);

                try {
                } catch(Exception e){
                    e.printStackTrace();
                }
            memoryCache ca = new memoryCache();
            ca.clear();
        }
    }*/

    /*public Model coba (ArrayList<String> uri, String harga, String lokasi, String UID) {
        Model model = new Model();
//        model.setPrice(harga);
        model.setLokasi(lokasi);
        model.setUrl(uri);
        return model;
    }*/

    /*@Override
    protected void onStop() {
        super.onStop();
        try {
            trimCache(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            trimCache(getContext());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private  void checkFieldsForEmptyValues(String tipe_tempat, String tipe_bangunan){
        if (tipe_tempat != null && !tipe_bangunan.equals("Pilih Tipe Bangunan")) {
            Model_Detail model_detail = new Model_Detail();
            model_detail.setTipe_tempat(tipe_tempat);
            model_detail.setTipe_bangunan(tipe_bangunan);
            Toast.makeText(getContext(), "oke", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putParcelable("next", model_detail);
            AddUnit2 fNext2 = new AddUnit2();
            fNext2.setArguments(bundle);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame_next, fNext2).addToBackStack(null);
            ft.commit();
        } else {
            Toast.makeText(getContext(), "Pilihan Harus Terisi Semua", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        final MyTextView tv1 = (MyTextView) getView().findViewById(R.id.textView1);
        final MyTextView tv2 = (MyTextView) getView().findViewById(R.id.textView2);
        final MyTextView tv3 = (MyTextView) getView().findViewById(R.id.textView3);
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        final Handler handler = new Handler();
        switch (v.getId()) {
            case R.id.textView1:
                tv1.setClickable(false);
                tv2.setClickable(true);
                tv3.setClickable(true);
                Toast.makeText(getContext(), "TV 1", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        tv1.setBackgroundResource(R.drawable.disable_button);
                        tv2.setBackgroundResource(R.drawable.ripple_button);
                        tv3.setBackgroundResource(R.drawable.ripple_button);
                    }
                }, 200);
                tipe_tempat = tv1.getText().toString();
                break;
            case R.id.textView2:
                tv1.setClickable(true);
                tv2.setClickable(false);
                tv3.setClickable(true);
                Toast.makeText(getContext(), "TV 2", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        tv1.setBackgroundResource(R.drawable.ripple_button);
                        tv2.setBackgroundResource(R.drawable.disable_button);
                        tv3.setBackgroundResource(R.drawable.ripple_button);
                    }
                }, 200);
                tipe_tempat = tv2.getText().toString();
                break;
            case R.id.textView3:
                tv1.setClickable(true);
                tv2.setClickable(true);
                tv3.setClickable(false);
                Toast.makeText(getContext(), "TV 1", Toast.LENGTH_SHORT).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        tv1.setBackgroundResource(R.drawable.ripple_button);
                        tv2.setBackgroundResource(R.drawable.ripple_button);
                        tv3.setBackgroundResource(R.drawable.disable_button);
                    }
                }, 200);
                tipe_tempat = tv3.getText().toString();
                break;
            case R.id.next2:
                tipe_bangunan = String.valueOf(spinner.getSelectedItem());
                checkFieldsForEmptyValues(tipe_tempat, tipe_bangunan);
        }
    }

}
