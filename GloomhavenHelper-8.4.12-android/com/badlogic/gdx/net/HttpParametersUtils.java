package com.badlogic.gdx.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public final class HttpParametersUtils {
    public static String defaultEncoding = "UTF-8";
    public static String nameValueSeparator = "=";
    public static String parameterSeparator = "&";

    static {
    }

    public static String convertHttpParameters(Map map0) {
        Set set0 = map0.keySet();
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object0: set0) {
            stringBuilder0.append(HttpParametersUtils.encode(((String)object0), "UTF-8"));
            stringBuilder0.append("=");
            stringBuilder0.append(HttpParametersUtils.encode(((String)map0.get(((String)object0))), "UTF-8"));
            stringBuilder0.append("&");
        }
        if(stringBuilder0.length() > 0) {
            stringBuilder0.deleteCharAt(stringBuilder0.length() - 1);
        }
        return stringBuilder0.toString();
    }

    private static String encode(String s, String s1) {
        try {
            return URLEncoder.encode(s, s1);
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new IllegalArgumentException(unsupportedEncodingException0);
        }
    }
}

