package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

public final class zzcdn extends zzbnf {
    private final zzass zzdpd;
    private final WeakReference zzflu;
    private final zzbui zzflv;
    private final zzbnz zzflx;
    private final zzdjh zzfly;
    private final zzbxb zzfmb;
    private boolean zzftd;
    private final zzbrq zzfts;
    private final zzbql zzfuc;
    private final Context zzur;

    zzcdn(zzbne zzbne0, Context context0, @Nullable zzbdv zzbdv0, zzbxb zzbxb0, zzbui zzbui0, zzbql zzbql0, zzbrq zzbrq0, zzbnz zzbnz0, zzdei zzdei0, zzdjh zzdjh0) {
        super(zzbne0);
        this.zzftd = false;
        this.zzur = context0;
        this.zzfmb = zzbxb0;
        this.zzflu = new WeakReference(zzbdv0);
        this.zzflv = zzbui0;
        this.zzfuc = zzbql0;
        this.zzfts = zzbrq0;
        this.zzflx = zzbnz0;
        this.zzfly = zzdjh0;
        this.zzdpd = new zzatp(zzdei0.zzdmd);
    }

    @Override
    public final void finalize() throws Throwable {
        try {
            zzbdv zzbdv0 = (zzbdv)this.zzflu.get();
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcsd)).booleanValue()) {
                if(zzbdv0 != null) {
                    zzbdv0.destroy();
                }
            }
            else if(!this.zzftd && zzbdv0 != null) {
                zzbdv0.getClass();
                Runnable runnable0 = zzcdm.zzh(zzbdv0);
                zzazq.zzdxo.execute(runnable0);
            }
        }
        finally {
            super.finalize();
        }
    }

    public final Bundle getAdMetadata() {
        return this.zzfts.getAdMetadata();
    }

    public final boolean isClosed() {
        return this.zzflx.isClosed();
    }

    public final boolean zzaly() {
        return this.zzftd;
    }

    public final void zzb(boolean z, @Nullable Activity activity0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjg)).booleanValue() && zzawo.zzaw(this.zzur)) {
            zzawf.zzfa("Rewarded ads that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit https://googlemobileadssdk.page.link/admob-interstitial-policies");
            this.zzfuc.zzco(3);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjh)).booleanValue()) {
                this.zzfly.zzgt(this.zzfdp.zzgqm.zzgqj.zzcac);
            }
            return;
        }
        if(this.zzftd) {
            zzawf.zzfa("The rewarded ad have been showed.");
            this.zzfuc.zzco(1);
            return;
        }
        this.zzftd = true;
        this.zzflv.zzait();
        if(activity0 == null) {
            activity0 = this.zzur;
        }
        this.zzfmb.zza(z, activity0);
    }

    public final zzass zzqc() {
        return this.zzdpd;
    }

    public final boolean zzqd() {
        zzbdv zzbdv0 = (zzbdv)this.zzflu.get();
        return zzbdv0 != null && !zzbdv0.zzaau();
    }
}

