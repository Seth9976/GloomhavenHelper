package com.google.android.gms.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zztz;
import com.google.android.gms.internal.ads.zzxv;

public final class InterstitialAd {
    private final zzxv zzabs;

    public InterstitialAd(Context context0) {
        this.zzabs = new zzxv(context0);
        Preconditions.checkNotNull(context0, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zzabs.getAdListener();
    }

    public final Bundle getAdMetadata() {
        return this.zzabs.getAdMetadata();
    }

    public final String getAdUnitId() {
        return this.zzabs.getAdUnitId();
    }

    @Deprecated
    public final String getMediationAdapterClassName() {
        return this.zzabs.getMediationAdapterClassName();
    }

    @Nullable
    public final ResponseInfo getResponseInfo() {
        return this.zzabs.getResponseInfo();
    }

    public final boolean isLoaded() {
        return this.zzabs.isLoaded();
    }

    public final boolean isLoading() {
        return this.zzabs.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(AdRequest adRequest0) {
        this.zzabs.zza(adRequest0.zzdl());
    }

    public final void setAdListener(AdListener adListener0) {
        this.zzabs.setAdListener(adListener0);
        if(adListener0 != null && adListener0 instanceof zztz) {
            this.zzabs.zza(((zztz)adListener0));
            return;
        }
        if(adListener0 == null) {
            this.zzabs.zza(null);
        }
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener0) {
        this.zzabs.setAdMetadataListener(adMetadataListener0);
    }

    public final void setAdUnitId(String s) {
        this.zzabs.setAdUnitId(s);
    }

    public final void setImmersiveMode(boolean z) {
        this.zzabs.setImmersiveMode(z);
    }

    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener0) {
        this.zzabs.setOnPaidEventListener(onPaidEventListener0);
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener0) {
        this.zzabs.setRewardedVideoAdListener(rewardedVideoAdListener0);
    }

    public final void show() {
        this.zzabs.show();
    }

    public final void zzd(boolean z) {
        this.zzabs.zzd(true);
    }
}

