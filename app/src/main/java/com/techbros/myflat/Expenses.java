package com.techbros.myflat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TreeMap;

public class Expenses extends AppCompatActivity {

    ImageButton view;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    HashSet<String> monthset = new HashSet<>();
    ArrayList<ExpenseDetails> arrayList = new ArrayList<>();
    ArrayList<ExpenseDetails> temp = new ArrayList<>();

    TreeMap<String, String> dropDownHash = new TreeMap<>();
    Spinner spin;
    ProgressBar pb;

    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "shared_prefs";
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        //enable full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pb = findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.VISIBLE);
        view = findViewById(R.id.btn_view);
        mRequestQueue = Volley.newRequestQueue(this);
        spin = findViewById(R.id.spinner);

        // initializing our shared preferences
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        link = sharedpreferences.getString("SheetLink", null);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, link + "Expenses&start=1&end=10000", new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray list = new JSONArray(response.toString());
                    for (int i = 0; i < list.length() - 1; i++) {
                        JSONObject expense = null;
                        expense = list.getJSONObject(i);
                        String index = expense.getString("index");
                        String month = expense.getString("Month");
                        String cmonth = getMMMFormat(month.substring(5, 7));
                        String formattedDate = dateFormatter(month.substring(0, 10), "yyyy-MM-dd", "MMM-yyyy");

                        dropDownHash.put(month.substring(5, 7), formattedDate);

                        String date = expense.getString("Date");
                        String type1 = expense.getString("Type 1");
                        String type2 = expense.getString("Type 2");
                        String amount = expense.getString("Amount");
                        String paymentType = expense.getString("Payment Type");
                        String remark = expense.getString("Remark");

                        ExpenseDetails expenseDetails = new ExpenseDetails(index, formattedDate, date, type1, type2, amount, paymentType, remark);

                        arrayList.add(expenseDetails);
                        monthset.add(month);

                    }

                    String[] monthset1 = new String[dropDownHash.size()];

                    int iterate = 0;
                    for (String key : dropDownHash.keySet()) {
                        monthset1[iterate] = dropDownHash.get(key);
                        iterate++;
                    }

                    ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, monthset1);

                    ad.setDropDownViewResource(
                            android.R.layout
                                    .simple_spinner_dropdown_item);

                    spin.setAdapter(ad);

                    System.out.println("print set :: " + monthset);
                    setListView(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            private String getMMMFormat(String month) {
                String cmonth = "";
                switch (month) {
                    case "01":
                        cmonth = "JAN";
                        break;
                    case "02":
                        cmonth = "FEB";
                        break;
                    case "03":
                        cmonth = "MAR";
                        break;
                    case "04":
                        cmonth = "APR";
                        break;
                    case "05":
                        cmonth = "MAY";
                        break;
                    case "06":
                        cmonth = "JUN";
                        break;
                    case "07":
                        cmonth = "JUL";
                        break;
                    case "08":
                        cmonth = "AUG";
                        break;
                    case "09":
                        cmonth = "SEP";
                        break;
                    case "10":
                        cmonth = "OCT";
                        break;
                    case "11":
                        cmonth = "NOV";
                        break;
                    case "12":
                        cmonth = "DEC";
                        break;

                }
                return cmonth;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), R.string.err_msg, Toast.LENGTH_LONG).show();//display the response on screen

            }
        });

        mRequestQueue.add(mStringRequest);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = spin.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(),value, Toast.LENGTH_LONG).show();
                temp.clear();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getMonth().equalsIgnoreCase(value)) {
                        System.out.println(arrayList.get(i).getMonth().equalsIgnoreCase(value));
                        temp.add(arrayList.get(i));
                    }
                }
                setListView(temp);
                System.out.println(temp);
            }
        });

    }

    private void setListView(ArrayList<ExpenseDetails> arrayList) {
        pb.setVisibility(ProgressBar.INVISIBLE);
        ListView expensesListView = findViewById(R.id.list_view);
        ExpenseAdapter adapter = new ExpenseAdapter(this, arrayList);
        expensesListView.setAdapter(adapter);
    }

    private String dateFormatter(String dateStr, String originalDateFormat, String targetDateFormat) {
        DateFormat originalFormat = new SimpleDateFormat(originalDateFormat, Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat(targetDateFormat);
        Date date = null;
        try {
            date = originalFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }
}

class ExpenseDetails {
    String index, month, date, type1, type2, amount, paymentType, remark;

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
