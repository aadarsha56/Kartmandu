package com.example.kartmandu.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kartmandu.Api.UserApi;
import com.example.kartmandu.Model.User;
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
public class RegisterFragment extends Fragment implements View.OnClickListener {
    EditText  fname, lname, email, username, password;
    Button btn_reg;
    UserApi uapi;

    public RegisterFragment() {

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view= inflater.inflate(R.layout.registerfragment, container, false);

            fname= view.findViewById(R.id.et_fname);
            lname=view.findViewById(R.id.et_lname);
            username=view.findViewById(R.id.et_username);
            email=view.findViewById(R.id.et_email);
            password=view.findViewById(R.id.et_password);
            btn_reg=view.findViewById(R.id.btn_register);

            btn_reg.setOnClickListener(this);

            return view;
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btn_register)
            {
                //validation_Registration();
                Gson gson=new GsonBuilder()
                        .setLenient().create();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:6060/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                uapi=retrofit.create(UserApi.class);

                User userModel= new User( fname.getText().toString(),
                lname.getText().toString(),
                email.getText().toString(),
                username.getText().toString(),
                password.getText().toString());


                Call<Void> Usercall=uapi.useradd(userModel);
                Usercall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }
    }
