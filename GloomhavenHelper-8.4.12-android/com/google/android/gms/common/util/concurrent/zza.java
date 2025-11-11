package com.google.android.gms.common.util.concurrent;

import android.os.Process;

final class zza implements Runnable {
    private final int priority;
    private final Runnable zzhu;

    public zza(Runnable runnable0, int v) {
        this.zzhu = runnable0;
        this.priority = v;
    }

    @Override
    public final void run() {
        Process.setThreadPriority(this.priority);
        this.zzhu.run();
    }
}

