package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzape;

public final class zzs extends zzape {
    private boolean zzdib;
    private AdOverlayInfoParcel zzdjd;
    private boolean zzdje;
    private Activity zzzo;

    public zzs(Activity activity0, AdOverlayInfoParcel adOverlayInfoParcel0) {
        this.zzdib = false;
        this.zzdje = false;
        this.zzdjd = adOverlayInfoParcel0;
        this.zzzo = activity0;
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onActivityResult(int v, int v1, Intent intent0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onBackPressed() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onCreate(Bundle bundle0) {
        boolean z = bundle0 != null && bundle0.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        AdOverlayInfoParcel adOverlayInfoParcel0 = this.zzdjd;
        if(adOverlayInfoParcel0 == null) {
            this.zzzo.finish();
            return;
        }
        if(z) {
            this.zzzo.finish();
            return;
        }
        if(bundle0 == null) {
            if(adOverlayInfoParcel0.zzcch != null) {
                this.zzdjd.zzcch.onAdClicked();
            }
            if(this.zzzo.getIntent() != null && this.zzzo.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true) && this.zzdjd.zzdit != null) {
                this.zzdjd.zzdit.zztk();
            }
        }
        if(!zza.zza(this.zzzo, this.zzdjd.zzdis, this.zzdjd.zzdix)) {
            this.zzzo.finish();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onDestroy() throws RemoteException {
        if(this.zzzo.isFinishing()) {
            this.zzua();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onPause() throws RemoteException {
        if(this.zzdjd.zzdit != null) {
            this.zzdjd.zzdit.onPause();
        }
        if(this.zzzo.isFinishing()) {
            this.zzua();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onRestart() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onResume() throws RemoteException {
        if(this.zzdib) {
            this.zzzo.finish();
            return;
        }
        this.zzdib = true;
        if(this.zzdjd.zzdit != null) {
            this.zzdjd.zzdit.onResume();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onSaveInstanceState(Bundle bundle0) throws RemoteException {
        bundle0.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdib);
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onStart() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void onStop() throws RemoteException {
        if(this.zzzo.isFinishing()) {
            this.zzua();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void zzad(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final void zzdk() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzapb
    public final boolean zztr() throws RemoteException {
        return false;
    }

    private final void zzua() {
        synchronized(this) {
            if(!this.zzdje) {
                if(this.zzdjd.zzdit != null) {
                    this.zzdjd.zzdit.zztj();
                }
                this.zzdje = true;
            }
        }
    }
}

