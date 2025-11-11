package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;

final class zzaa extends zza {
    private final String zzc;
    private final String zzd;
    private final Context zze;
    private final Bundle zzf;
    private final zzx zzg;

    zzaa(zzx zzx0, String s, String s1, Context context0, Bundle bundle0) {
        this.zzg = zzx0;
        this.zzc = s;
        this.zzd = s1;
        this.zze = context0;
        this.zzf = bundle0;
        super();
    }

    @Override  // com.google.android.gms.internal.measurement.zzx$zza
    public final void zza() {
        boolean z1;
        int v2;
        String s2;
        String s1;
        String s;
        try {
            ArrayList arrayList0 = new ArrayList();
            this.zzg.zzf = arrayList0;
            if(zzx.zza(this.zzg, this.zzc, this.zzd)) {
                s = this.zzc;
                s1 = this.zzd;
                s2 = this.zzg.zzc;
            }
            else {
                s2 = null;
                s = null;
                s1 = null;
            }
            zzx.zzi(this.zze);
            boolean z = zzx.zzi.booleanValue() || s != null;
            zzm zzm0 = this.zzg.zza(this.zze, z);
            this.zzg.zzr = zzm0;
            if(this.zzg.zzr == null) {
                Log.w(this.zzg.zzc, "Failed to connect to measurement client.");
                return;
            }
            int v = zzx.zzh(this.zze);
            int v1 = zzx.zzg(this.zze);
            if(z) {
                v2 = Math.max(v, v1);
                z1 = v1 < v;
            }
            else {
                v2 = v <= 0 ? v1 : v;
                z1 = v > 0;
            }
            zzv zzv0 = new zzv(18102L, ((long)v2), z1, s2, s, s1, this.zzf);
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zze);
            this.zzg.zzr.initialize(iObjectWrapper0, zzv0, this.zza);
        }
        catch(RemoteException remoteException0) {
            this.zzg.zza(remoteException0, true, false);
        }
    }
}

