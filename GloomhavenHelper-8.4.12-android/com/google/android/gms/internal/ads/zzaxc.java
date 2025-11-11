package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.TelephonyManager;

@TargetApi(26)
public class zzaxc extends zzawz {
    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzawu
    public final zztf zza(Context context0, TelephonyManager telephonyManager0) {
        return !zzawo.zzq(context0, "android.permission.ACCESS_NETWORK_STATE") || !telephonyManager0.isDataEnabled() ? zztf.zzbwh : zztf.zzbwi;
    }
}

