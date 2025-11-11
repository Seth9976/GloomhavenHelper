package com.google.android.gms.internal.ads;

import android.os.Environment;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzq;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public final class zzsn {
    private final zzss zzbsk;
    @GuardedBy("this")
    private final zztv zzbsl;
    private final boolean zzbsm;

    private zzsn() {
        this.zzbsm = false;
        this.zzbsk = new zzss();
        this.zzbsl = new zztv();
        this.zzmz();
    }

    public zzsn(zzss zzss0) {
        this.zzbsk = zzss0;
        this.zzbsm = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcot)).booleanValue();
        this.zzbsl = new zztv();
        this.zzmz();
    }

    public final void zza(zza zzsp$zza$zza0) {
        synchronized(this) {
            if(!this.zzbsm) {
                return;
            }
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcou)).booleanValue()) {
                this.zzc(zzsp$zza$zza0);
                return;
            }
            this.zzb(zzsp$zza$zza0);
        }
    }

    public final void zza(zzsq zzsq0) {
        synchronized(this) {
            if(this.zzbsm) {
                try {
                    zzsq0.zza(this.zzbsl);
                }
                catch(NullPointerException nullPointerException0) {
                    zzq.zzkz().zza(nullPointerException0, "AdMobClearcutLogger.modify");
                }
            }
        }
    }

    private final void zzb(zza zzsp$zza$zza0) {
        synchronized(this) {
            this.zzbsl.zzcbi = zzsn.zzna();
            byte[] arr_b = zzeda.zzb(this.zzbsl);
            this.zzbsk.zzf(arr_b).zzbr(zzsp$zza$zza0.zzaf()).zzdt();
            String s = Integer.toString(zzsp$zza$zza0.zzaf(), 10);
            zzawf.zzee((s.length() == 0 ? new String("Logging Event with event code : ") : "Logging Event with event code : " + s));
        }
    }

    private final void zzc(zza zzsp$zza$zza0) {
        FileOutputStream fileOutputStream0;
        synchronized(this) {
            File file0 = Environment.getExternalStorageDirectory();
            if(file0 == null) {
                return;
            }
            File file1 = new File(file0, "clearcut_events.txt");
            try {
                fileOutputStream0 = new FileOutputStream(file1, true);
            }
            catch(FileNotFoundException unused_ex) {
                zzawf.zzee("Could not find file for Clearcut");
                return;
            }
            try {
                try {
                    fileOutputStream0.write(this.zzd(zzsp$zza$zza0).getBytes());
                    goto label_25;
                }
                catch(IOException unused_ex) {
                }
                zzawf.zzee("Could not write Clearcut to file.");
            }
            catch(Throwable throwable0) {
                goto label_20;
            }
            try {
                fileOutputStream0.close();
            }
            catch(IOException unused_ex) {
                try {
                    zzawf.zzee("Could not close Clearcut output stream.");
                }
                catch(FileNotFoundException unused_ex) {
                    zzawf.zzee("Could not find file for Clearcut");
                }
            }
            return;
            try {
            label_20:
                fileOutputStream0.close();
                throw throwable0;
            }
            catch(IOException unused_ex) {
                try {
                    zzawf.zzee("Could not close Clearcut output stream.");
                    throw throwable0;
                }
                catch(FileNotFoundException unused_ex) {
                    zzawf.zzee("Could not find file for Clearcut");
                    return;
                }
            }
            try {
            label_25:
                fileOutputStream0.close();
                return;
            }
            catch(IOException unused_ex) {
                try {
                    zzawf.zzee("Could not close Clearcut output stream.");
                    return;
                }
                catch(FileNotFoundException unused_ex) {
                }
            }
            zzawf.zzee("Could not find file for Clearcut");
        }
    }

    private final String zzd(zza zzsp$zza$zza0) {
        Object[] arr_object;
        synchronized(this) {
            arr_object = new Object[]{this.zzbsl.zzcbe, zzq.zzlc().elapsedRealtime(), zzsp$zza$zza0.zzaf(), Base64.encodeToString(zzeda.zzb(this.zzbsl), 3)};
        }
        return String.format("id=%s,timestamp=%s,event=%s,data=%s\n", arr_object);
    }

    public static zzsn zzmy() {
        return new zzsn();
    }

    private final void zzmz() {
        synchronized(this) {
            this.zzbsl.zzcbm = new zztr();
            zztr zztr0 = this.zzbsl.zzcbm;
            zztr0.zzcak = new zztq();
            this.zzbsl.zzcbj = new zztt();
        }
    }

    private static long[] zzna() {
        int v;
        List list0 = zzzx.zzqk();
        ArrayList arrayList0 = new ArrayList();
        Iterator iterator0 = list0.iterator();
        while(true) {
            v = 0;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            String[] arr_s = ((String)object0).split(",");
            while(v < arr_s.length) {
                String s = arr_s[v];
                try {
                    arrayList0.add(Long.valueOf(s));
                }
                catch(NumberFormatException unused_ex) {
                    zzawf.zzee("Experiment ID is not a number");
                }
                ++v;
            }
        }
        long[] arr_v = new long[arrayList0.size()];
        int v1 = arrayList0.size();
        for(int v2 = 0; v < v1; ++v2) {
            Object object1 = arrayList0.get(v);
            ++v;
            arr_v[v2] = (long)(((Long)object1));
        }
        return arr_v;
    }
}

