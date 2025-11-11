package com.google.android.gms.internal.ads;

public final class zzdtb extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdtb.zzhhg);
        }

        zza(zzdta zzdta0) {
        }

        public final zza zzeh(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtb)this.zzhsu).setVersion(0);
            return this;
        }

        public final zza zzx(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtb)this.zzhsu).zzs(zzdxn0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private static final zzdtb zzhhg;

    static {
        zzdtb zzdtb0 = new zzdtb();
        zzdtb.zzhhg = zzdtb0;
        zzdyz.zza(zzdtb.class, zzdtb0);
    }

    private zzdtb() {
        this.zzhgh = zzdxn.zzhoe;
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdta.zzdk[v - 1]) {
            case 1: {
                return new zzdtb();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdtb.zza(zzdtb.zzhhg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\n", new Object[]{"zzhgg", "zzhgh"});
            }
            case 4: {
                return zzdtb.zzhhg;
            }
            case 5: {
                zzeau zzeau0 = zzdtb.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdtb.class;
                    synchronized(class0) {
                        zzeau0 = zzdtb.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdtb.zzhhg);
                            zzdtb.zzdz = zzeau0;
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

    public static zza zzawy() {
        return (zza)zzdtb.zzhhg.zzbcz();
    }

    public static zzdtb zzl(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdtb)zzdyz.zza(zzdtb.zzhhg, zzdxn0, zzdym0);
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }
}

