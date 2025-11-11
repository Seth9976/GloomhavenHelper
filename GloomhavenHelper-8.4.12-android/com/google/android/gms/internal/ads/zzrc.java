package com.google.android.gms.internal.ads;

import java.util.Comparator;

public final class zzrc implements Comparator {
    public zzrc(zzqz zzqz0) {
    }

    @Override
    public final int compare(Object object0, Object object1) {
        if(((zzqq)object0).zzmn() < ((zzqq)object1).zzmn()) {
            return -1;
        }
        if(((zzqq)object0).zzmn() > ((zzqq)object1).zzmn()) {
            return 1;
        }
        if(((zzqq)object0).zzmm() < ((zzqq)object1).zzmm()) {
            return -1;
        }
        if(((zzqq)object0).zzmm() > ((zzqq)object1).zzmm()) {
            return 1;
        }
        float f = (((zzqq)object0).zzmp() - ((zzqq)object0).zzmn()) * (((zzqq)object0).zzmo() - ((zzqq)object0).zzmm());
        float f1 = (((zzqq)object1).zzmp() - ((zzqq)object1).zzmn()) * (((zzqq)object1).zzmo() - ((zzqq)object1).zzmm());
        if(f > f1) {
            return -1;
        }
        return f < f1 ? 1 : 0;
    }
}

