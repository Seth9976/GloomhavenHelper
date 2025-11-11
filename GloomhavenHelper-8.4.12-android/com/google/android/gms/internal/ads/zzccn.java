package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

public final class zzccn extends zzadi {
    @Nullable
    private final String zzfik;
    private final zzbyz zzfne;
    private final zzbyo zzfqg;

    public zzccn(@Nullable String s, zzbyo zzbyo0, zzbyz zzbyz0) {
        this.zzfik = s;
        this.zzfqg = zzbyo0;
        this.zzfne = zzbyz0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final void destroy() throws RemoteException {
        this.zzfqg.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final String getAdvertiser() throws RemoteException {
        return this.zzfne.getAdvertiser();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final String getBody() throws RemoteException {
        return this.zzfne.getBody();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final String getCallToAction() throws RemoteException {
        return this.zzfne.getCallToAction();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final Bundle getExtras() throws RemoteException {
        return this.zzfne.getExtras();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final String getHeadline() throws RemoteException {
        return this.zzfne.getHeadline();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final List getImages() throws RemoteException {
        return this.zzfne.getImages();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final String getMediationAdapterClassName() throws RemoteException {
        return this.zzfik;
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final zzxj getVideoController() throws RemoteException {
        return this.zzfne.getVideoController();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final void performClick(Bundle bundle0) throws RemoteException {
        this.zzfqg.zzf(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final boolean recordImpression(Bundle bundle0) throws RemoteException {
        return this.zzfqg.zzh(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final void reportTouchEvent(Bundle bundle0) throws RemoteException {
        this.zzfqg.zzg(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final IObjectWrapper zzrj() throws RemoteException {
        return ObjectWrapper.wrap(this.zzfqg);
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final zzacj zzrl() throws RemoteException {
        return this.zzfne.zzrl();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final IObjectWrapper zzrm() throws RemoteException {
        return this.zzfne.zzrm();
    }

    @Override  // com.google.android.gms.internal.ads.zzadj
    public final zzacr zzrn() throws RemoteException {
        return this.zzfne.zzrn();
    }
}

