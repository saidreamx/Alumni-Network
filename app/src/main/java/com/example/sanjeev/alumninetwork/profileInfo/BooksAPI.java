package com.example.sanjeev.alumninetwork.profileInfo;

        import java.util.List;

        import retrofit.Callback;
        import retrofit.http.GET;

public interface BooksAPI {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/retrofit/get_book_details.php")
    public void getBooks(Callback<List<Book>> response);
}
