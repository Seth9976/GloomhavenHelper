package com.google.android.gms.internal.ads;

public final class zzbxw implements zzeej {
    private final zzeew zzfjm;
    private final zzeew zzfmx;
    private final zzeew zzfmy;
    private final zzeew zzfmz;
    private final zzeew zzfna;

    public zzbxw(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzfmx = zzeew0;
        this.zzfmy = zzeew1;
        this.zzfmz = zzeew2;
        this.zzfna = zzeew3;
        this.zzfjm = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzbgk zzbgk0 = (zzbgk)this.zzfmx.get();
        zza zzbpt$zza0 = (zza)this.zzfmy.get();
        zzbtl zzbtl0 = (zzbtl)this.zzfmz.get();
        zzbxk zzbxk0 = (zzbxk)this.zzfna.get();
        zzbsf zzbsf0 = (zzbsf)this.zzfjm.get();
        return (zzbng)zzeep.zza(zzbgk0.zzacm().zzc(zzbpt$zza0.zzahz()).zzc(zzbtl0).zzb(zzbxk0).zza(new zzcrh(null)).zza(new zzbmy(zzbsf0)).zzb(new zzblf(null)).zzael().zzaes(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

