package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.util.LinkedList;

public abstract class zzdj implements zzdg {
    protected static volatile zzei zzuy;
    protected MotionEvent zzvq;
    protected LinkedList zzvr;
    protected long zzvs;
    protected long zzvt;
    protected long zzvu;
    protected long zzvv;
    protected long zzvw;
    protected long zzvx;
    protected long zzvy;
    protected double zzvz;
    private double zzwa;
    private double zzwb;
    protected float zzwc;
    protected float zzwd;
    protected float zzwe;
    protected float zzwf;
    private boolean zzwg;
    protected boolean zzwh;
    protected DisplayMetrics zzwi;

    static {
    }

    protected zzdj(Context context0) {
        this.zzvr = new LinkedList();
        this.zzvs = 0L;
        this.zzvt = 0L;
        this.zzvu = 0L;
        this.zzvv = 0L;
        this.zzvw = 0L;
        this.zzvx = 0L;
        this.zzvy = 0L;
        this.zzwg = false;
        this.zzwh = false;
        try {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmd)).booleanValue()) {
                zzck.zzbm();
            }
            else {
                zzeo.zzb(zzdj.zzuy);
            }
            this.zzwi = context0.getResources().getDisplayMetrics();
        }
        catch(Throwable unused_ex) {
        }
    }

    private final String zza(Context context0, String s, int v, View view0, Activity activity0, byte[] arr_b) {
        int v6;
        int v5;
        String s2;
        int v3;
        int v2;
        String s1;
        zzde zzde0;
        long v1 = System.currentTimeMillis();
        boolean z = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcls)).booleanValue();
        zzb zzbs$zza$zzb0 = null;
        if(z) {
            zzde0 = zzdj.zzuy == null ? null : zzdj.zzuy.zzch();
            s1 = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcmd)).booleanValue() ? "be" : "te";
        }
        else {
            zzde0 = null;
            s1 = null;
        }
        try {
            if(v == zzee.zzxj) {
                zzbs$zza$zzb0 = this.zzc(context0, view0, activity0);
                this.zzwg = true;
                v2 = 1002;
            }
            else if(v == zzee.zzxi) {
                zzbs$zza$zzb0 = this.zzb(context0, view0, activity0);
                v2 = 0x3F0;
            }
            else {
                zzbs$zza$zzb0 = this.zza(context0, null);
                v2 = 1000;
            }
            if(z && zzde0 != null) {
                zzde0.zza(v2, -1, System.currentTimeMillis() - v1, s1);
            }
        }
        catch(Exception exception0) {
            if(z && zzde0 != null) {
                if(v == zzee.zzxj) {
                    v3 = 1003;
                }
                else if(v == zzee.zzxi) {
                    v3 = 1009;
                }
                else {
                    v3 = v == zzee.zzxh ? 1001 : -1;
                }
                zzde0.zza(v3, -1, System.currentTimeMillis() - v1, s1, exception0);
            }
        }
        long v4 = System.currentTimeMillis();
        if(zzbs$zza$zzb0 == null) {
            return "5";
        }
        try {
            if(((zza)(((zzdyz)zzbs$zza$zzb0.zzbcx()))).zzbda() == 0) {
                return "5";
            }
            s2 = zzck.zzj(((zza)(((zzdyz)zzbs$zza$zzb0.zzbcx()))), s);
            if(z && zzde0 != null) {
                if(v == zzee.zzxj) {
                    v5 = 1006;
                }
                else if(v == zzee.zzxi) {
                    v5 = 1010;
                }
                else {
                    v5 = v == zzee.zzxh ? 1004 : -1;
                }
                zzde0.zza(v5, -1, System.currentTimeMillis() - v4, s1);
                return s2;
            }
        }
        catch(Exception exception1) {
            s2 = "7";
            if(z && zzde0 != null) {
                if(v == zzee.zzxj) {
                    v6 = 1007;
                }
                else if(v == zzee.zzxi) {
                    v6 = 0x3F3;
                }
                else {
                    v6 = v == zzee.zzxh ? 1005 : -1;
                }
                zzde0.zza(v6, -1, System.currentTimeMillis() - v4, s1, exception1);
            }
        }
        return s2;
    }

    protected abstract long zza(StackTraceElement[] arg1) throws zzeh;

    protected abstract zzb zza(Context arg1, com.google.android.gms.internal.ads.zzbo.zza arg2);

    @Override  // com.google.android.gms.internal.ads.zzdg
    public String zza(Context context0, View view0, Activity activity0) {
        return this.zza(context0, null, zzee.zzxi, view0, activity0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public final String zza(Context context0, String s, View view0) {
        return this.zza(context0, s, view0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public String zza(Context context0, String s, View view0, Activity activity0) {
        return this.zza(context0, s, zzee.zzxj, view0, activity0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public void zza(int v, int v1, int v2) {
        if(this.zzvq != null) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzclq)).booleanValue()) {
                this.zzca();
            }
            else {
                this.zzvq.recycle();
            }
        }
        DisplayMetrics displayMetrics0 = this.zzwi;
        this.zzvq = displayMetrics0 == null ? null : MotionEvent.obtain(0L, v2, 1, ((float)v) * displayMetrics0.density, this.zzwi.density * ((float)v1), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        this.zzwh = false;
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public void zza(MotionEvent motionEvent0) {
        if(this.zzwg) {
            this.zzca();
            this.zzwg = false;
        }
        switch(motionEvent0.getAction()) {
            case 0: {
                this.zzvz = 0.0;
                this.zzwa = (double)motionEvent0.getRawX();
                this.zzwb = (double)motionEvent0.getRawY();
                break;
            }
            case 1: 
            case 2: {
                double f = (double)motionEvent0.getRawX();
                double f1 = (double)motionEvent0.getRawY();
                double f2 = f - this.zzwa;
                double f3 = f1 - this.zzwb;
                this.zzvz += Math.sqrt(f2 * f2 + f3 * f3);
                this.zzwa = f;
                this.zzwb = f1;
            }
        }
        switch(motionEvent0.getAction()) {
            case 0: {
                this.zzwc = motionEvent0.getX();
                this.zzwd = motionEvent0.getY();
                this.zzwe = motionEvent0.getRawX();
                this.zzwf = motionEvent0.getRawY();
                ++this.zzvs;
                this.zzwh = true;
                return;
            }
            case 1: {
                this.zzvq = MotionEvent.obtain(motionEvent0);
                this.zzvr.add(this.zzvq);
                if(this.zzvr.size() > 6) {
                    ((MotionEvent)this.zzvr.remove()).recycle();
                }
                try {
                    ++this.zzvu;
                    this.zzvw = this.zza(new Throwable().getStackTrace());
                }
                catch(zzeh unused_ex) {
                }
                this.zzwh = true;
                return;
            }
            case 2: {
                this.zzvt += (long)(motionEvent0.getHistorySize() + 1);
                try {
                    zzeq zzeq0 = this.zzb(motionEvent0);
                    if(zzeq0 != null && zzeq0.zzyq != null && zzeq0.zzyt != null) {
                        this.zzvx += ((long)zzeq0.zzyq) + ((long)zzeq0.zzyt);
                    }
                    if(this.zzwi != null && zzeq0 != null && zzeq0.zzyr != null && zzeq0.zzyu != null) {
                        this.zzvy += ((long)zzeq0.zzyr) + ((long)zzeq0.zzyu);
                        this.zzwh = true;
                        return;
                    }
                }
                catch(zzeh unused_ex) {
                }
                this.zzwh = true;
                return;
            }
            case 3: {
                ++this.zzvv;
                this.zzwh = true;
                return;
            }
            default: {
                this.zzwh = true;
            }
        }
    }

    protected abstract zzb zzb(Context arg1, View arg2, Activity arg3);

    protected abstract zzeq zzb(MotionEvent arg1) throws zzeh;

    @Override  // com.google.android.gms.internal.ads.zzdg
    public String zzb(Context context0) {
        if(zzep.isMainThread() && ((Boolean)zzvh.zzpd().zzd(zzzx.zzcmf)).booleanValue()) {
            throw new IllegalStateException("The caller must not be called from the UI thread.");
        }
        return this.zza(context0, null, zzee.zzxh, null, null, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdg
    public void zzb(View view0) {
    }

    protected abstract zzb zzc(Context arg1, View arg2, Activity arg3);

    private final void zzca() {
        this.zzvw = 0L;
        this.zzvs = 0L;
        this.zzvt = 0L;
        this.zzvu = 0L;
        this.zzvv = 0L;
        this.zzvx = 0L;
        this.zzvy = 0L;
        if(this.zzvr.size() > 0) {
            for(Object object0: this.zzvr) {
                ((MotionEvent)object0).recycle();
            }
            this.zzvr.clear();
        }
        else {
            MotionEvent motionEvent0 = this.zzvq;
            if(motionEvent0 != null) {
                motionEvent0.recycle();
            }
        }
        this.zzvq = null;
    }
}

