package com.google.android.gms.internal.ads;

public final class zzdsy extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsy.zzhhf);
        }

        zza(zzdsz zzdsz0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private int zzhgl;
    private static final zzdsy zzhhf;

    static {
        zzdsy zzdsy0 = new zzdsy();
        zzdsy.zzhhf = zzdsy0;
        zzdyz.zza(zzdsy.class, zzdsy0);
    }

    public final int getKeySize() {
        return this.zzhgl;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsz.zzdk[v - 1]) {
            case 1: {
                return new zzdsy();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsy.zza(zzdsy.zzhhf, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000B\u0003\u000B", new Object[]{"zzhgl", "zzhgg"});
            }
            case 4: {
                return zzdsy.zzhhf;
            }
            case 5: {
                zzeau zzeau0 = zzdsy.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsy.class;
                    synchronized(class0) {
                        zzeau0 = zzdsy.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsy.zzhhf);
                            zzdsy.zzdz = zzeau0;
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

    public static zzdsy zzk(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsy)zzdyz.zza(zzdsy.zzhhf, zzdxn0, zzdym0);
    }
}

