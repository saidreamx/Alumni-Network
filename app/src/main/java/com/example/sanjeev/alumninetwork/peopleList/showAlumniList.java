package com.example.sanjeev.alumninetwork.peopleList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.R;

import java.util.ArrayList;

public class showAlumniList extends ListActivity {
    showAlumniList()
    {

    }
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        /*String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };*/
        ArrayList<alumniListModel> values= new ArrayList<alumniListModel>();
        /*customAdapter adapter = new customAdapter(this, values);
        setListAdapter(adapter);
    */}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
}
