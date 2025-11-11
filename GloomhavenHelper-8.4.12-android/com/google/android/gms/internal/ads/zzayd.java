package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzayd extends zzq {
    private final Map zzab;
    private final zzazy zzdvr;
    private final zzazb zzdvs;

    public zzayd(String s, zzazy zzazy0) {
        this(s, null, zzazy0);
    }

    private zzayd(String s, Map map0, zzazy zzazy0) {
        super(0, s, new zzayg(zzazy0));
        this.zzab = null;
        this.zzdvr = zzazy0;
        this.zzdvs = new zzazb();
        this.zzdvs.zza(s, "GET", null, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzq
    protected final zzz zza(zzo zzo0) {
        return zzz.zza(zzo0, zzas.zzb(zzo0));
    }

    @Override  // com.google.android.gms.internal.ads.zzq
    protected final void zza(Object object0) {
        this.zzdvs.zza(((zzo)object0).zzab, ((zzo)object0).statusCode);
        zzazb zzazb0 = this.zzdvs;
        byte[] arr_b = ((zzo)object0).data;
        if(zzazb.isEnabled() && arr_b != null) {
            zzazb0.zzi(arr_b);
        }
        this.zzdvr.set(((zzo)object0));
    }
}

