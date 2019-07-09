package com.example.kartmandu.Api;

import com.example.kartmandu.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    @FormUrlEncoded
    @POST("login")
    Call<String> login(@Field("username") String uname, @Field("password") String password);

    @POST("registeruser")
    Call<Void> useradd(@Body User userModel);
}
