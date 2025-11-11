package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Callable;

final class zzarh implements Callable {
    private final Context val$context;
    private final zzari zzdom;

    zzarh(zzari zzari0, Context context0) {
        this.zzdom = zzari0;
        this.val$context = context0;
        super();
    }

    @Override
    public final Object call() throws Exception {
        zzarg zzarg0;
        zzark zzark0 = (zzark)this.zzdom.zzdon.get(this.val$context);
        if(zzark0 == null) {
            zzarg0 = new zzarf(this.val$context).zzul();
        }
        else {
            long v = (long)(((Long)zzaba.zzcto.get()));
            long v1 = zzq.zzlc().currentTimeMillis();
            zzarg0 = zzark0.zzdoq + v >= v1 ? new zzarf(this.val$context, zzark0.zzdor).zzul() : new zzarf(this.val$context).zzul();
        }
        zzark zzark1 = new zzark(this.zzdom, zzarg0);
        this.zzdom.zzdon.put(this.val$context, zzark1);
        return zzarg0;
    }
}

