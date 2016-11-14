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
import com.example.sanjeev.alumninetwork.peopleList.database;

import java.io.BufferedReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class commentsByPeople extends AppCompatActivity
{
    int PID;
    int size2;
    private String[] posts;
    private String[] comments;
    private String[] times;
    private String[] names;
    private Bitmap[] dps;
    ListView lv;
    public static final String ROOT_URL = "http://getsanjeev.esy.es";
    wrapper_comment_model responsedata2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_iinfo);
        lv= (ListView) findViewById(R.id.listView_in_cominfo);
        PID = database.current_post_id_mine;
        getcomments(PID);
    }


    void getcomments(int PID)
    {
        Log.e("POST_ID", PID+"");
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        commentAPI api = adapter.create(commentAPI.class);
        api.getcomment(
                PID,
                new Callback<wrapper_comment_model>() {
                    @Override
                    public void success(wrapper_comment_model result, Response response) {
                        BufferedReader reader = null;
                        responsedata2 = result;
                        size2 = responsedata2.getcomment().size();
                        Log.e("SIZE OF COMMENT", size2+"");
                        Log.e("Response", responsedata2.toString());
                        Log.e("getting results", responsedata2.getcomment().get(0).getComment());
                        Log.e("CREATE OF POSTBY PEOPLE","found list view");
                        comments = new String[size2];
                        names = new String[size2];
                        times = new String[size2];
                        dps = new Bitmap[size2];
                        for(int i = 0;i<size2;i++)
                        {
                            comments[i] = responsedata2.getcomment().get(i).getComment();
                            names[i] = responsedata2.getcomment().get(i).getName();
                            Log.e("NAME OF PERSON", names[i]);
                            times[i] = responsedata2.getcomment().get(i).getTime();
                            dps[i] = BitmapFactory.decodeResource(getResources(), R.drawable.one);
                        }
                        comment_adapter adapter = new comment_adapter(getApplicationContext(),names,comments,times,dps);
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
