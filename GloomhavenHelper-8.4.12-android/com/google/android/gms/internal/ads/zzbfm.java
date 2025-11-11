package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;

public final class zzbfm extends MutableContextWrapper {
    private Activity zzdwu;
    private Context zzeiz;
    private Context zzyz;

    public zzbfm(Context context0) {
        super(context0);
        this.setBaseContext(context0);
    }

    @Override  // android.content.ContextWrapper
    public final Object getSystemService(String s) {
        return this.zzeiz.getSystemService(s);
    }

    @Override  // android.content.MutableContextWrapper
    public final void setBaseContext(Context context0) {
        this.zzyz = context0.getApplicationContext();
        this.zzdwu = context0 instanceof Activity ? ((Activity)context0) : null;
        this.zzeiz = context0;
        super.setBaseContext(this.zzyz);
    }

    @Override  // android.content.ContextWrapper
    public final void startActivity(Intent intent0) {
        Activity activity0 = this.zzdwu;
        if(activity0 != null) {
            activity0.startActivity(intent0);
            return;
        }
        intent0.setFlags(0x10000000);
        this.zzyz.startActivity(intent0);
    }

    public final Context zzaaa() {
        return this.zzeiz;
    }

    public final Activity zzys() {
        return this.zzdwu;
    }
}

