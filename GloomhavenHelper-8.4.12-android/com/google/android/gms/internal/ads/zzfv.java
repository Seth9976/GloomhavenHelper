package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzfv implements Callable {
    private final String TAG;
    private final String className;
    private final String zzaaf;
    protected Method zzaah;
    private final int zzaak;
    private final int zzaal;
    protected final zzei zzuy;
    protected final zzb zzzx;

    public zzfv(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        this.TAG = this.getClass().getSimpleName();
        this.zzuy = zzei0;
        this.className = s;
        this.zzaaf = s1;
        this.zzzx = zzbs$zza$zzb0;
        this.zzaak = v;
        this.zzaal = v1;
    }

    @Override
    public Object call() throws Exception {
        return this.zzcu();
    }

    protected abstract void zzcs() throws IllegalAccessException, InvocationTargetException;

    public Void zzcu() throws Exception {
        try {
            long v = System.nanoTime();
            this.zzaah = this.zzuy.zza(this.className, this.zzaaf);
            if(this.zzaah == null) {
                return null;
            }
            this.zzcs();
            zzde zzde0 = this.zzuy.zzch();
            if(zzde0 != null && this.zzaak != 0x80000000) {
                zzde0.zza(this.zzaal, this.zzaak, (System.nanoTime() - v) / 1000L);
            }
        }
        catch(IllegalAccessException | InvocationTargetException unused_ex) {
        }
        return null;
    }
}

