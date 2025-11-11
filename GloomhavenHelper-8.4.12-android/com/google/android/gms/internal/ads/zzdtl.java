package com.google.android.gms.internal.ads;

public final class zzdtl extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdtl.zzhhv);
        }

        zza(zzdtk zzdtk0) {
        }
    }

    private static volatile zzeau zzdz;
    private zzdtq zzhhs;
    private zzdth zzhht;
    private int zzhhu;
    private static final zzdtl zzhhv;

    static {
        zzdtl zzdtl0 = new zzdtl();
        zzdtl.zzhhv = zzdtl0;
        zzdyz.zza(zzdtl.class, zzdtl0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtk.zzdk[v - 1]) {
            case 1: {
                return new zzdtl();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdtl.zza(zzdtl.zzhhv, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zzhhs", "zzhht", "zzhhu"});
            }
            case 4: {
                return zzdtl.zzhhv;
            }
            case 5: {
                zzeau zzeau0 = zzdtl.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdtl.class;
                    synchronized(class0) {
                        zzeau0 = zzdtl.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdtl.zzhhv);
                            zzdtl.zzdz = zzeau0;
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

    public final zzdtq zzaxg() {
        return this.zzhhs == null ? zzdtq.zzaxw() : this.zzhhs;
    }

    public final zzdth zzaxh() {
        return this.zzhht == null ? zzdth.zzaxc() : this.zzhht;
    }

    public final zzdtf zzaxi() {
        zzdtf zzdtf0 = zzdtf.zzei(this.zzhhu);
        return zzdtf0 == null ? zzdtf.zzhhm : zzdtf0;
    }

    public static zzdtl zzaxj() {
        return zzdtl.zzhhv;
    }
}

