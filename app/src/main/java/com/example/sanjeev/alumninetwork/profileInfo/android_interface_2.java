package com.example.sanjeev.alumninetwork.profileInfo;
import retrofit.Callback;
import retrofit.http.GET;


public interface android_interface_2 {

    @GET("/android/jsonandroid/")
    void getAndroid(Callback<wrapper_android_model> response);
}