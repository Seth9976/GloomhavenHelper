package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeMap;
import jeb.synthetic.FIN;

public final class zzal implements zza {
    private final Map zzbx;
    private long zzby;
    private final zzap zzbz;
    private final int zzca;

    public zzal(zzap zzap0) {
        this(zzap0, 0x500000);
    }

    private zzal(zzap zzap0, int v) {
        this.zzbx = new LinkedHashMap(16, 0.75f, true);
        this.zzby = 0L;
        this.zzbz = zzap0;
        this.zzca = 0x500000;
    }

    public zzal(File file0, int v) {
        this.zzbx = new LinkedHashMap(16, 0.75f, true);
        this.zzby = 0L;
        this.zzbz = new zzao(this, file0);
        this.zzca = 0x1400000;
    }

    @Override  // com.google.android.gms.internal.ads.zza
    public final void initialize() {
        synchronized(this) {
            File file0 = this.zzbz.zzo();
            if(!file0.exists()) {
                if(!file0.mkdirs()) {
                    zzag.e("Unable to create cache dir %s", new Object[]{file0.getAbsolutePath()});
                }
                return;
            }
            File[] arr_file = file0.listFiles();
            if(arr_file == null) {
                return;
            }
            for(int v1 = 0; v1 < arr_file.length; ++v1) {
                File file1 = arr_file[v1];
                try {
                    long v2 = file1.length();
                    zzaq zzaq0 = new zzaq(new BufferedInputStream(zzal.zza(file1)), v2);
                    try {
                        zzan zzan0 = zzan.zzc(zzaq0);
                        zzan0.zzcb = v2;
                        this.zza(zzan0.zzcc, zzan0);
                    }
                    finally {
                        zzaq0.close();
                    }
                }
                catch(IOException unused_ex) {
                    file1.delete();
                }
            }
        }
    }

    private final void remove(String s) {
        synchronized(this) {
            boolean z = this.zze(s).delete();
            this.removeEntry(s);
            if(!z) {
                zzag.d("Could not delete cache entry for key=%s, filename=%s", new Object[]{s, zzal.zzd(s)});
            }
        }
    }

    private final void removeEntry(String s) {
        zzan zzan0 = (zzan)this.zzbx.remove(s);
        if(zzan0 != null) {
            this.zzby -= zzan0.zzcb;
        }
    }

    private static int zza(InputStream inputStream0) throws IOException {
        int v = inputStream0.read();
        if(v == -1) {
            throw new EOFException();
        }
        return v;
    }

    @VisibleForTesting
    private static InputStream zza(File file0) throws FileNotFoundException {
        return new FileInputStream(file0);
    }

    static String zza(zzaq zzaq0) throws IOException {
        return new String(zzal.zza(zzaq0, zzal.zzc(zzaq0)), "UTF-8");
    }

    static void zza(OutputStream outputStream0, int v) throws IOException {
        outputStream0.write(v & 0xFF);
        outputStream0.write(v >> 8 & 0xFF);
        outputStream0.write(v >> 16 & 0xFF);
        outputStream0.write(v >>> 24);
    }

