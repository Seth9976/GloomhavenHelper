package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.DisplayOpenMeasurement;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzacn implements DisplayOpenMeasurement {
    private final zzadn zzcwv;

    public zzacn(zzadn zzadn0) {
        this.zzcwv = zzadn0;
        try {
            zzadn0.zzrr();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd$DisplayOpenMeasurement
    public final void setView(View view0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
            this.zzcwv.zzq(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeCustomTemplateAd$DisplayOpenMeasurement
    public final boolean start() {
        try {
            return this.zzcwv.zzrq();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return false;
        }
    }
}

