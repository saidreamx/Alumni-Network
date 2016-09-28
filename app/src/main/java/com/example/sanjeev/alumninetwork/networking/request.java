package com.example.sanjeev.alumninetwork.networking;

import com.example.sanjeev.alumninetwork.json_model.json_format;

import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;

public interface request {
    @GET("/sanjeev.php")
    public void get_location_details(Callback<List<json_format>> response);
}
