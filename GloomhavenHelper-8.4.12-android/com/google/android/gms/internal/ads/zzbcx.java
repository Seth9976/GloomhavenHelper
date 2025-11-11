package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzbcx implements Iterable {
    private final List zzeef;

    public zzbcx() {
        this.zzeef = new ArrayList();
    }

    @Override
    public final Iterator iterator() {
        return this.zzeef.iterator();
    }

    public final void zza(zzbcv zzbcv0) {
        this.zzeef.add(zzbcv0);
    }

    public final void zzb(zzbcv zzbcv0) {
        this.zzeef.remove(zzbcv0);
    }

    public static boolean zzc(zzbbm zzbbm0) {
        zzbcv zzbcv0 = zzbcx.zzd(zzbbm0);
        if(zzbcv0 != null) {
            zzbcv0.zzeee.abort();
            return true;
        }
        return false;
    }

    static zzbcv zzd(zzbbm zzbbm0) {
        for(Object object0: zzq.zzlr()) {
            zzbcv zzbcv0 = (zzbcv)object0;
            if(zzbcv0.zzdza == zzbbm0) {
                return zzbcv0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }
}

