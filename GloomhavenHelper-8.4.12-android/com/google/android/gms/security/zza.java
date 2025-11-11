package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

final class zza extends AsyncTask {
    private final Context val$context;
    private final ProviderInstallListener zzix;

    zza(Context context0, ProviderInstallListener providerInstaller$ProviderInstallListener0) {
        this.val$context = context0;
        this.zzix = providerInstaller$ProviderInstallListener0;
        super();
    }

    @Override  // android.os.AsyncTask
    protected final Object doInBackground(Object[] arr_object) {
        return this.zza(((Void[])arr_object));
    }

    @Override  // android.os.AsyncTask
    protected final void onPostExecute(Object object0) {
        if(((int)(((Integer)object0))) == 0) {
            this.zzix.onProviderInstalled();
            return;
        }
        Intent intent0 = ProviderInstaller.zziv.getErrorResolutionIntent(this.val$context, ((int)(((Integer)object0))), "pi");
        this.zzix.onProviderInstallFailed(((int)(((Integer)object0))), intent0);
    }

    private final Integer zza(Void[] arr_void) {
        try {
            ProviderInstaller.installIfNeeded(this.val$context);
            return 0;
        }
        catch(GooglePlayServicesRepairableException googlePlayServicesRepairableException0) {
            return googlePlayServicesRepairableException0.getConnectionStatusCode();
        }
        catch(GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException0) {
            return googlePlayServicesNotAvailableException0.errorCode;
        }
    }
}

