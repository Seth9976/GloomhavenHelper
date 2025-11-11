package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

final class zzzp extends zzzi {
    zzzp(int v, String s, String s1) {
        super(v, s, s1, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(SharedPreferences sharedPreferences0) {
        return sharedPreferences0.getString(this.getKey(), ((String)this.zzqi()));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zza(Bundle bundle0) {
        String s = this.getKey();
        if(bundle0.containsKey((s.length() == 0 ? new String("com.google.android.gms.ads.flag.") : "com.google.android.gms.ads.flag." + s))) {
            String s1 = this.getKey();
            return s1.length() == 0 ? bundle0.getString(new String("com.google.android.gms.ads.flag.")) : bundle0.getString("com.google.android.gms.ads.flag." + s1);
        }
        return (String)this.zzqi();
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final void zza(SharedPreferences.Editor sharedPreferences$Editor0, Object object0) {
        sharedPreferences$Editor0.putString(this.getKey(), ((String)object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzzi
    public final Object zzb(JSONObject jSONObject0) {
        return jSONObject0.optString(this.getKey(), ((String)this.zzqi()));
    }
}

