package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;

public class ProviderInstaller {
    public interface ProviderInstallListener {
        void onProviderInstallFailed(int arg1, Intent arg2);

        void onProviderInstalled();
    }

    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final Object lock;
    private static final GoogleApiAvailabilityLight zziv;
    private static Method zziw;

    static {
        ProviderInstaller.zziv = GoogleApiAvailabilityLight.getInstance();
        ProviderInstaller.lock = new Object();
        ProviderInstaller.zziw = null;
    }

    public static void installIfNeeded(Context context0) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Preconditions.checkNotNull(context0, "Context must not be null");
        ProviderInstaller.zziv.verifyGooglePlayServicesIsAvailable(context0, 11925000);
        Context context1 = ProviderInstaller.zzk(context0);
        if(context1 == null) {
            context1 = ProviderInstaller.zzl(context0);
        }
        if(context1 != null) {
            synchronized(ProviderInstaller.lock) {
                try {
                    if(ProviderInstaller.zziw == null) {
                        ProviderInstaller.zziw = context1.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", Context.class);
                    }
                    ProviderInstaller.zziw.invoke(null, context1);
                }
                catch(Exception exception0) {
                    Throwable throwable0 = exception0.getCause();
                    if(Log.isLoggable("ProviderInstaller", 6)) {
                        String s = String.valueOf((throwable0 == null ? exception0.getMessage() : throwable0.getMessage()));
                        Log.e("ProviderInstaller", (s.length() == 0 ? new String("Failed to install provider: ") : "Failed to install provider: " + s));
                    }
                    if(throwable0 != null) {
                        exception0 = throwable0;
                    }
                    CrashUtils.addDynamiteErrorToDropBox(context0, exception0);
                    throw new GooglePlayServicesNotAvailableException(8);
                }
            }
            return;
        }
        Log.e("ProviderInstaller", "Failed to get remote context");
        throw new GooglePlayServicesNotAvailableException(8);
    }

    public static void installIfNeededAsync(Context context0, ProviderInstallListener providerInstaller$ProviderInstallListener0) {
        Preconditions.checkNotNull(context0, "Context must not be null");
        Preconditions.checkNotNull(providerInstaller$ProviderInstallListener0, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context0, providerInstaller$ProviderInstallListener0).execute(new Void[0]);
    }

    @Nullable
    private static Context zzk(Context context0) {
        try {
            return DynamiteModule.load(context0, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "providerinstaller").getModuleContext();
        }
        catch(LoadingException dynamiteModule$LoadingException0) {
            String s = dynamiteModule$LoadingException0.getMessage();
            Log.w("ProviderInstaller", (s.length() == 0 ? new String("Failed to load providerinstaller module: ") : "Failed to load providerinstaller module: " + s));
            return null;
        }
    }

    @Nullable
    private static Context zzl(Context context0) {
        try {
            return GooglePlayServicesUtilLight.getRemoteContext(context0);
        }
        catch(Resources.NotFoundException resources$NotFoundException0) {
            String s = resources$NotFoundException0.getMessage();
            Log.w("ProviderInstaller", (s.length() == 0 ? new String("Failed to load GMS Core context for providerinstaller: ") : "Failed to load GMS Core context for providerinstaller: " + s));
            CrashUtils.addDynamiteErrorToDropBox(context0, resources$NotFoundException0);
            return null;
        }
    }
}

