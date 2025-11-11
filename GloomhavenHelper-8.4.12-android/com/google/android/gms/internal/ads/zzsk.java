package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.io.IOException;

final class zzsk implements Runnable {
    private final zzsh zzbsf;
    private final zzsa zzbsg;
    private final zzrz zzbsh;
    private final zzazy zzbsi;

    zzsk(zzsh zzsh0, zzsa zzsa0, zzrz zzrz0, zzazy zzazy0) {
        this.zzbsf = zzsh0;
        this.zzbsg = zzsa0;
        this.zzbsh = zzrz0;
        this.zzbsi = zzazy0;
    }

    @Override
    public final void run() {
        zzazy zzazy0;
        zzsh zzsh0;
        try {
            zzsh0 = this.zzbsf;
            zzazy0 = this.zzbsi;
            zzry zzry0 = this.zzbsg.zzmx().zza(this.zzbsh);
            if(!zzry0.zzmu()) {
                zzazy0.setException(new RuntimeException("No entry contents."));
                zzsh0.zzbsc.disconnect();
                return;
            }
            zzsm zzsm0 = new zzsm(zzsh0, zzry0.zzmv(), 1);
            int v = zzsm0.read();
            if(v == -1) {
                throw new IOException("Unable to read from cache.");
            }
            zzsm0.unread(v);
            zzazy0.set(zzsm0);
        }
        catch(IOException | RemoteException iOException0) {
            zzawf.zzc("Unable to obtain a cache service instance.", iOException0);
            zzazy0.setException(iOException0);
            zzsh0.zzbsc.disconnect();
        }
    }
}

