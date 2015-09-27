package com.ck.hack.olaalert.utils;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;

public class BackgroundLooper {

    private static Looper sLooper;

    public synchronized static Looper getInstance() {
        if (sLooper == null) {
            HandlerThread thread = new HandlerThread(BackgroundLooper.class.getName(),
                    Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();
            sLooper = thread.getLooper();
        }
        return sLooper;
    }
}