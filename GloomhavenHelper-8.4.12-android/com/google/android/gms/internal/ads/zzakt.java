package com.google.android.gms.internal.ads;

public final class zzakt {
    private zzaja zzdch;
    private zzdof zzdci;

    zzakt(zzaja zzaja0) {
        this.zzdch = zzaja0;
    }

    public final zzakw zzb(String s, zzakh zzakh0, zzake zzake0) {
        this.zzsn();
        return new zzakw(this.zzdci, s, zzakh0, zzake0);
    }

    public final void zzc(String s, zzafz zzafz0) {
        this.zzsn();
        this.zzdci = zzdnt.zzb(this.zzdci, new zzaku(s, zzafz0), zzazq.zzdxp);
    }

    public final void zzd(String s, zzafz zzafz0) {
        this.zzdci = zzdnt.zzb(this.zzdci, new zzakx(s, zzafz0), zzazq.zzdxp);
    }

    private final void zzsn() {
        if(this.zzdci == null) {
            zzazy zzazy0 = new zzazy();
            this.zzdci = zzazy0;
            this.zzdch.zzb(null).zza(new zzaks(zzazy0), new zzakv(zzazy0));
        }
    }
}

