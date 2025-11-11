package com.android.billingclient.api;

import androidx.annotation.NonNull;

public class PriceChangeFlowParams {
    public static class Builder {
        private SkuDetails zza;

        @NonNull
        public PriceChangeFlowParams build() {
            if(this.zza == null) {
                throw new IllegalArgumentException("SkuDetails must be set");
            }
            PriceChangeFlowParams priceChangeFlowParams0 = new PriceChangeFlowParams();
            priceChangeFlowParams0.zza = this.zza;
            return priceChangeFlowParams0;
        }

        @NonNull
        public Builder setSkuDetails(@NonNull SkuDetails skuDetails0) {
            this.zza = skuDetails0;
            return this;
        }
    }

    private SkuDetails zza;

    @NonNull
    public SkuDetails getSkuDetails() {
        return this.zza;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }
}

