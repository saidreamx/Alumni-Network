package com.example.sanjeev.alumninetwork.profileInfo;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface profileAPI {

    @GET("/get_my_data.php")
    void insertUser(@Query("s_l_name") String email, Callback<wrapper_profile_model> callback);
}
