package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

public final class zzarp extends zzaru {
    private final String type;
    private final int zzdot;

    public zzarp(String s, int v) {
        this.type = s;
        this.zzdot = v;
    }

    // 去混淆评级： 低(30)
    @Override
    public final boolean equals(Object object0) {
        return object0 != null && object0 instanceof zzarp && Objects.equal(this.type, ((zzarp)object0).type) && Objects.equal(this.zzdot, ((zzarp)object0).zzdot);
    }

    @Override  // com.google.android.gms.internal.ads.zzarr
    public final int getAmount() {
        return this.zzdot;
    }

    @Override  // com.google.android.gms.internal.ads.zzarr
    public final String getType() {
        return this.type;
    }
}

