package com.example.azizainun.maps.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.azizainun.maps.R;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class Detail_gambar extends Fragment {

    ArrayList<String> list_url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_detail_gambar, container, false);
        BannerSlider bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);
        list_url = getArguments().getStringArrayList("list url");
        List<Banner> banners=new ArrayList<>();
        for(int i=0; i<list_url.size(); i++){
            banners.add(new RemoteBanner(list_url.get(i)));
        }
        //add banner using image url
        bannerSlider.setBanners(banners);
        return view;
    }
}
