package com.example.skyfire;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by ttlnisoffice on 11/30/17.
 */

public class BroadcastManager {

    Context c;

    public BroadcastManager(Context c) {
        this.c = c;
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {
            String title = intent.getStringExtra("title");
            String message = intent.getStringExtra("message");

            Notification.Builder builder = new Notification.Builder(c)
                    .setContentTitle(title)
                    .setContentText(message);

            Notification notification = builder.build();
            NotificationManager manager = (NotificationManager) c.getSystemService(NOTIFICATION_SERVICE);
            manager.notify(321, notification);
        }
    };

    public void registerNewReceiver() {
        LocalBroadcastManager.getInstance(c).registerReceiver(messageReceiver, new IntentFilter("com.example.skyfire_FCM-MESSAGE"));
    }

    public void unregisterReceiver() {
        LocalBroadcastManager.getInstance(c).unregisterReceiver(messageReceiver);
    }
}
