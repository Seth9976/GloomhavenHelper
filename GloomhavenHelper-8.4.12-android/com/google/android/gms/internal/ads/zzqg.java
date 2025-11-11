package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzqg {
    private final Object lock;
    @VisibleForTesting
    private int zzbpk;
    private List zzbpl;

    public zzqg() {
        this.lock = new Object();
        this.zzbpl = new LinkedList();
    }

    public final boolean zza(zzqd zzqd0) {
        synchronized(this.lock) {
            return this.zzbpl.contains(zzqd0);
        }
    }

    public final boolean zzb(zzqd zzqd0) {
        synchronized(this.lock) {
            Iterator iterator0 = this.zzbpl.iterator();
            while(iterator0.hasNext()) {
                Object object1 = iterator0.next();
                zzqd zzqd1 = (zzqd)object1;
                if(!zzq.zzkz().zzvk().zzvz()) {
                    if(zzqd0 == zzqd1 || !zzqd1.zzlx().equals(zzqd0.zzlx())) {
                        continue;
                    }
                    iterator0.remove();
                    return true;
                }
                if(!zzq.zzkz().zzvk().zzwb() && zzqd0 != zzqd1 && zzqd1.zzlz().equals(zzqd0.zzlz())) {
                    iterator0.remove();
                    return true;
                }
                if(false) {
                    break;
                }
            }
            return false;
        }
    }

    public final void zzc(zzqd zzqd0) {
        synchronized(this.lock) {
            if(this.zzbpl.size() >= 10) {
                zzawf.zzeb(("Queue is full, current size = " + this.zzbpl.size()));
                this.zzbpl.remove(0);
            }
            int v1 = this.zzbpk;
            this.zzbpk = v1 + 1;
            zzqd0.zzbp(v1);
            zzqd0.zzmd();
            this.zzbpl.add(zzqd0);
        }
    }

    @Nullable
    public final zzqd zzo(boolean z) {
        int v = 0;
        zzqd zzqd0 = null;
        synchronized(this.lock) {
            if(this.zzbpl.size() == 0) {
                zzawf.zzeb("Queue empty");
                return null;
            }
            if(this.zzbpl.size() >= 2) {
                int v2 = 0x80000000;
                int v3 = 0;
                for(Object object1: this.zzbpl) {
                    zzqd zzqd1 = (zzqd)object1;
                    int v4 = zzqd1.getScore();
                    if(v4 > v2) {
                        v = v3;
                        zzqd0 = zzqd1;
                        v2 = v4;
                    }
                    ++v3;
                }
                this.zzbpl.remove(v);
                return zzqd0;
            }
            zzqd zzqd2 = (zzqd)this.zzbpl.get(0);
            if(z) {
                this.zzbpl.remove(0);
            }
            else {
                zzqd2.zzma();
            }
            return zzqd2;
        }
    }
}

