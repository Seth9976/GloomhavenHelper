package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class zzic implements PrivilegedExceptionAction {
    @Override
    public final Object run() throws Exception {
        Class class0 = Unsafe.class;
        Field[] arr_field = class0.getDeclaredFields();
        for(int v = 0; v < arr_field.length; ++v) {
            Field field0 = arr_field[v];
            field0.setAccessible(true);
            Object object0 = field0.get(null);
            if(class0.isInstance(object0)) {
                return (Unsafe)class0.cast(object0);
            }
        }
        return null;
    }
}

