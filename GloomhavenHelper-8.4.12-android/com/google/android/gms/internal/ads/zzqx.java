package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

public final class zzqx {
    final long value;
    final int zzbqp;
    final String zzbqw;

    zzqx(long v, String s, int v1) {
        this.value = v;
        this.zzbqw = s;
        this.zzbqp = v1;
    }

    @Override
    public final boolean equals(@Nullable Object object0) {
        return object0 != null && object0 instanceof zzqx && ((zzqx)object0).value == this.value && ((zzqx)object0).zzbqp == this.zzbqp;
    }

    @Override
    public final int hashCode() {
        return (int)this.value;
    }
}

