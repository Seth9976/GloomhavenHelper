package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager.BadTokenException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzaxg {
    private Handler handler;
    private int state;
    private String zzbmj;
    private String zzbri;
    private String zzdiz;
    @Nullable
    private String zzdmj;
    private int zzduh;
    private PointF zzdui;
    private PointF zzduj;
    private Runnable zzduk;
    private final Context zzur;

    public zzaxg(Context context0) {
        this.state = 0;
        this.zzduk = () -> {
            this.state = 4;
            this.showDialog();
        };
        this.zzur = context0;
        this.zzduh = ViewConfiguration.get(context0).getScaledTouchSlop();
        zzq.zzlj().zzxg();
        this.handler = zzq.zzlj().getHandler();
    }

    public zzaxg(Context context0, String s) {
        this(context0);
        this.zzdiz = s;
    }

    public final void setAdUnitId(String s) {
        this.zzbri = s;
    }

    public final void showDialog() {
        try {
            if(!(this.zzur instanceof Activity)) {
                zzawf.zzez("Can not create dialog without Activity Context");
                return;
            }
            String s = zzq.zzlf().zzxd() ? "Troubleshooting (Enabled)" : "Troubleshooting";
            ArrayList arrayList0 = new ArrayList();
            int v = zzaxg.zza(arrayList0, "Ad Information", true);
            int v1 = zzaxg.zza(arrayList0, "Creative Preview", true);
            int v2 = zzaxg.zza(arrayList0, s, true);
            AlertDialog.Builder alertDialog$Builder0 = new AlertDialog.Builder(this.zzur, zzq.zzkx().zzww());
            alertDialog$Builder0.setTitle("Select a Debug Mode").setItems(((CharSequence[])arrayList0.toArray(new String[0])), (DialogInterface dialogInterface0, int v3) -> {
                String s1;
                if(v3 == v) {
                    if(!(this.zzur instanceof Activity)) {
                        zzawf.zzez("Can not create dialog without Activity Context");
                        return;
                    }
                    String s = this.zzdiz;
                    if(TextUtils.isEmpty(s)) {
                        s1 = "No debug information";
                    }
                    else {
                        Uri uri0 = new Uri.Builder().encodedQuery(s.replaceAll("\\+", "%20")).build();
                        StringBuilder stringBuilder0 = new StringBuilder();
                        Map map0 = zzawo.zzi(uri0);
                        for(Object object0: map0.keySet()) {
                            stringBuilder0.append(((String)object0));
                            stringBuilder0.append(" = ");
                            stringBuilder0.append(((String)map0.get(((String)object0))));
                            stringBuilder0.append("\n\n");
                        }
                        s1 = stringBuilder0.toString().trim();
                        if(TextUtils.isEmpty(s1)) {
                            s1 = "No debug information";
                        }
                    }
                    AlertDialog.Builder alertDialog$Builder0 = new AlertDialog.Builder(this.zzur);
                    alertDialog$Builder0.setMessage(s1);
                    alertDialog$Builder0.setTitle("Ad Information");
                    alertDialog$Builder0.setPositiveButton("Share", (DialogInterface dialogInterface0, int v) -> {
                        Intent intent0 = Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", s1), "Share via");
                        zzawo.zza(this.zzur, intent0);
                    });
                    alertDialog$Builder0.setNegativeButton("Close", zzaxk.zzduo);
                    alertDialog$Builder0.create().show();
                    return;
                }
                if(v3 == v1) {
                    zzawf.zzeb("Debug mode [Creative Preview] selected.");
                    zzaxj zzaxj0 = () -> zzq.zzlf().zze(this.zzur, this.zzbri, this.zzbmj);
                    zzazq.zzdxk.execute(zzaxj0);
                    return;
                }
                if(v3 == v2) {
                    zzawf.zzeb("Debug mode [Troubleshooting] selected.");
                    zzaxm zzaxm0 = () -> zzq.zzlf().zza(this.zzur, this.zzbri, this.zzbmj, this.zzdmj);
                    zzazq.zzdxk.execute(zzaxm0);
                }
            });
            alertDialog$Builder0.create().show();
        }
        catch(WindowManager.BadTokenException windowManager$BadTokenException0) {
            zzawf.zza("", windowManager$BadTokenException0);
        }
    }

    @Override
    public final String toString() {
        return "{Dialog: " + this.zzdiz + ",DebugSignal: " + this.zzdmj + ",AFMA Version: " + this.zzbmj + ",Ad Unit ID: " + this.zzbri + "}";
    }

    private static int zza(List list0, String s, boolean z) {
        list0.add(s);
        return list0.size() - 1;
    }

    private final boolean zza(float f, float f1, float f2, float f3) {
        return Math.abs(this.zzdui.x - f) < ((float)this.zzduh) && Math.abs(this.zzdui.y - f1) < ((float)this.zzduh) && Math.abs(this.zzduj.x - f2) < ((float)this.zzduh) && Math.abs(this.zzduj.y - f3) < ((float)this.zzduh);
    }

    // 检测为 Lambda 实现
    final void zza(int v, int v1, int v2, DialogInterface dialogInterface0, int v3) [...]

    // 检测为 Lambda 实现
    final void zza(String s, DialogInterface dialogInterface0, int v) [...]

    public final void zzd(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        int v1 = motionEvent0.getHistorySize();
        int v2 = motionEvent0.getPointerCount();
        if(v == 0) {
            this.state = 0;
            this.zzdui = new PointF(motionEvent0.getX(0), motionEvent0.getY(0));
            return;
        }
        int v3 = 1;
        int v4 = this.state;
        if(v4 == -1) {
            return;
        }
        if(v4 == 0 && v == 5) {
            this.state = 5;
            this.zzduj = new PointF(motionEvent0.getX(1), motionEvent0.getY(1));
            this.handler.postDelayed(this.zzduk, ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcoi)))));
            return;
        }
        if(this.state == 5) {
            if(v2 == 2) {
                if(v == 2) {
                    int v6 = 0;
                    for(int v5 = 0; v5 < v1; ++v5) {
                        if(!this.zza(motionEvent0.getHistoricalX(0, v5), motionEvent0.getHistoricalY(0, v5), motionEvent0.getHistoricalX(1, v5), motionEvent0.getHistoricalY(1, v5))) {
                            v6 = 1;
                        }
                    }
                    if(this.zza(motionEvent0.getX(), motionEvent0.getY(), motionEvent0.getX(1), motionEvent0.getY(1))) {
                        v3 = v6;
                    }
                }
                else {
                    v3 = 0;
                }
            }
            if(v3 != 0) {
                this.state = -1;
                this.handler.removeCallbacks(this.zzduk);
            }
        }
    }

    public final void zzep(String s) {
        this.zzdiz = s;
    }

    public final void zzeq(String s) {
        this.zzdmj = s;
    }

    // 检测为 Lambda 实现
    final void zzwz() [...]

    public final void zzx(String s) {
        this.zzbmj = s;
    }

    // 检测为 Lambda 实现
    final void zzxa() [...]

    // 检测为 Lambda 实现
    final void zzxb() [...]
}

