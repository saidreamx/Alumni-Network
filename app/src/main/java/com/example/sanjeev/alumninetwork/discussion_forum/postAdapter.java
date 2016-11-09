package com.example.sanjeev.alumninetwork.discussion_forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanjeev.alumninetwork.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class postAdapter  extends BaseAdapter {
    private Context context;
    public postAdapter(Context context) {
        this.context = context;
    }
    TextView name;
    TextView time;
    TextView post;
    CircularImageView profile_pic;
    ImageView like;
    CircularImageView unlike;
    ImageView comment;
    TextView comment_1;
    TextView comment_2;
    Button load_more;
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.post_page, parent, false);
        name = (TextView)rowView.findViewById(R.id.name);
        time = (TextView)rowView.findViewById(R.id.time);
        post = (TextView)rowView.findViewById(R.id.post);
        profile_pic = (CircularImageView)rowView.findViewById(R.id.profile_pic);
        like = (ImageView)rowView.findViewById(R.id.like_btn);

        comment = (ImageView)rowView.findViewById(R.id.comment_btn);

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