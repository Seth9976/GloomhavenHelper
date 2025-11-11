package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public final class zzdfl {
    private final zzazo zzblr;
    private final zzavr zzbmv;
    private final Map zzgrh;
    private final Context zzyz;

    public zzdfl(Context context0, zzazo zzazo0, zzavr zzavr0) {
        this.zzgrh = new HashMap();
        this.zzyz = context0;
        this.zzblr = zzazo0;
        this.zzbmv = zzavr0;
    }

    private final zzdfn zzarf() {
        zzawh zzawh0 = this.zzbmv.zzvk();
        zzawc zzawc0 = this.zzbmv.zzvm();
        return new zzdfn(this.zzyz, zzawh0, zzawc0, null);
    }

    public final zzdfn zzgo(@Nullable String s) {
        if(s == null) {
            return this.zzarf();
        }
        if(this.zzgrh.containsKey(s)) {
            return (zzdfn)this.zzgrh.get(s);
        }
        zzdfn zzdfn0 = this.zzgp(s);
        this.zzgrh.put(s, zzdfn0);
        return zzdfn0;
    }

    private final zzdfn zzgp(String s) {
        zzars zzars0 = zzars.zzab(this.zzyz);
        try {
            zzars0.setAppPackageName(s);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return this.zzarf();
        }
        zzawk zzawk0 = new zzawk();
        zzawk0.zza(this.zzyz, s, false);
        zzawl zzawl0 = new zzawl(this.zzbmv.zzvk(), zzawk0);
        return new zzdfn(zzars0, zzawl0, new zzawc("15263950917808097189", zzawl0), null);
    }
}

