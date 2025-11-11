package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class zze implements zzs {
    private final Map zzn;
    private final zzc zzo;

    zze(zzc zzc0) {
        this.zzn = new HashMap();
        this.zzo = zzc0;
    }

    @Override  // com.google.android.gms.internal.ads.zzs
    public final void zza(zzq zzq0) {
        synchronized(this) {
            String s = zzq0.zze();
            List list0 = (List)this.zzn.remove(s);
            if(list0 != null && !list0.isEmpty()) {
                if(zzag.DEBUG) {
                    zzag.v("%d waiting requests for cacheKey=%s; resend to network", new Object[]{list0.size(), s});
                }
                zzq zzq1 = (zzq)list0.remove(0);
                this.zzn.put(s, list0);
                zzq1.zza(this);
                try {
                    this.zzo.zzb.put(zzq1);
                }
                catch(InterruptedException interruptedException0) {
                    zzag.e("Couldn\'t add request to queue. %s", new Object[]{interruptedException0.toString()});
                    Thread.currentThread().interrupt();
                    this.zzo.quit();
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzs
    public final void zza(zzq zzq0, zzz zzz0) {
        List list0;
        if(zzz0.zzbh != null && !zzz0.zzbh.zza()) {
            String s = zzq0.zze();
            synchronized(this) {
                list0 = (List)this.zzn.remove(s);
            }
            if(list0 != null) {
                if(zzag.DEBUG) {
                    zzag.v("Releasing %d waiting requests for cacheKey=%s.", new Object[]{list0.size(), s});
                }
                for(Object object0: list0) {
                    this.zzo.zzd.zzb(((zzq)object0), zzz0);
                }
            }
            return;
        }
        this.zza(zzq0);
    }

    private final boolean zzb(zzq zzq0) {
        synchronized(this) {
            String s = zzq0.zze();
            if(this.zzn.containsKey(s)) {
                List list0 = (List)this.zzn.get(s);
                if(list0 == null) {
                    list0 = new ArrayList();
                }
                zzq0.zzb("waiting-for-response");
                list0.add(zzq0);
                this.zzn.put(s, list0);
                if(zzag.DEBUG) {
                    zzag.d("Request for cacheKey=%s is in flight, putting on hold.", new Object[]{s});
                }
                return true;
            }
            this.zzn.put(s, null);
            zzq0.zza(this);
            if(zzag.DEBUG) {
                zzag.d("new request, sending to network %s", new Object[]{s});
            }
            return false;
        }
    }
}

