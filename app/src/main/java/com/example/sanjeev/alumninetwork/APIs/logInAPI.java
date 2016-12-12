package com.example.sanjeev.alumninetwork.APIs;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sanjeev on 26/10/16.
 */
public interface logInAPI
{
    @FormUrlEncoded
    @POST("/logIn.php")
    public void logInUser
            (
                    @Field("email") String email,
                    @Field("password") String password,
                    Callback<Response> callback
            );
}