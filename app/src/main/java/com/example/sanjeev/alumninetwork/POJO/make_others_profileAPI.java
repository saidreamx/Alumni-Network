package com.example.sanjeev.alumninetwork.POJO;

import com.example.sanjeev.alumninetwork.profileInfo.wrapper_profile_model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface make_others_profileAPI
{
    @GET("/getsearch_result.php")
    void getUser(@Query("s_f_name") String s_f_name, @Query("s_l_name") String s_l_name, Callback<wrapper_profile_model> callback);
}
