package com.google.android.gms.internal.ads;

public final class zzbou implements zzeej {
    private final zzeew zzenw;
    private final zzeew zzepb;
    private final zzeew zzfea;
    private final zzeew zzfhn;
    private final zzeew zzfje;
    private final zzeew zzfjf;

    private zzbou(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        this.zzfea = zzeew0;
        this.zzfhn = zzeew1;
        this.zzenw = zzeew2;
        this.zzepb = zzeew3;
        this.zzfje = zzeew4;
        this.zzfjf = zzeew5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbne(((zzdeq)this.zzfea.get()), ((zzdei)this.zzfhn.get()), ((zzbqw)this.zzenw.get()), ((zzbrm)this.zzepb.get()), ((zzdcl)this.zzfje.get()), ((zzbqc)this.zzfjf.get()));
    }

    public static zzbou zzb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5) {
        return new zzbou(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5);
    }
}

