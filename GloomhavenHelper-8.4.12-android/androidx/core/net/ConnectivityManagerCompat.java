package androidx.core.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RestrictBackgroundStatus {
    }

    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    @Nullable
    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkInfo getNetworkInfoFromBroadcast(@NonNull ConnectivityManager connectivityManager0, @NonNull Intent intent0) {
        NetworkInfo networkInfo0 = (NetworkInfo)intent0.getParcelableExtra("networkInfo");
        return networkInfo0 == null ? null : connectivityManager0.getNetworkInfo(networkInfo0.getType());
    }

    public static int getRestrictBackgroundStatus(@NonNull ConnectivityManager connectivityManager0) {
        return Build.VERSION.SDK_INT < 24 ? 3 : connectivityManager0.getRestrictBackgroundStatus();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isActiveNetworkMetered(@NonNull ConnectivityManager connectivityManager0) {
        if(Build.VERSION.SDK_INT >= 16) {
            return connectivityManager0.isActiveNetworkMetered();
        }
        NetworkInfo networkInfo0 = connectivityManager0.getActiveNetworkInfo();
        if(networkInfo0 == null) {
            return true;
        }
        switch(networkInfo0.getType()) {
            case 0: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                return true;
            }
            case 1: 
            case 7: 
            case 9: {
                return false;
            }
            default: {
                return true;
            }
        }
    }
}

