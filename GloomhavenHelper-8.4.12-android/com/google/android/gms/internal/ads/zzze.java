package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;

public final class zzze {
    private final Context zzur;

    public zzze(Context context0) {
        Preconditions.checkNotNull(context0, "Context can not be null");
        this.zzur = context0;
    }

    private final boolean zza(Intent intent0) {
        Preconditions.checkNotNull(intent0, "Intent can not be null");
        return !this.zzur.getPackageManager().queryIntentActivities(intent0, 0).isEmpty();
    }

    public final boolean zzqe() {
        Intent intent0 = new Intent("android.intent.action.DIAL");
        intent0.setData(Uri.parse("tel:"));
        return this.zza(intent0);
    }

    public final boolean zzqf() {
        Intent intent0 = new Intent("android.intent.action.VIEW");
        intent0.setData(Uri.parse("sms:"));
        return this.zza(intent0);
    }

    public final boolean zzqg() {
        zzzh zzzh0 = new zzzh();
        return ((Boolean)zzayp.zza(this.zzur, zzzh0)).booleanValue() && Wrappers.packageManager(this.zzur).checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    @TargetApi(14)
    public final boolean zzqh() {
        return this.zza(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}

