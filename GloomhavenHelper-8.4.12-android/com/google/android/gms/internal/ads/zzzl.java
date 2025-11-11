package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

final class zzzl extends zzzi {
    zzzl(int v, String s, Boolean boolean0) {
        super(1, s, boolean0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(SharedPreferences sharedPreferences0) {
        return Boolean.valueOf(sharedPreferences0.getBoolean(this.getKey(), ((Boolean)this.zzqi()).booleanValue()));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(Bundle bundle0) {
        String s = this.getKey();
        if(bundle0.containsKey((s.length() == 0 ? new String("com.google.android.gms.ads.flag.") : "com.google.android.gms.ads.flag." + s))) {
            String s1 = this.getKey();
            return s1.length() == 0 ? Boolean.valueOf(bundle0.getBoolean(new String("com.google.android.gms.ads.flag."))) : Boolean.valueOf(bundle0.getBoolean("com.google.android.gms.ads.flag." + s1));
        }
        return (Boolean)this.zzqi();
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final void zza(SharedPreferences.Editor sharedPreferences$Editor0, Object object0) {
        sharedPreferences$Editor0.putBoolean(this.getKey(), ((Boolean)object0).booleanValue());
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zzb(JSONObject jSONObject0) {
        return Boolean.valueOf(jSONObject0.optBoolean(this.getKey(), ((Boolean)this.zzqi()).booleanValue()));
    }
}

