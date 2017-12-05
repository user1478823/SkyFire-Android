package com.example.skyfire;

import android.app.NotificationManager;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ttlnisoffice on 11/30/17.
 */

public class ForegroundService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(getIcon())
                .setContentTitle(getTitle(remoteMessage))
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(getAutoCancel());

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());


    }

    private String getTitle(RemoteMessage remoteMessage) {
        String remoteTitle = remoteMessage.getNotification().getTitle();
        String savedTitle  = PreferenceManager.getDefaultSharedPreferences(this)
                                              .getString("MsgTitle", getApplicationInfo().loadLabel(getPackageManager()).toString());


        if (remoteTitle != null) {
            savedTitle = remoteTitle;
        }

        return savedTitle;
    }

    private int getIcon() {
        return PreferenceManager.getDefaultSharedPreferences(this)
                                .getInt("MsgIcon", getApplicationInfo().icon);
    }

    private boolean getAutoCancel() {
        return PreferenceManager.getDefaultSharedPreferences(this)
                                .getBoolean("MsgAutoCancel", true);
    }
}
