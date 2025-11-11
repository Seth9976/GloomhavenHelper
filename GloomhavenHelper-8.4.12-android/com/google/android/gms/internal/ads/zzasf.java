package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;

public final class zzasf extends RemoteCreator {
    public zzasf() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    @Override  // com.google.android.gms.dynamic.RemoteCreator
    protected final Object getRemoteCreator(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        return iInterface0 instanceof zzarz ? ((zzarz)iInterface0) : new zzasc(iBinder0);
    }

    public final zzary zza(Context context0, zzalk zzalk0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            IBinder iBinder0 = ((zzarz)this.getRemoteCreatorInstance(context0)).zzb(iObjectWrapper0, zzalk0, 20089000);
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            return iInterface0 instanceof zzary ? ((zzary)iInterface0) : new zzasa(iBinder0);
        }
        catch(RemoteException | RemoteCreatorException remoteException0) {
            zzazh.zzd("Could not get remote RewardedVideoAd.", remoteException0);
            return null;
        }
    }
}

