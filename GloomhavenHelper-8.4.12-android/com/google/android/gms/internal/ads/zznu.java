package com.google.android.gms.internal.ads;

import java.io.IOException;

public class zznu extends IOException {
    private final int type;
    private final zznp zzbff;

    public zznu(IOException iOException0, zznp zznp0, int v) {
        super(iOException0);
        this.zzbff = zznp0;
        this.type = v;
    }

    public zznu(String s, zznp zznp0, int v) {
        super(s);
        this.zzbff = zznp0;
        this.type = 1;
    }

    public zznu(String s, IOException iOException0, zznp zznp0, int v) {
        super(s, iOException0);
        this.zzbff = zznp0;
        this.type = 1;
    }
}

