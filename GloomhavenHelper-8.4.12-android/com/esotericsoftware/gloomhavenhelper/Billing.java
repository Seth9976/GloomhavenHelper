package com.esotericsoftware.gloomhavenhelper;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.util.Base64;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase.PurchasesResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.esotericsoftware.minlog.Log;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

public class Billing {
    interface BillingRunnable {
        void run(BillingClient arg1);
    }

    private BillingClient billingClient;
    private boolean connected;
    private final AndroidLauncher launcher;
    public volatile SkuDetails noAds;
    public volatile boolean noAdsAlreadyOwned;
    public volatile boolean noAdsPurchased;
    private static final String noAdsSku = "noads";
    private static final String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwtkpu+VxYjIo6qE4/bKyqzRJsAJ2Q/4cxCbyWd/9b2TSivXZoQ8SxfzMHAuPfl4ig9/gaXW52DoIbuV1fq06hsYg17doebed2iyo2yXW6tWa6B0F7fN4jRg6EJPmVnh6mSRkENecQ3JKRq2fYeDTdrp9Lxda7YPGCt3Syq6VGpv4RilMsjS2SVV5fuouuuUUEBS82j1de0anz4jc9GDqsBH/H7qjrj4yOSLWqfib59rqP3MpPlQ9T4RE4dXkkRVuUkwwbILPGtRG0q/OG2Rlco4evW1G0hxO/S72sAbEZ8WuIObIIF7YcdXOxWRUAHB3R0Ig/3B+IkYpmSqw3s5PKwIDAQAB";

    public Billing(AndroidLauncher androidLauncher0) {
        this.launcher = androidLauncher0;
        this.noAdsPurchased = this.loadPurchase("noads");
        try {
            this.billingClient = BillingClient.newBuilder(androidLauncher0).setListener(new PurchasesUpdatedListener() {
                @Override  // com.android.billingclient.api.PurchasesUpdatedListener
                public void onPurchasesUpdated(BillingResult billingResult0, @Nullable List list0) {
                    if(Billing.this.billingClient == null) {
                        return;
                    }
                    switch(billingResult0.getResponseCode()) {
                        case 0: {
                            if(list0 != null) {
                                Log.debug((list0.size() + " purchases updated."));
                                Billing.this.storePurchases(list0);
                            }
                            return;
                        }
                        case 7: {
                            Log.info("Already purchased: noads");
                            if(!Billing.this.noAdsPurchased) {
                                Log.info("Ads removed!");
                                Billing.this.noAdsAlreadyOwned = true;
                                Billing.this.noAdsPurchased = true;
                                App.game.purchased();
                            }
                            Billing.this.updatePurchases();
                            return;
                        }
                        default: {
                            Log.debug(("Unable to update purchases: " + billingResult0.getResponseCode() + " " + billingResult0.getDebugMessage()));
                        }
                    }
                }
            }).enablePendingPurchases().build();
        }
        catch(Throwable throwable0) {
            Log.debug("Unable to create billing client.", throwable0);
        }
    }

    private void connect(BillingRunnable billing$BillingRunnable0, int v) {
        if(this.billingClient == null) {
            return;
        }
        Log.debug("Connecting to billing service...");
        try {
            this.billingClient.startConnection(new BillingClientStateListener() {
                @Override  // com.android.billingclient.api.BillingClientStateListener
                public void onBillingServiceDisconnected() {
                    Billing.this.connected = false;
                    if(Billing.this.billingClient == null) {
                        return;
                    }
                    if(v > 0) {
                        Log.warn(("Unable to connect to billing service, retries: " + (v - 1) + "..."));
                        Billing.this.connect(billing$BillingRunnable0, v - 1);
                        return;
                    }
                    Log.error("Unable to connect to billing service.");
                }

                @Override  // com.android.billingclient.api.BillingClientStateListener
                public void onBillingSetupFinished(BillingResult billingResult0) {
                    if(Billing.this.billingClient == null) {
                        return;
                    }
                    if(billingResult0.getResponseCode() != 0) {
                        Log.debug(("Unable to connect to billing service: " + billingResult0.getResponseCode() + " " + billingResult0.getDebugMessage()));
                        this.onBillingServiceDisconnected();
                        return;
                    }
                    Log.debug("Connected to billing service.");
                    Billing.this.connected = true;
                    if(Billing.this.noAds == null) {
                        Billing.this.updateSkus();
                        Billing.this.updatePurchases();
                    }
                    BillingClient billingClient0 = Billing.this.billingClient;
                    if(billingClient0 != null) {
                        BillingRunnable billing$BillingRunnable0 = billing$BillingRunnable0;
                        if(billing$BillingRunnable0 != null) {
                            billing$BillingRunnable0.run(billingClient0);
                        }
                    }
                }
            });
        }
        catch(Throwable throwable0) {
            Log.debug("Unable to connect to billing service.", throwable0);
        }
    }

