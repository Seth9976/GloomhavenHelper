package com.google.android.gms.internal.play_billing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult.Builder;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.zzaq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;

public final class zza {
    public static final int zza;

    static {
        zza.zza = Runtime.getRuntime().availableProcessors();
    }

    public static int zza(Bundle bundle0, String s) {
        if(bundle0 == null) {
            zza.zzk(s, "Unexpected null bundle received!");
            return 6;
        }
        Object object0 = bundle0.get("RESPONSE_CODE");
        if(object0 == null) {
            zza.zzj(s, "getResponseCodeFromBundle() got null response code, assuming OK");
            return 0;
        }
        if(object0 instanceof Integer) {
            return (int)(((Integer)object0));
        }
        String s1 = object0.getClass().getName();
        zza.zzk(s, (s1.length() == 0 ? new String("Unexpected type for bundle response code: ") : "Unexpected type for bundle response code: " + s1));
        return 6;
    }

    public static Bundle zzb(AcknowledgePurchaseParams acknowledgePurchaseParams0, String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("playBillingLibraryVersion", s);
        return bundle0;
    }

    public static Bundle zzc(ConsumeParams consumeParams0, boolean z, String s) {
        Bundle bundle0 = new Bundle();
        if(z) {
            bundle0.putString("playBillingLibraryVersion", s);
        }
        return bundle0;
    }

    public static Bundle zzd(int v, boolean z, String s, @Nullable String s1, ArrayList arrayList0) {
        Bundle bundle0 = new Bundle();
        if(v >= 9) {
            bundle0.putString("playBillingLibraryVersion", s);
        }
        if(v >= 9 && z) {
            bundle0.putBoolean("enablePendingPurchases", true);
        }
        if(v >= 14) {
            ArrayList arrayList1 = new ArrayList();
            int v1 = arrayList0.size();
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                zzaq zzaq0 = (zzaq)arrayList0.get(v2);
                arrayList1.add(null);
                v3 |= !TextUtils.isEmpty(null);
            }
            if(v3 != 0) {
                bundle0.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList1);
            }
        }
        return bundle0;
    }

    public static Bundle zze(BillingFlowParams billingFlowParams0, boolean z, boolean z1, String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("playBillingLibraryVersion", s);
        if(billingFlowParams0.zzb() != 0) {
            bundle0.putInt("prorationMode", billingFlowParams0.zzb());
        }
        if(!TextUtils.isEmpty(billingFlowParams0.zzf())) {
            bundle0.putString("accountId", billingFlowParams0.zzf());
        }
        if(!TextUtils.isEmpty(billingFlowParams0.zzg())) {
            bundle0.putString("obfuscatedProfileId", billingFlowParams0.zzg());
        }
        if(billingFlowParams0.getVrPurchaseFlow()) {
            bundle0.putBoolean("vr", true);
        }
        if(!TextUtils.isEmpty(null)) {
            bundle0.putStringArrayList("skusToReplace", new ArrayList(Arrays.asList(new String[]{null})));
        }
        if(!TextUtils.isEmpty(billingFlowParams0.zzh())) {
            bundle0.putString("oldSkuPurchaseToken", billingFlowParams0.zzh());
        }
        if(!TextUtils.isEmpty(null)) {
            bundle0.putString("oldSkuPurchaseId", null);
        }
        if(!TextUtils.isEmpty(null)) {
            bundle0.putString("paymentsPurchaseParams", null);
        }
        if(z && z1) {
            bundle0.putBoolean("enablePendingPurchases", true);
        }
        return bundle0;
    }

    public static Bundle zzf(boolean z, boolean z1, String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("playBillingLibraryVersion", s);
        if(z && z1) {
            bundle0.putBoolean("enablePendingPurchases", true);
        }
        return bundle0;
    }

    public static BillingResult zzg(Intent intent0, String s) {
        if(intent0 == null) {
            zza.zzk("BillingHelper", "Got null intent!");
            Builder billingResult$Builder0 = BillingResult.newBuilder();
            billingResult$Builder0.setResponseCode(6);
            billingResult$Builder0.setDebugMessage("An internal error occurred.");
            return billingResult$Builder0.build();
        }
        Builder billingResult$Builder1 = BillingResult.newBuilder();
        billingResult$Builder1.setResponseCode(zza.zza(intent0.getExtras(), s));
        billingResult$Builder1.setDebugMessage(zza.zzh(intent0.getExtras(), s));
        return billingResult$Builder1.build();
    }

    public static String zzh(Bundle bundle0, String s) {
        if(bundle0 == null) {
            zza.zzk(s, "Unexpected null bundle received!");
            return "";
        }
        Object object0 = bundle0.get("DEBUG_MESSAGE");
        if(object0 == null) {
            zza.zzj(s, "getDebugMessageFromBundle() got null response code, assuming OK");
            return "";
        }
        if(object0 instanceof String) {
            return (String)object0;
        }
        String s1 = object0.getClass().getName();
        zza.zzk(s, (s1.length() == 0 ? new String("Unexpected type for debug message: ") : "Unexpected type for debug message: " + s1));
        return "";
    }

    public static List zzi(Bundle bundle0) {
        if(bundle0 == null) {
            return null;
        }
        ArrayList arrayList0 = bundle0.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList arrayList1 = bundle0.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        List list0 = new ArrayList();
        if(arrayList0 != null && arrayList1 != null) {
            for(int v = 0; v < arrayList0.size() && v < arrayList1.size(); ++v) {
                Purchase purchase0 = zza.zzl(((String)arrayList0.get(v)), ((String)arrayList1.get(v)));
                if(purchase0 != null) {
                    list0.add(purchase0);
                }
            }
            return list0;
        }
        zza.zzk("BillingHelper", "Couldn\'t find purchase lists, trying to find single data.");
        Purchase purchase1 = zza.zzl(bundle0.getString("INAPP_PURCHASE_DATA"), bundle0.getString("INAPP_DATA_SIGNATURE"));
        if(purchase1 == null) {
            zza.zzk("BillingHelper", "Couldn\'t find single purchase data as well.");
            return null;
        }
        list0.add(purchase1);
        return list0;
    }

    public static void zzj(String s, String s1) {
        if(Log.isLoggable(s, 2)) {
            Log.v(s, s1);
        }
    }

    public static void zzk(String s, String s1) {
        if(Log.isLoggable(s, 5)) {
            Log.w(s, s1);
        }
    }

    private static Purchase zzl(String s, String s1) {
        if(s != null && s1 != null) {
            try {
                return new Purchase(s, s1);
            }
            catch(JSONException jSONException0) {
                zza.zzk("BillingHelper", "Got JSONException while parsing purchase data: " + jSONException0);
                return null;
            }
        }
        zza.zzk("BillingHelper", "Received a bad purchase data.");
        return null;
    }
}

