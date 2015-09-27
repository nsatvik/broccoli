package com.ck.hack.olaalert.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ck.hack.olaalert.R;
import com.ck.hack.olaalert.utils.Utils;

/**
 * Created by Satvik on 26/09/15.
 */
public class UserInfo {

    private static final String LOGTAG = UserInfo.class.getSimpleName();


    private SharedPreferences mPref;
    private Context mContext;

    private String mName;
    private String mEmail;
    private String mPhoneNumber;
    private boolean mIsLoggedIn;


    public UserInfo(Context context) {
        mContext = context;
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void setLoginStatus(boolean status) {
        SharedPreferences.Editor e = mPref.edit();
        mIsLoggedIn = status;
        e.putBoolean(mContext.getString(R.string.is_user_logged_in_key), status);
        e.apply();
    }


    public void init() {
        Utils.assertNotInMainThread();
        Log.v(LOGTAG, "Loading up user info");

        mPref = PreferenceManager.getDefaultSharedPreferences(mContext);

        mName = mPref.getString(mContext.getString(R.string.user_name), "");
        mEmail = mPref.getString(mContext.getString(R.string.user_email), "");
        mPhoneNumber = mPref.getString(mContext.getString(R.string.user_phone_num), "");
        mIsLoggedIn = mPref.getBoolean(mContext.getString(R.string.is_user_logged_in_key), false);
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        SharedPreferences.Editor e = mPref.edit();
        this.mPhoneNumber = phoneNumber;
        e.putString(mContext.getString(R.string.user_phone_num), phoneNumber);
        e.apply();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;

        SharedPreferences.Editor e = mPref.edit();
        e.putString(mContext.getString(R.string.user_name), mName);
        e.apply();
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
        SharedPreferences.Editor e = mPref.edit();
        e.putString(mContext.getString(R.string.user_email), mEmail);
        e.apply();
    }
}
