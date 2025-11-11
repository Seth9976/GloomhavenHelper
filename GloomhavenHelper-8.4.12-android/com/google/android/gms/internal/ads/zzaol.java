package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.ads.impl.R.string;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;

public final class zzaol extends zzaow {
    private final Map zzcsz;
    private String zzdge;
    private long zzdgf;
    private long zzdgg;
    private String zzdgh;
    private String zzdgi;
    private final Context zzur;

    public zzaol(zzbdv zzbdv0, Map map0) {
        super(zzbdv0, "createCalendarEvent");
        this.zzcsz = map0;
        this.zzur = zzbdv0.zzys();
        this.zzdge = this.zzdr("description");
        this.zzdgh = this.zzdr("summary");
        this.zzdgf = this.zzds("start_ticks");
        this.zzdgg = this.zzds("end_ticks");
        this.zzdgi = this.zzdr("location");
    }

    @TargetApi(14)
    final Intent createIntent() {
        Intent intent0 = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        intent0.putExtra("title", this.zzdge);
        intent0.putExtra("eventLocation", this.zzdgi);
        intent0.putExtra("description", this.zzdgh);
        long v = this.zzdgf;
        if(v > -1L) {
            intent0.putExtra("beginTime", v);
        }
        long v1 = this.zzdgg;
        if(v1 > -1L) {
            intent0.putExtra("endTime", v1);
        }
        intent0.setFlags(0x10000000);
        return intent0;
    }

    public final void execute() {
        if(this.zzur == null) {
            this.zzdt("Activity context is not available.");
            return;
        }
        if(!zzawo.zzau(this.zzur).zzqh()) {
            this.zzdt("This feature is not available on the device.");
            return;
        }
        AlertDialog.Builder alertDialog$Builder0 = zzawo.zzat(this.zzur);
        Resources resources0 = zzq.zzkz().getResources();
        alertDialog$Builder0.setTitle((resources0 == null ? "Create calendar event" : resources0.getString(string.s5)));
        alertDialog$Builder0.setMessage((resources0 == null ? "Allow Ad to create a calendar event?" : resources0.getString(string.s6)));
        alertDialog$Builder0.setPositiveButton((resources0 == null ? "Accept" : resources0.getString(string.s3)), new zzaok(this));
        alertDialog$Builder0.setNegativeButton((resources0 == null ? "Decline" : resources0.getString(string.s4)), new zzaon(this));
        alertDialog$Builder0.create().show();
    }

    // 去混淆评级： 低(20)
    private final String zzdr(String s) {
        return TextUtils.isEmpty(((CharSequence)this.zzcsz.get(s))) ? "" : ((String)this.zzcsz.get(s));
    }

    private final long zzds(String s) {
        String s1 = (String)this.zzcsz.get(s);
        if(s1 == null) {
            return -1L;
        }
        try {
            return Long.parseLong(s1);
        }
        catch(NumberFormatException unused_ex) {
            return -1L;
        }
    }
}

