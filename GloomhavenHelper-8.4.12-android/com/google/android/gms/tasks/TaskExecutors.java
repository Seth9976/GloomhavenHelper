package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors {
    static final class zza implements Executor {
        private final Handler mHandler;

        public zza() {
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public final void execute(@NonNull Runnable runnable0) {
            this.mHandler.post(runnable0);
        }
    }

    public static final Executor MAIN_THREAD;
    static final Executor zzw;

    static {
        TaskExecutors.MAIN_THREAD = new zza();
        TaskExecutors.zzw = new zzt();
    }
}

