package com.example.sanjeev.alumninetwork.discussion_forum;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.peopleList.database;
import com.example.sanjeev.alumninetwork.profileInfo.onePerson;
import com.example.sanjeev.alumninetwork.signUp.logInAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class commentsByPeople extends AppCompatActivity {
    int PID;
    int size2;
    private String[] posts;
    private String[] comments;
    private String[] times;
    private String[] names;
    private Bitmap[] dps;
    Button send_comment;
    Response comment_response;
    ListView lv;
    EditText comment_box;
    public static final String ROOT_URL = "http://getsanjeev.esy.es";
    wrapper_comment_model responsedata2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_iinfo);
        lv = (ListView) findViewById(R.id.listView_in_cominfo);
        PID = database.current_post_id_mine;
        send_comment = (Button) findViewById(R.id.comment_btn2);
        comment_box = (EditText) findViewById(R.id.comment_et);
        getcomments(PID);
        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_comment();
            }
        });

    }

    void send_comment() {
        int post_id = database.current_post_id_mine;
        String comment = comment_box.getText().toString();
        String person = database.name_of_current_user;
        if (comment.equals("")) {
            Toast.makeText(commentsByPeople.this, "Enter something to Comment", Toast.LENGTH_SHORT).show();
        } else {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL) //Setting the Root URL
                    .build();
            sendcommentAPI api = adapter.create(sendcommentAPI.class);
            api.send_com(
                    post_id, comment, person,
                    new Callback<Response>() {
                        @Override
                        public void success(Response result, Response response) {
                            BufferedReader reader = null;
                            String output = "";
                            Toast.makeText(commentsByPeople.this, "commented", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("SIXTH", error.toString());
                        }
                    }
            );
        }
    }


    void getcomments(int PID) {
        Log.e("POST_ID", PID + "");
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
                        if(size2>0)
                        {
                            Log.e("SIZE OF COMMENT", size2 + "");
                            Log.e("Response", responsedata2.toString());
                            Log.e("getting results", responsedata2.getcomment().get(0).getComment());
                            Log.e("CREATE OF POSTBY PEOPLE", "found list view");
                            comments = new String[size2];
                            names = new String[size2];
                            times = new String[size2];
                            dps = new Bitmap[size2];
                            for (int i = 0; i < size2; i++) {
                                comments[i] = responsedata2.getcomment().get(i).getComment();
                                names[i] = responsedata2.getcomment().get(i).getName();
                                Log.e("NAME OF PERSON", names[i]);
                                times[i] = responsedata2.getcomment().get(i).getTime();
                                dps[i] = BitmapFactory.decodeResource(getResources(), R.drawable.one);
                            }
                            comment_adapter adapter = new comment_adapter(getApplicationContext(), names, comments, times, dps);
                            Log.e("CREATE OF POSTBY PEOPLE", "created adapter");
                            lv.setAdapter(adapter);
                            Log.e("CREATE OF POSTBY PEOPLE", "set adapter");
                        }
                        else {
                            Toast.makeText(commentsByPeople.this, "NO_comments", Toast.LENGTH_SHORT).show();
                        }
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
