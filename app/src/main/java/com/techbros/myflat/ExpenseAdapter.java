package com.techbros.myflat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpenseAdapter extends ArrayAdapter<ExpenseDetails> {


    public ExpenseAdapter(Activity context, ArrayList<ExpenseDetails> expenseDetails){
        super(context, 0, expenseDetails);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.expenses_list_item, parent, false
            );
        }

        ExpenseDetails expenseDetails = getItem(position);

        TextView month =  (TextView) listItemView.findViewById(R.id.month);
        TextView date =  (TextView) listItemView.findViewById(R.id.date);
        TextView type1 =  (TextView) listItemView.findViewById(R.id.type1);
        TextView type2 =  (TextView) listItemView.findViewById(R.id.type2);
        TextView amount =  (TextView) listItemView.findViewById(R.id.amount);
        TextView paymentType =  (TextView) listItemView.findViewById(R.id.paymentType);
        TextView remark =  (TextView) listItemView.findViewById(R.id.remark);
        month.setText(expenseDetails.getMonth());
        date.setText(expenseDetails.getDate());
        type1.setText(expenseDetails.getType1());
        type2.setText(expenseDetails.getType2());
        amount.setText(expenseDetails.getAmount());
        paymentType.setText(expenseDetails.getPaymentType());
        remark.setText(expenseDetails.getRemark());

        return listItemView;
    }
}
