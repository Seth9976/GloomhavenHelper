package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public final class zzdr extends zzdi {
    private zzdr(Context context0, String s, boolean z) {
        super(context0, s, z);
    }

    public static zzdr zza(String s, Context context0, boolean z) {
        zzdr.zza(context0, z);
        return new zzdr(context0, s, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzdi
    protected final List zza(zzei zzei0, Context context0, zzb zzbs$zza$zzb0, zza zzbo$zza0) {
        if(zzei0.zzcc() != null && this.zzvl) {
            int v = zzei0.zzbs();
            List list0 = new ArrayList();
            list0.addAll(super.zza(zzei0, context0, zzbs$zza$zzb0, zzbo$zza0));
            list0.add(new zzfe(zzei0, "qx4UdLiOxO035Rp2Sp5jdcIn7SxRXj1uC6nLGnC0V5PATHCv48yXpp/CIYW6LCQ1", "u/LSytBgFghDSxQZ2K1QNXzwbiqofQ/7a2BjJIFkPKQ=", zzbs$zza$zzb0, v, 24));
            return list0;
        }
        return super.zza(zzei0, context0, zzbs$zza$zzb0, zzbo$zza0);
    }
}

