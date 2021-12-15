package com.techbros.myflat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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


public class SecurityCheck extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String SHARED_PREFS2 = "shared_prefs2";
    public static final String SHARED_PREFS_ACCESS = "shared_prefs_access";
    SharedPreferences sp3,sp2,sp;
    String key;
    Button verify;
    EditText et1;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_check);

        // initializing our shared preferences.
        //sp2 = getSharedPreferences(SHARED_PREFS2, Context.MODE_PRIVATE);
        sp2 = getSharedPreferences(SHARED_PREFS_ACCESS, Context.MODE_PRIVATE);
        sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        String link = sp.getString("SheetLink", null);
        //String key = sp.getString("SecurityKey", null);
        //Toast.makeText(getApplicationContext(),"Key "+link, Toast.LENGTH_LONG).show();


        //key = sp3.getString("SecurityKey", null);
        //Toast.makeText(getApplicationContext(),"Key"+key, Toast.LENGTH_LONG).show();
        verify = findViewById(R.id.verify);
        et1 = findViewById(R.id.et_security);


        mRequestQueue = Volley.newRequestQueue(this);

        mStringRequest = new StringRequest(Request.Method.GET, link+"Security&start=1&end=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                //tv.setText(response.toString());
                try {
                    JSONArray list = new JSONArray(response.toString());
                    for (int i = 0; i < list.length() - 1; i++) {
                        JSONObject users = null;
                        users = list.getJSONObject(i);
                        key = users.getString("Key");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error :" + error.toString()+"SMS err", Toast.LENGTH_LONG).show();//display the response on screen

            }
        });

        mRequestQueue.add(mStringRequest);
        //Toast.makeText(getApplicationContext(),"Link "+key, Toast.LENGTH_LONG).show();



        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Button Matched", Toast.LENGTH_LONG).show();
                String et_value  = et1.getText().toString();
                if(et_value.equals(key)){
                    Toast.makeText(getApplicationContext(),"Key Matched", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = sp2.edit();

                    // below two lines will put values for
                    // email and password in shared preferences.
                    editor.putString("accessGranted", "Yes");

                    // to save our data with key and value.
                    editor.apply();

                    Intent i = new Intent(getApplicationContext(), Home.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
                else{
                    String msg="Security Key not matching";
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}