    private void deletePurchase(String s) {
        SharedPreferences.Editor sharedPreferences$Editor0 = this.launcher.getSharedPreferences("com.esotericsoftware.gloomhavenhelper", 0).edit();
        sharedPreferences$Editor0.remove(s);
        sharedPreferences$Editor0.remove(s + "-signature");
        sharedPreferences$Editor0.apply();
    }

    private boolean loadPurchase(String s) {
        SharedPreferences sharedPreferences0 = this.launcher.getSharedPreferences("com.esotericsoftware.gloomhavenhelper", 0);
        String s1 = sharedPreferences0.getString(s, null);
        String s2 = sharedPreferences0.getString(s + "-signature", null);
        if(s1 != null && s2 != null) {
            if(Billing.validate(s1, s2)) {
                Log.info(("Purchase loaded: " + s));
                return true;
            }
            Log.warn(("Purchase is invalid: " + s));
            return false;
        }
        Log.debug(("Purchase not loaded: " + s));
        return false;
    }

    public void purchase(SkuDetails skuDetails0) {
        this.run(new BillingRunnable() {
            @Override  // com.esotericsoftware.gloomhavenhelper.Billing$BillingRunnable
            public void run(BillingClient billingClient0) {
                Log.debug(("Starting purchase: " + skuDetails0.getSku()));
                try {
                    BillingFlowParams billingFlowParams0 = BillingFlowParams.newBuilder().setSkuDetails(skuDetails0).build();
                    BillingResult billingResult0 = billingClient0.launchBillingFlow(Billing.this.launcher, billingFlowParams0);
                    if(billingResult0.getResponseCode() != 0) {
                        Log.error(("Unable to purchase: " + billingResult0.getResponseCode() + " " + billingResult0.getDebugMessage()));
                    }
                }
                catch(Throwable throwable0) {
                    Log.debug("Unable to start purchase.", throwable0);
                }
            }
        });
    }

    private void run(BillingRunnable billing$BillingRunnable0) {
        if(this.billingClient == null) {
            return;
        }
        com.esotericsoftware.gloomhavenhelper.Billing.7 billing$70 = new Runnable() {
            @Override
            public void run() {
                BillingClient billingClient0 = Billing.this.billingClient;
                if(billingClient0 == null) {
                    return;
                }
                if(Billing.this.connected) {
                    billing$BillingRunnable0.run(billingClient0);
                    return;
                }
                Billing.this.connect(billing$BillingRunnable0, 5);
            }
        };
        this.launcher.runOnUiThread(billing$70);
    }

    private boolean storePurchase(String s, String s1, String s2) {
        if(!Billing.validate(s1, s2)) {
            Log.warn(("Purchase is invalid: " + s));
            return false;
        }
        Log.info(("Purchased: " + s));
        SharedPreferences.Editor sharedPreferences$Editor0 = this.launcher.getSharedPreferences("com.esotericsoftware.gloomhavenhelper", 0).edit();
        sharedPreferences$Editor0.putString(s, s1);
        sharedPreferences$Editor0.putString(s + "-signature", s2);
        sharedPreferences$Editor0.apply();
        return true;
    }

