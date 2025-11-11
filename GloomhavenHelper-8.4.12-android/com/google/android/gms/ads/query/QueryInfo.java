package com.google.android.gms.ads.query;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzapw;
import com.google.android.gms.internal.ads.zzyf;

@KeepForSdk
public class QueryInfo {
    private zzyf zzgwi;

    public QueryInfo(zzyf zzyf0) {
        this.zzgwi = zzyf0;
    }

    @KeepForSdk
    public static void generate(Context context0, AdFormat adFormat0, @Nullable AdRequest adRequest0, QueryInfoGenerationCallback queryInfoGenerationCallback0) {
        new zzapw(context0, adFormat0, (adRequest0 == null ? null : adRequest0.zzdl())).zza(queryInfoGenerationCallback0);
    }

    @KeepForSdk
    public String getQuery() {
        return this.zzgwi.getQuery();
    }

    @KeepForSdk
    public Bundle getQueryBundle() {
        return this.zzgwi.getQueryBundle();
    }
}

