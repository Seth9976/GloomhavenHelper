package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Map.Entry;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzbxz implements zzbzh {
    private final zzazo zzblu;
    private final Clock zzbmz;
    private final zzdq zzehb;
    private final zzdis zzfdr;
    private final zzbzg zzfge;
    private final zzdei zzfhg;
    private final zzdeu zzfir;
    private final JSONObject zzfnc;
    private final zzccv zzfnd;
    private final zzbyz zzfne;
    private final zzbqp zzfnf;
    private final zzbqg zzfng;
    private final zzbkb zzfnh;
    private final zzbzy zzfni;
    private final zzbuu zzfnj;
    private boolean zzfnk;
    private boolean zzfnl;
    private boolean zzfnm;
    private boolean zzfnn;
    private Point zzfno;
    private Point zzfnp;
    private long zzfnq;
    private long zzfnr;
    private zzwq zzfns;
    private final Context zzur;

    public zzbxz(Context context0, zzbzg zzbzg0, JSONObject jSONObject0, zzccv zzccv0, zzbyz zzbyz0, zzdq zzdq0, zzbqp zzbqp0, zzbqg zzbqg0, zzdei zzdei0, zzazo zzazo0, zzdeu zzdeu0, zzbkb zzbkb0, zzbzy zzbzy0, Clock clock0, zzbuu zzbuu0, zzdis zzdis0) {
        this.zzfnk = false;
        this.zzfnm = false;
        this.zzfnn = false;
        this.zzfno = new Point();
        this.zzfnp = new Point();
        this.zzfnq = 0L;
        this.zzfnr = 0L;
        this.zzur = context0;
        this.zzfge = zzbzg0;
        this.zzfnc = jSONObject0;
        this.zzfnd = zzccv0;
        this.zzfne = zzbyz0;
        this.zzehb = zzdq0;
        this.zzfnf = zzbqp0;
        this.zzfng = zzbqg0;
        this.zzfhg = zzdei0;
        this.zzblu = zzazo0;
        this.zzfir = zzdeu0;
        this.zzfnh = zzbkb0;
        this.zzfni = zzbzy0;
        this.zzbmz = clock0;
        this.zzfnj = zzbuu0;
        this.zzfdr = zzdis0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void cancelUnconfirmedClick() {
        if(!this.zzfnc.optBoolean("custom_one_point_five_click_enabled", false)) {
            return;
        }
        this.zzfni.cancelUnconfirmedClick();
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void destroy() {
        this.zzfnd.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final boolean isCustomClickGestureEnabled() {
        return this.zzajr();
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void setClickConfirmingView(View view0) {
        if(!this.zzfnc.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzawf.zzfa("setClickConfirmingView: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            return;
        }
        zzbzy zzbzy0 = this.zzfni;
        if(view0 != null) {
            view0.setOnClickListener(zzbzy0);
            view0.setClickable(true);
            zzbzy0.zzfrd = new WeakReference(view0);
        }
    }

    private final JSONObject zza(@Nullable Map map0, @Nullable Map map1, @Nullable View view0) {
        JSONObject jSONObject3;
        JSONObject jSONObject0 = new JSONObject();
        if(map0 != null && view0 != null) {
            int[] arr_v = zzbxz.zzx(view0);
            for(Object object0: map0.entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                View view1 = (View)((WeakReference)map$Entry0.getValue()).get();
                if(view1 != null) {
                    int[] arr_v1 = zzbxz.zzx(view1);
                    JSONObject jSONObject1 = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("width", this.zzdi(view1.getMeasuredWidth()));
                        jSONObject2.put("height", this.zzdi(view1.getMeasuredHeight()));
                        boolean z = false;
                        jSONObject2.put("x", this.zzdi(arr_v1[0] - arr_v[0]));
                        jSONObject2.put("y", this.zzdi(arr_v1[1] - arr_v[1]));
                        jSONObject2.put("relative_to", "ad_view");
                        jSONObject1.put("frame", jSONObject2);
                        Rect rect0 = new Rect();
                        if(view1.getLocalVisibleRect(rect0)) {
                            jSONObject3 = this.zzb(rect0);
                        }
                        else {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("width", 0);
                            jSONObject4.put("height", 0);
                            jSONObject4.put("x", this.zzdi(arr_v1[0] - arr_v[0]));
                            jSONObject4.put("y", this.zzdi(arr_v1[1] - arr_v[1]));
                            jSONObject4.put("relative_to", "ad_view");
                            jSONObject3 = jSONObject4;
                        }
                        jSONObject1.put("visible_bounds", jSONObject3);
                        if(view1 instanceof TextView) {
                            jSONObject1.put("text_color", ((TextView)view1).getCurrentTextColor());
                            jSONObject1.put("font_size", ((double)((TextView)view1).getTextSize()));
                            jSONObject1.put("text", ((TextView)view1).getText());
                        }
                        if(map1 != null && map1.containsKey(map$Entry0.getKey()) && view1.isClickable()) {
                            z = true;
                        }
                        jSONObject1.put("is_clickable", z);
                        jSONObject0.put(((String)map$Entry0.getKey()), jSONObject1);
                    }
                    catch(JSONException unused_ex) {
                        zzawf.zzfa("Unable to get asset views information");
                    }
                }
            }
            return jSONObject0;
        }
        return jSONObject0;
    }

    private final void zza(@Nullable View view0, @Nullable JSONObject jSONObject0, @Nullable JSONObject jSONObject1, @Nullable JSONObject jSONObject2, @Nullable JSONObject jSONObject3, @Nullable String s, @Nullable JSONObject jSONObject4, @Nullable JSONObject jSONObject5, boolean z, boolean z1) {
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzfnc);
            jSONObject6.put("asset_view_signal", jSONObject1);
            jSONObject6.put("ad_view_signal", jSONObject0);
            jSONObject6.put("click_signal", jSONObject4);
            jSONObject6.put("scroll_view_signal", jSONObject2);
            jSONObject6.put("lock_screen_signal", jSONObject3);
            String s1 = this.zzfne.getCustomTemplateId();
            boolean z2 = false;
            jSONObject6.put("has_custom_click_handler", this.zzfge.zzgb(s1) != null);
            jSONObject6.put("provided_signals", jSONObject5);
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("asset_id", s);
            jSONObject7.put("template", this.zzfne.zzake());
            jSONObject7.put("view_aware_api_used", z);
            jSONObject7.put("custom_mute_requested", this.zzfir.zzdff != null && this.zzfir.zzdff.zzbkj);
            jSONObject7.put("custom_mute_enabled", !this.zzfne.getMuteThisAdReasons().isEmpty() && this.zzfne.zzakh() != null);
            if(this.zzfni.zzaln() != null && this.zzfnc.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject7.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject7.put("timestamp", this.zzbmz.currentTimeMillis());
            if(this.zzfnn && this.zzajr()) {
                jSONObject7.put("custom_click_gesture_eligible", true);
            }
            if(z1) {
                jSONObject7.put("is_custom_click_gesture", true);
            }
            String s2 = this.zzfne.getCustomTemplateId();
            if(this.zzfge.zzgb(s2) != null) {
                z2 = true;
            }
            jSONObject7.put("has_custom_click_handler", z2);
            jSONObject7.put("click_signals", this.zzv(view0));
            jSONObject6.put("click", jSONObject7);
            JSONObject jSONObject8 = new JSONObject();
            long v = this.zzbmz.currentTimeMillis();
            jSONObject8.put("time_from_last_touch_down", v - this.zzfnq);
            jSONObject8.put("time_from_last_touch", v - this.zzfnr);
            jSONObject6.put("touch_signal", jSONObject8);
            zzazu.zza(this.zzfnd.zzc("google.afma.nativeAds.handleClick", jSONObject6), "Error during performing handleClick");
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Unable to create click JSON.", jSONException0);
        }
    }

    private final boolean zza(@Nullable JSONObject jSONObject0, @Nullable JSONObject jSONObject1, @Nullable JSONObject jSONObject2, @Nullable JSONObject jSONObject3, @Nullable String s, @Nullable JSONObject jSONObject4) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("ad", this.zzfnc);
            jSONObject5.put("asset_view_signal", jSONObject1);
            jSONObject5.put("ad_view_signal", jSONObject0);
            jSONObject5.put("scroll_view_signal", jSONObject2);
            jSONObject5.put("lock_screen_signal", jSONObject3);
            jSONObject5.put("provided_signals", jSONObject4);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmo)).booleanValue()) {
                jSONObject5.put("view_signals", s);
            }
            zzbyb zzbyb0 = new zzbyb(this, null);
            this.zzfnd.zza("/logScionEvent", zzbyb0);
            zzbya zzbya0 = new zzbya(this, null);
            this.zzfnd.zza("/nativeImpression", zzbya0);
            zzazu.zza(this.zzfnd.zzc("google.afma.nativeAds.handleImpression", jSONObject5), "Error during performing handleImpression");
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Unable to create impression JSON.", jSONException0);
            return false;
        }
        if(!this.zzfnk && this.zzfhg.zzgpv != null) {
            this.zzfnk |= zzq.zzlf().zzb(this.zzur, this.zzblu.zzbmj, this.zzfhg.zzgpv.toString(), this.zzfir.zzgqr);
        }
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(@Nullable View view0, MotionEvent motionEvent0, @Nullable View view1) {
        int[] arr_v = zzbxz.zzx(view1);
        this.zzfno = new Point(((int)motionEvent0.getRawX()) - arr_v[0], ((int)motionEvent0.getRawY()) - arr_v[1]);
        long v = this.zzbmz.currentTimeMillis();
        this.zzfnr = v;
        if(motionEvent0.getAction() == 0) {
            this.zzfnq = v;
            this.zzfnp = this.zzfno;
        }
        MotionEvent motionEvent1 = MotionEvent.obtain(motionEvent0);
        motionEvent1.setLocation(((float)this.zzfno.x), ((float)this.zzfno.y));
        this.zzehb.zza(motionEvent1);
        motionEvent1.recycle();
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, @Nullable View view1, @Nullable Map map0, @Nullable Map map1, boolean z) {
        JSONObject jSONObject0 = this.zza(map0, map1, view1);
        JSONObject jSONObject1 = this.zzs(view1);
        JSONObject jSONObject2 = zzbxz.zzt(view1);
        JSONObject jSONObject3 = this.zzu(view1);
        String s = this.zzb(view0, map0);
        this.zza(view0, jSONObject1, jSONObject0, jSONObject2, jSONObject3, s, this.zzfw(s), null, z, false);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, @Nullable Map map0) {
        this.zzfno = new Point();
        this.zzfnp = new Point();
        this.zzfnj.zzr(view0);
        this.zzfnl = false;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(@Nullable View view0, @Nullable Map map0, @Nullable Map map1) {
        JSONObject jSONObject0 = this.zza(map0, map1, view0);
        this.zza(this.zzs(view0), jSONObject0, zzbxz.zzt(view0), this.zzu(view0), this.zzw(view0), null);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, @Nullable Map map0, @Nullable Map map1, View.OnTouchListener view$OnTouchListener0, View.OnClickListener view$OnClickListener0) {
        this.zzfno = new Point();
        this.zzfnp = new Point();
        if(!this.zzfnl) {
            this.zzfnj.zzq(view0);
            this.zzfnl = true;
        }
        view0.setOnTouchListener(view$OnTouchListener0);
        view0.setClickable(true);
        view0.setOnClickListener(view$OnClickListener0);
        this.zzfnh.zzo(this);
        boolean z = zzayl.zzcs(this.zzblu.zzdxg);
        if(map0 != null) {
            for(Object object0: map0.entrySet()) {
                View view1 = (View)((WeakReference)((Map.Entry)object0).getValue()).get();
                if(view1 != null) {
                    if(z) {
                        view1.setOnTouchListener(view$OnTouchListener0);
                    }
                    view1.setClickable(true);
                    view1.setOnClickListener(view$OnClickListener0);
                }
            }
        }
        if(map1 != null) {
            for(Object object1: map1.entrySet()) {
                View view2 = (View)((WeakReference)((Map.Entry)object1).getValue()).get();
                if(view2 != null) {
                    if(z) {
                        view2.setOnTouchListener(view$OnTouchListener0);
                    }
                    view2.setClickable(false);
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(@Nullable View view0, @Nullable Map map0, @Nullable Map map1, boolean z) {
        if(!this.zzfnn) {
            zzawf.zzeb("Custom click reporting failed. enableCustomClickGesture is not set.");
            return;
        }
        if(!this.zzajr()) {
            zzawf.zzeb("Custom click reporting failed. Ad unit id not whitelisted.");
            return;
        }
        JSONObject jSONObject0 = this.zza(map0, map1, view0);
        JSONObject jSONObject1 = this.zzs(view0);
        JSONObject jSONObject2 = zzbxz.zzt(view0);
        JSONObject jSONObject3 = this.zzu(view0);
        String s = this.zzb(null, map0);
        this.zza(view0, jSONObject1, jSONObject0, jSONObject2, jSONObject3, s, this.zzfw(s), null, z, true);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(zzaem zzaem0) {
        if(!this.zzfnc.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzawf.zzfa("setUnconfirmedClickListener: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            return;
        }
        this.zzfni.zza(zzaem0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(zzwq zzwq0) {
        this.zzfns = zzwq0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(@Nullable zzwu zzwu0) {
        try {
            if(this.zzfnm) {
                return;
            }
            if(zzwu0 == null && this.zzfne.zzakh() != null) {
                this.zzfnm = true;
                String s = this.zzfne.zzakh().zzpm();
                this.zzfdr.zzeo(s);
                this.zzajt();
                return;
            }
            this.zzfnm = true;
            String s1 = zzwu0.zzpm();
            this.zzfdr.zzeo(s1);
            this.zzajt();
        }
        catch(RemoteException remoteException0) {
            zzawf.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    private final boolean zzajr() {
        return this.zzfnc.optBoolean("allow_custom_click_gesture", false);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzajs() {
        this.zza(null, null, null, null, null, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzajt() {
        try {
            if(this.zzfns != null) {
                this.zzfns.onAdMuted();
            }
        }
        catch(RemoteException remoteException0) {
            zzawf.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzaju() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject0 = new JSONObject();
            jSONObject0.put("ad", this.zzfnc);
            zzazu.zza(this.zzfnd.zzc("google.afma.nativeAds.handleDownloadedImpression", jSONObject0), "Error during performing handleDownloadedImpression");
        }
        catch(JSONException jSONException0) {
            zzazh.zzc("", jSONException0);
        }
    }

    @Nullable
    private final JSONObject zzajv() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            jSONObject0.put("x", this.zzdi(this.zzfno.x));
            jSONObject0.put("y", this.zzdi(this.zzfno.y));
            jSONObject0.put("start_x", this.zzdi(this.zzfnp.x));
            jSONObject0.put("start_y", this.zzdi(this.zzfnp.y));
            return jSONObject0;
        }
        catch(JSONException jSONException0) {
            zzawf.zzc("Error occurred while putting signals into JSON object.", jSONException0);
            return null;
        }
    }

    @Nullable
    private final String zzb(@Nullable View view0, @Nullable Map map0) {
        if(map0 != null && view0 != null) {
            for(Object object0: map0.entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                if(view0.equals(((View)((WeakReference)map$Entry0.getValue()).get()))) {
                    return (String)map$Entry0.getKey();
                }
                if(false) {
                    break;
                }
            }
        }
        int v = this.zzfne.zzake();
        if(v != 6) {
            switch(v) {
                case 1: {
                    return "1099";
                }
                case 2: {
                    return "2099";
                }
                case 3: {
                    return null;
                }
                default: {
                    return null;
                }
            }
        }
        return "3099";
    }

    private final JSONObject zzb(Rect rect0) throws JSONException {
        JSONObject jSONObject0 = new JSONObject();
        jSONObject0.put("width", this.zzdi(rect0.right - rect0.left));
        jSONObject0.put("height", this.zzdi(rect0.bottom - rect0.top));
        jSONObject0.put("x", this.zzdi(rect0.left));
        jSONObject0.put("y", this.zzdi(rect0.top));
        jSONObject0.put("relative_to", "self");
        return jSONObject0;
    }

    private final int zzdi(int v) {
        return zzvh.zzoz().zzc(this.zzur, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzf(@Nullable Bundle bundle0) {
        if(bundle0 == null) {
            zzawf.zzeb("Click data is null. No click is reported.");
            return;
        }
        if(!this.zzfu("click_reporting")) {
            zzawf.zzey("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
            return;
        }
        Bundle bundle1 = bundle0.getBundle("click_signal");
        this.zza(null, null, null, null, null, (bundle1 == null ? null : bundle1.getString("asset_id")), null, zzq.zzkv().zza(bundle0, null), false, false);
    }

    private final boolean zzfu(String s) {
        JSONObject jSONObject0 = this.zzfnc.optJSONObject("allow_pub_event_reporting");
        return jSONObject0 != null && jSONObject0.optBoolean(s, false);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzfv(String s) {
        this.zza(null, null, null, null, null, s, null, null, false, false);
    }

    private final JSONObject zzfw(@Nullable String s) {
        JSONObject jSONObject0 = null;
        try {
            jSONObject0 = new JSONObject();
            jSONObject0.put("click_point", this.zzajv());
            jSONObject0.put("asset_id", s);
        }
        catch(Exception exception0) {
            zzawf.zzc("Error occurred while grabbing click signals.", exception0);
        }
        return jSONObject0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzg(@Nullable Bundle bundle0) {
        if(bundle0 == null) {
            zzawf.zzeb("Touch event data is null. No touch event is reported.");
            return;
        }
        if(!this.zzfu("touch_reporting")) {
            zzawf.zzey("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
            return;
        }
        int v = (int)bundle0.getFloat("x");
        int v1 = (int)bundle0.getFloat("y");
        int v2 = bundle0.getInt("duration_ms");
        this.zzehb.zzcb().zza(v, v1, v2);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final boolean zzh(Bundle bundle0) {
        if(!this.zzfu("impression_reporting")) {
            zzawf.zzey("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
            return false;
        }
        return this.zza(null, null, null, null, null, zzq.zzkv().zza(bundle0, null));
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzru() {
        this.zzfnn = true;
    }

    private final JSONObject zzs(@Nullable View view0) {
        String s;
        JSONObject jSONObject2;
        JSONObject jSONObject0 = new JSONObject();
        if(view0 == null) {
            return jSONObject0;
        }
        try {
            int[] arr_v = zzbxz.zzx(view0);
            JSONObject jSONObject1 = new JSONObject();
            jSONObject1.put("width", this.zzdi(view0.getMeasuredWidth()));
            jSONObject1.put("height", this.zzdi(view0.getMeasuredHeight()));
            jSONObject1.put("x", this.zzdi(arr_v[0]));
            jSONObject1.put("y", this.zzdi(arr_v[1]));
            jSONObject1.put("relative_to", "window");
            jSONObject0.put("frame", jSONObject1);
            Rect rect0 = new Rect();
            if(view0.getGlobalVisibleRect(rect0)) {
                jSONObject2 = this.zzb(rect0);
            }
            else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("width", 0);
                jSONObject3.put("height", 0);
                jSONObject3.put("x", this.zzdi(arr_v[0]));
                jSONObject3.put("y", this.zzdi(arr_v[1]));
                jSONObject3.put("relative_to", "window");
                jSONObject2 = jSONObject3;
            }
            jSONObject0.put("visible_bounds", jSONObject2);
        }
        catch(Exception unused_ex) {
            zzawf.zzfa("Unable to get native ad view bounding box");
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcrg)).booleanValue()) {
            ViewParent viewParent0 = view0.getParent();
            if(viewParent0 == null) {
            label_38:
                s = "";
            }
            else {
                try {
                    s = (String)viewParent0.getClass().getMethod("getTemplateTypeName").invoke(viewParent0);
                    goto label_39;
                }
                catch(NoSuchMethodException securityException0) {
                }
                catch(SecurityException illegalAccessException0) {
                    zzawf.zzc("Cannot access method getTemplateTypeName: ", illegalAccessException0);
                    goto label_38;
                }
                catch(IllegalAccessException invocationTargetException0) {
                    zzawf.zzc("Cannot access method getTemplateTypeName: ", invocationTargetException0);
                    goto label_38;
                }
                catch(InvocationTargetException unused_ex) {
                    goto label_38;
                }
                zzawf.zzc("Cannot access method getTemplateTypeName: ", securityException0);
                goto label_38;
            }
            try {
            label_39:
                switch(s) {
                    case "medium_template": {
                        jSONObject0.put("native_template_type", 2);
                        return jSONObject0;
                    }
                    case "small_template": {
                        jSONObject0.put("native_template_type", 1);
                        return jSONObject0;
                    }
                    default: {
                        jSONObject0.put("native_template_type", 0);
                        return jSONObject0;
                    }
                }
            }
            catch(JSONException jSONException0) {
                zzawf.zzc("Could not log native template signal to JSON", jSONException0);
            }
        }
        return jSONObject0;
    }

    private static JSONObject zzt(@Nullable View view0) {
        JSONObject jSONObject0 = new JSONObject();
        if(view0 == null) {
            return jSONObject0;
        }
        try {
            jSONObject0.put("contained_in_scroll_view", zzawo.zzp(view0) != -1);
        }
        catch(Exception unused_ex) {
        }
        return jSONObject0;
    }

    private final JSONObject zzu(@Nullable View view0) {
        JSONObject jSONObject0 = new JSONObject();
        if(view0 == null) {
            return jSONObject0;
        }
        try {
            jSONObject0.put("can_show_on_lock_screen", zzawo.zzo(view0));
            jSONObject0.put("is_keyguard_locked", zzawo.zzaz(this.zzur));
        }
        catch(JSONException unused_ex) {
            zzawf.zzfa("Unable to get lock screen information");
        }
        return jSONObject0;
    }

    private final String zzv(View view0) {
        try {
            JSONObject jSONObject0 = this.zzfnc.optJSONObject("tracking_urls_and_actions");
            if(jSONObject0 == null) {
                jSONObject0 = new JSONObject();
            }
            String s = jSONObject0.optString("click_string");
            return this.zzehb.zzcb().zza(this.zzur, s, view0);
        }
        catch(Exception exception0) {
            zzawf.zzc("Exception obtaining click signals", exception0);
            return null;
        }
    }

    private final String zzw(View view0) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcmo)).booleanValue()) {
            return null;
        }
        try {
            return this.zzehb.zzcb().zza(this.zzur, view0, null);
        }
        catch(Exception unused_ex) {
            zzawf.zzey("Exception getting data.");
            return null;
        }
    }

    private static int[] zzx(@Nullable View view0) {
        int[] arr_v = new int[2];
        if(view0 != null) {
            view0.getLocationOnScreen(arr_v);
        }
        return arr_v;
    }
}

