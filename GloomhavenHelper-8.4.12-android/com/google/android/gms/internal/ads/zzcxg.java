package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class zzcxg implements zzcye {
    private final ScheduledExecutorService zzfib;
    private final zzdeu zzfir;
    private final zzdoe zzfrv;
    private final zzcro zzgec;
    private String zzghg;
    private final zzcrq zzgjt;
    private final Context zzur;

    public zzcxg(zzdoe zzdoe0, ScheduledExecutorService scheduledExecutorService0, String s, zzcrq zzcrq0, Context context0, zzdeu zzdeu0, zzcro zzcro0) {
        this.zzfrv = zzdoe0;
        this.zzfib = scheduledExecutorService0;
        this.zzghg = s;
        this.zzgjt = zzcrq0;
        this.zzur = context0;
        this.zzfir = zzdeu0;
        this.zzgec = zzcro0;
    }

    final zzdof zza(String s, List list0, Bundle bundle0) throws Exception {
        zzdof zzdof0 = new zzazy();
        this.zzgec.zzgj(s);
        zzanq zzanq0 = this.zzgec.zzgk(s);
        if(zzanq0 == null) {
            throw new NullPointerException();
        }
        zzcrw zzcrw0 = new zzcrw(s, zzanq0, ((zzazy)zzdof0));
        zzanq0.zza(ObjectWrapper.wrap(this.zzur), this.zzghg, bundle0, ((Bundle)list0.get(0)), this.zzfir.zzblv, zzcrw0);
        return zzdof0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzclg)).booleanValue() ? zzdnt.zza(() -> {
            Map map0 = this.zzgjt.zzr(this.zzghg, this.zzfir.zzgqr);
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: map0.entrySet()) {
                String s = (String)((Map.Entry)object0).getKey();
                zzdno zzdno0 = zzdno.zzg(zzdnt.zza(new zzcxi(this, s, ((List)((Map.Entry)object0).getValue()), (this.zzfir.zzgqq.zzcct == null ? null : this.zzfir.zzgqq.zzcct.getBundle(s))), this.zzfrv)).zza(((long)(((Long)zzvh.zzpd().zzd(zzzx.zzclf)))), TimeUnit.MILLISECONDS, this.zzfib);
                zzcxh zzcxh0 = new zzcxh(s);
                arrayList0.add(zzdno0.zza(Throwable.class, zzcxh0, this.zzfrv));
            }
            return zzdnt.zzi(arrayList0).zza(new zzcxk(arrayList0), this.zzfrv);
        }, this.zzfrv) : zzdnt.zzaj(null);
    }

    // 检测为 Lambda 实现
    final zzdof zzapm() [...]
}

