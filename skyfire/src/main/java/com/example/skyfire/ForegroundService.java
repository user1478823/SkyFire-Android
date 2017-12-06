package com.example.skyfire;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ttlnisoffice on 11/30/17.
 */

public class ForegroundService extends FirebaseMessagingService {

    private SharedPreferences pref;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        String title   = (remoteMessage.getNotification().getTitle()  == null) ? getSavedTitle()   : remoteMessage.getNotification().getTitle();
        String message = (remoteMessage.getNotification().getBody()   == null) ? "Default message" : remoteMessage.getNotification().getBody();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(getIcon())
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(shouldAutoCancel());

        shouldPlaySound(notificationBuilder);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(message);
        bigText.setSummaryText(title);
        notificationBuilder.setStyle(bigText);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());


    }

    private int getIcon() {
        return pref.getInt("MsgIcon", getApplicationInfo().icon);
    }

    // if you don't set the title with SkyFireManager.setMessageTitle(), Application name will be
    // set as message title
    private String getSavedTitle() {
        String appName    = getApplicationInfo().loadLabel(getPackageManager()).toString();
        return pref.getString("MsgTitle", appName);
    }

    private boolean shouldAutoCancel() {
        return pref.getBoolean("MsgAutoCancel", true);
    }

    private void shouldPlaySound(NotificationCompat.Builder notificationBuilder) {
        if (pref.getBoolean("MsgShouldPlaySound", true)) {
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(defaultSoundUri);
        }
    }
}
