package com.example.sanjeev.alumninetwork.signUp;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.profileInfo.onePerson;


public class signUp extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.register, container, false);
        Button sign_up = (Button) view.findViewById(R.id.sign_up);
        Button login = (Button) view.findViewById(R.id.login_option);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* SharedPreferences mango = getActivity().getSharedPreferences("mango", Context.MODE_PRIVATE);
                SharedPreferences.Editor my__editor = mango.edit();
                my__editor.putString("phi", latitude);
                my__editor.putString("xi", longitude);
                my__editor.apply();*/
                Intent intent = new Intent(getActivity(), logIn.class);
                startActivity(intent);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* SharedPreferences mango = getActivity().getSharedPreferences("mango", Context.MODE_PRIVATE);
                SharedPreferences.Editor my__editor = mango.edit();
                my__editor.putString("phi", latitude);
                my__editor.putString("xi", longitude);
                my__editor.apply();*/
                Intent intent = new Intent(getActivity(), onePerson.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
