package com.google.android.gms.internal.ads;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class zzaim implements zzain {
    @GuardedBy("this")
    private final Map zzczx;
    private Object zzczy;

    public zzaim() {
        this.zzczx = new HashMap();
    }

    public final void reset() {
        synchronized(this) {
            this.zzczx.clear();
        }
    }

    // 检测为 Lambda 实现
    final void zza(zzafz zzafz0, Map map0) [...]

    public final void zza(String s, Predicate predicate0) {
        synchronized(this) {
            CopyOnWriteArrayList copyOnWriteArrayList0 = (CopyOnWriteArrayList)this.zzczx.get(s);
            if(copyOnWriteArrayList0 == null) {
                return;
            }
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: copyOnWriteArrayList0) {
                zzafz zzafz0 = (zzafz)object0;
                if(predicate0.apply(zzafz0)) {
                    arrayList0.add(zzafz0);
                }
            }
            copyOnWriteArrayList0.removeAll(arrayList0);
        }
    }

    public final void zza(String s, zzafz zzafz0) {
        synchronized(this) {
            CopyOnWriteArrayList copyOnWriteArrayList0 = (CopyOnWriteArrayList)this.zzczx.get(s);
            if(copyOnWriteArrayList0 == null) {
                copyOnWriteArrayList0 = new CopyOnWriteArrayList();
                this.zzczx.put(s, copyOnWriteArrayList0);
            }
            copyOnWriteArrayList0.add(zzafz0);
        }
    }

    private final void zzb(String s, Map map0) {
        synchronized(this) {
            if(zzawf.isLoggable(2)) {
                String s1 = String.valueOf(s);
                zzawf.zzee((s1.length() == 0 ? new String("Received GMSG: ") : "Received GMSG: " + s1));
                for(Object object0: map0.keySet()) {
                    zzawf.zzee(("  " + ((String)object0) + ": " + ((String)map0.get(((String)object0)))));
                }
            }
            CopyOnWriteArrayList copyOnWriteArrayList0 = (CopyOnWriteArrayList)this.zzczx.get(s);
            if(copyOnWriteArrayList0 != null && !copyOnWriteArrayList0.isEmpty()) {
                for(Object object1: copyOnWriteArrayList0) {
                    zzaip zzaip0 = () -> ((zzafz)object1).zza(this.zzczy, map0);
                    zzazq.zzdxo.execute(zzaip0);
                }
                return;
            }
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcse)).booleanValue() && zzq.zzkz().zzve() != null) {
                zzaio zzaio0 = new zzaio(s);
                zzazq.zzdxk.execute(zzaio0);
            }
        }
    }

    public final void zzb(String s, zzafz zzafz0) {
        synchronized(this) {
            CopyOnWriteArrayList copyOnWriteArrayList0 = (CopyOnWriteArrayList)this.zzczx.get(s);
            if(copyOnWriteArrayList0 == null) {
                return;
            }
            copyOnWriteArrayList0.remove(zzafz0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzain
    public final boolean zzdd(@Nullable String s) {
        return s != null && this.zzg(Uri.parse(s));
    }

    public final void zzg(Object object0) {
        this.zzczy = object0;
    }

    public final boolean zzg(Uri uri0) {
        if("gmsg".equalsIgnoreCase(uri0.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri0.getHost())) {
            this.zzh(uri0);
            return true;
        }
        return false;
    }

    public final void zzh(Uri uri0) {
        this.zzb(uri0.getPath(), zzawo.zzi(uri0));
    }
}

