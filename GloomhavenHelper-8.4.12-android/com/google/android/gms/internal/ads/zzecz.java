package com.google.android.gms.internal.ads;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class zzecz {
    public static String zza(zzeda zzeda0) {
        if(zzeda0 == null) {
            return "";
        }
        StringBuffer stringBuffer0 = new StringBuffer();
        try {
            zzecz.zza(null, zzeda0, new StringBuffer(), stringBuffer0);
            return stringBuffer0.toString();
        }
        catch(IllegalAccessException illegalAccessException0) {
            String s = illegalAccessException0.getMessage();
            return s.length() == 0 ? new String("Error printing proto: ") : "Error printing proto: " + s;
        }
        catch(InvocationTargetException invocationTargetException0) {
            String s1 = invocationTargetException0.getMessage();
            return s1.length() == 0 ? new String("Error printing proto: ") : "Error printing proto: " + s1;
        }
    }

    private static void zza(String s, Object object0, StringBuffer stringBuffer0, StringBuffer stringBuffer1) throws IllegalAccessException, InvocationTargetException {
        Method method1;
        String s3;
        if(object0 != null) {
            if(object0 instanceof zzeda) {
                int v = stringBuffer0.length();
                if(s != null) {
                    stringBuffer1.append(stringBuffer0);
                    stringBuffer1.append(zzecz.zzhn(s));
                    stringBuffer1.append(" <\n");
                    stringBuffer0.append("  ");
                }
                Class class0 = object0.getClass();
                Field[] arr_field = class0.getFields();
                for(int v1 = 0; v1 < arr_field.length; ++v1) {
                    Field field0 = arr_field[v1];
                    int v2 = field0.getModifiers();
                    String s1 = field0.getName();
                    if(!"cachedSize".equals(s1) && (v2 & 1) == 1 && (v2 & 8) != 8 && !s1.startsWith("_") && !s1.endsWith("_")) {
                        Class class1 = field0.getType();
                        Object object1 = field0.get(object0);
                        if(!class1.isArray() || class1.getComponentType() == Byte.TYPE) {
                            zzecz.zza(s1, object1, stringBuffer0, stringBuffer1);
                        }
                        else {
                            int v3 = object1 == null ? 0 : Array.getLength(object1);
                            for(int v4 = 0; v4 < v3; ++v4) {
                                zzecz.zza(s1, Array.get(object1, v4), stringBuffer0, stringBuffer1);
                            }
                        }
                    }
                }
                Method[] arr_method = class0.getMethods();
                for(int v5 = 0; v5 < arr_method.length; ++v5) {
                    String s2 = arr_method[v5].getName();
                    if(s2.startsWith("set")) {
                        try {
                            s3 = s2.substring(3);
                            String s4 = String.valueOf(s3);
                            Method method0 = class0.getMethod((s4.length() == 0 ? new String("has") : "has" + s4));
                        }
                        catch(NoSuchMethodException unused_ex) {
                            continue;
                        }
                        if(((Boolean)method0.invoke(object0, new Object[0])).booleanValue()) {
                            try {
                                String s5 = String.valueOf(s3);
                                method1 = class0.getMethod((s5.length() == 0 ? new String("get") : "get" + s5));
                            }
                            catch(NoSuchMethodException unused_ex) {
                                continue;
                            }
                            zzecz.zza(s3, method1.invoke(object0), stringBuffer0, stringBuffer1);
                        }
                    }
                }
                if(s != null) {
                    stringBuffer0.setLength(v);
                    stringBuffer1.append(stringBuffer0);
                    stringBuffer1.append(">\n");
                }
                return;
            }
            String s6 = zzecz.zzhn(s);
            stringBuffer1.append(stringBuffer0);
            stringBuffer1.append(s6);
            stringBuffer1.append(": ");
            if(object0 instanceof String) {
                String s7 = (String)object0;
                if(!s7.startsWith("http") && s7.length() > 200) {
                    s7 = s7.substring(0, 200) + "[...]";
                }
                int v6 = s7.length();
                StringBuilder stringBuilder0 = new StringBuilder(v6);
                for(int v7 = 0; v7 < v6; ++v7) {
                    int v8 = s7.charAt(v7);
                    if(v8 < 0x20 || v8 > 0x7E || v8 == 34 || v8 == 39) {
                        stringBuilder0.append(String.format("\\u%04x", v8));
                    }
                    else {
                        stringBuilder0.append(((char)v8));
                    }
                }
                stringBuffer1.append("\"");
                stringBuffer1.append(stringBuilder0.toString());
                stringBuffer1.append("\"");
            }
            else if(!(object0 instanceof byte[])) {
                stringBuffer1.append(object0);
            }
            else if(((byte[])object0) == null) {
                stringBuffer1.append("\"\"");
            }
            else {
                stringBuffer1.append('\"');
                for(int v9 = 0; v9 < ((byte[])object0).length; ++v9) {
                    int v10 = ((byte[])object0)[v9] & 0xFF;
                    if(v10 == 34 || v10 == 92) {
                        stringBuffer1.append('\\');
                        stringBuffer1.append(((char)v10));
                    }
                    else if(v10 < 0x20 || v10 >= 0x7F) {
                        stringBuffer1.append(String.format("\\%03o", v10));
                    }
                    else {
                        stringBuffer1.append(((char)v10));
                    }
                }
                stringBuffer1.append('\"');
            }
            stringBuffer1.append("\n");
        }
    }

    private static String zzhn(String s) {
        StringBuffer stringBuffer0 = new StringBuffer();
        for(int v = 0; v < s.length(); ++v) {
            int v1 = s.charAt(v);
            if(v == 0) {
                stringBuffer0.append(Character.toLowerCase(((char)v1)));
            }
            else if(Character.isUpperCase(((char)v1))) {
                stringBuffer0.append('_');
                stringBuffer0.append(Character.toLowerCase(((char)v1)));
            }
            else {
                stringBuffer0.append(((char)v1));
            }
        }
        return stringBuffer0.toString();
    }
}

