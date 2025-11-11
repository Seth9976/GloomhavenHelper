package com.android.billingclient.api;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class SkuDetailsParams {
    public static class Builder {
        private String zza;
        private List zzb;

        private Builder() {
        }

        Builder(zzan zzan0) {
        }

        @NonNull
        public SkuDetailsParams build() {
            if(this.zza == null) {
                throw new IllegalArgumentException("SKU type must be set");
            }
            if(this.zzb == null) {
                throw new IllegalArgumentException("SKU list or SkuWithOffer list must be set");
            }
            SkuDetailsParams skuDetailsParams0 = new SkuDetailsParams();
            skuDetailsParams0.zza = this.zza;
            skuDetailsParams0.zzb = this.zzb;
            return skuDetailsParams0;
        }

        @NonNull
        public Builder setSkusList(@NonNull List list0) {
            this.zzb = new ArrayList(list0);
            return this;
        }

        @NonNull
        public Builder setType(@NonNull String s) {
            this.zza = s;
            return this;
        }
    }

    private String zza;
    private List zzb;

    @NonNull
    public String getSkuType() {
        return this.zza;
    }

    @NonNull
    public List getSkusList() {
        return this.zzb;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }
}

