package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzawf;
import com.google.android.gms.internal.ads.zzayx;
import com.google.android.gms.internal.ads.zzazo;
import com.google.android.gms.internal.ads.zzazq;
import com.google.android.gms.internal.ads.zzdg;
import com.google.android.gms.internal.ads.zzdr;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzzx;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public final class zzh implements zzdg, Runnable {
    private final List zzblp;
    private final AtomicReference zzblq;
    private zzazo zzblr;
    private CountDownLatch zzbls;
    private Context zzur;

    public zzh(Context context0, zzazo zzazo0) {
        this.zzblp = new Vector();
        this.zzblq = new AtomicReference();
        this.zzbls = new CountDownLatch(1);
        this.zzur = context0;
        this.zzblr = zzazo0;
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmg)).booleanValue()) {
            zzazq.zzdxk.execute(this);
            return;
        }
        if(zzayx.zzxj()) {
            zzazq.zzdxk.execute(this);
            return;
        }
        this.run();
    }

    @Override
    public final void run() {
        try {
            boolean z = false;
            boolean z1 = this.zzblr.zzdxh;
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcjy)).booleanValue() && z1) {
                z = true;
            }
            zzdr zzdr0 = zzdr.zza(this.zzblr.zzbmj, zzh.zzf(this.zzur), z);
            this.zzblq.set(zzdr0);
        }
        finally {
            this.zzbls.countDown();
            this.zzur = null;
            this.zzblr = null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final String zza(Context context0, View view0, Activity activity0) {
        zzdg zzdg0 = (zzdg)this.zzblq.get();
        return zzdg0 == null ? "" : zzdg0.zza(context0, view0, activity0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final String zza(Context context0, String s, View view0) {
        return this.zza(context0, s, view0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final String zza(Context context0, String s, View view0, Activity activity0) {
        if(this.zzjy()) {
            zzdg zzdg0 = (zzdg)this.zzblq.get();
            if(zzdg0 != null) {
                this.zzjz();
                return zzdg0.zza(zzh.zzf(context0), s, view0, activity0);
            }
        }
        return "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final void zza(int v, int v1, int v2) {
        zzdg zzdg0 = (zzdg)this.zzblq.get();
        if(zzdg0 != null) {
            this.zzjz();
            zzdg0.zza(v, v1, v2);
            return;
        }
        this.zzblp.add(new Object[]{v, v1, v2});
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final void zza(MotionEvent motionEvent0) {
        zzdg zzdg0 = (zzdg)this.zzblq.get();
        if(zzdg0 != null) {
            this.zzjz();
            zzdg0.zza(motionEvent0);
            return;
        }
        this.zzblp.add(new Object[]{motionEvent0});
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final String zzb(Context context0) {
        if(this.zzjy()) {
            zzdg zzdg0 = (zzdg)this.zzblq.get();
            if(zzdg0 != null) {
                this.zzjz();
                return zzdg0.zzb(zzh.zzf(context0));
            }
        }
        return "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final void zzb(View view0) {
        zzdg zzdg0 = (zzdg)this.zzblq.get();
        if(zzdg0 != null) {
            zzdg0.zzb(view0);
        }
    }

    private static Context zzf(Context context0) {
        Context context1 = context0.getApplicationContext();
        return context1 == null ? context0 : context1;
    }

    private final boolean zzjy() {
        try {
            this.zzbls.await();
            return true;
        }
        catch(InterruptedException interruptedException0) {
            zzawf.zzd("Interrupted during GADSignals creation.", interruptedException0);
            return false;
        }
    }

    private final void zzjz() {
        if(this.zzblp.isEmpty()) {
            return;
        }
        for(Object object0: this.zzblp) {
            Object[] arr_object = (Object[])object0;
            if(arr_object.length == 1) {
                ((zzdg)this.zzblq.get()).zza(((MotionEvent)arr_object[0]));
            }
            else if(arr_object.length == 3) {
                ((zzdg)this.zzblq.get()).zza(((int)(((Integer)arr_object[0]))), ((int)(((Integer)arr_object[1]))), ((int)(((Integer)arr_object[2]))));
            }
        }
        this.zzblp.clear();
    }
}

