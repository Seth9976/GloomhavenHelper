package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzff extends zzfv {
    public zzff(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 76);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        boolean z = ((Boolean)this.zzaah.invoke(null, this.zzuy.getContext())).booleanValue();
        this.zzzx.zzj((z ? zzcd.zzlc : zzcd.zzlb));
    }
}

