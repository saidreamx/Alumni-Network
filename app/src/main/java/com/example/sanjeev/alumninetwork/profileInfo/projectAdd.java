package com.example.sanjeev.alumninetwork.profileInfo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.signUp.collectionLoginSignup;
import com.example.sanjeev.alumninetwork.signUp.logIn;
import com.example.sanjeev.alumninetwork.signUp.registerAPI;
import com.example.sanjeev.alumninetwork.signUp.sendMail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class projectAdd extends AppCompatActivity
{

    public static final String ROOT_URL = "http://getsanjeev.esy.es/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project_page);
        final EditText title_box = (EditText) findViewById(R.id.title_pro);
        final EditText mentor_box = (EditText) findViewById(R.id.mentor);
        final EditText duration_box = (EditText) findViewById(R.id.duration);
        final EditText description_box = (EditText) findViewById(R.id.descp);
        Button add_pro = (Button) findViewById(R.id.add_pro);
        add_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String title = title_box.getText().toString();
                String mentor = mentor_box.getText().toString();
                String duration = duration_box.getText().toString();
                String description =  description_box.getText().toString();
                int SID = aboutMe.SID;
                Log.e("SID OF USER",Integer.toString(SID));
                send_data(title,mentor,duration,description,SID);
            }
        });


    }

    void send_data(String ptitle, String pmentor, String pduration, String pdescription, int sid)
    {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build();
        updateprojectAPI api = adapter.create(updateprojectAPI.class);
        api.addProject(
                //Passing the values by getting it from editTexts
                ptitle, pmentor, pduration, pdescription, sid,
                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;
                        //An string to store output from the server
                        String output = "";
                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            //Reading the output in the string
                            output = reader.readLine();
                            if(output.equals("successfully added")) Toast.makeText(projectAdd.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                            else Toast.makeText(projectAdd.this, "Error, try again", Toast.LENGTH_SHORT).show();
                            finish();
                            Log.e("OUPUT LINE", output);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Displaying the output as a toast
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH",error.toString());
                    }
                }
        );
    }
}
