package com.google.android.gms.internal.ads;

public final class zzduc extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzduc.zzhjb);
        }

        zza(zzdud zzdud0) {
        }

        public final zza zzaf(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzduc)this.zzhsu).zzae(zzdxn0);
            return this;
        }

        public final zza zzb(zzb zzduc$zzb0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzduc)this.zzhsu).zza(zzduc$zzb0);
            return this;
        }

        public final zza zzhe(String s) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzduc)this.zzhsu).zzhd(s);
            return this;
        }
    }

    public static enum zzb implements zzdzb {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);

        private final int value;
        private static final zzdze zzen;

        static {
            zzb.zzen = new zzdue();
        }

        private zzb(int v1) {
            this.value = v1;
        }

        @Override
        public final String toString() {
            StringBuilder stringBuilder0 = new StringBuilder("<");
            stringBuilder0.append(this.getClass().getName());
            stringBuilder0.append('@');
            stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
            if(this != zzb.zzhjh) {
                stringBuilder0.append(" number=");
                stringBuilder0.append(this.zzaf());
            }
            stringBuilder0.append(" name=");
            stringBuilder0.append(this.name());
            stringBuilder0.append('>');
            return stringBuilder0.toString();
        }

        @Override  // com.google.android.gms.internal.ads.zzdzb
        public final int zzaf() {
            if(this == zzb.zzhjh) {
                throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
            }
            return this.value;
        }

        public static zzb zzeo(int v) {
            switch(v) {
                case 0: {
                    return zzb.zzhjc;
                }
                case 1: {
                    return zzb.zzhjd;
                }
                case 2: {
                    return zzb.zzhje;
                }
                case 3: {
                    return zzb.zzhjf;
                }
                case 4: {
                    return zzb.zzhjg;
                }
                default: {
                    return null;
                }
            }
        }
    }

    private static volatile zzeau zzdz;
    private String zzhiy;
    private zzdxn zzhiz;
    private int zzhja;
    private static final zzduc zzhjb;

    static {
        zzduc zzduc0 = new zzduc();
        zzduc.zzhjb = zzduc0;
        zzdyz.zza(zzduc.class, zzduc0);
    }

    private zzduc() {
        this.zzhiy = "";
        this.zzhiz = zzdxn.zzhoe;
    }

    private final void zza(zzb zzduc$zzb0) {
        this.zzhja = zzduc$zzb0.zzaf();
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdud.zzdk[v - 1]) {
            case 1: {
                return new zzduc();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzduc.zza(zzduc.zzhjb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzhiy", "zzhiz", "zzhja"});
            }
            case 4: {
                return zzduc.zzhjb;
            }
            case 5: {
                zzeau zzeau0 = zzduc.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzduc.class;
                    synchronized(class0) {
                        zzeau0 = zzduc.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzduc.zzhjb);
                            zzduc.zzdz = zzeau0;
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

    private final void zzae(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhiz = zzdxn0;
    }

    public final String zzayh() [...] // 潜在的解密器

    public final zzdxn zzayi() {
        return this.zzhiz;
    }

    public final zzb zzayj() {
        zzb zzduc$zzb0 = zzb.zzeo(this.zzhja);
        return zzduc$zzb0 == null ? zzb.zzhjh : zzduc$zzb0;
    }

    public static zza zzayk() {
        return (zza)zzduc.zzhjb.zzbcz();
    }

    public static zzduc zzayl() {
        return zzduc.zzhjb;
    }

    private final void zzhd(String s) {
        s.getClass();
        this.zzhiy = s;
    }
}

