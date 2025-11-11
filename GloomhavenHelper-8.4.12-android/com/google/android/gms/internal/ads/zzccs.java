package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.Map;

public final class zzccs implements zzafz {
    private final zzeed zzfok;
    private final zzccv zzfqy;
    @Nullable
    private final zzadx zzfth;

    public zzccs(zzbzg zzbzg0, zzbyz zzbyz0, zzccv zzccv0, zzeed zzeed0) {
        this.zzfth = zzbzg0.zzgb(zzbyz0.getCustomTemplateId());
        this.zzfqy = zzccv0;
        this.zzfok = zzeed0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        String s = (String)map0.get("asset");
        try {
            zzadn zzadn0 = (zzadn)this.zzfok.get();
            this.zzfth.zza(zzadn0, s);
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd(("Failed to call onCustomClick for asset " + s + "."), remoteException0);
        }
    }

    public final void zzalw() {
        if(this.zzfth == null) {
            return;
        }
        this.zzfqy.zza("/nativeAdCustomClick", this);
    }
}

