package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Future;
import javax.annotation.concurrent.GuardedBy;

public final class zzsf {
    private final Object lock;
    @Nullable
    @GuardedBy("lock")
    private zzsa zzbrm;
    @GuardedBy("lock")
    private boolean zzbrz;
    private final Context zzur;

    zzsf(Context context0) {
        this.lock = new Object();
        this.zzur = context0;
    }

    private final void disconnect() {
        synchronized(this.lock) {
            if(this.zzbrm == null) {
                return;
            }
            this.zzbrm.disconnect();
            this.zzbrm = null;
            Binder.flushPendingCommands();
        }
    }

    static boolean zza(zzsf zzsf0, boolean z) {
        zzsf0.zzbrz = true;
        return true;
    }

    final Future zzb(zzrz zzrz0) {
        Future future0 = new zzsi(this);
        zzsh zzsh0 = new zzsh(this, zzrz0, ((zzazy)future0));
        zzsl zzsl0 = new zzsl(this, ((zzazy)future0));
        synchronized(this.lock) {
            Looper looper0 = zzq.zzlj().zzxg();
            this.zzbrm = new zzsa(this.zzur, looper0, zzsh0, zzsl0);
            this.zzbrm.checkAvailabilityAndConnect();
            return future0;
        }
    }
}

