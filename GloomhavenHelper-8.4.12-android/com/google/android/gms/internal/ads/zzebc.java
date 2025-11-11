package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Arrays;

final class zzebc {
    private final ArrayDeque zzhwn;

    private zzebc() {
        this.zzhwn = new ArrayDeque();
    }

    zzebc(zzeaz zzeaz0) {
    }

    private final void zzal(zzdxn zzdxn0) {
        while(true) {
            if(zzdxn0.zzbaw()) {
                int v = zzebc.zzgv(zzdxn0.size());
                int v1 = zzeba.zzgu(v + 1);
                if(!this.zzhwn.isEmpty() && ((zzdxn)this.zzhwn.peek()).size() < v1) {
                    int v2 = zzeba.zzgu(v);
                    zzdxn zzdxn1;
                    for(zzdxn1 = (zzdxn)this.zzhwn.pop(); !this.zzhwn.isEmpty() && ((zzdxn)this.zzhwn.peek()).size() < v2; zzdxn1 = new zzeba(((zzdxn)this.zzhwn.pop()), zzdxn1, null)) {
                    }
                    zzeba zzeba0;
                    for(zzeba0 = new zzeba(zzdxn1, zzdxn0, null); !this.zzhwn.isEmpty(); zzeba0 = new zzeba(((zzdxn)this.zzhwn.pop()), zzeba0, null)) {
                        int v3 = zzeba.zzgu(zzebc.zzgv(zzeba0.size()) + 1);
                        if(((zzdxn)this.zzhwn.peek()).size() >= v3) {
                            break;
                        }
                    }
                    this.zzhwn.push(zzeba0);
                    return;
                }
                this.zzhwn.push(zzdxn0);
                return;
            }
            if(!(zzdxn0 instanceof zzeba)) {
                break;
            }
            this.zzal(zzeba.zza(((zzeba)zzdxn0)));
            zzdxn0 = zzeba.zzb(((zzeba)zzdxn0));
        }
        throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + zzdxn0.getClass());
    }

    private final zzdxn zzc(zzdxn zzdxn0, zzdxn zzdxn1) {
        this.zzal(zzdxn0);
        this.zzal(zzdxn1);
        zzdxn zzdxn2;
        for(zzdxn2 = (zzdxn)this.zzhwn.pop(); !this.zzhwn.isEmpty(); zzdxn2 = new zzeba(((zzdxn)this.zzhwn.pop()), zzdxn2, null)) {
        }
        return zzdxn2;
    }

    private static int zzgv(int v) {
        int v1 = Arrays.binarySearch(zzeba.zzhwf, v);
        return v1 >= 0 ? v1 : -(v1 + 1) - 1;
    }
}

