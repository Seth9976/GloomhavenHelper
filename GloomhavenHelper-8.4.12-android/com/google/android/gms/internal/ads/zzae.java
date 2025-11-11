package com.google.android.gms.internal.ads;

public class zzae extends Exception {
    private long zzad;
    private final zzo zzbk;

    public zzae() {
        this.zzbk = null;
    }

    public zzae(zzo zzo0) {
        this.zzbk = zzo0;
    }

    public zzae(String s) {
        super(s);
        this.zzbk = null;
    }

    public zzae(Throwable throwable0) {
        super(throwable0);
        this.zzbk = null;
    }

    final void zza(long v) {
        this.zzad = v;
    }
}

