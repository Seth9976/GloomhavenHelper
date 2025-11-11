package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;

public final class zzdc {
    private final Context zzur;
    private final zzdka zzus;
    private final zzdja zzut;
    private final zzdix zzuu;
    private final Executor zzuv;
    private boolean zzuw;
    private volatile long zzux;

    @VisibleForTesting
    zzdc(@NonNull Context context0, @NonNull zzdix zzdix0, @NonNull zzdka zzdka0, @NonNull zzdja zzdja0, @NonNull Executor executor0) {
        this.zzux = 0L;
        this.zzur = context0;
        this.zzuu = zzdix0;
        this.zzus = zzdka0;
        this.zzut = zzdja0;
        this.zzuv = executor0;
    }

    public final String zza(Context context0, View view0, Activity activity0) {
        this.zzbr();
        if(this.zzbo()) {
            String s = this.zzut.zzb(context0, null, view0, activity0);
            this.zzuu.zza(5002, 0L, s, null);
            return s;
        }
        return "";
    }

    public final String zza(Context context0, String s, View view0, Activity activity0) {
        this.zzbr();
        if(this.zzbo()) {
            String s1 = this.zzut.zza(context0, null, s, view0, activity0);
            this.zzuu.zza(5000, 0L, s1, null);
            return s1;
        }
        return "";
    }

    public final void zza(MotionEvent motionEvent0) {
        this.zzbr();
        if(this.zzbo()) {
            this.zzut.zza(null, motionEvent0);
        }
    }

    public final String zzb(Context context0) {
        this.zzbr();
        if(this.zzbo()) {
            String s = this.zzut.zzu(context0, null);
            this.zzuu.zza(5001, 0L, s, null);
            return s;
        }
        return "";
    }

    public final boolean zzbo() {
        synchronized(this) {
            if(this.zzuw) {
                return true;
            }
            zzdkb zzdkb0 = this.zzus.zzdr(zzdkj.zzgyf);
            if(zzdkb0 != null && !zzdkb0.zza() && this.zzut.zzb(zzdkb0) == null) {
                this.zzuw = true;
            }
            return this.zzuw;
        }
    }

    public final void zzbp() {
        zzdf zzdf0 = new zzdf(this);
        this.zzuv.execute(zzdf0);
    }

    private final void zzbq() {
        String s1;
        String s;
        if(this.zzus.zzdr(zzdkj.zzgyf) == null) {
            s1 = null;
            s = null;
        }
        else {
            s = "";
            s1 = "";
        }
        try {
            zzdke zzdke0 = zzdjf.zza(this.zzur, 1, s1, s, "1", this.zzuu);
            if(zzdke0.zzgye != null && zzdke0.zzgye.length != 0) {
                zzfx.zza(zzdxn.zzt(zzdke0.zzgye), zzdym.zzbch());
            }
        }
        catch(zzdzh zzdzh0) {
            this.zzuu.zza(4002, 0L, zzdzh0);
        }
    }

    private final void zzbr() {
        if(System.currentTimeMillis() / 1000L - this.zzux > 3600L && (!this.zzuw || this.zzut.zzatb() != null && this.zzut.zzatb().zzff(3600L))) {
            this.zzbp();
        }
    }
}

