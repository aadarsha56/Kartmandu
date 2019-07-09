package com.example.kartmandu.BLL;

import com.example.kartmandu.Api.UserApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BLLLogin {
    UserApi uapi;

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:6060/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //uapi = retrofit.create(UserApi.class);
}
