package com.google.android.gms.internal.ads;

public final class zzdsl extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsl.zzhgw);
        }

        zza(zzdsk zzdsk0) {
        }

        public final zza zzc(zzdsp zzdsp0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsl)this.zzhsu).zzb(zzdsp0);
            return this;
        }

        public final zza zzee(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsl)this.zzhsu).setVersion(0);
            return this;
        }

        public final zza zzu(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsl)this.zzhsu).zzs(zzdxn0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private zzdsp zzhgv;
    private static final zzdsl zzhgw;

    static {
        zzdsl zzdsl0 = new zzdsl();
        zzdsl.zzhgw = zzdsl0;
        zzdyz.zza(zzdsl.class, zzdsl0);
    }

    private zzdsl() {
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
        switch(zzdsk.zzdk[v - 1]) {
            case 1: {
                return new zzdsl();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsl.zza(zzdsl.zzhgw, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"zzhgg", "zzhgv", "zzhgh"});
            }
            case 4: {
                return zzdsl.zzhgw;
            }
            case 5: {
                zzeau zzeau0 = zzdsl.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsl.class;
                    synchronized(class0) {
                        zzeau0 = zzdsl.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsl.zzhgw);
                            zzdsl.zzdz = zzeau0;
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

    public final zzdsp zzawg() {
        return this.zzhgv == null ? zzdsp.zzawn() : this.zzhgv;
    }

    public static zza zzawh() {
        return (zza)zzdsl.zzhgw.zzbcz();
    }

    public static zzdsl zzawi() {
        return zzdsl.zzhgw;
    }

    private final void zzb(zzdsp zzdsp0) {
        zzdsp0.getClass();
        this.zzhgv = zzdsp0;
    }

    public static zzdsl zzf(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsl)zzdyz.zza(zzdsl.zzhgw, zzdxn0, zzdym0);
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }
}

