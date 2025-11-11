package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzft {
    private static final String TAG = "zzft";
    private final String className;
    private final String zzaaf;
    private final int zzaag;
    private volatile Method zzaah;
    private final Class[] zzaai;
    private CountDownLatch zzaaj;
    private final zzei zzuy;

    static {
    }

    public zzft(zzei zzei0, String s, String s1, Class[] arr_class) {
        this.zzaag = 2;
        this.zzaah = null;
        this.zzaaj = new CountDownLatch(1);
        this.zzuy = zzei0;
        this.className = s;
        this.zzaaf = s1;
        this.zzaai = arr_class;
        zzfw zzfw0 = new zzfw(this);
        this.zzuy.zzcc().submit(zzfw0);
    }

    private final String zzb(byte[] arr_b, String s) throws zzdv, UnsupportedEncodingException {
        return new String(this.zzuy.zzce().zza(arr_b, s), "UTF-8");
    }

    private final void zzcw() {
        try {
            Class class0 = this.zzuy.zzcd().loadClass(this.zzb(this.zzuy.zzcf(), this.className));
            if(class0 != null) {
                this.zzaah = class0.getMethod(this.zzb(this.zzuy.zzcf(), this.zzaaf), this.zzaai);
                goto label_15;
            }
            return;
        }
        catch(zzdv unused_ex) {
            return;
        }
        catch(UnsupportedEncodingException unused_ex) {
            return;
        }
        catch(ClassNotFoundException unused_ex) {
            return;
        }
        catch(NoSuchMethodException unused_ex) {
            return;
        }
        catch(NullPointerException unused_ex) {
            return;
        }
        finally {
            this.zzaaj.countDown();
        }
    label_15:
        if(this.zzaah == null) {
        }
    }

    public final Method zzcx() {
        if(this.zzaah != null) {
            return this.zzaah;
        }
        try {
            return this.zzaaj.await(2L, TimeUnit.SECONDS) ? this.zzaah : null;
        }
        catch(InterruptedException unused_ex) {
            return null;
        }
    }
}

