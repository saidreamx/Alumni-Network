package com.example.sanjeev.alumninetwork.signUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class logIn extends Activity {
    public static final String ROOT_URL = "http://getsanjeev.esy.es/";
    Button logIn;
    EditText editTextEmail;
    EditText editTextpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        editTextEmail = (EditText) findViewById(R.id.email_edit);
        editTextpassword = (EditText) findViewById(R.id.pass_edit);
        logIn = (Button) findViewById(R.id.login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = editTextEmail.getText().toString();
                String password = editTextpassword.getText().toString();
                   // log_me_in(email, password);
                //logme_in();
                log_me_in(email, password);
            }
        });
    }

    public void log_me_in(String email, String password)
    {
        if ((!email.contains("@")) || (email.length() < 10)) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }
        if (password.length() < 5) {
            Toast.makeText(this, "Password Too Short", Toast.LENGTH_SHORT).show();
        }
        else
        {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ROOT_URL) //Setting the Root URL
                    .build();
            logInAPI api = adapter.create(logInAPI.class);
            api.logInUser(
                    email, password,
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
                                Log.e("OUTPUT: ", output);
                               if (output.equals("validation successful")) {
                                    Intent intent = new Intent(logIn.this, onePerson.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(logIn.this, "Login Error, Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //Displaying the output as a toast
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("SIXTH", error.toString());
                        }
                    }
            );
        }
    }

    void logme_in()
    {
        Intent intent = new Intent(logIn.this, onePerson.class);
        startActivity(intent);
    }
}
