package com.esotericsoftware.gloomhavenhelper;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.esotericsoftware.minlog.Log.Logger;
import com.esotericsoftware.minlog.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication {
    private AdView banner;
    private LinearLayout.LayoutParams bannerLayoutParams;
    Billing billing;
    InterstitialAd fullScreen;
    private LinearLayout layout;
    private final boolean testAds;

    public AndroidLauncher() {
        this.testAds = false;
    }

    void newFullscreenAd() {
        if(this.billing.noAdsPurchased) {
            this.removeAds();
            return;
        }
        InterstitialAd interstitialAd0 = this.fullScreen;
        if(interstitialAd0 != null) {
            interstitialAd0.setAdListener(null);
            this.fullScreen = null;
            System.gc();
            Thread.yield();
            System.gc();
        }
        this.fullScreen = new InterstitialAd(this.getApplication());
        this.fullScreen.setAdUnitId("ca-app-pub-5399002202635011/4759730608");
        this.fullScreen.loadAd(new Builder().build());
        this.fullScreen.setAdListener(new AdListener() {
            @Override  // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                AndroidLauncher.this.newFullscreenAd();
            }
        });
    }

    @Override  // android.app.Activity
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        Log.setLogger(new Logger() {
            @Override  // com.esotericsoftware.minlog.Log$Logger
            public void log(int v, String s, String s1, Throwable throwable0) {
                if(s == null) {
                    s = "GHH";
                }
                if(throwable0 != null) {
                    switch(v) {
                        case 1: {
                            android.util.Log.v(s, s1, throwable0);
                            return;
                        }
                        case 2: {
                            android.util.Log.d(s, s1, throwable0);
                            return;
                        }
                        case 3: {
                            android.util.Log.i(s, s1, throwable0);
                            return;
                        }
                        case 4: {
                            android.util.Log.w(s, s1, throwable0);
                            return;
                        }
                        case 5: {
                            android.util.Log.e(s, s1, throwable0);
                            return;
                        }
                        default: {
                            return;
                        }
                    }
                }
                switch(v) {
                    case 1: {
                        android.util.Log.v(s, s1);
                        return;
                    }
                    case 2: {
                        android.util.Log.d(s, s1);
                        return;
                    }
                    case 3: {
                        android.util.Log.i(s, s1);
                        return;
                    }
                    case 4: {
                        android.util.Log.w(s, s1);
                        return;
                    }
                    case 5: {
                        android.util.Log.e(s, s1);
                    }
                }
            }
        });
        Log.DEBUG();
        this.getWindow().addFlags(0x80);
        if(Build.VERSION.SDK_INT >= 19) {
            this.getWindow().getDecorView().setSystemUiVisibility(0x1706);
            View view0 = this.getWindow().getDecorView();
            view0.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override  // android.view.View$OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int v) {
                    if((v & 4) == 0) {
                        view0.setSystemUiVisibility(0x1706);
                    }
                }
            });
        }
        this.billing = new Billing(this);
        App.game = new GameAndroid(this);
        AndroidApplicationConfiguration androidApplicationConfiguration0 = new AndroidApplicationConfiguration();
        if(this.billing.noAdsPurchased) {
            this.initialize(App.gloom, androidApplicationConfiguration0);
            return;
        }
        MobileAds.initialize(this, "ca-app-pub-5399002202635011~4361109506");
        MobileAds.setAppMuted(true);
        MobileAds.setAppVolume(0.0f);
        int v = (int)TypedValue.applyDimension(1, 50.0f, this.getResources().getDisplayMetrics());
        this.banner = new AdView(this.getApplicationContext());
        this.banner.setAdSize(new AdSize(-1, 50));
        this.banner.setAdUnitId("ca-app-pub-5399002202635011/8655577731");
        this.banner.loadAd(new Builder().build());
        this.newFullscreenAd();
        this.layout = new LinearLayout(this);
        this.layout.setOrientation(1);
        this.layout.setGravity(1);
        this.bannerLayoutParams = new LinearLayout.LayoutParams(-2, v);
        this.layout.addView(this.banner, this.bannerLayoutParams);
        this.layout.addView(this.initializeForView(App.gloom, androidApplicationConfiguration0));
        this.setContentView(this.layout);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplication
    protected void onDestroy() {
        super.onDestroy();
        this.removeAds();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplication
    protected void onResume() {
        super.onResume();
        this.billing.updatePurchases();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplication
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if(Build.VERSION.SDK_INT >= 19 && z) {
            this.getWindow().getDecorView().setSystemUiVisibility(0x1706);
        }
    }

    void removeAds() {
        AdView adView0 = this.banner;
        if(adView0 != null) {
            adView0.setVisibility(8);
            LinearLayout linearLayout0 = this.layout;
            if(linearLayout0 != null) {
                linearLayout0.removeView(this.banner);
            }
            this.banner.destroy();
            this.banner = null;
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = this.bannerLayoutParams;
            if(linearLayout$LayoutParams0 != null) {
                linearLayout$LayoutParams0.height = 0;
            }
            LinearLayout linearLayout1 = this.layout;
            if(linearLayout1 != null) {
                linearLayout1.requestLayout();
            }
        }
        InterstitialAd interstitialAd0 = this.fullScreen;
        if(interstitialAd0 != null) {
            interstitialAd0.setAdListener(null);
            this.fullScreen = null;
        }
        System.gc();
        Thread.yield();
        System.gc();
    }
}