    static void zza(OutputStream outputStream0, long v) throws IOException {
        outputStream0.write(((int)(((byte)(((int)v))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 8)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 16)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 24)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 0x20)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 40)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 0x30)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 56)))))));
    }

    static void zza(OutputStream outputStream0, String s) throws IOException {
        byte[] arr_b = s.getBytes("UTF-8");
        zzal.zza(outputStream0, ((long)arr_b.length));
        outputStream0.write(arr_b, 0, arr_b.length);
    }

    private final void zza(String s, zzan zzan0) {
        if(this.zzbx.containsKey(s)) {
            zzan zzan1 = (zzan)this.zzbx.get(s);
            this.zzby += zzan0.zzcb - zzan1.zzcb;
        }
        else {
            this.zzby += zzan0.zzcb;
        }
        this.zzbx.put(s, zzan0);
    }

    @VisibleForTesting
    private static byte[] zza(zzaq zzaq0, long v) throws IOException {
        long v1 = zzaq0.zzp();
        if(v < 0L || v > v1 || ((long)(((int)v))) != v) {
            throw new IOException("streamToBytes length=" + v + ", maxLength=" + v1);
        }
        byte[] arr_b = new byte[((int)v)];
        new DataInputStream(zzaq0).readFully(arr_b);
        return arr_b;
    }

    @Override  // com.google.android.gms.internal.ads.zza
    public final zzd zza(String s) {
        zzd zzd0;
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        zzan zzan0 = (zzan)this.zzbx.get(s);
        if(zzan0 == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(this);
            FIN.finallyCodeEnd$NT(v);
            return null;
        }
        File file0 = this.zze(s);
        try {
            zzaq zzaq0 = new zzaq(new BufferedInputStream(zzal.zza(file0)), file0.length());
            try {
                zzan zzan1 = zzan.zzc(zzaq0);
                if(!TextUtils.equals(s, zzan1.zzcc)) {
                    zzag.d("%s: key=%s, found=%s", new Object[]{file0.getAbsolutePath(), s, zzan1.zzcc});
                    this.removeEntry(s);
                    FIN.finallyExec$NT(v);
                    return null;
                }
                byte[] arr_b = zzal.zza(zzaq0, zzaq0.zzp());
                zzd0 = new zzd();
                zzd0.data = arr_b;
                zzd0.zzg = zzan0.zzg;
                zzd0.zzh = zzan0.zzh;
                zzd0.zzi = zzan0.zzi;
                zzd0.zzj = zzan0.zzj;
                zzd0.zzk = zzan0.zzk;
                TreeMap treeMap0 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                for(Object object0: zzan0.zzm) {
                    treeMap0.put(((zzk)object0).getName(), ((zzk)object0).getValue());
                }
                zzd0.zzl = treeMap0;
                zzd0.zzm = Collections.unmodifiableList(zzan0.zzm);
            }
            finally {
                zzaq0.close();
            }
            FIN.finallyExec$NT(v);
            return zzd0;
        }
        catch(IOException iOException0) {
            zzag.d("%s: %s", new Object[]{file0.getAbsolutePath(), iOException0.toString()});
            this.remove(s);
            FIN.finallyExec$NT(v);
            return null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zza
    public final void zza(String s, zzd zzd0) {
        long v4;
        synchronized(this) {
            if(this.zzby + ((long)zzd0.data.length) > ((long)this.zzca) && ((float)zzd0.data.length) > ((float)this.zzca) * 0.9f) {
                return;
            }
            File file0 = this.zze(s);
            try {
                BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(new FileOutputStream(file0));
                zzan zzan0 = new zzan(s, zzd0);
                if(zzan0.zza(bufferedOutputStream0)) {
                    bufferedOutputStream0.write(zzd0.data);
                    bufferedOutputStream0.close();
                    zzan0.zzcb = file0.length();
                    this.zza(s, zzan0);
                    if(this.zzby >= ((long)this.zzca)) {
                        if(zzag.DEBUG) {
                            zzag.v("Pruning old cache entries.", new Object[0]);
                        }
                        long v1 = this.zzby;
                        long v2 = SystemClock.elapsedRealtime();
                        int v3 = 0;
                        Iterator iterator0 = this.zzbx.entrySet().iterator();
                        while(true) {
                            if(!iterator0.hasNext()) {
                                v4 = v1;
                                break;
                            }
                            Object object0 = iterator0.next();
                            zzan zzan1 = (zzan)((Map.Entry)object0).getValue();
                            if(this.zze(zzan1.zzcc).delete()) {
                                v4 = v1;
                                this.zzby -= zzan1.zzcb;
                            }
                            else {
                                v4 = v1;
                                zzag.d("Could not delete cache entry for key=%s, filename=%s", new Object[]{zzan1.zzcc, zzal.zzd(zzan1.zzcc)});
                            }
                            iterator0.remove();
                            ++v3;
                            if(((float)this.zzby) < ((float)this.zzca) * 0.9f) {
                                break;
                            }
                            v1 = v4;
                        }
                        if(zzag.DEBUG) {
                            zzag.v("pruned %d files, %d bytes, %d ms", new Object[]{v3, ((long)(this.zzby - v4)), ((long)(SystemClock.elapsedRealtime() - v2))});
                        }
                    }
                    return;
                }
                bufferedOutputStream0.close();
                zzag.d("Failed to write header for %s", new Object[]{file0.getAbsolutePath()});
            }
            catch(IOException unused_ex) {
            }
            if(!file0.delete()) {
                zzag.d("Could not clean up file %s", new Object[]{file0.getAbsolutePath()});
            }
        }
    }

    static int zzb(InputStream inputStream0) throws IOException {
        int v = zzal.zza(inputStream0);
        int v1 = zzal.zza(inputStream0);
        int v2 = zzal.zza(inputStream0);
        return zzal.zza(inputStream0) << 24 | (v | v1 << 8 | v2 << 16);
    }

    static List zzb(zzaq zzaq0) throws IOException {
        int v = zzal.zzb(zzaq0);
        if(v < 0) {
            throw new IOException("readHeaderList size=" + v);
        }
        List list0 = v == 0 ? Collections.emptyList() : new ArrayList();
        for(int v1 = 0; v1 < v; ++v1) {
            list0.add(new zzk(zzal.zza(zzaq0).intern(), zzal.zza(zzaq0).intern()));
        }
        return list0;
    }

    static long zzc(InputStream inputStream0) throws IOException {
        return ((long)zzal.zza(inputStream0)) & 0xFFL | (((long)zzal.zza(inputStream0)) & 0xFFL) << 8 | (((long)zzal.zza(inputStream0)) & 0xFFL) << 16 | (((long)zzal.zza(inputStream0)) & 0xFFL) << 24 | (((long)zzal.zza(inputStream0)) & 0xFFL) << 0x20 | (((long)zzal.zza(inputStream0)) & 0xFFL) << 40 | (((long)zzal.zza(inputStream0)) & 0xFFL) << 0x30 | (0xFFL & ((long)zzal.zza(inputStream0))) << 56;
    }

    private static String zzd(String s) {
        int v = s.length();
        String s1 = String.valueOf(s.substring(0, v / 2).hashCode());
        String s2 = String.valueOf(s.substring(v / 2).hashCode());
        return s2.length() == 0 ? new String(s1) : s1 + s2;
    }

    private final File zze(String s) {
        return new File(this.zzbz.zzo(), zzal.zzd(s));
    }
}

