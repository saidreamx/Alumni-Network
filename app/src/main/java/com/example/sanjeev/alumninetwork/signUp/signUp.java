package com.example.sanjeev.alumninetwork.signUp;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
    SharedPreferences internal_data = new SharedPreferences() {
        @Override
        public Map<String, ?> getAll() {
            return null;
        }

        @Nullable
        @Override
        public String getString(String key, String defValue) {
            return null;
        }

        @Nullable
        @Override
        public Set<String> getStringSet(String key, Set<String> defValues) {
            return null;
        }

        @Override
        public int getInt(String key, int defValue) {
            return 0;
        }

        @Override
        public long getLong(String key, long defValue) {
            return 0;
        }

        @Override
        public float getFloat(String key, float defValue) {
            return 0;
        }

        @Override
        public boolean getBoolean(String key, boolean defValue) {
            return false;
        }

        @Override
        public boolean contains(String key) {
            return false;
        }

        @Override
        public Editor edit() {
            return null;
        }

        @Override
        public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        }

        @Override
        public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        }
    }
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
                }
            }
        });
        return view;
    }
    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }
    private void insertUser(String email, String password)
    {
        final String emailID;
        emailID = email;
        final String subject = "Verification CIC-Swajana";
        int random_no = gen();
        final String messagecontent = "Welcome to CIC-Swajana. Your OTP is: "+random_no;
        //Here we will handle the http request to insert user to mysql db
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build();
        final String message;
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
                            if(!output.equals("444"))
                            {
                                sendMail sm = new sendMail(getContext(), emailID, subject, messagecontent);

                                //Executing sendmail to send email
                                sm.execute();
                                Toast.makeText(getContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(), "Verification code sent to Email", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), verifyOTP.class);
                                startActivity(intent);
                            }
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
