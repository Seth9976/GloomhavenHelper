package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class zzyc extends zzwx {
    private final String description;
    private final String zzcfw;

    public zzyc(String s, String s1) {
        this.description = s;
        this.zzcfw = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzwu
    public final String getDescription() throws RemoteException {
        return this.description;
    }

    @Override  // com.google.android.gms.internal.ads.zzwu
    public final String zzpm() throws RemoteException {
        return this.zzcfw;
    }
}

