package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import javax.annotation.ParametersAreNonnullByDefault;
import jeb.synthetic.FIN;

@ParametersAreNonnullByDefault
public final class zzaja {
    private final Object lock;
    private int status;
    private final zzazo zzblu;
    private final String zzdal;
    private zzaxu zzdam;
    private zzaxu zzdan;
    @Nullable
    private zzajv zzdao;
    private final Context zzur;

    public zzaja(Context context0, zzazo zzazo0, String s) {
        this.lock = new Object();
        this.status = 1;
        this.zzdal = s;
        this.zzur = context0.getApplicationContext();
        this.zzblu = zzazo0;
        this.zzdam = new zzajo();
        this.zzdan = new zzajo();
    }

    public zzaja(Context context0, zzazo zzazo0, String s, zzaxu zzaxu0, zzaxu zzaxu1) {
        this(context0, zzazo0, s);
        this.zzdam = zzaxu0;
        this.zzdan = zzaxu1;
    }

    protected final zzajv zza(@Nullable zzdq zzdq0) {
        zzajv zzajv0 = new zzajv(this.zzdan);
        zzajd zzajd0 = () -> {
            zzaid zzaid0;
            try {
                Context context0 = this.zzur;
                zzazo zzazo0 = this.zzblu;
                zzaid0 = ((Boolean)zzabu.zzcwa.get()).booleanValue() ? new zzaid(context0, zzazo0) : new zzait(context0, zzazo0, zzdq0, null);
            }
            catch(Throwable throwable0) {
                zzawf.zzc("Error creating webview.", throwable0);
                zzq.zzkz().zza(throwable0, "SdkJavascriptFactory.loadJavascriptEngine");
                zzajv0.reject();
                return;
            }
            zzaid0.zza(new zzaje(this, zzajv0, zzaid0));
            zzaid0.zza("/jsLoaded", new zzajj(this, zzajv0, zzaid0));
            zzayq zzayq0 = new zzayq();
            zzaji zzaji0 = new zzaji(this, zzdq0, zzaid0, zzayq0);
            zzayq0.set(zzaji0);
            zzaid0.zza("/requestReload", zzaji0);
            if(this.zzdal.endsWith(".js")) {
                zzaid0.zzcw(this.zzdal);
            }
            else if(this.zzdal.startsWith("<html>")) {
                zzaid0.zzcx(this.zzdal);
            }
            else {
                zzaid0.zzcy(this.zzdal);
            }
            zzajl zzajl0 = new zzajl(this, zzajv0, zzaid0);
            zzawo.zzdtx.postDelayed(zzajl0, ((long)zzajp.zzdbc));
        };
        zzazq.zzdxo.execute(zzajd0);
        zzajv0.zza(new zzajn(this, zzajv0), new zzajm(this, zzajv0));
        return zzajv0;
    }

    // 检测为 Lambda 实现
    final void zza(zzair zzair0) [...]

    final void zza(zzajv zzajv0, zzair zzair0) {
        Object object0 = this.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        switch(zzajv0.getStatus()) {
            case -1: 
            case 1: {
                FIN.finallyCodeBegin$NT(v);
                __monitor_exit(object0);
                FIN.finallyCodeEnd$NT(v);
                return;
            }
            default: {
                zzajv0.reject();
                zzair0.getClass();
                Runnable runnable0 = zzajg.zzb(zzair0);
                zzazq.zzdxo.execute(runnable0);
                zzawf.zzee("Could not receive loaded message in a timely manner. Rejecting.");
                FIN.finallyExec$NT(v);
            }
        }
    }

    // 检测为 Lambda 实现
    final void zza(zzdq zzdq0, zzajv zzajv0) [...]

    public final zzajr zzb(@Nullable zzdq zzdq0) {
        synchronized(this.lock) {
            synchronized(this.lock) {
                if(this.zzdao != null && this.status == 0) {
                    this.zzdao.zza((zzair zzair0) -> if(zzair0.isDestroyed()) {
                        this.status = 1;
                    }, zzajf.zzdau);
                }
            }
            if(this.zzdao != null && this.zzdao.getStatus() != -1) {
                switch(this.status) {
                    case 0: {
                        return this.zzdao.zzsh();
                    }
                    case 1: {
                        this.status = 2;
                        this.zza(null);
                        return this.zzdao.zzsh();
                    }
                    case 2: {
                        return this.zzdao.zzsh();
                    }
                    default: {
                        return this.zzdao.zzsh();
                    }
                }
            }
            this.status = 2;
            this.zzdao = this.zza(null);
            return this.zzdao.zzsh();
        }
    }
}

