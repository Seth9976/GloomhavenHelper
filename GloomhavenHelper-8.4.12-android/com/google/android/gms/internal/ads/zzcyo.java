package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public final class zzcyo implements zzcye {
    private final zzdoe zzfrv;
    private final Context zzur;

    public zzcyo(zzdoe zzdoe0, Context context0) {
        this.zzfrv = zzdoe0;
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcyn zzcyn0 = () -> {
            TelephonyManager telephonyManager0 = (TelephonyManager)this.zzur.getSystemService("phone");
            String s = telephonyManager0.getNetworkOperator();
            int v = telephonyManager0.getNetworkType();
            int v1 = telephonyManager0.getPhoneType();
            if(zzawo.zzq(this.zzur, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager0 = (ConnectivityManager)this.zzur.getSystemService("connectivity");
                NetworkInfo networkInfo0 = connectivityManager0.getActiveNetworkInfo();
                if(networkInfo0 != null) {
                    int v2 = networkInfo0.getType();
                    int v3 = networkInfo0.getDetailedState().ordinal();
                    return new zzcyl(s, v2, v, v1, connectivityManager0.isActiveNetworkMetered(), v3);
                }
                return new zzcyl(s, -1, v, v1, connectivityManager0.isActiveNetworkMetered(), -1);
            }
            return new zzcyl(s, -2, v, v1, false, -1);
        };
        return this.zzfrv.zzd(zzcyn0);
    }

    // 检测为 Lambda 实现
    final zzcyl zzaps() throws Exception [...]
}

