package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public final class zzagt implements zzn {
    private volatile zzagi zzczd;
    private final Context zzur;

    public zzagt(Context context0) {
        this.zzur = context0;
    }

    private final void disconnect() {
        if(this.zzczd == null) {
            return;
        }
        this.zzczd.disconnect();
        Binder.flushPendingCommands();
    }

    @Override  // com.google.android.gms.internal.ads.zzn
    public final zzo zzc(zzq zzq0) throws zzae {
        ParcelFileDescriptor parcelFileDescriptor0;
        zzagl zzagl0 = zzagl.zzh(zzq0);
        long v = com.google.android.gms.ads.internal.zzq.zzlc().elapsedRealtime();
        try {
            zzazy zzazy0 = new zzazy();
            zzagx zzagx0 = new zzagx(this, zzazy0);
            zzagw zzagw0 = new zzagw(this, zzazy0);
            Looper looper0 = com.google.android.gms.ads.internal.zzq.zzlj().zzxg();
            this.zzczd = new zzagi(this.zzur, looper0, zzagx0, zzagw0);
            this.zzczd.checkAvailabilityAndConnect();
            zzdof zzdof0 = zzdnt.zza(zzdnt.zzb(zzazy0, new zzags(this, zzagl0), zzazq.zzdxk), ((long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcof)))))), TimeUnit.MILLISECONDS, zzazq.zzdxn);
            zzdof0.addListener(new zzagu(this), zzazq.zzdxk);
            parcelFileDescriptor0 = (ParcelFileDescriptor)zzdof0.get();
        }
        catch(InterruptedException | ExecutionException unused_ex) {
            return null;
        }
        finally {
            zzawf.zzee(("Http assets remote cache took " + (com.google.android.gms.ads.internal.zzq.zzlc().elapsedRealtime() - v) + "ms"));
        }
        zzagn zzagn0 = (zzagn)new zzaqw(parcelFileDescriptor0).zza(zzagn.CREATOR);
        if(zzagn0 == null) {
            return null;
        }
        if(zzagn0.zzcza) {
            throw new zzae(zzagn0.zzczb);
        }
        if(zzagn0.zzcyy.length != zzagn0.zzcyz.length) {
            return null;
        }
        HashMap hashMap0 = new HashMap();
        for(int v2 = 0; v2 < zzagn0.zzcyy.length; ++v2) {
            hashMap0.put(zzagn0.zzcyy[v2], zzagn0.zzcyz[v2]);
        }
        return new zzo(zzagn0.statusCode, zzagn0.data, hashMap0, zzagn0.zzac, zzagn0.zzad);
    }
}

