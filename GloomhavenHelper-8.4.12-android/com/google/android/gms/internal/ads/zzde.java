package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import android.os.ConditionVariable;
import androidx.annotation.VisibleForTesting;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class zzde {
    private zzei zzuy;
    private static final ConditionVariable zzuz;
    @VisibleForTesting
    protected static volatile zzss zzva;
    @VisibleForTesting
    protected volatile Boolean zzvb;
    private static volatile Random zzvc;

    static {
        zzde.zzuz = new ConditionVariable();
        zzde.zzva = null;
        zzde.zzvc = null;
    }

    public zzde(zzei zzei0) {
        this.zzuy = zzei0;
        zzei0.zzcc().execute(new zzdh(this));
    }

    public final void zza(int v, int v1, long v2) {
        this.zza(v, v1, v2, null, null);
    }

    public final void zza(int v, int v1, long v2, String s) {
        this.zza(v, -1, v2, s, null);
    }

    public final void zza(int v, int v1, long v2, String s, Exception exception0) {
        try {
            zzde.zzuz.block();
            if(this.zzvb.booleanValue() && zzde.zzva != null) {
                zza zzbm$zza$zza0 = com.google.android.gms.internal.ads.zzbm.zza.zzt().zzi("com.esotericsoftware.gloomhavenhelper").zzc(v2);
                if(s != null) {
                    zzbm$zza$zza0.zzl(s);
                }
                if(exception0 != null) {
                    StringWriter stringWriter0 = new StringWriter();
                    zzdww.zza(exception0, new PrintWriter(stringWriter0));
                    zzbm$zza$zza0.zzj(stringWriter0.toString()).zzk(exception0.getClass().getName());
                }
                zzsw zzsw0 = zzde.zzva.zzf(((com.google.android.gms.internal.ads.zzbm.zza)(((zzdyz)zzbm$zza$zza0.zzbcx()))).toByteArray());
                zzsw0.zzbr(v);
                if(v1 != -1) {
                    zzsw0.zzbq(v1);
                }
                zzsw0.zzdt();
            }
        }
        catch(Exception unused_ex) {
        }
    }

    public static int zzbs() {
        try {
            return Build.VERSION.SDK_INT < 21 ? zzde.zzbt().nextInt() : ThreadLocalRandom.current().nextInt();
        }
        catch(RuntimeException unused_ex) {
            return zzde.zzbt().nextInt();
        }
    }

    private static Random zzbt() {
        if(zzde.zzvc == null) {
            Class class0 = zzde.class;
            synchronized(class0) {
                if(zzde.zzvc == null) {
                    zzde.zzvc = new Random();
                }
                return zzde.zzvc;
            }
        }
        return zzde.zzvc;
    }
}

