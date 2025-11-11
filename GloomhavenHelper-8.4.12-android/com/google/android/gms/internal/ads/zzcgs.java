package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class zzcgs implements zzdil {
    private final Clock zzbmz;
    private final Map zzfvv;
    private final zzcgq zzfvw;
    private final Map zzfvx;

    public zzcgs(zzcgq zzcgq0, Set set0, Clock clock0) {
        this.zzfvw = zzcgq0;
        this.zzfvv = new HashMap();
        this.zzfvx = new HashMap();
        for(Object object0: set0) {
            zzdig zzdig0 = ((zzcgv)object0).zzfwb;
            this.zzfvx.put(zzdig0, ((zzcgv)object0));
        }
        this.zzbmz = clock0;
    }

    private final void zza(zzdig zzdig0, boolean z) {
        zzdig zzdig1 = ((zzcgv)this.zzfvx.get(zzdig0)).zzfwa;
        if(this.zzfvv.containsKey(zzdig1)) {
            long v = this.zzbmz.elapsedRealtime();
            long v1 = (long)(((Long)this.zzfvv.get(zzdig1)));
            String s = ((zzcgv)this.zzfvx.get(zzdig0)).label;
            String s1 = String.valueOf((z ? "s." : "f."));
            String s2 = Long.toString(v - v1);
            this.zzfvw.zzqv().put((s.length() == 0 ? new String("label.") : "label." + s), (s2.length() == 0 ? new String(s1) : s1 + s2));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s, Throwable throwable0) {
        if(this.zzfvv.containsKey(zzdig0)) {
            String s1 = String.valueOf(s);
            String s2 = Long.toString(this.zzbmz.elapsedRealtime() - ((long)(((Long)this.zzfvv.get(zzdig0)))));
            this.zzfvw.zzqv().put((s1.length() == 0 ? new String("task.") : "task." + s1), (s2.length() == 0 ? new String("f.") : "f." + s2));
        }
        if(this.zzfvx.containsKey(zzdig0)) {
            this.zza(zzdig0, false);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzb(zzdig zzdig0, String s) {
        Long long0 = this.zzbmz.elapsedRealtime();
        this.zzfvv.put(zzdig0, long0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzc(zzdig zzdig0, String s) {
        if(this.zzfvv.containsKey(zzdig0)) {
            String s1 = String.valueOf(s);
            String s2 = Long.toString(this.zzbmz.elapsedRealtime() - ((long)(((Long)this.zzfvv.get(zzdig0)))));
            this.zzfvw.zzqv().put((s1.length() == 0 ? new String("task.") : "task." + s1), (s2.length() == 0 ? new String("s.") : "s." + s2));
        }
        if(this.zzfvx.containsKey(zzdig0)) {
            this.zza(zzdig0, true);
        }
    }
}

