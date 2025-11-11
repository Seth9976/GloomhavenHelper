package com.android.billingclient.api;

import androidx.annotation.NonNull;

public final class BillingResult {
    public static class Builder {
        private int zza;
        private String zzb;

        private Builder() {
            this.zzb = "";
        }

        Builder(zzaj zzaj0) {
            this.zzb = "";
        }

        @NonNull
        public BillingResult build() {
            BillingResult billingResult0 = new BillingResult();
            billingResult0.zza = this.zza;
            billingResult0.zzb = this.zzb;
            return billingResult0;
        }

        @NonNull
        public Builder setDebugMessage(@NonNull String s) {
            this.zzb = s;
            return this;
        }

        @NonNull
        public Builder setResponseCode(int v) {
            this.zza = v;
            return this;
        }
    }

    private int zza;
    private String zzb;

    @NonNull
    public String getDebugMessage() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }
}

