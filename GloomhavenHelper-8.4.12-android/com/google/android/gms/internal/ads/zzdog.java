package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

final class zzdog implements Executor {
    boolean zzhde;
    private final Executor zzhdf;
    private final zzdmt zzhdg;

    zzdog(Executor executor0, zzdmt zzdmt0) {
        this.zzhdf = executor0;
        this.zzhdg = zzdmt0;
        super();
        this.zzhde = true;
    }

    @Override
    public final void execute(Runnable runnable0) {
        try {
            zzdoj zzdoj0 = new zzdoj(this, runnable0);
            this.zzhdf.execute(zzdoj0);
        }
        catch(RejectedExecutionException rejectedExecutionException0) {
            if(this.zzhde) {
                this.zzhdg.setException(rejectedExecutionException0);
            }
        }
    }
}

