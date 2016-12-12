package com.example.sanjeev.alumninetwork.APIs;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;


public interface ServerAPI {

    @Multipart
    @POST("/upload.php")
    void upload(
            @Part("image") TypedFile file,
            @Part("s_id") int s_id,
            Callback<Response> callback);
}