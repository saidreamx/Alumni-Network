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
import android.provider.MediaStore;
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

import java.io.IOException;

public class aboutMe extends Fragment implements View.OnClickListener
{
    ImageButton edit_cover;
    ImageView coverPhoto;
    String ImageDecode;
    int PICK_IMAGE_REQUEST = 1;
    int IMG_RESULT = 1;
    Activity mActivity;
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

    private void showFileChooser()
    {
        Log.e("IN SHOWFILE CHOOSER", "AAAA");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMG_RESULT && resultCode == Activity.RESULT_OK
                    && null != data) {
                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContext().getContentResolver().query(URI,
                        FILE, null, null, null);
                Log.e("dbfhfbh",cursor.toString());

                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();
                coverPhoto.setImageBitmap(BitmapFactory
                        .decodeFile(ImageDecode));
            }
        } catch (Exception e) {
            Log.e("in catch", e.toString());
        }

    }


    @Override
    public void onClick(View v) {
        if(v == edit_cover){
            showFileChooser();
        }
       /* if(v == buttonUpload){
            uploadImage();
        }*/
    }
}
