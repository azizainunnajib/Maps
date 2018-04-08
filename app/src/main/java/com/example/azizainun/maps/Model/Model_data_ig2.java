package com.example.azizainun.maps.Model;

import android.os.Parcelable;

/**
 * Created by aziza on 2/3/2018.
 */

public class Model_data_ig2 {
    String url, nama_lokasi, username_ig, caption;
    Long id_location;
    Double lat, lng;

    public Model_data_ig2() {
    }

    public Model_data_ig2(String url, String nama_lokasi, String username_ig, Long id_location, Double lat, Double lng, String caption) {
        this.url = url;
        this.nama_lokasi = nama_lokasi;
        this.username_ig = username_ig;
        this.id_location = id_location;
        this.lat = lat;
        this.lng = lng;
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }

    public String getUsername_ig() {
        return username_ig;
    }

    public void setUsername_ig(String username_ig) {
        this.username_ig = username_ig;
    }

    public Long getId_location() {
        return id_location;
    }

    public void setId_location(Long id_location) {
        this.id_location = id_location;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
