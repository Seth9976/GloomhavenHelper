package com.google.android.gms.common.logging;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

@KeepForSdk
public class Logger {
    private final String mTag;
    private final String zzei;
    private final GmsLogger zzew;
    private final int zzex;

    private Logger(String s, String s1) {
        this.zzei = s1;
        this.mTag = s;
        this.zzew = new GmsLogger(s);
        int v;
        for(v = 2; 7 >= v && !Log.isLoggable(this.mTag, v); ++v) {
        }
        this.zzex = v;
    }

    @KeepForSdk
    public Logger(String s, String[] arr_s) {
        String s1;
        if(arr_s.length == 0) {
            s1 = "";
        }
        else {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append('[');
            for(int v = 0; v < arr_s.length; ++v) {
                String s2 = arr_s[v];
                if(stringBuilder0.length() > 1) {
                    stringBuilder0.append(",");
                }
                stringBuilder0.append(s2);
            }
            stringBuilder0.append(']');
            stringBuilder0.append(' ');
            s1 = stringBuilder0.toString();
        }
        this(s, s1);
    }

    @KeepForSdk
    public void d(String s, @Nullable Object[] arr_object) {
        if(this.isLoggable(3)) {
            String s1 = this.format(s, arr_object);
            Log.d(this.mTag, s1);
        }
    }

    @KeepForSdk
    public void e(String s, Throwable throwable0, @Nullable Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.e(this.mTag, s1, throwable0);
    }

    @KeepForSdk
    public void e(String s, @Nullable Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.e(this.mTag, s1);
    }

    private final String format(String s, @Nullable Object[] arr_object) {
        if(arr_object != null && arr_object.length > 0) {
            s = String.format(Locale.US, s, arr_object);
        }
        return this.zzei + s;
    }

    @KeepForSdk
    public void i(String s, @Nullable Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.i(this.mTag, s1);
    }

    @KeepForSdk
    public boolean isLoggable(int v) {
        return this.zzex <= v;
    }

    @KeepForSdk
    public void v(String s, @Nullable Object[] arr_object) {
        if(this.isLoggable(2)) {
            String s1 = this.format(s, arr_object);
            Log.v(this.mTag, s1);
        }
    }

    @KeepForSdk
    public void w(String s, @Nullable Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.w(this.mTag, s1);
    }

    @KeepForSdk
    public void wtf(String s, Throwable throwable0, @Nullable Object[] arr_object) {
        String s1 = this.format(s, arr_object);
        Log.wtf(this.mTag, s1, throwable0);
    }

    @KeepForSdk
    public void wtf(Throwable throwable0) {
        Log.wtf(this.mTag, throwable0);
    }
}

