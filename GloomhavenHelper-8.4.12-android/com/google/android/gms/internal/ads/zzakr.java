package com.google.android.gms.internal.ads;

final class zzakr implements zzbaa {
    private final zzazy zzcyv;
    private final zzajr zzdbz;

    zzakr(zzakp zzakp0, zzazy zzazy0, zzajr zzajr0) {
        this.zzcyv = zzazy0;
        this.zzdbz = zzajr0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzbaa
    public final void run() {
        zzakd zzakd0 = new zzakd("Unable to obtain a JavascriptEngine.");
        this.zzcyv.setException(zzakd0);
        this.zzdbz.release();
    }
}

