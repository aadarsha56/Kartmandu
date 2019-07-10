package com.example.kartmandu.Channel;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class Channel {

    Context context;
    public final static String Channel_1 = "Channel1";
//    public final static String Channel_2 = "Channel2";

    //    Constructor
    public Channel(Context context) {
        this.context = context;
    }

    public void Channel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    Channel_1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is chanel 1");

           ;
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }


    }
}