package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Purchase {
    @Retention(RetentionPolicy.SOURCE)
    public @interface PurchaseState {
        public static final int PENDING = 2;
        public static final int PURCHASED = 1;
        public static final int UNSPECIFIED_STATE;

    }

    public static class PurchasesResult {
        @Nullable
        private final List zza;
        private final BillingResult zzb;

        public PurchasesResult(@NonNull BillingResult billingResult0, @Nullable List list0) {
            this.zza = list0;
            this.zzb = billingResult0;
        }

        @NonNull
        public BillingResult getBillingResult() {
            return this.zzb;
        }

        @Nullable
        public List getPurchasesList() {
            return this.zza;
        }

        public int getResponseCode() {
            return this.getBillingResult().getResponseCode();
        }
    }

    private final String zza;
    private final String zzb;
    private final JSONObject zzc;

    public Purchase(@NonNull String s, @NonNull String s1) throws JSONException {
        this.zza = s;
        this.zzb = s1;
        this.zzc = new JSONObject(this.zza);
    }

    @Override
    public boolean equals(@Nullable Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Purchase)) {
            return false;
        }
        String s = ((Purchase)object0).getOriginalJson();
        if(TextUtils.equals(this.zza, s)) {
            String s1 = ((Purchase)object0).getSignature();
            return TextUtils.equals(this.zzb, s1);
        }
        return false;
    }

    @Nullable
    public AccountIdentifiers getAccountIdentifiers() {
        String s = this.zzc.optString("obfuscatedAccountId");
        String s1 = this.zzc.optString("obfuscatedProfileId");
        return s != null || s1 != null ? new AccountIdentifiers(s, s1) : null;
    }

    @NonNull
    public String getDeveloperPayload() {
        return this.zzc.optString("developerPayload");
    }

    @NonNull
    public String getOrderId() {
        return this.zzc.optString("orderId");
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
    }

    @NonNull
    public String getPackageName() {
        return this.zzc.optString("packageName");
    }

    public int getPurchaseState() {
        return this.zzc.optInt("purchaseState", 1) == 4 ? 2 : 1;
    }

    public long getPurchaseTime() {
        return this.zzc.optLong("purchaseTime");
    }

    @NonNull
    public String getPurchaseToken() {
        String s = this.zzc.optString("purchaseToken");
        return this.zzc.optString("token", s);
    }

    @zzd
    public int getQuantity() {
        return this.zzc.optInt("quantity", 1);
    }

    @NonNull
    public String getSignature() {
        return this.zzb;
    }

    @NonNull
    @zzc
    public ArrayList getSkus() {
        ArrayList arrayList0 = new ArrayList();
        if(this.zzc.has("productIds")) {
            JSONArray jSONArray0 = this.zzc.optJSONArray("productIds");
            if(jSONArray0 != null) {
                for(int v = 0; v < jSONArray0.length(); ++v) {
                    arrayList0.add(jSONArray0.optString(v));
                }
                return arrayList0;
            }
        }
        else if(this.zzc.has("productId")) {
            arrayList0.add(this.zzc.optString("productId"));
        }
        return arrayList0;
    }

    @Override
    public int hashCode() {
        return this.zza.hashCode();
    }

    public boolean isAcknowledged() {
        return this.zzc.optBoolean("acknowledged", true);
    }

    public boolean isAutoRenewing() {
        return this.zzc.optBoolean("autoRenewing");
    }

    @Override
    @NonNull
    public String toString() {
        String s = String.valueOf(this.zza);
        return s.length() == 0 ? new String("Purchase. Json: ") : "Purchase. Json: " + s;
    }
}

