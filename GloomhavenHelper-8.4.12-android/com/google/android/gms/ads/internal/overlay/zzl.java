package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.ads.zzawo;

public final class zzl {
    public static void zza(Context context0, AdOverlayInfoParcel adOverlayInfoParcel0, boolean z) {
        if(adOverlayInfoParcel0.zzdiy == 4 && adOverlayInfoParcel0.zzdit == null) {
            if(adOverlayInfoParcel0.zzcch != null) {
                adOverlayInfoParcel0.zzcch.onAdClicked();
            }
            zza.zza(context0, adOverlayInfoParcel0.zzdis, adOverlayInfoParcel0.zzdix);
            return;
        }
        Intent intent0 = new Intent();
        intent0.setClassName(context0, "com.google.android.gms.ads.AdActivity");
        intent0.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel0.zzblu.zzdxh);
        intent0.putExtra("shouldCallOnOverlayOpened", z);
        AdOverlayInfoParcel.zza(intent0, adOverlayInfoParcel0);
        if(!(context0 instanceof Activity)) {
            intent0.addFlags(0x10000000);
        }
        zzawo.zza(context0, intent0);
    }
}

