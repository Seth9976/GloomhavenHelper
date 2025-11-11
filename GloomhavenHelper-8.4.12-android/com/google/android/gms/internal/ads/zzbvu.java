package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

public final class zzbvu extends zzbnf {
    private final WeakReference zzflu;
    private final zzbui zzflv;
    private final zzbxb zzflw;
    private final zzbnz zzflx;
    private final zzdjh zzfly;
    private boolean zzflz;
    private final Context zzur;

    zzbvu(zzbne zzbne0, Context context0, @Nullable zzbdv zzbdv0, zzbui zzbui0, zzbxb zzbxb0, zzbnz zzbnz0, zzdjh zzdjh0) {
        super(zzbne0);
        this.zzflz = false;
        this.zzur = context0;
        this.zzflu = new WeakReference(zzbdv0);
        this.zzflv = zzbui0;
        this.zzflw = zzbxb0;
        this.zzflx = zzbnz0;
        this.zzfly = zzdjh0;
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
            else if(!this.zzflz && zzbdv0 != null) {
                zzbdv0.getClass();
                Runnable runnable0 = zzbvx.zzh(zzbdv0);
                zzazq.zzdxo.execute(runnable0);
            }
        }
        finally {
            super.finalize();
        }
    }

    public final boolean isClosed() {
        return this.zzflx.isClosed();
    }

    public final boolean zzaja() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjg)).booleanValue() && zzawo.zzaw(this.zzur)) {
            zzawf.zzfa("Interstitials that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit  https://googlemobileadssdk.page.link/admob-interstitial-policies");
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjh)).booleanValue()) {
                this.zzfly.zzgt(this.zzfdp.zzgqm.zzgqj.zzcac);
            }
            return false;
        }
        return !this.zzflz;
    }

    public final void zzbi(boolean z) {
        this.zzflv.zzait();
        this.zzflw.zza(z, this.zzur);
        this.zzflz = true;
    }
}

