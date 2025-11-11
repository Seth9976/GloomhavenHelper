package com.google.android.gms.internal.ads;

public final class zzdsq extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsq.zzhhb);
        }

        zza(zzdsr zzdsr0) {
        }

        public final zza zzb(zzdsu zzdsu0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsq)this.zzhsu).zza(zzdsu0);
            return this;
        }

        public final zza zzef(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsq)this.zzhsu).setVersion(0);
            return this;
        }

        public final zza zzv(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsq)this.zzhsu).zzs(zzdxn0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private zzdsu zzhha;
    private static final zzdsq zzhhb;

    static {
        zzdsq zzdsq0 = new zzdsq();
        zzdsq.zzhhb = zzdsq0;
        zzdyz.zza(zzdsq.class, zzdsq0);
    }

    private zzdsq() {
        this.zzhgh = zzdxn.zzhoe;
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    private final void zza(zzdsu zzdsu0) {
        zzdsu0.getClass();
        this.zzhha = zzdsu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsr.zzdk[v - 1]) {
            case 1: {
                return new zzdsq();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsq.zza(zzdsq.zzhhb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"zzhgg", "zzhha", "zzhgh"});
            }
            case 4: {
                return zzdsq.zzhhb;
            }
            case 5: {
                zzeau zzeau0 = zzdsq.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsq.class;
                    synchronized(class0) {
                        zzeau0 = zzdsq.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsq.zzhhb);
                            zzdsq.zzdz = zzeau0;
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

    public final zzdxn zzavr() {
        return this.zzhgh;
    }

    public final zzdsu zzawp() {
        return this.zzhha == null ? zzdsu.zzawt() : this.zzhha;
    }

    public static zza zzawq() {
        return (zza)zzdsq.zzhhb.zzbcz();
    }

    public static zzdsq zzh(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsq)zzdyz.zza(zzdsq.zzhhb, zzdxn0, zzdym0);
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }
}

