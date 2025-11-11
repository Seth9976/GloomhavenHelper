package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

final class zzzk extends zzzi {
    zzzk(int v, String s, Integer integer0) {
        super(1, s, integer0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(SharedPreferences sharedPreferences0) {
        return sharedPreferences0.getInt(this.getKey(), ((int)(((Integer)this.zzqi()))));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(Bundle bundle0) {
        String s = this.getKey();
        if(bundle0.containsKey((s.length() == 0 ? new String("com.google.android.gms.ads.flag.") : "com.google.android.gms.ads.flag." + s))) {
            String s1 = this.getKey();
            return s1.length() == 0 ? bundle0.getInt(new String("com.google.android.gms.ads.flag.")) : bundle0.getInt("com.google.android.gms.ads.flag." + s1);
        }
        return (Integer)this.zzqi();
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final void zza(SharedPreferences.Editor sharedPreferences$Editor0, Object object0) {
        sharedPreferences$Editor0.putInt(this.getKey(), ((int)(((Integer)object0))));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zzb(JSONObject jSONObject0) {
        return jSONObject0.optInt(this.getKey(), ((int)(((Integer)this.zzqi()))));
    }
}

