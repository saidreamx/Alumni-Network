package com.example.sanjeev.alumninetwork.profileInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.POJO.make_others_profileAPI;
import com.example.sanjeev.alumninetwork.POJO.projectAPI;
import com.example.sanjeev.alumninetwork.POJO.projectforothersAPI;
import com.example.sanjeev.alumninetwork.POJO.wrapper_people_model;
import com.example.sanjeev.alumninetwork.POJO.wrapper_project_model;
import com.example.sanjeev.alumninetwork.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class showOthersProfile extends AppCompatActivity {
    public static final String ROOT_URL = "http://getsanjeev.esy.es/";
    TextView tv;
    TextView name_tv;
    TextView course_tv;
    String text;
    public  int SID;
    CircularImageView pro_photo;
    int size;
    int size2;
    String[] array;
    wrapper_project_model responseData;
    wrapper_profile_model responsedata2;
    String name = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.others_profile);
        Bundle bundle = getIntent().getExtras();
        tv = (TextView) findViewById(R.id.project_data);
        name_tv = (TextView) findViewById(R.id.name1);
        course_tv = (TextView) findViewById((R.id.course1));
        pro_photo = (CircularImageView) findViewById(R.id.profile_pic1);
        pro_photo.setBorderColor(getResources().getColor(R.color.profile_border));
        pro_photo.setBorderWidth(20);
        pro_photo.setShadowColor(getResources().getColor(R.color.profile_border));
        if (bundle != null && bundle.containsKey("name")) {
            name = bundle.getString("name");
            Log.e("NAMEIS", name);
        } else {
            Log.e("dsds", "sdsgydgsydfsydf");
        }
        get_profile_info(name);
    }

    void retrieve_image()
    {
        int s_id = SID;
        Log.e("MY SID", Integer.toString(s_id));
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        photoAPI api = adapter.create(photoAPI.class);
        api.getphoto(
                //Passing the values by getting it from editTexts
                s_id,
                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;
                        String output = "";
                        try
                        {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            //Reading the output in the string
                            output = reader.readLine();
                        }
                        catch (IOException e)
                        {
                            Log.e("EXCEPTION", e.toString());
                        }
                        Log.e("getimage function", output);
                        String image_source = ROOT_URL+"/uploads/"+output;
                        Log.e("IMAGE URL",image_source);
                        Picasso.with(getApplicationContext()).load(image_source).into(pro_photo);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );
    }



    void get_profile_info(final String name) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        make_others_profileAPI api = adapter.create(make_others_profileAPI.class);
        api.getUser(
                //Passing the values by getting it from editTexts
                name,
                //Creating an anonymous callback
                new Callback<wrapper_profile_model>() {
                    @Override
                    public void success(wrapper_profile_model result, Response response) {
                        Log.e("in the success", result.toString());
                        responsedata2 = result;
                        size2 = responsedata2.getprofile().size();
                        SID = responsedata2.getprofile().get(0).getS_id();
                        Log.e("getting results human", responsedata2.getprofile().get(0).getS_f_name());
                        get_array_data(name);
                        retrieve_image();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );
    }

    public void set_data() {
        Log.e("Entered set_DATA", responsedata2.getprofile().get(0).getS_f_name());
        name_tv.setText(responsedata2.getprofile().get(0).getS_f_name() + " " +
                responsedata2.getprofile().get(0).getS_l_name());
        course_tv.setText(responsedata2.getprofile().get(0).getS_course());
    }


    void get_array_data(String name) {
        Log.e("SSJJJJJJ", "IN THE GET ARRAY FUNCTION");
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        projectforothersAPI api = adapter.create(projectforothersAPI.class);
        api.insertUser(
                //Passing the values by getting it from editTexts
                name,
                //Creating an anonymous callback
                new Callback<wrapper_project_model>() {
                    @Override
                    public void success(wrapper_project_model result, Response response) {
                        Log.e("in the success of proj", result.toString());
                        responseData = result;
                        size = responseData.getprojects().size();
                        Log.e("Response", responseData.getprojects().toString());
                        size = responseData.getprojects().size();
                        //Log.e("size of array", Integer.toString(size));
                        Log.e("getting results", responseData.getprojects().get(1).getPtitle());
                        array = new String[size];
                        for (int i = 0; i < size; i++) {
                            array[i] = responseData.getprojects().get(i).getPtitle() + "\n" + responseData.getprojects().get(i).getPmentor() +
                                    "\n" + responseData.getprojects().get(i).getPdescription();
                        }
                        int x = 0;
                        text = "";
                        Log.e("befor while", "PPPPP");
                        while (x < size) {
                            text = text + array[x] + "\n\n";
                            x++;
                        }
                        Log.e("DATA", text);
                        tv.setText(text);
                        Log.e("Returning TEXT", text);
                        set_data();

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
