package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public final class zzex extends zzfv {
    private static final zzfy zzzq;
    private final Context zzzr;
    private zza zzzs;

    static {
        zzex.zzzq = new zzfy();
    }

    public zzex(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1, Context context0, zza zzbo$zza0) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 27);
        this.zzzr = context0;
        this.zzzs = zzbo$zza0;
    }

    // 去混淆评级： 中等(80)
    private static String zza(zza zzbo$zza0) {
        return zzbo$zza0 == null || !zzbo$zza0.zzz() || true ? null : "";
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        zzcl zzcl2;
        zzbq zzbq0;
        AtomicReference atomicReference0 = zzex.zzzq.zzaw("com.esotericsoftware.gloomhavenhelper");
        synchronized(atomicReference0) {
            zzcl zzcl0 = (zzcl)atomicReference0.get();
            boolean z = false;
            if(zzcl0 == null || zzep.zzav(zzcl0.zzmx) || zzcl0.zzmx.equals("E") || zzcl0.zzmx.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
                if(zzep.zzav(zzex.zza(this.zzzs))) {
                    zzbq0 = !zzep.zzav(zzex.zza(this.zzzs)) || this.zzzs == null || !this.zzzs.zzx() || this.zzzs.zzy().zzac() != zzbq.zzel || !this.zzuy.zzci() ? zzbq.zzek : zzbq.zzel;
                }
                else {
                    zzbq0 = zzbq.zzem;
                }
                Method method0 = this.zzaah;
                Object[] arr_object = {this.zzzr, null, null};
                if(zzbq0 == zzbq.zzek) {
                    z = true;
                }
                arr_object[1] = Boolean.valueOf(z);
                arr_object[2] = zzvh.zzpd().zzd(zzzx.zzcma);
                zzcl zzcl1 = new zzcl(((String)method0.invoke(null, arr_object)));
                if(zzep.zzav(zzcl1.zzmx) || zzcl1.zzmx.equals("E")) {
                    switch(zzfa.zzzu[zzbq0.ordinal()]) {
                        case 1: {
                            zzcl1.zzmx = "";
                            break;
                        }
                        case 2: {
                            String s = this.zzct();
                            if(!zzep.zzav(s)) {
                                zzcl1.zzmx = s;
                            }
                        }
                    }
                }
                atomicReference0.set(zzcl1);
            }
            zzcl2 = (zzcl)atomicReference0.get();
        }
        synchronized(this.zzzx) {
            if(zzcl2 != null) {
                this.zzzx.zzah(zzcl2.zzmx);
                this.zzzx.zzba(zzcl2.zzmy);
                this.zzzx.zzaj(zzcl2.zzmz);
                this.zzzx.zzak(zzcl2.zzna);
                this.zzzx.zzal(zzcl2.zznb);
            }
        }
    }

    private final String zzct() {
        try {
            if(this.zzuy.zzcl() != null) {
                this.zzuy.zzcl().get();
            }
            com.google.android.gms.internal.ads.zzbs.zza zzbs$zza0 = this.zzuy.zzck();
            if(zzbs$zza0 != null && zzbs$zza0.zzak()) {
                return "";
            }
        }
        catch(InterruptedException | ExecutionException unused_ex) {
        }
        return null;
    }
}

