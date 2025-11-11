package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

final class zzlv implements zzoa {
    private final Uri uri;
    private final zzno zzamy;
    private final zzls zzbab;
    private final zzly zzbaj;
    private final zzod zzbak;
    private final zzjm zzbbd;
    private volatile boolean zzbbe;
    private boolean zzbbf;
    private long zzbbg;
    private long zzce;

    public zzlv(zzls zzls0, Uri uri0, zzno zzno0, zzly zzly0, zzod zzod0) {
        this.zzbab = zzls0;
        super();
        this.uri = (Uri)zzob.checkNotNull(uri0);
        this.zzamy = (zzno)zzob.checkNotNull(zzno0);
        this.zzbaj = (zzly)zzob.checkNotNull(zzly0);
        this.zzbak = zzod0;
        this.zzbbd = new zzjm();
        this.zzbbf = true;
        this.zzce = -1L;
    }

    @Override  // com.google.android.gms.internal.ads.zzoa
    public final void cancelLoad() {
        this.zzbbe = true;
    }

    public final void zze(long v, long v1) {
        this.zzbbd.zzana = v;
        this.zzbbg = v1;
        this.zzbbf = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzoa
    public final boolean zzhu() {
        return this.zzbbe;
    }

    @Override  // com.google.android.gms.internal.ads.zzoa
    public final void zzhv() throws IOException, InterruptedException {
        zzjd zzjd0;
        long v1;
        int v = 0;
        while(v == 0 && !this.zzbbe) {
            try {
                v1 = this.zzbbd.zzana;
                zznp zznp0 = new zznp(this.uri, v1, -1L, zzls.zzf(this.zzbab));
                this.zzce = this.zzamy.zza(zznp0);
                if(this.zzce != -1L) {
                    this.zzce += v1;
                }
                zzjd0 = new zzjd(this.zzamy, v1, this.zzce);
            }
            catch(Throwable throwable0) {
                zzjd0 = null;
                goto label_26;
            }
            try {
                Uri uri0 = this.zzamy.getUri();
                zzjg zzjg0 = this.zzbaj.zza(zzjd0, uri0);
                if(this.zzbbf) {
                    zzjg0.zzc(v1, this.zzbbg);
                    this.zzbbf = false;
                }
                while(true) {
                    if(v != 0 || this.zzbbe) {
                        goto label_30;
                    }
                    this.zzbak.block();
                    v = zzjg0.zza(zzjd0, this.zzbbd);
                    if(zzjd0.getPosition() > zzls.zzg(this.zzbab) + v1) {
                        v1 = zzjd0.getPosition();
                        this.zzbak.zzir();
                        zzls.zzi(this.zzbab).post(zzls.zzh(this.zzbab));
                    }
                }
            }
            catch(Throwable throwable0) {
            }
        label_26:
            if(v != 1 && zzjd0 != null) {
                this.zzbbd.zzana = zzjd0.getPosition();
            }
            zzop.zza(this.zzamy);
            throw throwable0;
        label_30:
            if(v == 1) {
                v = 0;
            }
            else {
                this.zzbbd.zzana = zzjd0.getPosition();
            }
            zzop.zza(this.zzamy);
        }
    }
}

