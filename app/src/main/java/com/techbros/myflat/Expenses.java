package com.techbros.myflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Expenses extends AppCompatActivity {


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    ArrayList<ExpenseDetails> arrayList = new ArrayList<>();
    private String url = "https://script.google.com/macros/s/AKfycbxMEYfs4ZgNliWS-tIPDqvyd2Zs6l8BRzrOn4u11aBwGeN91kT0eKt8ksXXWcTf7Xgr/exec?sheet=Expenses&start=1&end=1000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                try {
                    JSONArray list = new JSONArray(response.toString());
                    for (int i = 0; i < list.length() - 1; i++) {
                        JSONObject expense = null;
                        expense = list.getJSONObject(i);
                        String index = expense.getString("index");
                        String month = expense.getString("Month");
                        month=month.substring(0,7);
                        String date = expense.getString("Date");
                        String type1 = expense.getString("Type 1");
                        String type2 = expense.getString("Type 2");
                        String amount = expense.getString("Amount");
                        String paymentType = expense.getString("Payment Type");
                        String remark = expense.getString("Remark");
                        ExpenseDetails expenseDetails = new ExpenseDetails(index, month, date, type1, type2, amount, paymentType, remark);
                        arrayList.add(expenseDetails);
                    }
                    //System.out.println("print arraylist :: "+arrayList);
                    setListView(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error :" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        });

        mRequestQueue.add(mStringRequest);
    }
    private void setListView(ArrayList<ExpenseDetails> arrayList) {
        ListView expensesListView = findViewById(R.id.list_view);
        ExpenseAdapter adapter = new ExpenseAdapter(this, this.arrayList);
        expensesListView.setAdapter(adapter);
//        carsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(getApplicationContext(),"Item"+arrayList.get(position).getBlock(), Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(getApplicationContext(),UserDetailsDisplay.class);
//                i.putExtra("index", arrayList.get(position).getIndex());
//                i.putExtra("block", arrayList.get(position).getBlock());
//                i.putExtra("flatNumber", arrayList.get(position).getFlatNumber());
//                i.putExtra("name", arrayList.get(position).getName());
//                i.putExtra("phone", arrayList.get(position).getPhone());
//                i.putExtra("occupied", arrayList.get(position).getOccupied());
//                i.putExtra("tenantName", arrayList.get(position).getTenantName());
//                startActivity(i);
//            }
//        });
    }
}
class ExpenseDetails {
        String index,month,date,type1,type2,amount,paymentType,remark;

        public ExpenseDetails(String index, String month, String date, String type1, String type2, String amount, String paymentType, String remark) {
            this.index = index;
            this.month = month;
            this.date = date;
            this.type1 = type1;
            this.type2 = type2;
            this.amount = amount;
            this.paymentType = paymentType;
            this.remark = remark;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
