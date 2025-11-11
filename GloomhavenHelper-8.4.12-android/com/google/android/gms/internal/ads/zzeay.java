package com.google.android.gms.internal.ads;

final class zzeay implements zzeaf {
    private final int flags;
    private final String info;
    private final Object[] zzhvg;
    private final zzeah zzhvj;

    zzeay(zzeah zzeah0, String s, Object[] arr_object) {
        this.zzhvj = zzeah0;
        this.info = s;
        this.zzhvg = arr_object;
        int v = s.charAt(0);
        if(v < 0xD800) {
            this.flags = v;
            return;
        }
        int v1 = v & 0x1FFF;
        int v2 = 13;
        int v4;
        for(int v3 = 1; (v4 = s.charAt(v3)) >= 0xD800; ++v3) {
            v1 |= (v4 & 0x1FFF) << v2;
            v2 += 13;
        }
        this.flags = v1 | v4 << v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeaf
    public final int zzbec() {
        return (this.flags & 1) == 1 ? zzf.zzhtk : zzf.zzhtl;
    }

    @Override  // com.google.android.gms.internal.ads.zzeaf
    public final boolean zzbed() {
        return (this.flags & 2) == 2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeaf
    public final zzeah zzbee() {
        return this.zzhvj;
    }

    final String zzben() {
        return this.info;
    }

    final Object[] zzbeo() {
        return this.zzhvg;
    }
}

