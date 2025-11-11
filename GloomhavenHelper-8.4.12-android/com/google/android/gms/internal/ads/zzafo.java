package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zze;
import java.util.Map;

final class zzafo implements zzafz {
    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        if(((zzbdv)object0).zzaat() != null) {
            ((zzbdv)object0).zzaat().zzmr();
        }
        zze zze0 = ((zzbdv)object0).zzaab();
        if(zze0 != null) {
            zze0.close();
            return;
        }
        zze zze1 = ((zzbdv)object0).zzaac();
        if(zze1 != null) {
            zze1.close();
            return;
        }
        zzawf.zzfa("A GMSG tried to close something that wasn\'t an overlay.");
    }
}

