package com.google.android.gms.internal.ads;

public final class zzdjj extends zzdyz implements zzeaj {
    public static enum zza implements zzdzb {
        BLOCKED_REASON_UNKNOWN(1),
        BLOCKED_REASON_BACKGROUND(2);

        private final int value;
        private static final zzdze zzen;

        static {
            zza.zzen = new zzdjm();
        }

        private zza(int v1) {
            this.value = v1;
        }

        @Override
        public final String toString() {
            return "<" + this.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + this.name() + '>';
        }

        @Override  // com.google.android.gms.internal.ads.zzdzb
        public final int zzaf() {
            return this.value;
        }

        public static zzdzd zzag() {
            return zzdjl.zzew;
        }

        public static zza zzdp(int v) {
            switch(v) {
                case 1: {
                    return zza.zzgxj;
                }
                case 2: {
                    return zza.zzgxk;
                }
                default: {
                    return null;
                }
            }
        }
    }

    public static final class zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zzb() {
            super(zzdjj.zzgxi);
        }

        zzb(zzdji zzdji0) {
        }

        public final zzb zzb(zza zzdjj$zza0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdjj)this.zzhsu).zza(zzdjj$zza0);
            return this;
        }

        public final zzb zzgv(String s) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdjj)this.zzhsu).zzgu(s);
            return this;
        }
    }

    private int zzdl;
    private static volatile zzeau zzdz;
    private zzdzg zzgxd;
    private static final zzdzf zzgxe;
    private String zzgxf;
    private String zzgxg;
    private String zzgxh;
    private static final zzdjj zzgxi;

    static {
        zzdjj.zzgxe = new zzdji();
        zzdjj zzdjj0 = new zzdjj();
        zzdjj.zzgxi = zzdjj0;
        zzdyz.zza(zzdjj.class, zzdjj0);
    }

    private zzdjj() {
        this.zzgxd = zzdjj.zzbdb();
        this.zzgxf = "";
        this.zzgxg = "";
        this.zzgxh = "";
    }

    private final void zza(zza zzdjj$zza0) {
        zzdjj$zza0.getClass();
        if(!this.zzgxd.zzbam()) {
            this.zzgxd = zzdyz.zza(this.zzgxd);
        }
        this.zzgxd.zzgl(zzdjj$zza0.zzaf());
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdjk.zzdk[v - 1]) {
            case 1: {
                return new zzdjj();
            }
            case 2: {
                return new zzb(null);
            }
            case 3: {
                return zzdjj.zza(zzdjj.zzgxi, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001E\u0002\b\u0000\u0003\b\u0001\u0004\b\u0002", new Object[]{"zzdl", "zzgxd", zza.zzag(), "zzgxf", "zzgxg", "zzgxh"});
            }
            case 4: {
                return zzdjj.zzgxi;
            }
            case 5: {
                zzeau zzeau0 = zzdjj.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdjj.class;
                    synchronized(class0) {
                        zzeau0 = zzdjj.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdjj.zzgxi);
                            zzdjj.zzdz = zzeau0;
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

    public static zzb zzatg() {
        return (zzb)zzdjj.zzgxi.zzbcz();
    }

    private final void zzgu(String s) {
        s.getClass();
        this.zzdl |= 1;
        this.zzgxf = s;
    }
}

