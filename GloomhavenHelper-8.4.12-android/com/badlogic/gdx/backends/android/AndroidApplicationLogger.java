package com.badlogic.gdx.backends.android;

import android.util.Log;
import com.badlogic.gdx.ApplicationLogger;

public class AndroidApplicationLogger implements ApplicationLogger {
    @Override  // com.badlogic.gdx.ApplicationLogger
    public void debug(String s, String s1) {
        Log.d(s, s1);
    }

    @Override  // com.badlogic.gdx.ApplicationLogger
    public void debug(String s, String s1, Throwable throwable0) {
        Log.d(s, s1, throwable0);
    }

    @Override  // com.badlogic.gdx.ApplicationLogger
    public void error(String s, String s1) {
        Log.e(s, s1);
    }

    @Override  // com.badlogic.gdx.ApplicationLogger
    public void error(String s, String s1, Throwable throwable0) {
        Log.e(s, s1, throwable0);
    }

    @Override  // com.badlogic.gdx.ApplicationLogger
    public void log(String s, String s1) {
        Log.i(s, s1);
    }

    @Override  // com.badlogic.gdx.ApplicationLogger
    public void log(String s, String s1, Throwable throwable0) {
        Log.i(s, s1, throwable0);
    }
}

