package com.example.sanjeev.alumninetwork.discussion_forum;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface sendpostAPI
{
    @FormUrlEncoded
    @POST("/send_post.php")
    public void send_post
            (
                    @Field("post") String post,
                    @Field("name") String person,
                    Callback<Response> callback
            );
}
