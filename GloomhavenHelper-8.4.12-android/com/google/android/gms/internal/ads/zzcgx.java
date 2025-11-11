package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public final class zzcgx {
    private final Executor executor;
    private final String packageName;
    private final String zzcsn;
    private final zzazl zzdwb;
    private final Map zzfwd;
    private final Context zzur;

    public zzcgx(Executor executor0, zzazl zzazl0, Context context0) {
        this.zzcsn = (String)zzabf.zzcuh.get();
        this.zzfwd = new HashMap();
        this.executor = executor0;
        this.zzdwb = zzazl0;
        this.zzur = context0;
        this.packageName = "com.esotericsoftware.gloomhavenhelper";
        this.zzfwd.put("s", "gmob_sdk");
        this.zzfwd.put("v", "3");
        this.zzfwd.put("os", Build.VERSION.RELEASE);
        this.zzfwd.put("sdk", Build.VERSION.SDK);
        String s = zzawo.zzwq();
        this.zzfwd.put("device", s);
        this.zzfwd.put("app", this.packageName);
        String s1 = zzawo.zzba(this.zzur) ? "1" : "0";
        this.zzfwd.put("is_lite_sdk", s1);
        String s2 = TextUtils.join(",", zzzx.zzqk());
        this.zzfwd.put("e", s2);
    }

    public final Map zzana() {
        return new HashMap(this.zzfwd);
    }

    public final ConcurrentHashMap zzanb() {
        return new ConcurrentHashMap(this.zzfwd);
    }

    // 检测为 Lambda 实现
    final void zzgd(String s) [...]

    final void zzm(Map map0) {
        Uri.Builder uri$Builder0 = Uri.parse(this.zzcsn).buildUpon();
        for(Object object0: map0.entrySet()) {
            uri$Builder0.appendQueryParameter(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
        }
        String s = uri$Builder0.build().toString();
        if(((Boolean)zzabf.zzcui.get()).booleanValue() || ((Boolean)zzvh.zzpd().zzd(zzzx.zzclo)).booleanValue()) {
            zzcgw zzcgw0 = () -> this.zzdwb.zzeo(s);
            this.executor.execute(zzcgw0);
        }
        zzawf.zzee(s);
    }
}

