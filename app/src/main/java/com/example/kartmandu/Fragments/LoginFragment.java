package com.example.kartmandu.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kartmandu.Api.UserApi;
import com.example.kartmandu.DashboardMain;
import com.example.kartmandu.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText etuname,etpass;
    Button btn_login;
    UserApi uapi;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.loginfragment, container, false);

        etuname=view.findViewById(R.id.et_lusername);
        etpass=view.findViewById(R.id.et_lpassword);
        btn_login=view.findViewById(R.id.btn_lsignin);

        btn_login.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_lsignin)
        {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:6060/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            uapi=retrofit.create(UserApi.class);

            String uname=etuname.getText().toString();
            String pass=etpass.getText().toString();


            Call<String> Logincall=uapi.login(uname,pass);

            Logincall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(), DashboardMain.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
