package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzbtj implements Runnable {
    private final Object zzdcl;
    private final zzbtm zzfkv;

    zzbtj(zzbtm zzbtm0, Object object0) {
        this.zzfkv = zzbtm0;
        this.zzdcl = object0;
    }

    @Override
    public final void run() {
        try {
            this.zzfkv.zzp(this.zzdcl);
        }
        catch(Throwable throwable0) {
            zzq.zzkz().zzb(throwable0, "EventEmitter.notify");
            zzawf.zza("Event emitter exception.", throwable0);
        }
    }
}

