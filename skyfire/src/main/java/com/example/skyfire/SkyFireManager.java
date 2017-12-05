package com.example.skyfire;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ttlnisoffice on 12/4/17.
 */

public class SkyFireManager {

    private Context c;

    public SkyFireManager(Context c) {
        this.c = c;
    }

    public void setMessageTitle(String title) {
        PreferenceManager.getDefaultSharedPreferences(c).edit().putString("MsgTitle", title).apply();
    }

    public String getMessageTitle() {
        return PreferenceManager.getDefaultSharedPreferences(c).getString("MsgTitle", null);
    }

    public void setMessageIcon(int icon) {
        PreferenceManager.getDefaultSharedPreferences(c).edit().putInt("MsgIcon", icon).apply();
    }

    public int getMessageIcon() {
        return PreferenceManager.getDefaultSharedPreferences(c).getInt("MsgIcon", 404);
    }
}
