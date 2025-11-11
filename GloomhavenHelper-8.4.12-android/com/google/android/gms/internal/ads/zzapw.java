package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzapw {
    @Nullable
    private final zzxr zzabh;
    private final AdFormat zzdjh;
    private final Context zzur;

    public zzapw(Context context0, AdFormat adFormat0, @Nullable zzxr zzxr0) {
        this.zzur = context0;
        this.zzdjh = adFormat0;
        this.zzabh = zzxr0;
    }

    public final void zza(QueryInfoGenerationCallback queryInfoGenerationCallback0) {
        zzavb zzavb0 = zzapw.zzt(this.zzur);
        if(zzavb0 == null) {
            queryInfoGenerationCallback0.onFailure("Internal Error.");
            return;
        }
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzur);
        zzuh zzuh0 = this.zzabh == null ? new zzug().zzop() : zzui.zza(this.zzur, this.zzabh);
        zzavh zzavh0 = new zzavh(null, this.zzdjh.name(), null, zzuh0);
        try {
            zzavb0.zza(iObjectWrapper0, zzavh0, new zzapy(this, queryInfoGenerationCallback0));
        }
        catch(RemoteException unused_ex) {
            queryInfoGenerationCallback0.onFailure("Internal Error.");
        }
    }

    @Nullable
    private static zzavb zzt(Context context0) {
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
        try {
            return ((zzavg)zzazk.zza(context0, "com.google.android.gms.ads.DynamiteSignalGeneratorCreatorImpl", zzapv.zzbun)).zzf(iObjectWrapper0, 20089000);
        }
        catch(zzazm | RemoteException | NullPointerException unused_ex) {
            return null;
        }
    }
}

