package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import java.util.concurrent.ThreadFactory;

final class zzos implements ThreadFactory {
    private final String zzbhh;

    zzos(String s) {
        this.zzbhh = s;
        super();
    }

    @Override
    public final Thread newThread(@NonNull Runnable runnable0) {
        return new Thread(runnable0, this.zzbhh);
    }
}

