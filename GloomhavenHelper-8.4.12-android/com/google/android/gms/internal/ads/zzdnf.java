package com.google.android.gms.internal.ads;

import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class zzdnf extends zzdna {
    private List zzhco;

    zzdnf(zzdlq zzdlq0, boolean z) {
        super(zzdlq0, true, true);
        zzdlr zzdlr0 = zzdlq0.isEmpty() ? zzdlr.zzauc() : zzdlz.zzdz(zzdlq0.size());
        this.zzhco = zzdlr0;
        for(int v = 0; v < zzdlq0.size(); ++v) {
            this.zzhco.add(null);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdna
    final void zza(zza zzdna$zza0) {
        super.zza(zzdna$zza0);
        this.zzhco = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdna
    final void zzaun() {
        List list0 = this.zzhco;
        if(list0 != null) {
            this.set(this.zzi(list0));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdna
    final void zzb(int v, @NullableDecl Object object0) {
        List list0 = this.zzhco;
        if(list0 != null) {
            list0.set(v, zzdla.zzac(object0));
        }
    }

    abstract Object zzi(List arg1);
}

