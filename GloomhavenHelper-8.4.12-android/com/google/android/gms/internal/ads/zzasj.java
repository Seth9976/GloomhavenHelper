package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import jeb.synthetic.FIN;

public final class zzasj implements RewardedVideoAd {
    private final Object lock;
    @NonNull
    private final zzary zzdoy;
    private final zzasi zzdoz;
    private String zzdpa;
    private String zzdpb;
    private final Context zzur;

    public zzasj(Context context0, @Nullable zzary zzary0) {
        this.lock = new Object();
        this.zzdoz = new zzasi(null);
        if(zzary0 == null) {
            zzary0 = new zzys();
        }
        this.zzdoy = zzary0;
        this.zzur = context0.getApplicationContext();
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void destroy() {
        this.destroy(null);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void destroy(Context context0) {
        synchronized(this.lock) {
            this.zzdoz.setRewardedVideoAdListener(null);
            if(this.zzdoy == null) {
                return;
            }
            try {
                IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
                this.zzdoy.zzl(iObjectWrapper0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final Bundle getAdMetadata() {
        synchronized(this.lock) {
            if(this.zzdoy != null) {
                try {
                    return this.zzdoy.getAdMetadata();
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
        return new Bundle();
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getCustomData() {
        synchronized(this.lock) {
        }
        return this.zzdpb;
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getMediationAdapterClassName() {
        if(this.zzdoy != null) {
            try {
                return this.zzdoy.getMediationAdapterClassName();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        return null;
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final ResponseInfo getResponseInfo() {
        if(this.zzdoy != null) {
            try {
                return ResponseInfo.zza(this.zzdoy.zzkg());
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
                return ResponseInfo.zza(null);
            }
        }
        return ResponseInfo.zza(null);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        synchronized(this.lock) {
        }
        return this.zzdoz.getRewardedVideoAdListener();
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getUserId() {
        synchronized(this.lock) {
        }
        return this.zzdpa;
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final boolean isLoaded() {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(this.zzdoy == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return false;
        }
        try {
            boolean z = this.zzdoy.isLoaded();
            FIN.finallyExec$NT(v);
            return z;
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            FIN.finallyExec$NT(v);
            return false;
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void loadAd(String s, AdRequest adRequest0) {
        this.zza(s, adRequest0.zzdl());
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void loadAd(String s, PublisherAdRequest publisherAdRequest0) {
        this.zza(s, publisherAdRequest0.zzdl());
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void pause() {
        this.pause(null);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void pause(Context context0) {
        synchronized(this.lock) {
            if(this.zzdoy == null) {
                return;
            }
            try {
                IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
                this.zzdoy.zzj(iObjectWrapper0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void resume() {
        this.resume(null);
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void resume(Context context0) {
        synchronized(this.lock) {
            if(this.zzdoy == null) {
                return;
            }
            try {
                IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
                this.zzdoy.zzk(iObjectWrapper0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setAdMetadataListener(AdMetadataListener adMetadataListener0) {
        synchronized(this.lock) {
            if(this.zzdoy != null) {
                try {
                    zzue zzue0 = new zzue(adMetadataListener0);
                    this.zzdoy.zza(zzue0);
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setCustomData(String s) {
        synchronized(this.lock) {
            if(this.zzdoy != null) {
                try {
                    this.zzdoy.setCustomData(s);
                    this.zzdpb = s;
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setImmersiveMode(boolean z) {
        synchronized(this.lock) {
            if(this.zzdoy != null) {
                try {
                    this.zzdoy.setImmersiveMode(z);
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener0) {
        synchronized(this.lock) {
            this.zzdoz.setRewardedVideoAdListener(rewardedVideoAdListener0);
            if(this.zzdoy != null) {
                try {
                    this.zzdoy.zza(this.zzdoz);
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setUserId(String s) {
        synchronized(this.lock) {
            this.zzdpa = s;
            if(this.zzdoy != null) {
                try {
                    this.zzdoy.setUserId(s);
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    @Override  // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void show() {
        synchronized(this.lock) {
            if(this.zzdoy == null) {
                return;
            }
            try {
                this.zzdoy.show();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }

    private final void zza(String s, zzxr zzxr0) {
        synchronized(this.lock) {
            if(this.zzdoy == null) {
                return;
            }
            try {
                zzash zzash0 = zzui.zza(this.zzur, zzxr0, s);
                this.zzdoy.zza(zzash0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }
}

