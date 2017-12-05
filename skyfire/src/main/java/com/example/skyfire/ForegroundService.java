package com.example.skyfire;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ttlnisoffice on 11/30/17.
 */

public class ForegroundService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String title = getTitle(remoteMessage);
        int icon = getIcon();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle(title)
                .setContentText(remoteMessage.getNotification().getBody());

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());


    }

    private String getTitle(RemoteMessage remoteMessage) {
        String remoteTitle = remoteMessage.getNotification().getTitle();
        String savedTitle = new SkyFireManager(this).getMessageTitle();
        String title = "Default title";
        if (remoteTitle != null) {
            title = remoteTitle;
        } else {
            if (savedTitle != null) {
                title = savedTitle;
            }
        }

        return title;
    }

    private int getIcon() {
        int icon = new SkyFireManager(this).getMessageIcon();
        if (icon == 404) {
            icon = android.R.drawable.btn_plus;
        }
        return icon;
    }
}
