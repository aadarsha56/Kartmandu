package com.example.kartmandu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DashboardMain extends AppCompatActivity implements View.OnClickListener {
Button products, profile,  logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);

        products = findViewById(R.id.viewProducts);
        products.setOnClickListener(this);

        profile = findViewById(R.id.viewProfile);
        profile.setOnClickListener(this);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.viewProducts){
            Intent intent = new Intent(DashboardMain.this, Products.class);
            startActivity(intent);
        }

        if(v.getId()== R.id.viewProfile){
            Intent intent = new Intent(DashboardMain.this, Profile.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.logout){
            Intent intent = new Intent (DashboardMain.this, Logout.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Log Out Successful", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
