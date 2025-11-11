package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

public abstract class zzdxn implements Serializable, Iterable {
    private int zzhno;
    public static final zzdxn zzhoe;
    private static final zzdxt zzhof;
    private static final Comparator zzhog;

    static {
        zzdxn.zzhoe = new zzdxx(zzdzc.zzhtq);
        zzdxn.zzhof = new zzdxr(null);
        zzdxn.zzhog = new zzdxp();
    }

    zzdxn() {
        this.zzhno = 0;
    }

    @Override
    public abstract boolean equals(Object arg1);

    @Override
    public final int hashCode() {
        int v = this.zzhno;
        if(v == 0) {
            int v1 = this.size();
            v = this.zzh(v1, 0, v1);
            if(v == 0) {
                v = 1;
            }
            this.zzhno = v;
        }
        return v;
    }

    public final boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator iterator() {
        return this.zzbar();
    }

    public abstract int size();

    public final byte[] toByteArray() {
        int v = this.size();
        if(v == 0) {
            return zzdzc.zzhtq;
        }
        byte[] arr_b = new byte[v];
        this.zzb(arr_b, 0, 0, v);
        return arr_b;
    }

    @Override
    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), this.size(), (this.size() > 50 ? zzebr.zzam(this.zzz(0, 0x2F)) + "..." : zzebr.zzam(this)));
    }

    private static zzdxn zza(Iterator iterator0, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", v));
        }
        if(v == 1) {
            return iterator0.next();
        }
        zzdxn zzdxn0 = zzdxn.zza(iterator0, v >>> 1);
        zzdxn zzdxn1 = zzdxn.zza(iterator0, v - (v >>> 1));
        if(0x7FFFFFFF - zzdxn0.size() < zzdxn1.size()) {
            throw new IllegalArgumentException("ByteString would be too long: " + zzdxn0.size() + "+" + zzdxn1.size());
        }
        return zzeba.zza(zzdxn0, zzdxn1);
    }

    protected abstract String zza(Charset arg1);

    abstract void zza(zzdxo arg1) throws IOException;

    @Deprecated
    public final void zza(byte[] arr_b, int v, int v1, int v2) {
        zzdxn.zzi(v, v + v2, this.size());
        zzdxn.zzi(v1, v1 + v2, arr_b.length);
        if(v2 > 0) {
            this.zzb(arr_b, v, v1, v2);
        }
    }

    static void zzaa(int v, int v1) {
        if((v1 - (v + 1) | v) < 0) {
            throw v >= 0 ? new ArrayIndexOutOfBoundsException("Index > length: " + v + ", " + v1) : new ArrayIndexOutOfBoundsException("Index < 0: " + v);
        }
    }

    private static int zzb(byte b) [...] // Inlined contents

    protected abstract void zzb(byte[] arg1, int arg2, int arg3, int arg4);

    public zzdxw zzbar() {
        return new zzdxq(this);
    }

    public final String zzbas() {
        return this.size() == 0 ? "" : this.zza(zzdzc.UTF_8);
    }

    public abstract boolean zzbat();

    public abstract zzdxz zzbau();

    protected abstract int zzbav();

    protected abstract boolean zzbaw();

    protected final int zzbax() {
        return this.zzhno;
    }

    static int zzc(byte b) {
        return b & 0xFF;
    }

    public static zzdxn zzf(InputStream inputStream0) throws IOException {
        ArrayList arrayList0 = new ArrayList();
        for(int v = 0x100; true; v = Math.min(v << 1, 0x2000)) {
            byte[] arr_b = new byte[v];
            int v1;
            for(v1 = 0; v1 < v; v1 += v2) {
                int v2 = inputStream0.read(arr_b, v1, v - v1);
                if(v2 == -1) {
                    break;
                }
            }
            zzdxn zzdxn0 = v1 == 0 ? null : zzdxn.zzh(arr_b, 0, v1);
            if(zzdxn0 == null) {
                break;
            }
            arrayList0.add(zzdxn0);
        }
        int v3 = arrayList0.size();
        return v3 == 0 ? zzdxn.zzhoe : zzdxn.zza(arrayList0.iterator(), v3);
    }

    public abstract byte zzfe(int arg1);

    abstract byte zzff(int arg1);

    static zzdxv zzfg(int v) {
        return new zzdxv(v, null);
    }

    protected abstract int zzg(int arg1, int arg2, int arg3);

    public static zzdxn zzh(byte[] arr_b, int v, int v1) {
        zzdxn.zzi(v, v + v1, arr_b.length);
        return new zzdxx(zzdxn.zzhof.zzj(arr_b, v, v1));
    }

    protected abstract int zzh(int arg1, int arg2, int arg3);

    public static zzdxn zzhi(String s) {
        return new zzdxx(s.getBytes(zzdzc.UTF_8));
    }

    static int zzi(int v, int v1, int v2) {
        int v3 = v1 - v;
        if((v | v1 | v3 | v2 - v1) < 0) {
            if(v < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + v + " < 0");
            }
            throw v1 >= v ? new IndexOutOfBoundsException("End index: " + v1 + " >= " + v2) : new IndexOutOfBoundsException("Beginning index larger than ending index: " + v + ", " + v1);
        }
        return v3;
    }

    public static zzdxn zzt(byte[] arr_b) {
        return zzdxn.zzh(arr_b, 0, arr_b.length);
    }

    static zzdxn zzu(byte[] arr_b) {
        return new zzdxx(arr_b);
    }

    public abstract zzdxn zzz(int arg1, int arg2);
}

