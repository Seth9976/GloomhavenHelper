package com.android.billingclient.api;

import androidx.annotation.NonNull;

public final class ConsumeParams {
    public static final class Builder {
        private String zza;

        private Builder() {
        }

        Builder(zzal zzal0) {
        }

        @NonNull
        public ConsumeParams build() {
            if(this.zza == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            ConsumeParams consumeParams0 = new ConsumeParams(null);
            consumeParams0.zza = this.zza;
            return consumeParams0;
        }

        @NonNull
        public Builder setPurchaseToken(@NonNull String s) {
            this.zza = s;
            return this;
        }
    }

    private String zza;

    private ConsumeParams() {
    }

    ConsumeParams(zzal zzal0) {
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

