package com.example.sanjeev.alumninetwork.signUp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sanjeev.alumninetwork.R;

public class verifyOTP extends Fragment
{
    Button submit;
    EditText OTP;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view1 = inflater.inflate(R.layout.register2, container, false);
        submit = (Button) view1.findViewById(R.id.verify);
        OTP = (EditText) view1.findViewById(R.id.cnfm_otp);
        return view1;
    }

}
