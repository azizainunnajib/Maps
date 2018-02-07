package com.example.azizainun.maps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail_gambar.*
import ss.com.bannerslider.banners.Banner
import ss.com.bannerslider.banners.RemoteBanner
import ss.com.bannerslider.views.BannerSlider
import java.util.ArrayList

class DetailGambar : AppCompatActivity() {

    internal lateinit var list_url: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_gambar)
        list_url = intent.extras!!.getStringArrayList("list url")
        val banners = ArrayList<Banner>()
        for (i in list_url.indices) {
            banners.add(RemoteBanner(list_url.get(i)))
        }
        //add banner using image url
        banner_slider1.setBanners(banners)
    }
}
