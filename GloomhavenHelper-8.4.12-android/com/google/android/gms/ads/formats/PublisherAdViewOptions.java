package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.internal.ads.zzaek;
import com.google.android.gms.internal.ads.zzael;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzwe;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzza;

@Class(creator = "PublisherAdViewOptionsCreator")
public final class PublisherAdViewOptions extends AbstractSafeParcelable {
    public static final class Builder {
        private boolean zzbkp;
        @Nullable
        private AppEventListener zzbkr;
        @Nullable
        private ShouldDelayBannerRenderingListener zzbkt;

        public Builder() {
            this.zzbkp = false;
        }

        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this, null);
        }

        public final Builder setAppEventListener(AppEventListener appEventListener0) {
            this.zzbkr = appEventListener0;
            return this;
        }

        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzbkp = z;
            return this;
        }

        @KeepForSdk
        public final Builder setShouldDelayBannerRenderingListener(ShouldDelayBannerRenderingListener shouldDelayBannerRenderingListener0) {
            this.zzbkt = shouldDelayBannerRenderingListener0;
            return this;
        }

        static boolean zza(Builder publisherAdViewOptions$Builder0) {
            return publisherAdViewOptions$Builder0.zzbkp;
        }

        static AppEventListener zzb(Builder publisherAdViewOptions$Builder0) {
            return publisherAdViewOptions$Builder0.zzbkr;
        }

        static ShouldDelayBannerRenderingListener zzc(Builder publisherAdViewOptions$Builder0) {
            return publisherAdViewOptions$Builder0.zzbkt;
        }
    }

    public static final Parcelable.Creator CREATOR;
    @Field(getter = "getManualImpressionsEnabled", id = 1)
    private final boolean zzbkp;
    @Nullable
    @Field(getter = "getAppEventListenerBinder", id = 2, type = "android.os.IBinder")
    private final zzwf zzbkq;
    @Nullable
    private AppEventListener zzbkr;
    @Nullable
    @Field(getter = "getDelayedBannerAdListenerBinder", id = 3)
    private final IBinder zzbks;

    static {
        PublisherAdViewOptions.CREATOR = new zzc();
    }

    private PublisherAdViewOptions(Builder publisherAdViewOptions$Builder0) {
        this.zzbkp = Builder.zza(publisherAdViewOptions$Builder0);
        this.zzbkr = Builder.zzb(publisherAdViewOptions$Builder0);
        zzza zzza0 = null;
        this.zzbkq = this.zzbkr == null ? null : new zzuo(this.zzbkr);
        if(Builder.zzc(publisherAdViewOptions$Builder0) != null) {
            zzza0 = new zzza(Builder.zzc(publisherAdViewOptions$Builder0));
        }
        this.zzbks = zzza0;
    }

    PublisherAdViewOptions(Builder publisherAdViewOptions$Builder0, zzb zzb0) {
        this(publisherAdViewOptions$Builder0);
    }

    @Constructor
    PublisherAdViewOptions(@Param(id = 1) boolean z, @Nullable @Param(id = 2) IBinder iBinder0, @Nullable @Param(id = 3) IBinder iBinder1) {
        this.zzbkp = z;
        this.zzbkq = iBinder0 == null ? null : zzwe.zze(iBinder0);
        this.zzbks = iBinder1;
    }

    @Nullable
    public final AppEventListener getAppEventListener() {
        return this.zzbkr;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzbkp;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 1, this.getManualImpressionsEnabled());
        SafeParcelWriter.writeIBinder(parcel0, 2, (this.zzbkq == null ? null : this.zzbkq.asBinder()), false);
        SafeParcelWriter.writeIBinder(parcel0, 3, this.zzbks, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Nullable
    public final zzwf zzjr() {
        return this.zzbkq;
    }

    @Nullable
    public final zzael zzjs() {
        return zzaek.zzy(this.zzbks);
    }
}

