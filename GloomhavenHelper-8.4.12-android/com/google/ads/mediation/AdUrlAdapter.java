package com.google.ads.mediation;

import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;

@Keep
public final class AdUrlAdapter extends AbstractAdViewAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    @Override  // com.google.ads.mediation.AbstractAdViewAdapter
    public final String getAdUnitId(Bundle bundle0) {
        return "adurl";
    }

    @Override  // com.google.ads.mediation.AbstractAdViewAdapter
    protected final Bundle zza(Bundle bundle0, Bundle bundle1) {
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        bundle0.putBundle("sdk_less_server_data", bundle1);
        bundle0.putBoolean("_noRefresh", true);
        return bundle0;
    }
}

