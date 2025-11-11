package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

final class zzzn extends zzzi {
    zzzn(int v, String s, Long long0) {
        super(1, s, long0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(SharedPreferences sharedPreferences0) {
        return sharedPreferences0.getLong(this.getKey(), ((long)(((Long)this.zzqi()))));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(Bundle bundle0) {
        String s = this.getKey();
        if(bundle0.containsKey((s.length() == 0 ? new String("com.google.android.gms.ads.flag.") : "com.google.android.gms.ads.flag." + s))) {
            String s1 = this.getKey();
            return s1.length() == 0 ? bundle0.getLong(new String("com.google.android.gms.ads.flag.")) : bundle0.getLong("com.google.android.gms.ads.flag." + s1);
        }
        return (Long)this.zzqi();
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final void zza(SharedPreferences.Editor sharedPreferences$Editor0, Object object0) {
        sharedPreferences$Editor0.putLong(this.getKey(), ((long)(((Long)object0))));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zzb(JSONObject jSONObject0) {
        return jSONObject0.optLong(this.getKey(), ((long)(((Long)this.zzqi()))));
    }
}

