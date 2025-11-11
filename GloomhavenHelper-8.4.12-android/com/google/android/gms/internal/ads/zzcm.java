package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class zzcm implements Runnable {
    private zzcm() {
    }

    zzcm(zzcn zzcn0) {
    }

    @Override
    public final void run() {
        try {
            zzck.zzmt = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException unused_ex) {
        }
        finally {
            zzck.zzmw.countDown();
        }
    }
}

