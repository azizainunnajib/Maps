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

    public String harga, lokasi, UID_;
    public ArrayList<String> url;
    int urut;

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getUrut() {
        return urut;
    }

    public void setUrut(int urut) {
        this.urut = urut;
    }

    public ArrayList<String> getUrl() {
     return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url=url;
    }
}
