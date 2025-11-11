package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcrw extends zzanu {
    private final String zzczf;
    private final zzanq zzgfh;
    private zzazy zzgfi;
    private final JSONObject zzgfj;
    @GuardedBy("this")
    private boolean zzgfk;

    public zzcrw(String s, zzanq zzanq0, zzazy zzazy0) {
        this.zzgfj = new JSONObject();
        this.zzgfk = false;
        this.zzgfi = zzazy0;
        this.zzczf = s;
        this.zzgfh = zzanq0;
        try {
            String s1 = this.zzgfh.zzth().toString();
            this.zzgfj.put("adapter_version", s1);
            String s2 = this.zzgfh.zzti().toString();
            this.zzgfj.put("sdk_version", s2);
            this.zzgfj.put("name", this.zzczf);
        }
        catch(JSONException | NullPointerException | RemoteException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanv
    public final void onFailure(String s) throws RemoteException {
        synchronized(this) {
            if(this.zzgfk) {
                return;
            }
            try {
                this.zzgfj.put("signal_error", s);
            }
            catch(JSONException unused_ex) {
            }
            this.zzgfi.set(this.zzgfj);
            this.zzgfk = true;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzanv
    public final void zzdo(String s) throws RemoteException {
        __monitor_enter(this);
        if(this.zzgfk) {
            __monitor_exit(this);
            return;
        }
        if(s == null) {
            try {
                this.onFailure("Adapter returned null signals");
            }
            catch(Throwable throwable0) {
                __monitor_exit(this);
                throw throwable0;
            }
            __monitor_exit(this);
            return;
        }
        try {
            try {
                this.zzgfj.put("signals", s);
            }
            catch(JSONException unused_ex) {
            }
            this.zzgfi.set(this.zzgfj);
            this.zzgfk = true;
        }
        catch(Throwable throwable0) {
            __monitor_exit(this);
            throw throwable0;
        }
        __monitor_exit(this);
    }
}

