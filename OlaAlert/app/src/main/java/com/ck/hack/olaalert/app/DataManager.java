package com.ck.hack.olaalert.app;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ck.hack.olaalert.service.EmergencyService;
import com.ck.hack.olaalert.service.OlaService;
import com.ck.hack.olaalert.utils.BackgroundLooper;

public class DataManager {

    private static final String LOGTAG = DataManager.class.getSimpleName();
    private static DataManager sInstance;
    private final Object mLock = new Object();
    private Context mContext;
    private volatile boolean mLoaded = false;
    private Handler mBkgHandler = new Handler(BackgroundLooper.getInstance());

    private AppInfo mAppInfo;
    private UserInfo mUserInfo;

    private RequestQueue mRequestQueue;

    private EmergencyService mEmergService;
    private OlaService mOlaService;

    private ImageLoader mImageLoader;

    private DataManager(Context c) {
        mContext = c;
    }

    public static DataManager getInstance(Context c) {
        if (sInstance == null) {
            Log.v(LOGTAG, "Creating data manager instance");
            sInstance = new DataManager(c);
        }
        return sInstance;
    }

    public void init() {

        mBkgHandler.post(new Runnable() {
            @Override
            public void run() {

                synchronized (mLock) {

                    mAppInfo = new AppInfo(mContext);
                    mAppInfo.init();

                    mUserInfo = new UserInfo(mContext);
                    mUserInfo.init();


                    mRequestQueue = Volley.newRequestQueue(mContext);

                    mLoaded = true;
                    mLock.notifyAll();
                }
            }
        });
    }

    private void awaitLoadCompletion() {
        synchronized (mLock) {
            while (!mLoaded) {
                try {
                    mLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clearAllRequests() {
        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public AppInfo getAppInfo() {
        awaitLoadCompletion();
        synchronized (mLock) {
            return mAppInfo;
        }
    }

    public UserInfo getUserInfo() {
        awaitLoadCompletion();
        synchronized (mLock) {
            return mUserInfo;
        }
    }




    public void terminate() {
        // TODO
    }


    /**
     * Clean up on logging out
     */
    public void clearDataManager() {
        mUserInfo = null;
        mBkgHandler.removeCallbacksAndMessages(null);
    }

}
