package com.techbros.myflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetailsDisplay extends AppCompatActivity {

    TextView block_t,flatNumber_t,name_t,phone_t,occupied_t,tenantName_t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_display);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        Intent intent = getIntent();
        String index = intent.getStringExtra("index");
        String block = intent.getStringExtra("block");
        String flatNumber = intent.getStringExtra("flatNumber");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String occupied = intent.getStringExtra("occupied");
        String tenantName = intent.getStringExtra("tenantName");
        block_t = findViewById(R.id.blockName);
        flatNumber_t = findViewById(R.id.flatNumber);
        name_t = findViewById(R.id.name);
        phone_t = findViewById(R.id.phone);
        occupied_t = findViewById(R.id.occupied);
        tenantName_t = findViewById(R.id.tenantName);
        block_t.setText(block);
        flatNumber_t.setText(flatNumber);
        name_t.setText(name);
        phone_t.setText(phone);
        occupied_t.setText(occupied);
        tenantName_t.setText(tenantName);
    }
}