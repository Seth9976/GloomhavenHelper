package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing.zza;
import com.google.android.gms.internal.play_billing.zzd;
import com.google.android.gms.internal.play_billing.zzp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

class BillingClientImpl extends BillingClient {
    private volatile int zza;
    private final String zzb;
    private final Handler zzc;
    private volatile zzh zzd;
    private Context zze;
    private Context zzf;
    private volatile zzd zzg;
    private volatile zzaf zzh;
    private boolean zzi;
    private boolean zzj;
    private int zzk;
    private boolean zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private ExecutorService zzu;

    private BillingClientImpl(Activity activity0, boolean z, String s) {
        this(activity0.getApplicationContext(), z, new zzah(), s, null);
    }

    @AnyThread
    private BillingClientImpl(Context context0, boolean z, PurchasesUpdatedListener purchasesUpdatedListener0, String s, String s1) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = s;
        this.initialize(context0, purchasesUpdatedListener0, z);
    }

    private BillingClientImpl(String s) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = s;
    }

    @AnyThread
    BillingClientImpl(@Nullable String s, boolean z, Context context0, PurchasesUpdatedListener purchasesUpdatedListener0) {
        String s1 = "4.0.0";
        try {
            s1 = (String)Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        }
        catch(Exception unused_ex) {
        }
        this(context0, z, purchasesUpdatedListener0, s1, null);
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final void acknowledgePurchase(AcknowledgePurchaseParams acknowledgePurchaseParams0, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener0) {
        if(!this.isReady()) {
            acknowledgePurchaseResponseListener0.onAcknowledgePurchaseResponse(zzak.zzq);
            return;
        }
        if(TextUtils.isEmpty(acknowledgePurchaseParams0.getPurchaseToken())) {
            zza.zzk("BillingClient", "Please provide a valid purchase token.");
            acknowledgePurchaseResponseListener0.onAcknowledgePurchaseResponse(zzak.zzk);
            return;
        }
        if(!this.zzn) {
            acknowledgePurchaseResponseListener0.onAcknowledgePurchaseResponse(zzak.zzb);
            return;
        }
        if(this.zzH(new zzk(this, acknowledgePurchaseParams0, acknowledgePurchaseResponseListener0), 30000L, new zzj(acknowledgePurchaseResponseListener0), this.zzD()) == null) {
            acknowledgePurchaseResponseListener0.onAcknowledgePurchaseResponse(this.zzF());
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final void consumeAsync(ConsumeParams consumeParams0, ConsumeResponseListener consumeResponseListener0) {
        if(!this.isReady()) {
            consumeResponseListener0.onConsumeResponse(zzak.zzq, consumeParams0.getPurchaseToken());
            return;
        }
        if(this.zzH(new zzl(this, consumeParams0, consumeResponseListener0), 30000L, new zzr(consumeResponseListener0, consumeParams0), this.zzD()) == null) {
            consumeResponseListener0.onConsumeResponse(this.zzF(), consumeParams0.getPurchaseToken());
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final void endConnection() {
        try {
            this.zze = null;
            this.zzd.zzc();
            if(this.zzh != null) {
                this.zzh.zzc();
            }
            if(this.zzh != null && this.zzg != null) {
                zza.zzj("BillingClient", "Unbinding from service.");
                this.zzf.unbindService(this.zzh);
                this.zzh = null;
            }
            this.zzg = null;
            ExecutorService executorService0 = this.zzu;
            if(executorService0 != null) {
                executorService0.shutdownNow();
                this.zzu = null;
            }
        }
        catch(Exception exception0) {
            zza.zzk("BillingClient", "There was an exception while ending connection: " + exception0);
        }
        finally {
            this.zza = 3;
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final int getConnectionState() {
        return this.zza;
    }

    private void initialize(Context context0, PurchasesUpdatedListener purchasesUpdatedListener0, boolean z) {
        this.zzf = context0.getApplicationContext();
        this.zzd = new zzh(this.zzf, purchasesUpdatedListener0);
        this.zze = context0;
        this.zzt = z;
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final BillingResult isFeatureSupported(String s) {
        if(!this.isReady()) {
            return zzak.zzq;
        }
        switch(s) {
            case "aaa": {
                return this.zzr ? zzak.zzp : zzak.zzi;
            }
            case "bbb": {
                return this.zzp ? zzak.zzp : zzak.zzi;
            }
            case "ccc": {
                return this.zzs ? zzak.zzp : zzak.zzi;
            }
            case "ddd": {
                return this.zzq ? zzak.zzp : zzak.zzi;
            }
            case "eee": {
                return this.zzs ? zzak.zzp : zzak.zzi;
            }
            case "inAppItemsOnVr": {
                return this.zzG("inapp");
            }
            case "priceChangeConfirmation": {
                return this.zzm ? zzak.zzp : zzak.zzi;
            }
            case "subscriptions": {
                return this.zzi ? zzak.zzp : zzak.zzi;
            }
            case "subscriptionsOnVr": {
                return this.zzG("subs");
            }
            case "subscriptionsUpdate": {
                return this.zzj ? zzak.zzp : zzak.zzi;
            }
            default: {
                String s1 = String.valueOf(s);
                zza.zzk("BillingClient", (s1.length() == 0 ? new String("Unsupported feature: ") : "Unsupported feature: " + s1));
                return zzak.zzv;
            }
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final boolean isReady() {
        return this.zza == 2 && this.zzg != null && this.zzh != null;
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final BillingResult launchBillingFlow(Activity activity0, BillingFlowParams billingFlowParams0) {
        Future future0;
        int v9;
        boolean z;
        String s3;
        if(!this.isReady()) {
            this.zzE(zzak.zzq);
            return zzak.zzq;
        }
        ArrayList arrayList0 = billingFlowParams0.zzj();
        Object object0 = arrayList0.get(0);
        String s = ((SkuDetails)object0).getType();
        if(s.equals("subs") && !this.zzi) {
            zza.zzk("BillingClient", "Current client doesn\'t support subscriptions.");
            this.zzE(zzak.zzs);
            return zzak.zzs;
        }
        if(billingFlowParams0.zzm() && !this.zzl) {
            zza.zzk("BillingClient", "Current client doesn\'t support extra params for buy intent.");
            this.zzE(zzak.zzh);
            return zzak.zzh;
        }
        if(arrayList0.size() > 1 && !this.zzs) {
            zza.zzk("BillingClient", "Current client doesn\'t support multi-item purchases.");
            this.zzE(zzak.zzu);
            return zzak.zzu;
        }
        String s1 = "";
        for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
            String s2 = s1 + arrayList0.get(v1);
            s1 = v1 >= arrayList0.size() - 1 ? s2 : s2 + ", ";
        }
        zza.zzj("BillingClient", "Constructing buy intent for " + s1 + ", item type: " + s);
        if(this.zzl) {
            Bundle bundle0 = zza.zze(billingFlowParams0, this.zzn, this.zzt, this.zzb);
            ArrayList arrayList1 = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            int v2 = arrayList0.size();
            int v3 = 0;
            int v4 = 0;
            int v5 = 0;
            int v6 = 0;
            for(int v = 0; v < v2; ++v) {
                SkuDetails skuDetails0 = (SkuDetails)arrayList0.get(v);
                if(!skuDetails0.zze().isEmpty()) {
                    arrayList1.add(skuDetails0.zze());
                }
                try {
                    s3 = "";
                    s3 = new JSONObject(skuDetails0.getOriginalJson()).optString("offer_id_token");
                }
                catch(JSONException unused_ex) {
                }
                String s4 = skuDetails0.zzb();
                int v7 = skuDetails0.zza();
                String s5 = skuDetails0.zzd();
                arrayList2.add(s3);
                v3 |= !TextUtils.isEmpty(s3);
                arrayList3.add(s4);
                v4 |= !TextUtils.isEmpty(s4);
                arrayList4.add(v7);
                v5 |= (v7 == 0 ? 0 : 1);
                v6 |= !TextUtils.isEmpty(s5);
                arrayList5.add(s5);
            }
            if(!arrayList1.isEmpty()) {
                bundle0.putStringArrayList("skuDetailsTokens", arrayList1);
            }
            if(v3 != 0) {
                if(!this.zzq) {
                    this.zzE(zzak.zzi);
                    return zzak.zzi;
                }
                bundle0.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList2);
            }
            if(v4 != 0) {
                bundle0.putStringArrayList("SKU_OFFER_ID_LIST", arrayList3);
            }
            if(v5 != 0) {
                bundle0.putIntegerArrayList("SKU_OFFER_TYPE_LIST", arrayList4);
            }
            if(v6 != 0) {
                bundle0.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
            }
            if(TextUtils.isEmpty(((SkuDetails)object0).zzc())) {
                z = false;
            }
            else {
                bundle0.putString("skuPackageName", ((SkuDetails)object0).zzc());
                z = true;
            }
            if(!TextUtils.isEmpty(null)) {
                bundle0.putString("accountName", null);
            }
            if(arrayList0.size() > 1) {
                ArrayList arrayList6 = new ArrayList(arrayList0.size() - 1);
                ArrayList arrayList7 = new ArrayList(arrayList0.size() - 1);
                for(int v8 = 1; v8 < arrayList0.size(); ++v8) {
                    arrayList6.add(((SkuDetails)arrayList0.get(v8)).getSku());
                    arrayList7.add(((SkuDetails)arrayList0.get(v8)).getType());
                }
                bundle0.putStringArrayList("additionalSkus", arrayList6);
                bundle0.putStringArrayList("additionalSkuTypes", arrayList7);
            }
            if(!TextUtils.isEmpty(activity0.getIntent().getStringExtra("PROXY_PACKAGE"))) {
                String s6 = activity0.getIntent().getStringExtra("PROXY_PACKAGE");
                bundle0.putString("proxyPackage", s6);
                try {
                    bundle0.putString("proxyPackageVersion", this.zzf.getPackageManager().getPackageInfo(s6, 0).versionName);
                }
                catch(PackageManager.NameNotFoundException unused_ex) {
                    bundle0.putString("proxyPackageVersion", "package not found");
                }
            }
            if(this.zzr && z) {
                v9 = 15;
            }
            else if(this.zzn) {
                v9 = 9;
            }
            else {
                v9 = billingFlowParams0.getVrPurchaseFlow() ? 7 : 6;
            }
            future0 = this.zzH(() -> this.zzg.zzg(v9, "com.esotericsoftware.gloomhavenhelper", ((SkuDetails)object0).getSku(), s, null, bundle0), 5000L, null, this.zzc);
        }
        else {
            future0 = this.zzH(() -> this.zzg.zzf(3, "com.esotericsoftware.gloomhavenhelper", ((SkuDetails)object0).getSku(), s, null), 5000L, null, this.zzc);
        }
        try {
            Bundle bundle1 = (Bundle)future0.get(5000L, TimeUnit.MILLISECONDS);
            int v10 = zza.zza(bundle1, "BillingClient");
            String s7 = zza.zzh(bundle1, "BillingClient");
            if(v10 != 0) {
                zza.zzk("BillingClient", "Unable to buy item, Error response code: " + v10);
                Builder billingResult$Builder0 = BillingResult.newBuilder();
                billingResult$Builder0.setResponseCode(v10);
                billingResult$Builder0.setDebugMessage(s7);
                BillingResult billingResult0 = billingResult$Builder0.build();
                this.zzE(billingResult0);
                return billingResult0;
            }
            Intent intent0 = new Intent(activity0, ProxyBillingActivity.class);
            intent0.putExtra("BUY_INTENT", ((PendingIntent)bundle1.getParcelable("BUY_INTENT")));
            activity0.startActivity(intent0);
            return zzak.zzp;
        }
        catch(TimeoutException | CancellationException unused_ex) {
            zza.zzk("BillingClient", "Time out while launching billing flow: ; for sku: " + s1 + "; try to reconnect");
            this.zzE(zzak.zzr);
            return zzak.zzr;
        }
        catch(Exception unused_ex) {
            zza.zzk("BillingClient", "Exception while launching billing flow: ; for sku: " + s1 + "; try to reconnect");
            this.zzE(zzak.zzq);
            return zzak.zzq;
        }
    }

    private int launchBillingFlowCpp(Activity activity0, BillingFlowParams billingFlowParams0) {
        return this.launchBillingFlow(activity0, billingFlowParams0).getResponseCode();
    }

    private void launchPriceChangeConfirmationFlow(Activity activity0, PriceChangeFlowParams priceChangeFlowParams0, long v) {
        this.launchPriceChangeConfirmationFlow(activity0, priceChangeFlowParams0, new zzah(v));
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final void launchPriceChangeConfirmationFlow(Activity activity0, PriceChangeFlowParams priceChangeFlowParams0, PriceChangeConfirmationListener priceChangeConfirmationListener0) {
        if(!this.isReady()) {
            this.zzI(zzak.zzq, priceChangeConfirmationListener0);
            return;
        }
        if(priceChangeFlowParams0 != null && priceChangeFlowParams0.getSkuDetails() != null) {
            String s = priceChangeFlowParams0.getSkuDetails().getSku();
            if(s == null) {
                zza.zzk("BillingClient", "Please fix the input params. priceChangeFlowParams must contain valid sku.");
                this.zzI(zzak.zzn, priceChangeConfirmationListener0);
                return;
            }
            if(!this.zzm) {
                zza.zzk("BillingClient", "Current client doesn\'t support price change confirmation flow.");
                this.zzI(zzak.zzi, priceChangeConfirmationListener0);
                return;
            }
            Bundle bundle0 = new Bundle();
            bundle0.putString("playBillingLibraryVersion", this.zzb);
            bundle0.putBoolean("subs_price_change", true);
            Future future0 = this.zzH(() -> this.zzg.zzm(8, "com.esotericsoftware.gloomhavenhelper", s, "subs", bundle0), 5000L, null, this.zzc);
            try {
                Bundle bundle1 = (Bundle)future0.get(5000L, TimeUnit.MILLISECONDS);
                int v = zza.zza(bundle1, "BillingClient");
                String s1 = zza.zzh(bundle1, "BillingClient");
                Builder billingResult$Builder0 = BillingResult.newBuilder();
                billingResult$Builder0.setResponseCode(v);
                billingResult$Builder0.setDebugMessage(s1);
                BillingResult billingResult0 = billingResult$Builder0.build();
                if(v != 0) {
                    zza.zzk("BillingClient", "Unable to launch price change flow, error response code: " + v);
                    this.zzI(billingResult0, priceChangeConfirmationListener0);
                    return;
                }
                zzy zzy0 = new zzy(this, this.zzc, priceChangeConfirmationListener0);
                Intent intent0 = new Intent(activity0, ProxyBillingActivity.class);
                intent0.putExtra("SUBS_MANAGEMENT_INTENT", ((PendingIntent)bundle1.getParcelable("SUBS_MANAGEMENT_INTENT")));
                intent0.putExtra("result_receiver", zzy0);
                activity0.startActivity(intent0);
            }
            catch(TimeoutException | CancellationException unused_ex) {
                zza.zzk("BillingClient", "Time out while launching Price Change Flow for sku: " + s + "; try to reconnect");
                this.zzI(zzak.zzr, priceChangeConfirmationListener0);
            }
            catch(Exception unused_ex) {
                zza.zzk("BillingClient", "Exception caught while launching Price Change Flow for sku: " + s + "; try to reconnect");
                this.zzI(zzak.zzq, priceChangeConfirmationListener0);
            }
            return;
        }
        zza.zzk("BillingClient", "Please fix the input params. priceChangeFlowParams must contain valid sku.");
        this.zzI(zzak.zzn, priceChangeConfirmationListener0);
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final void queryPurchaseHistoryAsync(String s, PurchaseHistoryResponseListener purchaseHistoryResponseListener0) {
        if(!this.isReady()) {
            purchaseHistoryResponseListener0.onPurchaseHistoryResponse(zzak.zzq, null);
            return;
        }
        if(this.zzH(new zzab(this, s, purchaseHistoryResponseListener0), 30000L, new zzt(purchaseHistoryResponseListener0), this.zzD()) == null) {
            purchaseHistoryResponseListener0.onPurchaseHistoryResponse(this.zzF(), null);
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final PurchasesResult queryPurchases(String s) {
        if(!this.isReady()) {
            return new PurchasesResult(zzak.zzq, null);
        }
        if(TextUtils.isEmpty(s)) {
            zza.zzk("BillingClient", "Please provide a valid SKU type.");
            return new PurchasesResult(zzak.zzg, null);
        }
        Future future0 = this.zzH(new zzz(this, s), 5000L, null, this.zzc);
        try {
            return (PurchasesResult)future0.get(5000L, TimeUnit.MILLISECONDS);
        }
        catch(TimeoutException | CancellationException unused_ex) {
            return new PurchasesResult(zzak.zzr, null);
        }
        catch(Exception unused_ex) {
            return new PurchasesResult(zzak.zzl, null);
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    @zze
    public void queryPurchasesAsync(String s, PurchasesResponseListener purchasesResponseListener0) {
        if(!this.isReady()) {
            purchasesResponseListener0.onQueryPurchasesResponse(zzak.zzq, zzp.zzg());
            return;
        }
        if(TextUtils.isEmpty(s)) {
            zza.zzk("BillingClient", "Please provide a valid SKU type.");
            purchasesResponseListener0.onQueryPurchasesResponse(zzak.zzg, zzp.zzg());
            return;
        }
        if(this.zzH(new zzaa(this, s, purchasesResponseListener0), 30000L, new zzu(purchasesResponseListener0), this.zzD()) == null) {
            purchasesResponseListener0.onQueryPurchasesResponse(this.zzF(), zzp.zzg());
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final void querySkuDetailsAsync(SkuDetailsParams skuDetailsParams0, SkuDetailsResponseListener skuDetailsResponseListener0) {
        if(!this.isReady()) {
            skuDetailsResponseListener0.onSkuDetailsResponse(zzak.zzq, null);
            return;
        }
        String s = skuDetailsParams0.getSkuType();
        List list0 = skuDetailsParams0.getSkusList();
        if(TextUtils.isEmpty(s)) {
            zza.zzk("BillingClient", "Please fix the input params. SKU type can\'t be empty.");
            skuDetailsResponseListener0.onSkuDetailsResponse(zzak.zzg, null);
            return;
        }
        if(list0 != null) {
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: list0) {
                zzap zzap0 = new zzap(null);
                zzap0.zza(((String)object0));
                arrayList0.add(zzap0.zzb());
            }
            if(this.zzH(new com.android.billingclient.api.zzp(this, s, arrayList0, null, skuDetailsResponseListener0), 30000L, new zzv(skuDetailsResponseListener0), this.zzD()) == null) {
                skuDetailsResponseListener0.onSkuDetailsResponse(this.zzF(), null);
            }
            return;
        }
        zza.zzk("BillingClient", "Please fix the input params. The list of SKUs can\'t be empty - set SKU list or SkuWithOffer list.");
        skuDetailsResponseListener0.onSkuDetailsResponse(zzak.zzf, null);
    }

    private void startConnection(long v) {
        zzah zzah0 = new zzah(v);
        if(this.isReady()) {
            zza.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            zzah0.onBillingSetupFinished(zzak.zzp);
            return;
        }
        switch(this.zza) {
            case 1: {
                zza.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
                zzah0.onBillingSetupFinished(zzak.zzd);
                return;
            }
            case 3: {
                zza.zzk("BillingClient", "Client was already closed and can\'t be reused. Please create another instance.");
                zzah0.onBillingSetupFinished(zzak.zzq);
                return;
            }
            default: {
                this.zza = 1;
                this.zzd.zzd();
                zza.zzj("BillingClient", "Starting in-app billing setup.");
                this.zzh = new zzaf(this, zzah0, null);
                Intent intent0 = new Intent("com.android.vending.billing.InAppBillingService.BIND");
                intent0.setPackage("com.android.vending");
                List list0 = this.zzf.getPackageManager().queryIntentServices(intent0, 0);
                if(list0 != null && !list0.isEmpty()) {
                    ResolveInfo resolveInfo0 = (ResolveInfo)list0.get(0);
                    if(resolveInfo0.serviceInfo != null) {
                        String s = resolveInfo0.serviceInfo.packageName;
                        String s1 = resolveInfo0.serviceInfo.name;
                        if(!"com.android.vending".equals(s) || s1 == null) {
                            zza.zzk("BillingClient", "The device doesn\'t have valid Play Store.");
                        }
                        else {
                            ComponentName componentName0 = new ComponentName(s, s1);
                            Intent intent1 = new Intent(intent0);
                            intent1.setComponent(componentName0);
                            intent1.putExtra("playBillingLibraryVersion", this.zzb);
                            if(this.zzf.bindService(intent1, this.zzh, 1)) {
                                zza.zzj("BillingClient", "Service was bonded successfully.");
                                return;
                            }
                            zza.zzk("BillingClient", "Connection to Billing service is blocked.");
                        }
                    }
                }
                this.zza = 0;
                zza.zzj("BillingClient", "Billing service unavailable on device.");
                zzah0.onBillingSetupFinished(zzak.zzc);
            }
        }
    }

    @Override  // com.android.billingclient.api.BillingClient
    public final void startConnection(BillingClientStateListener billingClientStateListener0) {
        if(this.isReady()) {
            zza.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            billingClientStateListener0.onBillingSetupFinished(zzak.zzp);
            return;
        }
        switch(this.zza) {
            case 1: {
                zza.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
                billingClientStateListener0.onBillingSetupFinished(zzak.zzd);
                return;
            }
            case 3: {
                zza.zzk("BillingClient", "Client was already closed and can\'t be reused. Please create another instance.");
                billingClientStateListener0.onBillingSetupFinished(zzak.zzq);
                return;
            }
            default: {
                this.zza = 1;
                this.zzd.zzd();
                zza.zzj("BillingClient", "Starting in-app billing setup.");
                this.zzh = new zzaf(this, billingClientStateListener0, null);
                Intent intent0 = new Intent("com.android.vending.billing.InAppBillingService.BIND");
                intent0.setPackage("com.android.vending");
                List list0 = this.zzf.getPackageManager().queryIntentServices(intent0, 0);
                if(list0 != null && !list0.isEmpty()) {
                    ResolveInfo resolveInfo0 = (ResolveInfo)list0.get(0);
                    if(resolveInfo0.serviceInfo != null) {
                        String s = resolveInfo0.serviceInfo.packageName;
                        String s1 = resolveInfo0.serviceInfo.name;
                        if(!"com.android.vending".equals(s) || s1 == null) {
                            zza.zzk("BillingClient", "The device doesn\'t have valid Play Store.");
                        }
                        else {
                            ComponentName componentName0 = new ComponentName(s, s1);
                            Intent intent1 = new Intent(intent0);
                            intent1.setComponent(componentName0);
                            intent1.putExtra("playBillingLibraryVersion", this.zzb);
                            if(this.zzf.bindService(intent1, this.zzh, 1)) {
                                zza.zzj("BillingClient", "Service was bonded successfully.");
                                return;
                            }
                            zza.zzk("BillingClient", "Connection to Billing service is blocked.");
                        }
                    }
                }
                this.zza = 0;
                zza.zzj("BillingClient", "Billing service unavailable on device.");
                billingClientStateListener0.onBillingSetupFinished(zzak.zzc);
            }
        }
    }

    private final Handler zzD() {
        return Looper.myLooper() == null ? this.zzc : new Handler(Looper.myLooper());
    }

    private final BillingResult zzE(BillingResult billingResult0) {
        if(Thread.interrupted()) {
            return billingResult0;
        }
        zzq zzq0 = () -> this.zzd.zzb().onPurchasesUpdated(billingResult0, null);
        this.zzc.post(zzq0);
        return billingResult0;
    }

    private final BillingResult zzF() {
        return this.zza == 0 || this.zza == 3 ? zzak.zzq : zzak.zzl;
    }

    private final BillingResult zzG(String s) {
        Future future0 = this.zzH(() -> {
            zzd zzd0 = this.zzg;
            Bundle bundle0 = new Bundle();
            bundle0.putBoolean("vr", true);
            return zzd0.zzc(7, "com.esotericsoftware.gloomhavenhelper", s, bundle0);
        }, 5000L, null, this.zzD());
        try {
            return ((int)(((Integer)future0.get(5000L, TimeUnit.MILLISECONDS)))) == 0 ? zzak.zzp : zzak.zzi;
        }
        catch(Exception unused_ex) {
            zza.zzk("BillingClient", "Exception while checking if billing is supported; try to reconnect");
            return zzak.zzq;
        }
    }

    @Nullable
    private final Future zzH(Callable callable0, long v, @Nullable Runnable runnable0, Handler handler0) {
        Future future0;
        if(this.zzu == null) {
            zzac zzac0 = new zzac(this);
            this.zzu = Executors.newFixedThreadPool(zza.zza, zzac0);
        }
        try {
            future0 = this.zzu.submit(callable0);
        }
        catch(Exception exception0) {
            zza.zzk("BillingClient", "Async task throws exception " + exception0);
            return null;
        }
        handler0.postDelayed(new zzw(future0, runnable0), ((long)(((double)v) * 0.95)));
        return future0;
    }

    private final void zzI(BillingResult billingResult0, PriceChangeConfirmationListener priceChangeConfirmationListener0) {
        if(Thread.interrupted()) {
            return;
        }
        zzs zzs0 = new zzs(priceChangeConfirmationListener0, billingResult0);
        this.zzc.post(zzs0);
    }

    static Context zzd(BillingClientImpl billingClientImpl0) {
        return billingClientImpl0.zzf;
    }

    // 检测为 Lambda 实现
    public final Bundle zze(int v, SkuDetails skuDetails0, String s, BillingFlowParams billingFlowParams0, Bundle bundle0) throws Exception [...]

    // 检测为 Lambda 实现
    public final Bundle zzf(SkuDetails skuDetails0, String s) throws Exception [...]

    // 检测为 Lambda 实现
    public final Bundle zzg(String s, Bundle bundle0) throws Exception [...]

    static zzag zzi(BillingClientImpl billingClientImpl0, String s) {
        PurchaseHistoryRecord purchaseHistoryRecord0;
        Bundle bundle1;
        String s1 = String.valueOf(s);
        zza.zzj("BillingClient", (s1.length() == 0 ? new String("Querying purchase history, item type: ") : "Querying purchase history, item type: " + s1));
        ArrayList arrayList0 = new ArrayList();
        Bundle bundle0 = zza.zzf(billingClientImpl0.zzn, billingClientImpl0.zzt, billingClientImpl0.zzb);
        String s2 = null;
        do {
            if(!billingClientImpl0.zzl) {
                zza.zzk("BillingClient", "getPurchaseHistory is not supported on current device");
                return new zzag(zzak.zzj, null);
            }
            try {
                bundle1 = billingClientImpl0.zzg.zzh(6, "com.esotericsoftware.gloomhavenhelper", s, s2, bundle0);
            }
            catch(RemoteException remoteException0) {
                zza.zzk("BillingClient", "Got exception trying to get purchase history: " + remoteException0 + "; try to reconnect");
                return new zzag(zzak.zzq, null);
            }
            BillingResult billingResult0 = zzam.zza(bundle1, "BillingClient", "getPurchaseHistory()");
            if(billingResult0 != zzak.zzp) {
                return new zzag(billingResult0, null);
            }
            ArrayList arrayList1 = bundle1.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            ArrayList arrayList2 = bundle1.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
            ArrayList arrayList3 = bundle1.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
            for(int v = 0; v < arrayList2.size(); ++v) {
                String s3 = (String)arrayList2.get(v);
                String s4 = (String)arrayList3.get(v);
                String s5 = String.valueOf(((String)arrayList1.get(v)));
                zza.zzj("BillingClient", (s5.length() == 0 ? new String("Purchase record found for sku : ") : "Purchase record found for sku : " + s5));
                try {
                    purchaseHistoryRecord0 = new PurchaseHistoryRecord(s3, s4);
                }
                catch(JSONException jSONException0) {
                    zza.zzk("BillingClient", "Got an exception trying to decode the purchase: " + jSONException0);
                    return new zzag(zzak.zzl, null);
                }
                if(TextUtils.isEmpty(purchaseHistoryRecord0.getPurchaseToken())) {
                    zza.zzk("BillingClient", "BUG: empty/null token!");
                }
                arrayList0.add(purchaseHistoryRecord0);
            }
            s2 = bundle1.getString("INAPP_CONTINUATION_TOKEN");
            String s6 = String.valueOf(s2);
            zza.zzj("BillingClient", (s6.length() == 0 ? new String("Continuation token: ") : "Continuation token: " + s6));
        }
        while(!TextUtils.isEmpty(s2));
        return new zzag(zzak.zzp, arrayList0);
    }

    static PurchasesResult zzk(BillingClientImpl billingClientImpl0, String s) {
        Purchase purchase0;
        Bundle bundle1;
        String s1 = String.valueOf(s);
        zza.zzj("BillingClient", (s1.length() == 0 ? new String("Querying owned items, item type: ") : "Querying owned items, item type: " + s1));
        ArrayList arrayList0 = new ArrayList();
        Bundle bundle0 = zza.zzf(billingClientImpl0.zzn, billingClientImpl0.zzt, billingClientImpl0.zzb);
        String s2 = null;
        do {
            try {
                bundle1 = billingClientImpl0.zzn ? billingClientImpl0.zzg.zzj(9, "com.esotericsoftware.gloomhavenhelper", s, s2, bundle0) : billingClientImpl0.zzg.zzi(3, "com.esotericsoftware.gloomhavenhelper", s, s2);
            }
            catch(Exception exception0) {
                zza.zzk("BillingClient", "Got exception trying to get purchases: " + exception0 + "; try to reconnect");
                return new PurchasesResult(zzak.zzq, null);
            }
            BillingResult billingResult0 = zzam.zza(bundle1, "BillingClient", "getPurchase()");
            if(billingResult0 != zzak.zzp) {
                return new PurchasesResult(billingResult0, null);
            }
            ArrayList arrayList1 = bundle1.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            ArrayList arrayList2 = bundle1.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
            ArrayList arrayList3 = bundle1.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
            for(int v = 0; v < arrayList2.size(); ++v) {
                String s3 = (String)arrayList2.get(v);
                String s4 = (String)arrayList3.get(v);
                String s5 = String.valueOf(((String)arrayList1.get(v)));
                zza.zzj("BillingClient", (s5.length() == 0 ? new String("Sku is owned: ") : "Sku is owned: " + s5));
                try {
                    purchase0 = new Purchase(s3, s4);
                }
                catch(JSONException jSONException0) {
                    zza.zzk("BillingClient", "Got an exception trying to decode the purchase: " + jSONException0);
                    return new PurchasesResult(zzak.zzl, null);
                }
                if(TextUtils.isEmpty(purchase0.getPurchaseToken())) {
                    zza.zzk("BillingClient", "BUG: empty/null token!");
                }
                arrayList0.add(purchase0);
            }
            s2 = bundle1.getString("INAPP_CONTINUATION_TOKEN");
            String s6 = String.valueOf(s2);
            zza.zzj("BillingClient", (s6.length() == 0 ? new String("Continuation token: ") : "Continuation token: " + s6));
        }
        while(!TextUtils.isEmpty(s2));
        return new PurchasesResult(zzak.zzp, arrayList0);
    }

    // 检测为 Lambda 实现
    public final Integer zzn(String s) throws Exception [...]

    public final Object zzo(AcknowledgePurchaseParams acknowledgePurchaseParams0, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener0) throws Exception {
        Bundle bundle0;
        try {
            bundle0 = this.zzg.zzd(9, "com.esotericsoftware.gloomhavenhelper", acknowledgePurchaseParams0.getPurchaseToken(), zza.zzb(acknowledgePurchaseParams0, this.zzb));
        }
        catch(Exception exception0) {
            zza.zzk("BillingClient", "Error acknowledge purchase; ex: " + exception0);
            acknowledgePurchaseResponseListener0.onAcknowledgePurchaseResponse(zzak.zzq);
            return null;
        }
        int v = zza.zza(bundle0, "BillingClient");
        String s = zza.zzh(bundle0, "BillingClient");
        Builder billingResult$Builder0 = BillingResult.newBuilder();
        billingResult$Builder0.setResponseCode(v);
        billingResult$Builder0.setDebugMessage(s);
        acknowledgePurchaseResponseListener0.onAcknowledgePurchaseResponse(billingResult$Builder0.build());
        return null;
    }

    public final Object zzp(ConsumeParams consumeParams0, ConsumeResponseListener consumeResponseListener0) throws Exception {
        String s2;
        int v;
        String s = consumeParams0.getPurchaseToken();
        try {
            String s1 = String.valueOf(s);
            zza.zzj("BillingClient", (s1.length() == 0 ? new String("Consuming purchase with token: ") : "Consuming purchase with token: " + s1));
            if(this.zzn) {
                Bundle bundle0 = this.zzg.zze(9, "com.esotericsoftware.gloomhavenhelper", s, zza.zzc(consumeParams0, this.zzn, this.zzb));
                v = bundle0.getInt("RESPONSE_CODE");
                s2 = zza.zzh(bundle0, "BillingClient");
            }
            else {
                v = this.zzg.zza(3, "com.esotericsoftware.gloomhavenhelper", s);
                s2 = "";
            }
            Builder billingResult$Builder0 = BillingResult.newBuilder();
            billingResult$Builder0.setResponseCode(v);
            billingResult$Builder0.setDebugMessage(s2);
            BillingResult billingResult0 = billingResult$Builder0.build();
            if(v == 0) {
                zza.zzj("BillingClient", "Successfully consumed purchase.");
                consumeResponseListener0.onConsumeResponse(billingResult0, s);
                return null;
            }
            zza.zzk("BillingClient", "Error consuming purchase with token. Response code: " + v);
            consumeResponseListener0.onConsumeResponse(billingResult0, s);
        }
        catch(Exception exception0) {
            zza.zzk("BillingClient", "Error consuming purchase; ex: " + exception0);
            consumeResponseListener0.onConsumeResponse(zzak.zzq, s);
        }
        return null;
    }

    public final Object zzq(String s, List list0, String s1, SkuDetailsResponseListener skuDetailsResponseListener0) throws Exception {
        SkuDetails skuDetails0;
        ArrayList arrayList3;
        String s2;
        Bundle bundle1;
        int v2;
        ArrayList arrayList0 = new ArrayList();
        int v = list0.size();
    alab1:
        for(int v1 = 0; true; v1 += 20) {
            v2 = 6;
            if(v1 >= v) {
                v2 = 0;
                arrayList3 = arrayList0;
                s2 = "";
                break;
            }
            ArrayList arrayList1 = new ArrayList(list0.subList(v1, (v1 + 20 <= v ? v1 + 20 : v)));
            ArrayList arrayList2 = new ArrayList();
            int v3 = arrayList1.size();
            for(int v4 = 0; v4 < v3; ++v4) {
                arrayList2.add(((zzaq)arrayList1.get(v4)).zza());
            }
            Bundle bundle0 = new Bundle();
            bundle0.putStringArrayList("ITEM_ID_LIST", arrayList2);
            bundle0.putString("playBillingLibraryVersion", this.zzb);
            try {
                bundle1 = this.zzo ? this.zzg.zzl(10, "com.esotericsoftware.gloomhavenhelper", s, bundle0, zza.zzd(this.zzk, this.zzt, this.zzb, null, arrayList1)) : this.zzg.zzk(3, "com.esotericsoftware.gloomhavenhelper", s, bundle0);
            }
            catch(Exception exception0) {
                zza.zzk("BillingClient", "querySkuDetailsAsync got a remote exception (try to reconnect)." + exception0);
                s2 = "Service connection is disconnected.";
                arrayList3 = null;
                v2 = -1;
                break;
            }
            if(bundle1 == null) {
                zza.zzk("BillingClient", "querySkuDetailsAsync got null sku details list");
                s2 = "Item is unavailable for purchase.";
                arrayList3 = null;
                v2 = 4;
                break;
            }
            if(!bundle1.containsKey("DETAILS_LIST")) {
                int v5 = zza.zza(bundle1, "BillingClient");
                String s3 = zza.zzh(bundle1, "BillingClient");
                if(v5 != 0) {
                    zza.zzk("BillingClient", "getSkuDetails() failed. Response code: " + v5);
                    v2 = v5;
                    arrayList3 = arrayList0;
                    s2 = s3;
                    break;
                }
                zza.zzk("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                arrayList3 = arrayList0;
                s2 = s3;
                break;
            }
            ArrayList arrayList4 = bundle1.getStringArrayList("DETAILS_LIST");
            if(arrayList4 == null) {
                zza.zzk("BillingClient", "querySkuDetailsAsync got null response list");
                s2 = "Item is unavailable for purchase.";
                arrayList3 = null;
                v2 = 4;
                break;
            }
            for(int v6 = 0; v6 < arrayList4.size(); ++v6) {
                String s4 = (String)arrayList4.get(v6);
                try {
                    skuDetails0 = new SkuDetails(s4);
                }
                catch(JSONException unused_ex) {
                    zza.zzk("BillingClient", "Got a JSON exception trying to decode SkuDetails.");
                    s2 = "Error trying to decode SkuDetails.";
                    arrayList3 = null;
                    break alab1;
                }
                zza.zzj("BillingClient", "Got sku details: " + skuDetails0);
                arrayList0.add(skuDetails0);
            }
        }
        Builder billingResult$Builder0 = BillingResult.newBuilder();
        billingResult$Builder0.setResponseCode(v2);
        billingResult$Builder0.setDebugMessage(s2);
        skuDetailsResponseListener0.onSkuDetailsResponse(billingResult$Builder0.build(), arrayList3);
        return null;
    }

    static Future zzr(BillingClientImpl billingClientImpl0, Callable callable0, long v, Runnable runnable0, Handler handler0) {
        return billingClientImpl0.zzH(callable0, 30000L, runnable0, handler0);
    }

    // 检测为 Lambda 实现
    public final void zzs(BillingResult billingResult0) [...]
}

