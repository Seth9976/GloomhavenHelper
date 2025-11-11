package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;

final class zzcja implements zzdnu {
    private final zzciz zzfyc;

    zzcja(zzciz zzciz0) {
        this.zzfyc = zzciz0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue()) {
            zzciz.zza(this.zzfyc).zzdk(((zzdeq)object0).zzgqm.zzgqj.responseCode);
            zzciz.zza(this.zzfyc).zzeq(((zzdeq)object0).zzgqm.zzgqj.zzfzs);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue()) {
            Matcher matcher0 = zzciz.zzanq().matcher(throwable0.getMessage());
            if(matcher0.matches()) {
                String s = matcher0.group(1);
                zzciz.zza(this.zzfyc).zzdk(Integer.parseInt(s));
            }
        }
    }
}

