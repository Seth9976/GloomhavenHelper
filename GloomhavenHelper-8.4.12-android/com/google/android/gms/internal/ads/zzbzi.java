package com.google.android.gms.internal.ads;

import androidx.collection.SimpleArrayMap;

public final class zzbzi {
    zzads zzfps;
    zzadr zzfpt;
    zzaeg zzfpu;
    zzaef zzfpv;
    zzaht zzfpw;
    final SimpleArrayMap zzfpx;
    final SimpleArrayMap zzfpy;

    public zzbzi() {
        this.zzfpx = new SimpleArrayMap();
        this.zzfpy = new SimpleArrayMap();
    }

    public final zzbzi zza(zzaef zzaef0) {
        this.zzfpv = zzaef0;
        return this;
    }

    public final zzbzg zzala() {
        return new zzbzg(this, null);
    }

    public final zzbzi zzb(zzadr zzadr0) {
        this.zzfpt = zzadr0;
        return this;
    }

    public final zzbzi zzb(zzads zzads0) {
        this.zzfps = zzads0;
        return this;
    }

    public final zzbzi zzb(zzaeg zzaeg0) {
        this.zzfpu = zzaeg0;
        return this;
    }

    public final zzbzi zzb(zzaht zzaht0) {
        this.zzfpw = zzaht0;
        return this;
    }

    public final zzbzi zzb(String s, zzady zzady0, zzadx zzadx0) {
        this.zzfpx.put(s, zzady0);
        this.zzfpy.put(s, zzadx0);
        return this;
    }
}

