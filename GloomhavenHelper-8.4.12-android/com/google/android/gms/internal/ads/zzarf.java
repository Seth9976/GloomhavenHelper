package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;

public final class zzarf {
    private float zzboq;
    private int zzdhj;
    private int zzdhk;
    private int zzdnm;
    private boolean zzdnn;
    private boolean zzdno;
    private int zzdnp;
    private int zzdnq;
    private int zzdnr;
    private String zzdns;
    private int zzdnt;
    private int zzdnu;
    private int zzdnv;
    private boolean zzdnw;
    private int zzdnx;
    private double zzdny;
    private boolean zzdnz;
    private String zzdoa;
    private String zzdob;
    private boolean zzdoc;
    private boolean zzdod;
    private String zzdoe;
    private boolean zzdof;
    private final boolean zzdog;
    private boolean zzdoh;
    private String zzdoi;
    private String zzdoj;
    private String zzdok;
    private boolean zzdol;

    public zzarf(Context context0) {
        PackageManager packageManager0 = context0.getPackageManager();
        this.zzv(context0);
        this.zzw(context0);
        this.zzx(context0);
        Locale locale0 = Locale.getDefault();
        boolean z = true;
        this.zzdoc = zzarf.zza(packageManager0, "geo:0,0?q=donuts") != null;
        if(zzarf.zza(packageManager0, "http://www.google.com") == null) {
            z = false;
        }
        this.zzdod = z;
        this.zzdoe = locale0.getCountry();
        this.zzdof = zzayx.zzxi();
        this.zzdog = DeviceProperties.isLatchsky(context0);
        this.zzdoh = DeviceProperties.isSidewinder(context0);
        this.zzdoi = locale0.getLanguage();
        this.zzdoj = zzarf.zza(context0, packageManager0);
        this.zzdok = zzarf.zzy(context0);
        Resources resources0 = context0.getResources();
        if(resources0 == null) {
            return;
        }
        DisplayMetrics displayMetrics0 = resources0.getDisplayMetrics();
        if(displayMetrics0 == null) {
            return;
        }
        this.zzboq = displayMetrics0.density;
        this.zzdhj = displayMetrics0.widthPixels;
        this.zzdhk = displayMetrics0.heightPixels;
    }

    public zzarf(Context context0, zzarg zzarg0) {
        this.zzv(context0);
        this.zzw(context0);
        this.zzx(context0);
        this.zzdoa = "google/sunfish/sunfish:13/TQ2A.230405.003/9719927:user/release-keys";
        this.zzdob = Build.DEVICE;
        this.zzdol = zzaau.zzl(context0);
        this.zzdoc = zzarg0.zzdoc;
        this.zzdod = zzarg0.zzdod;
        this.zzdoe = zzarg0.zzdoe;
        this.zzdof = zzarg0.zzdof;
        this.zzdog = zzarg0.zzdog;
        this.zzdoh = zzarg0.zzdoh;
        this.zzdoi = zzarg0.zzdoi;
        this.zzdoj = zzarg0.zzdoj;
        this.zzdok = zzarg0.zzdok;
        this.zzboq = zzarg0.zzboq;
        this.zzdhj = zzarg0.zzdhj;
        this.zzdhk = zzarg0.zzdhk;
    }

    private static ResolveInfo zza(PackageManager packageManager0, String s) {
        try {
            return packageManager0.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)), 0x10000);
        }
        catch(Throwable throwable0) {
            zzq.zzkz().zza(throwable0, "DeviceInfo.getResolveInfo");
            return null;
        }
    }

    private static String zza(Context context0, PackageManager packageManager0) {
        ResolveInfo resolveInfo0 = zzarf.zza(packageManager0, "market://details?id=com.google.android.gms.ads");
        if(resolveInfo0 == null) {
            return null;
        }
        ActivityInfo activityInfo0 = resolveInfo0.activityInfo;
        if(activityInfo0 == null) {
            return null;
        }
        try {
            PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo(activityInfo0.packageName, 0);
            return packageInfo0 == null ? null : packageInfo0.versionCode + "." + activityInfo0.packageName;
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    public final zzarg zzul() {
        return new zzarg(this.zzdnm, this.zzdoc, this.zzdod, this.zzdns, this.zzdoe, this.zzdof, this.zzdog, this.zzdoh, this.zzdnn, this.zzdno, this.zzdoi, this.zzdoj, this.zzdok, this.zzdnp, this.zzdnt, this.zzdnu, this.zzdnv, this.zzdnq, this.zzdnr, this.zzboq, this.zzdhj, this.zzdhk, this.zzdny, this.zzdnz, this.zzdnw, this.zzdnx, this.zzdoa, this.zzdol, this.zzdob);
    }

    private final void zzv(Context context0) {
        AudioManager audioManager0 = (AudioManager)context0.getSystemService("audio");
        if(audioManager0 != null) {
            try {
                this.zzdnm = audioManager0.getMode();
                this.zzdnn = audioManager0.isMusicActive();
                this.zzdno = audioManager0.isSpeakerphoneOn();
                this.zzdnp = audioManager0.getStreamVolume(3);
                this.zzdnq = audioManager0.getRingerMode();
                this.zzdnr = audioManager0.getStreamVolume(2);
                return;
            }
            catch(Throwable throwable0) {
                zzq.zzkz().zza(throwable0, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zzdnm = -2;
        this.zzdnn = false;
        this.zzdno = false;
        this.zzdnp = 0;
        this.zzdnq = 2;
        this.zzdnr = 0;
    }

    @TargetApi(16)
    private final void zzw(Context context0) {
        TelephonyManager telephonyManager0 = (TelephonyManager)context0.getSystemService("phone");
        ConnectivityManager connectivityManager0 = (ConnectivityManager)context0.getSystemService("connectivity");
        this.zzdns = telephonyManager0.getNetworkOperator();
        this.zzdnu = telephonyManager0.getNetworkType();
        this.zzdnv = telephonyManager0.getPhoneType();
        this.zzdnt = -2;
        this.zzdnw = false;
        this.zzdnx = -1;
        if(zzawo.zzq(context0, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo networkInfo0 = connectivityManager0.getActiveNetworkInfo();
            if(networkInfo0 == null) {
                this.zzdnt = -1;
            }
            else {
                this.zzdnt = networkInfo0.getType();
                this.zzdnx = networkInfo0.getDetailedState().ordinal();
            }
            this.zzdnw = connectivityManager0.isActiveNetworkMetered();
        }
    }

    private final void zzx(Context context0) {
        Intent intent0 = context0.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if(intent0 != null) {
            int v = intent0.getIntExtra("status", -1);
            this.zzdny = (double)(((float)intent0.getIntExtra("level", -1)) / ((float)intent0.getIntExtra("scale", -1)));
            if(v == 2 || v == 5) {
                z = true;
            }
            this.zzdnz = z;
            return;
        }
        this.zzdny = -1.0;
        this.zzdnz = false;
    }

    private static String zzy(Context context0) {
        try {
            PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo("com.android.vending", 0x80);
            return packageInfo0 == null ? null : packageInfo0.versionCode + "." + packageInfo0.packageName;
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

