package com.example.sanjeev.alumninetwork.profileInfo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.APIs.projectAPI;
import com.example.sanjeev.alumninetwork.POJO_wrapper.wrapper_project_model;
import com.example.sanjeev.alumninetwork.R;

import java.io.BufferedReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class projectsDetails extends Fragment
{

    wrapper_project_model responseData;
    int size;
    public static final String ROOT_URL = "http://getsanjeev.esy.es/";
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_2, container, false);
        lv=(ListView) view.findViewById(R.id.listView_projets);
        getdata();
        ImageButton add = (ImageButton) view.findViewById(R.id.plus);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), projectAdd.class);
                startActivity(intent);
            }
        });
        return view;
    }

    void getdata() {
        SharedPreferences mango = getActivity().getSharedPreferences("mango", Activity.MODE_PRIVATE);
        String email = mango.getString("emailID", "getsa");
        String short_email = email;
        short_email = short_email.substring(0, 5);
        Log.e("short_email", short_email);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        final String message;
        Log.e("SECOND", "ENTERED ON INSERT USER");
        projectAPI api = adapter.create(projectAPI.class);
        api.insertUser(
                //Passing the values by getting it from editTexts
                short_email,
                //Creating an anonymous callback
                new Callback<wrapper_project_model>() {
                    @Override
                    public void success(wrapper_project_model result, Response response) {
                        BufferedReader reader = null;
                        responseData = result;
                        Log.e("Response", responseData.getprojects().toString());
                        size = responseData.getprojects().size();
                        Log.e("size of array", Integer.toString(size));
                        if (size>0) {
                            Log.e("getting results", responseData.getprojects().get(0).getPtitle());
                            showitems();
                        }else{
                            Toast.makeText(getContext(), "No projects found!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );
    }
        void showitems()
        {
            String title [] = new String[size];
            String mentor [] = new String[size];
            String descp [] = new String[size];
            for(int i = 0;i<size;i++)
            {
                /*title[i] = responseData.getprojects().get(i).gettitle();
                mentor[i] = responseData.getprojects().get(i).getmentor();
                descp[i] = responseData.getprojects().get(i).get_description();*/
                title[i] = responseData.getprojects().get(i).getPtitle()+"\n"+responseData.getprojects().get(i).getPmentor()+"\n"
                        +responseData.getprojects().get(i).getPdescription();
            }

            //lv.setAdapter(new customAdapter(, title,mentor,descp));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.list_items,title);
            Log.e("kalpana","chawla");
            lv.setAdapter(adapter);
        }

    }

