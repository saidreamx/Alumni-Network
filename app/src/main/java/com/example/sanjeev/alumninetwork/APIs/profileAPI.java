package com.example.sanjeev.alumninetwork.APIs;

import com.example.sanjeev.alumninetwork.POJO_wrapper.wrapper_profile_model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface profileAPI {

    @GET("/get_my_data.php")
    void insertUser(@Query("email") String email, Callback<wrapper_profile_model> callback);
}
