package com.example.kartmandu.Fragments;


import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kartmandu.Api.UserApi;
import com.example.kartmandu.Channel.Channel;
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
    private NotificationManagerCompat notificationManagerCompat;
    Button btn_reg;
//    UserApi uapi;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final String BASE_URL ="http:/10.0.2.2:8000";
    UserApi uapi;
    Retrofit retrofit;
//    UserModel userModel;

    public RegisterFragment() {

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            // Inflate the layout for this fragment
            View view= inflater.inflate(R.layout.registerfragment, container, false);
            notificationManagerCompat = NotificationManagerCompat.from(getActivity());
            Channel channel = new Channel(getActivity());
            channel.Channel();

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
                        .baseUrl("http://10.0.2.2:8000/")
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
  //                      Intent intent = new Intent(getActivity(),LoginFragment.class);
//                        startActivity(intent);
                        DispalyNotification();
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }
        private void DispalyNotification(){
            Notification notification = new NotificationCompat.Builder(getActivity(),Channel.Channel_1)
                    .setSmallIcon(R.drawable.ic_done_all_black_24dp)
                    .setContentTitle("Registered Successfully")
                    .setContentText("Your account has been registered successfully")
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();
            notificationManagerCompat.notify(1,notification);
        }
    }
