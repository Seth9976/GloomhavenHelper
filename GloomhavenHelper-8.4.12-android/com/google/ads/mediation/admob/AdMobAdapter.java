package com.google.ads.mediation.admob;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.google.ads.mediation.AbstractAdViewAdapter;

@Keep
public final class AdMobAdapter extends AbstractAdViewAdapter {
    public static final String NEW_BUNDLE = "_newBundle";

    @Override  // com.google.ads.mediation.AbstractAdViewAdapter
    protected final Bundle zza(Bundle bundle0, Bundle bundle1) {
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        if(bundle0.getBoolean("_newBundle")) {
            bundle0 = new Bundle(bundle0);
        }
        bundle0.putInt("gw", 1);
        bundle0.putString("mad_hac", bundle1.getString("mad_hac"));
        if(!TextUtils.isEmpty(bundle1.getString("adJson"))) {
            bundle0.putString("_ad", bundle1.getString("adJson"));
        }
        bundle0.putBoolean("_noRefresh", true);
        return bundle0;
    }
}

