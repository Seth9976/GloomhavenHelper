package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzq;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.Executor;
import org.json.JSONObject;

public final class zzckh extends zzaqn {
    private final Executor zzfeo;
    private final zzaro zzfza;
    private final zzarl zzfzb;
    private final zzbjh zzfzc;
    private final HashMap zzfzd;
    private final Context zzur;

    public zzckh(Context context0, Executor executor0, zzaro zzaro0, zzbjh zzbjh0, zzarl zzarl0, HashMap hashMap0) {
        zzzx.initialize(context0);
        this.zzur = context0;
        this.zzfeo = executor0;
        this.zzfza = zzaro0;
        this.zzfzb = zzarl0;
        this.zzfzc = zzbjh0;
        this.zzfzd = hashMap0;
    }

    private static zzdof zza(zzaqx zzaqx0, zzdif zzdif0, zzcyw zzcyw0) {
        zzckl zzckl0 = new zzckl(zzcyw0);
        zzdof zzdof0 = zzdnt.zzaj(zzaqx0.zzdmz);
        return zzdif0.zza(zzdig.zzgvi, zzdof0).zza(zzckl0).zzb(zzckk.zzfxq).zzata();
    }

    private static zzdof zza(zzdof zzdof0, zzdif zzdif0, zzakk zzakk0) {
        zzakc zzakc0 = zzakk0.zza("AFMA_getAdDictionary", zzakj.zzdbu, zzckn.zzdbt);
        return zzdif0.zza(zzdig.zzgvk, zzdof0).zza(zzakc0).zzata();
    }

