package com.example.azizainun.maps;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by aziza on 8/31/2017.
 */

public class Model_Detail implements Parcelable{
    public String harga, lokasi, UID_, tipe_tempat, tipe_bangunan, jenis_kasur;
    int total_tamu, total_kamar, total_kasur, total_toilet;
    public ArrayList<String> url;
    int urut;

    public Model_Detail()  {
    }

    protected Model_Detail(Parcel in) {
        harga = in.readString();
        lokasi = in.readString();
        UID_ = in.readString();
        tipe_tempat = in.readString();
        tipe_bangunan = in.readString();
        jenis_kasur = in.readString();
        total_tamu = in.readInt();
        total_kamar = in.readInt();
        total_kasur = in.readInt();
        total_toilet = in.readInt();
        url = in.createStringArrayList();
        urut = in.readInt();
    }

    public static final Creator<Model_Detail> CREATOR = new Creator<Model_Detail>() {
        @Override
        public Model_Detail createFromParcel(Parcel in) {
            return new Model_Detail(in);
        }

        @Override
        public Model_Detail[] newArray(int size) {
            return new Model_Detail[size];
        }
    };

    public String getJenis_kasur() {
        return jenis_kasur;
    }

    public void setJenis_kasur(String jenis_kasur) {
        this.jenis_kasur = jenis_kasur;
    }

    public int getTotal_tamu() {
        return total_tamu;
    }

    public void setTotal_tamu(int total_tamu) {
        this.total_tamu = total_tamu;
    }

    public int getTotal_kamar() {
        return total_kamar;
    }

    public void setTotal_kamar(int total_kamar) {
        this.total_kamar = total_kamar;
    }

    public int getTotal_kasur() {
        return total_kasur;
    }

    public void setTotal_kasur(int total_kasur) {
        this.total_kasur = total_kasur;
    }

    public int getTotal_toilet() {
        return total_toilet;
    }

    public void setTotal_toilet(int total_toilet) {
        this.total_toilet = total_toilet;
    }

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

    public String getTipe_tempat() {
        return tipe_tempat;
    }

    public void setTipe_tempat(String tipe_tempat) {
        this.tipe_tempat = tipe_tempat;
    }

    public String getTipe_bangunan() {
        return tipe_bangunan;
    }

    public void setTipe_bangunan(String tipe_bangunan) {
        this.tipe_bangunan = tipe_bangunan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(harga);
        dest.writeString(lokasi);
        dest.writeString(UID_);
        dest.writeString(tipe_tempat);
        dest.writeString(tipe_bangunan);
        dest.writeInt(total_kamar);
        dest.writeInt(total_kasur);
        dest.writeInt(total_tamu);
        dest.writeInt(total_toilet);
        dest.writeStringList(url);
        dest.writeInt(urut);
        dest.writeString(jenis_kasur);
    }
}
