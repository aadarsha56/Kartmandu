package com.example.kartmandu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardMain extends AppCompatActivity implements View.OnClickListener {
Button products, profile, feedback, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);

        products = findViewById(R.id.viewProducts);
        products.setOnClickListener(this);

        profile = findViewById(R.id.viewProfile);
        profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.viewProducts){
            Intent intent = new Intent(DashboardMain.this, Products.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.feedback){
            Intent intent = new Intent (DashboardMain.this, Feedback.class);
            startActivity(intent);
        }
        if(v.getId()== R.id.viewProfile){
            Intent intent = new Intent(DashboardMain.this, Profile.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.logout){
            Intent intent = new Intent (DashboardMain.this, Logout.class);
        }

    }
}
