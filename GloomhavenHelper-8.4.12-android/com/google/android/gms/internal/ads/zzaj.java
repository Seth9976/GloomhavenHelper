package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class zzaj {
    private final List zzbp;
    private final List zzbq;
    private int zzbr;
    private final int zzbs;
    private static final Comparator zzbt;

    static {
        zzaj.zzbt = new zzam();
    }

    public zzaj(int v) {
        this.zzbp = new ArrayList();
        this.zzbq = new ArrayList(0x40);
        this.zzbr = 0;
        this.zzbs = 0x1000;
    }

    public final void zza(byte[] arr_b) {
        synchronized(this) {
            if(arr_b != null && arr_b.length <= this.zzbs) {
                this.zzbp.add(arr_b);
                int v1 = Collections.binarySearch(this.zzbq, arr_b, zzaj.zzbt);
                if(v1 < 0) {
                    v1 = -v1 - 1;
                }
                this.zzbq.add(v1, arr_b);
                this.zzbr += arr_b.length;
                this.zzn();
            }
        }
    }

    public final byte[] zzc(int v) {
        synchronized(this) {
            for(int v2 = 0; v2 < this.zzbq.size(); ++v2) {
                byte[] arr_b = (byte[])this.zzbq.get(v2);
                if(arr_b.length >= v) {
                    this.zzbr -= arr_b.length;
                    this.zzbq.remove(v2);
                    this.zzbp.remove(arr_b);
                    return arr_b;
                }
            }
        }
        return new byte[v];
    }

    private final void zzn() {
        synchronized(this) {
            while(this.zzbr > this.zzbs) {
                byte[] arr_b = (byte[])this.zzbp.remove(0);
                this.zzbq.remove(arr_b);
                this.zzbr -= arr_b.length;
            }
        }
    }
}

