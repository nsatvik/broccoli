package com.ck.hack.olaalert.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by Satvik on 26/09/15.
 */
public class OlaAlertApp extends Application {

    private static final String LOGTAG = OlaAlertApp.class.getSimpleName();

    private DataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(LOGTAG, "onCreate");

        mDataManager = DataManager.getInstance(OlaAlertApp.this);
        mDataManager.init();
    }

    public synchronized DataManager getDataManager() {
        return mDataManager;
    }
}
