package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.query.QueryInfo;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;

final class zzapy extends zzauz {
    private final QueryInfoGenerationCallback zzdji;

    zzapy(zzapw zzapw0, QueryInfoGenerationCallback queryInfoGenerationCallback0) {
        this.zzdji = queryInfoGenerationCallback0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzava
    public final void onError(String s) throws RemoteException {
        this.zzdji.onFailure(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzava
    public final void zza(String s, String s1, Bundle bundle0) throws RemoteException {
        QueryInfo queryInfo0 = new QueryInfo(new zzyf(s, bundle0));
        zzvh.zzph().put(queryInfo0, s1);
        this.zzdji.onSuccess(queryInfo0);
    }

    @Override  // com.google.android.gms.internal.ads.zzava
    public final void zzk(String s, String s1) throws RemoteException {
        QueryInfo queryInfo0 = new QueryInfo(new zzyf(s, null));
        zzvh.zzph().put(queryInfo0, s1);
        this.zzdji.onSuccess(queryInfo0);
    }
}

