package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

abstract class zzdnj extends zzdob {
    private final Executor zzhcq;
    boolean zzhcr;
    private final zzdnh zzhcs;

    zzdnj(zzdnh zzdnh0, Executor executor0) {
        this.zzhcs = zzdnh0;
        super();
        this.zzhcr = true;
        this.zzhcq = (Executor)zzdlg.checkNotNull(executor0);
    }

    final void execute() {
        try {
            this.zzhcq.execute(this);
        }
        catch(RejectedExecutionException rejectedExecutionException0) {
            if(this.zzhcr) {
                this.zzhcs.setException(rejectedExecutionException0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdob
    final boolean isDone() {
        return this.zzhcs.isDone();
    }

    abstract void setValue(Object arg1);

    @Override  // com.google.android.gms.internal.ads.zzdob
    final void zzb(Object object0, Throwable throwable0) {
        zzdnh.zza(this.zzhcs, null);
        if(throwable0 != null) {
            if(throwable0 instanceof ExecutionException) {
                this.zzhcs.setException(throwable0.getCause());
                return;
            }
            if(throwable0 instanceof CancellationException) {
                this.zzhcs.cancel(false);
                return;
            }
            this.zzhcs.setException(throwable0);
            return;
        }
        this.setValue(object0);
    }
}

