package com.google.android.gms.internal.ads;

final class zzajn implements zzbac {
    private final zzaja zzdaz;
    private final zzajv zzdbb;

    zzajn(zzaja zzaja0, zzajv zzajv0) {
        this.zzdaz = zzaja0;
        this.zzdbb = zzajv0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzbac
    public final void zzh(Object object0) {
        zzair zzair0 = (zzair)object0;
        synchronized(this.zzdaz.lock) {
            this.zzdaz.status = 0;
            if(this.zzdaz.zzdao != null && this.zzdbb != this.zzdaz.zzdao) {
                zzawf.zzee("New JS engine is loaded, marking previous one as destroyable.");
                this.zzdaz.zzdao.zzsj();
            }
            this.zzdaz.zzdao = this.zzdbb;
        }
    }
}

