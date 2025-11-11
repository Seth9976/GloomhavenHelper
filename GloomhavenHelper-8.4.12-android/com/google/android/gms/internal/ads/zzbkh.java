package com.google.android.gms.internal.ads;

import org.json.JSONObject;

public final class zzbkh implements zzeej {
    private final zzeew zzenn;
    private final zzeew zzffh;
    private final zzeew zzffi;
    private final zzeew zzffj;

    private zzbkh(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzffh = zzeew0;
        this.zzffi = zzeew1;
        this.zzenn = zzeew2;
        this.zzffj = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzdei zzdei0 = (zzdei)this.zzffh.get();
        Object object0 = this.zzffi.get();
        Object object1 = this.zzenn.get();
        Object object2 = this.zzffj.get();
        return (zzpo)zzeep.zza(new zzpo("10220aa6-2c04-4e9e-88a4-4ccbad252cdd", ((zzazo)object0), ((String)object2), ((JSONObject)object1), false, "native".equals(((String)object2))), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzbkh zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        return new zzbkh(zzeew0, zzeew1, zzeew2, zzeew3);
    }
}

