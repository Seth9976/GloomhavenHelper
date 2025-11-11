package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzv implements Runnable {
    private final Callable val$callable;
    private final zzu zzad;

    zzv(zzu zzu0, Callable callable0) {
        this.zzad = zzu0;
        this.val$callable = callable0;
        super();
    }

    @Override
    public final void run() {
        try {
            Object object0 = this.val$callable.call();
            this.zzad.setResult(object0);
        }
        catch(Exception exception0) {
            this.zzad.setException(exception0);
        }
    }
}

