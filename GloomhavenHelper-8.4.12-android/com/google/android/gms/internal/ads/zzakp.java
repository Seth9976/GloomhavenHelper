package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzakp implements zzakc {
    private final zzaja zzdby;
    private final zzake zzdcc;
    private final zzakh zzdcd;
    private final String zzdce;

    zzakp(zzaja zzaja0, String s, zzakh zzakh0, zzake zzake0) {
        this.zzdby = zzaja0;
        this.zzdce = s;
        this.zzdcd = zzakh0;
        this.zzdcc = zzake0;
    }

    static zzake zza(zzakp zzakp0) {
        return zzakp0.zzdcc;
    }

    private final void zza(zzajr zzajr0, zzajy zzajy0, Object object0, zzazy zzazy0) {
        try {
            zzakq zzakq0 = new zzakq(this, zzajr0, zzazy0);
            zzafi.zzcyk.zza("6e8e161c-f2dc-41e4-a746-819079a6debf", zzakq0);
            JSONObject jSONObject0 = new JSONObject();
            jSONObject0.put("id", "6e8e161c-f2dc-41e4-a746-819079a6debf");
            jSONObject0.put("args", this.zzdcd.zzj(object0));
            zzajy0.zzb(this.zzdce, jSONObject0);
        }
        catch(Exception unused_ex) {
            try {
                zzazy0.setException(exception0);
                zzawf.zzc("Unable to invokeJavascript", exception0);
            }
            finally {
                zzajr0.release();
            }
        }
    }

    static void zza(zzakp zzakp0, zzajr zzajr0, zzajy zzajy0, Object object0, zzazy zzazy0) {
        zzakp0.zza(zzajr0, zzajy0, object0, zzazy0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(@Nullable Object object0) throws Exception {
        return this.zzi(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzakc
    public final zzdof zzi(Object object0) {
        zzdof zzdof0 = new zzazy();
        zzajr zzajr0 = this.zzdby.zzb(null);
        zzajr0.zza(new zzako(this, zzajr0, object0, ((zzazy)zzdof0)), new zzakr(this, ((zzazy)zzdof0), zzajr0));
        return zzdof0;
    }
}

