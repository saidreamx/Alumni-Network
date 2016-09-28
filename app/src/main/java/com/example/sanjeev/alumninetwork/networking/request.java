package com.example.sanjeev.alumninetwork.networking;

import com.example.sanjeev.alumninetwork.json_model.android_versions;

import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;

public interface request {
    @GET("/android/jsonandroid/")
    public void get_android_details(Callback<List<android_versions>> response);
}
