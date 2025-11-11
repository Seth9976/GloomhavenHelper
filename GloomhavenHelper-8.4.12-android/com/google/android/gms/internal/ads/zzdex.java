package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.AdSize;
import java.util.ArrayList;
import java.util.List;

public final class zzdex {
    public static zzdeh zza(List list0, zzdeh zzdeh0) {
        return (zzdeh)list0.get(0);
    }

    public static zzuk zzb(Context context0, List list0) {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: list0) {
            zzdeh zzdeh0 = (zzdeh)object0;
            if(zzdeh0.zzgpj) {
                arrayList0.add(AdSize.FLUID);
            }
            else {
                arrayList0.add(new AdSize(zzdeh0.width, zzdeh0.height));
            }
        }
        return new zzuk(context0, ((AdSize[])arrayList0.toArray(new AdSize[arrayList0.size()])));
    }

    // 去混淆评级： 低(20)
    public static zzdeh zze(zzuk zzuk0) {
        return zzuk0.zzcde ? new zzdeh(-3, 0, true) : new zzdeh(zzuk0.width, zzuk0.height, false);
    }
}

