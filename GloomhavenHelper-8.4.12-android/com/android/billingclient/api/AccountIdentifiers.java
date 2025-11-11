package com.android.billingclient.api;

import androidx.annotation.Nullable;

public final class AccountIdentifiers {
    @Nullable
    private final String zza;
    @Nullable
    private final String zzb;

    AccountIdentifiers(@Nullable String s, @Nullable String s1) {
        this.zza = s;
        this.zzb = s1;
    }

    @Nullable
    public String getObfuscatedAccountId() {
        return this.zza;
    }

    @Nullable
    public String getObfuscatedProfileId() {
        return this.zzb;
    }
}

