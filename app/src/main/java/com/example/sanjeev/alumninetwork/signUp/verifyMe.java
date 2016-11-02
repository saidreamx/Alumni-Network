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

/**
 * Created by sanjeev on 31/10/16.
 */
public class verifyMe extends Fragment
{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.register3, container, false);
        Button thisIsMe = (Button) view.findViewById(R.id.me_button);
        Button thisIsNotMe = (Button) view.findViewById(R.id.not_me_button);
        thisIsMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                collectionLoginSignup.viewPager.setCurrentItem(collectionLoginSignup.viewPager.getCurrentItem()+1);
            }
        });
        thisIsNotMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), mail_Developer.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
