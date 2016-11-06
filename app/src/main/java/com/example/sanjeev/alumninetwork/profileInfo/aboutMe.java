package com.example.sanjeev.alumninetwork.profileInfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.POJO.peopleAPI;
import com.example.sanjeev.alumninetwork.POJO.wrapper_people_model;
import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.peopleList.customAdapter;
import com.example.sanjeev.alumninetwork.peopleList.database;
import com.example.sanjeev.alumninetwork.peopleList.showAlumniList;
import com.example.sanjeev.alumninetwork.signUp.collectionLoginSignup;


import java.io.BufferedReader;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class aboutMe extends Fragment implements View.OnClickListener
{
    public static int SID = 0;
    ImageButton edit_cover;
    ImageView coverPhoto;
    String ImageDecode;
    int PICK_IMAGE_REQUEST = 1;
    int IMG_RESULT = 1;
    int size2;
    onePerson mactivity;
  //  private Bitmap bitmap;
    public static Context my_context;
    public static final String ROOT_URL = "http://getsanjeev.esy.es/";
    wrapper_profile_model responseData;
    wrapper_people_model responsedata2;
    TextView name_tv;
    TextView course_tv;
    EditText search;
    Button search_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_1, container, false);
        Log.e("IN oncreateview", "BBBB");
        edit_cover = (ImageButton) view.findViewById(R.id.button_choose);
        name_tv = (TextView) view.findViewById(R.id.name);
        course_tv = (TextView)view.findViewById(R.id.course);
        search  = (EditText) view.findViewById(R.id.search);
        search_btn = (Button) view.findViewById(R.id.search_btn);
        edit_cover.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        SharedPreferences mango = getActivity().getSharedPreferences("mango", getActivity().MODE_PRIVATE);
        String email = mango.getString("email_ID","supiou");
        getdata(email);
        return view;
    }

    void getdata(String email)
    {
        String short_email = email;
        short_email = short_email.substring(0,5);
        Log.e("short_email", short_email);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        final String message;
        Log.e("SECOND","ENTERED ON INSERT USER");
        profileAPI api = adapter.create(profileAPI.class);
        api.insertUser(
                //Passing the values by getting it from editTexts
                short_email,
                //Creating an anonymous callback
                new Callback<wrapper_profile_model>() {
                    @Override
                    public void success(wrapper_profile_model result, Response response)
                    {
                        BufferedReader reader = null;
                        responseData = result;
                        SID = responseData.getprofile().get(0).getsid();
                        Log.e("Response",responseData.toString());
                        Log.e("getting results", responseData.getprofile().get(0).getname());
                        showitems();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH",error.toString());
                    }
                }
        );

    }

    void showitems()
    {
        String name = responseData.getprofile().get(0).getname();
        String course = responseData.getprofile().get(0).getcourse();
        name_tv.setText(name);
        course_tv.setText(course);
    }




    @Override
    public void onClick(View v) {
        if(v == edit_cover)
        {
           mactivity.showFileChooser();
        }
        if(v == search_btn)
        {
            String person = search.getText().toString();
            int firstSpace = person.indexOf(" "); // detect the first space character
            String firstName = person.substring(0, firstSpace);  // get everything upto the first space character
            String lastName = person.substring(firstSpace).trim();
            search_person(firstName,lastName);
        }
    }

    void search_person(String s_f_name, String s_l_name)
    {
        Log.e("NAMES SENT",s_f_name + " "+ s_l_name);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        peopleAPI api = adapter.create(peopleAPI.class);
        api.getUser(
                //Passing the values by getting it from editTexts
                s_f_name, s_l_name,
                //Creating an anonymous callback
                new Callback<wrapper_people_model>() {
                    @Override
                    public void success(wrapper_people_model result, Response response)
                    {
                        BufferedReader reader = null;
                        responsedata2 = result;
                        size2 = responsedata2.getpeople().size();
                        Log.e("Response",responsedata2.toString());
                        Log.e("getting results", responsedata2.getpeople().get(0).gets_f_name());
                        show_alumni_list();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH",error.toString());
                    }
                }
        );
    }

    void show_alumni_list()
    {
        database.name_list = new String[size2];
        for(int i=0;i<size2;i++)
        {
            database.name_list[i] = responsedata2.getpeople().get(i).gets_f_name() + " " +
                    responsedata2.getpeople().get(i).gets_l_name();
        }
        database.image_list = new Integer[size2];
        for(int i =0;i<size2;i++)
        {
            database.image_list[i] =  R.drawable.images1;
        }
        Intent intent = new Intent(getActivity(), showAlumniList.class);
        startActivity(intent);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mactivity = (onePerson) getActivity();
    }

    public void getBits(Bitmap bitmap) {
        coverPhoto.setImageBitmap(bitmap);
    }
}
