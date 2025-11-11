package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

public final class zzbnv {
    private final zzdeq zzenb;
    private final zzdei zzfdq;
    private final String zzfik;

    public zzbnv(zzdeq zzdeq0, zzdei zzdei0, @Nullable String s) {
        this.zzenb = zzdeq0;
        this.zzfdq = zzdei0;
        if(s == null) {
            s = "com.google.ads.mediation.admob.AdMobAdapter";
        }
        this.zzfik = s;
    }

    public final zzdeq zzahm() {
        return this.zzenb;
    }

    public final zzdei zzahn() {
        return this.zzfdq;
    }

    public final String zzaho() {
        return this.zzfik;
    }
}

