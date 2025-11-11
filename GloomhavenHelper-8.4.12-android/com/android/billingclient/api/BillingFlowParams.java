package com.android.billingclient.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class BillingFlowParams {
    public static class Builder {
        private String zza;
        private String zzb;
        private String zzc;
        private int zzd;
        private ArrayList zze;
        private boolean zzf;

        private Builder() {
            this.zzd = 0;
        }

        Builder(zzai zzai0) {
            this.zzd = 0;
        }

        @NonNull
        public BillingFlowParams build() {
            if(this.zze == null || this.zze.isEmpty()) {
                throw new IllegalArgumentException("SkuDetails must be provided.");
            }
            ArrayList arrayList0 = this.zze;
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                if(((SkuDetails)arrayList0.get(v1)) == null) {
                    throw new IllegalArgumentException("SKU cannot be null.");
                }
            }
            if(this.zze.size() > 1) {
                SkuDetails skuDetails0 = (SkuDetails)this.zze.get(0);
                String s = skuDetails0.getType();
                ArrayList arrayList1 = this.zze;
                int v2 = arrayList1.size();
                for(int v3 = 0; v3 < v2; ++v3) {
                    SkuDetails skuDetails1 = (SkuDetails)arrayList1.get(v3);
                    if(!s.equals("play_pass_subs") && !skuDetails1.getType().equals("play_pass_subs") && !s.equals(skuDetails1.getType())) {
                        throw new IllegalArgumentException("SKUs should have the same type.");
                    }
                }
                String s1 = skuDetails0.zzc();
                ArrayList arrayList2 = this.zze;
                int v4 = arrayList2.size();
                for(int v5 = 0; v5 < v4; ++v5) {
                    SkuDetails skuDetails2 = (SkuDetails)arrayList2.get(v5);
                    if(!s.equals("play_pass_subs") && !skuDetails2.getType().equals("play_pass_subs") && !s1.equals(skuDetails2.zzc())) {
                        throw new IllegalArgumentException("All SKUs must have the same package name.");
                    }
                }
            }
            BillingFlowParams billingFlowParams0 = new BillingFlowParams(null);
            billingFlowParams0.zza = (boolean)(true ^ ((SkuDetails)this.zze.get(0)).zzc().isEmpty());
            billingFlowParams0.zzb = this.zza;
            billingFlowParams0.zzd = this.zzc;
            billingFlowParams0.zzc = this.zzb;
            billingFlowParams0.zze = this.zzd;
            billingFlowParams0.zzf = this.zze;
            billingFlowParams0.zzg = this.zzf;
            return billingFlowParams0;
        }

        @NonNull
        public Builder setObfuscatedAccountId(@NonNull String s) {
            this.zza = s;
            return this;
        }

        @NonNull
        public Builder setObfuscatedProfileId(@NonNull String s) {
            this.zzc = s;
            return this;
        }

        @NonNull
        public Builder setSkuDetails(@NonNull SkuDetails skuDetails0) {
            ArrayList arrayList0 = new ArrayList();
            arrayList0.add(skuDetails0);
            this.zze = arrayList0;
            return this;
        }

        @NonNull
        @zzc
        public Builder setSubscriptionUpdateParams(@NonNull SubscriptionUpdateParams billingFlowParams$SubscriptionUpdateParams0) {
            this.zzb = billingFlowParams$SubscriptionUpdateParams0.getOldSkuPurchaseToken();
            this.zzd = billingFlowParams$SubscriptionUpdateParams0.getReplaceSkusProrationMode();
            return this;
        }

        @NonNull
        public Builder setVrPurchaseFlow(boolean z) {
            this.zzf = z;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProrationMode {
        public static final int DEFERRED = 4;
        @zzb
        public static final int IMMEDIATE_AND_CHARGE_FULL_PRICE = 5;
        public static final int IMMEDIATE_AND_CHARGE_PRORATED_PRICE = 2;
        public static final int IMMEDIATE_WITHOUT_PRORATION = 3;
        public static final int IMMEDIATE_WITH_TIME_PRORATION = 1;
        public static final int UNKNOWN_SUBSCRIPTION_UPGRADE_DOWNGRADE_POLICY;

    }

    @zzc
    public static class SubscriptionUpdateParams {
        @zzc
        public static class com.android.billingclient.api.BillingFlowParams.SubscriptionUpdateParams.Builder {
            private String zza;
            private int zzb;

            private com.android.billingclient.api.BillingFlowParams.SubscriptionUpdateParams.Builder() {
                this.zzb = 0;
            }

            com.android.billingclient.api.BillingFlowParams.SubscriptionUpdateParams.Builder(zzai zzai0) {
                this.zzb = 0;
            }

            @NonNull
            @zzc
            public SubscriptionUpdateParams build() {
                if(TextUtils.isEmpty(this.zza) && TextUtils.isEmpty(null)) {
                    throw new IllegalArgumentException("Old SKU purchase token/id must be provided.");
                }
                SubscriptionUpdateParams billingFlowParams$SubscriptionUpdateParams0 = new SubscriptionUpdateParams(null);
                billingFlowParams$SubscriptionUpdateParams0.zza = this.zza;
                billingFlowParams$SubscriptionUpdateParams0.zzb = this.zzb;
                return billingFlowParams$SubscriptionUpdateParams0;
            }

            @NonNull
            @zzc
            public com.android.billingclient.api.BillingFlowParams.SubscriptionUpdateParams.Builder setOldSkuPurchaseToken(@NonNull String s) {
                this.zza = s;
                return this;
            }

            @NonNull
            @zzc
            public com.android.billingclient.api.BillingFlowParams.SubscriptionUpdateParams.Builder setReplaceSkusProrationMode(int v) {
                this.zzb = v;
                return this;
            }
        }

        private String zza;
        private int zzb;

        private SubscriptionUpdateParams() {
            this.zzb = 0;
        }

        SubscriptionUpdateParams(zzai zzai0) {
            this.zzb = 0;
        }

        @zzc
        String getOldSkuPurchaseToken() {
            return this.zza;
        }

        @zzc
        int getReplaceSkusProrationMode() {
            return this.zzb;
        }

        @NonNull
        @zzc
        public static com.android.billingclient.api.BillingFlowParams.SubscriptionUpdateParams.Builder newBuilder() {
            return new com.android.billingclient.api.BillingFlowParams.SubscriptionUpdateParams.Builder(null);
        }
    }

    @NonNull
    public static final String EXTRA_PARAM_KEY_ACCOUNT_ID = "accountId";
    @NonNull
    public static final String EXTRA_PARAM_KEY_OLD_SKUS = "skusToReplace";
    @NonNull
    public static final String EXTRA_PARAM_KEY_OLD_SKU_PURCHASE_TOKEN = "oldSkuPurchaseToken";
    @NonNull
    public static final String EXTRA_PARAM_KEY_REPLACE_SKUS_PRORATION_MODE = "prorationMode";
    @NonNull
    public static final String EXTRA_PARAM_KEY_VR = "vr";
    private boolean zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private int zze;
    private ArrayList zzf;
    private boolean zzg;

    private BillingFlowParams() {
        this.zze = 0;
    }

    BillingFlowParams(zzai zzai0) {
        this.zze = 0;
    }

    public boolean getVrPurchaseFlow() {
        return this.zzg;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    public final int zzb() {
        return this.zze;
    }

    @Nullable
    public final String zzf() {
        return this.zzb;
    }

    @Nullable
    public final String zzg() {
        return this.zzd;
    }

    @Nullable
    public final String zzh() {
        return this.zzc;
    }

    @NonNull
    public final ArrayList zzj() {
        ArrayList arrayList0 = new ArrayList();
        arrayList0.addAll(this.zzf);
        return arrayList0;
    }

    // 去混淆评级： 低(20)
    final boolean zzm() {
        return this.zzg || this.zzb != null || this.zzd != null || this.zze != 0 || this.zza;
    }
}

