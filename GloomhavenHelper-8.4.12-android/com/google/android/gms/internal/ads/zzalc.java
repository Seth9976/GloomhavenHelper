package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzalc {
    private static zzalc zzdcm;
    private AtomicBoolean zzdcn;

    @VisibleForTesting
    zzalc() {
        this.zzdcn = new AtomicBoolean(false);
    }

    private static void zza(Context context0, AppMeasurementSdk appMeasurementSdk0) {
        try {
            ((zzbgf)zzazk.zza(context0, "com.google.android.gms.ads.measurement.DynamiteMeasurementManager", zzalh.zzbun)).zza(ObjectWrapper.wrap(context0), new zzald(appMeasurementSdk0));
        }
        catch(zzazm | NullPointerException | RemoteException zzazm0) {
            zzazh.zze("#007 Could not call remote method.", zzazm0);
        }
    }

    @Nullable
    public final Thread zzc(Context context0, String s) {
        if(!this.zzdcn.compareAndSet(false, true)) {
            return null;
        }
        Thread thread0 = new Thread(() -> {
            zzzx.initialize(context0);
            Bundle bundle0 = new Bundle();
            bundle0.putBoolean("measurementEnabled", ((Boolean)zzvh.zzpd().zzd(zzzx.zzciz)).booleanValue());
            zzalc.zza(context0, AppMeasurementSdk.getInstance(context0, "FA-Ads", "am", s, bundle0));
        });
        thread0.start();
        return thread0;
    }

    // 检测为 Lambda 实现
    static void zzd(Context context0, String s) [...]

    @Nullable
    public final Thread zzn(Context context0) {
        if(!this.zzdcn.compareAndSet(false, true)) {
            return null;
        }
        Thread thread0 = new Thread(() -> {
            zzzx.initialize(context0);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcje)).booleanValue() && zzalc.zzo(context0)) {
                zzalc.zza(context0, AppMeasurementSdk.getInstance(context0));
            }
        });
        thread0.start();
        return thread0;
    }

    private static boolean zzo(Context context0) {
        try {
            context0.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        }
        catch(ClassNotFoundException unused_ex) {
            return false;
        }
    }

    // 检测为 Lambda 实现
    static void zzp(Context context0) [...]

    public static zzalc zzso() {
        if(zzalc.zzdcm == null) {
            zzalc.zzdcm = new zzalc();
        }
        return zzalc.zzdcm;
    }
}

