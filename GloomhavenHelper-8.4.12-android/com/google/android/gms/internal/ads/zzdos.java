package com.google.android.gms.internal.ads;

final class zzdos extends zzdob {
    private final zzdne zzhdq;
    private final zzdot zzhdr;

    zzdos(zzdot zzdot0, zzdne zzdne0) {
        this.zzhdr = zzdot0;
        super();
        this.zzhdq = (zzdne)zzdlg.checkNotNull(zzdne0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final boolean isDone() {
        return this.zzhdr.isDone();
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final Object zzaur() throws Exception {
        return (zzdof)zzdlg.zza(this.zzhdq.zzapl(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.zzhdq);
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final String zzaus() {
        return this.zzhdq.toString();
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final void zzb(Object object0, Throwable throwable0) {
        if(throwable0 == null) {
            this.zzhdr.setFuture(((zzdof)object0));
            return;
        }
        this.zzhdr.setException(throwable0);
    }
}

