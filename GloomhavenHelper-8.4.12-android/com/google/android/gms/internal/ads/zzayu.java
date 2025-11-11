package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzayu implements Callable {
    private final Context val$context;
    private final Context zzdwi;

    zzayu(zzays zzays0, Context context0, Context context1) {
        this.zzdwi = context0;
        this.val$context = context1;
        super();
    }

    @Override
    public final Object call() throws Exception {
        SharedPreferences sharedPreferences0;
        boolean z = false;
        if(this.zzdwi == null) {
            zzawf.zzee("Attempting to read user agent from local cache.");
            sharedPreferences0 = this.val$context.getSharedPreferences("admob_user_agent", 0);
            z = true;
        }
        else {
            zzawf.zzee("Attempting to read user agent from Google Play Services.");
            sharedPreferences0 = this.zzdwi.getSharedPreferences("admob_user_agent", 0);
        }
        String s = sharedPreferences0.getString("user_agent", "");
        if(TextUtils.isEmpty(s)) {
            zzawf.zzee("Reading user agent from WebSettings");
            s = WebSettings.getDefaultUserAgent(this.val$context);
            if(z) {
                sharedPreferences0.edit().putString("user_agent", s).apply();
                zzawf.zzee("Persisting user agent.");
            }
        }
        return s;
    }
}

