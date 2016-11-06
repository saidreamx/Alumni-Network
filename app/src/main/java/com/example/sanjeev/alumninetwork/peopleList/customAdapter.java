package com.example.sanjeev.alumninetwork.peopleList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.profileInfo.aboutMe;
import com.example.sanjeev.alumninetwork.profileInfo.onePerson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class customAdapter extends BaseAdapter{
    String [] title;
    String [] mentor;
    String [] descp;
    Context context;
    private static LayoutInflater inflater=null;
    public customAdapter(aboutMe mainActivity, String[] title, String[] mentor, String [] descp) {
        // TODO Auto-generated constructor stub
        this.title = title;
        this.mentor = mentor;
        this.descp = descp;
        context = mainActivity.getContext();
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView title;
        TextView mentor;
        TextView descp;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.alumni_details_in_list, null);
        holder.title=(TextView) rowView.findViewById(R.id.title);
        holder.mentor=(TextView) rowView.findViewById(R.id.welcome);
        holder.descp =(TextView) rowView.findViewById(R.id.descp);

        //holder.img=(ImageView) rowView.findViewById(R.id.);
        holder.title.setText(title[position]);
        holder.mentor.setText(mentor[position]);
        holder.descp.setText(descp[position]);
//        holder.im.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+ title[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}