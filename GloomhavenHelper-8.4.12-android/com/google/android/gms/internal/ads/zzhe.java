package com.google.android.gms.internal.ads;

import java.io.IOException;

public interface zzhe extends zzgp {
    void disable();

    int getState();

    int getTrackType();

    boolean isReady();

    void setIndex(int arg1);

    void start() throws zzgk;

    void stop() throws zzgk;

    void zza(zzhg arg1, zzgz[] arg2, zzmn arg3, long arg4, boolean arg5, long arg6) throws zzgk;

    void zza(zzgz[] arg1, zzmn arg2, long arg3) throws zzgk;

    void zzb(long arg1, long arg2) throws zzgk;

    void zzdm(long arg1) throws zzgk;

    zzhh zzdu();

    zzof zzdv();

    zzmn zzdw();

    boolean zzdx();

    void zzdy();

    boolean zzdz();

    void zzea() throws IOException;

    boolean zzez();
}

