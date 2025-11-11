package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzgt implements zzhc {
    private final zzgn zza;
    private final zzhu zzb;
    private final boolean zzc;
    private final zzes zzd;

    private zzgt(zzhu zzhu0, zzes zzes0, zzgn zzgn0) {
        this.zzb = zzhu0;
        this.zzc = zzes0.zza(zzgn0);
        this.zzd = zzes0;
        this.zza = zzgn0;
    }

    static zzgt zza(zzhu zzhu0, zzes zzes0, zzgn zzgn0) {
        return new zzgt(zzhu0, zzes0, zzgn0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final int zza(Object object0) {
        int v = this.zzb.zzb(object0).hashCode();
        return this.zzc ? v * 53 + this.zzd.zza(object0).hashCode() : v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final Object zza() {
        return this.zza.zzbq().zzt();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zza(Object object0, zzhd zzhd0, zzeq zzeq0) throws IOException {
        boolean z;
        zzhu zzhu0 = this.zzb;
        zzes zzes0 = this.zzd;
        Object object1 = zzhu0.zzc(object0);
        zzet zzet0 = zzes0.zzb(object0);
        try {
            while(true) {
                if(zzhd0.zza() == 0x7FFFFFFF) {
                    return;
                }
                int v1 = zzhd0.zzb();
                if(v1 == 11) {
                    int v2 = 0;
                    Object object3 = null;
                    zzdv zzdv0 = null;
                    while(zzhd0.zza() != 0x7FFFFFFF) {
                        int v3 = zzhd0.zzb();
                        if(v3 == 16) {
                            v2 = zzhd0.zzo();
                            object3 = zzes0.zza(zzeq0, this.zza, v2);
                        }
                        else if(v3 == 26) {
                            if(object3 == null) {
                                zzdv0 = zzhd0.zzn();
                            }
                            else {
                                zzes0.zza(zzhd0, object3, zzeq0, zzet0);
                            }
                        }
                        else if(!zzhd0.zzc()) {
                            break;
                        }
                    }
                    if(zzhd0.zzb() != 12) {
                        break;
                    }
                    if(zzdv0 != null) {
                        if(object3 == null) {
                            zzhu0.zza(object1, v2, zzdv0);
                        }
                        else {
                            zzes0.zza(zzdv0, object3, zzeq0, zzet0);
                        }
                    }
                }
                else {
                    if((v1 & 7) == 2) {
                        Object object2 = zzes0.zza(zzeq0, this.zza, v1 >>> 3);
                        if(object2 == null) {
                            z = zzhu0.zza(object1, zzhd0);
                            goto label_44;
                        }
                        else {
                            zzes0.zza(zzhd0, object2, zzeq0, zzet0);
                            goto label_43;
                        }
                    }
                    z = zzhd0.zzc();
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
            zzhu0.zzb(object0, object1);
        }
        throw zzfn.zze();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zza(Object object0, zzir zzir0) throws IOException {
        Iterator iterator0 = this.zzd.zza(object0).zzd();
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            Map.Entry map$Entry0 = (Map.Entry)object1;
            zzev zzev0 = (zzev)map$Entry0.getKey();
            if(zzev0.zzc() != zzio.zzi || zzev0.zzd() || zzev0.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if(map$Entry0 instanceof zzfq) {
                zzir0.zza(zzev0.zza(), ((zzfq)map$Entry0).zza().zzc());
            }
            else {
                zzir0.zza(zzev0.zza(), map$Entry0.getValue());
            }
        }
        Object object2 = this.zzb.zzb(object0);
        this.zzb.zzb(object2, zzir0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zza(Object object0, byte[] arr_b, int v, int v1, zzdq zzdq0) throws IOException {
        zzhx zzhx0 = ((zzfd)object0).zzb;
        if(zzhx0 == zzhx.zza()) {
            zzhx0 = zzhx.zzb();
            ((zzfd)object0).zzb = zzhx0;
        }
        ((zzb)object0).zza();
        zze zzfd$zze0 = null;
        while(v < v1) {
            int v2 = zzdr.zza(arr_b, v, zzdq0);
            int v3 = zzdq0.zza;
            if(v3 == 11) {
                int v4 = 0;
                zzdv zzdv0 = null;
                while(v2 < v1) {
                    v2 = zzdr.zza(arr_b, v2, zzdq0);
                    int v5 = zzdq0.zza;
                    int v6 = v5 & 7;
                    switch(v5 >>> 3) {
                        case 2: {
                            if(v6 == 0) {
                                v2 = zzdr.zza(arr_b, v2, zzdq0);
                                v4 = zzdq0.zza;
                                zzfd$zze0 = (zze)this.zzd.zza(zzdq0.zzd, this.zza, v4);
                                continue;
                            }
                            break;
                        }
                        case 3: {
                            if(zzfd$zze0 != null) {
                                throw new NoSuchMethodError();
                            }
                            if(v6 == 2) {
                                v2 = zzdr.zze(arr_b, v2, zzdq0);
                                zzdv0 = (zzdv)zzdq0.zzc;
                                continue;
                            }
                        }
                    }
                    if(v5 == 12) {
                        break;
                    }
                    v2 = zzdr.zza(v5, arr_b, v2, v1, zzdq0);
                }
                if(zzdv0 != null) {
                    zzhx0.zza(v4 << 3 | 2, zzdv0);
                }
                v = v2;
            }
            else if((v3 & 7) == 2) {
                zzfd$zze0 = (zze)this.zzd.zza(zzdq0.zzd, this.zza, v3 >>> 3);
                if(zzfd$zze0 != null) {
                    throw new NoSuchMethodError();
                }
                v = zzdr.zza(v3, arr_b, v2, v1, zzhx0, zzdq0);
            }
            else {
                v = zzdr.zza(v3, arr_b, v2, v1, zzdq0);
            }
        }
        if(v != v1) {
            throw zzfn.zzg();
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final boolean zza(Object object0, Object object1) {
        if(!this.zzb.zzb(object0).equals(this.zzb.zzb(object1))) {
            return false;
        }
        return this.zzc ? this.zzd.zza(object0).equals(this.zzd.zza(object1)) : true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final int zzb(Object object0) {
        Object object1 = this.zzb.zzb(object0);
        int v = this.zzb.zze(object1);
        return this.zzc ? v + this.zzd.zza(object0).zzg() : v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zzb(Object object0, Object object1) {
        zzhe.zza(this.zzb, object0, object1);
        if(this.zzc) {
            zzhe.zza(this.zzd, object0, object1);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zzc(Object object0) {
        this.zzb.zzd(object0);
        this.zzd.zzc(object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final boolean zzd(Object object0) {
        return this.zzd.zza(object0).zzf();
    }
}

