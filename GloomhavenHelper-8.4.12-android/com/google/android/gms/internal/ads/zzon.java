package com.google.android.gms.internal.ads;

import android.os.SystemClock;

public final class zzon implements zzof {
    private boolean started;
    private zzhf zzadp;
    private long zzbha;
    private long zzbhb;

    public zzon() {
        this.zzadp = zzhf.zzagj;
    }

    public final void start() {
        if(!this.started) {
            this.zzbhb = SystemClock.elapsedRealtime();
            this.started = true;
        }
    }

    public final void stop() {
        if(this.started) {
            this.zzel(this.zzfx());
            this.started = false;
        }
    }

    public final void zza(zzof zzof0) {
        this.zzel(zzof0.zzfx());
        this.zzadp = zzof0.zzfn();
    }

    @Override  // com.google.android.gms.internal.ads.zzof
    public final zzhf zzb(zzhf zzhf0) {
        if(this.started) {
            this.zzel(this.zzfx());
        }
        this.zzadp = zzhf0;
        return zzhf0;
    }

    public final void zzel(long v) {
        this.zzbha = v;
        if(this.started) {
            this.zzbhb = SystemClock.elapsedRealtime();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzof
    public final zzhf zzfn() {
        return this.zzadp;
    }

    @Override  // com.google.android.gms.internal.ads.zzof
    public final long zzfx() {
        long v = this.zzbha;
        if(this.started) {
            long v1 = SystemClock.elapsedRealtime() - this.zzbhb;
            return this.zzadp.zzagk == 1.0f ? v + zzgl.zzdp(v1) : v + this.zzadp.zzdu(v1);
        }
        return v;
    }
}

