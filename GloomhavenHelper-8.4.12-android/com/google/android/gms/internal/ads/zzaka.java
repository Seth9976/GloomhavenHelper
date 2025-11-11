package com.google.android.gms.internal.ads;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;

public final class zzaka implements zzaii, zzakb {
    private final zzajy zzdbn;
    private final HashSet zzdbo;

    public zzaka(zzajy zzajy0) {
        this.zzdbn = zzajy0;
        this.zzdbo = new HashSet();
    }

    @Override  // com.google.android.gms.internal.ads.zzajy
    public final void zza(String s, zzafz zzafz0) {
        this.zzdbn.zza(s, zzafz0);
        AbstractMap.SimpleEntry abstractMap$SimpleEntry0 = new AbstractMap.SimpleEntry(s, zzafz0);
        this.zzdbo.add(abstractMap$SimpleEntry0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, Map map0) {
        zzail.zza(this, s, map0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaii, com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, JSONObject jSONObject0) {
        zzail.zzb(this, s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzajy
    public final void zzb(String s, zzafz zzafz0) {
        this.zzdbn.zzb(s, zzafz0);
        AbstractMap.SimpleEntry abstractMap$SimpleEntry0 = new AbstractMap.SimpleEntry(s, zzafz0);
        this.zzdbo.remove(abstractMap$SimpleEntry0);
    }

    @Override  // com.google.android.gms.internal.ads.zzajb
    public final void zzb(String s, JSONObject jSONObject0) {
        zzail.zza(this, s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaii, com.google.android.gms.internal.ads.zzajb
    public final void zzcz(String s) {
        this.zzdbn.zzcz(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzaii
    public final void zzj(String s, String s1) {
        zzail.zza(this, s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzakb
    public final void zzsl() {
        for(Object object0: this.zzdbo) {
            String s = ((zzafz)((AbstractMap.SimpleEntry)object0).getValue()).toString();
            zzawf.zzee((s.length() == 0 ? new String("Unregistering eventhandler: ") : "Unregistering eventhandler: " + s));
            String s1 = (String)((AbstractMap.SimpleEntry)object0).getKey();
            zzafz zzafz0 = (zzafz)((AbstractMap.SimpleEntry)object0).getValue();
            this.zzdbn.zzb(s1, zzafz0);
        }
        this.zzdbo.clear();
    }
}

