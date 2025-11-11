package com.google.android.gms.internal.ads;

public final class zzbhv {
    private zzbgn zzelf;
    private zzbih zzfab;
    private zzdiq zzfac;
    private zzbio zzfad;
    private zzdff zzfae;

    private zzbhv() {
    }

    zzbhv(zzbhi zzbhi0) {
    }

    public final zzbhv zza(zzbih zzbih0) {
        this.zzfab = (zzbih)zzeep.checkNotNull(zzbih0);
        return this;
    }

    public final zzbgk zzaet() {
        zzeep.zza(this.zzelf, zzbgn.class);
        zzeep.zza(this.zzfab, zzbih.class);
        if(this.zzfac == null) {
            this.zzfac = new zzdiq();
        }
        if(this.zzfad == null) {
            this.zzfad = new zzbio();
        }
        if(this.zzfae == null) {
            this.zzfae = new zzdff();
        }
        return new zzbhf(this.zzelf, this.zzfab, this.zzfac, this.zzfad, this.zzfae, null);
    }

    public final zzbhv zzc(zzbgn zzbgn0) {
        this.zzelf = (zzbgn)zzeep.checkNotNull(zzbgn0);
        return this;
    }
}

