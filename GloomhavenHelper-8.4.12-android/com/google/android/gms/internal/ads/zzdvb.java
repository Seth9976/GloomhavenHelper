package com.google.android.gms.internal.ads;

public final class zzdvb extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdvb.zzhkz);
        }

        zza(zzdvc zzdvc0) {
        }

        public final zza zzag(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdvb)this.zzhsu).zzs(zzdxn0);
            return this;
        }

        public final zza zzex(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdvb)this.zzhsu).setVersion(0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private static final zzdvb zzhkz;

    static {
        zzdvb zzdvb0 = new zzdvb();
        zzdvb.zzhkz = zzdvb0;
        zzdyz.zza(zzdvb.class, zzdvb0);
    }

    private zzdvb() {
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
        switch(zzdvc.zzdk[v - 1]) {
            case 1: {
                return new zzdvb();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdvb.zza(zzdvb.zzhkz, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000B\u0003\n", new Object[]{"zzhgg", "zzhgh"});
            }
            case 4: {
                return zzdvb.zzhkz;
            }
            case 5: {
                zzeau zzeau0 = zzdvb.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdvb.class;
                    synchronized(class0) {
                        zzeau0 = zzdvb.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdvb.zzhkz);
                            zzdvb.zzdz = zzeau0;
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

    public static zza zzazw() {
        return (zza)zzdvb.zzhkz.zzbcz();
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }

    public static zzdvb zzw(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdvb)zzdyz.zza(zzdvb.zzhkz, zzdxn0, zzdym0);
    }
}

