package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.ads.internal.zzq;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzbfr {
    public static zzbdv zza(Context context0, zzbfl zzbfl0, String s, boolean z, boolean z1, @Nullable zzdq zzdq0, zzazo zzazo0, zzaak zzaak0, zzi zzi0, zza zza0, zzsn zzsn0, zzrp zzrp0, boolean z2) throws zzbei {
        try {
            return (zzbdv)zzayp.zza(new zzbfu(context0, zzbfl0, s, z, z1, zzdq0, zzazo0, null, zzi0, zza0, zzsn0, zzrp0, z2));
        }
        catch(Throwable throwable0) {
            zzq.zzkz().zza(throwable0, "AdWebViewFactory.newAdWebView2");
            throw new zzbei("Webview initialization failed.", throwable0);
        }
    }
}

