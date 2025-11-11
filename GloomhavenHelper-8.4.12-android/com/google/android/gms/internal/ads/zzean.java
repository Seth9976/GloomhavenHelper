package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Map.Entry;

final class zzean implements zzebd {
    private final zzeah zzhvj;
    private final boolean zzhvk;
    private final zzebv zzhvt;
    private final zzdyo zzhvu;

    private zzean(zzebv zzebv0, zzdyo zzdyo0, zzeah zzeah0) {
        this.zzhvt = zzebv0;
        this.zzhvk = zzdyo0.zzj(zzeah0);
        this.zzhvu = zzdyo0;
        this.zzhvj = zzeah0;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final boolean equals(Object object0, Object object1) {
        if(!this.zzhvt.zzbb(object0).equals(this.zzhvt.zzbb(object1))) {
            return false;
        }
        return this.zzhvk ? this.zzhvu.zzal(object0).equals(this.zzhvu.zzal(object1)) : true;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final int hashCode(Object object0) {
        int v = this.zzhvt.zzbb(object0).hashCode();
        return this.zzhvk ? v * 53 + this.zzhvu.zzal(object0).hashCode() : v;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final Object newInstance() {
        return this.zzhvj.zzbde().zzbcw();
    }

    static zzean zza(zzebv zzebv0, zzdyo zzdyo0, zzeah zzeah0) {
        return new zzean(zzebv0, zzdyo0, zzeah0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zza(Object object0, zzeax zzeax0, zzdym zzdym0) throws IOException {
        boolean z;
        zzebv zzebv0 = this.zzhvt;
        zzdyo zzdyo0 = this.zzhvu;
        Object object1 = zzebv0.zzbc(object0);
        zzdyp zzdyp0 = zzdyo0.zzam(object0);
        try {
            while(true) {
                if(zzeax0.zzbbz() == 0x7FFFFFFF) {
                    return;
                }
                int v1 = zzeax0.getTag();
                if(v1 == 11) {
                    int v2 = 0;
                    Object object3 = null;
                    zzdxn zzdxn0 = null;
                    while(zzeax0.zzbbz() != 0x7FFFFFFF) {
                        int v3 = zzeax0.getTag();
                        if(v3 == 16) {
                            v2 = zzeax0.zzbbk();
                            object3 = zzdyo0.zza(zzdym0, this.zzhvj, v2);
                        }
                        else if(v3 == 26) {
                            if(object3 == null) {
                                zzdxn0 = zzeax0.zzbbj();
                            }
                            else {
                                zzdyo0.zza(zzeax0, object3, zzdym0, zzdyp0);
                            }
                        }
                        else if(!zzeax0.zzbca()) {
                            break;
                        }
                    }
                    if(zzeax0.getTag() != 12) {
                        break;
                    }
                    if(zzdxn0 != null) {
                        if(object3 == null) {
                            zzebv0.zza(object1, v2, zzdxn0);
                        }
                        else {
                            zzdyo0.zza(zzdxn0, object3, zzdym0, zzdyp0);
                        }
                    }
                }
                else {
                    if((v1 & 7) == 2) {
                        Object object2 = zzdyo0.zza(zzdym0, this.zzhvj, v1 >>> 3);
                        if(object2 == null) {
                            z = zzebv0.zza(object1, zzeax0);
                            goto label_44;
                        }
                        else {
                            zzdyo0.zza(zzeax0, object2, zzdym0, zzdyp0);
                            goto label_43;
                        }
                    }
                    z = zzeax0.zzbca();
                    goto label_44;
                }
            label_43:
                z = true;
            label_44:
                if(!z) {
                    return;
                }
            }
        }
        finally {
            zzebv0.zzi(object0, object1);
        }
        throw zzdzh.zzbdm();
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zza(Object object0, zzecs zzecs0) throws IOException {
        for(Object object1: this.zzhvu.zzal(object0)) {
            Map.Entry map$Entry0 = (Map.Entry)object1;
            zzdyr zzdyr0 = (zzdyr)map$Entry0.getKey();
            if(zzdyr0.zzbco() != zzecp.zzhzq || zzdyr0.zzbcp() || zzdyr0.zzbcq()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if(map$Entry0 instanceof zzdzo) {
                zzecs0.zzc(zzdyr0.zzaf(), ((zzdzo)map$Entry0).zzbdt().zzbai());
            }
            else {
                zzecs0.zzc(zzdyr0.zzaf(), map$Entry0.getValue());
            }
        }
        Object object2 = this.zzhvt.zzbb(object0);
        this.zzhvt.zzc(object2, zzecs0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zza(Object object0, byte[] arr_b, int v, int v1, zzdxm zzdxm0) throws IOException {
        zzeby zzeby0 = ((zzdyz)object0).zzhsw;
        if(zzeby0 == zzeby.zzbff()) {
            zzeby0 = zzeby.zzbfg();
            ((zzdyz)object0).zzhsw = zzeby0;
        }
        ((zzb)object0).zzbdf();
        zzd zzdyz$zzd0 = null;
        while(v < v1) {
            int v2 = zzdxj.zza(arr_b, v, zzdxm0);
            int v3 = zzdxm0.zzhoa;
            if(v3 == 11) {
                int v4 = 0;
                zzdxn zzdxn0 = null;
                while(v2 < v1) {
                    v2 = zzdxj.zza(arr_b, v2, zzdxm0);
                    int v5 = zzdxm0.zzhoa;
                    int v6 = v5 & 7;
                    switch(v5 >>> 3) {
                        case 2: {
                            if(v6 == 0) {
                                v2 = zzdxj.zza(arr_b, v2, zzdxm0);
                                v4 = zzdxm0.zzhoa;
                                zzdyz$zzd0 = (zzd)this.zzhvu.zza(zzdxm0.zzhod, this.zzhvj, v4);
                                continue;
                            }
                            break;
                        }
                        case 3: {
                            if(zzdyz$zzd0 != null) {
                                throw new NoSuchMethodError();
                            }
                            if(v6 == 2) {
                                v2 = zzdxj.zze(arr_b, v2, zzdxm0);
                                zzdxn0 = (zzdxn)zzdxm0.zzhoc;
                                continue;
                            }
                        }
                    }
                    if(v5 == 12) {
                        break;
                    }
                    v2 = zzdxj.zza(v5, arr_b, v2, v1, zzdxm0);
                }
                if(zzdxn0 != null) {
                    zzeby0.zzd(v4 << 3 | 2, zzdxn0);
                }
                v = v2;
            }
            else if((v3 & 7) == 2) {
                zzdyz$zzd0 = (zzd)this.zzhvu.zza(zzdxm0.zzhod, this.zzhvj, v3 >>> 3);
                if(zzdyz$zzd0 != null) {
                    throw new NoSuchMethodError();
                }
                v = zzdxj.zza(v3, arr_b, v2, v1, zzeby0, zzdxm0);
            }
            else {
                v = zzdxj.zza(v3, arr_b, v2, v1, zzdxm0);
            }
        }
        if(v != v1) {
            throw zzdzh.zzbdp();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zzan(Object object0) {
        this.zzhvt.zzan(object0);
        this.zzhvu.zzan(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final int zzax(Object object0) {
        Object object1 = this.zzhvt.zzbb(object0);
        int v = this.zzhvt.zzbd(object1);
        return this.zzhvk ? v + this.zzhvu.zzal(object0).zzbcj() : v;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final boolean zzaz(Object object0) {
        return this.zzhvu.zzal(object0).isInitialized();
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zzf(Object object0, Object object1) {
        zzebf.zza(this.zzhvt, object0, object1);
        if(this.zzhvk) {
            zzebf.zza(this.zzhvu, object0, object1);
        }
    }
}

