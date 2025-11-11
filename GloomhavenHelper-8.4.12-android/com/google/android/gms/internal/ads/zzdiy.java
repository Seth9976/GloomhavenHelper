package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzdiy implements Continuation {
    private final int zzdul;
    private final zza zzgwq;

    zzdiy(zza zzbm$zza$zza0, int v) {
        this.zzgwq = zzbm$zza$zza0;
        this.zzdul = v;
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) {
        zza zzbm$zza$zza0 = this.zzgwq;
        int v = this.zzdul;
        if(task0.isSuccessful()) {
            zzsw zzsw0 = ((zzss)task0.getResult()).zzf(((com.google.android.gms.internal.ads.zzbm.zza)(((zzdyz)zzbm$zza$zza0.zzbcx()))).toByteArray());
            zzsw0.zzbr(v);
            zzsw0.zzdt();
            return true;
        }
        return false;
    }
}

