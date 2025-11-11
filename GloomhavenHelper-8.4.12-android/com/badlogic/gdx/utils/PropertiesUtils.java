package com.badlogic.gdx.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class PropertiesUtils {
    private static final int CONTINUE = 3;
    private static final int IGNORE = 5;
    private static final int KEY_DONE = 4;
    private static final String LINE_SEPARATOR = "\n";
    private static final int NONE = 0;
    private static final int SLASH = 1;
    private static final int UNICODE = 2;

    private static void dumpString(StringBuilder stringBuilder0, String s, boolean z, boolean z1) {
        int v = s.length();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            if(v2 <= 61 || v2 >= 0x7F) {
                switch(v2) {
                    case 9: {
                        stringBuilder0.append("\\t");
                        break;
                    }
                    case 10: {
                        stringBuilder0.append("\\n");
                        break;
                    }
                    case 12: {
                        stringBuilder0.append("\\f");
                        break;
                    }
                    case 13: {
                        stringBuilder0.append("\\r");
                        break;
                    }
                    case 0x20: {
                        if(v1 == 0 || z) {
                            stringBuilder0.append("\\ ");
                        }
                        else {
                            stringBuilder0.append(' ');
                        }
                        break;
                    }
                    case 33: 
                    case 35: 
                    case 58: 
                    case 61: {
                        stringBuilder0.append('\\').append(((char)v2));
                        break;
                    }
                    default: {
                        if((v2 < 0x20 || v2 > 0x7E) && z1) {
                            String s2 = Integer.toHexString(v2);
                            stringBuilder0.append("\\u");
                            for(int v3 = 0; v3 < 4 - s2.length(); ++v3) {
                                stringBuilder0.append('0');
                            }
                            stringBuilder0.append(s2);
                        }
                        else {
                            stringBuilder0.append(((char)v2));
                        }
                    }
                }
            }
            else {
                String s1 = v2 == 92 ? "\\\\" : Character.valueOf(((char)v2));
                stringBuilder0.append(s1);
            }
        }
    }

    public static void load(ObjectMap objectMap0, Reader reader0) throws IOException {
        if(objectMap0 == null) {
            throw new NullPointerException("properties cannot be null");
        }
        if(reader0 != null) {
            BufferedReader bufferedReader0 = new BufferedReader(reader0);
            char[] arr_c = new char[40];
            int v = 0;
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;
            int v4 = -1;
            boolean z = true;
            while(true) {
                int v5 = bufferedReader0.read();
                if(v5 == -1) {
                    if(v1 == 2 && v2 <= 4) {
                        throw new IllegalArgumentException("Invalid Unicode sequence: expected format \\uxxxx");
                    }
                    if(v4 == -1 && v > 0) {
                        v4 = v;
                    }
                    if(v4 >= 0) {
                        String s = new String(arr_c, 0, v);
                        objectMap0.put(s.substring(0, v4), (v1 == 1 ? s.substring(v4) + "\u0000" : s.substring(v4)));
                    }
                    return;
                }
                char c = (char)v5;
                if(v == arr_c.length) {
                    char[] arr_c1 = new char[arr_c.length * 2];
                    System.arraycopy(arr_c, 0, arr_c1, 0, v);
                    arr_c = arr_c1;
                }
                if(v1 == 2) {
                    int v6 = Character.digit(c, 16);
                    if(v6 >= 0) {
                        v3 = (v3 << 4) + v6;
                        ++v2;
                        if(v2 < 4) {
                            continue;
                        }
                        else {
                            goto label_33;
                        }
                        goto label_32;
                    }
                    else {
                    label_32:
                        if(v2 > 4) {
                        label_33:
                            arr_c[v] = (char)v3;
                            if(c == 10) {
                                ++v;
                                v1 = 0;
                                goto label_42;
                            }
                            else {
                                ++v;
                                v1 = 0;
                                continue;
                            }
                        }
                        throw new IllegalArgumentException("Invalid Unicode sequence: illegal character");
                    }
                }
            label_42:
                if(v1 == 1) {
                    switch(c) {
                        case 10: {
                            v1 = 5;
                            continue;
                        }
                        case 13: {
                            v1 = 3;
                            continue;
                        }
                        case 98: {
                            c = '\b';
                            v1 = 0;
                            break;
                        }
                        case 102: {
                            c = '\f';
                            v1 = 0;
                            break;
                        }
                        case 110: {
                            c = '\n';
                            v1 = 0;
                            break;
                        }
                        case 0x72: {
                            c = '\r';
                            v1 = 0;
                            break;
                        }
                        case 0x74: {
                            c = '\t';
                            v1 = 0;
                            break;
                        }
                        case 0x75: {
                            v1 = 2;
                            v2 = 0;
                            v3 = 0;
                            continue;
                        }
                        default: {
                            v1 = 0;
                        }
                    }
                }
                else {
                    switch(c) {
                        case 10: {
                            if(v1 == 3) {
                                v1 = 5;
                                continue;
                            }
                            goto label_74;
                        }
                        case 13: {
                        label_74:
                            if(v > 0 || v == 0 && v4 == 0) {
                                if(v4 == -1) {
                                    v4 = v;
                                }
                                String s1 = new String(arr_c, 0, v);
                                objectMap0.put(s1.substring(0, v4), s1.substring(v4));
                            }
                            v = 0;
                            v1 = 0;
                            v4 = -1;
                            z = true;
                            continue;
                        }
                        case 33: 
                        case 35: {
                            if(z) {
                                do {
                                    int v7 = bufferedReader0.read();
                                }
                                while(v7 != -1 && ((char)v7) != 13 && ((char)v7) != 10);
                                continue;
                            }
                            goto label_92;
                        }
                        case 58: 
                        case 61: {
                            if(v4 == -1) {
                                v4 = v;
                                v1 = 0;
                                continue;
                            }
                        label_92:
                            if(Character.isSpace(c)) {
                                if(v1 == 3) {
                                    v1 = 5;
                                }
                                if(v == 0 || v == v4 || v1 == 5) {
                                    continue;
                                }
                                if(v4 == -1) {
                                    v1 = 4;
                                    continue;
                                }
                            }
                            if(v1 == 3 || v1 == 5) {
                                v1 = 0;
                            }
                            break;
                        }
                        case 92: {
                            goto label_109;
                        }
                        default: {
                            goto label_92;
                        }
                    }
                }
                if(v1 == 4) {
                    v4 = v;
                    v1 = 0;
                }
                arr_c[v] = c;
                ++v;
                z = false;
                continue;
            label_109:
                if(v1 == 4) {
                    v4 = v;
                }
                v1 = 1;
            }
        }
        throw new NullPointerException("reader cannot be null");
    }

    public static void store(ObjectMap objectMap0, Writer writer0, String s) throws IOException {
        PropertiesUtils.storeImpl(objectMap0, writer0, s, false);
    }

    private static void storeImpl(ObjectMap objectMap0, Writer writer0, String s, boolean z) throws IOException {
        if(s != null) {
            PropertiesUtils.writeComment(writer0, s);
        }
        writer0.write("#");
        writer0.write("Tue Nov 11 17:55:34 CST 2025");
        writer0.write("\n");
        StringBuilder stringBuilder0 = new StringBuilder(200);
        for(Object object0: objectMap0.entries()) {
            PropertiesUtils.dumpString(stringBuilder0, ((String)((Entry)object0).key), true, z);
            stringBuilder0.append('=');
            PropertiesUtils.dumpString(stringBuilder0, ((String)((Entry)object0).value), false, z);
            writer0.write("\n");
            writer0.write("");
            stringBuilder0.setLength(0);
        }
        writer0.flush();
    }

    private static void writeComment(Writer writer0, String s) throws IOException {
        writer0.write("#");
        int v = s.length();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v) {
            int v3 = s.charAt(v1);
            if(v3 > 0xFF || v3 == 10 || v3 == 13) {
                if(v2 != v1) {
                    writer0.write(s.substring(v2, v1));
                }
                if(v3 > 0xFF) {
                    String s1 = Integer.toHexString(v3);
                    writer0.write("\\u");
                    for(int v4 = 0; v4 < 4 - s1.length(); ++v4) {
                        writer0.write(0x30);
                    }
                    writer0.write(s1);
                }
                else {
                    writer0.write("\n");
                    if(v3 == 13 && v1 != v - 1 && s.charAt(v1 + 1) == 10) {
                        ++v1;
                    }
                    if(v1 == v - 1 || s.charAt(v1 + 1) != 35 && s.charAt(v1 + 1) != 33) {
                        writer0.write("#");
                    }
                }
                v2 = v1 + 1;
            }
            ++v1;
        }
        if(v2 != v1) {
            writer0.write(s.substring(v2, v1));
        }
        writer0.write("\n");
    }
}

