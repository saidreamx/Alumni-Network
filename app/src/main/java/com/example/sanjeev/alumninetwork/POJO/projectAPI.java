package com.example.sanjeev.alumninetwork.POJO;

import com.example.sanjeev.alumninetwork.profileInfo.wrapper_profile_model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface projectAPI
{
    @GET("/get_projects.php")
    void insertUser(@Query("email") String email, Callback<wrapper_project_model> callback);
}
