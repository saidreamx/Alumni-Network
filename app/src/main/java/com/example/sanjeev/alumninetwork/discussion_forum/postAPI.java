package com.example.sanjeev.alumninetwork.discussion_forum;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface postAPI {

    @GET("/get_post.php")
    void getpost(Callback<wrapper_post_model> callback);
}
