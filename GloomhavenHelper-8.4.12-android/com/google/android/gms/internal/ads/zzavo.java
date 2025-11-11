package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzavo implements Runnable {
    private final Context val$context;
    private final zzazy zzdrb;

    zzavo(zzavl zzavl0, Context context0, zzazy zzazy0) {
        this.val$context = context0;
        this.zzdrb = zzazy0;
        super();
    }

    @Override
    public final void run() {
        try {
            Info advertisingIdClient$Info0 = AdvertisingIdClient.getAdvertisingIdInfo(this.val$context);
            this.zzdrb.set(advertisingIdClient$Info0);
        }
        catch(IOException | IllegalStateException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException iOException0) {
            this.zzdrb.setException(iOException0);
            zzazh.zzc("Exception while getting advertising Id info", iOException0);
        }
    }
}

