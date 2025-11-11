package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzcib extends zzaha {
    private final zzcho zzfxe;
    private final Object zzfxh;
    private final String zzfxi;
    private final long zzfxj;
    private final zzazy zzfxk;

    zzcib(zzcho zzcho0, Object object0, String s, long v, zzazy zzazy0) {
        this.zzfxe = zzcho0;
        this.zzfxh = object0;
        this.zzfxi = s;
        this.zzfxj = v;
        this.zzfxk = zzazy0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzahb
    public final void onInitializationFailed(String s) {
        synchronized(this.zzfxh) {
            long v1 = zzq.zzlc().elapsedRealtime();
            this.zzfxe.zza(this.zzfxi, false, s, ((int)(v1 - this.zzfxj)));
            this.zzfxe.zzfwt.zzq(this.zzfxi, "error");
            this.zzfxk.set(Boolean.FALSE);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzahb
    public final void onInitializationSucceeded() {
        synchronized(this.zzfxh) {
            long v1 = zzq.zzlc().elapsedRealtime();
            this.zzfxe.zza(this.zzfxi, true, "", ((int)(v1 - this.zzfxj)));
            this.zzfxe.zzfwt.zzgf(this.zzfxi);
            this.zzfxk.set(Boolean.TRUE);
        }
    }
}

