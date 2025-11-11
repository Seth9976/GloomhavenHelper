package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public final class zzawc implements zzqk {
    private final Object lock;
    private final zzawh zzdsq;
    private final zzawa zzdsw;
    @VisibleForTesting
    private final zzavy zzdsx;
    @VisibleForTesting
    private final HashSet zzdsy;
    @VisibleForTesting
    private final HashSet zzdsz;

    public zzawc(String s, zzawh zzawh0) {
        this.lock = new Object();
        this.zzdsy = new HashSet();
        this.zzdsz = new HashSet();
        this.zzdsx = new zzavy(s, zzawh0);
        this.zzdsq = zzawh0;
        this.zzdsw = new zzawa();
    }

    public final Bundle zza(Context context0, zzavx zzavx0) {
        HashSet hashSet0 = new HashSet();
        synchronized(this.lock) {
            hashSet0.addAll(this.zzdsy);
            this.zzdsy.clear();
        }
        Bundle bundle0 = new Bundle();
        bundle0.putBundle("app", this.zzdsx.zzo(context0, "0"));
        Bundle bundle1 = new Bundle();
        Iterator iterator0 = this.zzdsz.iterator();
        if(!iterator0.hasNext()) {
            bundle0.putBundle("slots", bundle1);
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: hashSet0) {
                arrayList0.add(((zzavq)object0).toBundle());
            }
            bundle0.putParcelableArrayList("ads", arrayList0);
            zzavx0.zza(hashSet0);
            return bundle0;
        }
        Object object1 = iterator0.next();
        zzavz zzavz0 = (zzavz)object1;
        throw new NoSuchMethodError();
    }

    // 去混淆评级： 低(20)
    public final zzavq zza(Clock clock0, String s) {
        return new zzavq(clock0, this, "1", s);
    }

    public final void zza(zzuh zzuh0, long v) {
        synchronized(this.lock) {
            this.zzdsx.zza(zzuh0, v);
        }
    }

    public final void zzb(zzavq zzavq0) {
        synchronized(this.lock) {
            this.zzdsy.add(zzavq0);
        }
    }

    public final void zzb(HashSet hashSet0) {
        synchronized(this.lock) {
            this.zzdsy.addAll(hashSet0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzqk
    public final void zzp(boolean z) {
        long v = zzq.zzlc().currentTimeMillis();
        if(z) {
            if(v - this.zzdsq.zzwg() > ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcjv))))) {
                this.zzdsx.zzdsn = -1;
                return;
            }
            this.zzdsx.zzdsn = this.zzdsq.zzwh();
            return;
        }
        this.zzdsq.zzez(v);
        this.zzdsq.zzcq(this.zzdsx.zzdsn);
    }

    public final void zzva() {
        synchronized(this.lock) {
            this.zzdsx.zzva();
        }
    }

    public final void zzvb() {
        synchronized(this.lock) {
            this.zzdsx.zzvb();
        }
    }
}

