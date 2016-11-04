package com.example.sanjeev.alumninetwork.profileInfo;

import retrofit.Callback;
import retrofit.http.GET;

public interface profileAPI
{
    @GET("/")
    void getAndroid(Callback<wrapper_android_model> response);
}
