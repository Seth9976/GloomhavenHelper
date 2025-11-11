package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.Map;

final class zzbxi implements zzbng {
    private final zzbzg zzfge;
    private final Map zzfhz;
    private final Map zzfmo;
    private final Map zzfmp;
    private final zzeew zzfmq;

    zzbxi(Map map0, Map map1, Map map2, zzeew zzeew0, zzbzg zzbzg0) {
        this.zzfhz = map0;
        this.zzfmo = map1;
        this.zzfmp = map2;
        this.zzfmq = zzeew0;
        this.zzfge = zzbzg0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbng
    @Nullable
    public final zzcly zzd(int v, String s) {
        zzcly zzcly0 = (zzcly)this.zzfhz.get(s);
        if(zzcly0 != null) {
            return zzcly0;
        }
        switch(v) {
            case 1: {
                if(this.zzfge.zzakw() == null) {
                    return null;
                }
                zzcly zzcly1 = ((zzbng)this.zzfmq.get()).zzd(1, s);
                return zzcly1 == null ? null : zzbnl.zza(zzcly1);
            }
            case 4: {
                zzcoe zzcoe0 = (zzcoe)this.zzfmp.get(s);
                if(zzcoe0 != null) {
                    return zzbnl.zza(zzcoe0);
                }
                zzcly zzcly2 = (zzcly)this.zzfmo.get(s);
                return zzcly2 == null ? null : zzbnl.zza(zzcly2);
            }
            default: {
                return null;
            }
        }
    }
}

