package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.UnifiedNativeAd.UnconfirmedClickListener;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

public final class zzaes extends UnifiedNativeAd {
    private final VideoController zzcfe;
    private final List zzcwz;
    private final zzacs zzcxa;
    private final AdChoicesInfo zzcxb;
    private final zzaer zzcxi;
    private final List zzcxj;

    public zzaes(zzaer zzaer0) {
        zzacs zzacs1;
        zzacr zzacr0;
        this.zzcwz = new ArrayList();
        this.zzcfe = new VideoController();
        this.zzcxj = new ArrayList();
        this.zzcxi = zzaer0;
        AdChoicesInfo nativeAd$AdChoicesInfo0 = null;
        try {
            List list0 = this.zzcxi.getImages();
            if(list0 != null) {
                for(Object object0: list0) {
                    if(!(object0 instanceof IBinder) || ((IBinder)object0) == null) {
                        zzacr0 = null;
                    }
                    else {
                        IInterface iInterface0 = ((IBinder)object0).queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                        zzacr0 = iInterface0 instanceof zzacr ? ((zzacr)iInterface0) : new zzact(((IBinder)object0));
                    }
                    if(zzacr0 != null) {
                        zzacs zzacs0 = new zzacs(zzacr0);
                        this.zzcwz.add(zzacs0);
                    }
                }
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        try {
            List list1 = this.zzcxi.getMuteThisAdReasons();
            if(list1 != null) {
                for(Object object1: list1) {
                    zzwu zzwu0 = object1 instanceof IBinder ? zzwx.zzg(((IBinder)object1)) : null;
                    if(zzwu0 != null) {
                        zzwz zzwz0 = new zzwz(zzwu0);
                        this.zzcxj.add(zzwz0);
                    }
                }
            }
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
        }
        try {
            zzacr zzacr1 = this.zzcxi.zzrk();
            zzacs1 = zzacr1 == null ? null : new zzacs(zzacr1);
        }
        catch(RemoteException remoteException2) {
            zzazh.zzc("", remoteException2);
            zzacs1 = null;
        }
        try {
            this.zzcxa = zzacs1;
            if(this.zzcxi.zzrl() != null) {
                nativeAd$AdChoicesInfo0 = new zzack(this.zzcxi.zzrl());
            }
        }
        catch(RemoteException remoteException3) {
            zzazh.zzc("", remoteException3);
        }
        this.zzcxb = nativeAd$AdChoicesInfo0;
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void cancelUnconfirmedClick() {
        try {
            this.zzcxi.cancelUnconfirmedClick();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Failed to cancelUnconfirmedClick", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void destroy() {
        try {
            this.zzcxi.destroy();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void enableCustomClickGesture() {
        try {
            this.zzcxi.zzru();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final AdChoicesInfo getAdChoicesInfo() {
        return this.zzcxb;
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getAdvertiser() {
        try {
            return this.zzcxi.getAdvertiser();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getBody() {
        try {
            return this.zzcxi.getBody();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getCallToAction() {
        try {
            return this.zzcxi.getCallToAction();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Bundle getExtras() {
        try {
            Bundle bundle0 = this.zzcxi.getExtras();
            if(bundle0 != null) {
                return bundle0;
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        return new Bundle();
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getHeadline() {
        try {
            return this.zzcxi.getHeadline();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Image getIcon() {
        return this.zzcxa;
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final List getImages() {
        return this.zzcwz;
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final MediaContent getMediaContent() {
        try {
            if(this.zzcxi.zzrv() != null) {
                return new zzye(this.zzcxi.zzrv());
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        return null;
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getMediationAdapterClassName() {
        try {
            return this.zzcxi.getMediationAdapterClassName();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final List getMuteThisAdReasons() {
        return this.zzcxj;
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getPrice() {
        try {
            return this.zzcxi.getPrice();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final ResponseInfo getResponseInfo() {
        try {
            return ResponseInfo.zza(this.zzcxi.zzkg());
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return ResponseInfo.zza(null);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Double getStarRating() {
        try {
            double f = this.zzcxi.getStarRating();
            return f == -1.0 ? null : f;
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getStore() {
        try {
            return this.zzcxi.getStore();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final VideoController getVideoController() {
        try {
            if(this.zzcxi.getVideoController() != null) {
                zzxj zzxj0 = this.zzcxi.getVideoController();
                this.zzcfe.zza(zzxj0);
                return this.zzcfe;
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Exception occurred while getting video controller", remoteException0);
        }
        return this.zzcfe;
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean isCustomClickGestureEnabled() {
        try {
            return this.zzcxi.isCustomClickGestureEnabled();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return false;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean isCustomMuteThisAdEnabled() {
        try {
            return this.zzcxi.isCustomMuteThisAdEnabled();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return false;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void muteThisAd(MuteThisAdReason muteThisAdReason0) {
        try {
            if(!this.isCustomMuteThisAdEnabled()) {
                zzazh.zzey("Ad is not custom mute enabled");
                return;
            }
            if(muteThisAdReason0 == null) {
                this.zzcxi.zza(null);
                return;
            }
            if(muteThisAdReason0 instanceof zzwz) {
                zzwu zzwu0 = ((zzwz)muteThisAdReason0).zzpn();
                this.zzcxi.zza(zzwu0);
                return;
            }
            zzazh.zzey("Use mute reason from UnifiedNativeAd.getMuteThisAdReasons() or null");
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void performClick(Bundle bundle0) {
        try {
            this.zzcxi.performClick(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void recordCustomClickGesture() {
        try {
            this.zzcxi.recordCustomClickGesture();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean recordImpression(Bundle bundle0) {
        try {
            return this.zzcxi.recordImpression(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return false;
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void reportTouchEvent(Bundle bundle0) {
        try {
            this.zzcxi.reportTouchEvent(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setMuteThisAdListener(MuteThisAdListener muteThisAdListener0) {
        try {
            zzwv zzwv0 = new zzwv(muteThisAdListener0);
            this.zzcxi.zza(zzwv0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener0) {
        try {
            zzyx zzyx0 = new zzyx(onPaidEventListener0);
            this.zzcxi.zza(zzyx0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Failed to setOnPaidEventListener", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setUnconfirmedClickListener(UnconfirmedClickListener unifiedNativeAd$UnconfirmedClickListener0) {
        try {
            zzafc zzafc0 = new zzafc(unifiedNativeAd$UnconfirmedClickListener0);
            this.zzcxi.zza(zzafc0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Failed to setUnconfirmedClickListener", remoteException0);
        }
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    protected final Object zzjo() {
        return this.zzrj();
    }

    @Override  // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Object zzjt() {
        try {
            IObjectWrapper iObjectWrapper0 = this.zzcxi.zzrm();
            if(iObjectWrapper0 != null) {
                return ObjectWrapper.unwrap(iObjectWrapper0);
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        }
        return null;
    }

    private final IObjectWrapper zzrj() {
        try {
            return this.zzcxi.zzrj();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            return null;
        }
    }
}

