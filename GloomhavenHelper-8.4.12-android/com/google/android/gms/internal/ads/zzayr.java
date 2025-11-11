package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import java.util.concurrent.Callable;

final class zzayr implements Callable {
    private final Context val$context;

    zzayr(zzays zzays0, Context context0) {
        this.val$context = context0;
        super();
    }

    @Override
    public final Object call() throws Exception {
        SharedPreferences sharedPreferences0 = this.val$context.getSharedPreferences("admob_user_agent", 0);
        String s = sharedPreferences0.getString("user_agent", "");
        if(TextUtils.isEmpty(s)) {
            zzawf.zzee("User agent is not initialized on Google Play Services. Initializing.");
            s = WebSettings.getDefaultUserAgent(this.val$context);
            SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit().putString("user_agent", s);
            SharedPreferencesUtils.publishWorldReadableSharedPreferences(this.val$context, sharedPreferences$Editor0, "admob_user_agent");
            return s;
        }
        zzawf.zzee("User agent is already initialized on Google Play Services.");
        return s;
    }
}

