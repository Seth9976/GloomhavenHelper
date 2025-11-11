package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.CopyOnWriteArraySet;

final class zzgq implements zzgn {
    private int repeatMode;
    private final zzhe[] zzacx;
    private final zzni zzacy;
    private final zznf zzacz;
    private final zzdkp zzada;
    private final zzgs zzadb;
    private final CopyOnWriteArraySet zzadc;
    private final zzhk zzadd;
    private final zzhl zzade;
    private boolean zzadf;
    private boolean zzadg;
    private int zzadh;
    private int zzadi;
    private int zzadj;
    private boolean zzadk;
    private zzhj zzadl;
    private Object zzadm;
    private zzmu zzadn;
    private zznf zzado;
    private zzhf zzadp;
    private zzgu zzadq;
    private int zzadr;
    private int zzads;
    private long zzadt;

    @SuppressLint({"HandlerLeak"})
    public zzgq(zzhe[] arr_zzhe, zzni zzni0, zzhd zzhd0) {
        Log.i("ExoPlayerImpl", "Init ExoPlayerLib/2.4.2 [" + zzop.zzbhc + "]");
        zzob.checkState(arr_zzhe.length > 0);
        this.zzacx = (zzhe[])zzob.checkNotNull(arr_zzhe);
        this.zzacy = (zzni)zzob.checkNotNull(zzni0);
        this.zzadg = false;
        this.repeatMode = 0;
        this.zzadh = 1;
        this.zzadc = new CopyOnWriteArraySet();
        this.zzacz = new zznf(new zznd[arr_zzhe.length]);
        this.zzadl = zzhj.zzagp;
        this.zzadd = new zzhk();
        this.zzade = new zzhl();
        this.zzadn = zzmu.zzbdl;
        this.zzado = this.zzacz;
        this.zzadp = zzhf.zzagj;
        this.zzada = new zzgt(this, (Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper()));
        this.zzadq = new zzgu(0, 0L);
        this.zzadb = new zzgs(arr_zzhe, zzni0, zzhd0, false, 0, this.zzada, this.zzadq, this);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final long getBufferedPosition() {
        if(!this.zzadl.isEmpty() && this.zzadi <= 0) {
            this.zzadl.zza(this.zzadq.zzaeq, this.zzade, false);
            return this.zzade.zzfc() + zzgl.zzdo(this.zzadq.zzaet);
        }
        return this.zzadt;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzgn
    public final long getDuration() {
        return this.zzadl.isEmpty() ? 0x8000000000000001L : zzgl.zzdo(this.zzadl.zza(this.zzej(), this.zzadd, false).zzagy);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final int getPlaybackState() {
        return this.zzadh;
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void release() {
        this.zzadb.release();
        this.zzada.removeCallbacksAndMessages(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void seekTo(long v) {
        int v1 = this.zzej();
        if(v1 < 0 || !this.zzadl.isEmpty() && v1 >= this.zzadl.zzfa()) {
            throw new zzha(this.zzadl, v1, v);
        }
        ++this.zzadi;
        this.zzadr = v1;
        if(!this.zzadl.isEmpty()) {
            this.zzadl.zza(v1, this.zzadd, false);
            boolean z = this.zzadl.zza(0, this.zzade, false).zzagy == 0x8000000000000001L;
        }
        this.zzads = 0;
        if(v == 0x8000000000000001L) {
            this.zzadt = 0L;
            this.zzadb.zza(this.zzadl, v1, 0x8000000000000001L);
            return;
        }
        this.zzadt = v;
        this.zzadb.zza(this.zzadl, v1, zzgl.zzdp(v));
        for(Object object0: this.zzadc) {
            ((zzgm)object0).zzef();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void stop() {
        this.zzadb.stop();
    }

    final void zza(Message message0) {
        boolean z = true;
        switch(message0.what) {
            case 0: {
                --this.zzadj;
                return;
            }
            case 1: {
                this.zzadh = message0.arg1;
                for(Object object0: this.zzadc) {
                    ((zzgm)object0).zza(this.zzadg, this.zzadh);
                }
                return;
            }
            case 2: {
                if(message0.arg1 == 0) {
                    z = false;
                }
                this.zzadk = z;
                for(Object object1: this.zzadc) {
                    ((zzgm)object1).zzf(this.zzadk);
                }
                return;
            }
            case 3: {
                if(this.zzadj == 0) {
                    this.zzadf = true;
                    this.zzadn = ((zznk)message0.obj).zzben;
                    this.zzado = ((zznk)message0.obj).zzbeo;
                    this.zzacy.zzd(((zznk)message0.obj).zzbep);
                    for(Object object3: this.zzadc) {
                        ((zzgm)object3).zza(this.zzadn, this.zzado);
                    }
                    return;
                }
                break;
            }
            case 4: {
                int v = this.zzadi - 1;
                this.zzadi = v;
                if(v == 0) {
                    this.zzadq = (zzgu)message0.obj;
                    if(message0.arg1 != 0) {
                        for(Object object4: this.zzadc) {
                            ((zzgm)object4).zzef();
                        }
                        return;
                    }
                }
                break;
            }
            case 5: {
                if(this.zzadi == 0) {
                    this.zzadq = (zzgu)message0.obj;
                    for(Object object5: this.zzadc) {
                        ((zzgm)object5).zzef();
                    }
                    return;
                }
                break;
            }
            case 6: {
                zzgw zzgw0 = (zzgw)message0.obj;
                this.zzadi -= zzgw0.zzaff;
                if(this.zzadj == 0) {
                    this.zzadl = zzgw0.zzadl;
                    this.zzadm = zzgw0.zzadm;
                    this.zzadq = zzgw0.zzadq;
                    for(Object object6: this.zzadc) {
                        ((zzgm)object6).zza(this.zzadl, this.zzadm);
                    }
                    return;
                }
                break;
            }
            case 7: {
                zzhf zzhf0 = (zzhf)message0.obj;
                if(!this.zzadp.equals(zzhf0)) {
                    this.zzadp = zzhf0;
                    for(Object object7: this.zzadc) {
                        ((zzgm)object7).zza(zzhf0);
                    }
                    return;
                }
                break;
            }
            case 8: {
                zzgk zzgk0 = (zzgk)message0.obj;
                for(Object object2: this.zzadc) {
                    ((zzgm)object2).zza(zzgk0);
                }
                return;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void zza(zzgm zzgm0) {
        this.zzadc.add(zzgm0);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void zza(zzme zzme0) {
        if(!this.zzadl.isEmpty() || this.zzadm != null) {
            this.zzadl = zzhj.zzagp;
            this.zzadm = null;
            for(Object object0: this.zzadc) {
                ((zzgm)object0).zza(this.zzadl, this.zzadm);
            }
        }
        if(this.zzadf) {
            this.zzadf = false;
            this.zzadn = zzmu.zzbdl;
            this.zzado = this.zzacz;
            this.zzacy.zzd(null);
            for(Object object1: this.zzadc) {
                ((zzgm)object1).zza(this.zzadn, this.zzado);
            }
        }
        ++this.zzadj;
        this.zzadb.zza(zzme0, true);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void zza(zzgo[] arr_zzgo) {
        this.zzadb.zza(arr_zzgo);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void zzb(zzgm zzgm0) {
        this.zzadc.remove(zzgm0);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void zzb(zzgo[] arr_zzgo) {
        this.zzadb.zzb(arr_zzgo);
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final boolean zzeg() {
        return this.zzadg;
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final int zzeh() {
        return this.zzacx.length;
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final long zzei() {
        if(!this.zzadl.isEmpty() && this.zzadi <= 0) {
            this.zzadl.zza(this.zzadq.zzaeq, this.zzade, false);
            return this.zzade.zzfc() + zzgl.zzdo(this.zzadq.zzaes);
        }
        return this.zzadt;
    }

    private final int zzej() {
        if(!this.zzadl.isEmpty() && this.zzadi <= 0) {
            this.zzadl.zza(this.zzadq.zzaeq, this.zzade, false);
            return 0;
        }
        return this.zzadr;
    }

    @Override  // com.google.android.gms.internal.ads.zzgn
    public final void zzg(boolean z) {
        if(this.zzadg != z) {
            this.zzadg = z;
            this.zzadb.zzg(z);
            for(Object object0: this.zzadc) {
                ((zzgm)object0).zza(z, this.zzadh);
            }
        }
    }
}

