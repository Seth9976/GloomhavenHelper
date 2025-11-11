package com.google.android.gms.ads.rewarded;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzati;

public final class RewardedAd {
    private final zzati zzgwl;

    public RewardedAd(Context context0, String s) {
        Preconditions.checkNotNull(context0, "context cannot be null");
        Preconditions.checkNotNull(s, "adUnitID cannot be null");
        this.zzgwl = new zzati(context0, s);
    }

    public final Bundle getAdMetadata() {
        return this.zzgwl.getAdMetadata();
    }

    @Deprecated
    public final String getMediationAdapterClassName() {
        return this.zzgwl.getMediationAdapterClassName();
    }

    @Nullable
    public final ResponseInfo getResponseInfo() {
        return this.zzgwl.getResponseInfo();
    }

    @Nullable
    public final RewardItem getRewardItem() {
        return this.zzgwl.getRewardItem();
    }

    public final boolean isLoaded() {
        return this.zzgwl.isLoaded();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(AdRequest adRequest0, RewardedAdLoadCallback rewardedAdLoadCallback0) {
        this.zzgwl.zza(adRequest0.zzdl(), rewardedAdLoadCallback0);
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(PublisherAdRequest publisherAdRequest0, RewardedAdLoadCallback rewardedAdLoadCallback0) {
        this.zzgwl.zza(publisherAdRequest0.zzdl(), rewardedAdLoadCallback0);
    }

    public final void setOnAdMetadataChangedListener(OnAdMetadataChangedListener onAdMetadataChangedListener0) {
        this.zzgwl.setOnAdMetadataChangedListener(onAdMetadataChangedListener0);
    }

    public final void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener0) {
        this.zzgwl.setOnPaidEventListener(onPaidEventListener0);
    }

    public final void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions0) {
        this.zzgwl.setServerSideVerificationOptions(serverSideVerificationOptions0);
    }

    public final void show(Activity activity0, RewardedAdCallback rewardedAdCallback0) {
        this.zzgwl.show(activity0, rewardedAdCallback0);
    }

    public final void show(Activity activity0, RewardedAdCallback rewardedAdCallback0, boolean z) {
        this.zzgwl.show(activity0, rewardedAdCallback0, z);
    }
}

