package com.techbros.myflat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsDisplay extends AppCompatActivity {

    TextView block_t,flatNumber_t,name_t,phone_t,occupied_t,tenantName_t,tenantPhone_t;
    ImageButton ib1,ib2;
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
        String tenantPhone = intent.getStringExtra("tenantPhone");
        block_t = findViewById(R.id.blockName);
        flatNumber_t = findViewById(R.id.flatNumber);
        name_t = findViewById(R.id.name);
        phone_t = findViewById(R.id.phone);
        occupied_t = findViewById(R.id.occupied);
        tenantName_t = findViewById(R.id.tenantName);
        tenantPhone_t = findViewById(R.id.tenantNum);

        ib1 = findViewById(R.id.ib1);
        ib2 = findViewById(R.id.ib2);

        block_t.setText(block);
        flatNumber_t.setText(flatNumber);
        name_t.setText(name);
        phone_t.setText(phone);
        occupied_t.setText(occupied);
        tenantName_t.setText(tenantName);
        tenantPhone_t.setText(tenantPhone);

        if(tenantPhone.equals(""))
            ib2.setEnabled(false);

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" +phone_t.getText().toString()));
                startActivity(callIntent);
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" +tenantPhone_t.getText().toString()));
                startActivity(callIntent);
            }
        });
    }
}