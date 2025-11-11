package com.google.android.gms.common;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class zzm {
    private final Throwable cause;
    private static final zzm zzac;
    final boolean zzad;
    private final String zzae;

    static {
        zzm.zzac = new zzm(true, null, null);
    }

    zzm(boolean z, @Nullable String s, @Nullable Throwable throwable0) {
        this.zzad = z;
        this.zzae = s;
        this.cause = throwable0;
    }

    @Nullable
    String getErrorMessage() {
        return this.zzae;
    }

    static zzm zza(@NonNull String s, @NonNull Throwable throwable0) {
        return new zzm(false, s, throwable0);
    }

    static zzm zza(Callable callable0) {
        return new zzo(callable0, null);
    }

    static zzm zzb(@NonNull String s) {
        return new zzm(false, s, null);
    }

    // 去混淆评级： 低(20)
    static String zzc(String s, zze zze0, boolean z, boolean z1) {
        return z1 ? String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", "debug cert rejected", s, Hex.bytesToStringLowercase(AndroidUtilsLight.zzj("SHA-1").digest(zze0.getBytes())), Boolean.valueOf(z), "12451009.false") : String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", "not whitelisted", s, Hex.bytesToStringLowercase(AndroidUtilsLight.zzj("SHA-1").digest(zze0.getBytes())), Boolean.valueOf(z), "12451009.false");
    }

    static zzm zze() {
        return zzm.zzac;
    }

    final void zzf() {
        if(!this.zzad && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if(this.cause != null) {
                Log.d("GoogleCertificatesRslt", this.getErrorMessage(), this.cause);
                return;
            }
            Log.d("GoogleCertificatesRslt", this.getErrorMessage());
        }
    }
}

