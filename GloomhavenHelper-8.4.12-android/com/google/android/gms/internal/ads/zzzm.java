package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

final class zzzm extends zzzi {
    zzzm(int v, String s, Float float0) {
        super(1, s, float0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(SharedPreferences sharedPreferences0) {
        return sharedPreferences0.getFloat(this.getKey(), ((float)(((Float)this.zzqi()))));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(Bundle bundle0) {
        String s = this.getKey();
        if(bundle0.containsKey((s.length() == 0 ? new String("com.google.android.gms.ads.flag.") : "com.google.android.gms.ads.flag." + s))) {
            String s1 = this.getKey();
            return s1.length() == 0 ? bundle0.getFloat(new String("com.google.android.gms.ads.flag.")) : bundle0.getFloat("com.google.android.gms.ads.flag." + s1);
        }
        return (Float)this.zzqi();
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final void zza(SharedPreferences.Editor sharedPreferences$Editor0, Object object0) {
        sharedPreferences$Editor0.putFloat(this.getKey(), ((float)(((Float)object0))));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zzb(JSONObject jSONObject0) {
        return (float)jSONObject0.optDouble(this.getKey(), ((double)(((float)(((Float)this.zzqi()))))));
    }
}

