package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;

public final class zzdka {
    private final SharedPreferences zzcgv;
    @VisibleForTesting
    private final File zzgxx;
    @VisibleForTesting
    private final File zzgxy;

    public zzdka(@NonNull Context context0) {
        this.zzcgv = context0.getSharedPreferences("pcvmspf", 0);
        this.zzgxx = zzdkd.zza(context0.getDir("pccache", 0), false);
        this.zzgxy = zzdkd.zza(context0.getDir("tmppccache", 0), true);
    }

    @VisibleForTesting
    private static String zza(@NonNull zzgb zzgb0) {
        return Hex.bytesToStringLowercase(zzgb0.zzbai().toByteArray());
    }

    // 去混淆评级： 中等(113)
    public final boolean zza(@NonNull zzfx zzfx0, @Nullable zzdkg zzdkg0) {
        zzfx0.zzcz().toByteArray();
        zzfx0.zzda().toByteArray();
        return false;
    }

    public final zzdkb zzdr(int v) {
        zzgb zzgb0 = this.zzds(v);
        if(zzgb0 == null) {
            return null;
        }
        File file0 = zzdkd.zza("", "pcam", this.zzgxx);
        File file1 = zzdkd.zza("", "pcopt", this.zzgxx);
        return new zzdkb(zzgb0, file0, zzdkd.zza("", "pcbc", this.zzgxx), file1);
    }

    @VisibleForTesting
    private final zzgb zzds(int v) {
        String s;
        if(v == zzdkj.zzgyf) {
            s = this.zzcgv.getString("LATMTD", null);
        }
        else {
            s = v == zzdkj.zzgyg ? this.zzcgv.getString("FBAMTD", null) : null;
        }
        if(TextUtils.isEmpty(s)) {
            return null;
        }
        try {
            zzgb zzgb0 = zzgb.zzl(zzdxn.zzt(Hex.stringToBytes(s)));
            File file0 = zzdkd.zza("", "pcam", this.zzgxx);
            File file1 = zzdkd.zza("", "pcbc", this.zzgxx);
            if(file0.exists() && file1.exists()) {
                return zzgb0;
            }
        }
        catch(zzdzh unused_ex) {
        }
        return null;
    }
}

