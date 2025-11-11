package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;

public class zzcpq extends zzcqs {
    private zzbvf zzgdk;
    private zzbql zzgdl;

    public zzcpq(zzbqg zzbqg0, zzbqp zzbqp0, zzbrc zzbrc0, zzbrm zzbrm0, zzbql zzbql0, zzbsy zzbsy0, zzbvk zzbvk0, zzbrv zzbrv0, zzbvf zzbvf0, zzbsu zzbsu0) {
        super(zzbqg0, zzbqp0, zzbrc0, zzbrm0, zzbsy0, zzbrv0, zzbvk0, zzbsu0);
        this.zzgdk = zzbvf0;
        this.zzgdl = zzbql0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcqs
    public final void onVideoEnd() {
        this.zzgdk.zzry();
    }

    @Override  // com.google.android.gms.internal.ads.zzcqs
    public final void zza(zzass zzass0) throws RemoteException {
        this.zzgdk.zza(new zzasq(zzass0.getType(), zzass0.getAmount()));
    }

    @Override  // com.google.android.gms.internal.ads.zzcqs
    public final void zzb(Bundle bundle0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzcqs
    public final void zzb(zzasq zzasq0) {
        this.zzgdk.zza(zzasq0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcqs
    public final void zzco(int v) throws RemoteException {
        this.zzgdl.zzco(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzcqs
    public final void zzsx() {
        this.zzgdk.zzrx();
    }

    @Override  // com.google.android.gms.internal.ads.zzcqs
    public final void zzsy() throws RemoteException {
        this.zzgdk.zzry();
    }
}

