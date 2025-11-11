package com.google.android.gms.internal.ads;

public final class zzhq {
    private final zzdkp zzadx;
    private final zzhr zzahm;

    public zzhq(zzdkp zzdkp0, zzhr zzhr0) {
        this.zzadx = zzhr0 == null ? null : ((zzdkp)zzob.checkNotNull(zzdkp0));
        this.zzahm = zzhr0;
    }

    public final void zza(int v, long v1, long v2) {
        if(this.zzahm != null) {
            zzhu zzhu0 = new zzhu(this, v, v1, v2);
            this.zzadx.post(zzhu0);
        }
    }

    public final void zza(zzis zzis0) {
        if(this.zzahm != null) {
            zzht zzht0 = new zzht(this, zzis0);
            this.zzadx.post(zzht0);
        }
    }

    public final void zza(String s, long v, long v1) {
        if(this.zzahm != null) {
            zzhs zzhs0 = new zzhs(this, s, v, v1);
            this.zzadx.post(zzhs0);
        }
    }

    public final void zzb(zzgz zzgz0) {
        if(this.zzahm != null) {
            zzhv zzhv0 = new zzhv(this, zzgz0);
            this.zzadx.post(zzhv0);
        }
    }

    public final void zzb(zzis zzis0) {
        if(this.zzahm != null) {
            zzhx zzhx0 = new zzhx(this, zzis0);
            this.zzadx.post(zzhx0);
        }
    }

    public final void zzr(int v) {
        if(this.zzahm != null) {
            zzhw zzhw0 = new zzhw(this, v);
            this.zzadx.post(zzhw0);
        }
    }
}

