package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzawg extends zzawb {
    private Context zzur;

    zzawg(Context context0) {
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzawb
    public final void zztz() {
        boolean z;
        try {
            z = AdvertisingIdClient.getIsAdIdFakeForDebugLogging(this.zzur);
        }
        catch(IOException | IllegalStateException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException iOException0) {
            zzawf.zzc("Fail to get isAdIdFakeForDebugLogging", iOException0);
            z = false;
        }
        zzazb.zzar(z);
        zzawf.zzfa(("Update ad debug logging enablement as " + z));
    }
}

