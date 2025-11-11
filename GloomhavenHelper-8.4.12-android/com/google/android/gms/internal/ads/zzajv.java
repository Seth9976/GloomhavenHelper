package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;

public final class zzajv extends zzbab {
    private final Object lock;
    private zzaxu zzdan;
    private boolean zzdbi;
    private int zzdbj;

    public zzajv(zzaxu zzaxu0) {
        this.lock = new Object();
        this.zzdan = zzaxu0;
        this.zzdbi = false;
        this.zzdbj = 0;
    }

    public final zzajr zzsh() {
        zzajr zzajr0 = new zzajr(this);
        synchronized(this.lock) {
            this.zza(new zzaju(this, zzajr0), new zzajx(this, zzajr0));
            Preconditions.checkState(this.zzdbj >= 0);
            ++this.zzdbj;
            return zzajr0;
        }
    }

    protected final void zzsi() {
        synchronized(this.lock) {
            Preconditions.checkState(this.zzdbj > 0);
            zzawf.zzee("Releasing 1 reference for JS Engine");
            --this.zzdbj;
            this.zzsk();
        }
    }

    public final void zzsj() {
        synchronized(this.lock) {
            Preconditions.checkState(this.zzdbj >= 0);
            zzawf.zzee("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzdbi = true;
            this.zzsk();
        }
    }

    private final void zzsk() {
        synchronized(this.lock) {
            Preconditions.checkState(this.zzdbj >= 0);
            if(!this.zzdbi || this.zzdbj != 0) {
                zzawf.zzee("There are still references to the engine. Not destroying.");
            }
            else {
                zzawf.zzee("No reference is left (including root). Cleaning up engine.");
                this.zza(new zzajw(this), new zzazz());
            }
        }
    }
}

