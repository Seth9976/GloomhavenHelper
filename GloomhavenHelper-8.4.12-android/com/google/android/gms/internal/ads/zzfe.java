package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class zzfe extends zzfv {
    public zzfe(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 24);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    public final Object call() throws Exception {
        return this.zzcu();
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        if(this.zzuy.zzcg()) {
            this.zzcv();
            return;
        }
        synchronized(this.zzzx) {
            this.zzzx.zzan(((String)this.zzaah.invoke(null, this.zzuy.getContext())));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    public final Void zzcu() throws Exception {
        if(this.zzuy.isInitialized()) {
            return super.zzcu();
        }
        if(this.zzuy.zzcg()) {
            this.zzcv();
        }
        return null;
    }

    private final void zzcv() {
        AdvertisingIdClient advertisingIdClient0 = this.zzuy.zzco();
        if(advertisingIdClient0 == null) {
            return;
        }
        try {
            Info advertisingIdClient$Info0 = advertisingIdClient0.getInfo();
            String s = zzep.zzau(advertisingIdClient$Info0.getId());
            if(s != null) {
                synchronized(this.zzzx) {
                    this.zzzx.zzan(s);
                    this.zzzx.zzb(advertisingIdClient$Info0.isLimitAdTrackingEnabled());
                    this.zzzx.zzb(zzc.zzii);
                }
            }
        }
        catch(IOException unused_ex) {
        }
    }
}

