package com.google.android.gms.internal.ads;

final class zzaun implements zzauv {
    static final zzauv zzdqw;

    static {
        zzaun.zzdqw = new zzaun();
    }

    @Override  // com.google.android.gms.internal.ads.zzauv
    public final Object zzb(zzbgd zzbgd0) {
        String s = zzbgd0.getCurrentScreenName();
        if(s != null) {
            return s;
        }
        String s1 = zzbgd0.getCurrentScreenClass();
        return s1 == null ? "" : s1;
    }
}

