package com.google.android.gms.internal.ads;

public final class zzbfl {
    public final int heightPixels;
    private final int type;
    public final int widthPixels;

    private zzbfl(int v, int v1, int v2) {
        this.type = v;
        this.widthPixels = v1;
        this.heightPixels = v2;
    }

    public final boolean isFluid() {
        return this.type == 2;
    }

    public static zzbfl zzabv() {
        return new zzbfl(0, 0, 0);
    }

    public static zzbfl zzabw() {
        return new zzbfl(4, 0, 0);
    }

    public static zzbfl zzabx() {
        return new zzbfl(5, 0, 0);
    }

    public final boolean zzaby() {
        return this.type == 3;
    }

    public final boolean zzabz() {
        return this.type == 0;
    }

    public final boolean zzaca() {
        return this.type == 4;
    }

    public final boolean zzacb() {
        return this.type == 5;
    }

    public static zzbfl zzb(zzuk zzuk0) {
        if(zzuk0.zzcdc) {
            return new zzbfl(3, 0, 0);
        }
        if(zzuk0.zzcde) {
            return new zzbfl(2, 0, 0);
        }
        return zzuk0.zzbml ? zzbfl.zzabv() : zzbfl.zzq(zzuk0.widthPixels, zzuk0.heightPixels);
    }

    public static zzbfl zzq(int v, int v1) {
        return new zzbfl(1, v, v1);
    }
}

