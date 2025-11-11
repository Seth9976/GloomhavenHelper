package com.google.android.gms.internal.ads;

final class zzchv implements Runnable {
    private final Object zzdcl;
    private final String zzfux;
    private final zzcho zzfwv;
    private final zzazy zzfwy;
    private final long zzfwz;

    zzchv(zzcho zzcho0, Object object0, zzazy zzazy0, String s, long v) {
        this.zzfwv = zzcho0;
        this.zzdcl = object0;
        this.zzfwy = zzazy0;
        this.zzfux = s;
        this.zzfwz = v;
    }

    @Override
    public final void run() {
        this.zzfwv.zza(this.zzdcl, this.zzfwy, this.zzfux, this.zzfwz);
    }
}

