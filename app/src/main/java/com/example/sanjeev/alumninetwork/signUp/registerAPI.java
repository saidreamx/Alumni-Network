package com.example.sanjeev.alumninetwork.signUp;
        import retrofit.Callback;
        import retrofit.client.Response;
        import retrofit.http.Field;
        import retrofit.http.FormUrlEncoded;
        import retrofit.http.POST;


public interface registerAPI
{
    @FormUrlEncoded
    @POST("/insert_test.php")
    public void insertUser
            (
            @Field("email") String email,
            @Field("password") String password,
            Callback<Response> callback
            );
}
