package com.google.android.gms.internal.ads;

public final class zzczr implements zzcye {
    private String packageName;
    private zzdoe zzfrv;
    private zzavj zzgls;

    public zzczr(zzavj zzavj0, zzdoe zzdoe0, String s) {
        this.zzgls = zzavj0;
        this.zzfrv = zzdoe0;
        this.packageName = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        new zzazy();
        zzdof zzdof0 = zzdnt.zzaj(null);
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcqh)).booleanValue()) {
            zzdof0 = this.zzgls.zzec(this.packageName);
        }
        zzdof zzdof1 = this.zzgls.zzed(this.packageName);
        return zzdnt.zzb(new zzdof[]{zzdof0, zzdof1}).zza(new zzczu(zzdof0, zzdof1), zzazq.zzdxk);
    }
}

