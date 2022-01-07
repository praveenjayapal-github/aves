package com.techbros.myflat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationAdapter extends ArrayAdapter<NotificationDetails> {

    public NotificationAdapter(Activity context, ArrayList<NotificationDetails> notificationDetails) {
        super(context, 0, notificationDetails);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.notification_list_item, parent, false
            );
        }

        NotificationDetails notificationDetails = getItem(position);
        TextView productTextView = (TextView) listItemView.findViewById(R.id.textView2);
        productTextView.setText(notificationDetails.getNotification());

        TextView tv = (TextView) listItemView.findViewById(R.id.textView22);
        tv.setText("PostedBy: " + notificationDetails.getPostedBy());

        return listItemView;
    }
}
