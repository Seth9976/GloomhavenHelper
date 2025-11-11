package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

final class zzsm extends PushbackInputStream {
    private final zzsh zzbsj;

    zzsm(zzsh zzsh0, InputStream inputStream0, int v) {
        this.zzbsj = zzsh0;
        super(inputStream0, 1);
    }

    @Override
    public final void close() throws IOException {
        synchronized(this) {
            this.zzbsj.zzbsc.disconnect();
            super.close();
        }
    }
}

