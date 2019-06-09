package com.example.kartmandu;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmailAddress, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        etEmailAddress = findViewById(R.id.et_login_email_address);
        etPassword = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            if(validateFields()){
                Toast.makeText(Login.this,"Login Validated",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean validateFields() {
        if (TextUtils.isEmpty(etEmailAddress.getText().toString())) {
            etEmailAddress.setError("Enter Email Address");
            etEmailAddress.requestFocus();
            return false;

        } else if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError("Enter Password");
            etPassword.requestFocus();
            return false;

        }
        else {
            return true;
        }
    }

}
