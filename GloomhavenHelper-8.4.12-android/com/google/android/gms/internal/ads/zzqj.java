package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(14)
@ParametersAreNonnullByDefault
public final class zzqj extends Thread {
    private final Object lock;
    private boolean started;
    private final int zzbos;
    private final int zzbou;
    private final boolean zzbov;
    private boolean zzbps;
    private boolean zzbpt;
    private final zzqg zzbpu;
    private final int zzbpv;
    private final int zzbpw;
    private final int zzbpx;
    private final int zzbpy;
    private final int zzbpz;
    private final int zzbqa;
    private final String zzbqb;
    private final boolean zzbqc;
    private final boolean zzbqd;

    public zzqj() {
        this(new zzqg());
    }

    @VisibleForTesting
    private zzqj(zzqg zzqg0) {
        this.started = false;
        this.zzbps = false;
        this.zzbpt = false;
        this.zzbpu = zzqg0;
        this.lock = new Object();
        this.zzbos = ((Long)zzabc.zzctw.get()).intValue();
        this.zzbpw = ((Long)zzabc.zzctt.get()).intValue();
        this.zzbou = ((Long)zzabc.zzctx.get()).intValue();
        this.zzbpx = ((Long)zzabc.zzctv.get()).intValue();
        this.zzbpy = (int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcik)));
        this.zzbpz = (int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcil)));
        this.zzbqa = (int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcim)));
        this.zzbpv = ((Long)zzabc.zzcty.get()).intValue();
        this.zzbqb = (String)zzvh.zzpd().zzd(zzzx.zzcio);
        this.zzbqc = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcip)).booleanValue();
        this.zzbov = ((Boolean)zzvh.zzpd().zzd(zzzx.zzciq)).booleanValue();
        this.zzbqd = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcir)).booleanValue();
        this.setName("ContentFetchTask");
    }

    @Override
    public final void run() {
        Activity activity0;
        View view0;
        while(true) {
            try {
                if(zzqj.zzmh()) {
                    view0 = null;
                    activity0 = zzq.zzky().getActivity();
                    if(activity0 == null) {
                        zzawf.zzeb("ContentFetchThread: no activity. Sleeping.");
                        this.zzmj();
                        goto label_19;
                    }
                    goto label_10;
                }
                else {
                    zzawf.zzeb("ContentFetchTask: sleeping");
                    this.zzmj();
                }
                goto label_19;
            }
            catch(InterruptedException interruptedException0) {
                zzawf.zzc("Error in ContentFetchTask", interruptedException0);
                goto label_27;
            }
            catch(Exception exception0) {
                goto label_25;
            }
        label_10:
            if(activity0 != null) {
                try {
                    if(activity0.getWindow() != null && activity0.getWindow().getDecorView() != null) {
                        view0 = activity0.getWindow().getDecorView().findViewById(0x1020002);
                    }
                    goto label_17;
                }
                catch(Exception exception1) {
                    try {
                        zzq.zzkz().zza(exception1, "ContentFetchTask.extractContent");
                        zzawf.zzeb("Failed getting root view of activity. Content not extracted.");
                    label_17:
                        if(view0 != null) {
                            view0.post(new zzqm(this, view0));
                        }
                    label_19:
                        Thread.sleep(this.zzbpv * 1000);
                        goto label_27;
                    }
                    catch(InterruptedException interruptedException0) {
                        zzawf.zzc("Error in ContentFetchTask", interruptedException0);
                        goto label_27;
                    }
                    catch(Exception exception0) {
                        goto label_25;
                    }
                }
            }
            goto label_19;
        label_25:
            zzawf.zzc("Error in ContentFetchTask", exception0);
            zzq.zzkz().zza(exception0, "ContentFetchTask.run");
        label_27:
            synchronized(this.lock) {
                while(this.zzbps) {
                    try {
                        zzawf.zzeb("ContentFetchTask: waiting");
                        this.lock.wait();
                    }
                    catch(InterruptedException unused_ex) {
                    }
                }
            }
        }
    }

    public final void wakeup() {
        synchronized(this.lock) {
            this.zzbps = false;
            this.lock.notifyAll();
            zzawf.zzeb("ContentFetchThread: wakeup");
        }
    }

    @VisibleForTesting
    private final zzqn zza(@Nullable View view0, zzqd zzqd0) {
        if(view0 == null) {
            return new zzqn(this, 0, 0);
        }
        boolean z = view0.getGlobalVisibleRect(new Rect());
        if(view0 instanceof TextView && !(view0 instanceof EditText)) {
            CharSequence charSequence0 = ((TextView)view0).getText();
            if(!TextUtils.isEmpty(charSequence0)) {
                zzqd0.zzb(charSequence0.toString(), z, view0.getX(), view0.getY(), ((float)view0.getWidth()), ((float)view0.getHeight()));
                return new zzqn(this, 1, 0);
            }
            return new zzqn(this, 0, 0);
        }
        if(view0 instanceof WebView && !(view0 instanceof zzbdv)) {
            zzqd0.zzmc();
            ((WebView)view0).post(new zzql(this, zzqd0, ((WebView)view0), z));
            return new zzqn(this, 0, 1);
        }
        if(view0 instanceof ViewGroup) {
            int v1 = 0;
            int v2 = 0;
            for(int v = 0; v < ((ViewGroup)view0).getChildCount(); ++v) {
                zzqn zzqn0 = this.zza(((ViewGroup)view0).getChildAt(v), zzqd0);
                v1 += zzqn0.zzbqk;
                v2 += zzqn0.zzbql;
            }
            return new zzqn(this, v1, v2);
        }
        return new zzqn(this, 0, 0);
    }

    @VisibleForTesting
    final void zza(zzqd zzqd0, WebView webView0, String s, boolean z) {
        zzqd0.zzmb();
        try {
            if(!TextUtils.isEmpty(s)) {
                String s1 = new JSONObject(s).optString("text");
                if(this.zzbqc || TextUtils.isEmpty(webView0.getTitle())) {
                    zzqd0.zza(s1, z, webView0.getX(), webView0.getY(), ((float)webView0.getWidth()), ((float)webView0.getHeight()));
                }
                else {
                    zzqd0.zza(webView0.getTitle() + "\n" + s1, z, webView0.getX(), webView0.getY(), ((float)webView0.getWidth()), ((float)webView0.getHeight()));
                }
            }
            if(zzqd0.zzlw()) {
                this.zzbpu.zzb(zzqd0);
            }
        }
        catch(JSONException unused_ex) {
            zzawf.zzeb("Json string may be malformed.");
        }
        catch(Throwable throwable0) {
            zzawf.zzb("Failed to get webview content.", throwable0);
            zzq.zzkz().zza(throwable0, "ContentFetchTask.processWebViewContent");
        }
    }

    @VisibleForTesting
    final void zzi(View view0) {
        try {
            zzqd zzqd0 = new zzqd(this.zzbos, this.zzbpw, this.zzbou, this.zzbpx, this.zzbpy, this.zzbpz, this.zzbqa, this.zzbov);
            Context context0 = zzq.zzky().getContext();
            if(context0 != null && !TextUtils.isEmpty(this.zzbqb)) {
                String s = (String)view0.getTag(context0.getResources().getIdentifier(((String)zzvh.zzpd().zzd(zzzx.zzcin)), "id", "com.esotericsoftware.gloomhavenhelper"));
                if(s != null && s.equals(this.zzbqb)) {
                    return;
                }
            }
            zzqn zzqn0 = this.zza(view0, zzqd0);
            zzqd0.zzme();
            if(zzqn0.zzbqk == 0 && zzqn0.zzbql == 0) {
                return;
            }
            if(zzqn0.zzbql == 0 && zzqd0.zzmf() == 0) {
                return;
            }
            if(zzqn0.zzbql == 0 && this.zzbpu.zza(zzqd0)) {
                return;
            }
            this.zzbpu.zzc(zzqd0);
        }
        catch(Exception exception0) {
            zzawf.zzc("Exception in fetchContentOnUIThread", exception0);
            zzq.zzkz().zza(exception0, "ContentFetchTask.fetchContent");
        }
    }

    public final void zzmg() {
        synchronized(this.lock) {
            if(this.started) {
                zzawf.zzeb("Content hash thread already started, quiting...");
                return;
            }
            this.started = true;
        }
        this.start();
    }

    @VisibleForTesting
    private static boolean zzmh() {
        try {
            Context context0 = zzq.zzky().getContext();
            if(context0 == null) {
                return false;
            }
            ActivityManager activityManager0 = (ActivityManager)context0.getSystemService("activity");
            KeyguardManager keyguardManager0 = (KeyguardManager)context0.getSystemService("keyguard");
            if(activityManager0 == null || keyguardManager0 == null) {
                return false;
            }
            List list0 = activityManager0.getRunningAppProcesses();
            if(list0 == null) {
                return false;
            }
            for(Object object0: list0) {
                ActivityManager.RunningAppProcessInfo activityManager$RunningAppProcessInfo0 = (ActivityManager.RunningAppProcessInfo)object0;
                if(Process.myPid() == activityManager$RunningAppProcessInfo0.pid) {
                    if(activityManager$RunningAppProcessInfo0.importance != 100 || keyguardManager0.inKeyguardRestrictedInputMode()) {
                        break;
                    }
                    PowerManager powerManager0 = (PowerManager)context0.getSystemService("power");
                    return powerManager0 == null ? false : powerManager0.isScreenOn();
                }
                if(false) {
                    break;
                }
            }
        }
        catch(Throwable throwable0) {
            zzq.zzkz().zza(throwable0, "ContentFetchTask.isInForeground");
        }
        return false;
    }

    public final zzqd zzmi() {
        return this.zzbpu.zzo(this.zzbqd);
    }

    private final void zzmj() {
        synchronized(this.lock) {
            this.zzbps = true;
            zzawf.zzeb(("ContentFetchThread: paused, mPause = " + true));
        }
    }

    public final boolean zzmk() {
        return this.zzbps;
    }
}

