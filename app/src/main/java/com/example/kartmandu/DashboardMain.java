package com.example.kartmandu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardMain extends AppCompatActivity implements View.OnClickListener {
Button products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);

        products = findViewById(R.id.viewProducts);
        products.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.viewProducts){
            Intent intent = new Intent(DashboardMain.this, Products.class);
            startActivity(intent);
        }

    }
}
