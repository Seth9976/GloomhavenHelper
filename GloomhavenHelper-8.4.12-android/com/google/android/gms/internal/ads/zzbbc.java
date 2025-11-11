package com.google.android.gms.internal.ads;

final class zzbbc implements Runnable {
    private final zzbav zzdzu;
    private final boolean zzdzx;

    zzbbc(zzbav zzbav0, boolean z) {
        this.zzdzu = zzbav0;
        this.zzdzx = z;
        super();
    }

    @Override
    public final void run() {
        this.zzdzu.zzd("windowVisibilityChanged", new String[]{"isVisible", String.valueOf(this.zzdzx)});
    }
}

