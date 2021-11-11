package com.techbros.myflat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        TextView name =  (TextView) listItemView.findViewById(R.id.tv4);
        TextView service =  (TextView) listItemView.findViewById(R.id.tv5);
        TextView contact =  (TextView) listItemView.findViewById(R.id.tv6);
        name.setText(phoneBookDetails.getName());
        service.setText(phoneBookDetails.getService());
        contact.setText(phoneBookDetails.getContact());

        return listItemView;
    }
}
