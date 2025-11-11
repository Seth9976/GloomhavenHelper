package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

public final class zzb {
    private SharedPreferences zzs;

    public zzb(Context context0) {
        try {
            Context context1 = GooglePlayServicesUtilLight.getRemoteContext(context0);
            this.zzs = context1 == null ? null : context1.getSharedPreferences("google_ads_flags", 0);
        }
        catch(Throwable throwable0) {
            Log.w("GmscoreFlag", "Error while getting SharedPreferences ", throwable0);
            this.zzs = null;
        }
    }

    public final boolean getBoolean(String s, boolean z) {
        try {
            return this.zzs == null ? false : this.zzs.getBoolean(s, false);
        }
        catch(Throwable throwable0) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", throwable0);
            return false;
        }
    }

    final float getFloat(String s, float f) {
        try {
            return this.zzs == null ? 0.0f : this.zzs.getFloat(s, 0.0f);
        }
        catch(Throwable throwable0) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", throwable0);
            return 0.0f;
        }
    }

    final String getString(String s, String s1) {
        try {
            return this.zzs == null ? s1 : this.zzs.getString(s, s1);
        }
        catch(Throwable throwable0) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", throwable0);
            return s1;
        }
    }
}

