package com.example.azizainun.maps;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by aziza on 6/9/2017.
 */
public class Model implements Serializable {

    public Model() {
    }

    public String harga, lokasi, UID_;
    public String url;
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

    public String getUrl() {
     return url;
    }

    public void setUrl(String url) {
        this.url=url;
    }
}
