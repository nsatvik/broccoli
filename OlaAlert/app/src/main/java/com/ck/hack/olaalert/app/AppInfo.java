package com.ck.hack.olaalert.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ck.hack.olaalert.utils.Utils;

/**
 * Created by Satvik on 26/09/15.
 */
public class AppInfo {

    private static final String LOGTAG = AppInfo.class.getSimpleName();


    private SharedPreferences mPref;
    private Context mContext;

    public AppInfo(Context context) {
        mContext = context;
    }


    public void init() {
        Utils.assertNotInMainThread();
        Log.v(LOGTAG, "Loading up user info");

        mPref = PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
