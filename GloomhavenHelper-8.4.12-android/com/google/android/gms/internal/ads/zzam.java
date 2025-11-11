package com.google.android.gms.internal.ads;

import java.util.Comparator;

final class zzam implements Comparator {
    @Override
    public final int compare(Object object0, Object object1) {
        return ((byte[])object0).length - ((byte[])object1).length;
    }
}

