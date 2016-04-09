package net.kosen10s.nicebox.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by yasuhiro on 4/9/16.
 */
public class StatusPreference {
    static private final String key = "nicebox";
    static private final String statusKey = "status";
    private Context mContext;

    public StatusPreference(Context mContext) {
        this.mContext = mContext;
    }

    public int getStatus() {
        SharedPreferences prefs = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
        return prefs.getInt(statusKey, 0);
    }

    public boolean setStatus(int status) {
        SharedPreferences prefs = mContext.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(statusKey, status);
        return editor.commit();
    }

    public boolean increaseStatus() {
        int status = getStatus();
        return setStatus(status + 1);
    }
}
