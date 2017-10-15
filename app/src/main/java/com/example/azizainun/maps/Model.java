package com.example.azizainun.maps;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aziza on 6/9/2017.
 */
public class Model implements Serializable{

    public Model() {
    }

    public String harga, kotakab, uid, url, nama_tempat, tipe_bangunan;

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKotakab() {
        return kotakab;
    }

    public void setKotakab(String kotakab) {
        this.kotakab = kotakab;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNama_tempat() {
        return nama_tempat;
    }

    public void setNama_tempat(String nama_tempat) {
        this.nama_tempat = nama_tempat;
    }

    public String getTipe_bangunan() {
        return tipe_bangunan;
    }

    public void setTipe_bangunan(String tipe_bangunan) {
        this.tipe_bangunan = tipe_bangunan;
    }
}
