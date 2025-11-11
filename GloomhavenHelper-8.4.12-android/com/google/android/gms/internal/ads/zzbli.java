package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;

final class zzbli extends zzblg {
    private final View view;
    @Nullable
    private final zzbdv zzdae;
    private final Executor zzfeo;
    private final zzdeh zzffr;
    private final zzbnc zzfgd;
    private final zzbzg zzfge;
    private final zzbuz zzfgf;
    private final zzeed zzfgg;
    private zzuk zzfgh;
    private final Context zzur;

    zzbli(zzbne zzbne0, Context context0, zzdeh zzdeh0, View view0, @Nullable zzbdv zzbdv0, zzbnc zzbnc0, zzbzg zzbzg0, zzbuz zzbuz0, zzeed zzeed0, Executor executor0) {
        super(zzbne0);
        this.zzur = context0;
        this.view = view0;
        this.zzdae = zzbdv0;
        this.zzffr = zzdeh0;
        this.zzfgd = zzbnc0;
        this.zzfge = zzbzg0;
        this.zzfgf = zzbuz0;
        this.zzfgg = zzeed0;
        this.zzfeo = executor0;
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final zzxj getVideoController() {
        try {
            return this.zzfgd.getVideoController();
        }
        catch(zzdfa unused_ex) {
            return null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final void zza(ViewGroup viewGroup0, zzuk zzuk0) {
        if(viewGroup0 != null) {
            zzbdv zzbdv0 = this.zzdae;
            if(zzbdv0 != null) {
                zzbdv0.zza(zzbfl.zzb(zzuk0));
                viewGroup0.setMinimumHeight(zzuk0.heightPixels);
                viewGroup0.setMinimumWidth(zzuk0.widthPixels);
                this.zzfgh = zzuk0;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final zzdeh zzagl() {
        zzuk zzuk0 = this.zzfgh;
        if(zzuk0 != null) {
            return zzdex.zze(zzuk0);
        }
        if(this.zzfhg.zzgqc) {
            for(Object object0: this.zzfhg.zzgpk) {
                if(((String)object0) != null && ((String)object0).contains("FirstParty")) {
                    return zzdex.zza(this.zzfhg.zzgpp, this.zzffr);
                }
                if(false) {
                    break;
                }
            }
            return new zzdeh(this.view.getWidth(), this.view.getHeight(), false);
        }
        return zzdex.zza(this.zzfhg.zzgpp, this.zzffr);
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final View zzagm() {
        return this.view;
    }

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final int zzagr() {
        return this.zzfdp.zzgqm.zzgqj.zzgqf;
    }

    @Override  // com.google.android.gms.internal.ads.zzbnf
    public final void zzags() {
        zzbll zzbll0 = () -> if(this.zzfge.zzakw() != null) {
            try {
                zzvx zzvx0 = (zzvx)this.zzfgg.get();
                IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzur);
                this.zzfge.zzakw().zza(zzvx0, iObjectWrapper0);
            }
            catch(RemoteException remoteException0) {
                zzawf.zzc("RemoteException when notifyAdLoad is called", remoteException0);
            }
        };
        this.zzfeo.execute(zzbll0);
        super.zzags();
    }

    // 检测为 Lambda 实现
    final void zzagt() [...]

    @Override  // com.google.android.gms.internal.ads.zzblg
    public final void zzkd() {
        this.zzfgf.zzaix();
    }
}

