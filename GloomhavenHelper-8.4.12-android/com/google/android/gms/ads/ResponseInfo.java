package com.google.android.gms.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzxe;

public final class ResponseInfo {
    private final zzxe zzaca;

    private ResponseInfo(zzxe zzxe0) {
        this.zzaca = zzxe0;
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        try {
            return this.zzaca.getMediationAdapterClassName();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Could not forward getMediationAdapterClassName to ResponseInfo.", remoteException0);
            return null;
        }
    }

    @Nullable
    public final String getResponseId() {
        try {
            return this.zzaca.getResponseId();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Could not forward getResponseId to ResponseInfo.", remoteException0);
            return null;
        }
    }

    @Nullable
    public static ResponseInfo zza(@Nullable zzxe zzxe0) {
        return zzxe0 == null ? null : new ResponseInfo(zzxe0);
    }
}

