package com.example.sanjeev.alumninetwork.signUp;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.profileInfo.onePerson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class signUp extends Fragment
{


    public static final String ROOT_URL = "http://getsanjeev.esy.es/";
    EditText editTextEmail;
    EditText editTextpassword;
    EditText cnfmPass;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register, container, false);

        Button sign_up = (Button) view.findViewById(R.id.sign_up);
        Button login = (Button) view.findViewById(R.id.login_option);
        editTextEmail = (EditText) view.findViewById(R.id.email_edit);
        editTextpassword = (EditText) view.findViewById(R.id.pass_edit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), logIn.class);
                startActivity(intent);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.e("FIRST","ENTERED ON CLICK SIGNUP");
                String email =  editTextEmail.getText().toString();
                String password = editTextpassword.getText().toString();
                String cnfm_password = editTextpassword.getText().toString();
                if((!email.contains("@"))||(email.length()<10))
                {
                    Toast.makeText(getContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                if(password.length()<5)
                {
                    Toast.makeText(getContext(), "Password Too Short", Toast.LENGTH_SHORT).show();
                }

                if(!password.equals(cnfm_password))
                {
                    Toast.makeText(getContext(), "Password Mismatch", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    insertUser(email, password);
                    Intent intent = new Intent(getActivity(), onePerson.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
    private void insertUser(String email, String password)
    {
        //Here we will handle the http request to insert user to mysql db
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build();

        Log.e("SECOND","ENTERED ON INSERT USER");
        registerAPI api = adapter.create(registerAPI.class);
        api.insertUser(
                //Passing the values by getting it from editTexts
               email,password,
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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Displaying the output as a toast
                        Toast.makeText(getContext(), output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("SIXTH",error.toString());
                    }
                }
        );
    }
}
