package com.techbros.myflat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class profiles extends AppCompatActivity {

    //ListView lv;
    ProgressBar pb;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ArrayList<UserDetails> arrayList = new ArrayList<>();
    private String url = "https://script.google.com/macros/s/AKfycbxMEYfs4ZgNliWS-tIPDqvyd2Zs6l8BRzrOn4u11aBwGeN91kT0eKt8ksXXWcTf7Xgr/exec?sheet=list&start=1&end=100";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                //tv.setText(response.toString());
                try {
                    JSONArray list = new JSONArray(response.toString());
                    for (int i=0; i<list.length()-1; i++) {
                        JSONObject users = null;
                        users = list.getJSONObject(i);
                        String index = users.getString("index");
                        String block = users.getString("block");
                        String flatNumber = users.getString("flat_number");
                        String name = users.getString("name");
                        String phone = users.getString("phone");
                        String occupied = users.getString("occupied");
                        String tenantName = users.getString("tenant_name");
                        UserDetails userDetails = new UserDetails(index,block,flatNumber,name,phone,occupied,tenantName);
                        arrayList.add(userDetails);
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
        //ArrayList<UserDetails> carsDetails = UsersSheet.extractUsersDetails();


    }

    private void setListView(ArrayList<UserDetails> arrayList) {
        pb.setVisibility(ProgressBar.INVISIBLE);
        ListView carsListView = findViewById(R.id.list_view);
        UserAdapter adapter = new UserAdapter(this, this.arrayList);
        carsListView.setAdapter(adapter);
        carsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),"Item"+arrayList.get(position).getBlock(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),UserDetailsDisplay.class);
                i.putExtra("index", arrayList.get(position).getIndex());
                i.putExtra("block", arrayList.get(position).getBlock());
                i.putExtra("flatNumber", arrayList.get(position).getFlatNumber());
                i.putExtra("name", arrayList.get(position).getName());
                i.putExtra("phone", arrayList.get(position).getPhone());
                i.putExtra("occupied", arrayList.get(position).getOccupied());
                i.putExtra("tenantName", arrayList.get(position).getTenantName());
                startActivity(i);
            }
        });
    }

}
class UserDetails {

    String index,block,flatNumber,name,phone,occupied,tenantName;


    public UserDetails(String index, String block, String flatNumber, String name, String phone, String occupied, String tenantName) {
        this.index = index;
        this.block = block;
        this.flatNumber = flatNumber;
        this.name = name;
        this.phone = phone;
        this.occupied = occupied;
        this.tenantName = tenantName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

}
