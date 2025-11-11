package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zzbj extends zzeds {
    private ByteBuffer zzcs;

    public zzbj(String s) {
        super(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzeds
    public final void zzg(ByteBuffer byteBuffer0) {
        this.zzcs = byteBuffer0;
        byteBuffer0.position(byteBuffer0.position() + byteBuffer0.remaining());
    }
}

