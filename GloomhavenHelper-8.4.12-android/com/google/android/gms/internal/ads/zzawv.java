package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings.Global;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

@TargetApi(17)
public class zzawv extends zzawu {
    public zzawv() {
        super(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final String getDefaultUserAgent(Context context0) {
        zzays zzays0 = zzays.zzxh();
        if(TextUtils.isEmpty(zzays0.zzdwe)) {
            zzays0.zzdwe = (String)zzayp.zza(context0, new zzayu(zzays0, GooglePlayServicesUtilLight.getRemoteContext(context0), context0));
        }
        return zzays0.zzdwe;
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final int zza(ContentResolver contentResolver0) {
        return Settings.Global.getInt(contentResolver0, "wifi_on", 0);
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final Drawable zza(Context context0, Bitmap bitmap0, boolean z, float f) {
        if(z && f > 0.0f && f <= 25.0f) {
            try {
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap0, bitmap0.getWidth(), bitmap0.getHeight(), false);
                Bitmap bitmap2 = Bitmap.createBitmap(bitmap1);
                RenderScript renderScript0 = RenderScript.create(context0);
                ScriptIntrinsicBlur scriptIntrinsicBlur0 = ScriptIntrinsicBlur.create(renderScript0, Element.U8_4(renderScript0));
                Allocation allocation0 = Allocation.createFromBitmap(renderScript0, bitmap1);
                Allocation allocation1 = Allocation.createFromBitmap(renderScript0, bitmap2);
                scriptIntrinsicBlur0.setRadius(f);
                scriptIntrinsicBlur0.setInput(allocation0);
                scriptIntrinsicBlur0.forEach(allocation1);
                allocation1.copyTo(bitmap2);
                return new BitmapDrawable(context0.getResources(), bitmap2);
            }
            catch(RuntimeException unused_ex) {
                return new BitmapDrawable(context0.getResources(), bitmap0);
            }
        }
        return new BitmapDrawable(context0.getResources(), bitmap0);
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final boolean zza(Context context0, WebSettings webSettings0) {
        super.zza(context0, webSettings0);
        webSettings0.setMediaPlaybackRequiresUserGesture(false);
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final int zzb(ContentResolver contentResolver0) {
        return Settings.Global.getInt(contentResolver0, "airplane_mode_on", 0);
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final void zzbf(Context context0) {
        zzays zzays0 = zzays.zzxh();
        zzawf.zzee("Updating user agent.");
        String s = WebSettings.getDefaultUserAgent(context0);
        if(!s.equals(zzays0.zzdwe)) {
            if(GooglePlayServicesUtilLight.getRemoteContext(context0) == null) {
                String s1 = WebSettings.getDefaultUserAgent(context0);
                context0.getSharedPreferences("admob_user_agent", 0).edit().putString("user_agent", s1).apply();
            }
            zzays0.zzdwe = s;
        }
        zzawf.zzee("User agent is updated.");
    }
}

