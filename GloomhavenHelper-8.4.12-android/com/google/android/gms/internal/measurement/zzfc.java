package com.google.android.gms.internal.measurement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class zzfc {
    private static final Logger zza;
    private static String zzb;

    static {
        zzfc.zza = Logger.getLogger("com.google.android.gms.internal.measurement.zzek");
        zzfc.zzb = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
    }

    static zzeq zza(Class class0) {
        zzfc zzfc0;
        String s;
        ClassLoader classLoader0 = zzfc.class.getClassLoader();
        if(class0.equals(zzeq.class)) {
            s = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
            goto label_6;
        }
        if(class0.getPackage().equals(zzfc.class.getPackage())) {
            s = String.format("%s.BlazeGenerated%sLoader", class0.getPackage().getName(), class0.getSimpleName());
            try {
            label_6:
                Class class1 = Class.forName(s, true, classLoader0);
                try {
                    zzfc0 = (zzfc)class1.getConstructor().newInstance();
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    throw new IllegalStateException(noSuchMethodException0);
                }
                catch(InstantiationException instantiationException0) {
                    throw new IllegalStateException(instantiationException0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    throw new IllegalStateException(illegalAccessException0);
                }
                catch(InvocationTargetException invocationTargetException0) {
                    throw new IllegalStateException(invocationTargetException0);
                }
                return (zzeq)class0.cast(zzfc0.zza());
            }
            catch(ClassNotFoundException unused_ex) {
                Iterator iterator0 = ServiceLoader.load(zzfc.class, classLoader0).iterator();
                ArrayList arrayList0 = new ArrayList();
                while(iterator0.hasNext()) {
                    try {
                        Object object0 = iterator0.next();
                        arrayList0.add(class0.cast(((zzfc)object0).zza()));
                    }
                    catch(ServiceConfigurationError serviceConfigurationError0) {
                        String s1 = class0.getSimpleName();
                        zzfc.zza.logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", (s1.length() == 0 ? new String("Unable to load ") : "Unable to load " + s1), serviceConfigurationError0);
                    }
                }
                switch(arrayList0.size()) {
                    case 0: {
                        return null;
                    }
                    case 1: {
                        return (zzeq)arrayList0.get(0);
                    }
                    default: {
                        try {
                            return (zzeq)class0.getMethod("combine", Collection.class).invoke(null, arrayList0);
                        }
                        catch(NoSuchMethodException noSuchMethodException1) {
                            throw new IllegalStateException(noSuchMethodException1);
                        }
                        catch(IllegalAccessException illegalAccessException1) {
                            throw new IllegalStateException(illegalAccessException1);
                        }
                        catch(InvocationTargetException invocationTargetException1) {
                            throw new IllegalStateException(invocationTargetException1);
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException(class0.getName());
    }

    protected abstract zzeq zza();
}

