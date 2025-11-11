package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MediationRewardedAdConfiguration extends MediationAdConfiguration {
    public MediationRewardedAdConfiguration(Context context0, String s, Bundle bundle0, Bundle bundle1, boolean z, @Nullable Location location0, int v, int v1, @Nullable String s1, String s2) {
        super(context0, s, bundle0, bundle1, z, location0, v, v1, s1, s2);
    }
}

