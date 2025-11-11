package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PurchaseHistoryRecord {
    private final String zza;
    private final String zzb;
    private final JSONObject zzc;

    public PurchaseHistoryRecord(@NonNull String s, @NonNull String s1) throws JSONException {
        this.zza = s;
        this.zzb = s1;
        this.zzc = new JSONObject(this.zza);
    }

    @Override
    public boolean equals(@Nullable Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof PurchaseHistoryRecord)) {
            return false;
        }
        String s = ((PurchaseHistoryRecord)object0).getOriginalJson();
        if(TextUtils.equals(this.zza, s)) {
            String s1 = ((PurchaseHistoryRecord)object0).getSignature();
            return TextUtils.equals(this.zzb, s1);
        }
        return false;
    }

    @NonNull
    public String getDeveloperPayload() {
        return this.zzc.optString("developerPayload");
    }

    @NonNull
    public String getOriginalJson() {
        return this.zza;
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

    @Override
    @NonNull
    public String toString() {
        String s = String.valueOf(this.zza);
        return s.length() == 0 ? new String("PurchaseHistoryRecord. Json: ") : "PurchaseHistoryRecord. Json: " + s;
    }
}

