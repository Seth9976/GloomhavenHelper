package com.google.android.gms.internal.ads;

final class zzbib implements zzcdt {
    private zzbtl zzemy;
    private zzbpt zzemz;
    private zzdeq zzenb;
    private zzdcu zzenc;
    private zzdby zzend;
    private final zzbhf zzene;

    private zzbib(zzbhf zzbhf0) {
        this.zzene = zzbhf0;
        super();
    }

    zzbib(zzbhf zzbhf0, zzbhi zzbhi0) {
        this(zzbhf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbpq
    public final zzbpq zza(zzdby zzdby0) {
        this.zzend = zzdby0;
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbpq
    public final zzbpq zza(zzdcu zzdcu0) {
        this.zzenc = zzdcu0;
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbpq
    public final zzbpq zza(zzdeq zzdeq0) {
        this.zzenb = zzdeq0;
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbpq
    public final Object zzadi() {
        return this.zzafa();
    }

    @Override  // com.google.android.gms.internal.ads.zzcdt
    public final zzcdq zzafa() {
        zzeep.zza(this.zzemy, zzbtl.class);
        zzeep.zza(this.zzemz, zzbpt.class);
        zzbog zzbog0 = new zzbog();
        zzdfh zzdfh0 = new zzdfh();
        zzbph zzbph0 = new zzbph();
        zzchc zzchc0 = new zzchc();
        zzbtl zzbtl0 = this.zzemy;
        zzbpt zzbpt0 = this.zzemz;
        zzdfp zzdfp0 = new zzdfp();
        return new zzbie(this.zzene, zzbog0, zzdfh0, zzbph0, zzchc0, zzbtl0, zzbpt0, zzdfp0, this.zzenb, this.zzenc, this.zzend, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzcdt
    public final zzcdt zze(zzbpt zzbpt0) {
        this.zzemz = (zzbpt)zzeep.checkNotNull(zzbpt0);
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzcdt
    public final zzcdt zze(zzbtl zzbtl0) {
        this.zzemy = (zzbtl)zzeep.checkNotNull(zzbtl0);
        return this;
    }
}

