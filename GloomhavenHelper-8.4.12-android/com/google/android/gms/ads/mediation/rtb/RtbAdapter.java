package com.google.android.gms.ads.mediation.rtb;

import com.google.android.gms.ads.mediation.Adapter;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class RtbAdapter extends Adapter {
    public abstract void collectSignals(RtbSignalData arg1, SignalCallbacks arg2);
}

