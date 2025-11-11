package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

final class zzbb extends ThreadLocal {
    zzbb(zzbc zzbc0) {
    }

    @Override
    protected final Object initialValue() {
        return ByteBuffer.allocate(0x20);
    }
}

