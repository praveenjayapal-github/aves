package com.techbros.myflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import java.util.ArrayList;

public class PhoneBook extends AppCompatActivity {

    ProgressBar pb;
    ImageButton wc,wm,el,dac,pl,all;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ArrayList<PhoneBookDetails> arrayList = new ArrayList<>();
    private ArrayList<PhoneBookDetails> temp = new ArrayList<>();
    private String url = "https://script.google.com/macros/s/AKfycbxMEYfs4ZgNliWS-tIPDqvyd2Zs6l8BRzrOn4u11aBwGeN91kT0eKt8ksXXWcTf7Xgr/exec?sheet=contact&start=1&end=100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        wc = findViewById(R.id.ib1);
        wm = findViewById(R.id.ib2);
        el = findViewById(R.id.ib3);
        dac = findViewById(R.id.ib4);
        pl = findViewById(R.id.ib5);
        all = findViewById(R.id.ib6);

        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray list = new JSONArray(response.toString());
                    for (int i=0; i<list.length()-1; i++) {
                        JSONObject users = null;
                        users = list.getJSONObject(i);
                        String index = users.getString("index");
                        String name = users.getString("name");
                        String contact = users.getString("contact");
                        String service = users.getString("service");
                        PhoneBookDetails phoneBookDetails = new PhoneBookDetails(index,name,contact,service);
                        arrayList.add(phoneBookDetails);
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
                Toast.makeText(getApplicationContext(),"Error :" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        });

        mRequestQueue.add(mStringRequest);
        wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "Drinking Water";
                temp.clear();
                for(int i=0;i<arrayList.size();i++){
                    if(arrayList.get(i).getService().equalsIgnoreCase(value)){
                        temp.add(arrayList.get(i));
                    }
                }
                setListView(temp);
            }
        });
        wm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "Watchman";
                temp.clear();
                for(int i=0;i<arrayList.size();i++){
                    if(arrayList.get(i).getService().equalsIgnoreCase(value)){
                        temp.add(arrayList.get(i));
                    }
                }
                setListView(temp);
            }
        });
        el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "Electrician";
                temp.clear();
                for(int i=0;i<arrayList.size();i++){
                    if(arrayList.get(i).getService().equalsIgnoreCase(value)){
                        temp.add(arrayList.get(i));
                    }
                }
                setListView(temp);
            }
        });
        dac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "DAC";
                temp.clear();
                for(int i=0;i<arrayList.size();i++){
                    if(arrayList.get(i).getService().equalsIgnoreCase(value)){
                        temp.add(arrayList.get(i));
                    }
                }
                setListView(temp);
            }
        });
        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "plumber";
                temp.clear();
                for(int i=0;i<arrayList.size();i++){
                    if(arrayList.get(i).getService().equalsIgnoreCase(value)){
                        temp.add(arrayList.get(i));
                    }
                }
                setListView(temp);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListView(arrayList);
            }
        });
    }

    private void setListView(ArrayList<PhoneBookDetails> arrayList) {
        pb.setVisibility(ProgressBar.INVISIBLE);

        ListView carsListView = findViewById(R.id.list_view);
        PhoneAdapter adapter = new PhoneAdapter(this, arrayList);
        carsListView.setAdapter(adapter);
    }
}
class PhoneBookDetails{
    String index,name,contact,service;

    public PhoneBookDetails(String index, String name, String contact, String service) {
        this.index = index;
        this.name = name;
        this.contact = contact;
        this.service = service;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}