package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Executor;

final class zzazs implements Executor {
    private final Handler zzdxr;

    zzazs() {
        this.zzdxr = new zzawi(Looper.getMainLooper());
    }

    @Override
    public final void execute(Runnable runnable0) {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
            try {
                runnable0.run();
                return;
            }
            catch(Throwable throwable0) {
                zzawo.zza(zzq.zzkz().getApplicationContext(), throwable0);
                throw throwable0;
            }
        }
        this.zzdxr.post(runnable0);
    }
}

