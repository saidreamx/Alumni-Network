package com.example.sanjeev.alumninetwork.peopleList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.adapters.customAdapter;
import com.example.sanjeev.alumninetwork.profileInfo.showOthersProfile;
import com.example.sanjeev.alumninetwork.splash.database;


// This class is used to display the search results of people in the community
// Here we have used the custom adapter for list view implementation.
public class showAlumniList extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        customAdapter adapter = new
                customAdapter(showAlumniList.this, database.name_list, database.image_list);
        list = (ListView) findViewById(R.id.listView_in_info);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                go_to_person(database.name_list[position]);
            }
        });
    }
    void go_to_person(String name)
    {
        Intent intent = new Intent(showAlumniList.this, showOthersProfile.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    }


