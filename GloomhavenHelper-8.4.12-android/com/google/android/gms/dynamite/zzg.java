package com.google.android.gms.dynamite;

import android.content.Context;

final class zzg implements VersionPolicy {
    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy
    public final zzb zza(Context context0, String s, zza dynamiteModule$VersionPolicy$zza0) throws LoadingException {
        zzb dynamiteModule$VersionPolicy$zzb0 = new zzb();
        dynamiteModule$VersionPolicy$zzb0.zzir = dynamiteModule$VersionPolicy$zza0.getLocalVersion(context0, s);
        dynamiteModule$VersionPolicy$zzb0.zzis = dynamiteModule$VersionPolicy$zzb0.zzir == 0 ? dynamiteModule$VersionPolicy$zza0.zza(context0, s, true) : dynamiteModule$VersionPolicy$zza0.zza(context0, s, false);
        if(dynamiteModule$VersionPolicy$zzb0.zzir == 0 && dynamiteModule$VersionPolicy$zzb0.zzis == 0) {
            dynamiteModule$VersionPolicy$zzb0.zzit = 0;
            return dynamiteModule$VersionPolicy$zzb0;
        }
        if(dynamiteModule$VersionPolicy$zzb0.zzis >= dynamiteModule$VersionPolicy$zzb0.zzir) {
            dynamiteModule$VersionPolicy$zzb0.zzit = 1;
            return dynamiteModule$VersionPolicy$zzb0;
        }
        dynamiteModule$VersionPolicy$zzb0.zzit = -1;
        return dynamiteModule$VersionPolicy$zzb0;
    }
}

