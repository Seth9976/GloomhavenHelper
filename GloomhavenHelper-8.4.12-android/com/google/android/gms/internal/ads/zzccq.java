package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;

public final class zzccq extends zzaeq {
    @Nullable
    private final String zzfik;
    private final zzbyz zzfne;
    private final zzbyo zzfqg;

    public zzccq(@Nullable String s, zzbyo zzbyo0, zzbyz zzbyz0) {
        this.zzfik = s;
        this.zzfqg = zzbyo0;
        this.zzfne = zzbyz0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void cancelUnconfirmedClick() throws RemoteException {
        this.zzfqg.cancelUnconfirmedClick();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void destroy() throws RemoteException {
        this.zzfqg.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getAdvertiser() throws RemoteException {
        return this.zzfne.getAdvertiser();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getBody() throws RemoteException {
        return this.zzfne.getBody();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getCallToAction() throws RemoteException {
        return this.zzfne.getCallToAction();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final Bundle getExtras() throws RemoteException {
        return this.zzfne.getExtras();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getHeadline() throws RemoteException {
        return this.zzfne.getHeadline();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final List getImages() throws RemoteException {
        return this.zzfne.getImages();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getMediationAdapterClassName() throws RemoteException {
        return this.zzfik;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzaer
    public final List getMuteThisAdReasons() throws RemoteException {
        return this.isCustomMuteThisAdEnabled() ? this.zzfne.getMuteThisAdReasons() : Collections.emptyList();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getPrice() throws RemoteException {
        return this.zzfne.getPrice();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final double getStarRating() throws RemoteException {
        return this.zzfne.getStarRating();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final String getStore() throws RemoteException {
        return this.zzfne.getStore();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzxj getVideoController() throws RemoteException {
        return this.zzfne.getVideoController();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final boolean isCustomClickGestureEnabled() {
        return this.zzfqg.isCustomClickGestureEnabled();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final boolean isCustomMuteThisAdEnabled() throws RemoteException {
        return !this.zzfne.getMuteThisAdReasons().isEmpty() && this.zzfne.zzakh() != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void performClick(Bundle bundle0) throws RemoteException {
        this.zzfqg.zzf(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void recordCustomClickGesture() {
        this.zzfqg.recordCustomClickGesture();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final boolean recordImpression(Bundle bundle0) throws RemoteException {
        return this.zzfqg.zzh(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void reportTouchEvent(Bundle bundle0) throws RemoteException {
        this.zzfqg.zzg(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(zzaem zzaem0) throws RemoteException {
        this.zzfqg.zza(zzaem0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(zzwq zzwq0) throws RemoteException {
        this.zzfqg.zza(zzwq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(@Nullable zzwu zzwu0) throws RemoteException {
        this.zzfqg.zza(zzwu0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zza(zzxd zzxd0) throws RemoteException {
        this.zzfqg.zza(zzxd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzxe zzkg() throws RemoteException {
        return !((Boolean)zzvh.zzpd().zzd(zzzx.zzcsf)).booleanValue() ? null : this.zzfqg.zzahi();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final IObjectWrapper zzrj() throws RemoteException {
        return ObjectWrapper.wrap(this.zzfqg);
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzacr zzrk() throws RemoteException {
        return this.zzfne.zzrk();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzacj zzrl() throws RemoteException {
        return this.zzfne.zzrl();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final IObjectWrapper zzrm() throws RemoteException {
        return this.zzfne.zzrm();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final void zzru() {
        this.zzfqg.zzru();
    }

    @Override  // com.google.android.gms.internal.ads.zzaer
    public final zzacm zzrv() throws RemoteException {
        return this.zzfqg.zzakb().zzrv();
    }
}

