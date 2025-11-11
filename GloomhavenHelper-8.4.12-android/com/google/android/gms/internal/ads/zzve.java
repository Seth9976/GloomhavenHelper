package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamite.DynamiteModule;

abstract class zzve {
    @Nullable
    private static final zzwg zzced;

    static {
        zzve.zzced = zzve.zzow();
    }

    @Nullable
    protected abstract Object zza(zzwg arg1) throws RemoteException;

    public final Object zzd(Context context0, boolean z) {
        Object object0;
        if(!z && !zzayx.zzd(context0, 12451000)) {
            zzazh.zzeb("Google Play Services is not available.");
            z = true;
        }
        if(DynamiteModule.getLocalVersion(context0, "com.google.android.gms.ads.dynamite") > DynamiteModule.getRemoteVersion(context0, "com.google.android.gms.ads.dynamite")) {
            z = true;
        }
        zzzx.initialize(context0);
        if(((Boolean)zzabe.zzcue.get()).booleanValue()) {
            z = false;
        }
        if(z) {
            object0 = this.zzox();
            if(object0 == null) {
                object0 = this.zzoy();
                return object0 == null ? this.zzou() : object0;
            }
        }
        else {
            Object object1 = this.zzoy();
            int v = object1 == null ? 1 : 0;
            if(v != 0 && zzvh.zzpg().nextInt(((Long)zzabo.zzcvh.get()).intValue()) == 0) {
                Bundle bundle0 = new Bundle();
                bundle0.putString("action", "dynamite_load");
                bundle0.putInt("is_missing", v);
                zzvh.zzoz().zza(context0, zzvh.zzpf().zzbmj, "gmob-apps", bundle0, true);
            }
            object0 = object1 == null ? this.zzox() : object1;
        }
        return object0 == null ? this.zzou() : object0;
    }

    @NonNull
    protected abstract Object zzou();

    @Nullable
    protected abstract Object zzov() throws RemoteException;

    private static zzwg zzow() {
        try {
            Object object0 = zzus.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").getDeclaredConstructor().newInstance();
            if(!(object0 instanceof IBinder)) {
                zzazh.zzfa("ClientApi class is not an instance of IBinder.");
                return null;
            }
            if(((IBinder)object0) == null) {
                return null;
            }
            IInterface iInterface0 = ((IBinder)object0).queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
            return iInterface0 instanceof zzwg ? ((zzwg)iInterface0) : new zzwi(((IBinder)object0));
        }
        catch(Exception unused_ex) {
            zzazh.zzfa("Failed to instantiate ClientApi class.");
            return null;
        }
    }

    @Nullable
    private final Object zzox() {
        zzwg zzwg0 = zzve.zzced;
        if(zzwg0 == null) {
            zzazh.zzfa("ClientApi class cannot be loaded.");
            return null;
        }
        try {
            return this.zza(zzwg0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Cannot invoke local loader using ClientApi class.", remoteException0);
            return null;
        }
    }

    @Nullable
    private final Object zzoy() {
        try {
            return this.zzov();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzd("Cannot invoke remote loader.", remoteException0);
            return null;
        }
    }
}

