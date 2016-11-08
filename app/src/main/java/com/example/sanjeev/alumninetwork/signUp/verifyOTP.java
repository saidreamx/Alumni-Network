package com.example.sanjeev.alumninetwork.signUp;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class verifyOTP extends Fragment
{
    Button submit;
    Button not_me;
    EditText OTP;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view1 = inflater.inflate(R.layout.register2, container, false);
        submit = (Button) view1.findViewById(R.id.verify);
        OTP = (EditText) view1.findViewById(R.id.cnfm_otp);
        not_me = (Button) view1.findViewById(R.id.not_me_button);


        not_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mail_Developer.class);
                startActivity(intent);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = verify();
                if(status)
                {
                    Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                    SharedPreferences internal_data = getActivity().getSharedPreferences("mango", getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor editor = internal_data.edit();
                    editor.putBoolean("otp_verify", true);
                    collectionLoginSignup.viewPager.setCurrentItem(collectionLoginSignup.viewPager.getCurrentItem()+1);
                }
                else
                {
                    Toast.makeText(getContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view1;
    }

    boolean verify()
    {
        SharedPreferences internal_data = getActivity().getSharedPreferences("mango", getActivity().MODE_PRIVATE);
        String otp = internal_data.getString("OTP"," ");
        String entered_otp = OTP.getText().toString();
        Log.e("OTP :", otp);
        if (otp.equals(entered_otp)) return true;
        else return false;
    }

}
