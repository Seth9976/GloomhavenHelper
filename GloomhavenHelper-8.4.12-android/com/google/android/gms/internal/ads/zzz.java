package com.google.android.gms.internal.ads;

public final class zzz {
    public final Object result;
    public final zzd zzbh;
    public final zzae zzbi;
    public boolean zzbj;

    private zzz(zzae zzae0) {
        this.zzbj = false;
        this.result = null;
        this.zzbh = null;
        this.zzbi = zzae0;
    }

    private zzz(Object object0, zzd zzd0) {
        this.zzbj = false;
        this.result = object0;
        this.zzbh = zzd0;
        this.zzbi = null;
    }

    public static zzz zza(Object object0, zzd zzd0) {
        return new zzz(object0, zzd0);
    }

    public static zzz zzd(zzae zzae0) {
        return new zzz(zzae0);
    }
}

