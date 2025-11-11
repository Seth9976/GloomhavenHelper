package com.google.android.gms.internal.ads;

import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzdkz {
    private final String className;
    private final zzdky zzgyr;
    private zzdky zzgys;
    private boolean zzgyt;

    private zzdkz(String s) {
        this.zzgyr = new zzdky(null);
        this.zzgys = this.zzgyr;
        this.zzgyt = false;
        this.className = (String)zzdlg.checkNotNull(s);
    }

    zzdkz(String s, zzdkw zzdkw0) {
        this(s);
    }

    @Override
    public final String toString() {
        String s = "";
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(this.className);
        stringBuilder0.append('{');
        for(zzdky zzdky0 = this.zzgyr.zzgyq; zzdky0 != null; zzdky0 = zzdky0.zzgyq) {
            Object object0 = zzdky0.value;
            stringBuilder0.append(s);
            s = ", ";
            if(object0 == null || !object0.getClass().isArray()) {
                stringBuilder0.append(object0);
            }
            else {
                String s1 = Arrays.deepToString(new Object[]{object0});
                stringBuilder0.append(s1, 1, s1.length() - 1);
            }
        }
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    public final zzdkz zzab(@NullableDecl Object object0) {
        zzdky zzdky0 = new zzdky(null);
        this.zzgys.zzgyq = zzdky0;
        this.zzgys = zzdky0;
        zzdky0.value = object0;
        return this;
    }
}

