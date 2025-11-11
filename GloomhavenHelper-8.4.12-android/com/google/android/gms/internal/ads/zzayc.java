package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzayc extends zzax {
    private final byte[] zzdvo;
    private final Map zzdvp;
    private final zzazb zzdvq;

    zzayc(zzaxx zzaxx0, int v, String s, zzab zzab0, zzy zzy0, byte[] arr_b, Map map0, zzazb zzazb0) {
        this.zzdvo = arr_b;
        this.zzdvp = map0;
        this.zzdvq = zzazb0;
        super(v, s, zzab0, zzy0);
    }

    @Override  // com.google.android.gms.internal.ads.zzq
    public final Map getHeaders() throws zzb {
        return this.zzdvp == null ? super.getHeaders() : this.zzdvp;
    }

    @Override  // com.google.android.gms.internal.ads.zzax
    protected final void zza(Object object0) {
        this.zzh(((String)object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzq
    public final byte[] zzg() throws zzb {
        return this.zzdvo == null ? super.zzg() : this.zzdvo;
    }

    @Override  // com.google.android.gms.internal.ads.zzax
    protected final void zzh(String s) {
        this.zzdvq.zzev(s);
        super.zzh(s);
    }
}

