package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzq;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbjz implements zzakh {
    private final zzpo zzfei;
    private final Context zzur;
    private final PowerManager zzza;

    public zzbjz(Context context0, zzpo zzpo0) {
        this.zzur = context0;
        this.zzfei = zzpo0;
        this.zzza = (PowerManager)context0.getSystemService("power");
    }

    public final JSONObject zza(zzbkd zzbkd0) throws JSONException {
        JSONObject jSONObject1;
        JSONArray jSONArray0 = new JSONArray();
        JSONObject jSONObject0 = new JSONObject();
        if(zzbkd0.zzffb == null) {
            jSONObject1 = new JSONObject();
        }
        else {
            zzpu zzpu0 = zzbkd0.zzffb;
            if(this.zzfei.zzkq() == null) {
                throw new JSONException("Active view Info cannot be null.");
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("afmaVersion", this.zzfei.zzkp()).put("activeViewJSON", this.zzfei.zzkq()).put("timestamp", zzbkd0.timestamp).put("adFormat", this.zzfei.zzko()).put("hashCode", this.zzfei.zzkr()).put("isMraid", false).put("isStopped", false).put("isPaused", zzbkd0.zzfey).put("isNative", this.zzfei.zzks()).put("isScreenOn", (Build.VERSION.SDK_INT < 20 ? this.zzza.isScreenOn() : this.zzza.isInteractive())).put("appMuted", zzq.zzla().zzpk()).put("appVolume", ((double)zzq.zzla().zzpj())).put("deviceVolume", ((double)zzaxd.zzbh(this.zzur.getApplicationContext())));
            Rect rect0 = new Rect();
            Display display0 = ((WindowManager)this.zzur.getSystemService("window")).getDefaultDisplay();
            rect0.right = display0.getWidth();
            rect0.bottom = display0.getHeight();
            DisplayMetrics displayMetrics0 = this.zzur.getResources().getDisplayMetrics();
            jSONObject2.put("windowVisibility", zzpu0.zzzh).put("isAttachedToWindow", zzpu0.zzboi).put("viewBox", new JSONObject().put("top", zzpu0.zzboj.top).put("bottom", zzpu0.zzboj.bottom).put("left", zzpu0.zzboj.left).put("right", zzpu0.zzboj.right)).put("adBox", new JSONObject().put("top", zzpu0.zzbok.top).put("bottom", zzpu0.zzbok.bottom).put("left", zzpu0.zzbok.left).put("right", zzpu0.zzbok.right)).put("globalVisibleBox", new JSONObject().put("top", zzpu0.zzbol.top).put("bottom", zzpu0.zzbol.bottom).put("left", zzpu0.zzbol.left).put("right", zzpu0.zzbol.right)).put("globalVisibleBoxVisible", zzpu0.zzbom).put("localVisibleBox", new JSONObject().put("top", zzpu0.zzbon.top).put("bottom", zzpu0.zzbon.bottom).put("left", zzpu0.zzbon.left).put("right", zzpu0.zzbon.right)).put("localVisibleBoxVisible", zzpu0.zzboo).put("hitBox", new JSONObject().put("top", zzpu0.zzbop.top).put("bottom", zzpu0.zzbop.bottom).put("left", zzpu0.zzbop.left).put("right", zzpu0.zzbop.right)).put("screenDensity", ((double)displayMetrics0.density));
            jSONObject2.put("isVisible", zzbkd0.zzbnz);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcld)).booleanValue()) {
                JSONArray jSONArray1 = new JSONArray();
                if(zzpu0.zzbor != null) {
                    for(Object object0: zzpu0.zzbor) {
                        jSONArray1.put(new JSONObject().put("top", ((Rect)object0).top).put("bottom", ((Rect)object0).bottom).put("left", ((Rect)object0).left).put("right", ((Rect)object0).right));
                    }
                }
                jSONObject2.put("scrollableContainerBoxes", jSONArray1);
            }
            if(!TextUtils.isEmpty(zzbkd0.zzffa)) {
                jSONObject2.put("doneReasonCode", "u");
            }
            jSONObject1 = jSONObject2;
        }
        jSONArray0.put(jSONObject1);
        jSONObject0.put("units", jSONArray0);
        return jSONObject0;
    }

    @Override  // com.google.android.gms.internal.ads.zzakh
    public final JSONObject zzj(Object object0) throws JSONException {
        return this.zza(((zzbkd)object0));
    }
}

