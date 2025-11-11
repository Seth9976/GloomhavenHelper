package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Collections;
import java.util.List;

public final class zzym extends zzwm {
    private zzahc zzcgb;

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final String getVersionString() {
        return "";
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void initialize() throws RemoteException {
        zzazh.zzey("The initialization is not processed because MobileAdsSettingsManager is not created successfully.");
        zzyp zzyp0 = () -> {
            zzahc zzahc0 = this.zzcgb;
            if(zzahc0 != null) {
                try {
                    zzahc0.zzd(Collections.emptyList());
                }
                catch(RemoteException remoteException0) {
                    zzazh.zzd("Could not notify onComplete event.", remoteException0);
                }
            }
        };
        zzayx.zzyy.post(zzyp0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void setAppMuted(boolean z) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void setAppVolume(float f) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzahc zzahc0) throws RemoteException {
        this.zzcgb = zzahc0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzalk zzalk0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzyw zzyw0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(String s, IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzb(IObjectWrapper iObjectWrapper0, String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzce(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzcf(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final float zzpj() throws RemoteException {
        return 1.0f;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final boolean zzpk() throws RemoteException {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final List zzpl() throws RemoteException {
        return Collections.emptyList();
    }

    // 检测为 Lambda 实现
    final void zzqb() [...]
}

