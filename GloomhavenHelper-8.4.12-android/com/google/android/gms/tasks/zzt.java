package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzt implements Executor {
    @Override
    public final void execute(@NonNull Runnable runnable0) {
        runnable0.run();
    }
}

