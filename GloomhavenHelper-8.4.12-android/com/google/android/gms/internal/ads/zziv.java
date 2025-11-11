package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zziv extends zzio {
    public final zzir zzamt;
    public long zzamu;
    private final int zzamv;
    public ByteBuffer zzcs;

    public zziv(int v) {
        this.zzamt = new zzir();
        this.zzamv = 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzio
    public final void clear() {
        super.clear();
        ByteBuffer byteBuffer0 = this.zzcs;
        if(byteBuffer0 != null) {
            byteBuffer0.clear();
        }
    }

    private final ByteBuffer zzaa(int v) {
        throw new IllegalStateException("Buffer too small (" + (this.zzcs == null ? 0 : this.zzcs.capacity()) + " < " + v + ")");
    }

    public final boolean zzgj() {
        return this.zzx(0x40000000);
    }

    public final void zzz(int v) throws IllegalStateException {
        ByteBuffer byteBuffer0 = this.zzcs;
        if(byteBuffer0 == null) {
            this.zzcs = this.zzaa(v);
            return;
        }
        int v1 = this.zzcs.position();
        int v2 = v + v1;
        if(byteBuffer0.capacity() >= v2) {
            return;
        }
        ByteBuffer byteBuffer1 = this.zzaa(v2);
        if(v1 > 0) {
            this.zzcs.position(0);
            this.zzcs.limit(v1);
            byteBuffer1.put(this.zzcs);
        }
        this.zzcs = byteBuffer1;
    }
}

