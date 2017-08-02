package com.example.azizainun.maps;

import java.io.Serializable;

/**
 * Created by aziza on 6/9/2017.
 */
public class Model implements Serializable {
    String namaTempat, lokasi, UID_;
    int urut;

    public String getPrice() {
        return namaTempat;
    }

    public void setPrice(String namaTempat) {
        this.namaTempat = namaTempat;
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

    public void setUID(String UID__) {
        this.UID_ = UID__;
    }

    public String  getUID() {
        return UID_;
    }
}
