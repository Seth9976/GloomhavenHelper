package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.Map;

final class zzcab implements zzafz {
    private final zzbzy zzfre;
    private final zzaem zzfrf;

    zzcab(zzbzy zzbzy0, zzaem zzaem0) {
        this.zzfre = zzbzy0;
        this.zzfrf = zzaem0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzaem zzaem0;
        zzbzy zzbzy0;
        try {
            zzbzy0 = this.zzfre;
            zzaem0 = this.zzfrf;
            zzbzy0.zzfrc = Long.parseLong(((String)map0.get("timestamp")));
        }
        catch(NumberFormatException unused_ex) {
            zzawf.zzey("Failed to call parse unconfirmedClickTimestamp.");
        }
        zzbzy0.zzfrb = (String)map0.get("id");
        String s = (String)map0.get("asset_id");
        if(zzaem0 == null) {
            zzawf.zzeb("Received unconfirmed click but UnconfirmedClickListener is null.");
            return;
        }
        try {
            zzaem0.onUnconfirmedClickReceived(s);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

