package com.example.sanjeev.alumninetwork.POJO;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface postAPI {

    @GET("/get_projects.php")
    void insertUser(@Query("sid") String email, Callback<wrapper_post_model> callback);
}
