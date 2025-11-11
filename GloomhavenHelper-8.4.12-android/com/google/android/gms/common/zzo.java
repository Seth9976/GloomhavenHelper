package com.google.android.gms.common;

import java.util.concurrent.Callable;

final class zzo extends zzm {
    private final Callable zzaf;

    private zzo(Callable callable0) {
        super(false, null, null);
        this.zzaf = callable0;
    }

    zzo(Callable callable0, zzn zzn0) {
        this(callable0);
    }

    @Override  // com.google.android.gms.common.zzm
    final String getErrorMessage() {
        try {
            return (String)this.zzaf.call();
        }
        catch(Exception exception0) {
            throw new RuntimeException(exception0);
        }
    }
}

