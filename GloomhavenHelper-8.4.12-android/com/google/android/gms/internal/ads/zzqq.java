package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzqq {
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;
    private final int zzbqo;

    @VisibleForTesting
    public zzqq(float f, float f1, float f2, float f3, int v) {
        this.left = f;
        this.top = f1;
        this.right = f + f2;
        this.bottom = f1 + f3;
        this.zzbqo = v;
    }

    final float zzmm() {
        return this.left;
    }

    final float zzmn() {
        return this.top;
    }

    final float zzmo() {
        return this.right;
    }

    final float zzmp() {
        return this.bottom;
    }

    final int zzmq() {
        return this.zzbqo;
    }
}

