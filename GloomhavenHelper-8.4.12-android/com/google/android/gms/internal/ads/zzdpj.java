package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class zzdpj {
    private final Class zzhdy;
    private final Map zzhdz;
    private final Class zzhea;

    @SafeVarargs
    protected zzdpj(Class class0, zzdpl[] arr_zzdpl) {
        this.zzhdy = class0;
        HashMap hashMap0 = new HashMap();
        for(int v = 0; v < arr_zzdpl.length; ++v) {
            zzdpl zzdpl0 = arr_zzdpl[v];
            if(hashMap0.containsKey(zzdpl0.zzauy())) {
                String s = zzdpl0.zzauy().getCanonicalName();
                throw new IllegalArgumentException((s.length() == 0 ? new String("KeyTypeManager constructed with duplicate factories for primitive ") : "KeyTypeManager constructed with duplicate factories for primitive " + s));
            }
            hashMap0.put(zzdpl0.zzauy(), zzdpl0);
        }
        this.zzhea = arr_zzdpl.length > 0 ? arr_zzdpl[0].zzauy() : Void.class;
        this.zzhdz = Collections.unmodifiableMap(hashMap0);
    }

    public abstract String getKeyType();

    public final Object zza(zzeah zzeah0, Class class0) throws GeneralSecurityException {
        zzdpl zzdpl0 = (zzdpl)this.zzhdz.get(class0);
        if(zzdpl0 == null) {
            throw new IllegalArgumentException("Requested primitive class " + class0.getCanonicalName() + " not supported.");
        }
        return zzdpl0.zzak(zzeah0);
    }

    public final Class zzavb() {
        return this.zzhdy;
    }

    public abstract zzb zzavc();

    public final Set zzavd() {
        return this.zzhdz.keySet();
    }

    final Class zzave() {
        return this.zzhea;
    }

    public zzdpi zzavf() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract void zze(zzeah arg1) throws GeneralSecurityException;

    public abstract zzeah zzr(zzdxn arg1) throws zzdzh;
}

