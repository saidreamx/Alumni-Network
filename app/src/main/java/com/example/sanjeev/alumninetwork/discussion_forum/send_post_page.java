package com.example.sanjeev.alumninetwork.discussion_forum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sanjeev.alumninetwork.APIs.sendpostAPI;
import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.splash.database;
import java.io.BufferedReader;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

// This class deals with the activity where a user posts something in the community.
public class send_post_page extends AppCompatActivity
{
    EditText post;
    Button send_post;
    public static final String ROOT_URL = "http://getsanjeev.esy.es";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_post);
        post = (EditText) findViewById(R.id.post_pro);
        send_post = (Button) findViewById(R.id.send_post);
        send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_post_me();
            }
        });
    }

    void send_post_me()
    {
        String post_str = post.getText().toString();
        if (post_str.equals(""))
        {
            Toast.makeText(send_post_page.this, "Enter something to POSt", Toast.LENGTH_SHORT).show();
        }
        else {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL) //Setting the Root URL
                    .build();
            Log.e("NAME OF USER", database.name_of_current_user);
            sendpostAPI api = adapter.create(sendpostAPI.class);
            api.send_post(
                    post_str, database.name_of_current_user,
                    new Callback<Response>() {
                        @Override
                        public void success(Response result, Response response) {
                            BufferedReader reader = null;
                            String output = "";
                            Log.e("Meassge wen send", result.toString());
                            Toast.makeText(send_post_page.this, "POsted successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("SIXTH", error.toString());
                        }
                    }
            );
        }
    }
}
