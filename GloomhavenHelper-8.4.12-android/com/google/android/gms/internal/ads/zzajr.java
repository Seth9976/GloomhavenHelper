package com.google.android.gms.internal.ads;

public final class zzajr extends zzbab {
    private final Object lock;
    private final zzajv zzdbe;
    private boolean zzdbf;

    public zzajr(zzajv zzajv0) {
        this.lock = new Object();
        this.zzdbe = zzajv0;
    }

    public final void release() {
        synchronized(this.lock) {
            if(this.zzdbf) {
                return;
            }
            this.zzdbf = true;
            this.zza(new zzajq(this), new zzazz());
            this.zza(new zzajt(this), new zzajs(this));
        }
    }
}

