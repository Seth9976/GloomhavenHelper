package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

public final class zzzo {
    private final Collection zzcgm;
    private final Collection zzcgn;
    private final Collection zzcgo;

    public zzzo() {
        this.zzcgm = new ArrayList();
        this.zzcgn = new ArrayList();
        this.zzcgo = new ArrayList();
    }

    public final void zza(SharedPreferences.Editor sharedPreferences$Editor0, int v, JSONObject jSONObject0) {
        for(Object object0: this.zzcgm) {
            zzzi zzzi0 = (zzzi)object0;
            if(zzzi0.getSource() == 1) {
                zzzi0.zza(sharedPreferences$Editor0, zzzi0.zzb(jSONObject0));
            }
        }
        if(jSONObject0 != null) {
            sharedPreferences$Editor0.putString("flag_configuration", jSONObject0.toString());
            return;
        }
        zzazh.zzey("Flag Json is null.");
    }

    public final void zza(zzzi zzzi0) {
        this.zzcgm.add(zzzi0);
    }

    public final void zzb(zzzi zzzi0) {
        this.zzcgn.add(zzzi0);
    }

    public final void zzc(zzzi zzzi0) {
        this.zzcgo.add(zzzi0);
    }

    public final List zzqj() {
        List list0 = new ArrayList();
        for(Object object0: this.zzcgn) {
            String s = (String)zzvh.zzpd().zzd(((zzzi)object0));
            if(!TextUtils.isEmpty(s)) {
                list0.add(s);
            }
        }
        list0.addAll(zzzy.zzqn());
        return list0;
    }

    public final List zzqk() {
        List list0 = this.zzqj();
        for(Object object0: this.zzcgo) {
            String s = (String)zzvh.zzpd().zzd(((zzzi)object0));
            if(!TextUtils.isEmpty(s)) {
                list0.add(s);
            }
        }
        list0.addAll(zzzy.zzqo());
        return list0;
    }
}

