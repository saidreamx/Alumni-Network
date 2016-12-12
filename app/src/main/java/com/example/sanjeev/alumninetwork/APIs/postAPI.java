package com.example.sanjeev.alumninetwork.APIs;

import com.example.sanjeev.alumninetwork.POJO_wrapper.wrapper_post_model;

import retrofit.Callback;
import retrofit.http.GET;


public interface postAPI {

    @GET("/get_post.php")
    void getpost(Callback<wrapper_post_model> callback);
}
