package com.ck.hack.olaalert.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility functions
 */
public class Utils {

    public static final String BASE_URL = "http://sandbox-t.olacabs.com";

    public static final long ONE_MINUTE = 60 * 1000;
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    public static final float DEFAULT_CAMERA_ZOOM = 15.0f;

    public static boolean isValidPhoneNumber(String phNo) {
        return !TextUtils.isEmpty(phNo) && Patterns.PHONE.matcher(phNo).matches() && (phNo.length() == 10);
    }

    public static void assertNotInMainThread() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new AssertionError();
        }
    }

    public static void assertInMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new AssertionError();
        }
    }


    public static void broadcastCallIntent(Context c, String phoneNum) {
        if (c == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
        c.startActivity(intent);
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View v = activity.getCurrentFocus();
            if (v != null) {
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static boolean needsRefresh(long accessTokenExpiry) {

        // Token needs refresh if -
        // 1. Expiry is in past
        // 2. About to expire in an hour
        return System.currentTimeMillis() + ONE_HOUR >= accessTokenExpiry;
    }

    public static String formatToYesterdayOrToday(Date dateTime, String separator) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        yesterday.add(Calendar.DATE, -1);
        DateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
        DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy/MM/dd\nhh:mm a"); //TODO newline hack

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return "Today " + separator + timeFormatter.format(dateTime);
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
            return "Yesterday" + separator + timeFormatter.format(dateTime);
        } else if (calendar.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == tomorrow.get(Calendar.DAY_OF_YEAR)) {
            return "Tomorrow " + separator + timeFormatter.format(dateTime);
        } else {
            return dateTimeFormatter.format(dateTime);
        }
    }


    public static boolean isInternetConnectionEnabled(Context c) {
        if (c != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        }
        return false;

    }
}
