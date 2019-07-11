package com.example.kartmandu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity {
    EditText p_et_fname,p_et_lname,p_et_username,p_et_email,p_et_password;
    Button p_btn_update;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        p_et_fname=findViewById(R.id.p_et_fname);
        p_et_lname=findViewById(R.id.p_et_lname);
        p_et_username=findViewById(R.id.p_et_username);
        p_et_email=findViewById(R.id.p_et_email);
        p_et_password=findViewById(R.id.p_et_password);

        preferences=getSharedPreferences("user_data", Context.MODE_PRIVATE);

        String fname=preferences.getString("firstname","");

        p_et_fname.setText(fname);

        String lname=preferences.getString("lastname","");

        p_et_lname.setText(lname);

        String username=preferences.getString("username","");

        p_et_username.setText(username);

        String email =preferences.getString("email","");

        p_et_email.setText(email);

        String password = preferences.getString("password","");

        p_et_password.setText(password);


    }
}
