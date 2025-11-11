package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public abstract class zzbgk implements zzbjh {
    @GuardedBy("AppComponent.class")
    private static zzbgk zzekw;

    public static zzbgk zza(Context context0, zzalk zzalk0, int v) {
        zzbgk zzbgk0 = zzbgk.zze(context0, v);
        zzbgk0.zzack().zzb(zzalk0);
        return zzbgk0;
    }

    @Deprecated
    private static zzbgk zza(zzazo zzazo0, Context context0, zza zzbih$zza0) {
        synchronized(zzbgk.class) {
            if(zzbgk.zzekw == null) {
                zzbgk.zzekw = new zzbhv(null).zzc(new zzbgn(new com.google.android.gms.internal.ads.zzbgn.zza().zza(zzazo0).zzbv(context0), null)).zza(new zzbih(zzbih$zza0)).zzaet();
                zzzx.initialize(context0);
                zzq.zzkz().zzd(context0, zzazo0);
                zzq.zzlb().initialize(context0);
                zzq.zzkv().zzaq(context0);
                zzq.zzkv().zzar(context0);
                zzawd.zzap(context0);
                zzq.zzky().initialize(context0);
                zzq.zzlq().initialize(context0);
                if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue()) {
                    new zzclu(context0, zzazo0, new zzsn(new zzss(context0)), new zzcle(new zzclc(context0), zzbgk.zzekw.zzaci())).zzaob();
                }
            }
            return zzbgk.zzekw;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbjh
    public final zzcyw zza(zzaqx zzaqx0, int v) {
        return this.zza(new zzdaf(zzaqx0, v));
    }

    protected abstract zzcyw zza(zzdaf arg1);

    public abstract Executor zzacf();

    public abstract ScheduledExecutorService zzacg();

    public abstract Executor zzach();

    public abstract zzdoe zzaci();

    public abstract zzbsf zzacj();

    public abstract zzcnk zzack();

    public abstract zzbik zzacl();

    public abstract zzbmf zzacm();

    public abstract zzbkx zzacn();

    public abstract zzdbi zzaco();

    public abstract zzbws zzacp();

    public abstract zzbxr zzacq();

    public abstract zzcdt zzacr();

    public abstract zzddz zzacs();

    public abstract zzcsw zzact();

    @Deprecated
    public static zzbgk zze(Context context0, int v) {
        synchronized(zzbgk.class) {
            if(zzbgk.zzekw != null) {
                return zzbgk.zzekw;
            }
        }
        return zzbgk.zza(new zzazo(20089000, v, true, false), context0, new zzbhg());
    }
}

