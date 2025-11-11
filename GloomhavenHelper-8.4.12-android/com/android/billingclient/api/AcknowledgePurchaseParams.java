package com.android.billingclient.api;

import androidx.annotation.NonNull;

public final class AcknowledgePurchaseParams {
    public static final class Builder {
        private String zza;

        private Builder() {
        }

        Builder(zza zza0) {
        }

        @NonNull
        public AcknowledgePurchaseParams build() {
            if(this.zza == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            AcknowledgePurchaseParams acknowledgePurchaseParams0 = new AcknowledgePurchaseParams(null);
            acknowledgePurchaseParams0.zza = this.zza;
            return acknowledgePurchaseParams0;
        }

        @NonNull
        public Builder setPurchaseToken(@NonNull String s) {
            this.zza = s;
            return this;
        }
    }

    private String zza;

    private AcknowledgePurchaseParams() {
    }

    AcknowledgePurchaseParams(zza zza0) {
    }

    @NonNull
    public String getPurchaseToken() {
        return this.zza;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }
}

