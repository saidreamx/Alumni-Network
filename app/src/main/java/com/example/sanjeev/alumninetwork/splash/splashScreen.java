package com.example.sanjeev.alumninetwork.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.profileInfo.onePerson;
import com.example.sanjeev.alumninetwork.signUp.collectionLoginSignup;
import com.example.sanjeev.alumninetwork.signUp.logIn;


public class splashScreen extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    SharedPreferences sp = getSharedPreferences("mango",MODE_PRIVATE);
                    if((sp.contains("check")))
                    {
                        if (sp.getBoolean("check",true)&&(sp.getBoolean("loggedin",true)))
                        {
                            Intent intent = new Intent(splashScreen.this,onePerson.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent intent = new Intent(splashScreen.this, logIn.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                    else
                    {
                        Intent intent = new Intent(splashScreen.this, collectionLoginSignup.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        };

        timerThread.start();

    }
}


