package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

@Deprecated
public final class CustomEventExtras implements NetworkExtras {
    private final HashMap zzeku;

    public CustomEventExtras() {
        this.zzeku = new HashMap();
    }

    public final Object getExtra(String s) {
        return this.zzeku.get(s);
    }

    public final void setExtra(String s, Object object0) {
        this.zzeku.put(s, object0);
    }
}

