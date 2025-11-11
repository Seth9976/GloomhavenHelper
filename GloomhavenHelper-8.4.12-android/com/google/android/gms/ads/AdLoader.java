package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzach;
import com.google.android.gms.internal.ads.zzaew;
import com.google.android.gms.internal.ads.zzaex;
import com.google.android.gms.internal.ads.zzaey;
import com.google.android.gms.internal.ads.zzaez;
import com.google.android.gms.internal.ads.zzafb;
import com.google.android.gms.internal.ads.zzafd;
import com.google.android.gms.internal.ads.zzall;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzud;
import com.google.android.gms.internal.ads.zzuh;
import com.google.android.gms.internal.ads.zzui;
import com.google.android.gms.internal.ads.zzuk;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzvp;
import com.google.android.gms.internal.ads.zzvq;
import com.google.android.gms.internal.ads.zzxr;

public class AdLoader {
    public static class Builder {
        private final zzvq zzabe;
        private final Context zzur;

        private Builder(Context context0, zzvq zzvq0) {
            this.zzur = context0;
            this.zzabe = zzvq0;
        }

        public Builder(Context context0, String s) {
            this(((Context)Preconditions.checkNotNull(context0, "context cannot be null")), zzvh.zzpa().zzb(context0, s, new zzall()));
        }

        public AdLoader build() {
            try {
                zzvp zzvp0 = this.zzabe.zzpi();
                return new AdLoader(this.zzur, zzvp0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Failed to build AdLoader.", remoteException0);
                return null;
            }
        }

        @Deprecated
        public Builder forAppInstallAd(OnAppInstallAdLoadedListener nativeAppInstallAd$OnAppInstallAdLoadedListener0) {
            try {
                zzaex zzaex0 = new zzaex(nativeAppInstallAd$OnAppInstallAdLoadedListener0);
                this.zzabe.zza(zzaex0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to add app install ad listener", remoteException0);
            }
            return this;
        }

        @Deprecated
        public Builder forContentAd(OnContentAdLoadedListener nativeContentAd$OnContentAdLoadedListener0) {
            try {
                zzaew zzaew0 = new zzaew(nativeContentAd$OnContentAdLoadedListener0);
                this.zzabe.zza(zzaew0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to add content ad listener", remoteException0);
            }
            return this;
        }

        public Builder forCustomTemplateAd(String s, OnCustomTemplateAdLoadedListener nativeCustomTemplateAd$OnCustomTemplateAdLoadedListener0, OnCustomClickListener nativeCustomTemplateAd$OnCustomClickListener0) {
            try {
                zzaey zzaey0 = new zzaey(nativeCustomTemplateAd$OnCustomTemplateAdLoadedListener0);
                zzaez zzaez0 = nativeCustomTemplateAd$OnCustomClickListener0 == null ? null : new zzaez(nativeCustomTemplateAd$OnCustomClickListener0);
                this.zzabe.zza(s, zzaey0, zzaez0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to add custom template ad listener", remoteException0);
            }
            return this;
        }

        public Builder forPublisherAdView(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener0, AdSize[] arr_adSize) {
            if(arr_adSize == null || arr_adSize.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                zzuk zzuk0 = new zzuk(this.zzur, arr_adSize);
                zzafb zzafb0 = new zzafb(onPublisherAdViewLoadedListener0);
                this.zzabe.zza(zzafb0, zzuk0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to add publisher banner ad listener", remoteException0);
            }
            return this;
        }

        public Builder forUnifiedNativeAd(OnUnifiedNativeAdLoadedListener unifiedNativeAd$OnUnifiedNativeAdLoadedListener0) {
            try {
                zzafd zzafd0 = new zzafd(unifiedNativeAd$OnUnifiedNativeAdLoadedListener0);
                this.zzabe.zza(zzafd0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to add google native ad listener", remoteException0);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener0) {
            try {
                zzud zzud0 = new zzud(adListener0);
                this.zzabe.zzb(zzud0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to set AdListener.", remoteException0);
            }
            return this;
        }

        @KeepForSdk
        @Deprecated
        public Builder withCorrelator(@NonNull Correlator correlator0) {
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions0) {
            try {
                zzach zzach0 = new zzach(nativeAdOptions0);
                this.zzabe.zza(zzach0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to specify native ad options", remoteException0);
            }
            return this;
        }

        public Builder withPublisherAdViewOptions(PublisherAdViewOptions publisherAdViewOptions0) {
            try {
                this.zzabe.zza(publisherAdViewOptions0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzd("Failed to specify DFP banner ad options", remoteException0);
            }
            return this;
        }
    }

    private final zzui zzabf;
    private final zzvp zzabg;
    private final Context zzur;

    AdLoader(Context context0, zzvp zzvp0) {
        this(context0, zzvp0, zzui.zzcdb);
    }

    private AdLoader(Context context0, zzvp zzvp0, zzui zzui0) {
        this.zzur = context0;
        this.zzabg = zzvp0;
        this.zzabf = zzui0;
    }

    @Deprecated
    public String getMediationAdapterClassName() {
        try {
            return this.zzabg.zzkf();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Failed to get the mediation adapter class name.", remoteException0);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzabg.isLoading();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Failed to check if ad is loading.", remoteException0);
            return false;
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest0) {
        this.zza(adRequest0.zzdl());
    }

    public void loadAd(PublisherAdRequest publisherAdRequest0) {
        this.zza(publisherAdRequest0.zzdl());
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAds(AdRequest adRequest0, int v) {
        zzxr zzxr0 = adRequest0.zzdl();
        try {
            zzuh zzuh0 = zzui.zza(this.zzur, zzxr0);
            this.zzabg.zza(zzuh0, v);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Failed to load ads.", remoteException0);
        }
    }

    private final void zza(zzxr zzxr0) {
        try {
            zzuh zzuh0 = zzui.zza(this.zzur, zzxr0);
            this.zzabg.zzb(zzuh0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Failed to load ad.", remoteException0);
        }
    }
}

