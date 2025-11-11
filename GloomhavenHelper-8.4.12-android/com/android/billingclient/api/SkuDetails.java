package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {
    private final String zza;
    private final JSONObject zzb;

    public SkuDetails(@NonNull String s) throws JSONException {
        this.zza = s;
        this.zzb = new JSONObject(this.zza);
        if(TextUtils.isEmpty(this.zzb.optString("productId"))) {
            throw new IllegalArgumentException("SKU cannot be empty.");
        }
        if(TextUtils.isEmpty(this.zzb.optString("type"))) {
            throw new IllegalArgumentException("SkuType cannot be empty.");
        }
    }

    @Override
    public boolean equals(@Nullable Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof SkuDetails ? TextUtils.equals(this.zza, ((SkuDetails)object0).zza) : false;
    }

    @NonNull
    public String getDescription() {
        return this.zzb.optString("description");
    }

    @NonNull
    public String getFreeTrialPeriod() {
        return this.zzb.optString("freeTrialPeriod");
    }

    @NonNull
    public String getIconUrl() {
        return this.zzb.optString("iconUrl");
    }

    @NonNull
    public String getIntroductoryPrice() {
        return this.zzb.optString("introductoryPrice");
    }

    public long getIntroductoryPriceAmountMicros() {
        return this.zzb.optLong("introductoryPriceAmountMicros");
    }

    public int getIntroductoryPriceCycles() {
        return this.zzb.optInt("introductoryPriceCycles");
    }

    @NonNull
    public String getIntroductoryPricePeriod() {
        return this.zzb.optString("introductoryPricePeriod");
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
    }

    // 去混淆评级： 低(20)
    @NonNull
    public String getOriginalPrice() {
        return this.zzb.has("original_price") ? this.zzb.optString("original_price") : this.getPrice();
    }

    // 去混淆评级： 低(20)
    public long getOriginalPriceAmountMicros() {
        return this.zzb.has("original_price_micros") ? this.zzb.optLong("original_price_micros") : this.getPriceAmountMicros();
    }

    @NonNull
    public String getPrice() {
        return this.zzb.optString("price");
    }

    public long getPriceAmountMicros() {
        return this.zzb.optLong("price_amount_micros");
    }

    @NonNull
    public String getPriceCurrencyCode() {
        return this.zzb.optString("price_currency_code");
    }

    @NonNull
    public String getSku() {
        return this.zzb.optString("productId");
    }

    @NonNull
    public String getSubscriptionPeriod() {
        return this.zzb.optString("subscriptionPeriod");
    }

    @NonNull
    public String getTitle() {
        return this.zzb.optString("title");
    }

    @NonNull
    public String getType() {
        return this.zzb.optString("type");
    }

    @Override
    public int hashCode() {
        return this.zza.hashCode();
    }

    @Override
    @NonNull
    public String toString() {
        String s = String.valueOf(this.zza);
        return s.length() == 0 ? new String("SkuDetails: ") : "SkuDetails: " + s;
    }

    public int zza() {
        return this.zzb.optInt("offer_type");
    }

    @NonNull
    public String zzb() {
        return this.zzb.optString("offer_id");
    }

    @NonNull
    public final String zzc() {
        return this.zzb.optString("packageName");
    }

    @NonNull
    public String zzd() {
        return this.zzb.optString("serializedDocid");
    }

    final String zze() {
        return this.zzb.optString("skuDetailsToken");
    }
}

