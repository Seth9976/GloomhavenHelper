package com.google.android.gms.internal.ads;

final class zzbhy implements zzbws {
    private zzbtl zzemy;
    private zzbpt zzemz;
    private zzdeq zzenb;
    private zzdcu zzenc;
    private zzdby zzend;
    private final zzbhf zzene;
    private zzcrh zzeyi;

    private zzbhy(zzbhf zzbhf0) {
        this.zzene = zzbhf0;
        super();
    }

    zzbhy(zzbhf zzbhf0, zzbhi zzbhi0) {
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
        return this.zzaev();
    }

    @Override  // com.google.android.gms.internal.ads.zzbws
    public final zzbwt zzaev() {
        zzeep.zza(this.zzemy, zzbtl.class);
        zzeep.zza(this.zzemz, zzbpt.class);
        zzeep.zza(this.zzeyi, zzcrh.class);
        zzbog zzbog0 = new zzbog();
        zzdfh zzdfh0 = new zzdfh();
        zzbph zzbph0 = new zzbph();
        zzchc zzchc0 = new zzchc();
        zzbtl zzbtl0 = this.zzemy;
        zzbpt zzbpt0 = this.zzemz;
        zzdfp zzdfp0 = new zzdfp();
        return new zzbhx(this.zzene, zzbog0, zzdfh0, zzbph0, zzchc0, zzbtl0, zzbpt0, zzdfp0, this.zzeyi, this.zzenb, this.zzenc, this.zzend, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzbws
    public final zzbws zzb(zzcrh zzcrh0) {
        this.zzeyi = (zzcrh)zzeep.checkNotNull(zzcrh0);
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbws
    public final zzbws zzd(zzbpt zzbpt0) {
        this.zzemz = (zzbpt)zzeep.checkNotNull(zzbpt0);
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzbws
    public final zzbws zzd(zzbtl zzbtl0) {
        this.zzemy = (zzbtl)zzeep.checkNotNull(zzbtl0);
        return this;
    }
}

