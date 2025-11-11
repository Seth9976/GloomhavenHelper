package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

public final class zzeee {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double w;
    private final double zzifx;
    private final double zzify;
    private final double zzifz;
    private final double zziga;
    public static final zzeee zzigb;
    private static final zzeee zzigc;
    private static final zzeee zzigd;
    private static final zzeee zzige;

    static {
        zzeee.zzigb = new zzeee(1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        zzeee.zzigc = new zzeee(0.0, 1.0, -1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        zzeee.zzigd = new zzeee(-1.0, 0.0, 0.0, -1.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        zzeee.zzige = new zzeee(0.0, -1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
    }

    private zzeee(double f, double f1, double f2, double f3, double f4, double f5, double f6, double f7, double f8) {
        this.zzifx = f4;
        this.zzify = f5;
        this.w = f6;
        this.a = f;
        this.b = f1;
        this.c = f2;
        this.d = f3;
        this.zzifz = f7;
        this.zziga = f8;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null || this.getClass() != object0.getClass() || Double.compare(((zzeee)object0).a, this.a) != 0) {
            return false;
        }
        if(Double.compare(((zzeee)object0).b, this.b) != 0) {
            return false;
        }
        if(Double.compare(((zzeee)object0).c, this.c) != 0) {
            return false;
        }
        if(Double.compare(((zzeee)object0).d, this.d) != 0) {
            return false;
        }
        if(Double.compare(((zzeee)object0).zzifz, this.zzifz) != 0) {
            return false;
        }
        if(Double.compare(((zzeee)object0).zziga, this.zziga) != 0) {
            return false;
        }
        if(Double.compare(((zzeee)object0).zzifx, this.zzifx) != 0) {
            return false;
        }
        return Double.compare(((zzeee)object0).zzify, this.zzify) == 0 ? Double.compare(((zzeee)object0).w, this.w) == 0 : false;
    }

    @Override
    public final int hashCode() {
        long v = Double.doubleToLongBits(this.zzifx);
        long v1 = Double.doubleToLongBits(this.zzify);
        long v2 = Double.doubleToLongBits(this.w);
        long v3 = Double.doubleToLongBits(this.a);
        long v4 = Double.doubleToLongBits(this.b);
        long v5 = Double.doubleToLongBits(this.c);
        long v6 = Double.doubleToLongBits(this.d);
        long v7 = Double.doubleToLongBits(this.zzifz);
        long v8 = Double.doubleToLongBits(this.zziga);
        return (((((((((int)(v ^ v >>> 0x20)) * 0x1F + ((int)(v1 ^ v1 >>> 0x20))) * 0x1F + ((int)(v2 ^ v2 >>> 0x20))) * 0x1F + ((int)(v3 ^ v3 >>> 0x20))) * 0x1F + ((int)(v4 ^ v4 >>> 0x20))) * 0x1F + ((int)(v5 ^ v5 >>> 0x20))) * 0x1F + ((int)(v6 ^ v6 >>> 0x20))) * 0x1F + ((int)(v7 ^ v7 >>> 0x20))) * 0x1F + ((int)(v8 ^ v8 >>> 0x20));
    }

    @Override
    public final String toString() {
        if(this.equals(zzeee.zzigb)) {
            return "Rotate 0°";
        }
        if(this.equals(zzeee.zzigc)) {
            return "Rotate 90°";
        }
        if(this.equals(zzeee.zzigd)) {
            return "Rotate 180°";
        }
        return this.equals(zzeee.zzige) ? "Rotate 270°" : "Matrix{u=" + this.zzifx + ", v=" + this.zzify + ", w=" + this.w + ", a=" + this.a + ", b=" + this.b + ", c=" + this.c + ", d=" + this.d + ", tx=" + this.zzifz + ", ty=" + this.zziga + "}";
    }

    public static zzeee zzp(ByteBuffer byteBuffer0) {
        double f = zzbg.zzd(byteBuffer0);
        double f1 = zzbg.zzd(byteBuffer0);
        double f2 = zzbg.zze(byteBuffer0);
        double f3 = zzbg.zzd(byteBuffer0);
        double f4 = zzbg.zzd(byteBuffer0);
        double f5 = zzbg.zze(byteBuffer0);
        double f6 = zzbg.zzd(byteBuffer0);
        double f7 = zzbg.zzd(byteBuffer0);
        return new zzeee(f, f1, f3, f4, f2, f5, zzbg.zze(byteBuffer0), f6, f7);
    }
}

