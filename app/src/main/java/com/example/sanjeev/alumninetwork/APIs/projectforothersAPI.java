package com.example.sanjeev.alumninetwork.APIs;

import com.example.sanjeev.alumninetwork.POJO_wrapper.wrapper_project_model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface projectforothersAPI
{
    @GET("/get_others_project.php")
    void insertUser(@Query("name") String name, Callback<wrapper_project_model> callback);
}
