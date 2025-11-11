package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public abstract class zzedu extends zzeds implements zzbf {
    private int flags;
    private int version;

    protected zzedu(String s) {
        super(s);
    }

    public final int getVersion() {
        if(!this.zzifi) {
            this.zzbgj();
        }
        return this.version;
    }

    protected final long zzo(ByteBuffer byteBuffer0) {
        this.version = zzbg.zza(byteBuffer0.get());
        this.flags = (zzbg.zzb(byteBuffer0) << 8) + zzbg.zza(byteBuffer0.get());
        return 4L;
    }
}

