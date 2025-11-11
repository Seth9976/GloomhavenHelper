package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;

final class zzaxo implements Runnable {
    final Context val$context;
    private final String zzduu;
    private final boolean zzduv;
    private final boolean zzduw;

    zzaxo(zzaxl zzaxl0, Context context0, String s, boolean z, boolean z1) {
        this.val$context = context0;
        this.zzduu = s;
        this.zzduv = z;
        this.zzduw = z1;
        super();
    }

    @Override
    public final void run() {
        AlertDialog.Builder alertDialog$Builder0 = new AlertDialog.Builder(this.val$context);
        alertDialog$Builder0.setMessage(this.zzduu);
        if(this.zzduv) {
            alertDialog$Builder0.setTitle("Error");
        }
        else {
            alertDialog$Builder0.setTitle("Info");
        }
        if(this.zzduw) {
            alertDialog$Builder0.setNeutralButton("Dismiss", null);
        }
        else {
            alertDialog$Builder0.setPositiveButton("Learn More", new zzaxn(this));
            alertDialog$Builder0.setNegativeButton("Dismiss", null);
        }
        alertDialog$Builder0.create().show();
    }
}

