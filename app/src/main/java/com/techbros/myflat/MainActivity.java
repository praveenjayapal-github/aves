package com.techbros.myflat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String SHARED_PREFS_ACCESS = "shared_prefs_access";

    String link, key;
    String accessCheck;
    ImageView iv;
    SharedPreferences sharedpreferences, sp2, sp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("token :" + token);
                    }
                });

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        sp2 = getSharedPreferences(SHARED_PREFS_ACCESS, Context.MODE_PRIVATE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        iv = findViewById(R.id.iv1);

        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.splashanim);
        iv.startAnimation(myAnim);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dac-aves-default-rtdb.asia-southeast1.firebasedatabase.app/");

        DatabaseReference reference;
        DatabaseReference reference2;
        reference = database.getReference("SheetsLink");
        reference2 = database.getReference("SecurityKey");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    link = dataSnapshot.getValue().toString();

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("SheetLink", link);

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
                if (dataSnapshot.exists()) {
                    key = dataSnapshot.getValue().toString();
                    SharedPreferences.Editor editor = sp2.edit();

                    editor.putString("SecurityKey", key);

                    editor.apply();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("SheetLink", link);
                editor.apply();

                String accessCheck = sp2.getString("accessGranted", null);
                Intent i;
                if (accessCheck == null) {
                    i = new Intent(getApplicationContext(), SecurityCheck.class);
                } else {
                    i = new Intent(getApplicationContext(), Home.class);
                }
                startActivity(i);
                finish();

            }

        }, 2000); // wait for 2 seconds
    }
}