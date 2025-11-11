package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

@KeepForSdk
public class HttpUtils {
    private static final Pattern zzha;
    private static final Pattern zzhb;
    private static final Pattern zzhc;

    static {
        HttpUtils.zzha = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
        HttpUtils.zzhb = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
        HttpUtils.zzhc = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
    }

    private static String decode(String s, String s1) {
        try {
            if(s1 == null) {
                s1 = "ISO-8859-1";
            }
            return URLDecoder.decode(s, s1);
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new IllegalArgumentException(unsupportedEncodingException0);
        }
    }

    @KeepForSdk
    public static Map parse(URI uRI0, String s) {
        Map map0 = Collections.emptyMap();
        String s1 = uRI0.getRawQuery();
        if(s1 != null && s1.length() > 0) {
            map0 = new HashMap();
            Scanner scanner0 = new Scanner(s1);
            scanner0.useDelimiter("&");
            while(scanner0.hasNext()) {
                String[] arr_s = scanner0.next().split("=");
                if(arr_s.length == 0 || arr_s.length > 2) {
                    throw new IllegalArgumentException("bad parameter");
                }
                map0.put(HttpUtils.decode(arr_s[0], s), (arr_s.length == 2 ? HttpUtils.decode(arr_s[1], s) : null));
            }
        }
        return map0;
    }
}

