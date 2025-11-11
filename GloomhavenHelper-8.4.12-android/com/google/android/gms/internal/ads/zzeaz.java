package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

final class zzeaz extends zzdxs {
    private final zzebb zzhwc;
    private zzdxw zzhwd;
    private final zzeba zzhwe;

    zzeaz(zzeba zzeba0) {
        this.zzhwe = zzeba0;
        super();
        this.zzhwc = new zzebb(this.zzhwe, null);
        this.zzhwd = this.zzbep();
    }

    @Override
    public final boolean hasNext() {
        return this.zzhwd != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxw
    public final byte nextByte() {
        zzdxw zzdxw0 = this.zzhwd;
        if(zzdxw0 == null) {
            throw new NoSuchElementException();
        }
        byte b = zzdxw0.nextByte();
        if(!this.zzhwd.hasNext()) {
            this.zzhwd = this.zzbep();
        }
        return b;
    }

    // 去混淆评级： 低(20)
    private final zzdxw zzbep() {
        return this.zzhwc.hasNext() ? ((zzdxw)((zzdxy)this.zzhwc.next()).iterator()) : null;
    }
}

