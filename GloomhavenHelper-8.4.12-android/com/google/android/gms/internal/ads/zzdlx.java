package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzdlx extends zzdlp {
    private int zzagg;
    @NullableDecl
    private Object[] zzhal;

    public zzdlx() {
        super(4);
    }

    zzdlx(int v) {
        super(v);
        this.zzhal = new Object[zzdly.zzdx(v)];
    }

    @Override  // com.google.android.gms.internal.ads.zzdls
    public final zzdls zza(Iterator iterator0) {
        zzdlg.checkNotNull(iterator0);
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            this.zzaf(object0);
        }
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlp
    public final zzdlp zzae(Object object0) {
        return (zzdlx)this.zzaf(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlp
    public final zzdls zzaf(Object object0) {
        zzdlg.checkNotNull(object0);
        if(this.zzhal != null) {
            int v = zzdly.zzdx(this.size);
            Object[] arr_object = this.zzhal;
            if(v <= arr_object.length) {
                int v1 = arr_object.length - 1;
                int v2 = object0.hashCode();
                for(int v3 = zzdln.zzdv(v2); true; v3 = v4 + 1) {
                    int v4 = v3 & v1;
                    Object[] arr_object1 = this.zzhal;
                    Object object1 = arr_object1[v4];
                    if(object1 == null) {
                        arr_object1[v4] = object0;
                        this.zzagg += v2;
                        super.zzae(object0);
                        return this;
                    }
                    if(object1.equals(object0)) {
                        break;
                    }
                }
                return this;
            }
        }
        this.zzhal = null;
        super.zzae(object0);
        return this;
    }

    public final zzdly zzaug() {
        zzdly zzdly0;
        switch(this.size) {
            case 0: {
                return zzdmh.zzhax;
            }
            case 1: {
                return zzdly.zzah(this.zzhab[0]);
            }
            default: {
                if(this.zzhal == null || zzdly.zzdx(this.size) != this.zzhal.length) {
                    zzdly0 = zzdly.zza(this.size, this.zzhab);
                    this.size = zzdly0.size();
                }
                else {
                    zzdly0 = new zzdmh((zzdly.zzv(this.size, this.zzhab.length) ? Arrays.copyOf(this.zzhab, this.size) : this.zzhab), this.zzagg, this.zzhal, this.zzhal.length - 1, this.size);
                }
                this.zzhac = true;
                this.zzhal = null;
                return zzdly0;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdlp
    public final zzdls zze(Iterable iterable0) {
        zzdlg.checkNotNull(iterable0);
        if(this.zzhal != null) {
            for(Object object0: iterable0) {
                this.zzaf(object0);
            }
            return this;
        }
        super.zze(iterable0);
        return this;
    }
}

