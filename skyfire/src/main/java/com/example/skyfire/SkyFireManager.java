package com.example.skyfire;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by ttlnisoffice on 12/4/17.
 */

public class SkyFireManager {

    private Context c;

    public SkyFireManager(Context c) {
        this.c = c;
    }

    public SkyFireManager setMessageTitle(String title) {
        PreferenceManager.getDefaultSharedPreferences(c).edit().putString("MsgTitle", title).apply();
        return this;
    }

    public SkyFireManager setMessageIcon(int icon) {
        PreferenceManager.getDefaultSharedPreferences(c).edit().putInt("MsgIcon", icon).apply();
        return this;
    }
}
