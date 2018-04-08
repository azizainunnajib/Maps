package com.example.azizainun.maps.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.azizainun.maps.Model.Data_ig;
import com.example.azizainun.maps.R;
import com.example.azizainun.maps.RestClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IG_upload extends AppCompatActivity {
    private ArrayList<Data_ig> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ig_upload);

        Call<InstagramResponse> call = RestClient.getRetrofitService().getTagPhotos("jakarta", "3859629641.8b194ec.b551c0748aa74f1fa5dd8504dcba418b");
        call.enqueue(new Callback<InstagramResponse>() {
            @Override
            public void onResponse(Call<InstagramResponse> call, Response<InstagramResponse> response) {

                if (response.body() != null) {
                    for(int i = 0; i < response.body().getData().length; i++){
                        data.add(response.body().getData()[i]);
                    }

//                    lvAdapter.notifyDataSetChanged();
                    String a = "ads";
                }
            }

            @Override
            public void onFailure(Call<InstagramResponse> call, Throwable t) {
                //Handle failure
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Call<InstagramResponse> calll = RestClient.getRetrofitService().getLocPhoto("163635496", "3859629641.8b194ec.b551c0748aa74f1fa5dd8504dcba418b");
        calll.enqueue(new Callback<InstagramResponse>() {
            @Override
            public void onResponse(Call<InstagramResponse> calll, Response<InstagramResponse> response) {

                if (response.body() != null) {
                    for(int i = 0; i < response.body().getData().length; i++){
                        data.add(response.body().getData()[i]);
                    }

//                    lvAdapter.notifyDataSetChanged();
                    String a = "ads";
                }
            }

            @Override
            public void onFailure(Call<InstagramResponse> calll, Throwable t) {
                //Handle failure
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
