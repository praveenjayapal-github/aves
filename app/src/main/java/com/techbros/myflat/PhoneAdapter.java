package com.techbros.myflat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneAdapter extends ArrayAdapter<PhoneBookDetails> {


    public PhoneAdapter(Activity context, ArrayList<PhoneBookDetails> phoneBookDetails){
        super(context, 0, phoneBookDetails);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.phonebook_item, parent, false
            );
        }

        PhoneBookDetails phoneBookDetails = getItem(position);
        ImageButton ib = (ImageButton) listItemView.findViewById(R.id.ib);
        TextView name =  (TextView) listItemView.findViewById(R.id.tv4);
        TextView service =  (TextView) listItemView.findViewById(R.id.tv5);
        TextView contact =  (TextView) listItemView.findViewById(R.id.tv6);
        name.setText(phoneBookDetails.getName());
        service.setText(phoneBookDetails.getService());
        contact.setText(phoneBookDetails.getContact());
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneBookDetails.getContact()));
                getContext().startActivity(callIntent);
            }
        });

        return listItemView;
    }
}
