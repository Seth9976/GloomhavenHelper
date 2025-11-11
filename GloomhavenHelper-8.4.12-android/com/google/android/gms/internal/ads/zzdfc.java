package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;

public final class zzdfc {
    public static void zzc(Throwable throwable0, String s) {
        zzawf.zzez(("Ad failed to load : " + zzcid.zzd(throwable0)));
        zzawf.zza(s, throwable0);
        if(zzcid.zzd(throwable0) == 3) {
            return;
        }
        zzq.zzkz().zzb(throwable0, s);
    }

    public static void zze(Context context0, boolean z) {
        if(z) {
            zzawf.zzez("This request is sent from a test device.");
            return;
        }
        zzawf.zzez(("Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList(\"" + zzayx.zzbl(context0) + "\") to get test ads on this device."));
    }
}

