package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONObject;

final class zzcba implements Callable {
    private final zzdof zzfig;
    private final zzdof zzfjy;
    private final zzcay zzfry;
    private final zzdof zzfrz;
    private final zzdof zzfsa;
    private final zzdof zzfsb;
    private final JSONObject zzfsc;
    private final zzdof zzfsd;
    private final zzdof zzfse;
    private final zzdof zzfsf;

    zzcba(zzcay zzcay0, zzdof zzdof0, zzdof zzdof1, zzdof zzdof2, zzdof zzdof3, zzdof zzdof4, JSONObject jSONObject0, zzdof zzdof5, zzdof zzdof6, zzdof zzdof7) {
        this.zzfry = zzcay0;
        this.zzfjy = zzdof0;
        this.zzfig = zzdof1;
        this.zzfrz = zzdof2;
        this.zzfsa = zzdof3;
        this.zzfsb = zzdof4;
        this.zzfsc = jSONObject0;
        this.zzfsd = zzdof5;
        this.zzfse = zzdof6;
        this.zzfsf = zzdof7;
    }

    @Override
    public final Object call() {
        zzdof zzdof0 = this.zzfse;
        zzdof zzdof1 = this.zzfsf;
        zzbyz zzbyz0 = (zzbyz)this.zzfjy.get();
        zzbyz0.setImages(((List)this.zzfig.get()));
        zzbyz0.zza(((zzacr)this.zzfrz.get()));
        zzbyz0.zzb(((zzacr)this.zzfsa.get()));
        zzbyz0.zza(((zzacj)this.zzfsb.get()));
        zzbyz0.zzg(zzcbc.zzj(this.zzfsc));
        zzbyz0.zza(zzcbc.zzk(this.zzfsc));
        zzbdv zzbdv0 = (zzbdv)this.zzfsd.get();
        if(zzbdv0 != null) {
            zzbyz0.zzi(zzbdv0);
            zzbyz0.zzab(zzbdv0.getView());
            zzbyz0.zzb(zzbdv0.zzyq());
        }
        zzbdv zzbdv1 = (zzbdv)zzdof0.get();
        if(zzbdv1 != null) {
            zzbyz0.zzj(zzbdv1);
        }
        for(Object object0: ((List)zzdof1.get())) {
            zzcbr zzcbr0 = (zzcbr)object0;
            switch(zzcbr0.type) {
                case 1: {
                    zzbyz0.zzn(zzcbr0.zzcc, zzcbr0.zzfsu);
                    break;
                }
                case 2: {
                    zzbyz0.zza(zzcbr0.zzcc, zzcbr0.zzfsv);
                }
            }
        }
        return zzbyz0;
    }
}

