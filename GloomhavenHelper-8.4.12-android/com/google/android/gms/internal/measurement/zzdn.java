package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzdn implements zzgm {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.zzo();
    }

    private final String zza(String s) [...] // 潜在的解密器

    protected abstract zzdn zza(zzdl arg1);

    public abstract zzdn zza(zzeh arg1, zzeq arg2) throws IOException;

    public zzdn zza(byte[] arr_b, int v, int v1) throws zzfn {
        try {
            zzeh zzeh0 = zzeh.zza(arr_b, 0, v1, false);
            this.zza(zzeh0, zzeq.zza());
            zzeh0.zza(0);
            return this;
        }
        catch(zzfn zzfn0) {
            throw zzfn0;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Reading com.google.android.gms.internal.measurement.zzdn from a byte array threw an IOException (should never happen).", iOException0);
        }
    }

    public zzdn zza(byte[] arr_b, int v, int v1, zzeq zzeq0) throws zzfn {
        try {
            zzeh zzeh0 = zzeh.zza(arr_b, 0, v1, false);
            this.zza(zzeh0, zzeq0);
            zzeh0.zza(0);
            return this;
        }
        catch(zzfn zzfn0) {
            throw zzfn0;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Reading com.google.android.gms.internal.measurement.zzdn from a byte array threw an IOException (should never happen).", iOException0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzgm
    public final zzgm zza(zzgn zzgn0) {
        if(!this.zzv().getClass().isInstance(zzgn0)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return this.zza(((zzdl)zzgn0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzgm
    public final zzgm zza(byte[] arr_b) throws zzfn {
        return this.zza(arr_b, 0, arr_b.length);
    }

    @Override  // com.google.android.gms.internal.measurement.zzgm
    public final zzgm zza(byte[] arr_b, zzeq zzeq0) throws zzfn {
        return this.zza(arr_b, 0, arr_b.length, zzeq0);
    }

    public abstract zzdn zzo();
}

