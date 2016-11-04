package com.example.sanjeev.alumninetwork.profileInfo;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.R;

import java.io.File;
import java.io.IOException;

public class aboutMe extends Fragment implements View.OnClickListener
{
    ImageButton edit_cover;
    ImageView coverPhoto;
    String ImageDecode;
    int PICK_IMAGE_REQUEST = 1;
    int IMG_RESULT = 1;
    onePerson mactivity;
  //  private Bitmap bitmap;
    public static Context my_context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
       // mActivity =  (onePerson)this.getActivity();
        View view = inflater.inflate(R.layout.frag_1, container, false);
        Log.e("IN oncreateview", "BBBB");
        edit_cover = (ImageButton) view.findViewById(R.id.button_choose);
        edit_cover.setOnClickListener(this);
        return view;
    }



    @Override
    public void onClick(View v) {
        if(v == edit_cover){
           mactivity.showFileChooser();
        }
       /* if(v == buttonUpload){
            uploadImage();
        }*/
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
