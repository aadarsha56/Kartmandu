package com.example.kartmandu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kartmandu.Model.ItemModel;
//import com.example.kartmandu.OnClick;
import com.example.kartmandu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String BASE_URL = "http://10.0.2.2:8000";
    List<ItemModel> itemModelList;
    private Context context;

    public RecyclerViewAdapter(List<ItemModel> itemModelList, Context context) {
        this.itemModelList = itemModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listproducts,viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ItemModel itemModel=itemModelList.get(i);
        viewHolder.product_name.setText(itemModel.getProdname());


        Picasso.with(context).load(BASE_URL+"/images/"+itemModel.getProdimagename()).into(viewHolder.productsimage);
        Log.d("log", "onBindViewHolder: "+BASE_URL+"/images/"+itemModel.getProdimagename());


        Toast.makeText(context, ""+itemModelList.size(), Toast.LENGTH_SHORT).show();



//        viewHolder.productsimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context vcontext= v.getContext();
//                String send_path=BASE_URL+"images/"+itemModel.getProdimagename();
//
//                System.out.println(send_path);
//
////                Intent intent=new Intent(context, OnClick.class);
////                intent.putExtra("itemName",itemModel.getProdname());
////                intent.putExtra("itemPrice",itemModel.getProdprice());
////                intent.putExtra("itemImageName",BASE_URL+"/images/"+itemModel.getProdimagename());
////                intent.putExtra("itemDescription",itemModel.getProdtype());
////
////                vcontext.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView productsimage;
        TextView product_name;
        RelativeLayout parent_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productsimage = itemView.findViewById(R.id.productsimage);
            product_name = itemView.findViewById(R.id.product_name);
            parent_layout = itemView.findViewById(R.id.parent_layout);


        }
    }
}
