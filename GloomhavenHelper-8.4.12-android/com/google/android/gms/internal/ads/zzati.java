package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzati {
    private final zzast zzdpe;
    private final Context zzyz;

    public zzati(Context context0, String s) {
        this.zzyz = context0.getApplicationContext();
        this.zzdpe = zzvh.zzpa().zzc(context0, s, new zzall());
    }

    public final Bundle getAdMetadata() {
        try {
            return this.zzdpe.getAdMetadata();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return new Bundle();
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzdpe.getMediationAdapterClassName();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return "";
        }
    }

    @Nullable
    public final ResponseInfo getResponseInfo() {
        try {
            return ResponseInfo.zza(this.zzdpe.zzkg());
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return ResponseInfo.zza(null);
        }
    }

    @Nullable
    public final RewardItem getRewardItem() {
        try {
            zzass zzass0 = this.zzdpe.zzqc();
            return zzass0 == null ? null : new zzath(zzass0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return null;
        }
    }

    public final boolean isLoaded() {
        try {
            return this.zzdpe.isLoaded();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return false;
        }
    }

    public final void setOnAdMetadataChangedListener(OnAdMetadataChangedListener onAdMetadataChangedListener0) {
        try {
            zzyu zzyu0 = new zzyu(onAdMetadataChangedListener0);
            this.zzdpe.zza(zzyu0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener0) {
        try {
            zzyx zzyx0 = new zzyx(onPaidEventListener0);
            this.zzdpe.zza(zzyx0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions0) {
        try {
            zzato zzato0 = new zzato(serverSideVerificationOptions0);
            this.zzdpe.zza(zzato0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void show(Activity activity0, RewardedAdCallback rewardedAdCallback0) {
        try {
            zzatk zzatk0 = new zzatk(rewardedAdCallback0);
            this.zzdpe.zza(zzatk0);
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(activity0);
            this.zzdpe.zzh(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void show(Activity activity0, RewardedAdCallback rewardedAdCallback0, boolean z) {
        try {
            zzatk zzatk0 = new zzatk(rewardedAdCallback0);
            this.zzdpe.zza(zzatk0);
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(activity0);
            this.zzdpe.zza(iObjectWrapper0, z);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    public final void zza(zzxr zzxr0, RewardedAdLoadCallback rewardedAdLoadCallback0) {
        try {
            zzuh zzuh0 = zzui.zza(this.zzyz, zzxr0);
            zzatl zzatl0 = new zzatl(rewardedAdLoadCallback0);
            this.zzdpe.zza(zzuh0, zzatl0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

