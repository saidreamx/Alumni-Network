package com.example.sanjeev.alumninetwork.APIs;

import com.example.sanjeev.alumninetwork.POJO_wrapper.wrapper_profile_model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface make_others_profileAPI
{
    @GET("/get_profile_people.php")
    void getUser(@Query("name") String name, Callback<wrapper_profile_model> callback);
}
