package com.example.sanjeev.alumninetwork.discussion_forum;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface commentAPI
{
    @GET("/get_comments.php")
    void getcomment(@Query("PID") int PID, Callback<wrapper_comment_model> callback);
}
