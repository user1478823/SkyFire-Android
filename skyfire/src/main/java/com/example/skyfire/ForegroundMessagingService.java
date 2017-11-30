package com.example.skyfire;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ttlnisoffice on 11/30/17.
 */

public class ForegroundMessagingService extends FirebaseMessagingService {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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

            Notification notification;

            Notification.Builder builder = new Notification.Builder(this)
                    .setContentTitle(title)
                    .setContentText(message);


            notification = builder.build();

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            manager.notify(321, notification);
        }

    }
}
