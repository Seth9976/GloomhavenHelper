package com.google.android.gms.internal.ads;

import android.view.Surface;

public final class zzpf {
    private final zzdkp zzadx;
    private final zzpg zzbjo;

    public zzpf(zzdkp zzdkp0, zzpg zzpg0) {
        this.zzadx = zzpg0 == null ? null : ((zzdkp)zzob.checkNotNull(zzdkp0));
        this.zzbjo = zzpg0;
    }

    public final void zza(int v, int v1, int v2, float f) {
        if(this.zzbjo != null) {
            zzpm zzpm0 = new zzpm(this, v, v1, v2, f);
            this.zzadx.post(zzpm0);
        }
    }

    public final void zza(Surface surface0) {
        if(this.zzbjo != null) {
            zzpl zzpl0 = new zzpl(this, surface0);
            this.zzadx.post(zzpl0);
        }
    }

    public final void zza(zzis zzis0) {
        if(this.zzbjo != null) {
            zzpi zzpi0 = new zzpi(this, zzis0);
            this.zzadx.post(zzpi0);
        }
    }

    public final void zza(String s, long v, long v1) {
        if(this.zzbjo != null) {
            zzph zzph0 = new zzph(this, s, v, v1);
            this.zzadx.post(zzph0);
        }
    }

    public final void zzb(zzgz zzgz0) {
        if(this.zzbjo != null) {
            zzpk zzpk0 = new zzpk(this, zzgz0);
            this.zzadx.post(zzpk0);
        }
    }

    public final void zzb(zzis zzis0) {
        if(this.zzbjo != null) {
            zzpn zzpn0 = new zzpn(this, zzis0);
            this.zzadx.post(zzpn0);
        }
    }

    public final void zze(int v, long v1) {
        if(this.zzbjo != null) {
            zzpj zzpj0 = new zzpj(this, v, v1);
            this.zzadx.post(zzpj0);
        }
    }
}

