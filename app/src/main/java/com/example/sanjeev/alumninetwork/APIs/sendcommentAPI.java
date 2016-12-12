package com.example.sanjeev.alumninetwork.APIs;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface sendcommentAPI
{
    @FormUrlEncoded
    @POST("/add_comment.php")
    public void send_com
            (
                    @Field("post_id") int post_id,
                    @Field("comment") String comment,
                    @Field("name") String person,
                    Callback<Response> callback
            );
}
