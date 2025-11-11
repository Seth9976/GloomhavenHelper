package com.google.android.gms.internal.ads;

public abstract class zzawb {
    private volatile Thread thread;
    private final Runnable zzdsu;
    private boolean zzdsv;

    public zzawb() {
        this.zzdsu = new zzawe(this);
        this.zzdsv = false;
    }

    public abstract void zztz();

    public final zzdof zzvw() {
        return zzazq.zzdxl.zzf(this.zzdsu);
    }
}

