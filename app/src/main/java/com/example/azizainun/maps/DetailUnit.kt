package com.example.azizainun.maps

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.azizainun.maps.AddUnit.AddUnit1
import com.example.azizainun.maps.R.id.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_detail_unit.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_add_unit5.view.*

import java.util.ArrayList

import ss.com.bannerslider.banners.Banner
import ss.com.bannerslider.banners.RemoteBanner
import ss.com.bannerslider.events.OnBannerClickListener
import ss.com.bannerslider.views.BannerSlider


class DetailUnit : AppCompatActivity(), OnMapReadyCallback {
    internal lateinit var list_url: ArrayList<String>
    internal lateinit var UID: String
    internal lateinit var nama_tempat: String
    internal lateinit var latlng: LatLng
    internal lateinit var mapView: MapView
    internal lateinit var namaTempat : String
    internal var lat: Double = 0.0
    internal var lng: Double = 0.0
    lateinit var googleMap: GoogleMap
    internal var model_detail: Model_Detail? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_unit)
        map.onCreate(savedInstanceState)
        val model = intent.extras!!.getSerializable("detail") as Model

        UID = model.getUid()
        nama_tempat = model.getNama_tempat()

        gambar_user.setOnClickListener {
//            supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_banner, fragment)*/
            val fragment = ProfilUserLain()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_banner, fragment)
                    .addToBackStack("settings")
                    .commit()
        }

        Database().mReadDataOnce("User/$UID/Tempat_sewa/$nama_tempat", object : Database.OnGetDataListener {
            override fun onStart() {

            }

            override fun onSuccess(data: DataSnapshot) {
                model_detail = data.getValue(Model_Detail::class.java)
                list_url = model_detail!!.getUrl()
                Log.d("teraja", list_url[0])
                Log.d("teraja", model_detail!!.getAc())
                Build_Banner(list_url)
                putData(model_detail)
            }

            override fun onFailed(databaseError: DatabaseError) {

            }
        })
    }

    private fun putData(model_detail: Model_Detail?) {
        text_judultempat.text = nama_tempat
        Glide
                .with(this)
                .load(model_detail?.getUrl()?.get(1))
                .asBitmap()
                .into(gambar_user)
        text_judultempat.text = "alskdf"
        lay_fasilitas.setOnClickListener {
            expand_fasilitas.toggle()
        }
        tipe_tempat.text = model_detail?.getTipe_tempat()
        tipe_bangunan.text = model_detail?.getTipe_bangunan()
        nama_user.text = model_detail?.getJudul()
        total_kasur.text = model_detail?.getTotal_kasur().toString() + "\nTotal Kasur"
        total_kamar.text = model_detail?.getTotal_kamar().toString() + "\nTotal Kamar"
        max_tamu.text = model_detail?.getTotal_tamu().toString() + "\nTotal Tamu"
        total_toilet.text = model_detail?.getTotal_toilet().toString() + "\nTotal Toilet"
        deskripsi.text = model_detail?.getDeskripsi()
        checkin.text =model_detail?.getCheckin()
        namaTempat = model_detail?.getJudul().toString()
        lat = model_detail!!.getLat()
        lng = model_detail!!.getLongi()
        latlng = LatLng(lat, lng)
        map.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
//        p0?.cameraPosition?.target?.to(latlng)
        googleMap = p0!!
        val oke = CameraPosition.builder().target(latlng).zoom(13f).build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(oke))
        val circle = googleMap.addCircle(CircleOptions()
                .center(latlng)
                .radius(200.0)
                .strokeColor(Color.RED)
                .strokeWidth(0.2f)
                .fillColor(R.color.icon_trans))

        googleMap.setOnMapClickListener(GoogleMap.OnMapClickListener() {
            val geoUri = "http://maps.google.com/maps?q=loc:$lat,$lng ($namaTempat)"
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(intent)
                })
    }

    private fun Build_Banner(list_url: ArrayList<String>) {
        val bannerSlider = findViewById<View>(R.id.banner_slider1) as BannerSlider
        val banners = ArrayList<Banner>()
        for (i in list_url.indices) {
            banners.add(RemoteBanner(list_url[i]))
        }
        //add banner using image url
        bannerSlider.setBanners(banners)
        bannerSlider.setOnBannerClickListener {
            Toast.makeText(this, "ini slider", Toast.LENGTH_SHORT).show()
//            val fNext = Detail_gambar()
//            val ft = supportFragmentManager.beginTransaction()
//            val args = Bundle()
//            args.putStringArrayList("list url", list_url)
//            fNext.arguments = args
//            Log.d("aserehe", "10")
//            ft.replace(R.id.frame_banner, fNext).addToBackStack(null)
//            Log.d("aserehe", "11")
//            ft.commit()
//            Log.d("aserehe", "12")

//            val fragment: Detail_gambar = Detail_gambar()
           /* var fragmentClass: Class<*>? = null
            fragmentClass = Detail_gambar::class.java
            try {
                fragment = fragmentClass!!.newInstance() as Fragment
            } catch (e: Exception) {
                e.printStackTrace()
            }


            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_banner, fragment)*/
//            val args = Bundle()
//            args.putStringArrayList("list url", list_url)
//            fragment.arguments = args
//            supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.frame_banner, fragment)
//                    .addToBackStack("settings")
//                    .commit()
            val intent = Intent(this, DetailGambar::class.java)
            intent.putExtra("list url", list_url)
            startActivity(intent);
            Log.d("aserehe", "10")
        }
    }
}
