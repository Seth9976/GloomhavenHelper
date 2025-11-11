package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

public class zzvg extends AdListener {
    private final Object lock;
    private AdListener zzcee;

    public zzvg() {
        this.lock = new Object();
    }

    @Override  // com.google.android.gms.ads.AdListener
    public void onAdClosed() {
        synchronized(this.lock) {
            if(this.zzcee != null) {
                this.zzcee.onAdClosed();
            }
        }
    }

    @Override  // com.google.android.gms.ads.AdListener
    public void onAdFailedToLoad(int v) {
        synchronized(this.lock) {
            if(this.zzcee != null) {
                this.zzcee.onAdFailedToLoad(v);
            }
        }
    }

    @Override  // com.google.android.gms.ads.AdListener
    public void onAdLeftApplication() {
        synchronized(this.lock) {
            if(this.zzcee != null) {
                this.zzcee.onAdLeftApplication();
            }
        }
    }

    @Override  // com.google.android.gms.ads.AdListener
    public void onAdLoaded() {
        synchronized(this.lock) {
            if(this.zzcee != null) {
                this.zzcee.onAdLoaded();
            }
        }
    }

    @Override  // com.google.android.gms.ads.AdListener
    public void onAdOpened() {
        synchronized(this.lock) {
            if(this.zzcee != null) {
                this.zzcee.onAdOpened();
            }
        }
    }

    public final void zza(AdListener adListener0) {
        synchronized(this.lock) {
            this.zzcee = adListener0;
        }
    }
}

