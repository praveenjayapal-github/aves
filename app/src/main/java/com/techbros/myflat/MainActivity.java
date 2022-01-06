package com.techbros.myflat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
//    public static final String SHARED_PREFS2 = "shared_prefs2";
    public static final String SHARED_PREFS_ACCESS = "shared_prefs_access";

    String link,key;//= "https://script.google.com/macros/s/AKfycbxZTVIRHTbDRVJBJDova1KLPAaK1aviZcZ6Fxe8MObrN1V0nSmIPp6gqRRU1wFrqroOtg/exec?sheet=";
    String accessCheck;
    ImageView iv;
    SharedPreferences sharedpreferences,sp2,sp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        sp2 = getSharedPreferences(SHARED_PREFS_ACCESS, Context.MODE_PRIVATE);
//        sp3 = getSharedPreferences(SHARED_PREFS_ACCESS, Context.MODE_PRIVATE);



        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        iv = findViewById(R.id.iv1);
        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.splashanim);
        iv.startAnimation(myAnim);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dac-aves-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference reference;
        DatabaseReference reference2;
//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance("https://dac-aves-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
//        mDatabase.child("SecurityKey").setValue("NewKey");
        reference = database.getReference("SheetsLink");
        reference2 = database.getReference("SecurityKey");
//        StringRequest mStringRequest = new StringRequest(Request.Method.GET, reference.toString(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                response.toString();
//            }
//        },new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), "Error :" + error.toString()+"SMS err", Toast.LENGTH_LONG).show();//display the response on screen
//
//                }
//            });
        reference.addValueEventListener(new ValueEventListener() {
                        @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    link = dataSnapshot.getValue().toString();

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    // below two lines will put values for
                    // email and password in shared preferences.
                    editor.putString("SheetLink", link);

                    // to save our data with key and value.
                    editor.apply();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    key = dataSnapshot.getValue().toString();

                    SharedPreferences.Editor editor = sp2.edit();

                    // below two lines will put values for
                    // email and password in shared preferences.
                    editor.putString("SecurityKey", key);

                    // to save our data with key and value.
                    editor.apply();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

 //       reference = database.getReference("SheetsLink");
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    availability = dataSnapshot.getValue().toString();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {

                SharedPreferences.Editor editor = sharedpreferences.edit();

                // below two lines will put values for
                // email and password in shared preferences.
                editor.putString("SheetLink", link);

                // to save our data with key and value.
                editor.apply();


                String accessCheck = sp2.getString("accessGranted", null);
                Intent i;
                if(accessCheck==null) {
                    i = new Intent(getApplicationContext(), SecurityCheck.class);
                    // close this activity
                }
                else{
                    i = new Intent(getApplicationContext(), Home.class);
                    // close this activity
                }
                startActivity(i);
                finish();

            }

        }, 1500); // wait for 1.5 seconds

    }
}