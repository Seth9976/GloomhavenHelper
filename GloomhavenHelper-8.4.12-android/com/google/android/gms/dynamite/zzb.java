package com.google.android.gms.dynamite;

import android.content.Context;

final class zzb implements VersionPolicy {
    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy
    public final com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb zza(Context context0, String s, zza dynamiteModule$VersionPolicy$zza0) throws LoadingException {
        com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb dynamiteModule$VersionPolicy$zzb0 = new com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb();
        dynamiteModule$VersionPolicy$zzb0.zzis = dynamiteModule$VersionPolicy$zza0.zza(context0, s, true);
        if(dynamiteModule$VersionPolicy$zzb0.zzis != 0) {
            dynamiteModule$VersionPolicy$zzb0.zzit = 1;
            return dynamiteModule$VersionPolicy$zzb0;
        }
        dynamiteModule$VersionPolicy$zzb0.zzir = dynamiteModule$VersionPolicy$zza0.getLocalVersion(context0, s);
        if(dynamiteModule$VersionPolicy$zzb0.zzir != 0) {
            dynamiteModule$VersionPolicy$zzb0.zzit = -1;
        }
        return dynamiteModule$VersionPolicy$zzb0;
    }
}

