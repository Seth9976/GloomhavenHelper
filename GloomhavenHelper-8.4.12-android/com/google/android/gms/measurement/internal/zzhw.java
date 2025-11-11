package com.google.android.gms.measurement.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class zzhw {
    public static Object zza(Object object0) {
        Object object1;
        ObjectOutputStream objectOutputStream0;
        ByteArrayOutputStream byteArrayOutputStream0;
        if(object0 == null) {
            return null;
        }
        try {
            byteArrayOutputStream0 = new ByteArrayOutputStream();
            objectOutputStream0 = new ObjectOutputStream(byteArrayOutputStream0);
            goto label_9;
        }
        catch(Throwable throwable0) {
            try {
                ObjectInputStream objectInputStream0 = null;
                objectOutputStream0 = null;
                goto label_16;
                try {
                label_9:
                    objectOutputStream0.writeObject(object0);
                    objectOutputStream0.flush();
                    objectInputStream0 = null;
                    objectInputStream0 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream0.toByteArray()));
                    object1 = objectInputStream0.readObject();
                    goto label_21;
                }
                catch(Throwable throwable0) {
                }
            label_16:
                if(objectOutputStream0 != null) {
                    objectOutputStream0.close();
                }
                if(objectInputStream0 != null) {
                    objectInputStream0.close();
                }
                throw throwable0;
            label_21:
                objectOutputStream0.close();
                objectInputStream0.close();
                return object1;
            }
            catch(IOException | ClassNotFoundException unused_ex) {
            }
        }
        return null;
    }

    @Nullable
    public static String zza(String s, String[] arr_s, String[] arr_s1) {
        boolean z;
        Preconditions.checkNotNull(arr_s);
        Preconditions.checkNotNull(arr_s1);
        int v = Math.min(arr_s.length, arr_s1.length);
        for(int v1 = 0; v1 < v; ++v1) {
            String s1 = arr_s[v1];
            if(s != null || s1 != null) {
                z = s == null ? false : s.equals(s1);
            }
            else {
                z = true;
            }
            if(z) {
                return arr_s1[v1];
            }
        }
        return null;
    }
}

