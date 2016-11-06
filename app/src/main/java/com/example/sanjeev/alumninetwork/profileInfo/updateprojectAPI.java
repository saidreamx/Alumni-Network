package com.example.sanjeev.alumninetwork.profileInfo;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface updateprojectAPI {
    @FormUrlEncoded
    @POST("/add_project.php")
    void addProject
            (
                    @Field("ptitle") String ptitle,
                    @Field("pmentor") String pmentor,
                    @Field("pduration") String pduration,
                    @Field("pdescription") String pdescription,
                    @Field("sid") int sid,
                    Callback<Response> callback
            );
}
