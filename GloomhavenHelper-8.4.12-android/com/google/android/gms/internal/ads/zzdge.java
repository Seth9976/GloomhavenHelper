package com.google.android.gms.internal.ads;

final class zzdge {
    private final zzdgd zzgsd;
    private int zzgse;
    private int zzgsf;
    private int zzgsg;
    private int zzgsh;
    private int zzgsi;

    zzdge() {
        this.zzgsd = new zzdgd();
    }

    public final void zzarq() {
        ++this.zzgsg;
    }

    public final void zzarr() {
        ++this.zzgsh;
    }

    public final void zzars() {
        ++this.zzgse;
        this.zzgsd.zzgsb = true;
    }

    public final void zzart() {
        ++this.zzgsf;
        this.zzgsd.zzgsc = true;
    }

    public final void zzaru() {
        ++this.zzgsi;
    }

    public final zzdgd zzarv() {
        zzdgd zzdgd0 = (zzdgd)this.zzgsd.clone();
        this.zzgsd.zzgsb = false;
        this.zzgsd.zzgsc = false;
        return zzdgd0;
    }

    public final String zzarw() [...] // 潜在的解密器
}

