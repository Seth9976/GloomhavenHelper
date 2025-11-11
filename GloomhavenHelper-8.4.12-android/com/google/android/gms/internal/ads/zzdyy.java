package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class zzdyy {
    private static final Logger logger;
    private static String zzhss;

    static {
        zzdyy.logger = Logger.getLogger("com.google.android.gms.internal.ads.zzdyi");
        zzdyy.zzhss = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
    }

    protected abstract zzdym zzbcs();

    static zzdym zze(Class class0) {
        zzdyy zzdyy0;
        String s;
        ClassLoader classLoader0 = zzdyy.class.getClassLoader();
        if(class0.equals(zzdym.class)) {
            s = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
            goto label_6;
        }
        if(class0.getPackage().equals(zzdyy.class.getPackage())) {
            s = String.format("%s.BlazeGenerated%sLoader", class0.getPackage().getName(), class0.getSimpleName());
            try {
            label_6:
                Class class1 = Class.forName(s, true, classLoader0);
                try {
                    zzdyy0 = (zzdyy)class1.getConstructor().newInstance();
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
                return (zzdym)class0.cast(zzdyy0.zzbcs());
            }
            catch(ClassNotFoundException unused_ex) {
                Iterator iterator0 = ServiceLoader.load(zzdyy.class, classLoader0).iterator();
                ArrayList arrayList0 = new ArrayList();
                while(iterator0.hasNext()) {
                    try {
                        Object object0 = iterator0.next();
                        arrayList0.add(class0.cast(((zzdyy)object0).zzbcs()));
                    }
                    catch(ServiceConfigurationError serviceConfigurationError0) {
                        String s1 = class0.getSimpleName();
                        zzdyy.logger.logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", (s1.length() == 0 ? new String("Unable to load ") : "Unable to load " + s1), serviceConfigurationError0);
                    }
                }
                switch(arrayList0.size()) {
                    case 0: {
                        return null;
                    }
                    case 1: {
                        return (zzdym)arrayList0.get(0);
                    }
                    default: {
                        try {
                            return (zzdym)class0.getMethod("combine", Collection.class).invoke(null, arrayList0);
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
}

