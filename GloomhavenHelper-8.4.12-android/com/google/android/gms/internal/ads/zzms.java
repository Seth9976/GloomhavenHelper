package com.google.android.gms.internal.ads;

public final class zzms extends zzhj {
    private final boolean zzagt;
    private final boolean zzagu;
    private static final Object zzbdg;
    private final long zzbdh;
    private final long zzbdi;
    private final long zzbdj;
    private final long zzbdk;

    static {
        zzms.zzbdg = new Object();
    }

    private zzms(long v, long v1, long v2, long v3, boolean z, boolean z1) {
        this.zzbdh = v;
        this.zzbdi = v1;
        this.zzbdj = 0L;
        this.zzbdk = 0L;
        this.zzagt = z;
        this.zzagu = false;
    }

    public zzms(long v, boolean z) {
        this(v, v, 0L, 0L, z, false);
    }

    @Override  // com.google.android.gms.internal.ads.zzhj
    public final zzhk zza(int v, zzhk zzhk0, boolean z, long v1) {
        zzob.zzc(v, 0, 1);
        zzhk0.zzagq = null;
        zzhk0.zzagr = 0x8000000000000001L;
        zzhk0.zzags = 0x8000000000000001L;
        zzhk0.zzagt = this.zzagt;
        zzhk0.zzagu = false;
        zzhk0.zzagx = 0L;
        zzhk0.zzagy = this.zzbdi;
        zzhk0.zzagv = 0;
        zzhk0.zzagw = 0;
        zzhk0.zzagz = 0L;
        return zzhk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzhj
    public final zzhl zza(int v, zzhl zzhl0, boolean z) {
        zzob.zzc(v, 0, 1);
        return z ? zzhl0.zza(zzms.zzbdg, zzms.zzbdg, 0, this.zzbdh, 0L, false) : zzhl0.zza(null, null, 0, this.zzbdh, 0L, false);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzhj
    public final int zzc(Object object0) {
        return zzms.zzbdg.equals(object0) ? 0 : -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzhj
    public final int zzfa() {
        return 1;
    }

    @Override  // com.google.android.gms.internal.ads.zzhj
    public final int zzfb() {
        return 1;
    }
}

