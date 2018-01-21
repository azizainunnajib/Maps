package com.example.azizainun.maps;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

public class DetailUnit extends AppCompatActivity {

    ArrayList<String> list_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_unit);

        Model model = (Model) getIntent().getExtras().getSerializable("detail");

//        EditText editText = (EditText)findViewById(R.id.editText90);
//        editText.setText((model.getKotakab()));

        new Database().mReadDataOnce("User/null/Tempat_sewa", new Database.OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
//                Model_Detail detail = new Model_Detail();
                for (DataSnapshot snapshot:data.getChildren()) {
                    Model_Detail detail = snapshot.getValue(Model_Detail.class);
                    list_url = detail.getUrl();
                    Log.d("teraja", list_url.get(0));
                    Log.d("teraja", detail.getAc());
                    Build_Banner(list_url);
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
    }

    private void Build_Banner(final ArrayList<String> list_url) {
        final BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
        List<Banner> banners=new ArrayList<>();
        for(int i=0; i<list_url.size(); i++){
            banners.add(new RemoteBanner(list_url.get(i)));
        }
        //add banner using image url
        bannerSlider.setBanners(banners);
        bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                Fragment fNext = new Detail_gambar();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putStringArrayList("list url", list_url);
                fNext.setArguments(args);
                ft.replace(R.id.content_frame_next, fNext).addToBackStack(null);
                ft.commit();
            }
        });
    }
}
