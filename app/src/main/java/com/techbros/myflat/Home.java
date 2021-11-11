package com.techbros.myflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    private ImageButton btnProfiles,btnExpenses,btnPhoneBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

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

    }

}