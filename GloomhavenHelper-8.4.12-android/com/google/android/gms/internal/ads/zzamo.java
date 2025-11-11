package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public final class zzamo extends zzalo {
    private final MediationAdapter zzdfd;
    private final NetworkExtras zzdfe;

    public zzamo(MediationAdapter mediationAdapter0, NetworkExtras networkExtras0) {
        this.zzdfd = mediationAdapter0;
        this.zzdfe = networkExtras0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void destroy() throws RemoteException {
        try {
            this.zzdfd.destroy();
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzxj getVideoController() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final boolean isInitialized() {
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void resume() throws RemoteException {
        throw new RemoteException();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void setImmersiveMode(boolean z) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void showInterstitial() throws RemoteException {
        MediationAdapter mediationAdapter0 = this.zzdfd;
        if(!(mediationAdapter0 instanceof MediationInterstitialAdapter)) {
            String s = mediationAdapter0.getClass().getCanonicalName();
            zzazh.zzfa((s.length() == 0 ? new String("Not a MediationInterstitialAdapter: ") : "Not a MediationInterstitialAdapter: " + s));
            throw new RemoteException();
        }
        zzazh.zzeb("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.zzdfd).showInterstitial();
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void showVideo() {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzahb zzahb0, List list0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzasm zzasm0, List list0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        this.zza(iObjectWrapper0, zzuh0, s, null, zzalq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzasm zzasm0, String s1) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws RemoteException {
        MediationAdapter mediationAdapter0 = this.zzdfd;
        if(!(mediationAdapter0 instanceof MediationInterstitialAdapter)) {
            String s2 = mediationAdapter0.getClass().getCanonicalName();
            zzazh.zzfa((s2.length() == 0 ? new String("Not a MediationInterstitialAdapter: ") : "Not a MediationInterstitialAdapter: " + s2));
            throw new RemoteException();
        }
        zzazh.zzeb("Requesting interstitial ad from adapter.");
        try {
            zzamr zzamr0 = new zzamr(zzalq0);
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            MediationServerParameters mediationServerParameters0 = this.zzdl(s);
            MediationAdRequest mediationAdRequest0 = zzand.zza(zzuh0, zzamo.zzc(zzuh0));
            ((MediationInterstitialAdapter)this.zzdfd).requestInterstitialAd(zzamr0, ((Activity)object0), mediationServerParameters0, mediationAdRequest0, this.zzdfe);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, String s1, zzalq zzalq0, zzach zzach0, List list0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
        this.zza(iObjectWrapper0, zzuk0, zzuh0, s, null, zzalq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws RemoteException {
        AdSize adSize0;
        MediationAdapter mediationAdapter0 = this.zzdfd;
        if(!(mediationAdapter0 instanceof MediationBannerAdapter)) {
            String s2 = mediationAdapter0.getClass().getCanonicalName();
            zzazh.zzfa((s2.length() == 0 ? new String("Not a MediationBannerAdapter: ") : "Not a MediationBannerAdapter: " + s2));
            throw new RemoteException();
        }
        zzazh.zzeb("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter0 = (MediationBannerAdapter)this.zzdfd;
            zzamr zzamr0 = new zzamr(zzalq0);
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            MediationServerParameters mediationServerParameters0 = this.zzdl(s);
            AdSize[] arr_adSize = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
            for(int v = 0; true; ++v) {
                if(v >= 6) {
                    adSize0 = new AdSize(zzb.zza(zzuk0.width, zzuk0.height, zzuk0.zzabk));
                    break;
                }
                if(arr_adSize[v].getWidth() == zzuk0.width && arr_adSize[v].getHeight() == zzuk0.height) {
                    adSize0 = arr_adSize[v];
                    break;
                }
            }
            mediationBannerAdapter0.requestBannerAd(zzamr0, ((Activity)object0), mediationServerParameters0, adSize0, zzand.zza(zzuh0, zzamo.zzc(zzuh0)), this.zzdfe);
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(zzuh zzuh0, String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zza(zzuh zzuh0, String s, String s1) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzb(IObjectWrapper iObjectWrapper0, zzuh zzuh0, String s, zzalq zzalq0) throws RemoteException {
    }

    // 去混淆评级： 低(20)
    private static boolean zzc(zzuh zzuh0) {
        return zzuh0.zzccp || zzayx.zzxi();
    }

    private final MediationServerParameters zzdl(String s) throws RemoteException {
        HashMap hashMap0;
        try {
            if(s == null) {
                hashMap0 = new HashMap(0);
            }
            else {
                JSONObject jSONObject0 = new JSONObject(s);
                hashMap0 = new HashMap(jSONObject0.length());
                Iterator iterator0 = jSONObject0.keys();
                while(iterator0.hasNext()) {
                    Object object0 = iterator0.next();
                    hashMap0.put(((String)object0), jSONObject0.getString(((String)object0)));
                }
            }
            Class class0 = this.zzdfd.getServerParametersType();
            MediationServerParameters mediationServerParameters0 = null;
            if(class0 != null) {
                mediationServerParameters0 = (MediationServerParameters)class0.newInstance();
                mediationServerParameters0.load(hashMap0);
            }
            return mediationServerParameters0;
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzs(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final IObjectWrapper zzsp() throws RemoteException {
        MediationAdapter mediationAdapter0 = this.zzdfd;
        if(!(mediationAdapter0 instanceof MediationBannerAdapter)) {
            String s = mediationAdapter0.getClass().getCanonicalName();
            zzazh.zzfa((s.length() == 0 ? new String("Not a MediationBannerAdapter: ") : "Not a MediationBannerAdapter: " + s));
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter)mediationAdapter0).getBannerView());
        }
        catch(Throwable throwable0) {
            zzazh.zzc("", throwable0);
            throw new RemoteException();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzalx zzsq() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzaly zzsr() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle zzss() {
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final Bundle zzst() {
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final boolean zzsu() {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzadn zzsv() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final zzamd zzsw() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzalp
    public final void zzt(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }
}

