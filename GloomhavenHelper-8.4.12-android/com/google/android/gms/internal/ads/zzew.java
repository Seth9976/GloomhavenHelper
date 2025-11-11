package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzew extends zzfv {
    private final Activity zzzo;
    private final View zzzp;

    public zzew(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1, View view0, Activity activity0) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 62);
        this.zzzp = view0;
        this.zzzo = activity0;
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(this.zzzp == null) {
            return;
        }
        boolean z = ((Boolean)zzvh.zzpd().zzd(zzzx.zzclv)).booleanValue();
        Object[] arr_object = (Object[])this.zzaah.invoke(null, this.zzzp, this.zzzo, Boolean.valueOf(z));
        synchronized(this.zzzx) {
            this.zzzx.zzbp(((long)(((Long)arr_object[0]))));
            this.zzzx.zzbq(((long)(((Long)arr_object[1]))));
            if(z) {
                this.zzzx.zzam(((String)arr_object[2]));
            }
        }
    }
}

