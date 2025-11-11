package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

public final class zzaxv {
    public final int count;
    public final String name;
    private final double zzdvd;
    private final double zzdve;
    public final double zzdvf;

    public zzaxv(String s, double f, double f1, double f2, int v) {
        this.name = s;
        this.zzdve = f;
        this.zzdvd = f1;
        this.zzdvf = f2;
        this.count = v;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof zzaxv ? Objects.equal(this.name, ((zzaxv)object0).name) && this.zzdvd == ((zzaxv)object0).zzdvd && this.zzdve == ((zzaxv)object0).zzdve && this.count == ((zzaxv)object0).count && Double.compare(this.zzdvf, ((zzaxv)object0).zzdvf) == 0 : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.name, this.zzdvd, this.zzdve, this.zzdvf, this.count});
    }

    @Override
    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("minBound", this.zzdve).add("maxBound", this.zzdvd).add("percent", this.zzdvf).add("count", this.count).toString();
    }
}

