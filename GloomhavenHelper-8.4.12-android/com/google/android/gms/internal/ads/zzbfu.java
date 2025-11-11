package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzi;

final class zzbfu implements zzdlk {
    private final Context zzcgz;
    private final String zzdcq;
    private final zzbfl zzegj;
    private final boolean zzegk;
    private final boolean zzegl;
    private final zzdq zzegm;
    private final zzazo zzegn;
    private final zzaak zzego;
    private final zzi zzegp;
    private final zza zzegq;
    private final zzsn zzegr;
    private final zzrp zzegs;
    private final boolean zzegt;

    zzbfu(Context context0, zzbfl zzbfl0, String s, boolean z, boolean z1, zzdq zzdq0, zzazo zzazo0, zzaak zzaak0, zzi zzi0, zza zza0, zzsn zzsn0, zzrp zzrp0, boolean z2) {
        this.zzcgz = context0;
        this.zzegj = zzbfl0;
        this.zzdcq = s;
        this.zzegk = z;
        this.zzegl = z1;
        this.zzegm = zzdq0;
        this.zzegn = zzazo0;
        this.zzego = zzaak0;
        this.zzegp = zzi0;
        this.zzegq = zza0;
        this.zzegr = zzsn0;
        this.zzegs = zzrp0;
        this.zzegt = z2;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlk
    public final Object get() {
        zzbfo zzbfo0 = new zzbfo();
        zzbft zzbft0 = new zzbft(new zzbfm(this.zzcgz), zzbfo0, this.zzegj, this.zzdcq, this.zzegk, this.zzegl, this.zzegm, this.zzegn, this.zzego, this.zzegp, this.zzegq, this.zzegr, this.zzegs, this.zzegt);
        zzbeh zzbeh0 = new zzbeh(zzbft0);
        zzbft0.setWebChromeClient(new zzbdn(zzbeh0));
        zzbfo0.zza(zzbeh0, this.zzegl);
        return zzbeh0;
    }
}

