package com.example.kartmandu.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kartmandu.Api.UserApi;
import com.example.kartmandu.BLL.BLLLogin;
import com.example.kartmandu.DashboardMain;
import com.example.kartmandu.Model.Authtoken;
import com.example.kartmandu.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText etuname, etpass;
    Button btn_login;
    UserApi uapi;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private SensorManager sensorManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginfragment, container, false);
        proximity();

        etuname = view.findViewById(R.id.et_lusername);
        etpass = view.findViewById(R.id.et_lpassword);
        btn_login = view.findViewById(R.id.btn_lsignin);

        btn_login.setOnClickListener(this);

        return view;
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_lsignin) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            uapi = retrofit.create(UserApi.class);

            String uname = etuname.getText().toString();
            String pass = etpass.getText().toString();

            final BLLLogin bllLogin = new BLLLogin(uname, pass);
            StrictMode();
            Authtoken authtoken = bllLogin.checkUser();
            if (!authtoken.getToken().isEmpty()) {

                preferences = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);

                String fname = authtoken.getUser().getFname();
                String lname = authtoken.getUser().getLname();
                String email = authtoken.getUser().getEmail();
                String username = authtoken.getUser().getUsername();
                String password = authtoken.getUser().getPassword();

                editor = preferences.edit();
                editor.putString("firstname", fname).commit();
                editor.putString("lastname", lname).commit();
                editor.putString("email", email).commit();
                editor.putString("username", username).commit();
                editor.putString("password", password).commit();


                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DashboardMain.class);
                startActivity(intent);

            } else {
                Toast.makeText(getActivity(), "Error while logging in", Toast.LENGTH_SHORT).show();
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(3000);

            }
        }
    }

//    public void proximity() {
//        sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
//        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
//
//        if (sensor == null) {
//            Toast.makeText(this, "No sensor detected", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Sensor Kicking in .....", Toast.LENGTH_SHORT).show();
//        }

        private void proximity() {
            sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            SensorEventListener proxilistener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values[0] <= 2) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
            sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        }
    }





//            Call<Authtoken> Logincall=uapi.login(uname,pass);
//            Logincall.enqueue(new Callback<Authtoken>() {
//                @Override
//                public void onResponse(Call<Authtoken> call, Response<Authtoken> response) {
//                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(getActivity(), DashboardMain.class);
//                    startActivity(intent);
//
//
//                }
//
//                @Override
//                public void onFailure(Call<Authtoken> call, Throwable t) {
//                    Toast.makeText(getActivity(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
//                    vibrator.vibrate(3000);
//                }
//            });
//        }
//
//    }
//}
