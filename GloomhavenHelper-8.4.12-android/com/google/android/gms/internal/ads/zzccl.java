package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

public final class zzccl extends zzade {
    @Nullable
    private final String zzfik;
    private final zzbyz zzfne;
    private final zzbyo zzfqg;

    public zzccl(@Nullable String s, zzbyo zzbyo0, zzbyz zzbyz0) {
        this.zzfik = s;
        this.zzfqg = zzbyo0;
        this.zzfne = zzbyz0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final void destroy() throws RemoteException {
        this.zzfqg.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getBody() throws RemoteException {
        return this.zzfne.getBody();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getCallToAction() throws RemoteException {
        return this.zzfne.getCallToAction();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final Bundle getExtras() throws RemoteException {
        return this.zzfne.getExtras();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getHeadline() throws RemoteException {
        return this.zzfne.getHeadline();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final List getImages() throws RemoteException {
        return this.zzfne.getImages();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getMediationAdapterClassName() throws RemoteException {
        return this.zzfik;
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getPrice() throws RemoteException {
        return this.zzfne.getPrice();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final double getStarRating() throws RemoteException {
        return this.zzfne.getStarRating();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final String getStore() throws RemoteException {
        return this.zzfne.getStore();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final zzxj getVideoController() throws RemoteException {
        return this.zzfne.getVideoController();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final void performClick(Bundle bundle0) throws RemoteException {
        this.zzfqg.zzf(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final boolean recordImpression(Bundle bundle0) throws RemoteException {
        return this.zzfqg.zzh(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final void reportTouchEvent(Bundle bundle0) throws RemoteException {
        this.zzfqg.zzg(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final IObjectWrapper zzrj() throws RemoteException {
        return ObjectWrapper.wrap(this.zzfqg);
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final zzacr zzrk() throws RemoteException {
        return this.zzfne.zzrk();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final zzacj zzrl() throws RemoteException {
        return this.zzfne.zzrl();
    }

    @Override  // com.google.android.gms.internal.ads.zzadf
    public final IObjectWrapper zzrm() throws RemoteException {
        return this.zzfne.zzrm();
    }
}

