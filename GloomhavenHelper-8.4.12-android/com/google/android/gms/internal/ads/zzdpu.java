package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzdpu {
    interface zza {
        Set zzavd();

        zzdpe zzavm();

        Class zzavn();

        Class zzavo();

        zzdpe zzb(Class arg1) throws GeneralSecurityException;
    }

    interface zzb {
    }

    private static final Logger logger;
    private static final ConcurrentMap zzhem;
    private static final ConcurrentMap zzhen;
    private static final ConcurrentMap zzheo;
    private static final ConcurrentMap zzhep;
    private static final ConcurrentMap zzheq;

    static {
        zzdpu.logger = Logger.getLogger(zzdpu.class.getName());
        zzdpu.zzhem = new ConcurrentHashMap();
        zzdpu.zzhen = new ConcurrentHashMap();
        zzdpu.zzheo = new ConcurrentHashMap();
        zzdpu.zzhep = new ConcurrentHashMap();
        zzdpu.zzheq = new ConcurrentHashMap();
    }

    private static Object checkNotNull(Object object0) {
        if(object0 == null) {
            throw new NullPointerException();
        }
        return object0;
    }

    private static zzdpe zza(String s, Class class0) throws GeneralSecurityException {
        zza zzdpu$zza0 = zzdpu.zzha(s);
        if(class0 == null) {
            return zzdpu$zza0.zzavm();
        }
        if(zzdpu$zza0.zzavd().contains(class0)) {
            return zzdpu$zza0.zzb(class0);
        }
        String s1 = class0.getName();
        String s2 = String.valueOf(zzdpu$zza0.zzavn());
        Set set0 = zzdpu$zza0.zzavd();
        StringBuilder stringBuilder0 = new StringBuilder();
        boolean z = true;
        for(Object object0: set0) {
            if(!z) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(((Class)object0).getCanonicalName());
            z = false;
        }
        throw new GeneralSecurityException("Primitive type " + s1 + " not supported by key manager of type " + s2 + ", supported primitives: " + stringBuilder0.toString());
    }

    public static zzdpr zza(zzdpk zzdpk0, zzdpe zzdpe0, Class class0) throws GeneralSecurityException {
        Class class1 = (Class)zzdpu.checkNotNull(class0);
        zzdqb.zzc(zzdpk0.zzavg());
        zzdpr zzdpr0 = zzdpr.zza(class1);
        for(Object object0: zzdpk0.zzavg().zzayv()) {
            com.google.android.gms.internal.ads.zzdum.zza zzdum$zza0 = (com.google.android.gms.internal.ads.zzdum.zza)object0;
            if(zzdum$zza0.zzavi() == zzdug.zzhjk) {
                zzdpq zzdpq0 = zzdpr0.zza(zzdpu.zza("", zzdum$zza0.zzayz().zzayi(), class1), zzdum$zza0);
                if(zzdum$zza0.zzaza() == zzdpk0.zzavg().zzayu()) {
                    zzdpr0.zza(zzdpq0);
                }
            }
        }
        return zzdpr0;
    }

    private static zza zza(zzdpj zzdpj0) {
        return new zzdpw(zzdpj0);
    }

    public static zzduc zza(zzdui zzdui0) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            zzdpe zzdpe0 = zzdpu.zzhc("");
            if(((Boolean)zzdpu.zzheo.get("")).booleanValue()) {
                return zzdpe0.zzo(zzdui0.zzayi());
            }
        }
        throw new GeneralSecurityException("newKey-operation not permitted for key type ");
    }

    public static Object zza(zzdpr zzdpr0) throws GeneralSecurityException {
        zzdpt zzdpt0 = (zzdpt)zzdpu.zzheq.get(zzdpr0.zzauy());
        if(zzdpt0 == null) {
            String s = zzdpr0.zzauy().getName();
            throw new GeneralSecurityException((s.length() == 0 ? new String("No wrapper found for ") : "No wrapper found for " + s));
        }
        return zzdpt0.zza(zzdpr0);
    }

    private static Object zza(String s, zzdxn zzdxn0, Class class0) throws GeneralSecurityException {
        return zzdpu.zza(s, class0).zzm(zzdxn0);
    }

    public static Object zza(String s, zzeah zzeah0, Class class0) throws GeneralSecurityException {
        return zzdpu.zza(s, ((Class)zzdpu.checkNotNull(class0))).zza(zzeah0);
    }

    public static Object zza(String s, byte[] arr_b, Class class0) throws GeneralSecurityException {
        return zzdpu.zza(s, zzdxn.zzt(arr_b), ((Class)zzdpu.checkNotNull(class0)));
    }

    public static void zza(zzdpe zzdpe0, boolean z) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            if(zzdpe0 != null) {
                String s = zzdpe0.getKeyType();
                zzdpu.zza(s, zzdpe0.getClass(), z);
                if(!zzdpu.zzhem.containsKey(s)) {
                    zzdpx zzdpx0 = new zzdpx(zzdpe0);
                    zzdpu.zzhem.put(s, zzdpx0);
                }
                zzdpu.zzheo.put(s, Boolean.valueOf(z));
                return;
            }
        }
        throw new IllegalArgumentException("key manager must be non-null.");
    }

    public static void zza(zzdpj zzdpj0, boolean z) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            String s = zzdpj0.getKeyType();
            zzdpu.zza(s, zzdpj0.getClass(), true);
            if(!zzdpu.zzhem.containsKey(s)) {
                zza zzdpu$zza0 = zzdpu.zza(zzdpj0);
                zzdpu.zzhem.put(s, zzdpu$zza0);
                zzb zzdpu$zzb0 = zzdpu.zzb(zzdpj0);
                zzdpu.zzhen.put(s, zzdpu$zzb0);
            }
            zzdpu.zzheo.put(s, Boolean.TRUE);
        }
    }

    public static void zza(zzdpt zzdpt0) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            if(zzdpt0 != null) {
                Class class1 = zzdpt0.zzauy();
                if(zzdpu.zzheq.containsKey(class1)) {
                    zzdpt zzdpt1 = (zzdpt)zzdpu.zzheq.get(class1);
                    if(!zzdpt0.getClass().equals(zzdpt1.getClass())) {
                        Level level0 = Level.WARNING;
                        String s = class1.toString();
                        zzdpu.logger.logp(level0, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", (s.length() == 0 ? new String("Attempted overwrite of a registered SetWrapper for type ") : "Attempted overwrite of a registered SetWrapper for type " + s));
                        throw new GeneralSecurityException(String.format("SetWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", class1.getName(), zzdpt1.getClass().getName(), zzdpt0.getClass().getName()));
                    }
                }
                zzdpu.zzheq.put(class1, zzdpt0);
                return;
            }
        }
        throw new IllegalArgumentException("wrapper must be non-null");
    }

    public static void zza(zzdpv zzdpv0, zzdpj zzdpj0, boolean z) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            String s = zzdpv0.getKeyType();
            String s1 = zzdpj0.getKeyType();
            zzdpu.zza(s, zzdpv0.getClass(), true);
            zzdpu.zza(s1, zzdpj0.getClass(), false);
            if(!s.equals(s1)) {
                if(zzdpu.zzhem.containsKey(s)) {
                    Class class1 = ((zza)zzdpu.zzhem.get(s)).zzavo();
                    if(class1 != null && !class1.equals(zzdpj0.getClass())) {
                        zzdpu.logger.logp(Level.WARNING, "com.google.crypto.tink.Registry", "registerAsymmetricKeyManagers", "Attempted overwrite of a registered key manager for key type " + s + " with inconsistent public key type " + s1);
                        throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", zzdpv0.getClass().getName(), class1.getName(), zzdpj0.getClass().getName()));
                    }
                }
                if(!zzdpu.zzhem.containsKey(s) || ((zza)zzdpu.zzhem.get(s)).zzavo() == null) {
                    zzdpz zzdpz0 = new zzdpz(zzdpv0, zzdpj0);
                    zzdpu.zzhem.put(s, zzdpz0);
                    zzb zzdpu$zzb0 = zzdpu.zzb(zzdpv0);
                    zzdpu.zzhen.put(s, zzdpu$zzb0);
                }
                zzdpu.zzheo.put(s, Boolean.TRUE);
                if(!zzdpu.zzhem.containsKey(s1)) {
                    zza zzdpu$zza0 = zzdpu.zza(zzdpj0);
                    zzdpu.zzhem.put(s1, zzdpu$zza0);
                }
                zzdpu.zzheo.put(s1, Boolean.FALSE);
                return;
            }
        }
        throw new GeneralSecurityException("Private and public key type must be different.");
    }

    private static void zza(String s, Class class0, boolean z) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            if(!zzdpu.zzhem.containsKey(s)) {
                return;
            }
            zza zzdpu$zza0 = (zza)zzdpu.zzhem.get(s);
            if(!zzdpu$zza0.zzavn().equals(class0)) {
                String s1 = String.valueOf(s);
                zzdpu.logger.logp(Level.WARNING, "com.google.crypto.tink.Registry", "ensureKeyManagerInsertable", (s1.length() == 0 ? new String("Attempted overwrite of a registered key manager for key type ") : "Attempted overwrite of a registered key manager for key type " + s1));
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", s, zzdpu$zza0.zzavn().getName(), class0.getName()));
            }
            if(z && !((Boolean)zzdpu.zzheo.get(s)).booleanValue()) {
                String s2 = String.valueOf(s);
                throw new GeneralSecurityException((s2.length() == 0 ? new String("New keys are already disallowed for key type ") : "New keys are already disallowed for key type " + s2));
            }
        }
    }

    private static zzb zzb(zzdpj zzdpj0) {
        return new zzdpy(zzdpj0);
    }

    public static zzeah zzb(zzdui zzdui0) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            zzdpe zzdpe0 = zzdpu.zzhc("");
            if(((Boolean)zzdpu.zzheo.get("")).booleanValue()) {
                return zzdpe0.zzn(zzdui0.zzayi());
            }
        }
        throw new GeneralSecurityException("newKey-operation not permitted for key type ");
    }

    private static zza zzha(String s) throws GeneralSecurityException {
        synchronized(zzdpu.class) {
            if(!zzdpu.zzhem.containsKey(s)) {
                String s1 = String.valueOf(s);
                throw new GeneralSecurityException((s1.length() == 0 ? new String("No key manager found for key type ") : "No key manager found for key type " + s1));
            }
            return (zza)zzdpu.zzhem.get(s);
        }
    }

    @Deprecated
    public static zzdpb zzhb(String s) throws GeneralSecurityException {
        if(s == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        zzdpb zzdpb0 = (zzdpb)zzdpu.zzhep.get(s.toLowerCase());
        if(zzdpb0 == null) {
            String s1 = s.toLowerCase().startsWith("tinkaead") ? String.format("no catalogue found for %s. ", s) + "Maybe call AeadConfig.register()." : String.format("no catalogue found for %s. ", s);
            if(s.toLowerCase().startsWith("tinkdeterministicaead")) {
                s1 = s1 + "Maybe call DeterministicAeadConfig.register().";
            }
            else if(s.toLowerCase().startsWith("tinkstreamingaead")) {
                s1 = s1 + "Maybe call StreamingAeadConfig.register().";
            }
            else if(s.toLowerCase().startsWith("tinkhybriddecrypt") || s.toLowerCase().startsWith("tinkhybridencrypt")) {
                s1 = s1 + "Maybe call HybridConfig.register().";
            }
            else if(s.toLowerCase().startsWith("tinkmac")) {
                s1 = s1 + "Maybe call MacConfig.register().";
            }
            else if(s.toLowerCase().startsWith("tinkpublickeysign") || s.toLowerCase().startsWith("tinkpublickeyverify")) {
                s1 = s1 + "Maybe call SignatureConfig.register().";
            }
            else if(s.toLowerCase().startsWith("tink")) {
                s1 = s1 + "Maybe call TinkConfig.register().";
            }
            throw new GeneralSecurityException(s1);
        }
        return zzdpb0;
    }

    private static zzdpe zzhc(String s) throws GeneralSecurityException {
        return zzdpu.zzha(s).zzavm();
    }
}

