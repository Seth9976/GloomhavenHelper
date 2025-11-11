package com.google.android.gms.internal.ads;

public final class zzduu extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzduu.zzhkl);
        }

        zza(zzdut zzdut0) {
        }

        public final zza zzb(zzduv zzduv0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzduu)this.zzhsu).zza(zzduv0);
            return this;
        }

        public final zza zzev(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzduu)this.zzhsu).setVersion(0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzduv zzhkk;
    private static final zzduu zzhkl;

    static {
        zzduu zzduu0 = new zzduu();
        zzduu.zzhkl = zzduu0;
        zzdyz.zza(zzduu.class, zzduu0);
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    private final void zza(zzduv zzduv0) {
        zzduv0.getClass();
        this.zzhkk = zzduv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdut.zzdk[v - 1]) {
            case 1: {
                return new zzduu();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzduu.zza(zzduu.zzhkl, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\t", new Object[]{"zzhgg", "zzhkk"});
            }
            case 4: {
                return zzduu.zzhkl;
            }
            case 5: {
                zzeau zzeau0 = zzduu.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzduu.class;
                    synchronized(class0) {
                        zzeau0 = zzduu.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzduu.zzhkl);
                            zzduu.zzdz = zzeau0;
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

    public final zzduv zzazm() {
        return this.zzhkk == null ? zzduv.zzazr() : this.zzhkk;
    }

    public static zza zzazn() {
        return (zza)zzduu.zzhkl.zzbcz();
    }

    public static zzduu zzu(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzduu)zzdyz.zza(zzduu.zzhkl, zzdxn0, zzdym0);
    }
}

