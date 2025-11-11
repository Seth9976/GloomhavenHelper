package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class BillingClient {
    @Retention(RetentionPolicy.SOURCE)
    public @interface BillingResponseCode {
        public static final int BILLING_UNAVAILABLE = 3;
        public static final int DEVELOPER_ERROR = 5;
        public static final int ERROR = 6;
        public static final int FEATURE_NOT_SUPPORTED = -2;
        public static final int ITEM_ALREADY_OWNED = 7;
        public static final int ITEM_NOT_OWNED = 8;
        public static final int ITEM_UNAVAILABLE = 4;
        public static final int OK = 0;
        public static final int SERVICE_DISCONNECTED = -1;
        public static final int SERVICE_TIMEOUT = -3;
        public static final int SERVICE_UNAVAILABLE = 2;
        public static final int USER_CANCELED = 1;

    }

    @AnyThread
    public static final class Builder {
        private volatile String zza;
        private volatile boolean zzb;
        private final Context zzc;
        private volatile PurchasesUpdatedListener zzd;

        Builder(Context context0, zzi zzi0) {
            this.zzc = context0;
        }

        @NonNull
        public BillingClient build() {
            if(this.zzc == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            if(this.zzd == null) {
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if(!this.zzb) {
                throw new IllegalArgumentException("Support for pending purchases must be enabled. Enable this by calling \'enablePendingPurchases()\' on BillingClientBuilder.");
            }
            return new BillingClientImpl(null, this.zzb, this.zzc, this.zzd);
        }

        @NonNull
        public Builder enablePendingPurchases() {
            this.zzb = true;
            return this;
        }

        @NonNull
        public Builder setListener(@NonNull PurchasesUpdatedListener purchasesUpdatedListener0) {
            this.zzd = purchasesUpdatedListener0;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionState {
        public static final int CLOSED = 3;
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED;

    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FeatureType {
        @NonNull
        public static final String IN_APP_ITEMS_ON_VR = "inAppItemsOnVr";
        @NonNull
        public static final String PRICE_CHANGE_CONFIRMATION = "priceChangeConfirmation";
        @NonNull
        public static final String SUBSCRIPTIONS = "subscriptions";
        @NonNull
        public static final String SUBSCRIPTIONS_ON_VR = "subscriptionsOnVr";
        @NonNull
        public static final String SUBSCRIPTIONS_UPDATE = "subscriptionsUpdate";

    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SkuType {
        @NonNull
        public static final String INAPP = "inapp";
        @NonNull
        public static final String SUBS = "subs";

    }

    @AnyThread
    public abstract void acknowledgePurchase(@NonNull AcknowledgePurchaseParams arg1, @NonNull AcknowledgePurchaseResponseListener arg2);

    @AnyThread
    public abstract void consumeAsync(@NonNull ConsumeParams arg1, @NonNull ConsumeResponseListener arg2);

    @AnyThread
    public abstract void endConnection();

    @AnyThread
    public abstract int getConnectionState();

    @AnyThread
    @NonNull
    public abstract BillingResult isFeatureSupported(@NonNull String arg1);

    @AnyThread
    public abstract boolean isReady();

    @NonNull
    @UiThread
    public abstract BillingResult launchBillingFlow(@NonNull Activity arg1, @NonNull BillingFlowParams arg2);

    @UiThread
    public abstract void launchPriceChangeConfirmationFlow(@NonNull Activity arg1, @NonNull PriceChangeFlowParams arg2, @NonNull PriceChangeConfirmationListener arg3);

    @AnyThread
    @NonNull
    public static Builder newBuilder(@NonNull Context context0) {
        return new Builder(context0, null);
    }

    @AnyThread
    public abstract void queryPurchaseHistoryAsync(@NonNull String arg1, @NonNull PurchaseHistoryResponseListener arg2);

    @NonNull
    @Deprecated
    public abstract PurchasesResult queryPurchases(@NonNull String arg1);

    @AnyThread
    @zze
    public abstract void queryPurchasesAsync(@NonNull String arg1, @NonNull PurchasesResponseListener arg2);

    @AnyThread
    public abstract void querySkuDetailsAsync(@NonNull SkuDetailsParams arg1, @NonNull SkuDetailsResponseListener arg2);

    @AnyThread
    public abstract void startConnection(@NonNull BillingClientStateListener arg1);
}

