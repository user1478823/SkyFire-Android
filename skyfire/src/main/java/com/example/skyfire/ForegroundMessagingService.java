package com.example.skyfire;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ttlnisoffice on 11/30/17.
 */

public class ForegroundMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //check if there is any data available in firebase message
        if (remoteMessage.getData().size() > 0){
            String title = "Title";
            if (remoteMessage.getData().containsKey("title")){
                title = remoteMessage.getData().get("title");
            }

            String message = "message";
            if (remoteMessage.getData().containsKey("message")){
                message = remoteMessage.getData().get("message");
            }

            Intent intent = new Intent("com.example.skyfire_FCM-MESSAGE");
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            //with local br manager you make sure that your data is not
            //available to other applications
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
            lbm.sendBroadcast(intent);
        }

    }
}
