package com.example.kartmandu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.kartmandu.Adapter.RecyclerViewAdapter;
import com.example.kartmandu.Api.UserApi;
import com.example.kartmandu.Model.ItemModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Products extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ItemModel> itemModelList=new ArrayList<>();
    Retrofit retrofit;
    UserApi api;

     @Override
    protected void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_products);

         recyclerView=findViewById(R.id.products);

         getItems();
         recyclerView.setLayoutManager(new GridLayoutManager(this,2));

     }
    public void getItems()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api=retrofit.create(UserApi.class);

        Call<List<ItemModel>> listCall=api.getItems();
        listCall.enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                List<ItemModel> itemModelList=response.body();
                recyclerView.setAdapter(new RecyclerViewAdapter(itemModelList,getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                Toast.makeText(Products.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
