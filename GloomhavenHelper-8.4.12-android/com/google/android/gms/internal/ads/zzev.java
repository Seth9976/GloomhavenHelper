package com.google.android.gms.internal.ads;

import android.provider.Settings.SettingNotFoundException;
import java.lang.reflect.InvocationTargetException;

public final class zzev extends zzfv {
    public zzev(zzei zzei0, String s, String s1, zzb zzbs$zza$zzb0, int v, int v1) {
        super(zzei0, s, s1, zzbs$zza$zzb0, v, 49);
    }

    @Override  // com.google.android.gms.internal.ads.zzfv
    protected final void zzcs() throws IllegalAccessException, InvocationTargetException {
        this.zzzx.zzg(zzcd.zzld);
        try {
            boolean z = ((Boolean)this.zzaah.invoke(null, this.zzuy.getContext())).booleanValue();
            this.zzzx.zzg((z ? zzcd.zzlc : zzcd.zzlb));
        }
        catch(InvocationTargetException invocationTargetException0) {
            if(!(invocationTargetException0.getTargetException() instanceof Settings.SettingNotFoundException)) {
                throw invocationTargetException0;
            }
        }
    }
}

