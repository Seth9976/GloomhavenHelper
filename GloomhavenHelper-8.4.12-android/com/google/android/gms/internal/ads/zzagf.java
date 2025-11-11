package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzagf implements zzafz {
    private final Object lock;
    @GuardedBy("lock")
    private final Map zzcyw;

    public zzagf() {
        this.lock = new Object();
        this.zzcyw = new HashMap();
    }

    public final zzdof zza(zzajb zzajb0, String s, JSONObject jSONObject0) {
        zzdof zzdof0 = new zzazy();
        this.zza("a0f0f72d-320e-4384-bfc5-e764953602f5", new zzage(this, ((zzazy)zzdof0)));
        try {
            JSONObject jSONObject1 = new JSONObject();
            jSONObject1.put("id", "a0f0f72d-320e-4384-bfc5-e764953602f5");
            jSONObject1.put("args", jSONObject0);
            zzajb0.zzb(s, jSONObject1);
        }
        catch(Exception exception0) {
            ((zzazy)zzdof0).setException(exception0);
        }
        return zzdof0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        String s5;
        String s = (String)map0.get("id");
        String s1 = (String)map0.get("fail");
        String s2 = (String)map0.get("fail_reason");
        String s3 = (String)map0.get("fail_stack");
        String s4 = (String)map0.get("result");
        if(TextUtils.isEmpty(s3)) {
            s2 = "Unknown Fail Reason.";
        }
        if(TextUtils.isEmpty(s3)) {
            s5 = "";
        }
        else {
            String s6 = String.valueOf(s3);
            s5 = s6.length() == 0 ? new String("\n") : "\n" + s6;
        }
        synchronized(this.lock) {
            zzagh zzagh0 = (zzagh)this.zzcyw.remove(s);
            if(zzagh0 == null) {
                String s7 = String.valueOf(s);
                zzawf.zzfa((s7.length() == 0 ? new String("Received result for unexpected method invocation: ") : "Received result for unexpected method invocation: " + s7));
                return;
            }
            if(!TextUtils.isEmpty(s1)) {
                String s8 = String.valueOf(s2);
                String s9 = String.valueOf(s5);
                zzagh0.onFailure((s9.length() == 0 ? new String(s8) : s8 + s9));
                return;
            }
            if(s4 == null) {
                zzagh0.zzc(null);
                return;
            }
            try {
                JSONObject jSONObject0 = new JSONObject(s4);
                if(zzawf.zzvx()) {
                    String s10 = jSONObject0.toString(2);
                    zzawf.zzee((s10.length() == 0 ? new String("Result GMSG: ") : "Result GMSG: " + s10));
                }
                zzagh0.zzc(jSONObject0);
            }
            catch(JSONException jSONException0) {
                zzagh0.onFailure(jSONException0.getMessage());
            }
        }
    }

    public final void zza(String s, zzagh zzagh0) {
        synchronized(this.lock) {
            this.zzcyw.put(s, zzagh0);
        }
    }
}

