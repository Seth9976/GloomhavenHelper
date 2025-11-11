package com.google.android.gms.internal.ads;

public final class zzdtq extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdtq.zzaxx());
        }

        zza(zzdtr zzdtr0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhib;
    private int zzhic;
    private zzdxn zzhid;
    private static final zzdtq zzhie;

    static {
        zzdtq zzdtq0 = new zzdtq();
        zzdtq.zzhie = zzdtq0;
        zzdyz.zza(zzdtq.class, zzdtq0);
    }

    private zzdtq() {
        this.zzhid = zzdxn.zzhoe;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtr.zzdk[v - 1]) {
            case 1: {
                return new zzdtq();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdtq.zza(zzdtq.zzhie, "\u0000\u0003\u0000\u0000\u0001\u000B\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000B\n", new Object[]{"zzhib", "zzhic", "zzhid"});
            }
            case 4: {
                return zzdtq.zzhie;
            }
            case 5: {
                zzeau zzeau0 = zzdtq.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdtq.class;
                    synchronized(class0) {
                        zzeau0 = zzdtq.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdtq.zzhie);
                            zzdtq.zzdz = zzeau0;
                        }
                        return zzeau0;
                    }
                }
                return zzeau0;
            }
            case 6: {
                return (byte)1;
            }
            case 7: {
                return null;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    public final zzdtt zzaxt() {
        zzdtt zzdtt0 = zzdtt.zzel(this.zzhib);
        return zzdtt0 == null ? zzdtt.zzhik : zzdtt0;
    }

    public final zzdtv zzaxu() {
        zzdtv zzdtv0 = zzdtv.zzem(this.zzhic);
        return zzdtv0 == null ? zzdtv.zzhir : zzdtv0;
    }

    public final zzdxn zzaxv() {
        return this.zzhid;
    }

    public static zzdtq zzaxw() {
        return zzdtq.zzhie;
    }

    static zzdtq zzaxx() {
        return zzdtq.zzhie;
    }
}

