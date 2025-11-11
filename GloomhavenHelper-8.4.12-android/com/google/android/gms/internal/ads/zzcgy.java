package com.google.android.gms.internal.ads;

import androidx.annotation.GuardedBy;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzcgy {
    @GuardedBy("this")
    private List zzfwe;
    @GuardedBy("this")
    private boolean zzfwf;
    @GuardedBy("this")
    private boolean zzfwg;
    private String zzfwh;
    private zzcgx zzfwi;

    public zzcgy(String s, zzcgx zzcgx0) {
        this.zzfwe = new ArrayList();
        this.zzfwf = false;
        this.zzfwg = false;
        this.zzfwh = s;
        this.zzfwi = zzcgx0;
    }

    public final void zzanc() {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzclo)).booleanValue()) {
                return;
            }
            if(!this.zzfwf) {
                Map map0 = this.zzane();
                map0.put("action", "init_started");
                this.zzfwe.add(map0);
                this.zzfwf = true;
            }
        }
    }

    public final void zzand() {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzclo)).booleanValue()) {
                return;
            }
            if(!this.zzfwg) {
                Map map0 = this.zzane();
                map0.put("action", "init_finished");
                this.zzfwe.add(map0);
                for(Object object0: this.zzfwe) {
                    this.zzfwi.zzm(((Map)object0));
                }
                this.zzfwg = true;
            }
        }
    }

    private final Map zzane() {
        Map map0 = this.zzfwi.zzana();
        map0.put("tms", Long.toString(zzq.zzlc().elapsedRealtime(), 10));
        map0.put("tid", this.zzfwh);
        return map0;
    }

    public final void zzge(String s) {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzclo)).booleanValue()) {
                return;
            }
            Map map0 = this.zzane();
            map0.put("action", "adapter_init_started");
            map0.put("ancn", s);
            this.zzfwe.add(map0);
        }
    }

    public final void zzgf(String s) {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzclo)).booleanValue()) {
                return;
            }
            Map map0 = this.zzane();
            map0.put("action", "adapter_init_finished");
            map0.put("ancn", s);
            this.zzfwe.add(map0);
        }
    }

    public final void zzq(String s, String s1) {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzclo)).booleanValue()) {
                return;
            }
            Map map0 = this.zzane();
            map0.put("action", "adapter_init_finished");
            map0.put("ancn", s);
            map0.put("rqe", s1);
            this.zzfwe.add(map0);
        }
    }
}

