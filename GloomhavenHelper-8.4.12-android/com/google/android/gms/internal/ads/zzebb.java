package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzebb implements Iterator {
    private final ArrayDeque zzhwl;
    private zzdxy zzhwm;

    private zzebb(zzdxn zzdxn0) {
        if(zzdxn0 instanceof zzeba) {
            this.zzhwl = new ArrayDeque(((zzeba)zzdxn0).zzbav());
            this.zzhwl.push(((zzeba)zzdxn0));
            this.zzhwm = this.zzak(zzeba.zza(((zzeba)zzdxn0)));
            return;
        }
        this.zzhwl = null;
        this.zzhwm = (zzdxy)zzdxn0;
    }

    zzebb(zzdxn zzdxn0, zzeaz zzeaz0) {
        this(zzdxn0);
    }

    @Override
    public final boolean hasNext() {
        return this.zzhwm != null;
    }

    @Override
    public final Object next() {
        zzdxy zzdxy2;
        zzdxy zzdxy0 = this.zzhwm;
        if(zzdxy0 == null) {
            throw new NoSuchElementException();
        }
        while(this.zzhwl != null && !this.zzhwl.isEmpty()) {
            zzdxy zzdxy1 = this.zzak(zzeba.zzb(((zzeba)this.zzhwl.pop())));
            if(zzdxy1.isEmpty()) {
                continue;
            }
            zzdxy2 = zzdxy1;
            this.zzhwm = zzdxy2;
            return zzdxy0;
        }
        zzdxy2 = null;
        this.zzhwm = zzdxy2;
        return zzdxy0;
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final zzdxy zzak(zzdxn zzdxn0) {
        while(zzdxn0 instanceof zzeba) {
            this.zzhwl.push(((zzeba)zzdxn0));
            zzdxn0 = zzeba.zza(((zzeba)zzdxn0));
        }
        return (zzdxy)zzdxn0;
    }
}

