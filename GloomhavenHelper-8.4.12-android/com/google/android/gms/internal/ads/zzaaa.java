package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaaa {
    private File file;
    @VisibleForTesting
    private String zzbmj;
    @VisibleForTesting
    private BlockingQueue zzcsk;
    @VisibleForTesting
    private LinkedHashMap zzcsl;
    @VisibleForTesting
    private Map zzcsm;
    @VisibleForTesting
    private String zzcsn;
    private final HashSet zzcso;
    private AtomicBoolean zzcsp;
    @VisibleForTesting
    private Context zzur;

    public zzaaa() {
        this.zzcsk = new ArrayBlockingQueue(100);
        this.zzcsl = new LinkedHashMap();
        this.zzcsm = new HashMap();
        this.zzcso = new HashSet(Arrays.asList(new String[]{"noop", "activeViewPingSent", "viewabilityChanged", "visibilityChanged"}));
    }

    private final void zza(Map map0, String s) {
        FileOutputStream fileOutputStream0;
        Uri.Builder uri$Builder0 = Uri.parse(this.zzcsn).buildUpon();
        for(Object object0: map0.entrySet()) {
            uri$Builder0.appendQueryParameter(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
        }
        StringBuilder stringBuilder0 = new StringBuilder(uri$Builder0.build().toString());
        if(!TextUtils.isEmpty(s)) {
            stringBuilder0.append("&it=");
            stringBuilder0.append(s);
        }
        String s1 = stringBuilder0.toString();
        if(this.zzcsp.get()) {
            File file0 = this.file;
            if(file0 != null) {
                try {
                    try {
                        fileOutputStream0 = null;
                        fileOutputStream0 = new FileOutputStream(file0, true);
                        fileOutputStream0.write(s1.getBytes());
                        fileOutputStream0.write(10);
                        goto label_35;
                    }
                    catch(IOException iOException0) {
                    }
                    zzawf.zzd("CsiReporter: Cannot write to file: sdk_csi_data.txt.", iOException0);
                    if(fileOutputStream0 != null) {
                        goto label_22;
                    }
                    return;
                }
                catch(Throwable throwable0) {
                    goto label_29;
                }
                try {
                label_22:
                    fileOutputStream0.close();
                }
                catch(IOException iOException1) {
                    zzawf.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", iOException1);
                }
                return;
            label_29:
                if(fileOutputStream0 != null) {
                    try {
                        fileOutputStream0.close();
                    }
                    catch(IOException iOException2) {
                        zzawf.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", iOException2);
                    }
                }
                throw throwable0;
                try {
                label_35:
                    fileOutputStream0.close();
                }
                catch(IOException iOException3) {
                    zzawf.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", iOException3);
                }
                return;
            }
            zzawf.zzfa("CsiReporter: File doesn\'t exists. Cannot write CSI data to file.");
            return;
        }
        zzawo.zzb(this.zzur, this.zzbmj, s1);
    }

    final Map zza(Map map0, @Nullable Map map1) {
        Map map2 = new LinkedHashMap(map0);
        if(map1 == null) {
            return map2;
        }
        for(Object object0: map1.entrySet()) {
            String s = (String)((Map.Entry)object0).getKey();
            String s1 = (String)((Map.Entry)object0).getValue();
            String s2 = (String)map2.get(s);
            map2.put(s, this.zzcr(s).zzg(s2, s1));
        }
        return map2;
    }

    public final void zza(Context context0, String s, String s1, Map map0) {
        this.zzur = context0;
        this.zzbmj = s;
        this.zzcsn = s1;
        this.zzcsp = new AtomicBoolean(false);
        this.zzcsp.set(((Boolean)zzabe.zzcug.get()).booleanValue());
        if(this.zzcsp.get()) {
            File file0 = Environment.getExternalStorageDirectory();
            if(file0 != null) {
                this.file = new File(file0, "sdk_csi_data.txt");
            }
        }
        for(Object object0: map0.entrySet()) {
            this.zzcsl.put(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
        }
        zzaad zzaad0 = () -> {
            String s;
            zzaak zzaak0;
            while(true) {
                try {
                label_0:
                    zzaak0 = (zzaak)this.zzcsk.take();
                    s = zzaak0.zzqu();
                }
                catch(InterruptedException interruptedException0) {
                    zzawf.zzd("CsiReporter:reporter interrupted", interruptedException0);
                    return;
                }
                if(TextUtils.isEmpty(s)) {
                    goto label_0;
                }
                this.zza(this.zza(this.zzcsl, zzaak0.zzqv()), s);
            }
        };
        zzazq.zzdxk.execute(zzaad0);
        this.zzcsm.put("action", zzaae.zzcst);
        this.zzcsm.put("ad_format", zzaae.zzcst);
        this.zzcsm.put("e", zzaae.zzcsu);
    }

    public final boolean zza(zzaak zzaak0) {
        return this.zzcsk.offer(zzaak0);
    }

    public final zzaae zzcr(String s) {
        zzaae zzaae0 = (zzaae)this.zzcsm.get(s);
        return zzaae0 == null ? zzaae.zzcss : zzaae0;
    }

    public final void zzcs(String s) {
        if(this.zzcso.contains(s)) {
            return;
        }
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        linkedHashMap0.put("sdkVersion", this.zzbmj);
        linkedHashMap0.put("ue", s);
        this.zza(this.zza(this.zzcsl, linkedHashMap0), "");
    }

    // 检测为 Lambda 实现
    final void zzqp() [...]
}

