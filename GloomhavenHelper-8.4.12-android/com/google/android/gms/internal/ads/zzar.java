package com.google.android.gms.internal.ads;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class zzar {
    private final int zzcg;
    private final List zzch;
    private final int zzci;
    private final InputStream zzcj;

    public zzar(int v, List list0) {
        this(v, list0, -1, null);
    }

    public zzar(int v, List list0, int v1, InputStream inputStream0) {
        this.zzcg = v;
        this.zzch = list0;
        this.zzci = v1;
        this.zzcj = inputStream0;
    }

    public final InputStream getContent() {
        return this.zzcj;
    }

    public final int getContentLength() {
        return this.zzci;
    }

    public final int getStatusCode() {
        return this.zzcg;
    }

    public final List zzq() {
        return Collections.unmodifiableList(this.zzch);
    }
}

