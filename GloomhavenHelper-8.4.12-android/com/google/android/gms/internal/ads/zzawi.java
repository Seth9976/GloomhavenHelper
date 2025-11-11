package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.zzq;

public final class zzawi extends zzdkp {
    public zzawi(Looper looper0) {
        super(looper0);
    }

    @Override  // android.os.Handler
    public final void handleMessage(Message message0) {
        try {
            super.handleMessage(message0);
        }
        catch(Exception exception0) {
            zzq.zzkz().zza(exception0, "AdMobHandler.handleMessage");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdkp
    protected final void zzb(Message message0) {
        try {
            super.zzb(message0);
        }
        catch(Throwable throwable0) {
            zzawo.zza(zzq.zzkz().getApplicationContext(), throwable0);
            throw throwable0;
        }
    }
}

