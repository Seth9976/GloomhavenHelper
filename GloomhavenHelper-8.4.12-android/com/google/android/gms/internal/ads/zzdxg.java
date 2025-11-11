package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzdxg implements zzeak {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.zzbal();
    }

    protected abstract zzdxg zza(zzdxd arg1);

    public abstract zzdxg zza(zzdxz arg1, zzdym arg2) throws IOException;

    public zzdxg zza(byte[] arr_b, int v, int v1, zzdym zzdym0) throws zzdzh {
        try {
            zzdxz zzdxz0 = zzdxz.zzb(arr_b, 0, v1, false);
            this.zza(zzdxz0, zzdym0);
            zzdxz0.zzfh(0);
            return this;
        }
        catch(zzdzh zzdzh0) {
            throw zzdzh0;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Reading " + this.getClass().getName() + " from a " + "byte array" + " threw an IOException (should never happen).", iOException0);
        }
    }

    public abstract zzdxg zzbal();

    @Override  // com.google.android.gms.internal.ads.zzeak
    public final zzeak zzf(zzeah zzeah0) {
        if(!this.zzbcy().getClass().isInstance(zzeah0)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return this.zza(((zzdxd)zzeah0));
    }
}

