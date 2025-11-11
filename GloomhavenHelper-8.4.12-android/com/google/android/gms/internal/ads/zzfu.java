package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzfu extends zzfv {
    private final View zzzp;

    public zzfu(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1, View view0) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 57);
        this.zzzp = view0;
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(this.zzzp != null) {
            Boolean boolean0 = (Boolean)zzvh.zzpd().zzd(zzzx.zzcml);
            DisplayMetrics displayMetrics0 = this.zzuy.getContext().getResources().getDisplayMetrics();
            zzes zzes0 = new zzes(((String)this.zzaah.invoke(null, this.zzzp, displayMetrics0, boolean0)));
            zza zzbs$zza$zzf$zza0 = zzf.zzau();
            zzbs$zza$zzf$zza0.zzdc(((long)zzes0.zzzj)).zzdd(((long)zzes0.zzzk)).zzde(((long)zzes0.zzzl));
            if(boolean0.booleanValue()) {
                zzbs$zza$zzf$zza0.zzdf(((long)zzes0.zzzm));
            }
            this.zzzx.zzb(((zzf)(((zzdyz)zzbs$zza$zzf$zza0.zzbcx()))));
        }
    }
}

