package com.example.sanjeev.alumninetwork.discussion_forum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.sanjeev.alumninetwork.R;

public class postByPeople extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        lv = (ListView) findViewById(R.id.listView_in_info);
        postAdapter adapter = new postAdapter(this);
        lv.setAdapter(adapter);
    }
}
