package com.example.azizainun.maps;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class Detail_gambar extends Fragment {

    ArrayList<String> list_url;

    public Detail_gambar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_gambar, container, false);
        BannerSlider bannerSlider = view.findViewById(R.id.banner_slider1);
        list_url = getArguments().getStringArrayList("list url");
        List<Banner> banners=new ArrayList<>();
        for(int i=0; i<list_url.size(); i++){
            banners.add(new RemoteBanner(list_url.get(i)));
        }
        //add banner using image url
        bannerSlider.setBanners(banners);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
