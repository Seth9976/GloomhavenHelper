package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfj extends zzfv {
    public zzfj(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 3);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        boolean z = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcly)).booleanValue();
        zzdu zzdu0 = new zzdu(((String)this.zzaah.invoke(null, this.zzuy.getContext(), Boolean.valueOf(z))));
        synchronized(this.zzzx) {
            this.zzzx.zzal(zzdu0.zzxb);
            this.zzzx.zzbn(zzdu0.zzxc);
        }
    }
}

