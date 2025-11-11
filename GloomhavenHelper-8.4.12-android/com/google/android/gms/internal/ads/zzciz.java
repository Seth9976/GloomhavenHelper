package com.google.android.gms.internal.ads;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

public final class zzciz extends zzcix {
    private final ScheduledExecutorService zzfib;
    private final zzdeu zzfir;
    private final zzdoe zzfrv;
    private final zzcic zzfxz;
    private final zzclk zzfya;
    private static final Pattern zzfyb;

    static {
        zzciz.zzfyb = Pattern.compile("Received error HTTP response code: (.*)");
    }

    zzciz(zzbsm zzbsm0, zzdeu zzdeu0, zzcic zzcic0, zzdoe zzdoe0, ScheduledExecutorService scheduledExecutorService0, zzclk zzclk0) {
        super(zzbsm0);
        this.zzfir = zzdeu0;
        this.zzfxz = zzcic0;
        this.zzfrv = zzdoe0;
        this.zzfib = scheduledExecutorService0;
        this.zzfya = zzclk0;
    }

    static zzclk zza(zzciz zzciz0) {
        return zzciz0.zzfya;
    }

    static Pattern zzanq() {
        return zzciz.zzfyb;
    }

    @Override  // com.google.android.gms.internal.ads.zzcix
    public final zzdof zzb(zzaqx zzaqx0) throws zzcid {
        zzdof zzdof0 = zzdnt.zzb(this.zzfxz.zze(zzaqx0), (InputStream inputStream0) -> zzdnt.zzaj(new zzdeq(new zzdel(this.zzfir), zzdeo.zza(new InputStreamReader(inputStream0)))), this.zzfrv);
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcpt)).booleanValue()) {
            zzdof0 = zzdnt.zzb(zzdnt.zza(zzdof0, ((long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcpu)))))), TimeUnit.SECONDS, this.zzfib), TimeoutException.class, zzcjb.zzblf, zzazq.zzdxp);
        }
        zzdnt.zza(zzdof0, new zzcja(this), zzazq.zzdxp);
        return zzdof0;
    }

    // 检测为 Lambda 实现
    final zzdof zzd(InputStream inputStream0) throws Exception [...]
}

