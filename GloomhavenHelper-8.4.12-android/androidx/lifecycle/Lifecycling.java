package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class Lifecycling {
    private static final int GENERATED_CALLBACK = 2;
    private static final int REFLECTIVE_CALLBACK = 1;
    private static Map sCallbackCache;
    private static Map sClassToAdapters;

    static {
        Lifecycling.sCallbackCache = new HashMap();
        Lifecycling.sClassToAdapters = new HashMap();
    }

    private static GeneratedAdapter createGeneratedAdapter(Constructor constructor0, Object object0) {
        try {
            return (GeneratedAdapter)constructor0.newInstance(object0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
        catch(InstantiationException instantiationException0) {
            throw new RuntimeException(instantiationException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException(invocationTargetException0);
        }
    }

    @Nullable
    private static Constructor generatedConstructor(Class class0) {
        try {
            Package package0 = class0.getPackage();
            String s = class0.getCanonicalName();
            String s1 = package0 == null ? "" : package0.getName();
            if(!s1.isEmpty()) {
                s = s.substring(s1.length() + 1);
            }
            String s2 = Lifecycling.getAdapterName(s);
            if(!s1.isEmpty()) {
                s2 = s1 + "." + s2;
            }
            Constructor constructor0 = Class.forName(s2).getDeclaredConstructor(class0);
            if(!constructor0.isAccessible()) {
                constructor0.setAccessible(true);
            }
            return constructor0;
        }
        catch(ClassNotFoundException unused_ex) {
            return null;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new RuntimeException(noSuchMethodException0);
        }
    }

    public static String getAdapterName(String s) {
        return s.replace(".", "_") + "_LifecycleAdapter";
    }

    @NonNull
    static GenericLifecycleObserver getCallback(Object object0) {
        if(object0 instanceof FullLifecycleObserver) {
            return new FullLifecycleObserverAdapter(((FullLifecycleObserver)object0));
        }
        if(object0 instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver)object0;
        }
        Class class0 = object0.getClass();
        if(Lifecycling.getObserverConstructorType(class0) == 2) {
            List list0 = (List)Lifecycling.sClassToAdapters.get(class0);
            if(list0.size() == 1) {
                return new SingleGeneratedAdapterObserver(Lifecycling.createGeneratedAdapter(((Constructor)list0.get(0)), object0));
            }
            GeneratedAdapter[] arr_generatedAdapter = new GeneratedAdapter[list0.size()];
            for(int v = 0; v < list0.size(); ++v) {
                arr_generatedAdapter[v] = Lifecycling.createGeneratedAdapter(((Constructor)list0.get(v)), object0);
            }
            return new CompositeGeneratedAdaptersObserver(arr_generatedAdapter);
        }
        return new ReflectiveGenericLifecycleObserver(object0);
    }

    private static int getObserverConstructorType(Class class0) {
        if(Lifecycling.sCallbackCache.containsKey(class0)) {
            return (int)(((Integer)Lifecycling.sCallbackCache.get(class0)));
        }
        int v = Lifecycling.resolveObserverCallbackType(class0);
        Lifecycling.sCallbackCache.put(class0, v);
        return v;
    }

    private static boolean isLifecycleParent(Class class0) {
        return class0 != null && LifecycleObserver.class.isAssignableFrom(class0);
    }

    private static int resolveObserverCallbackType(Class class0) {
        if(class0.getCanonicalName() == null) {
            return 1;
        }
        Constructor constructor0 = Lifecycling.generatedConstructor(class0);
        if(constructor0 != null) {
            Lifecycling.sClassToAdapters.put(class0, Collections.singletonList(constructor0));
            return 2;
        }
        if(ClassesInfoCache.sInstance.hasLifecycleMethods(class0)) {
            return 1;
        }
        Class class1 = class0.getSuperclass();
        ArrayList arrayList0 = null;
        if(Lifecycling.isLifecycleParent(class1)) {
            if(Lifecycling.getObserverConstructorType(class1) == 1) {
                return 1;
            }
            arrayList0 = new ArrayList(((Collection)Lifecycling.sClassToAdapters.get(class1)));
        }
        Class[] arr_class = class0.getInterfaces();
        for(int v = 0; v < arr_class.length; ++v) {
            Class class2 = arr_class[v];
            if(Lifecycling.isLifecycleParent(class2)) {
                if(Lifecycling.getObserverConstructorType(class2) == 1) {
                    return 1;
                }
                if(arrayList0 == null) {
                    arrayList0 = new ArrayList();
                }
                arrayList0.addAll(((Collection)Lifecycling.sClassToAdapters.get(class2)));
            }
        }
        if(arrayList0 != null) {
            Lifecycling.sClassToAdapters.put(class0, arrayList0);
            return 2;
        }
        return 1;
    }
}

