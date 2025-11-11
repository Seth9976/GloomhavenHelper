package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@KeepForSdk
@VisibleForTesting
public final class JsonUtils {
    private static final Pattern zzhd;
    private static final Pattern zzhe;

    static {
        JsonUtils.zzhd = Pattern.compile("\\\\.");
        JsonUtils.zzhe = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    @KeepForSdk
    public static boolean areJsonValuesEquivalent(Object object0, Object object1) {
        if(object0 == null && object1 == null) {
            return true;
        }
        if(object0 != null && object1 != null) {
            if(object0 instanceof JSONObject && object1 instanceof JSONObject) {
                JSONObject jSONObject0 = (JSONObject)object0;
                JSONObject jSONObject1 = (JSONObject)object1;
                if(jSONObject0.length() != jSONObject1.length()) {
                    return false;
                }
                Iterator iterator0 = jSONObject0.keys();
                while(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    String s = (String)object2;
                    if(!jSONObject1.has(s)) {
                        return false;
                    }
                    try {
                        if(!JsonUtils.areJsonValuesEquivalent(jSONObject0.get(s), jSONObject1.get(s))) {
                            return false;
                        }
                    }
                    catch(JSONException unused_ex) {
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
                return true;
            }
            if(object0 instanceof JSONArray && object1 instanceof JSONArray) {
                JSONArray jSONArray0 = (JSONArray)object0;
                JSONArray jSONArray1 = (JSONArray)object1;
                if(jSONArray0.length() != jSONArray1.length()) {
                    return false;
                }
                for(int v = 0; v < jSONArray0.length(); ++v) {
                    try {
                        if(!JsonUtils.areJsonValuesEquivalent(jSONArray0.get(v), jSONArray1.get(v))) {
                            return false;
                        }
                    }
                    catch(JSONException unused_ex) {
                        return false;
                    }
                }
                return true;
            }
            return object0.equals(object1);
        }
        return false;
    }

    @KeepForSdk
    public static String escapeString(String s) {
        if(!TextUtils.isEmpty(s)) {
            Matcher matcher0 = JsonUtils.zzhe.matcher(s);
            StringBuffer stringBuffer0 = null;
            while(matcher0.find()) {
                if(stringBuffer0 == null) {
                    stringBuffer0 = new StringBuffer();
                }
                switch(matcher0.group().charAt(0)) {
                    case 8: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\b");
                        break;
                    }
                    case 9: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\t");
                        break;
                    }
                    case 10: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\n");
                        break;
                    }
                    case 12: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\f");
                        break;
                    }
                    case 13: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\r");
                        break;
                    }
                    case 34: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\\\\"");
                        break;
                    }
                    case 0x2F: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\/");
                        break;
                    }
                    case 92: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\\\\\");
                    }
                }
            }
            if(stringBuffer0 == null) {
                return s;
            }
            matcher0.appendTail(stringBuffer0);
            return stringBuffer0.toString();
        }
        return s;
    }

    @KeepForSdk
    public static String unescapeString(String s) {
        if(!TextUtils.isEmpty(s)) {
            String s1 = zzd.unescape(s);
            Matcher matcher0 = JsonUtils.zzhd.matcher(s1);
            StringBuffer stringBuffer0 = null;
            while(matcher0.find()) {
                if(stringBuffer0 == null) {
                    stringBuffer0 = new StringBuffer();
                }
                switch(matcher0.group().charAt(1)) {
                    case 34: {
                        matcher0.appendReplacement(stringBuffer0, "\"");
                        break;
                    }
                    case 0x2F: {
                        matcher0.appendReplacement(stringBuffer0, "/");
                        break;
                    }
                    case 92: {
                        matcher0.appendReplacement(stringBuffer0, "\\\\");
                        break;
                    }
                    case 98: {
                        matcher0.appendReplacement(stringBuffer0, "\b");
                        break;
                    }
                    case 102: {
                        matcher0.appendReplacement(stringBuffer0, "\f");
                        break;
                    }
                    case 110: {
                        matcher0.appendReplacement(stringBuffer0, "\n");
                        break;
                    }
                    case 0x72: {
                        matcher0.appendReplacement(stringBuffer0, "\r");
                        break;
                    }
                    case 0x74: {
                        matcher0.appendReplacement(stringBuffer0, "\t");
                        break;
                    }
                    default: {
                        throw new IllegalStateException("Found an escaped character that should never be.");
                    }
                }
            }
            if(stringBuffer0 == null) {
                return s1;
            }
            matcher0.appendTail(stringBuffer0);
            return stringBuffer0.toString();
        }
        return s;
    }
}

