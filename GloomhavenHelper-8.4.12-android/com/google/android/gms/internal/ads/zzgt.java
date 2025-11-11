package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.Message;

final class zzgt extends zzdkp {
    private final zzgq zzaep;

    zzgt(zzgq zzgq0, Looper looper0) {
        this.zzaep = zzgq0;
        super(looper0);
    }

    @Override  // android.os.Handler
    public final void handleMessage(Message message0) {
        this.zzaep.zza(message0);
    }
}

