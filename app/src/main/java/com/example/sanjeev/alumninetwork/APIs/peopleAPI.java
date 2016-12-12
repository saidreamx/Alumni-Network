package com.example.sanjeev.alumninetwork.APIs;


import com.example.sanjeev.alumninetwork.POJO_wrapper.wrapper_people_model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface peopleAPI
{
    @GET("/getsearch_result.php")
    void getUser(@Query("s_f_name") String s_f_name, @Query("s_l_name") String s_l_name, Callback<wrapper_people_model> callback);

}
