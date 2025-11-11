package com.google.android.gms.dynamic;

import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.reflect.Field;

@KeepForSdk
public final class ObjectWrapper extends Stub {
    private final Object zzib;

    private ObjectWrapper(Object object0) {
        this.zzib = object0;
    }

    @KeepForSdk
    public static Object unwrap(IObjectWrapper iObjectWrapper0) {
        if(iObjectWrapper0 instanceof ObjectWrapper) {
            return ((ObjectWrapper)iObjectWrapper0).zzib;
        }
        IBinder iBinder0 = iObjectWrapper0.asBinder();
        Field[] arr_field = iBinder0.getClass().getDeclaredFields();
        Field field0 = null;
        int v1 = 0;
        for(int v = 0; v < arr_field.length; ++v) {
            Field field1 = arr_field[v];
            if(!field1.isSynthetic()) {
                ++v1;
                field0 = field1;
            }
        }
        if(v1 != 1) {
            throw new IllegalArgumentException("Unexpected number of IObjectWrapper declared fields: " + arr_field.length);
        }
        if(!field0.isAccessible()) {
            field0.setAccessible(true);
            try {
                return field0.get(iBinder0);
            }
            catch(NullPointerException nullPointerException0) {
                throw new IllegalArgumentException("Binder object is null.", nullPointerException0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new IllegalArgumentException("Could not access the field in remoteBinder.", illegalAccessException0);
            }
        }
        throw new IllegalArgumentException("IObjectWrapper declared field not private!");
    }

    @KeepForSdk
    public static IObjectWrapper wrap(Object object0) {
        return new ObjectWrapper(object0);
    }
}

