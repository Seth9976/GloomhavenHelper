package com.google.android.gms.internal.ads;

import java.util.List;

@Deprecated
public final class zzdva extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdva.zzhky);
        }

        zza(zzduz zzduz0) {
        }
    }

    private static volatile zzeau zzdz;
    private String zzhkw;
    private zzdzi zzhkx;
    private static final zzdva zzhky;

    static {
        zzdva zzdva0 = new zzdva();
        zzdva.zzhky = zzdva0;
        zzdyz.zza(zzdva.class, zzdva0);
    }

    private zzdva() {
        this.zzhkw = "";
        this.zzhkx = zzdva.zzbdc();
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzduz.zzdk[v - 1]) {
            case 1: {
                return new zzdva();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdva.zza(zzdva.zzhky, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001B", new Object[]{"zzhkw", "zzhkx", zzduj.class});
            }
            case 4: {
                return zzdva.zzhky;
            }
            case 5: {
                zzeau zzeau0 = zzdva.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdva.class;
                    synchronized(class0) {
                        zzeau0 = zzdva.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdva.zzhky);
                            zzdva.zzdz = zzeau0;
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

    public final List zzazt() {
        return this.zzhkx;
    }

    public static zzdva zzazu() {
        return zzdva.zzhky;
    }
}

