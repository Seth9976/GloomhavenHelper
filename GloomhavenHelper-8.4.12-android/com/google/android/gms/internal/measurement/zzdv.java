package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzdv implements Serializable, Iterable {
    public static final zzdv zza;
    private static final zzeb zzb;
    private int zzc;
    private static final Comparator zzd;

    static {
        zzdv.zza = new zzef(zzfe.zzb);
        zzdv.zzb = new zzdz(null);
        zzdv.zzd = new zzdx();
    }

    zzdv() {
        this.zzc = 0;
    }

    @Override
    public abstract boolean equals(Object arg1);

    @Override
    public final int hashCode() {
        int v = this.zzc;
        if(v == 0) {
            int v1 = this.zza();
            v = this.zza(v1, 0, v1);
            if(v == 0) {
                v = 1;
            }
            this.zzc = v;
        }
        return v;
    }

    @Override
    public Iterator iterator() {
        return new zzdu(this);
    }

    @Override
    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), this.zza());
    }

    static int zza(byte b) {
        return b & 0xFF;
    }

    public static zzdv zza(String s) {
        return new zzef(s.getBytes(zzfe.zza));
    }

    static zzdv zza(byte[] arr_b) {
        return new zzef(arr_b);
    }

    public static zzdv zza(byte[] arr_b, int v, int v1) {
        zzdv.zzb(v, v + v1, arr_b.length);
        return new zzef(zzdv.zzb.zza(arr_b, v, v1));
    }

    public abstract byte zza(int arg1);

    public abstract int zza();

    protected abstract int zza(int arg1, int arg2, int arg3);

    public abstract zzdv zza(int arg1, int arg2);

    protected abstract String zza(Charset arg1);

    abstract void zza(zzds arg1) throws IOException;

    private static int zzb(byte b) [...] // Inlined contents

    static int zzb(int v, int v1, int v2) {
        int v3 = v1 - v;
        if((v | v1 | v3 | v2 - v1) < 0) {
            if(v < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + v + " < 0");
            }
            throw v1 >= v ? new IndexOutOfBoundsException("End index: " + v1 + " >= " + v2) : new IndexOutOfBoundsException("Beginning index larger than ending index: " + v + ", " + v1);
        }
        return v3;
    }

    abstract byte zzb(int arg1);

    public final String zzb() {
        return this.zza() == 0 ? "" : this.zza(zzfe.zza);
    }

    static zzed zzc(int v) {
        return new zzed(v, null);
    }

    public abstract boolean zzc();

    protected final int zzd() {
        return this.zzc;
    }
}

