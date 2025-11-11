package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

public final class zzbkb implements zzp, zzbqt, zzbqu, zzpt {
    private final Clock zzbmz;
    private final zzbjs zzfek;
    private final zzbjz zzfel;
    private final Set zzfem;
    private final zzakw zzfen;
    private final Executor zzfeo;
    private final AtomicBoolean zzfep;
    @GuardedBy("this")
    private final zzbkd zzfeq;
    private boolean zzfer;
    private WeakReference zzfes;

    public zzbkb(zzakt zzakt0, zzbjz zzbjz0, Executor executor0, zzbjs zzbjs0, Clock clock0) {
        this.zzfem = new HashSet();
        this.zzfep = new AtomicBoolean(false);
        this.zzfeq = new zzbkd();
        this.zzfer = false;
        this.zzfes = new WeakReference(this);
        this.zzfek = zzbjs0;
        this.zzfen = zzakt0.zzb("google.afma.activeView.handleUpdate", zzakj.zzdbu, zzakj.zzdbu);
        this.zzfel = zzbjz0;
        this.zzfeo = executor0;
        this.zzbmz = clock0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        synchronized(this) {
            if(this.zzfep.compareAndSet(false, true)) {
                this.zzfek.zza(this);
                this.zzafw();
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
        synchronized(this) {
            this.zzfeq.zzfey = true;
            this.zzafw();
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
        synchronized(this) {
            this.zzfeq.zzfey = false;
            this.zzafw();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        synchronized(this) {
            this.zzfeq.zzbnz = zzpu0.zzbnz;
            this.zzfeq.zzffb = zzpu0;
            this.zzafw();
        }
    }

    public final void zzafw() {
        synchronized(this) {
            if(this.zzfes.get() == null) {
                this.zzafy();
                return;
            }
            if(!this.zzfer && this.zzfep.get()) {
                try {
                    this.zzfeq.timestamp = this.zzbmz.elapsedRealtime();
                    JSONObject jSONObject0 = this.zzfel.zza(this.zzfeq);
                    for(Object object0: this.zzfem) {
                        zzbka zzbka0 = new zzbka(((zzbdv)object0), jSONObject0);
                        this.zzfeo.execute(zzbka0);
                    }
                    zzazu.zzb(this.zzfen.zzf(jSONObject0), "ActiveViewListener.callActiveViewJs");
                }
                catch(Exception exception0) {
                    zzawf.zza("Failed to call ActiveViewJS", exception0);
                }
            }
        }
    }

    private final void zzafx() {
        for(Object object0: this.zzfem) {
            this.zzfek.zze(((zzbdv)object0));
        }
        this.zzfek.zzafv();
    }

    public final void zzafy() {
        synchronized(this) {
            this.zzafx();
            this.zzfer = true;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzby(@Nullable Context context0) {
        synchronized(this) {
            this.zzfeq.zzfey = true;
            this.zzafw();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzbz(@Nullable Context context0) {
        synchronized(this) {
            this.zzfeq.zzfey = false;
            this.zzafw();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzca(@Nullable Context context0) {
        synchronized(this) {
            this.zzfeq.zzffa = "u";
            this.zzafw();
            this.zzafx();
            this.zzfer = true;
        }
    }

    public final void zzf(zzbdv zzbdv0) {
        synchronized(this) {
            this.zzfem.add(zzbdv0);
            this.zzfek.zzd(zzbdv0);
        }
    }

    public final void zzo(Object object0) {
        this.zzfes = new WeakReference(object0);
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
    }
}

