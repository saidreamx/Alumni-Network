package com.example.sanjeev.alumninetwork.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface githubClient {
    @GET("/repos/{owner}/{repo}/contributors")      //bad ka URL not root one.
    Call<List<contributors>> get_contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
