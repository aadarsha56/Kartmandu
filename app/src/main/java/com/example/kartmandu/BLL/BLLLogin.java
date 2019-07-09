package com.example.kartmandu.BLL;

import com.example.kartmandu.Api.UserApi;

import com.example.kartmandu.Model.Authtoken;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BLLLogin {
    UserApi uapi;

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:6060/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //uapi = retrofit.create(UserApi.class);
    private String username;
    private String password;
    Authtoken authtoken;
    private static final String BASE_URL = "http://10.0.2.2:3000/";



    public BLLLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Authtoken  checkUser(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        UserApi userApi = retrofit.create(UserApi.class);

        Call<Authtoken> Logincall = userApi.login(username, password);
        try {
            Response<Authtoken> loginResponse = Logincall.execute();
            authtoken = loginResponse.body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return authtoken;
    }
}
