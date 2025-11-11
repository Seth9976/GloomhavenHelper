package com.google.android.gms.internal.ads;

public final class zzecv implements Cloneable {
    private int mSize;
    private static final zzecy zzhzv;
    private boolean zzhzw;
    private int[] zzhzx;
    private zzecy[] zzhzy;

    static {
        zzecv.zzhzv = new zzecy();
    }

    zzecv() {
        this(10);
    }

    private zzecv(int v) {
        this.zzhzw = false;
        int v1 = v << 2;
        for(int v2 = 4; v2 < 0x20; ++v2) {
            int v3 = (1 << v2) - 12;
            if(v1 <= v3) {
                v1 = v3;
                break;
            }
        }
        this.zzhzx = new int[v1 / 4];
        this.zzhzy = new zzecy[v1 / 4];
        this.mSize = 0;
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        int v = this.mSize;
        zzecv zzecv0 = new zzecv(v);
        System.arraycopy(this.zzhzx, 0, zzecv0.zzhzx, 0, v);
        for(int v1 = 0; v1 < v; ++v1) {
            zzecy[] arr_zzecy = this.zzhzy;
            if(arr_zzecy[v1] != null) {
                zzecy[] arr_zzecy1 = zzecv0.zzhzy;
                arr_zzecy1[v1] = (zzecy)arr_zzecy[v1].clone();
            }
        }
        zzecv0.mSize = v;
        return zzecv0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzecv)) {
            return false;
        }
        int v = this.mSize;
        if(v != ((zzecv)object0).mSize) {
            return false;
        }
        int[] arr_v = this.zzhzx;
        int[] arr_v1 = ((zzecv)object0).zzhzx;
        for(int v1 = 0; true; ++v1) {
            boolean z = true;
            if(v1 >= v) {
                break;
            }
            if(arr_v[v1] != arr_v1[v1]) {
                z = false;
                break;
            }
        }
        if(z) {
            zzecy[] arr_zzecy = this.zzhzy;
            zzecy[] arr_zzecy1 = ((zzecv)object0).zzhzy;
            int v2 = this.mSize;
            for(int v3 = 0; v3 < v2; ++v3) {
                if(!arr_zzecy[v3].equals(arr_zzecy1[v3])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int v = 17;
        for(int v1 = 0; v1 < this.mSize; ++v1) {
            v = (v * 0x1F + this.zzhzx[v1]) * 0x1F + this.zzhzy[v1].hashCode();
        }
        return v;
    }

    final int size() {
        return this.mSize;
    }

    final zzecy zzhd(int v) {
        return this.zzhzy[v];
    }
}

