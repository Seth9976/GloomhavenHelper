package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public interface MediationAdapter extends MediationExtrasReceiver {
    public static final class zza {
        private int zzejq;

        public final Bundle zzacd() {
            Bundle bundle0 = new Bundle();
            bundle0.putInt("capabilities", this.zzejq);
            return bundle0;
        }

        public final zza zzdf(int v) {
            this.zzejq = 1;
            return this;
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}

