package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class zzqp {
    protected Object mLock;
    @Nullable
    private static MessageDigest zzbqn;

    static {
    }

    public zzqp() {
        this.mLock = new Object();
    }

    abstract byte[] zzbw(String arg1);

    @Nullable
    protected final MessageDigest zzml() {
        synchronized(this.mLock) {
            if(zzqp.zzbqn != null) {
                return zzqp.zzbqn;
            }
            for(int v1 = 0; v1 < 2; ++v1) {
                try {
                    zzqp.zzbqn = MessageDigest.getInstance("MD5");
                }
                catch(NoSuchAlgorithmException unused_ex) {
                }
            }
            return zzqp.zzbqn;
        }
    }
}

