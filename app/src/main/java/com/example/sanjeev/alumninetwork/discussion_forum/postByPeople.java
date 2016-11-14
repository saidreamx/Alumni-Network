package com.example.sanjeev.alumninetwork.discussion_forum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.example.sanjeev.alumninetwork.R;
import java.io.BufferedReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class postByPeople extends AppCompatActivity {

    public static final String ROOT_URL = "http://getsanjeev.esy.es";

    private String[] posts;
    private String[] comments;
    private String[] times;
    private String[] names;
    private Bitmap[] dps;
    ListView lv;
    wrapper_post_model responsedata2;
    int post_id;
    int size2;
    int limit = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("CREATE OF POSTBY PEOPLE","aa");
        setContentView(R.layout.list_view_for_post);
        Log.e("CREATE OF POSTBY PEOPLE","contentview set");
        lv = (ListView) findViewById(R.id.listView_in_post);
        get_posts();
    }



    void get_posts()
    {
        Log.e("get posts","in function");
        int limit = 0;
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        postAPI api = adapter.create(postAPI.class);
        api.getpost(
                limit,
                new Callback<wrapper_post_model>() {
                    @Override
                    public void success(wrapper_post_model result, Response response) {
                        BufferedReader reader = null;
                        responsedata2 = result;
                        size2 = responsedata2.getpost().size();
                        Log.e("Response", responsedata2.toString());
                        Log.e("getting results", responsedata2.getpost().get(0).getPost_name());
                        Log.e("CREATE OF POSTBY PEOPLE","found list view");
                        posts = new String[size2];
                        comments = new String[size2];
                        names = new String[size2];
                        times = new String[size2];
                        dps = new Bitmap[size2];
                        for(int i = 0;i<size2;i++)
                        {
                            posts[i] = responsedata2.getpost().get(i).getPost_data();
                            comments[i] = responsedata2.getpost().get(i).getPost_name();
                            names[i] = responsedata2.getpost().get(i).getPost_name();
                            Log.e("NAME OF PERSON", names[i]);
                            times[i] = responsedata2.getpost().get(i).getPost_time();
                            dps[i] = BitmapFactory.decodeResource(getResources(),R.drawable.one);
                        }
                        postAdapter adapter = new postAdapter(getApplicationContext(),posts,comments,times,names,dps);
                        Log.e("CREATE OF POSTBY PEOPLE","created adapter");
                        lv.setAdapter(adapter);
                        Log.e("CREATE OF POSTBY PEOPLE","set adapter");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );
    }

}
