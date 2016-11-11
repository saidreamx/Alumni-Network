package com.example.sanjeev.alumninetwork.profileInfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
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

import com.example.sanjeev.alumninetwork.POJO.make_others_profileAPI;
import com.example.sanjeev.alumninetwork.POJO.peopleAPI;
import com.example.sanjeev.alumninetwork.POJO.wrapper_people_model;
import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.peopleList.customAdapter;
import com.example.sanjeev.alumninetwork.peopleList.database;
import com.example.sanjeev.alumninetwork.peopleList.showAlumniList;
import com.example.sanjeev.alumninetwork.signUp.collectionLoginSignup;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.net.ssl.HttpsURLConnection;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class aboutMe extends Fragment implements View.OnClickListener {
    public static final String UPLOAD_URL = "http://getsanjeev.esy.es/upload.php";
    public static final String UPLOAD_KEY = "image";
    public static int SID = 0;
    ImageButton edit_cover;
    int flag_first_time = 0;
    Bitmap bitmap;
    String ImageDecode;
    int PICK_IMAGE_REQUEST = 1;
    int IMG_RESULT = 1;
    int size2;
    onePerson mactivity;
    //  private Bitmap bitmap;
    public Context my_context;
    public static final String ROOT_URL = "http://getsanjeev.esy.es";
    wrapper_profile_model responseData;
    wrapper_people_model responsedata2;
    ImageSendData responsedata3;
    Response responsedata4;
    TextView name_tv;
    TextView course_tv;
    EditText search;
    Button search_btn;
    CircularImageView my_photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_1, container, false);
        Log.e("IN oncreateview", "BBBB");
        edit_cover = (ImageButton) view.findViewById(R.id.button_choose);
        name_tv = (TextView) view.findViewById(R.id.name);
        my_photo = (CircularImageView) view.findViewById(R.id.profile_photo);
        my_photo.setBorderColor(getResources().getColor(R.color.profile_border));
        my_photo.setBorderWidth(20);
        my_photo.setShadowColor(getResources().getColor(R.color.profile_border));
        course_tv = (TextView) view.findViewById(R.id.course);
        search = (EditText) view.findViewById(R.id.search);
        search_btn = (Button) view.findViewById(R.id.search_btn);
        edit_cover.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        SharedPreferences mango = getActivity().getSharedPreferences("mango", Activity.MODE_PRIVATE);
        String email = mango.getString("email_ID", "supiou");
        getdata(email);
        Log.e("GOING into retrieve","FUNCTION");
        return view;
    }

    void getdata(String email) {
        String short_email = email;
        short_email = short_email.substring(0, 5);
        Log.e("short_email", short_email);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        final String message;
        Log.e("SECOND", "ENTERED ON INSERT USER");
        profileAPI api = adapter.create(profileAPI.class);
        api.insertUser(
                //Passing the values by getting it from editTexts
                short_email,
                //Creating an anonymous callback
                new Callback<wrapper_profile_model>() {
                    @Override
                    public void success(wrapper_profile_model result, Response response) {
                        BufferedReader reader = null;
                        responseData = result;
                        SID = responseData.getprofile().get(0).getS_id();
                        Log.e("Response", responseData.toString());
                        Log.e("getting results", responseData.getprofile().get(0).getS_f_name());
                        showitems();
                        SharedPreferences sp =getActivity().getSharedPreferences("mango", Activity.MODE_PRIVATE);
                        if (sp.contains("uploaded")) retrieve_image();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );

    }

    void showitems() {
        String name = responseData.getprofile().get(0).getS_f_name() + " " + responseData.getprofile().get(0).getS_l_name();
        String course = responseData.getprofile().get(0).getS_course();
        name_tv.setText(name);
        course_tv.setText(course);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(getPickImageChooserIntent(), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = getPickImageResultUri(data);
            File file = new File(getRealPathFromURI(imageUri));
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            Log.e("Test_Bitmap", "Absolute Path " + file.getAbsolutePath() + "  real path " + getRealPathFromURI(imageUri));
            my_photo.setImageBitmap(bitmap);
            //uploadImage();
            upload_image_retrofit(file);
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return data.getData();
    }


    @Override
    public void onClick(View v) {
        if (v == edit_cover) {
            showFileChooser();
            //uploadImage();
        }
        if (v == search_btn) {
            String person = search.getText().toString();
            int firstSpace = person.indexOf(" "); // detect the first space character
            String firstName = person.substring(0, firstSpace);  // get everything upto the first space character
            String lastName = person.substring(firstSpace).trim();
            search_person(firstName, lastName);
        }
    }


    void search_person(String s_f_name, String s_l_name) {
        Log.e("NAMES SENT", s_f_name + " " + s_l_name);
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
                    public void success(wrapper_people_model result, Response response) {
                        BufferedReader reader = null;
                        responsedata2 = result;
                        size2 = responsedata2.getpeople().size();
                        Log.e("Response", responsedata2.toString());
                        Log.e("getting results", responsedata2.getpeople().get(0).gets_f_name());
                        show_alumni_list();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );
    }

    public Intent getPickImageChooserIntent() {

// Determine Uri of camera image to  save.


        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getActivity().getPackageManager();

// collect all camera intents

// collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

// the main intent is the last in the  list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

// Create a chooser from the main  intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

// Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }


    void show_alumni_list() {
        database.name_list = new String[size2];
        for (int i = 0; i < size2; i++) {
            database.name_list[i] = responsedata2.getpeople().get(i).gets_f_name() +
                    responsedata2.getpeople().get(i).gets_l_name();
        }
        database.image_list = new Integer[size2];
        for (int i = 0; i < size2; i++) {
            database.image_list[i] = R.drawable.images1;
        }
        Intent intent = new Intent(getActivity(), showAlumniList.class);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mactivity = (onePerson) getActivity();
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    void retrieve_image()
    {
        int s_id = aboutMe.SID;
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
                        responsedata4 = result;
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
                        Picasso.with(getContext()).load(image_source).into(my_photo);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH", error.toString());
                    }
                }
        );
    }


    void upload_image_retrofit(File file)
    {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        TypedFile typedFile = new TypedFile("multipart/form-data", file);
        int s_id = 11528;
        ServerAPI api = adapter.create(ServerAPI.class);
        api.upload(
                typedFile,
                s_id,
                new Callback<Response>()
                {
            @Override
            public void success(Response result, Response response) {

                Response myresonse = result;
                Log.e("iN SENDING IMAGE", result.toString());
                Log.e("Upload", "success");
                flag_first_time = 1;
                SharedPreferences sp =getActivity().getSharedPreferences("mango", Activity.MODE_PRIVATE);
                SharedPreferences.Editor my_editor = sp.edit();
                my_editor.putBoolean("uploaded", true);
                my_editor.apply();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Upload", error.toString());
            }
        });
}




}
