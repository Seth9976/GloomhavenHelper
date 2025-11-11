package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.Clock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzbdh extends zzbda {
    private File zzco;
    private static final Set zzees;
    private static final DecimalFormat zzeet;
    private boolean zzeeu;

    static {
        zzbdh.zzees = Collections.synchronizedSet(new HashSet());
        zzbdh.zzeet = new DecimalFormat("#,###");
    }

    public zzbdh(zzbbm zzbbm0) {
        super(zzbbm0);
        File file0 = this.mContext.getCacheDir();
        if(file0 == null) {
            zzawf.zzfa("Context.getCacheDir() returned null");
            return;
        }
        this.zzco = new File(file0, "admobVideoStreams");
        if(!this.zzco.isDirectory() && !this.zzco.mkdirs()) {
            String s = this.zzco.getAbsolutePath();
            zzawf.zzfa((s.length() == 0 ? new String("Could not create preload cache directory at ") : "Could not create preload cache directory at " + s));
            this.zzco = null;
            return;
        }
        if(this.zzco.setReadable(true, false) && this.zzco.setExecutable(true, false)) {
            return;
        }
        String s1 = this.zzco.getAbsolutePath();
        zzawf.zzfa((s1.length() == 0 ? new String("Could not set cache file permissions at ") : "Could not set cache file permissions at " + s1));
        this.zzco = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void abort() {
        this.zzeeu = true;
    }

    private final File zzc(File file0) {
        return new File(this.zzco, file0.getName() + ".done");
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final boolean zzfj(String s) {
        String s21;
        int v18;
        zzayn zzayn1;
        ReadableByteChannel readableByteChannel1;
        int v17;
        FileChannel fileChannel1;
        String s20;
        String s19;
        String s18;
        int v16;
        long v15;
        zzayn zzayn0;
        FileOutputStream fileOutputStream2;
        String s16;
        Long long0;
        ByteBuffer byteBuffer0;
        FileChannel fileChannel0;
        String s15;
        FileOutputStream fileOutputStream1;
        ReadableByteChannel readableByteChannel0;
        int v13;
        String s14;
        int v12;
        String s13;
        int v11;
        HttpURLConnection httpURLConnection0;
        URL uRL0;
        boolean z;
        int v1;
        FileOutputStream fileOutputStream0 = null;
        int v = 0;
        if(this.zzco == null) {
            this.zza(s, null, "noCacheDir", null);
            return false;
        }
        while(true) {
            File file0 = this.zzco;
            if(file0 == null) {
                v1 = 0;
            }
            else {
                File[] arr_file = file0.listFiles();
                v1 = 0;
                for(int v2 = 0; v2 < arr_file.length; ++v2) {
                    if(!arr_file[v2].getName().endsWith(".done")) {
                        ++v1;
                    }
                }
            }
            if(v1 <= ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzchn))))) {
                break;
            }
            File file1 = this.zzco;
            if(file1 == null) {
                z = false;
            }
            else {
                File[] arr_file1 = file1.listFiles();
                long v3 = 0x7FFFFFFFFFFFFFFFL;
                File file2 = null;
                for(int v4 = 0; v4 < arr_file1.length; ++v4) {
                    File file3 = arr_file1[v4];
                    if(!file3.getName().endsWith(".done")) {
                        long v5 = file3.lastModified();
                        if(v5 < v3) {
                            file2 = file3;
                            v3 = v5;
                        }
                    }
                }
                if(file2 == null) {
                    z = false;
                }
                else {
                    z = file2.delete();
                    File file4 = this.zzc(file2);
                    if(file4.isFile()) {
                        z &= file4.delete();
                    }
                }
            }
            if(!z) {
                zzawf.zzfa("Unable to expire stream cache");
                this.zza(s, null, "expireFailed", null);
                return false;
            }
        }
        String s1 = this.zzfk(s);
        File file5 = new File(this.zzco, s1);
        File file6 = this.zzc(file5);
        if(file5.isFile() && file6.isFile()) {
            int v6 = (int)file5.length();
            String s2 = String.valueOf(s);
            zzawf.zzeb((s2.length() == 0 ? new String("Stream cache hit at ") : "Stream cache hit at " + s2));
            this.zza(s, file5.getAbsolutePath(), v6);
            return true;
        }
        String s3 = this.zzco.getAbsolutePath();
        String s4 = String.valueOf(s);
        String s5 = s4.length() == 0 ? new String(s3) : s3 + s4;
        synchronized(zzbdh.zzees) {
            if(zzbdh.zzees.contains(s5)) {
                String s6 = String.valueOf(s);
                zzawf.zzfa((s6.length() == 0 ? new String("Stream cache already in progress at ") : "Stream cache already in progress at " + s6));
                this.zza(s, file5.getAbsolutePath(), "inProgress", null);
                return false;
            }
            zzbdh.zzees.add(s5);
        }
        String s7 = "error";
        try {
            int v8 = (int)(((Integer)zzvh.zzpd().zzd(zzzx.zzchs)));
            uRL0 = new URL(s);
            int v9 = 0;
            while(true) {
            label_76:
                ++v9;
                if(v9 > 20) {
                    s15 = s5;
                    throw new IOException("Too many redirects (20)");
                }
                URLConnection uRLConnection0 = uRL0.openConnection();
                uRLConnection0.setConnectTimeout(v8);
                uRLConnection0.setReadTimeout(v8);
                if(!(uRLConnection0 instanceof HttpURLConnection)) {
                    s15 = s5;
                    throw new IOException("Invalid protocol.");
                }
                httpURLConnection0 = (HttpURLConnection)uRLConnection0;
                zzazb zzazb0 = new zzazb();
                zzazb0.zza(httpURLConnection0, null);
                httpURLConnection0.setInstanceFollowRedirects(false);
                int v10 = httpURLConnection0.getResponseCode();
                zzazb0.zza(httpURLConnection0, v10);
                if(v10 / 100 == 3) {
                    break;
                }
                goto label_104;
            }
        }
        catch(IOException | RuntimeException iOException0) {
            s15 = s5;
            goto label_255;
        }
        try {
            String s8 = httpURLConnection0.getHeaderField("Location");
            if(s8 == null) {
                throw new IOException("Missing Location header in redirect");
            }
            URL uRL1 = new URL(uRL0, s8);
            String s9 = uRL1.getProtocol();
            if(s9 == null) {
                throw new IOException("Protocol is null");
            }
            if(!s9.equals("http") && !s9.equals("https")) {
                String s10 = String.valueOf(s9);
                throw new IOException((s10.length() == 0 ? new String("Unsupported scheme: ") : "Unsupported scheme: " + s10));
            }
            String s11 = String.valueOf(s8);
            zzawf.zzeb((s11.length() == 0 ? new String("Redirecting to ") : "Redirecting to " + s11));
            httpURLConnection0.disconnect();
            uRL0 = uRL1;
            goto label_76;
        label_104:
            if(httpURLConnection0 instanceof HttpURLConnection) {
                v11 = httpURLConnection0.getResponseCode();
                if(v11 >= 400) {
                    s7 = "badUrl";
                    String s12 = Integer.toString(v11);
                    s13 = s12.length() == 0 ? new String("HTTP request failed. Code: ") : "HTTP request failed. Code: " + s12;
                    throw new IOException("HTTP status code " + v11 + " at " + s);
                }
            }
            goto label_113;
        }
        catch(IOException | RuntimeException iOException0) {
            s13 = null;
            s15 = s5;
            goto label_256;
        }
        try {
            throw new IOException("HTTP status code " + v11 + " at " + s);
        }
        catch(IOException | RuntimeException iOException0) {
            s15 = s5;
            goto label_256;
        }
        try {
        label_113:
            v12 = httpURLConnection0.getContentLength();
            if(v12 >= 0) {
                s14 = zzbdh.zzeet.format(((long)v12));
                v13 = (int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcho)));
                if(v12 <= v13) {
                    zzawf.zzeb(("Caching " + s14 + " bytes from " + s));
                    readableByteChannel0 = Channels.newChannel(httpURLConnection0.getInputStream());
                    fileOutputStream1 = new FileOutputStream(file5);
                    goto label_125;
                }
                goto label_236;
            }
            goto label_241;
        }
        catch(IOException | RuntimeException iOException0) {
            s15 = s5;
            goto label_255;
        }
        try {
        label_125:
            fileChannel0 = fileOutputStream1.getChannel();
            byteBuffer0 = ByteBuffer.allocate(0x100000);
            Clock clock0 = zzq.zzlc();
            long v14 = clock0.currentTimeMillis();
            long0 = (Long)zzvh.zzpd().zzd(zzzx.zzchr);
            s16 = s5;
        }
        catch(IOException | RuntimeException iOException0) {
            fileOutputStream2 = fileOutputStream1;
            s15 = s5;
            fileOutputStream0 = fileOutputStream2;
            s13 = null;
            goto label_256;
        }
        try {
            zzayn0 = new zzayn(((long)long0));
            v15 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzchq)));
            while(true) {
            label_138:
                v16 = readableByteChannel0.read(byteBuffer0);
                break;
            }
        }
        catch(IOException | RuntimeException iOException0) {
            fileOutputStream2 = fileOutputStream1;
            s15 = s16;
            fileOutputStream0 = fileOutputStream2;
            s13 = null;
            goto label_256;
        }
        if(v16 < 0) {
            goto label_210;
        }
        v += v16;
        if(v > v13) {
            try {
                s7 = "sizeExceeded";
                String s17 = Integer.toString(v);
                s18 = s17.length() == 0 ? new String("File too big for full file cache. Size: ") : "File too big for full file cache. Size: " + s17;
            }
            catch(IOException | RuntimeException iOException0) {
                s15 = s16;
                s13 = null;
                goto label_159;
            }
            try {
                throw new IOException("stream cache file size limit exceeded");
            }
            catch(IOException | RuntimeException iOException0) {
                s13 = s18;
                s15 = s16;
            }
        label_159:
            fileOutputStream0 = fileOutputStream1;
        }
        else {
            try {
                byteBuffer0.flip();
                while(fileChannel0.write(byteBuffer0) > 0) {
                }
                byteBuffer0.clear();
                if(clock0.currentTimeMillis() - v14 <= 1000L * v15) {
                    if(this.zzeeu) {
                        fileOutputStream2 = fileOutputStream1;
                        s20 = s16;
                        s7 = "externalAbort";
                        throw new IOException("abort requested");
                    }
                    if(zzayn0.tryAcquire()) {
                        s19 = file5.getAbsolutePath();
                        s20 = s16;
                        fileChannel1 = fileChannel0;
                        v17 = v13;
                        fileOutputStream2 = fileOutputStream1;
                        readableByteChannel1 = readableByteChannel0;
                        zzayn1 = zzayn0;
                        v18 = v12;
                        goto label_181;
                    }
                    else {
                        goto label_183;
                    }
                    goto label_190;
                }
                goto label_202;
            }
            catch(IOException | RuntimeException iOException0) {
                fileOutputStream2 = fileOutputStream1;
                s15 = s16;
                fileOutputStream0 = fileOutputStream2;
                s13 = null;
                goto label_256;
            }
            try {
            label_181:
                zzayx.zzyy.post(new zzbcz(this, s, s19, v, v12, false));
                goto label_190;
            label_183:
                fileChannel1 = fileChannel0;
                v17 = v13;
                fileOutputStream2 = fileOutputStream1;
                readableByteChannel1 = readableByteChannel0;
                zzayn1 = zzayn0;
                v18 = v12;
                s20 = s16;
            label_190:
                zzayn0 = zzayn1;
                readableByteChannel0 = readableByteChannel1;
                s16 = s20;
                fileChannel0 = fileChannel1;
                v13 = v17;
                fileOutputStream1 = fileOutputStream2;
                v12 = v18;
                goto label_138;
            label_202:
                s7 = "downloadTimeout";
                s21 = Long.toString(v15);
            }
            catch(IOException | RuntimeException iOException0) {
                s15 = s20;
                fileOutputStream0 = fileOutputStream2;
                s13 = null;
                goto label_256;
            }
            try {
                throw new IOException("stream cache time limit exceeded");
            }
            catch(IOException | RuntimeException iOException0) {
                s13 = "Timeout exceeded. Limit: " + s21 + " sec";
                s15 = s16;
                fileOutputStream0 = fileOutputStream1;
                goto label_256;
            }
            try {
            label_210:
                fileOutputStream2 = fileOutputStream1;
                s20 = s16;
                fileOutputStream2.close();
                if(zzawf.isLoggable(3)) {
                    goto label_214;
                }
                goto label_215;
            }
            catch(IOException | RuntimeException iOException0) {
                s15 = s20;
                fileOutputStream0 = fileOutputStream2;
                s13 = null;
                goto label_256;
            }
            try {
            label_214:
                zzawf.zzeb(("Preloaded " + zzbdh.zzeet.format(((long)v)) + " bytes from " + s));
            }
            catch(IOException | RuntimeException iOException0) {
                s15 = s20;
                fileOutputStream0 = fileOutputStream2;
                s13 = null;
                goto label_256;
            }
            try {
            label_215:
                file5.setReadable(true, false);
                if(file6.isFile()) {
                    goto label_217;
                }
                else {
                    goto label_219;
                }
                goto label_224;
            }
            catch(IOException | RuntimeException iOException0) {
                s15 = s20;
                fileOutputStream0 = fileOutputStream2;
                s13 = null;
                goto label_256;
            }
            try {
                try {
                label_217:
                    file6.setLastModified(System.currentTimeMillis());
                    goto label_224;
                }
                catch(IOException iOException0) {
                    s15 = s20;
                    fileOutputStream0 = fileOutputStream2;
                    s13 = null;
                    goto label_256;
                }
                try {
                label_219:
                    file6.createNewFile();
                }
                catch(IOException unused_ex) {
                }
            }
            catch(RuntimeException iOException0) {
                s15 = s20;
                fileOutputStream0 = fileOutputStream2;
                s13 = null;
                goto label_256;
            }
            try {
            label_224:
                this.zza(s, file5.getAbsolutePath(), v);
                s15 = s20;
            }
            catch(IOException | RuntimeException iOException0) {
                s15 = s20;
                fileOutputStream0 = fileOutputStream2;
                s13 = null;
                goto label_256;
            }
            try {
                zzbdh.zzees.remove(s15);
                return true;
            }
            catch(IOException | RuntimeException iOException0) {
            }
            fileOutputStream0 = fileOutputStream2;
            s13 = null;
            goto label_256;
            try {
            label_236:
                zzawf.zzfa(("Content length " + s14 + " exceeds limit at " + s));
                String s22 = String.valueOf(s14);
                this.zza(s, file5.getAbsolutePath(), "sizeExceeded", (s22.length() == 0 ? new String("File too big for full file cache. Size: ") : "File too big for full file cache. Size: " + s22));
                zzbdh.zzees.remove(s5);
                return false;
            label_241:
                String s23 = String.valueOf(s);
                zzawf.zzfa((s23.length() == 0 ? new String("Stream cache aborted, missing content-length header at ") : "Stream cache aborted, missing content-length header at " + s23));
                this.zza(s, file5.getAbsolutePath(), "contentLengthMissing", null);
                zzbdh.zzees.remove(s5);
                return false;
            }
            catch(IOException | RuntimeException iOException0) {
                s13 = null;
            }
            s15 = s5;
            goto label_256;
            try {
                s15 = s5;
                throw new IOException("Invalid protocol.");
            }
            catch(IOException | RuntimeException iOException0) {
            }
        label_255:
            s13 = null;
        }
    label_256:
        if(iOException0 instanceof RuntimeException) {
            zzq.zzkz().zza(iOException0, "VideoStreamFullFileCache.preload");
        }
        try {
            fileOutputStream0.close();
        }
        catch(IOException | NullPointerException unused_ex) {
        }
        if(this.zzeeu) {
            zzawf.zzez(("Preload aborted for URL \"" + s + "\""));
        }
        else {
            zzawf.zzd(("Preload failed for URL \"" + s + "\""), iOException0);
        }
        if(file5.exists() && !file5.delete()) {
            String s24 = file5.getAbsolutePath();
            zzawf.zzfa((s24.length() == 0 ? new String("Could not delete partial cache file at ") : "Could not delete partial cache file at " + s24));
        }
        this.zza(s, file5.getAbsolutePath(), s7, s13);
        zzbdh.zzees.remove(s15);
        return false;
    }
}

