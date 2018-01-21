package com.example.azizainun.maps;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aziza on 11/26/2017.
 */


public interface RetrofitService {

    @GET("v1/tags/{tag_name}/media/recent")
    Call<InstagramResponse> getTagPhotos(@Path("tag_name") String tag_name,
                                         @Query("access_token") String access_token);

    @GET("v1/locations/{location-id}/media/recent")
    Call<InstagramResponse> getLocPhoto(@Path("location-id") String location_id,
                                        @Query("access_token") String access_token);
}
