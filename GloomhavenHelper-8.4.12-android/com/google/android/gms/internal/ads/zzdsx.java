package com.google.android.gms.internal.ads;

public final class zzdsx extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsx.zzhhe);
        }

        zza(zzdsw zzdsw0) {
        }

        public final zza zzeg(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsx)this.zzhsu).setVersion(0);
            return this;
        }

        public final zza zzw(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsx)this.zzhsu).zzs(zzdxn0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private static final zzdsx zzhhe;

    static {
        zzdsx zzdsx0 = new zzdsx();
        zzdsx.zzhhe = zzdsx0;
        zzdyz.zza(zzdsx.class, zzdsx0);
    }

    private zzdsx() {
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
        switch(zzdsw.zzdk[v - 1]) {
            case 1: {
                return new zzdsx();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsx.zza(zzdsx.zzhhe, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000B\u0003\n", new Object[]{"zzhgg", "zzhgh"});
            }
            case 4: {
                return zzdsx.zzhhe;
            }
            case 5: {
                zzeau zzeau0 = zzdsx.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsx.class;
                    synchronized(class0) {
                        zzeau0 = zzdsx.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsx.zzhhe);
                            zzdsx.zzdz = zzeau0;
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

    public static zza zzawv() {
        return (zza)zzdsx.zzhhe.zzbcz();
    }

    public static zzdsx zzj(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsx)zzdyz.zza(zzdsx.zzhhe, zzdxn0, zzdym0);
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }
}

