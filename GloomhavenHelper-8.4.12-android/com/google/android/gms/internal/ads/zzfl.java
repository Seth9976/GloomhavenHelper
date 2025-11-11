package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class zzfl extends zzfv {
    private List zzaab;

    public zzfl(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 0x1F);
        this.zzaab = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        this.zzzx.zzax(-1L);
        this.zzzx.zzay(-1L);
        if(this.zzaab == null) {
            this.zzaab = (List)this.zzaah.invoke(null, this.zzuy.getContext());
        }
        if(this.zzaab != null && this.zzaab.size() == 2) {
            synchronized(this.zzzx) {
                this.zzzx.zzax(((long)(((Long)this.zzaab.get(0)))));
                this.zzzx.zzay(((long)(((Long)this.zzaab.get(1)))));
            }
        }
    }
}

