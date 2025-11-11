package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzdix {
    private final Executor executor;
    private final Task zzgwp;
    private final Context zzur;

    private zzdix(@NonNull Context context0, @NonNull Executor executor0, @NonNull Task task0) {
        this.zzur = context0;
        this.executor = executor0;
        this.zzgwp = task0;
    }

    public static zzdix zza(@NonNull Context context0, @NonNull Executor executor0) {
        return new zzdix(context0, executor0, Tasks.call(executor0, new zzdiz(context0)));
    }

    private final Task zza(int v, long v1, Exception exception0, String s, Map map0) {
        zza zzbm$zza$zza0 = com.google.android.gms.internal.ads.zzbm.zza.zzt().zzi("com.esotericsoftware.gloomhavenhelper").zzc(v1);
        if(exception0 != null) {
            zzbm$zza$zza0.zzj(zzdlj.zza(exception0)).zzk(exception0.getClass().getName());
        }
        if(s != null) {
            zzbm$zza$zza0.zzm(s);
        }
        if(map0 != null) {
            for(Object object0: map0.keySet()) {
                zzbm$zza$zza0.zza(zzb.zzv().zzs(((String)object0)).zzt(((String)map0.get(((String)object0)))));
            }
        }
        zzdiy zzdiy0 = new zzdiy(zzbm$zza$zza0, v);
        return this.zzgwp.continueWith(this.executor, zzdiy0);
    }

    public final Task zza(int v, long v1, Exception exception0) {
        return this.zza(v, v1, exception0, null, null);
    }

    public final Task zza(int v, long v1, String s, Map map0) {
        return this.zza(v, v1, null, s, null);
    }

    public final Task zzg(int v, long v1) {
        return this.zza(v, v1, null, null, null);
    }
}

