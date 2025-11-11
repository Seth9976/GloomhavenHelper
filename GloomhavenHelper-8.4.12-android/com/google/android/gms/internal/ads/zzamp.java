package com.google.android.gms.internal.ads;

import android.location.Location;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzamp implements NativeMediationAdRequest {
    private final String zzabx;
    private final int zzccn;
    private final boolean zzccy;
    private final int zzdeq;
    private final int zzder;
    private final zzach zzdff;
    private final List zzdfg;
    private final Map zzdfh;
    private final Date zzmg;
    private final Set zzmi;
    private final boolean zzmj;
    private final Location zzmk;

    public zzamp(@Nullable Date date0, int v, @Nullable Set set0, @Nullable Location location0, boolean z, int v1, zzach zzach0, List list0, boolean z1, int v2, String s) {
        this.zzmg = date0;
        this.zzccn = v;
        this.zzmi = set0;
        this.zzmk = location0;
        this.zzmj = z;
        this.zzdeq = v1;
        this.zzdff = zzach0;
        this.zzccy = z1;
        this.zzder = v2;
        this.zzabx = s;
        this.zzdfg = new ArrayList();
        this.zzdfh = new HashMap();
        if(list0 != null) {
            for(Object object0: list0) {
                String s1 = (String)object0;
                if(s1.startsWith("custom:")) {
                    String[] arr_s = s1.split(":", 3);
                    if(arr_s.length != 3) {
                        continue;
                    }
                    if("true".equals(arr_s[2])) {
                        this.zzdfh.put(arr_s[1], Boolean.TRUE);
                    }
                    else {
                        if(!"false".equals(arr_s[2])) {
                            continue;
                        }
                        this.zzdfh.put(arr_s[1], Boolean.FALSE);
                    }
                }
                else {
                    this.zzdfg.add(s1);
                }
            }
        }
    }

    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final float getAdVolume() {
        return zzxu.zzpy().zzpj();
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final Date getBirthday() {
        return this.zzmg;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final int getGender() {
        return this.zzccn;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Set getKeywords() {
        return this.zzmi;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Location getLocation() {
        return this.zzmk;
    }

    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final NativeAdOptions getNativeAdOptions() {
        if(this.zzdff == null) {
            return null;
        }
        Builder nativeAdOptions$Builder0 = new Builder().setReturnUrlsForImageAssets(this.zzdff.zzcws).setImageOrientation(this.zzdff.zzbke).setRequestMultipleImages(this.zzdff.zzbkg);
        if(this.zzdff.versionCode >= 2) {
            nativeAdOptions$Builder0.setAdChoicesPlacement(this.zzdff.zzbkh);
        }
        if(this.zzdff.versionCode >= 3 && this.zzdff.zzcwt != null) {
            nativeAdOptions$Builder0.setVideoOptions(new VideoOptions(this.zzdff.zzcwt));
        }
        return nativeAdOptions$Builder0.build();
    }

    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isAdMuted() {
        return zzxu.zzpy().zzpk();
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isAppInstallAdRequested() {
        return this.zzdfg != null && (this.zzdfg.contains("2") || this.zzdfg.contains("6"));
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isContentAdRequested() {
        return this.zzdfg != null && (this.zzdfg.contains("1") || this.zzdfg.contains("6"));
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzccy;
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final boolean isTesting() {
        return this.zzmj;
    }

    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isUnifiedNativeAdRequested() {
        return this.zzdfg != null && this.zzdfg.contains("6");
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdRequest
    public final int taggedForChildDirectedTreatment() {
        return this.zzdeq;
    }

    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean zzte() {
        return this.zzdfg != null && this.zzdfg.contains("3");
    }

    @Override  // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final Map zztf() {
        return this.zzdfh;
    }
}

