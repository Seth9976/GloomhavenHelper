package com.android.billingclient.api;

import java.util.Collections;
import java.util.List;

final class zzah implements AcknowledgePurchaseResponseListener, BillingClientStateListener, ConsumeResponseListener, PriceChangeConfirmationListener, PurchaseHistoryResponseListener, PurchasesResponseListener, PurchasesUpdatedListener, SkuDetailsResponseListener {
    private final long zza;

    zzah() {
        this.zza = 0L;
    }

    zzah(long v) {
        this.zza = v;
    }

    public static native void nativeOnAcknowledgePurchaseResponse(int arg0, String arg1, long arg2) {
    }

    public static native void nativeOnBillingServiceDisconnected() {
    }

    public static native void nativeOnBillingSetupFinished(int arg0, String arg1, long arg2) {
    }

    public static native void nativeOnConsumePurchaseResponse(int arg0, String arg1, String arg2, long arg3) {
    }

    public static native void nativeOnPriceChangeConfirmationResult(int arg0, String arg1, long arg2) {
    }

    public static native void nativeOnPurchaseHistoryResponse(int arg0, String arg1, PurchaseHistoryRecord[] arg2, long arg3) {
    }

    public static native void nativeOnPurchasesUpdated(int arg0, String arg1, Purchase[] arg2) {
    }

    public static native void nativeOnQueryPurchasesResponse(int arg0, String arg1, Purchase[] arg2, long arg3) {
    }

    public static native void nativeOnSkuDetailsResponse(int arg0, String arg1, SkuDetails[] arg2, long arg3) {
    }

    @Override  // com.android.billingclient.api.AcknowledgePurchaseResponseListener
    public final void onAcknowledgePurchaseResponse(BillingResult billingResult0) {
        zzah.nativeOnAcknowledgePurchaseResponse(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), this.zza);
    }

    @Override  // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingServiceDisconnected() {
        zzah.nativeOnBillingServiceDisconnected();
    }

    @Override  // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingSetupFinished(BillingResult billingResult0) {
        zzah.nativeOnBillingSetupFinished(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), this.zza);
    }

    @Override  // com.android.billingclient.api.ConsumeResponseListener
    public final void onConsumeResponse(BillingResult billingResult0, String s) {
        zzah.nativeOnConsumePurchaseResponse(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), s, this.zza);
    }

    @Override  // com.android.billingclient.api.PriceChangeConfirmationListener
    public final void onPriceChangeConfirmationResult(BillingResult billingResult0) {
        zzah.nativeOnPriceChangeConfirmationResult(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), this.zza);
    }

    @Override  // com.android.billingclient.api.PurchaseHistoryResponseListener
    public final void onPurchaseHistoryResponse(BillingResult billingResult0, List list0) {
        if(list0 == null) {
            list0 = Collections.emptyList();
        }
        zzah.nativeOnPurchaseHistoryResponse(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), ((PurchaseHistoryRecord[])list0.toArray(new PurchaseHistoryRecord[list0.size()])), this.zza);
    }

    @Override  // com.android.billingclient.api.PurchasesUpdatedListener
    public final void onPurchasesUpdated(BillingResult billingResult0, List list0) {
        if(list0 == null) {
            list0 = Collections.emptyList();
        }
        zzah.nativeOnPurchasesUpdated(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), ((Purchase[])list0.toArray(new Purchase[list0.size()])));
    }

    @Override  // com.android.billingclient.api.PurchasesResponseListener
    public final void onQueryPurchasesResponse(BillingResult billingResult0, List list0) {
        zzah.nativeOnQueryPurchasesResponse(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), ((Purchase[])list0.toArray(new Purchase[list0.size()])), this.zza);
    }

    @Override  // com.android.billingclient.api.SkuDetailsResponseListener
    public final void onSkuDetailsResponse(BillingResult billingResult0, List list0) {
        if(list0 == null) {
            list0 = Collections.emptyList();
        }
        zzah.nativeOnSkuDetailsResponse(billingResult0.getResponseCode(), billingResult0.getDebugMessage(), ((SkuDetails[])list0.toArray(new SkuDetails[list0.size()])), this.zza);
    }
}

