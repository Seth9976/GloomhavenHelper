package com.google.android.gms.internal.ads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class zzas {
    public static zzd zzb(zzo zzo0) {
        long v9;
        long v8;
        boolean z;
        long v5;
        long v3;
        long v = System.currentTimeMillis();
        Map map0 = zzo0.zzab;
        String s = (String)map0.get("Date");
        long v1 = s == null ? 0L : zzas.zzf(s);
        String s1 = (String)map0.get("Cache-Control");
        int v2 = 0;
        if(s1 == null) {
            v3 = 0L;
            v5 = 0L;
            z = false;
        }
        else {
            String[] arr_s = s1.split(",", 0);
            v3 = 0L;
            int v4 = 0;
            v5 = 0L;
            while(v2 < arr_s.length) {
                String s2 = arr_s[v2].trim();
                if(!s2.equals("no-cache") && !s2.equals("no-store")) {
                    if(s2.startsWith("max-age=")) {
                        try {
                            v3 = Long.parseLong(s2.substring(8));
                            ++v2;
                            continue;
                        label_17:
                            if(s2.startsWith("stale-while-revalidate=")) {
                                v5 = Long.parseLong(s2.substring(23));
                            }
                            else if(s2.equals("must-revalidate") || s2.equals("proxy-revalidate")) {
                                v4 = 1;
                            }
                        }
                        catch(Exception unused_ex) {
                        }
                    }
                    else {
                        goto label_17;
                    }
                    ++v2;
                    continue;
                }
                return null;
            }
            v2 = v4;
            z = true;
        }
        String s3 = (String)map0.get("Expires");
        long v6 = s3 == null ? 0L : zzas.zzf(s3);
        String s4 = (String)map0.get("Last-Modified");
        long v7 = s4 == null ? 0L : zzas.zzf(s4);
        String s5 = (String)map0.get("ETag");
        if(z) {
            v8 = v + v3 * 1000L;
            v9 = v2 == 0 ? v5 * 1000L + v8 : v8;
        }
        else if(v1 <= 0L || v6 < v1) {
            v8 = 0L;
            v9 = 0L;
        }
        else {
            v9 = v6 - v1 + v;
            v8 = v9;
        }
        zzd zzd0 = new zzd();
        zzd0.data = zzo0.data;
        zzd0.zzg = s5;
        zzd0.zzk = v8;
        zzd0.zzj = v9;
        zzd0.zzh = v1;
        zzd0.zzi = v7;
        zzd0.zzl = map0;
        zzd0.zzm = zzo0.allHeaders;
        return zzd0;
    }

    static String zzb(long v) {
        return zzas.zzr().format(new Date(v));
    }

    private static long zzf(String s) {
        try {
            return zzas.zzr().parse(s).getTime();
        }
        catch(ParseException parseException0) {
            zzag.zza(parseException0, "Unable to parse dateStr: %s, falling back to 0", new Object[]{s});
            return 0L;
        }
    }

    private static SimpleDateFormat zzr() {
        SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat0.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat0;
    }
}

