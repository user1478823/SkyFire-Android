package com.example.skyfire;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by ttlnisoffice on 11/29/17.
 */

public class BackgroundMessagingService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d("TOKEN ==>>",
                FirebaseInstanceId.getInstance().getToken());
    }
}
