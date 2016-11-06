package com.example.sanjeev.alumninetwork.profileInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.POJO.projectAPI;
import com.example.sanjeev.alumninetwork.POJO.projectforothersAPI;
import com.example.sanjeev.alumninetwork.POJO.wrapper_project_model;
import com.example.sanjeev.alumninetwork.R;

import java.io.BufferedReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class showOthersProfile extends AppCompatActivity
{
    public static final String ROOT_URL = "http://getsanjeev.esy.es/";
    ListView lv;
    int size;
    String [] array;
    wrapper_project_model responseData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.others_profile);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        Log.e("PANKAJJJJJJJJJJJJJJ",name);
        lv = (ListView)findViewById(R.id.lv);
        get_array_data(name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_items,array);
        Log.e("kalpana","chawla");
        lv.setAdapter(adapter);

    }

    void get_array_data(String name)
    {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build();
        projectforothersAPI api = adapter.create(projectforothersAPI.class);
        api.insertUser(
                //Passing the values by getting it from editTexts
                name,
                //Creating an anonymous callback
                new Callback<wrapper_project_model>() {
                    @Override
                    public void success(wrapper_project_model result, Response response) {
                        responseData = result;
                        Log.e("Response", responseData.getprojects().toString());
                        size = responseData.getprojects().size();
                        //Log.e("size of array", Integer.toString(size));
                        Log.e("getting results", responseData.getprojects().get(1).gettitle());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );
    }
}
