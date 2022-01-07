package com.techbros.myflat;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Home extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
    ProgressBar pb;
    AlertDialog.Builder builder;
    SharedPreferences sharedpreferences;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ArrayList<NotificationDetails> arrayList = new ArrayList<>();
    private ImageButton btnProfiles, btnExpenses, btnPhoneBook;
    private String availability;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.VISIBLE);

        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        link = sharedpreferences.getString("SheetLink", null);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        builder = new AlertDialog.Builder(this);

        btnPhoneBook = findViewById(R.id.btn_contacts);
        btnProfiles = findViewById(R.id.btn_profiles);
        btnExpenses = findViewById(R.id.btn_expenses);

        btnProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profiles.class);
                startActivity(i);
            }
        });
        btnExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Expenses.class);
                startActivity(i);
            }
        });
        btnPhoneBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PhoneBook.class);
                startActivity(i);
            }
        });
        mRequestQueue = Volley.newRequestQueue(this);

        mStringRequest = new StringRequest(Request.Method.GET, link + "Notifications&start=1&end=10", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray list = new JSONArray(response.toString());
                    for (int i = 0; i < list.length() - 1; i++) {
                        JSONObject users = null;
                        users = list.getJSONObject(i);
                        String index = users.getString("index");
                        String notification = users.getString("Notification");
                        String dateTime = users.getString("DateTime");
                        String postedBy = users.getString("PostedBy");
                        NotificationDetails notificationDetails = new NotificationDetails(index, notification, dateTime, postedBy);
                        arrayList.add(notificationDetails);
                    }
                    System.out.println("print arraylist :: " + arrayList);
                    setListView(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), R.string.err_msg, Toast.LENGTH_LONG).show();//display the response on screen

            }
        });
        mRequestQueue.add(mStringRequest);
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                    }
                });
    }

    private void setListView(ArrayList<NotificationDetails> arrayList) {
        pb.setVisibility(ProgressBar.INVISIBLE);

        ListView carsListView = findViewById(R.id.list_view);
        Collections.reverse(arrayList);
        NotificationAdapter adapter = new NotificationAdapter(this, arrayList);

        carsListView.setAdapter(adapter);
        carsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                builder.setMessage(arrayList.get(position).getNotification())
                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Notification");
                alert.show();
            }
        });
    }
}

class NotificationDetails {

    String index, notification, dateTime, postedBy;

    public NotificationDetails(String index, String notification, String dateTime, String postedBy) {
        this.index = index;
        this.notification = notification;
        this.dateTime = dateTime;
        this.postedBy = postedBy;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}
