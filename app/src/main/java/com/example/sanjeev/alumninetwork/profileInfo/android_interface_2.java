package com.example.sanjeev.alumninetwork.profileInfo;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;


public interface android_interface_2 {

    @GET("/android/jsonandroid/")
    public void getAndroid(Callback<List<wrapper_android_model>> response);
}