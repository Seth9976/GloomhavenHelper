package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

final class zzdnv implements Runnable {
    private final Future zzhcx;
    private final zzdnu zzhcy;

    zzdnv(Future future0, zzdnu zzdnu0) {
        this.zzhcx = future0;
        this.zzhcy = zzdnu0;
    }

    @Override
    public final void run() {
        Object object0;
        Future future0 = this.zzhcx;
        if(future0 instanceof zzdow) {
            Throwable throwable0 = zzdoz.zza(((zzdow)future0));
            if(throwable0 != null) {
                this.zzhcy.zzb(throwable0);
                return;
            }
        }
        try {
            object0 = zzdnt.zza(this.zzhcx);
        }
        catch(ExecutionException executionException0) {
            this.zzhcy.zzb(executionException0.getCause());
            return;
        }
        catch(RuntimeException | Error runtimeException0) {
            this.zzhcy.zzb(runtimeException0);
            return;
        }
        this.zzhcy.onSuccess(object0);
    }

    @Override
    public final String toString() {
        return zzdkx.zzaa(this).zzab(this.zzhcy).toString();
    }
}

