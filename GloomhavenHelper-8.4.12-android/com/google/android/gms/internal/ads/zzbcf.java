package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.view.Surface;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public final class zzbcf implements zzgm, zzlz, zzoc, zzpg {
    private int bytesTransferred;
    private final zzbbj zzeck;
    @VisibleForTesting
    private static int zzedd;
    @VisibleForTesting
    private static int zzede;
    private final zzbcg zzedf;
    private final zzhe zzedg;
    private final zzhe zzedh;
    private final zznc zzedi;
    private zzgn zzedj;
    private ByteBuffer zzedk;
    private boolean zzedl;
    private zzbcn zzedm;
    private Set zzedn;
    private final Context zzur;

    public zzbcf(Context context0, zzbbj zzbbj0) {
        this.zzedn = new HashSet();
        this.zzur = context0;
        this.zzeck = zzbbj0;
        this.zzedf = new zzbcg();
        this.zzedg = new zzpa(this.zzur, zzkx.zzazk, 0L, zzawo.zzdtx, this, -1);
        this.zzedh = new zzii(zzkx.zzazk);
        this.zzedi = new zzmx();
        if(zzawf.zzvx()) {
            zzawf.zzee(("ExoPlayerAdapter initialize " + this));
        }
        ++zzbcf.zzedd;
        this.zzedj = zzgr.zza(new zzhe[]{this.zzedh, this.zzedg}, this.zzedi, this.zzedf);
        this.zzedj.zza(this);
    }

    @Override
    public final void finalize() throws Throwable {
        --zzbcf.zzedd;
        if(zzawf.zzvx()) {
            zzawf.zzee(("ExoPlayerAdapter finalize " + this));
        }
    }

    public final long getBytesTransferred() {
        return (long)this.bytesTransferred;
    }

    public final void release() {
        zzgn zzgn0 = this.zzedj;
        if(zzgn0 != null) {
            zzgn0.zzb(this);
            this.zzedj.release();
            this.zzedj = null;
            --zzbcf.zzede;
        }
    }

    // 检测为 Lambda 实现
    final zzno zza(zznn zznn0) [...]

    final void zza(Surface surface0, boolean z) {
        if(this.zzedj == null) {
            return;
        }
        zzgo zzgo0 = new zzgo(this.zzedg, 1, surface0);
        if(z) {
            this.zzedj.zzb(new zzgo[]{zzgo0});
            return;
        }
        this.zzedj.zza(new zzgo[]{zzgo0});
    }

    public final void zza(zzbcn zzbcn0) {
        this.zzedm = zzbcn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzgm
    public final void zza(zzgk zzgk0) {
        zzbcn zzbcn0 = this.zzedm;
        if(zzbcn0 != null) {
            zzbcn0.zza("onPlayerError", zzgk0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzgm
    public final void zza(zzhf zzhf0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzgm
    public final void zza(zzhj zzhj0, Object object0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzgm
    public final void zza(zzmu zzmu0, zznf zznf0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzoc
    public final void zza(Object object0, zznp zznp0) {
        this.bytesTransferred = 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzgm
    public final void zza(boolean z, int v) {
        zzbcn zzbcn0 = this.zzedm;
        if(zzbcn0 != null) {
            zzbcn0.zzda(v);
        }
    }

    public final void zza(Uri[] arr_uri, String s) {
        this.zza(arr_uri, s, ByteBuffer.allocate(0), false);
    }

    public final void zza(Uri[] arr_uri, String s, ByteBuffer byteBuffer0, boolean z) {
        zzme zzme0;
        if(this.zzedj == null) {
            return;
        }
        this.zzedk = byteBuffer0;
        this.zzedl = z;
        if(arr_uri.length == 1) {
            zzme0 = this.zzb(arr_uri[0], s);
        }
        else {
            zzme[] arr_zzme = new zzme[arr_uri.length];
            for(int v = 0; v < arr_uri.length; ++v) {
                arr_zzme[v] = this.zzb(arr_uri[v], s);
            }
            zzme0 = new zzmf(arr_zzme);
        }
        this.zzedj.zza(zzme0);
        ++zzbcf.zzede;
    }

    final void zzaw(boolean z) {
        if(this.zzedj == null) {
            return;
        }
        for(int v = 0; v < this.zzedj.zzeh(); ++v) {
            this.zzedi.zzf(v, !z);
        }
    }

    @VisibleForTesting
    private final zzme zzb(Uri uri0, String s) {
        if(this.zzedl && this.zzedk.limit() > 0) {
            byte[] arr_b = new byte[this.zzedk.limit()];
            this.zzedk.get(arr_b);
            return new zzma(uri0, new zzbci(arr_b), zzbcl.zzedw, this.zzeck.zzeau, zzawo.zzdtx, this, null, this.zzeck.zzeaq);
        }
        zzbch zzbch0 = this.zzeck.zzeas > 0 ? () -> {
            zzno zzno0 = new zzbcc(s, (this.zzeck.zzeat ? null : this), this.zzeck.zzean, this.zzeck.zzeap, this.zzeck.zzeas);
            this.zzedn.add(new WeakReference(zzno0));
            return zzno0;
        } : () -> // 去混淆评级： 低(20)
        (this.zzeck.zzeat ? new zznr(s, null, null, this.zzeck.zzean, this.zzeck.zzeap, true, null) : new zznr(s, null, this, this.zzeck.zzean, this.zzeck.zzeap, true, null));
        zzbcj zzbcj0 = this.zzeck.zzeat ? () -> {
            zzno zzno0 = zzbch0.zzim();
            zzbco zzbco0 = (boolean z, long v) -> {
                zzbcn zzbcn0 = this.zzedm;
                if(zzbcn0 != null) {
                    zzbcn0.zzb(z, v);
                }
            };
            return new zzbce(this.zzur, zzno0, this, zzbco0);
        } : zzbch0;
        if(this.zzedk.limit() > 0) {
            byte[] arr_b1 = new byte[this.zzedk.limit()];
            this.zzedk.get(arr_b1);
            zzbcj0 = new zzbcm(zzbcj0, arr_b1);
        }
        return new zzma(uri0, zzbcj0, zzbcl.zzedw, this.zzeck.zzeau, zzawo.zzdtx, this, null, this.zzeck.zzeaq);
    }

    final void zzb(float f, boolean z) {
        if(this.zzedj == null) {
            return;
        }
        zzgo zzgo0 = new zzgo(this.zzedh, 2, f);
        if(z) {
            this.zzedj.zzb(new zzgo[]{zzgo0});
            return;
        }
        this.zzedj.zza(new zzgo[]{zzgo0});
    }

    @Override  // com.google.android.gms.internal.ads.zzpg
    public final void zzb(int v, int v1, int v2, float f) {
        zzbcn zzbcn0 = this.zzedm;
        if(zzbcn0 != null) {
            zzbcn0.zzn(v, v1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzpg
    public final void zzb(Surface surface0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzlz
    public final void zzb(IOException iOException0) {
        zzbcn zzbcn0 = this.zzedm;
        if(zzbcn0 != null) {
            zzbcn0.zza("onLoadError", iOException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzoc
    public final void zzc(Object object0, int v) {
        this.bytesTransferred += v;
    }

    public final void zzcz(int v) {
        for(Object object0: this.zzedn) {
            zzbcc zzbcc0 = (zzbcc)((WeakReference)object0).get();
            if(zzbcc0 != null) {
                zzbcc0.setReceiveBufferSize(v);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzpg
    public final void zzd(String s, long v, long v1) {
    }

    // 检测为 Lambda 实现
    final void zzd(boolean z, long v) [...]

    @Override  // com.google.android.gms.internal.ads.zzpg
    public final void zze(zzis zzis0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzoc
    public final void zze(Object object0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzgm
    public final void zzef() {
    }

    @Override  // com.google.android.gms.internal.ads.zzpg
    public final void zzf(int v, long v1) {
    }

    @Override  // com.google.android.gms.internal.ads.zzpg
    public final void zzf(zzis zzis0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzgm
    public final void zzf(boolean z) {
    }

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    final zzno zzfh(String s) [...]

    // 检测为 Lambda 实现
    final zzno zzfi(String s) [...]

    @Override  // com.google.android.gms.internal.ads.zzpg
    public final void zzk(zzgz zzgz0) {
    }

    public final zzgn zzzr() {
        return this.zzedj;
    }

    public static int zzzs() [...] // 潜在的解密器

    public static int zzzt() [...] // 潜在的解密器

    public final zzbcg zzzu() {
        return this.zzedf;
    }
}

