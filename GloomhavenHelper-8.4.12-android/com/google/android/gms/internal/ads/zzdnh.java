package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdnh extends zzdna {
    private zzdnj zzhcp;

    zzdnh(zzdlq zzdlq0, boolean z, Executor executor0, Callable callable0) {
        super(zzdlq0, z, false);
        this.zzhcp = new zzdnk(this, callable0, executor0);
        this.zzaum();
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final void interruptTask() {
        zzdnj zzdnj0 = this.zzhcp;
        if(zzdnj0 != null) {
            zzdnj0.interruptTask();
        }
    }

    static zzdnj zza(zzdnh zzdnh0, zzdnj zzdnj0) {
        zzdnh0.zzhcp = null;
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdna
    final void zza(zza zzdna$zza0) {
        super.zza(zzdna$zza0);
        if(zzdna$zza0 == zza.zzhch) {
            this.zzhcp = null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdna
    final void zzaun() {
        zzdnj zzdnj0 = this.zzhcp;
        if(zzdnj0 != null) {
            zzdnj0.execute();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdna
    final void zzb(int v, @NullableDecl Object object0) {
    }
}

