package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicInteger;

@Deprecated
public class zzbab {
    private final zzazy zzdxv;
    private final AtomicInteger zzdxw;

    public zzbab() {
        this.zzdxv = new zzazy();
        this.zzdxw = new AtomicInteger(0);
        zzbae zzbae0 = new zzbae(this);
        zzdnt.zza(this.zzdxv, zzbae0, zzazq.zzdxp);
    }

    @Deprecated
    public final int getStatus() {
        return this.zzdxw.get();
    }

    @Deprecated
    public final void reject() {
        this.zzdxv.setException(new Exception());
    }

    @Deprecated
    public final void zza(zzbac zzbac0, zzbaa zzbaa0) {
        zzbad zzbad0 = new zzbad(this, zzbac0, zzbaa0);
        zzdnt.zza(this.zzdxv, zzbad0, zzazq.zzdxp);
    }

    @Deprecated
    public final void zzm(Object object0) {
        this.zzdxv.set(object0);
    }
}

