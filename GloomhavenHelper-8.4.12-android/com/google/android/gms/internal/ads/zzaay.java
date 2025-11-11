package com.google.android.gms.internal.ads;

public final class zzaay extends zzaax {
    protected zzaay(String s, Object object0, int v) {
        super(s, object0, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzaax
    public final Object get() {
        if(!zzabw.zzcwd.get()) {
            throw new IllegalStateException("Striped code is accessed: 54c42518-856a-44fb-aae0-cd6676d514e5");
        }
        return super.get();
    }

    @Override  // com.google.android.gms.internal.ads.zzaax
    public static zzaax zzf(String s, boolean z) {
        return new zzaay(s, Boolean.valueOf(z), zzaaz.zzctj);
    }
}

