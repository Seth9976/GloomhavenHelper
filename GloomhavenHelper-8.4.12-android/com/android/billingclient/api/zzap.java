package com.android.billingclient.api;

import android.text.TextUtils;

public final class zzap {
    private String zza;

    private zzap() {
    }

    zzap(zzao zzao0) {
    }

    public final zzap zza(String s) {
        this.zza = s;
        return this;
    }

    public final zzaq zzb() {
        if(TextUtils.isEmpty(this.zza)) {
            throw new IllegalArgumentException("SKU must be set.");
        }
        return new zzaq(this.zza, null, null);
    }
}

