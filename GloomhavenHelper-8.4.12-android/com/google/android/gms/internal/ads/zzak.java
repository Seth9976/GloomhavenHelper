package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeSet;

public class zzak implements zzn {
    private static final boolean DEBUG;
    @Deprecated
    private final zzau zzbu;
    private final zzah zzbv;
    private final zzaj zzbw;

    static {
        zzak.DEBUG = zzag.DEBUG;
    }

    public zzak(zzah zzah0) {
        this(zzah0, new zzaj(0x1000));
    }

    private zzak(zzah zzah0, zzaj zzaj0) {
        this.zzbv = zzah0;
        this.zzbu = zzah0;
        this.zzbw = zzaj0;
    }

    private static void zza(String s, zzq zzq0, zzae zzae0) throws zzae {
        zzad zzad0 = zzq0.zzj();
        int v = zzq0.zzi();
        try {
            zzad0.zza(zzae0);
        }
        catch(zzae zzae1) {
            zzq0.zzb(String.format("%s-timeout-giveup [timeout=%s]", s, v));
            throw zzae1;
        }
        zzq0.zzb(String.format("%s-retry [timeout=%s]", s, v));
    }

    private final byte[] zza(InputStream inputStream0, int v) throws IOException, zzac {
        byte[] arr_b1;
        zzay zzay0 = new zzay(this.zzbw, v);
        byte[] arr_b = null;
        try {
            if(inputStream0 == null) {
                throw new zzac();
            }
            arr_b = this.zzbw.zzc(0x400);
            int v1;
            while((v1 = inputStream0.read(arr_b)) != -1) {
                zzay0.write(arr_b, 0, v1);
            }
            arr_b1 = zzay0.toByteArray();
        }
        catch(Throwable throwable0) {
            if(inputStream0 != null) {
                try {
                    inputStream0.close();
                }
                catch(IOException unused_ex) {
                    zzag.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.zzbw.zza(arr_b);
            zzay0.close();
            throw throwable0;
        }
        try {
            inputStream0.close();
        }
        catch(IOException unused_ex) {
            zzag.v("Error occurred when closing InputStream", new Object[0]);
        }
        this.zzbw.zza(arr_b);
        zzay0.close();
        return arr_b1;
    }

    @Override  // com.google.android.gms.internal.ads.zzn
    public zzo zzc(zzq zzq0) throws zzae {
        byte[] arr_b1;
        int v1;
        zzar zzar0;
        Map map0;
        long v = SystemClock.elapsedRealtime();
        while(true) {
            List list0 = Collections.emptyList();
            try {
                zzd zzd0 = zzq0.zzf();
                if(zzd0 == null) {
                    map0 = Collections.emptyMap();
                }
                else {
                    HashMap hashMap0 = new HashMap();
                    if(zzd0.zzg != null) {
                        hashMap0.put("If-None-Match", zzd0.zzg);
                    }
                    if(zzd0.zzi > 0L) {
                        hashMap0.put("If-Modified-Since", zzas.zzb(zzd0.zzi));
                    }
                    map0 = hashMap0;
                }
                zzar0 = this.zzbv.zza(zzq0, map0);
                goto label_19;
            }
            catch(SocketTimeoutException unused_ex) {
                goto label_75;
            }
            catch(MalformedURLException malformedURLException0) {
                goto label_78;
            }
            catch(IOException iOException0) {
            }
            List list1 = list0;
            zzar0 = null;
            byte[] arr_b = null;
            goto label_83;
            try {
            label_19:
                v1 = zzar0.getStatusCode();
                list0 = zzar0.zzq();
                if(v1 == 304) {
                    zzd zzd1 = zzq0.zzf();
                    if(zzd1 == null) {
                        return new zzo(304, null, true, SystemClock.elapsedRealtime() - v, list0);
                    }
                    TreeSet treeSet0 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                    if(!list0.isEmpty()) {
                        for(Object object0: list0) {
                            treeSet0.add(((zzk)object0).getName());
                        }
                    }
                    ArrayList arrayList0 = new ArrayList(list0);
                    if(zzd1.zzm != null) {
                        if(!zzd1.zzm.isEmpty()) {
                            for(Object object2: zzd1.zzm) {
                                zzk zzk0 = (zzk)object2;
                                if(!treeSet0.contains(zzk0.getName())) {
                                    arrayList0.add(zzk0);
                                }
                            }
                        }
                    }
                    else if(!zzd1.zzl.isEmpty()) {
                        for(Object object1: zzd1.zzl.entrySet()) {
                            Map.Entry map$Entry0 = (Map.Entry)object1;
                            if(!treeSet0.contains(map$Entry0.getKey())) {
                                arrayList0.add(new zzk(((String)map$Entry0.getKey()), ((String)map$Entry0.getValue())));
                            }
                        }
                    }
                    return new zzo(304, zzd1.data, true, SystemClock.elapsedRealtime() - v, arrayList0);
                }
                InputStream inputStream0 = zzar0.getContent();
                if(inputStream0 == null) {
                    goto label_60;
                }
                else {
                    arr_b1 = this.zza(inputStream0, zzar0.getContentLength());
                }
                goto label_61;
            }
            catch(SocketTimeoutException unused_ex) {
                goto label_75;
            }
            catch(MalformedURLException malformedURLException0) {
                goto label_78;
            }
            catch(IOException iOException0) {
            }
            list1 = list0;
            arr_b = null;
            goto label_83;
        label_60:
            arr_b1 = new byte[0];
            try {
            label_61:
                long v2 = SystemClock.elapsedRealtime() - v;
                if(zzak.DEBUG || v2 > 3000L) {
                    Object[] arr_object = {zzq0, v2, null, null, null};
                    Integer integer0 = arr_b1 == null ? "null" : ((int)arr_b1.length);
                    arr_object[2] = integer0;
                    arr_object[3] = v1;
                    arr_object[4] = zzq0.zzj().zzc();
                    zzag.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", arr_object);
                }
                if(v1 < 200 || v1 > 299) {
                    throw new IOException();
                }
                return new zzo(v1, arr_b1, false, SystemClock.elapsedRealtime() - v, list0);
            }
            catch(SocketTimeoutException unused_ex) {
            label_75:
                zzak.zza("socket", zzq0, new zzaf());
                continue;
            }
            catch(MalformedURLException malformedURLException0) {
            label_78:
                String s = zzq0.getUrl();
                throw new RuntimeException((s.length() == 0 ? new String("Bad URL ") : "Bad URL " + s), malformedURLException0);
            }
            catch(IOException iOException0) {
                list1 = list0;
                arr_b = arr_b1;
            }
        label_83:
            if(zzar0 == null) {
                break;
            }
            int v3 = zzar0.getStatusCode();
            zzag.e("Unexpected response code %d for %s", new Object[]{v3, zzq0.getUrl()});
            if(arr_b == null) {
                zzak.zza("network", zzq0, new zzp());
            }
            else {
                zzo zzo0 = new zzo(v3, arr_b, false, SystemClock.elapsedRealtime() - v, list1);
                if(v3 != 401 && v3 != 403) {
                    if(!(v3 < 400 || v3 > 0x1F3)) {
                        throw new zzh(zzo0);
                    }
                    throw new zzac(zzo0);
                }
                zzak.zza("auth", zzq0, new zzb(zzo0));
            }
        }
        throw new zzr(iOException0);
    }
}

