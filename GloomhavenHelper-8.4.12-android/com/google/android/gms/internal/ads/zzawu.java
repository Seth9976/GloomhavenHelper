package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import com.google.android.gms.ads.internal.zzq;
import java.io.InputStream;
import java.util.Map;

@TargetApi(16)
public class zzawu {
    private zzawu() {
    }

    zzawu(zzaww zzaww0) {
    }

    public String getDefaultUserAgent(Context context0) {
        return "";
    }

    public boolean isAttachedToWindow(View view0) {
        return view0.getWindowToken() != null || view0.getWindowVisibility() != 8;
    }

    public static boolean zza(zzbdv zzbdv0) {
        if(zzbdv0 == null) {
            return false;
        }
        zzbdv0.onPause();
        return true;
    }

    public int zza(ContentResolver contentResolver0) {
        return Settings.System.getInt(contentResolver0, "wifi_on", 0);
    }

    public Drawable zza(Context context0, Bitmap bitmap0, boolean z, float f) {
        return new BitmapDrawable(context0.getResources(), bitmap0);
    }

    public WebResourceResponse zza(String s, String s1, int v, String s2, Map map0, InputStream inputStream0) {
        return new WebResourceResponse(s, s1, inputStream0);
    }

    public zzbdy zza(zzbdv zzbdv0, zzsn zzsn0, boolean z) {
        return new zzbex(zzbdv0, zzsn0, z);
    }

    public zztf zza(Context context0, TelephonyManager telephonyManager0) {
        return zztf.zzbwj;
    }

    public boolean zza(Activity activity0, Configuration configuration0) {
        return false;
    }

    public boolean zza(Context context0, WebSettings webSettings0) {
        zzayp.zza(context0, new zzawt(context0, webSettings0));
        webSettings0.setAllowFileAccessFromFileURLs(false);
        webSettings0.setAllowUniversalAccessFromFileURLs(false);
        return true;
    }

    public static boolean zzb(zzbdv zzbdv0) {
        if(zzbdv0 == null) {
            return false;
        }
        zzbdv0.onResume();
        return true;
    }

    public int zzb(ContentResolver contentResolver0) {
        return Settings.System.getInt(contentResolver0, "airplane_mode_on", 0);
    }

    public void zzbf(Context context0) {
    }

    public CookieManager zzbg(Context context0) {
        if(zzawu.zzwv()) {
            return null;
        }
        try {
            CookieSyncManager.createInstance(context0);
            return CookieManager.getInstance();
        }
        catch(Throwable throwable0) {
            zzawf.zzc("Failed to obtain CookieManager.", throwable0);
            zzq.zzkz().zza(throwable0, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public static zzawu zzcr(int v) {
        if(v >= 28) {
            return new zzaxb();
        }
        if(v >= 26) {
            return new zzaxc();
        }
        if(v >= 24) {
            return new zzawz();
        }
        if(v >= 21) {
            return new zzaxa();
        }
        if(v >= 19) {
            return new zzawx();
        }
        if(v >= 18) {
            return new zzawy();
        }
        return v >= 17 ? new zzawv() : new zzawu();
    }

    public void zzg(Activity activity0) {
    }

    public int zzwt() {
        return 5;
    }

    public ViewGroup.LayoutParams zzwu() {
        return new ViewGroup.LayoutParams(-2, -2);
    }

    public static boolean zzwv() {
        switch(Process.myUid()) {
            case 0: 
            case 1000: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public int zzww() {
        return 1;
    }

    public long zzwx() {
        return -1L;
    }
}

