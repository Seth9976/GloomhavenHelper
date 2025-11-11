package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzavr {
    private final Object lock;
    private zzazo zzblu;
    private final zzawc zzdrf;
    private zzpq zzdrn;
    private final zzawk zzdro;
    @Nullable
    private zzaaa zzdrp;
    @Nullable
    private Boolean zzdrq;
    private final AtomicInteger zzdrr;
    private final zzavw zzdrs;
    private final Object zzdrt;
    @GuardedBy("grantedPermissionLock")
    private zzdof zzdru;
    private Context zzur;
    private boolean zzyb;

    public zzavr() {
        this.lock = new Object();
        this.zzdro = new zzawk();
        this.zzdrf = new zzawc(zzvh.zzpe(), this.zzdro);
        this.zzyb = false;
        this.zzdrp = null;
        this.zzdrq = null;
        this.zzdrr = new AtomicInteger(0);
        this.zzdrs = new zzavw(null);
        this.zzdrt = new Object();
    }

    @Nullable
    public final Context getApplicationContext() {
        return this.zzur;
    }

    @Nullable
    public final Resources getResources() {
        if(this.zzblu.zzdxh) {
            return this.zzur.getResources();
        }
        try {
            zzazk.zzbs(this.zzur).getResources();
        }
        catch(zzazm zzazm0) {
            zzawf.zzd("Cannot load resource from dynamite apk or local jar", zzazm0);
        }
        return null;
    }

    public final void zza(Boolean boolean0) {
        synchronized(this.lock) {
            this.zzdrq = boolean0;
        }
    }

    public final void zza(Throwable throwable0, String s) {
        zzaqa.zzc(this.zzur, this.zzblu).zza(throwable0, s);
    }

    @TargetApi(16)
    private static ArrayList zzan(Context context0) {
        PackageInfo packageInfo0;
        ArrayList arrayList0 = new ArrayList();
        try {
            packageInfo0 = Wrappers.packageManager(context0).getPackageInfo(context0.getApplicationInfo().packageName, 0x1000);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return arrayList0;
        }
        if(packageInfo0.requestedPermissions != null && packageInfo0.requestedPermissionsFlags != null) {
            for(int v = 0; v < packageInfo0.requestedPermissions.length; ++v) {
                if((packageInfo0.requestedPermissionsFlags[v] & 2) != 0) {
                    arrayList0.add(packageInfo0.requestedPermissions[v]);
                }
            }
            return arrayList0;
        }
        return arrayList0;
    }

    public final void zzb(Throwable throwable0, String s) {
        zzaqa.zzc(this.zzur, this.zzblu).zza(throwable0, s, ((Double)zzabo.zzcvn.get()).floatValue());
    }

    @TargetApi(23)
    public final void zzd(Context context0, zzazo zzazo0) {
        synchronized(this.lock) {
            if(!this.zzyb) {
                this.zzur = context0.getApplicationContext();
                this.zzblu = zzazo0;
                zzq.zzky().zza(this.zzdrf);
                zzaaa zzaaa0 = null;
                this.zzdro.zza(this.zzur, null, true);
                zzaqa.zzc(this.zzur, this.zzblu);
                this.zzdrn = new zzpq(context0.getApplicationContext(), this.zzblu);
                if(((Boolean)zzabf.zzcui.get()).booleanValue()) {
                    zzaaa0 = new zzaaa();
                }
                else {
                    zzawf.zzee("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                }
                this.zzdrp = zzaaa0;
                if(this.zzdrp != null) {
                    zzazu.zza(new zzavt(this).zzvw(), "AppState.registerCsiReporter");
                }
                this.zzyb = true;
                this.zzvl();
            }
        }
        zzq.zzkv().zzr(context0, zzazo0.zzbmj);
    }

    @Nullable
    public final zzaaa zzve() {
        synchronized(this.lock) {
        }
        return this.zzdrp;
    }

    public final Boolean zzvf() {
        synchronized(this.lock) {
        }
        return this.zzdrq;
    }

    public final void zzvg() {
        this.zzdrs.zzvg();
    }

    public final void zzvh() {
        this.zzdrr.incrementAndGet();
    }

    public final void zzvi() {
        this.zzdrr.decrementAndGet();
    }

    public final int zzvj() {
        return this.zzdrr.get();
    }

    public final zzawh zzvk() {
        synchronized(this.lock) {
        }
        return this.zzdro;
    }

    public final zzdof zzvl() {
        if(this.zzur != null && !((Boolean)zzvh.zzpd().zzd(zzzx.zzclz)).booleanValue()) {
            Object object0 = this.zzdrt;
            synchronized(object0) {
                if(this.zzdru != null) {
                    return this.zzdru;
                }
                zzavu zzavu0 = () -> zzavr.zzan(zzars.zzac(this.zzur));
                zzdof zzdof0 = zzazq.zzdxk.zzd(zzavu0);
                this.zzdru = zzdof0;
                return zzdof0;
            }
        }
        return zzdnt.zzaj(new ArrayList());
    }

    public final zzawc zzvm() {
        return this.zzdrf;
    }

    // 检测为 Lambda 实现
    final ArrayList zzvn() throws Exception [...]
}

