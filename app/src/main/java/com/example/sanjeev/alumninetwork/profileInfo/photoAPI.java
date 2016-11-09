package com.example.sanjeev.alumninetwork.profileInfo;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;


public interface photoAPI
{
    @GET("/get_image.php")
    void getphoto(@Query("s_id") int s_id, Callback<Response> callback);

}
