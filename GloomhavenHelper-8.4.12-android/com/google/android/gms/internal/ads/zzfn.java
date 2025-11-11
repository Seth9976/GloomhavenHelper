package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzfn extends zzfv {
    private final StackTraceElement[] zzaac;

    public zzfn(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1, StackTraceElement[] arr_stackTraceElement) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 45);
        this.zzaac = arr_stackTraceElement;
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(this.zzaac != null) {
            zzeg zzeg0 = new zzeg(((String)this.zzaah.invoke(null, this.zzaac)));
            zzb zzbs$zza$zzb0 = this.zzzx;
            synchronized(zzbs$zza$zzb0) {
                this.zzzx.zzbi(((long)zzeg0.zzxl));
                if(zzeg0.zzxm.booleanValue()) {
                    this.zzzx.zzh((zzeg0.zzxn.booleanValue() ? zzcd.zzlb : zzcd.zzlc));
                }
                else {
                    this.zzzx.zzh(zzcd.zzld);
                }
            }
        }
    }
}

