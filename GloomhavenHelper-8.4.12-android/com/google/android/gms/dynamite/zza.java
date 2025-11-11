package com.google.android.gms.dynamite;

import android.content.Context;

final class zza implements com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza {
    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$zza
    public final int getLocalVersion(Context context0, String s) {
        return DynamiteModule.getLocalVersion(context0, s);
    }

    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$zza
    public final int zza(Context context0, String s, boolean z) throws LoadingException {
        return DynamiteModule.zza(context0, s, z);
    }
}

