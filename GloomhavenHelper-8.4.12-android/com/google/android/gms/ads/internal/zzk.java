package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.ads.zzawf;

final class zzk extends WebViewClient {
    private final zzl zzblt;

    zzk(zzl zzl0) {
        this.zzblt = zzl0;
        super();
    }

    @Override  // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView0, WebResourceRequest webResourceRequest0, WebResourceError webResourceError0) {
        if(this.zzblt.zzblz != null) {
            try {
                this.zzblt.zzblz.onAdFailedToLoad(0);
            }
            catch(RemoteException remoteException0) {
                zzawf.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }

    @Override  // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView0, String s) {
        if(s.startsWith(this.zzblt.zzkk())) {
            return false;
        }
        if(s.startsWith("gmsg://noAdLoaded")) {
            if(this.zzblt.zzblz != null) {
                try {
                    this.zzblt.zzblz.onAdFailedToLoad(3);
                }
                catch(RemoteException remoteException0) {
                    zzawf.zze("#007 Could not call remote method.", remoteException0);
                }
            }
            this.zzblt.zzbm(0);
            return true;
        }
        if(s.startsWith("gmsg://scriptLoadFailed")) {
            if(this.zzblt.zzblz != null) {
                try {
                    this.zzblt.zzblz.onAdFailedToLoad(0);
                }
                catch(RemoteException remoteException1) {
                    zzawf.zze("#007 Could not call remote method.", remoteException1);
                }
            }
            this.zzblt.zzbm(0);
            return true;
        }
        if(s.startsWith("gmsg://adResized")) {
            if(this.zzblt.zzblz != null) {
                try {
                    this.zzblt.zzblz.onAdLoaded();
                }
                catch(RemoteException remoteException2) {
                    zzawf.zze("#007 Could not call remote method.", remoteException2);
                }
            }
            int v = this.zzblt.zzbt(s);
            this.zzblt.zzbm(v);
            return true;
        }
        if(s.startsWith("gmsg://")) {
            return true;
        }
        if(this.zzblt.zzblz != null) {
            try {
                this.zzblt.zzblz.onAdLeftApplication();
            }
            catch(RemoteException remoteException3) {
                zzawf.zze("#007 Could not call remote method.", remoteException3);
            }
        }
        String s1 = this.zzblt.zzbu(s);
        this.zzblt.zzbv(s1);
        return true;
    }
}

