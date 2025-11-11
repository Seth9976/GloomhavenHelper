package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.Map;

public abstract class zzq implements Comparable {
    private final Object mLock;
    private final zza zzae;
    private final int zzaf;
    private final String zzag;
    private final int zzah;
    @GuardedBy("mLock")
    @Nullable
    private zzy zzai;
    private Integer zzaj;
    private zzu zzak;
    private boolean zzal;
    @GuardedBy("mLock")
    private boolean zzam;
    @GuardedBy("mLock")
    private boolean zzan;
    private boolean zzao;
    private zzad zzap;
    private zzd zzaq;
    @GuardedBy("mLock")
    private zzs zzar;

    public zzq(int v, String s, @Nullable zzy zzy0) {
        this.zzae = zza.zzbl ? new zza() : null;
        this.mLock = new Object();
        this.zzal = true;
        int v1 = 0;
        this.zzam = false;
        this.zzan = false;
        this.zzao = false;
        this.zzaq = null;
        this.zzaf = v;
        this.zzag = s;
        this.zzai = zzy0;
        this.zzap = new zzg();
        if(!TextUtils.isEmpty(s)) {
            Uri uri0 = Uri.parse(s);
            if(uri0 != null) {
                String s1 = uri0.getHost();
                if(s1 != null) {
                    v1 = s1.hashCode();
                }
            }
        }
        this.zzah = v1;
    }

    @Override
    public int compareTo(Object object0) {
        return ((int)this.zzaj) - ((int)((zzq)object0).zzaj);
    }

    public Map getHeaders() throws zzb {
        return Collections.emptyMap();
    }

    public final int getMethod() {
        return this.zzaf;
    }

    public final String getUrl() {
        return this.zzag;
    }

    public final boolean isCanceled() {
        synchronized(this.mLock) {
        }
        return false;
    }

    @Override
    public String toString() {
        String s = Integer.toHexString(this.zzah);
        return s.length() == 0 ? "[ ] " + this.zzag + " " + new String("0x") + " " + "NORMAL" + " " + this.zzaj : "[ ] " + this.zzag + " " + ("0x" + s) + " " + "NORMAL" + " " + this.zzaj;
    }

    public final zzq zza(zzd zzd0) {
        this.zzaq = zzd0;
        return this;
    }

    public final zzq zza(zzu zzu0) {
        this.zzak = zzu0;
        return this;
    }

    protected abstract zzz zza(zzo arg1);

    final void zza(int v) {
        zzu zzu0 = this.zzak;
        if(zzu0 != null) {
            zzu0.zza(this, v);
        }
    }

    final void zza(zzs zzs0) {
        synchronized(this.mLock) {
            this.zzar = zzs0;
        }
    }

    final void zza(zzz zzz0) {
        zzs zzs0;
        synchronized(this.mLock) {
            zzs0 = this.zzar;
        }
        if(zzs0 != null) {
            zzs0.zza(this, zzz0);
        }
    }

    protected abstract void zza(Object arg1);

    public final zzq zzb(int v) {
        this.zzaj = v;
        return this;
    }

    public final void zzb(zzae zzae0) {
        zzy zzy0;
        synchronized(this.mLock) {
            zzy0 = this.zzai;
        }
        if(zzy0 != null) {
            zzy0.zzc(zzae0);
        }
    }

    public final void zzb(String s) {
        if(zza.zzbl) {
            this.zzae.zza(s, Thread.currentThread().getId());
        }
    }

    final void zzc(String s) {
        zzu zzu0 = this.zzak;
        if(zzu0 != null) {
            zzu0.zzf(this);
        }
        if(zza.zzbl) {
            long v = Thread.currentThread().getId();
            if(Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzt(this, s, v));
                return;
            }
            this.zzae.zza(s, v);
            this.zzae.zzc(this.toString());
        }
    }

    public final int zzd() {
        return this.zzah;
    }

    public final String zze() {
        return this.zzaf == -1 || this.zzaf == 0 ? this.zzag : Integer.toString(this.zzaf) + '-' + this.zzag;
    }

    public final zzd zzf() {
        return this.zzaq;
    }

    public byte[] zzg() throws zzb {
        return null;
    }

    public final boolean zzh() {
        return this.zzal;
    }

    public final int zzi() {
        return this.zzap.zzb();
    }

    public final zzad zzj() {
        return this.zzap;
    }

    public final void zzk() {
        synchronized(this.mLock) {
            this.zzan = true;
        }
    }

    public final boolean zzl() {
        synchronized(this.mLock) {
        }
        return this.zzan;
    }

    final void zzm() {
        zzs zzs0;
        synchronized(this.mLock) {
            zzs0 = this.zzar;
        }
        if(zzs0 != null) {
            zzs0.zza(this);
        }
    }
}

