package com.example.sanjeev.alumninetwork.profileInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sanjeev.alumninetwork.R;

public class postsByPerson extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.frag_3, container, false);
        Button track = (Button) view.findViewById(R.id.track_button);
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), postsByPerson.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
