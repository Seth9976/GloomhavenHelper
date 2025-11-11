package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;

public class zzcqs extends zzalt {
    private final zzbrm zzfhv;
    private final zzbrc zzfil;
    private final zzbrv zzfmd;
    private final zzbqp zzfnf;
    private final zzbqg zzfng;
    private final zzbsy zzftt;
    private final zzbvk zzgeh;
    protected final zzbsu zzgei;

    public zzcqs(zzbqg zzbqg0, zzbqp zzbqp0, zzbrc zzbrc0, zzbrm zzbrm0, zzbsy zzbsy0, zzbrv zzbrv0, zzbvk zzbvk0, zzbsu zzbsu0) {
        this.zzfng = zzbqg0;
        this.zzfnf = zzbqp0;
        this.zzfil = zzbrc0;
        this.zzfhv = zzbrm0;
        this.zzftt = zzbsy0;
        this.zzfmd = zzbrv0;
        this.zzgeh = zzbvk0;
        this.zzgei = zzbsu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClicked() {
        this.zzfng.onAdClicked();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdClosed() {
        this.zzfmd.zztj();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdFailedToLoad(int v) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void onAdImpression() {
        this.zzfnf.onAdImpression();
        this.zzgei.zzaif();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLeftApplication() {
        this.zzfil.onAdLeftApplication();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdLoaded() {
        this.zzfhv.onAdLoaded();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAdOpened() {
        this.zzfmd.zztk();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onAppEvent(String s, String s1) {
        this.zzftt.onAppEvent(s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void onVideoEnd() {
        this.zzgeh.onVideoEnd();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPause() {
        this.zzgeh.onVideoPause();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void onVideoPlay() throws RemoteException {
        this.zzgeh.onVideoPlay();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzadn zzadn0, String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zza(zzalv zzalv0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void zza(zzass zzass0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void zzb(Bundle bundle0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void zzb(zzasq zzasq0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void zzco(int v) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public final void zzdk(String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void zzsx() {
        this.zzgeh.onVideoStart();
    }

    @Override  // com.google.android.gms.internal.ads.zzalq
    public void zzsy() throws RemoteException {
    }
}

