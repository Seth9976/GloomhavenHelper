package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

final class zzcxk implements Callable {
    private final List zzgjw;

    zzcxk(List list0) {
        this.zzgjw = list0;
    }

    @Override
    public final Object call() {
        JSONArray jSONArray0 = new JSONArray();
        for(Object object0: this.zzgjw) {
            zzdof zzdof0 = (zzdof)object0;
            if(((JSONObject)zzdof0.get()) != null) {
                jSONArray0.put(zzdof0.get());
            }
        }
        return jSONArray0.length() == 0 ? null : new zzcxd(jSONArray0.toString());
    }
}

