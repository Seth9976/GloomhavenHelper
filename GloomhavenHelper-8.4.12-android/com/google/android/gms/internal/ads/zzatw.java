package com.google.android.gms.internal.ads;

final class zzatw implements zzdnu {
    private final zzdof zzdpw;

    zzatw(zzats zzats0, zzdof zzdof0) {
        this.zzdpw = zzdof0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        Void void0 = (Void)object0;
        zzats.zzdpi.remove(this.zzdpw);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        zzats.zzdpi.remove(this.zzdpw);
    }
}

