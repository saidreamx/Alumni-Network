package com.example.sanjeev.alumninetwork.discussion_forum;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanjeev.alumninetwork.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class comment_adapter extends BaseAdapter
{
    Context context;
    CircularImageView commentor_image;
    TextView commentor_name;
    TextView comment;
    String [] comments;
    String [] dates;
    Bitmap [] dps;
    String [] names;
    public comment_adapter(Context context,String[] names, String[] comments, String[] dates, Bitmap[] dps)
    {
        this.context = context;
        this.comments = comments;
        this.dates = dates;
        this.dps = dps;
        this.names = names;
    }
    @Override
    public int getCount() {
        return comments.length;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.comment_page, parent, false);
        commentor_image = (CircularImageView)rowView.findViewById(R.id.commentor_pic);
        comment = (TextView) rowView.findViewById(R.id.comment);
        commentor_name = (TextView) rowView.findViewById(R.id.name_of_commentor);
        Log.e("POSITIONIS:", position+"");
        comment.setText(comments[position]);
        commentor_name.setText(names[position]);
        commentor_image.setImageBitmap(dps[position]);
        return rowView;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
