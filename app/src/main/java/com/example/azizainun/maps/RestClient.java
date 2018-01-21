package com.example.azizainun.maps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aziza on 11/26/2017.
 */

public class RestClient {
    public static RetrofitService getRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService.class);
    }
}