    private final void zza(zzdof zzdof0, zzaqr zzaqr0) {
        zzdnt.zza(zzdnt.zzb(zzdof0, new zzckr(this), zzazq.zzdxk), new zzckt(this, zzaqr0), zzazq.zzdxp);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final zzaqk zza(zzaqi zzaqi0) throws RemoteException {
        return null;
    }

    // 检测为 Lambda 实现
    final InputStream zza(zzdof zzdof0, zzdof zzdof1) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zza(zzaqi zzaqi0, zzaqp zzaqp0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zza(zzaqx zzaqx0, zzaqr zzaqr0) {
        zzdof zzdof0 = this.zzb(zzaqx0, Binder.getCallingUid());
        this.zza(zzdof0, zzaqr0);
        zzdof0.addListener(() -> zzazu.zza(this.zzfzb.zzum(), "persistFlags"), this.zzfeo);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zza(String s, zzaqr zzaqr0) {
        this.zza(this.zzgi(s), zzaqr0);
    }

    // 检测为 Lambda 实现
    final void zzant() [...]

    public final zzdof zzb(zzaqx zzaqx0, int v) {
        zzazo zzazo0 = zzazo.zzxr();
        zzakk zzakk0 = zzq.zzli().zza(this.zzur, zzazo0);
        zzcyw zzcyw0 = this.zzfzc.zza(zzaqx0, v);
        zzakc zzakc0 = zzakk0.zza("google.afma.response.normalize", zzckv.zzfzl, zzakj.zzdbv);
        zzckw zzckw0 = new zzckw(this.zzur, zzaqx0.zzdjo.zzbmj, this.zzfza, zzaqx0.zzdko);
        zzdif zzdif0 = zzcyw0.zzaee();
        zzcks zzcks0 = null;
        if(((Boolean)zzabn.zzcvg.get()).booleanValue()) {
            if(zzaqx0.zzdnd != null && !zzaqx0.zzdnd.isEmpty()) {
                zzcks0 = (zzcks)this.zzfzd.remove(zzaqx0.zzdnd);
                if(zzcks0 == null) {
                    zzawf.zzee("Request contained a PoolKey but no matching parameters were found.");
                }
            }
        }
        else if(zzaqx0.zzdnd != null && !zzaqx0.zzdnd.isEmpty()) {
            zzawf.zzee("Request contained a PoolKey but split request is disabled.");
        }
        if(zzcks0 == null) {
            zzdof zzdof0 = zzckh.zza(zzaqx0, zzdif0, zzcyw0);
            zzdof zzdof1 = zzckh.zza(zzdof0, zzdif0, zzakk0);
            zzdhs zzdhs0 = zzdif0.zza(zzdig.zzgvl, new zzdof[]{zzdof1, zzdof0}).zzb(new zzckg(zzdof0, zzdof1)).zzb(zzckw0).zzata();
            return zzdif0.zza(zzdig.zzgvm, new zzdof[]{zzdof0, zzdof1, zzdhs0}).zzb(new zzckj(zzdhs0, zzdof0, zzdof1)).zza(zzakc0).zzata();
        }
        zzdof zzdof2 = zzdnt.zzaj(new zzckz(zzcks0.zzfzi, zzcks0.zzfzh));
        zzdhs zzdhs1 = zzdif0.zza(zzdig.zzgvl, zzdof2).zzb(zzckw0).zzata();
        zzdof zzdof3 = zzdnt.zzaj(zzcks0);
        return zzdif0.zza(zzdig.zzgvm, new zzdof[]{zzdhs1, zzdof3}).zzb(new zzcki(zzdhs1, zzdof3)).zza(zzakc0).zzata();
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zzb(zzaqx zzaqx0, zzaqr zzaqr0) {
        zzdof zzdof0;
        int v = Binder.getCallingUid();
        zzazo zzazo0 = zzazo.zzxr();
        zzakk zzakk0 = zzq.zzli().zza(this.zzur, zzazo0);
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcqn)).booleanValue()) {
            zzcyw zzcyw0 = this.zzfzc.zza(zzaqx0, v);
            zzcyd zzcyd0 = zzcyw0.zzaed();
            zzakc zzakc0 = zzakk0.zza("google.afma.request.getSignals", zzakj.zzdbu, zzakj.zzdbv);
            zzdif zzdif0 = zzcyw0.zzaee();
            zzdof zzdof1 = zzdnt.zzaj(zzaqx0.zzdmz);
            zzdof0 = zzdif0.zza(zzdig.zzgvn, zzdof1).zza(new zzcko(zzcyd0)).zzw(zzdig.zzgvo).zza(zzakc0).zzata();
        }
        else {
            zzdof0 = zzdnt.immediateFailedFuture(new Exception("Signal collection disabled."));
        }
        this.zza(zzdof0, zzaqr0);
    }

    public final zzdof zzc(zzaqx zzaqx0, int v) {
        if(!((Boolean)zzabn.zzcvg.get()).booleanValue()) {
            return zzdnt.immediateFailedFuture(new Exception("Split request is disabled."));
        }
        if(zzaqx0.zzdnc == null) {
            return zzdnt.immediateFailedFuture(new Exception("Pool configuration missing from request."));
        }
        if(zzaqx0.zzdnc.zzgss != 0 && zzaqx0.zzdnc.zzgst != 0) {
            zzazo zzazo0 = zzazo.zzxr();
            zzakk zzakk0 = zzq.zzli().zza(this.zzur, zzazo0);
            zzcyw zzcyw0 = this.zzfzc.zza(zzaqx0, v);
            zzdif zzdif0 = zzcyw0.zzaee();
            zzdof zzdof0 = zzckh.zza(zzaqx0, zzdif0, zzcyw0);
            zzdof zzdof1 = zzckh.zza(zzdof0, zzdif0, zzakk0);
            return zzdif0.zza(zzdig.zzgwa, new zzdof[]{zzdof0, zzdof1}).zzb(() -> {
                String s = ((zzard)zzdof1.get()).zzuk();
                JSONObject jSONObject0 = (JSONObject)zzdof0.get();
                zzcks zzcks0 = new zzcks(((zzard)zzdof1.get()), jSONObject0);
                this.zzfzd.put(s, zzcks0);
                return new ByteArrayInputStream(s.getBytes(zzdks.UTF_8));
            }).zzata();
        }
        return zzdnt.immediateFailedFuture(new Exception("Caching is disabled."));
    }

    @Override  // com.google.android.gms.internal.ads.zzaqo
    public final void zzc(zzaqx zzaqx0, zzaqr zzaqr0) {
        this.zza(this.zzc(zzaqx0, Binder.getCallingUid()), zzaqr0);
    }

    public final zzdof zzgi(String s) {
        if(!((Boolean)zzabn.zzcvg.get()).booleanValue()) {
            return zzdnt.immediateFailedFuture(new Exception("Split request is disabled."));
        }
        zzckq zzckq0 = new zzckq(this);
        if(((zzcks)this.zzfzd.remove(s)) == null) {
            String s1 = String.valueOf(s);
            return s1.length() == 0 ? zzdnt.immediateFailedFuture(new Exception(new String("URL to be removed not found for cache key: "))) : zzdnt.immediateFailedFuture(new Exception("URL to be removed not found for cache key: " + s1));
        }
        return zzdnt.zzaj(zzckq0);
    }
}

