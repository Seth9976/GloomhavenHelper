package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.CheckForNull;

public final class zzcro {
    private final zzcnk zzfws;
    private final ConcurrentHashMap zzgfd;

    public zzcro(zzcnk zzcnk0) {
        this.zzgfd = new ConcurrentHashMap();
        this.zzfws = zzcnk0;
    }

    public final void zzgj(String s) {
        try {
            zzanq zzanq0 = this.zzfws.zzdh(s);
            this.zzgfd.put(s, zzanq0);
        }
        catch(RemoteException remoteException0) {
            zzawf.zzc("Couldn\'t create RTB adapter : ", remoteException0);
        }
    }

    // 去混淆评级： 低(20)
    @CheckForNull
    public final zzanq zzgk(String s) {
        return this.zzgfd.containsKey(s) ? ((zzanq)this.zzgfd.get(s)) : null;
    }
}

