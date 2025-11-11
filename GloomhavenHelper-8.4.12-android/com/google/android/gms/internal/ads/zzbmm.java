package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.WorkerThread;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class zzbmm extends zzblg {
    private final Executor zzfeo;
    private final zzael zzfhb;
    private final Runnable zzfhc;

    public zzbmm(zzbne zzbne0, zzael zzael0, Runnable runnable0, Executor executor0) {
        super(zzbne0);
        this.zzfhb = zzael0;
        this.zzfhc = runnable0;
        this.zzfeo = executor0;
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final zzxj getVideoController() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final void zza(ViewGroup viewGroup0, zzuk zzuk0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final zzdeh zzagl() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final View zzagm() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final int zzagr() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbnf
    @WorkerThread
    public final void zzags() {
        zzbmo zzbmo0 = () -> try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(new zzbmp(new AtomicReference(this.zzfhc)));
            if(!this.zzfhb.zzm(iObjectWrapper0)) {
                new zzbmp(new AtomicReference(this.zzfhc)).run();
            }
        }
        catch(RemoteException unused_ex) {
            new zzbmp(new AtomicReference(this.zzfhc)).run();
        };
        this.zzfeo.execute(zzbmo0);
    }

    // 检测为 Lambda 实现
    final void zze(Runnable runnable0) [...]

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final void zzkd() {
    }
}

