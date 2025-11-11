package com.google.android.gms.internal.ads;

public final class zzcli implements zzbqm, zzbrn {
    private static final Object zzfzx;
    private static int zzfzy;
    private final zzclr zzfzz;

    static {
        zzcli.zzfzx = new Object();
        zzcli.zzfzy = 0;
    }

    public zzcli(zzclr zzclr0) {
        this.zzfzz = zzclr0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue() && zzcli.zzanv()) {
            this.zzfzz.zzbl(false);
            zzcli.zzanu();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcre)).booleanValue() && zzcli.zzanv()) {
            this.zzfzz.zzbl(true);
            zzcli.zzanu();
        }
    }

    private static void zzanu() {
        synchronized(zzcli.zzfzx) {
            ++zzcli.zzfzy;
        }
    }

    private static boolean zzanv() {
        synchronized(zzcli.zzfzx) {
            return zzcli.zzfzy < ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrf))));
        }
    }
}

