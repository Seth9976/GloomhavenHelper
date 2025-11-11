package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;

abstract class zze extends zzj {
    private int zzt;

    protected zze(byte[] arr_b) {
        Preconditions.checkArgument(arr_b.length == 25);
        this.zzt = Arrays.hashCode(arr_b);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 != null && object0 instanceof zzi) {
            try {
                if(((zzi)object0).zzc() != this.hashCode()) {
                    return false;
                }
                IObjectWrapper iObjectWrapper0 = ((zzi)object0).zzb();
                if(iObjectWrapper0 == null) {
                    return false;
                }
                byte[] arr_b = (byte[])ObjectWrapper.unwrap(iObjectWrapper0);
                return Arrays.equals(this.getBytes(), arr_b);
            }
            catch(RemoteException remoteException0) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", remoteException0);
                return false;
            }
        }
        return false;
    }

    abstract byte[] getBytes();

    @Override
    public int hashCode() {
        return this.zzt;
    }

    protected static byte[] zza(String s) [...] // 潜在的解密器

    @Override  // com.google.android.gms.common.internal.zzi
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.getBytes());
    }

    @Override  // com.google.android.gms.common.internal.zzi
    public final int zzc() {
        return this.hashCode();
    }
}

