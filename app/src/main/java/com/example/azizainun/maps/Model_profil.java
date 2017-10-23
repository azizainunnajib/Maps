package com.example.azizainun.maps;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by aziza on 10/22/2017.
 */

public class Model_profil implements Parcelable {

    String nama, email, rekening, handphone, deskripsi;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Model_profil()  {
    }

    protected Model_profil(Parcel in) {
        nama = in.readString();
        email = in.readString();
        rekening = in.readString();
        handphone = in.readString();
        deskripsi = in.readString();
    }

    public static final Creator<Model_profil> CREATOR = new Creator<Model_profil>() {
        @Override
        public Model_profil createFromParcel(Parcel in) {
            return new Model_profil(in);
        }

        @Override
        public Model_profil[] newArray(int size) {
            return new Model_profil[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(email);
        dest.writeString(rekening);
        dest.writeString(handphone);
        dest.writeString(deskripsi);
    }
}
