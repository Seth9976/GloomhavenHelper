package com.google.android.gms.internal.ads;

public final class zzdub extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdub.zzhix);
        }

        zza(zzdua zzdua0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgn;
    private int zzhiw;
    private static final zzdub zzhix;

    static {
        zzdub zzdub0 = new zzdub();
        zzdub.zzhix = zzdub0;
        zzdyz.zza(zzdub.class, zzdub0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdua.zzdk[v - 1]) {
            case 1: {
                return new zzdub();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdub.zza(zzdub.zzhix, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000B", new Object[]{"zzhiw", "zzhgn"});
            }
            case 4: {
                return zzdub.zzhix;
            }
            case 5: {
                zzeau zzeau0 = zzdub.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdub.class;
                    synchronized(class0) {
                        zzeau0 = zzdub.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdub.zzhix);
                            zzdub.zzdz = zzeau0;
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

    public final int zzavw() {
        return this.zzhgn;
    }

    public final zzdtv zzaye() {
        zzdtv zzdtv0 = zzdtv.zzem(this.zzhiw);
        return zzdtv0 == null ? zzdtv.zzhir : zzdtv0;
    }

    public static zzdub zzayf() {
        return zzdub.zzhix;
    }
}

