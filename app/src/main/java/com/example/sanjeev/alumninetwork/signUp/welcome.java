package com.example.sanjeev.alumninetwork.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sanjeev.alumninetwork.R;

public class welcome extends Fragment
{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.welcome, container, false);
        Button welcome = (Button) view.findViewById(R.id.welcome);
        welcome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), logIn.class);
                startActivity(intent);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
