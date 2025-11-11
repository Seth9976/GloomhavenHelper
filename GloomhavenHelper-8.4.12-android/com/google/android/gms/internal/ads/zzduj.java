package com.google.android.gms.internal.ads;

@Deprecated
public final class zzduj extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzduj.zzhjv);
        }

        zza(zzduk zzduk0) {
        }
    }

    private static volatile zzeau zzdz;
    private String zzhiy;
    private String zzhjr;
    private int zzhjs;
    private boolean zzhjt;
    private String zzhju;
    private static final zzduj zzhjv;

    static {
        zzduj zzduj0 = new zzduj();
        zzduj.zzhjv = zzduj0;
        zzdyz.zza(zzduj.class, zzduj0);
    }

    private zzduj() {
        this.zzhjr = "";
        this.zzhiy = "";
        this.zzhju = "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzduk.zzdk[v - 1]) {
            case 1: {
                return new zzduj();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzduj.zza(zzduj.zzhjv, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000B\u0004\u0007\u0005Ȉ", new Object[]{"zzhjr", "zzhiy", "zzhjs", "zzhjt", "zzhju"});
            }
            case 4: {
                return zzduj.zzhjv;
            }
            case 5: {
                zzeau zzeau0 = zzduj.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzduj.class;
                    synchronized(class0) {
                        zzeau0 = zzduj.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzduj.zzhjv);
                            zzduj.zzdz = zzeau0;
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

    public final String zzayh() [...] // 潜在的解密器

    public final String zzayp() [...] // 潜在的解密器

    public final int zzayq() {
        return this.zzhjs;
    }

    public final boolean zzayr() {
        return this.zzhjt;
    }

    public final String zzays() [...] // 潜在的解密器
}