    private void storePurchases(List list0) {
        Purchase purchase0 = null;
        for(Object object0: list0) {
            Purchase purchase1 = (Purchase)object0;
            if(purchase1.getPurchaseState() == 1 && this.storePurchase(((String)purchase1.getSkus().get(0)), purchase1.getOriginalJson(), purchase1.getSignature()) && ((String)purchase1.getSkus().get(0)).equals("noads")) {
                purchase0 = purchase1;
            }
        }
        if(purchase0 != null) {
            if(this.noAdsPurchased) {
                Log.info("Ad removal purchase verified.");
            }
            else {
                Log.info("Ads removed!");
                this.noAdsPurchased = true;
                App.game.purchased();
            }
            com.esotericsoftware.gloomhavenhelper.Billing.4 billing$40 = new Runnable() {
                @Override
                public void run() {
                    try {
                        BillingClient billingClient0 = Billing.this.billingClient;
                        if(billingClient0 != null && billingClient0.isReady()) {
                            billingClient0.endConnection();
                            Billing.this.billingClient = null;
                        }
                    }
                    catch(Throwable throwable0) {
                        Log.debug("Unable to disconnect from billing service.", throwable0);
                    }
                }
            };
            BillingClient billingClient0 = this.billingClient;
            if(billingClient0 != null && !purchase0.isAcknowledged()) {
                billingClient0.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase0.getPurchaseToken()).build(), new AcknowledgePurchaseResponseListener() {
                    @Override  // com.android.billingclient.api.AcknowledgePurchaseResponseListener
                    public void onAcknowledgePurchaseResponse(BillingResult billingResult0) {
                        if(billingResult0.getResponseCode() == 0) {
                            Log.info("Purchase acknowledged: noads");
                            billing$40.run();
                            return;
                        }
                        Log.error(("Unable to acknowledge purchase: " + billingResult0.getResponseCode() + " " + billingResult0.getDebugMessage()));
                    }
                });
                return;
            }
            billing$40.run();
            return;
        }
        if(this.noAdsPurchased && !this.noAdsAlreadyOwned) {
            this.noAdsPurchased = false;
            Log.info("Ad removal purchase not found.");
            this.deletePurchase("noads");
            Toast.makeText(this.launcher.getApplicationContext(), "Sorry, your \"remove ads\" purchase was refunded.\nPlease restart Gloomhaven Helper.", 1).show();
            this.launcher.finish();
        }
    }

    public void updatePurchases() {
        this.run(new BillingRunnable() {
            @Override  // com.esotericsoftware.gloomhavenhelper.Billing$BillingRunnable
            public void run(BillingClient billingClient0) {
                Log.debug("Updating purchases...");
                try {
                    PurchasesResult purchase$PurchasesResult0 = billingClient0.queryPurchases("inapp");
                    if(purchase$PurchasesResult0.getResponseCode() == 0) {
                        Billing.this.storePurchases(purchase$PurchasesResult0.getPurchasesList());
                        return;
                    }
                    Log.debug(("Unable to update purchases: " + purchase$PurchasesResult0.getResponseCode()));
                }
                catch(Throwable throwable0) {
                    Log.debug("Unable to update purchases.", throwable0);
                }
            }
        });
    }

    private void updateSkus() {
        this.run(new BillingRunnable() {
            @Override  // com.esotericsoftware.gloomhavenhelper.Billing$BillingRunnable
            public void run(BillingClient billingClient0) {
                Log.debug("Updating SKUs...");
                try {
                    ArrayList arrayList0 = new ArrayList();
                    arrayList0.add("noads");
                    billingClient0.querySkuDetailsAsync(SkuDetailsParams.newBuilder().setSkusList(arrayList0).setType("inapp").build(), new SkuDetailsResponseListener() {
                        @Override  // com.android.billingclient.api.SkuDetailsResponseListener
                        public void onSkuDetailsResponse(BillingResult billingResult0, List list0) {
                            if(billingResult0.getResponseCode() != 0) {
                                Log.debug(("Unable to update SKUs: " + billingResult0.getResponseCode() + " " + billingResult0.getDebugMessage()));
                                return;
                            }
                            for(Object object0: list0) {
                                SkuDetails skuDetails0 = (SkuDetails)object0;
                                Log.debug(("SKU: " + skuDetails0.getSku()));
                                if(skuDetails0.getSku().equals("noads")) {
                                    Billing.this.noAds = skuDetails0;
                                }
                            }
                            Billing.this.updatePurchases();
                        }
                    });
                }
                catch(Throwable throwable0) {
                    Log.debug("Unable to update SKUs.", throwable0);
                }
            }
        });
    }

    private static boolean validate(String s, String s1) {
        PublicKey publicKey0;
        try {
            if(s == null || s1 == null || (s.isEmpty() || s1.isEmpty())) {
                throw new RuntimeException("Invalid signature verification input.");
            }
            try {
                byte[] arr_b = Base64.decode(s1, 0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new RuntimeException("Base64 decoding failed.", illegalArgumentException0);
            }
            try {
                byte[] arr_b1 = Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwtkpu+VxYjIo6qE4/bKyqzRJsAJ2Q/4cxCbyWd/9b2TSivXZoQ8SxfzMHAuPfl4ig9/gaXW52DoIbuV1fq06hsYg17doebed2iyo2yXW6tWa6B0F7fN4jRg6EJPmVnh6mSRkENecQ3JKRq2fYeDTdrp9Lxda7YPGCt3Syq6VGpv4RilMsjS2SVV5fuouuuUUEBS82j1de0anz4jc9GDqsBH/H7qjrj4yOSLWqfib59rqP3MpPlQ9T4RE4dXkkRVuUkwwbILPGtRG0q/OG2Rlco4evW1G0hxO/S72sAbEZ8WuIObIIF7YcdXOxWRUAHB3R0Ig/3B+IkYpmSqw3s5PKwIDAQAB", 0);
                publicKey0 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arr_b1));
            }
            catch(NoSuchAlgorithmException noSuchAlgorithmException0) {
                throw new RuntimeException(noSuchAlgorithmException0);
            }
            catch(InvalidKeySpecException invalidKeySpecException0) {
                throw new RuntimeException("Invalid key specification: " + invalidKeySpecException0);
            }
            try {
                Signature signature0 = Signature.getInstance("SHA1withRSA");
                signature0.initVerify(publicKey0);
                signature0.update(s.getBytes());
                if(!signature0.verify(arr_b)) {
                    throw new RuntimeException("Signature verification failed.");
                }
                return true;
            }
            catch(InvalidKeyException invalidKeyException0) {
                throw new RuntimeException("Invalid key specification.", invalidKeyException0);
            }
            catch(SignatureException signatureException0) {
                throw new RuntimeException("Invalid signature.", signatureException0);
            }
        }
        catch(Throwable throwable0) {
            Log.error("Unable to validate data.", throwable0);
            return false;
        }
    }
}

