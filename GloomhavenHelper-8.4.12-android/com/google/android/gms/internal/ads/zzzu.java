package com.google.android.gms.internal.ads;

final class zzzu implements zzabx {
    private final zzzt zzcgy;

    zzzu(zzzt zzzt0) {
        this.zzcgy = zzzt0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzabx
    public final String get(String s, String s1) {
        return this.zzcgy.zzcgv.getString(s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzabx
    public final Long getLong(String s, long v) {
        try {
            return this.zzcgy.zzcgv.getLong(s, v);
        }
        catch(ClassCastException unused_ex) {
            return (long)this.zzcgy.zzcgv.getInt(s, ((int)v));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzabx
    public final Double zza(String s, double f) {
        return (double)this.zzcgy.zzcgv.getFloat(s, ((float)f));
    }

    @Override  // com.google.android.gms.internal.ads.zzabx
    public final Boolean zze(String s, boolean z) {
        return Boolean.valueOf(this.zzcgy.zzcgv.getBoolean(s, z));
    }
}

