package com.example.azizainun.maps.ui.AddUnit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azizainun.maps.services.Database_unggah;
import com.example.azizainun.maps.GridView_Adapter;
import com.example.azizainun.maps.Model.Model;
import com.example.azizainun.maps.Model.Model_Detail;
import com.example.azizainun.maps.R;
import com.example.azizainun.maps.User;
import com.example.azizainun.maps.memoryCache;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.azizainun.maps.User.mFirebaseInstance;

public class AddUnitAkhir extends Fragment implements View.OnClickListener{

    List<Uri> filePath;
    ArrayList<String> URi = new ArrayList<String>();
    ProgressDialog progressDialog;
    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    protected StorageReference storageReference = storage.getReferenceFromUrl("gs://my-project-1479543973833.appspot.com");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        final View view = inflater.inflate(R.layout.activity_add_unit_akhir, container, false);
        Button pilih = (Button) view.findViewById(R.id.pilihNX);
        Button upload = (Button) view.findViewById(R.id.uploadNX);
        final int PICK = 1;
        pilih.setOnClickListener(this);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Model_Detail model = getArguments().getParcelable("nextakhir");
                final Model modelHome = new Model();

                final String nama_tempat = model.getJudul();
                final String kota = model.getKotakab();
                final String harga = model.getHarga();
                final String tipe_bangunan = model.getTipe_bangunan();

                if (filePath !=null) {
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Menunggu..");
                    progressDialog.setTitle("Mengunggah");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    final String UID = User.getUID();

                    final DatabaseReference push = mFirebaseInstance.getInstance().getReference().child("User/"+ UID + "/Tempat_sewa/" + nama_tempat);
                    DatabaseReference push1 = push.push();
                    final String postidkey = push1.getKey();
                    final DatabaseReference pushHome = mFirebaseInstance.getInstance().getReference().child("DetailUnit/" + postidkey);
//                    push1.setValue(model);

                    int i = 1;

                    for (Uri photo:filePath) {
                        StorageReference childRef = storageReference.child("User/"+ UID + "/Tempat Sewa/"+ nama_tempat +"/"+ i +".jpg");
                        UploadTask uploadTask = childRef.putFile(photo);
                        /*if (i == 1){

                        } else {

                        }*/

                        new Database_unggah().mUnggahData(uploadTask, new Database_unggah.OnUnggahDataListener() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String rl = taskSnapshot.getDownloadUrl().toString();
                                URi.add(rl);
                                if (URi.size() == 1) {
                                    modelHome.setHarga(harga);
                                    modelHome.setKotakab(kota);
                                    modelHome.setUrl(URi.get(0));
                                    modelHome.setNama_tempat(nama_tempat);
                                    modelHome.setTipe_bangunan(tipe_bangunan);
                                    modelHome.setUid(UID);
                                    pushHome.setValue(modelHome);
                                }

                                if (URi.size() == filePath.size()) {
                                    model.setKeyid(postidkey);
                                    model.setUrl(URi);
                                    model.setUid_(UID);
                                    push.setValue(model);
                                    progressDialog.dismiss();
                                    Intent i = new Intent(getContext(), AddUnit0.class);
                                    i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                    getActivity().finish();
                                }
                            }

                            @Override
                            public void onFailed(@NonNull Exception e) {

                            }
                        });

                        /*uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                TextView maya = (TextView) view.findViewById(R.id.maya);
                                String rl = taskSnapshot.getDownloadUrl().toString();
                                Log.d("hitung", "nomor");
                                maya.setText(rl);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Upload Failed" + e, Toast.LENGTH_SHORT).show();
                            }
                        });*/
                        TextView maya = (TextView) view.findViewById(R.id.maya);
                        String ur = maya.getText().toString();
                        Log.d("memati", "mematikan");
                        i = i+1;
                    }
                    /*model.setKeyid(postidkey);
                    model.setUrl(URi);
                    model.setUid_(UID);
                    push.setValue(model);*/
                } else {
                    Toast.makeText(getContext(), "asgagaw", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = this.getArguments();
    }

    @Override
    public void onClick(View v) {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(50)
                .imageEngine(new GlideEngine())
                .forResult(2999);
/*        Intent intent = new Intent();
        intent.setType("image");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getActivity().startActivityForResult(Intent.createChooser(intent, "Select Image"),1);*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Uri> mSelected;
        List<Bitmap> selectedImages = new ArrayList<>();
        GridView gridView = (GridView)getView().findViewById(R.id.gridGalleryNX);

        if (requestCode == 2999 && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            filePath = mSelected;
            for (Uri temp : mSelected) {
                String stringTemp = temp.toString();
                Bitmap bmp = compressImage(stringTemp);
                selectedImages.add(bmp);
                Log.d("AddUnitAkhir", stringTemp);
            }
            GridView_Adapter adapter = new GridView_Adapter(this.getContext(), selectedImages);
            gridView.setAdapter(adapter);

            try {
            } catch(Exception e){
                e.printStackTrace();
            }
            memoryCache ca = new memoryCache();
            ca.clear();
        }

    }

    public Bitmap compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        return scaledBitmap;

    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getActivity().getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

}
