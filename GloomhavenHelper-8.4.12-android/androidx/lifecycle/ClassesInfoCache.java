package androidx.lifecycle;

import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

class ClassesInfoCache {
    static class CallbackInfo {
        final Map mEventToHandlers;
        final Map mHandlerToEvent;

        CallbackInfo(Map map0) {
            this.mHandlerToEvent = map0;
            this.mEventToHandlers = new HashMap();
            for(Object object0: map0.entrySet()) {
                Event lifecycle$Event0 = (Event)((Map.Entry)object0).getValue();
                List list0 = (List)this.mEventToHandlers.get(lifecycle$Event0);
                if(list0 == null) {
                    list0 = new ArrayList();
                    this.mEventToHandlers.put(lifecycle$Event0, list0);
                }
                list0.add(((Map.Entry)object0).getKey());
            }
        }

        void invokeCallbacks(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0, Object object0) {
            CallbackInfo.invokeMethodsForEvent(((List)this.mEventToHandlers.get(lifecycle$Event0)), lifecycleOwner0, lifecycle$Event0, object0);
            CallbackInfo.invokeMethodsForEvent(((List)this.mEventToHandlers.get(Event.ON_ANY)), lifecycleOwner0, lifecycle$Event0, object0);
        }

        private static void invokeMethodsForEvent(List list0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0, Object object0) {
            if(list0 != null) {
                for(int v = list0.size() - 1; v >= 0; --v) {
                    ((MethodReference)list0.get(v)).invokeCallback(lifecycleOwner0, lifecycle$Event0, object0);
                }
            }
        }
    }

    static class MethodReference {
        final int mCallType;
        final Method mMethod;

        MethodReference(int v, Method method0) {
            this.mCallType = v;
            this.mMethod = method0;
            this.mMethod.setAccessible(true);
        }

        // 去混淆评级： 低(40)
        @Override
        public boolean equals(Object object0) {
            return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && (this.mCallType == ((MethodReference)object0).mCallType && this.mMethod.getName().equals(((MethodReference)object0).mMethod.getName()));
        }

        @Override
        public int hashCode() {
            int v = this.mMethod.getName().hashCode();
            return this.mCallType * 0x1F + v;
        }

        void invokeCallback(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0, Object object0) {
            try {
                switch(this.mCallType) {
                    case 0: {
                        this.mMethod.invoke(object0);
                        return;
                    }
                    case 1: {
                        this.mMethod.invoke(object0, lifecycleOwner0);
                        return;
                    }
                    case 2: {
                        this.mMethod.invoke(object0, lifecycleOwner0, lifecycle$Event0);
                    }
                }
            }
            catch(InvocationTargetException invocationTargetException0) {
                throw new RuntimeException("Failed to call observer method", invocationTargetException0.getCause());
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new RuntimeException(illegalAccessException0);
            }
        }
    }

    private static final int CALL_TYPE_NO_ARG = 0;
    private static final int CALL_TYPE_PROVIDER = 1;
    private static final int CALL_TYPE_PROVIDER_WITH_EVENT = 2;
    private final Map mCallbackMap;
    private final Map mHasLifecycleMethods;
    static ClassesInfoCache sInstance;

    static {
        ClassesInfoCache.sInstance = new ClassesInfoCache();
    }

    ClassesInfoCache() {
        this.mCallbackMap = new HashMap();
        this.mHasLifecycleMethods = new HashMap();
    }

    private CallbackInfo createInfo(Class class0, @Nullable Method[] arr_method) {
        int v2;
        Class class1 = class0.getSuperclass();
        HashMap hashMap0 = new HashMap();
        if(class1 != null) {
            CallbackInfo classesInfoCache$CallbackInfo0 = this.getInfo(class1);
            if(classesInfoCache$CallbackInfo0 != null) {
                hashMap0.putAll(classesInfoCache$CallbackInfo0.mHandlerToEvent);
            }
        }
        Class[] arr_class = class0.getInterfaces();
        for(int v = 0; v < arr_class.length; ++v) {
            for(Object object0: this.getInfo(arr_class[v]).mHandlerToEvent.entrySet()) {
                this.verifyAndPutHandler(hashMap0, ((MethodReference)((Map.Entry)object0).getKey()), ((Event)((Map.Entry)object0).getValue()), class0);
            }
        }
        if(arr_method == null) {
            arr_method = this.getDeclaredMethods(class0);
        }
        boolean z = false;
        for(int v1 = 0; v1 < arr_method.length; ++v1) {
            Method method0 = arr_method[v1];
            OnLifecycleEvent onLifecycleEvent0 = (OnLifecycleEvent)method0.getAnnotation(OnLifecycleEvent.class);
            if(onLifecycleEvent0 != null) {
                Class[] arr_class1 = method0.getParameterTypes();
                if(arr_class1.length > 0) {
                    if(!arr_class1[0].isAssignableFrom(LifecycleOwner.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    v2 = 1;
                }
                else {
                    v2 = 0;
                }
                Event lifecycle$Event0 = onLifecycleEvent0.value();
                if(arr_class1.length > 1) {
                    if(!arr_class1[1].isAssignableFrom(Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if(lifecycle$Event0 != Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    v2 = 2;
                }
                if(arr_class1.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                this.verifyAndPutHandler(hashMap0, new MethodReference(v2, method0), lifecycle$Event0, class0);
                z = true;
            }
        }
        CallbackInfo classesInfoCache$CallbackInfo1 = new CallbackInfo(hashMap0);
        this.mCallbackMap.put(class0, classesInfoCache$CallbackInfo1);
        this.mHasLifecycleMethods.put(class0, Boolean.valueOf(z));
        return classesInfoCache$CallbackInfo1;
    }

    private Method[] getDeclaredMethods(Class class0) {
        try {
            return class0.getDeclaredMethods();
        }
        catch(NoClassDefFoundError noClassDefFoundError0) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", noClassDefFoundError0);
        }
    }

    CallbackInfo getInfo(Class class0) {
        CallbackInfo classesInfoCache$CallbackInfo0 = (CallbackInfo)this.mCallbackMap.get(class0);
        return classesInfoCache$CallbackInfo0 == null ? this.createInfo(class0, null) : classesInfoCache$CallbackInfo0;
    }

    boolean hasLifecycleMethods(Class class0) {
        if(this.mHasLifecycleMethods.containsKey(class0)) {
            return ((Boolean)this.mHasLifecycleMethods.get(class0)).booleanValue();
        }
        Method[] arr_method = this.getDeclaredMethods(class0);
        for(int v = 0; v < arr_method.length; ++v) {
            if(((OnLifecycleEvent)arr_method[v].getAnnotation(OnLifecycleEvent.class)) != null) {
                this.createInfo(class0, arr_method);
                return true;
            }
        }
        this.mHasLifecycleMethods.put(class0, Boolean.FALSE);
        return false;
    }

    private void verifyAndPutHandler(Map map0, MethodReference classesInfoCache$MethodReference0, Event lifecycle$Event0, Class class0) {
        Event lifecycle$Event1 = (Event)map0.get(classesInfoCache$MethodReference0);
        if(lifecycle$Event1 != null && lifecycle$Event0 != lifecycle$Event1) {
            throw new IllegalArgumentException("Method " + classesInfoCache$MethodReference0.mMethod.getName() + " in " + class0.getName() + " already declared with different @OnLifecycleEvent value: previous" + " value " + lifecycle$Event1 + ", new value " + lifecycle$Event0);
        }
        if(lifecycle$Event1 == null) {
            map0.put(classesInfoCache$MethodReference0, lifecycle$Event0);
        }
    }
}

