package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

final class zzi implements Executor {
    private final Handler val$handler;

    zzi(zzj zzj0, Handler handler0) {
        this.val$handler = handler0;
        super();
    }

    @Override
    public final void execute(Runnable runnable0) {
        this.val$handler.post(runnable0);
    }
}

