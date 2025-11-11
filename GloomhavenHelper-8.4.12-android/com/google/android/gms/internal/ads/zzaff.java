package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzaff implements zzafz {
    private final zzafe zzcxu;

    public zzaff(zzafe zzafe0) {
        this.zzcxu = zzafe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        if(this.zzcxu == null) {
            return;
        }
        String s = (String)map0.get("name");
        if(s == null) {
            zzawf.zzez("Ad metadata with no name parameter.");
            s = "";
        }
        Bundle bundle0 = null;
        if(map0.containsKey("info")) {
            try {
                bundle0 = zzayf.zzh(new JSONObject(((String)map0.get("info"))));
            }
            catch(JSONException jSONException0) {
                zzawf.zzc("Failed to convert ad metadata to JSON.", jSONException0);
            }
        }
        if(bundle0 == null) {
            zzawf.zzey("Failed to convert ad metadata to Bundle.");
            return;
        }
        this.zzcxu.zza(s, bundle0);
    }
}

