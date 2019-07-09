package com.example.kartmandu.Api;

import com.example.kartmandu.Model.Authtoken;
import com.example.kartmandu.Model.ItemModel;
import com.example.kartmandu.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @FormUrlEncoded
    @POST("login")
    Call<Authtoken> login(@Field("username") String username, @Field("password") String password);

    @POST("register")
    Call<Void> useradd(@Body User userModel);

    @GET("showproducts")
    Call<List<ItemModel>> getItems();
}
