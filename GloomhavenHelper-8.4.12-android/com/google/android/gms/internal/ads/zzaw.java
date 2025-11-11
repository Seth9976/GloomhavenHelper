package com.google.android.gms.internal.ads;

import java.io.FilterInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

final class zzaw extends FilterInputStream {
    private final HttpURLConnection zzcm;

    zzaw(HttpURLConnection httpURLConnection0) {
        super(zzat.zza(httpURLConnection0));
        this.zzcm = httpURLConnection0;
    }

    @Override
    public final void close() throws IOException {
        super.close();
        this.zzcm.disconnect();
    }
}

