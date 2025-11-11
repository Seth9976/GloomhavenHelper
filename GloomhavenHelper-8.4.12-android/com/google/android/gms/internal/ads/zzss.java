package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzss {
    @VisibleForTesting
    zzgh zzbul;
    @VisibleForTesting
    boolean zzbum;

    public zzss() {
    }

    public zzss(Context context0) {
        zzzx.initialize(context0);
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcot)).booleanValue()) {
            try {
                this.zzbul = (zzgh)zzazk.zza(context0, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", zzsu.zzbun);
                ObjectWrapper.wrap(context0);
                this.zzbul.zza(ObjectWrapper.wrap(context0), "GMA_SDK");
                this.zzbum = true;
            }
            catch(zzazm | RemoteException | NullPointerException unused_ex) {
                zzazh.zzeb("Cannot dynamite load clearcut");
            }
        }
    }

    public zzss(Context context0, String s, String s1) {
        zzzx.initialize(context0);
        try {
            this.zzbul = (zzgh)zzazk.zza(context0, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", zzsv.zzbun);
            ObjectWrapper.wrap(context0);
            this.zzbul.zza(ObjectWrapper.wrap(context0), s, null);
            this.zzbum = true;
        }
        catch(zzazm | RemoteException | NullPointerException unused_ex) {
            zzazh.zzeb("Cannot dynamite load clearcut");
        }
    }

    public final zzsw zzf(byte[] arr_b) {
        return new zzsw(this, arr_b, null);
    }
}

