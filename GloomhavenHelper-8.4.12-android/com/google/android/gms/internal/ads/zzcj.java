package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public abstract class zzcj {
    private static final String TAG = "zzcj";

    static {
    }

    @Override
    public String toString() {
        try {
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream0 = new ObjectOutputStream(byteArrayOutputStream0);
            objectOutputStream0.writeObject(this.zzbl());
            objectOutputStream0.close();
            return Base64.encodeToString(byteArrayOutputStream0.toByteArray(), 0);
        }
        catch(IOException unused_ex) {
            return null;
        }
    }

    protected abstract void zzap(String arg1);

    protected static HashMap zzaq(String s) {
        try {
            if(!TextUtils.isEmpty(s)) {
                return (HashMap)new ObjectInputStream(new ByteArrayInputStream(Base64.decode(s.getBytes(), 0))).readObject();
            }
        }
        catch(IOException | ClassNotFoundException unused_ex) {
            Log.d("zzcj", "decode object failure");
        }
        return null;
    }

    protected abstract HashMap zzbl();
}

