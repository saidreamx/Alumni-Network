package com.example.sanjeev.alumninetwork.networking;

import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubClient {
    @GET("/repos/{owner}/{repo}/contributors")      //bad ka URL not root one.
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
