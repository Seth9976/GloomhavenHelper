package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zztt extends zzect {
    public String zzcas;
    private zzo zzcat;
    private Integer zzcau;
    public zztu zzcav;
    private Integer zzcaw;
    private zztf zzcax;
    private zztf zzcay;
    private zztf zzcaz;

    public zztt() {
        this.zzcas = null;
        this.zzcat = null;
        this.zzcau = null;
        this.zzcav = null;
        this.zzcaw = null;
        this.zzcax = null;
        this.zzcay = null;
        this.zzcaz = null;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        String s = this.zzcas;
        if(s != null) {
            zzecr0.zzf(1, s);
        }
        zztu zztu0 = this.zzcav;
        if(zztu0 != null) {
            zzecr0.zza(4, zztu0);
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v = super.zzon();
        String s = this.zzcas;
        if(s != null) {
            v += zzecr.zzg(1, s);
        }
        return this.zzcav == null ? v : v + zzecr.zzb(4, this.zzcav);
    }
}

