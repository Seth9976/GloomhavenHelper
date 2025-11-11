package com.google.android.gms.internal.ads;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzeea extends zzeeb {
    private Logger logger;

    public zzeea(String s) {
        this.logger = Logger.getLogger(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzeeb
    public final void zzhs(String s) {
        this.logger.logp(Level.FINE, "com.googlecode.mp4parser.util.JuliLogger", "logDebug", s);
    }
}

