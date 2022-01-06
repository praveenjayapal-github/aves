package com.techbros.myflat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<UserDetails> {


    public UserAdapter(Activity context, ArrayList<UserDetails> userDetails){
        super(context, 0, userDetails);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        UserDetails userDetails = getItem(position);

//        TextView ratingTextView = (TextView) listItemView.findViewById(R.id.textView);
//        ratingTextView.setText(userDetails.getBlock());

        TextView productTextView = (TextView) listItemView.findViewById(R.id.textView2);
        productTextView.setText(userDetails.getFlatNumber());

        TextView tv = (TextView) listItemView.findViewById(R.id.textView22);
        tv.setText(userDetails.getName());
        ImageView iv = listItemView.findViewById(R.id.imageView);
        if(userDetails.getBlock().equalsIgnoreCase("olive"))
        iv.setImageResource(R.drawable.olive_logo);

        else if(userDetails.getBlock().equalsIgnoreCase("maple"))
            iv.setImageResource(R.drawable.maple_logo);

        else
            iv.setImageResource(R.drawable.banyan_logo);

        return listItemView;
    }
}
