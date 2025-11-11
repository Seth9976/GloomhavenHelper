package com.google.android.gms.internal.ads;

public final class zzdui extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdui.zzhjq);
        }

        zza(zzduh zzduh0) {
        }
    }

    private static volatile zzeau zzdz;
    private String zzhiy;
    private zzdxn zzhiz;
    private int zzhjp;
    private static final zzdui zzhjq;

    static {
        zzdui zzdui0 = new zzdui();
        zzdui.zzhjq = zzdui0;
        zzdyz.zza(zzdui.class, zzdui0);
    }

    private zzdui() {
        this.zzhiy = "";
        this.zzhiz = zzdxn.zzhoe;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzduh.zzdk[v - 1]) {
            case 1: {
                return new zzdui();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdui.zza(zzdui.zzhjq, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzhiy", "zzhiz", "zzhjp"});
            }
            case 4: {
                return zzdui.zzhjq;
            }
            case 5: {
                zzeau zzeau0 = zzdui.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdui.class;
                    synchronized(class0) {
                        zzeau0 = zzdui.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdui.zzhjq);
                            zzdui.zzdz = zzeau0;
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

    public final zzdxn zzayi() {
        return this.zzhiz;
    }

    public static zzdui zzayn() {
        return zzdui.zzhjq;
    }
}

