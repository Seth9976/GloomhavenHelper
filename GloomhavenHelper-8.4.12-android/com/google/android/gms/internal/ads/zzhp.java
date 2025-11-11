package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public interface zzhp {
    public static final ByteBuffer zzahl;

    static {
        zzhp.zzahl = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
    }

    void flush();

    boolean isActive();

    void reset();

    boolean zzb(int arg1, int arg2, int arg3) throws zzho;

    boolean zzez();

    int zzfe();

    int zzff();

    void zzfg();

    ByteBuffer zzfh();

    void zzi(ByteBuffer arg1);
}

