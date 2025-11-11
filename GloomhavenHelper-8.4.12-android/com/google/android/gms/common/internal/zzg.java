package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri.Builder;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;

public final class zzg {
    private static final Uri zzed;
    private static final Uri zzee;

    static {
        Uri uri0 = Uri.parse("https://plus.google.com/");
        zzg.zzed = uri0;
        zzg.zzee = uri0.buildUpon().appendPath("circles").appendPath("find").build();
    }

    public static Intent zza(String s, @Nullable String s1) {
        Intent intent0 = new Intent("android.intent.action.VIEW");
        Uri.Builder uri$Builder0 = Uri.parse("market://details").buildUpon().appendQueryParameter("id", s);
        if(!TextUtils.isEmpty(s1)) {
            uri$Builder0.appendQueryParameter("pcampaignid", s1);
        }
        intent0.setData(uri$Builder0.build());
        intent0.setPackage("com.android.vending");
        intent0.addFlags(0x80000);
        return intent0;
    }

    public static Intent zzg(String s) {
        Uri uri0 = Uri.fromParts("package", s, null);
        Intent intent0 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent0.setData(uri0);
        return intent0;
    }

    public static Intent zzs() {
        Intent intent0 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent0.setPackage("com.google.android.wearable.app");
        return intent0;
    }
}

