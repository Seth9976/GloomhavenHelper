package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import androidx.annotation.GuardedBy;
import com.google.android.gms.ads.internal.zzq;
import java.lang.ref.WeakReference;
import jeb.synthetic.FIN;

public final class zzars extends ContextWrapper {
    @GuardedBy("this")
    private Context zzdov;
    @GuardedBy("this")
    private WeakReference zzdow;

    private zzars(Context context0) {
        super(context0);
        this.zzdow = new WeakReference(null);
    }

    @Override  // android.content.ContextWrapper
    public final Context getApplicationContext() {
        return this;
    }

    @Override  // android.content.ContextWrapper
    public final ApplicationInfo getApplicationInfo() {
        synchronized(this) {
            return this.zzdov != null ? this.zzdov.getApplicationInfo() : super.getApplicationInfo();
        }
    }

    @Override  // android.content.ContextWrapper
    public final String getPackageName() {
        synchronized(this) {
            return this.zzdov != null ? "com.esotericsoftware.gloomhavenhelper" : super.getPackageName();
        }
    }

    @Override  // android.content.ContextWrapper
    public final String getPackageResourcePath() {
        synchronized(this) {
            return this.zzdov != null ? this.zzdov.getPackageResourcePath() : super.getPackageResourcePath();
        }
    }

    public final void setAppPackageName(String s) throws PackageManager.NameNotFoundException {
        synchronized(this) {
            this.zzdov = super.createPackageContext(s, 0);
        }
    }

    @Override  // android.content.ContextWrapper
    public final void startActivity(Intent intent0) {
        synchronized(this) {
            this.zze(this.zzd(intent0));
        }
    }

    public static zzars zzab(Context context0) {
        return new zzars(zzars.zzm(context0));
    }

    // 去混淆评级： 低(20)
    public static Context zzac(Context context0) {
        return context0 instanceof zzars ? ((zzars)context0).getBaseContext() : zzars.zzm(context0);
    }

    private final Intent zzd(Intent intent0) {
        synchronized(this) {
            if(this.zzdov != null && intent0.getComponent() != null && intent0.getComponent().getPackageName().equals("com.esotericsoftware.gloomhavenhelper")) {
                Intent intent1 = (Intent)intent0.clone();
                intent1.setClassName(super.getPackageName(), intent0.getComponent().getClassName());
                return intent1;
            }
            return intent0;
        }
    }

    private final void zze(Intent intent0) {
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        Activity activity0 = (Activity)this.zzdow.get();
        if(activity0 == null) {
            intent0.addFlags(0x10000000);
            super.startActivity(intent0);
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(this);
            FIN.finallyCodeEnd$NT(v);
            return;
        }
        try {
            Intent intent1 = (Intent)intent0.clone();
            intent1.setFlags(intent0.getFlags() & 0xEFFFFFFF);
            activity0.startActivity(intent1);
            FIN.finallyExec$NT(v);
        }
        catch(Throwable throwable0) {
            zzq.zzkz().zza(throwable0, "");
            intent0.addFlags(0x10000000);
            super.startActivity(intent0);
            FIN.finallyExec$NT(v);
        }
    }

    private static Context zzm(Context context0) {
        Context context1 = context0.getApplicationContext();
        return context1 == null ? context0 : context1;
    }
}

