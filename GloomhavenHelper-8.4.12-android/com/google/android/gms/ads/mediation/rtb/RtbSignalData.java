package com.google.android.gms.ads.mediation.rtb;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import java.util.List;

public class RtbSignalData {
    private final Bundle zzcct;
    @Nullable
    private final AdSize zzdi;
    private final List zzekv;
    private final Context zzur;

    public RtbSignalData(Context context0, List list0, Bundle bundle0, @Nullable AdSize adSize0) {
        this.zzur = context0;
        this.zzekv = list0;
        this.zzcct = bundle0;
        this.zzdi = adSize0;
    }

    @Nullable
    public AdSize getAdSize() {
        return this.zzdi;
    }

    @Deprecated
    public MediationConfiguration getConfiguration() {
        return this.zzekv == null || this.zzekv.size() <= 0 ? null : ((MediationConfiguration)this.zzekv.get(0));
    }

    public List getConfigurations() {
        return this.zzekv;
    }

    public Context getContext() {
        return this.zzur;
    }

    public Bundle getNetworkExtras() {
        return this.zzcct;
    }
}

