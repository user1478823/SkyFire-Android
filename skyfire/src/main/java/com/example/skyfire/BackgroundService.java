package com.example.skyfire;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ttlnisoffice on 12/4/17.
 */

public class BackgroundService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Log.d("TOKEN:", FirebaseInstanceId.getInstance().getToken());
    }
}
