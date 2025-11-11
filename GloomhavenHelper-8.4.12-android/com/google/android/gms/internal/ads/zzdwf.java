package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzdwf {
    private static final Logger logger;
    private static final List zzhmq;
    public static final zzdwf zzhmr;
    public static final zzdwf zzhms;
    private static final zzdwf zzhmt;
    private static final zzdwf zzhmu;
    public static final zzdwf zzhmv;
    public static final zzdwf zzhmw;
    public static final zzdwf zzhmx;
    private zzdwe zzhmy;
    private List zzhmz;
    private boolean zzhna;

    static {
        zzdwf.logger = Logger.getLogger("com.google.android.gms.internal.ads.zzdwf");
        if(zzdws.zzbag()) {
            ArrayList arrayList0 = new ArrayList();
            for(int v = 0; v < 2; ++v) {
                Provider provider0 = Security.getProvider(new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL"}[v]);
                if(provider0 == null) {
                    zzdwf.logger.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", "Provider AndroidOpenSSL not available");
                }
                else {
                    arrayList0.add(provider0);
                }
            }
            zzdwf.zzhmq = arrayList0;
        }
        else {
            zzdwf.zzhmq = new ArrayList();
        }
        zzdwf.zzhmr = new zzdwf(new zzdwh());
        zzdwf.zzhms = new zzdwf(new zzdwl());
        zzdwf.zzhmt = new zzdwf(new zzdwn());
        zzdwf.zzhmu = new zzdwf(new zzdwk());
        zzdwf.zzhmv = new zzdwf(new zzdwg());
        zzdwf.zzhmw = new zzdwf(new zzdwi());
        zzdwf.zzhmx = new zzdwf(new zzdwj());
    }

    private zzdwf(zzdwe zzdwe0) {
        this.zzhmy = zzdwe0;
        this.zzhmz = zzdwf.zzhmq;
        this.zzhna = true;
    }

    public final Object zzhg(String s) throws GeneralSecurityException {
        Throwable throwable0 = null;
        for(Object object0: this.zzhmz) {
            Provider provider0 = (Provider)object0;
            try {
                return this.zzhmy.zza(s, provider0);
            }
            catch(Exception exception0) {
                if(throwable0 != null) {
                    continue;
                }
                throwable0 = exception0;
            }
        }
        if(!this.zzhna) {
            throw new GeneralSecurityException("No good Provider found.", throwable0);
        }
        return this.zzhmy.zza(s, null);
    }
}

