package com.badlogic.gdx.utils;

import java.io.UnsupportedEncodingException;

public class Base64Coder {
    public static class CharMap {
        protected final byte[] decodingMap;
        protected final char[] encodingMap;

        public CharMap(char c, char c1) {
            this.encodingMap = new char[0x40];
            this.decodingMap = new byte[0x80];
            int v1 = 0;
            char c2 = 'A';
            while(c2 <= 90) {
                this.encodingMap[v1] = c2;
                c2 = (char)(c2 + 1);
                ++v1;
            }
            char c3 = 'a';
            while(c3 <= 0x7A) {
                this.encodingMap[v1] = c3;
                c3 = (char)(c3 + 1);
                ++v1;
            }
            char c4 = '0';
            while(c4 <= 57) {
                this.encodingMap[v1] = c4;
                c4 = (char)(c4 + 1);
                ++v1;
            }
            this.encodingMap[v1] = c;
            this.encodingMap[v1 + 1] = c1;
            for(int v2 = 0; true; ++v2) {
                byte[] arr_b = this.decodingMap;
                if(v2 >= arr_b.length) {
                    break;
                }
                arr_b[v2] = -1;
            }
            for(int v = 0; v < 0x40; ++v) {
                this.decodingMap[this.encodingMap[v]] = (byte)v;
            }
        }

        public byte[] getDecodingMap() {
            return this.decodingMap;
        }

        public char[] getEncodingMap() {
            return this.encodingMap;
        }
    }

    public static final CharMap regularMap = null;
    private static final String systemLineSeparator = "\n";
    public static final CharMap urlsafeMap;

    static {
        Base64Coder.regularMap = new CharMap('+', '/');
        Base64Coder.urlsafeMap = new CharMap('-', '_');
    }

    public static byte[] decode(String s) {
        return Base64Coder.decode(s.toCharArray());
    }

    public static byte[] decode(String s, CharMap base64Coder$CharMap0) {
        return Base64Coder.decode(s.toCharArray(), base64Coder$CharMap0);
    }

    public static byte[] decode(char[] arr_c) {
        return Base64Coder.decode(arr_c, 0, arr_c.length, Base64Coder.regularMap.decodingMap);
    }

    public static byte[] decode(char[] arr_c, int v, int v1, CharMap base64Coder$CharMap0) {
        return Base64Coder.decode(arr_c, v, v1, base64Coder$CharMap0.decodingMap);
    }

    public static byte[] decode(char[] arr_c, int v, int v1, byte[] arr_b) {
        int v15;
        int v9;
        int v8;
        if(v1 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while(v1 > 0 && arr_c[v + v1 - 1] == 61) {
            --v1;
        }
        int v2 = v1 * 3 / 4;
        byte[] arr_b1 = new byte[v2];
        int v3 = v1 + v;
        int v4 = 0;
        while(v < v3) {
            int v5 = arr_c[v];
            int v6 = arr_c[v + 1];
            int v7 = 65;
            if(v + 2 < v3) {
                v8 = v + 3;
                v9 = arr_c[v + 2];
            }
            else {
                v8 = v + 2;
                v9 = 65;
            }
            if(v8 < v3) {
                int v10 = arr_c[v8];
                ++v8;
                v7 = v10;
            }
            if(v5 > 0x7F || v6 > 0x7F || v9 > 0x7F || v7 > 0x7F) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int v11 = arr_b[v5];
            int v12 = arr_b[v6];
            int v13 = arr_b[v9];
            int v14 = arr_b[v7];
            if(v11 < 0 || v12 < 0 || v13 < 0 || v14 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            arr_b1[v4] = (byte)(v11 << 2 | v12 >>> 4);
            if(v4 + 1 < v2) {
                v15 = v4 + 2;
                arr_b1[v4 + 1] = (byte)((v12 & 15) << 4 | v13 >>> 2);
            }
            else {
                v15 = v4 + 1;
            }
            if(v15 < v2) {
                v4 = v15 + 1;
                arr_b1[v15] = (byte)((v13 & 3) << 6 | v14);
            }
            else {
                v4 = v15;
            }
            v = v8;
        }
        return arr_b1;
    }

    public static byte[] decode(char[] arr_c, CharMap base64Coder$CharMap0) {
        return Base64Coder.decode(arr_c, 0, arr_c.length, base64Coder$CharMap0);
    }

    public static byte[] decode(char[] arr_c, byte[] arr_b) {
        return Base64Coder.decode(arr_c, 0, arr_c.length, arr_b);
    }

    public static byte[] decodeLines(String s) {
        return Base64Coder.decodeLines(s, Base64Coder.regularMap.decodingMap);
    }

    public static byte[] decodeLines(String s, CharMap base64Coder$CharMap0) {
        return Base64Coder.decodeLines(s, base64Coder$CharMap0.decodingMap);
    }

    public static byte[] decodeLines(String s, byte[] arr_b) {
        char[] arr_c = new char[s.length()];
        int v1 = 0;
        for(int v = 0; v < s.length(); ++v) {
            char c = s.charAt(v);
            if(c != 9 && c != 10 && c != 13 && c != 0x20) {
                arr_c[v1] = c;
                ++v1;
            }
        }
        return Base64Coder.decode(arr_c, 0, v1, arr_b);
    }

    public static String decodeString(String s) {
        return Base64Coder.decodeString(s, false);
    }

    public static String decodeString(String s, boolean z) {
        char[] arr_c = s.toCharArray();
        return z ? new String(Base64Coder.decode(arr_c, Base64Coder.urlsafeMap.decodingMap)) : new String(Base64Coder.decode(arr_c, Base64Coder.regularMap.decodingMap));
    }

    public static char[] encode(byte[] arr_b) {
        return Base64Coder.encode(arr_b, Base64Coder.regularMap.encodingMap);
    }

    public static char[] encode(byte[] arr_b, int v) {
        return Base64Coder.encode(arr_b, 0, v, Base64Coder.regularMap.encodingMap);
    }

    public static char[] encode(byte[] arr_b, int v, int v1, CharMap base64Coder$CharMap0) {
        return Base64Coder.encode(arr_b, v, v1, base64Coder$CharMap0.encodingMap);
    }

    public static char[] encode(byte[] arr_b, int v, int v1, char[] arr_c) {
        int v9;
        int v8;
        int v7;
        int v6;
        int v2 = (v1 * 4 + 2) / 3;
        char[] arr_c1 = new char[(v1 + 2) / 3 * 4];
        int v3 = v1 + v;
        int v4 = 0;
        while(v < v3) {
            int v5 = arr_b[v] & 0xFF;
            if(v + 1 < v3) {
                v6 = v + 2;
                v7 = arr_b[v + 1] & 0xFF;
            }
            else {
                v6 = v + 1;
                v7 = 0;
            }
            if(v6 < v3) {
                v8 = v6 + 1;
                v9 = arr_b[v6] & 0xFF;
            }
            else {
                v8 = v6;
                v9 = 0;
            }
            arr_c1[v4] = arr_c[v5 >>> 2];
            arr_c1[v4 + 1] = arr_c[(v5 & 3) << 4 | v7 >>> 4];
            char c = '=';
            arr_c1[v4 + 2] = v4 + 2 >= v2 ? '=' : arr_c[(v7 & 15) << 2 | v9 >>> 6];
            if(v4 + 3 < v2) {
                c = arr_c[v9 & 0x3F];
            }
            arr_c1[v4 + 3] = c;
            v4 += 4;
            v = v8;
        }
        return arr_c1;
    }

    public static char[] encode(byte[] arr_b, CharMap base64Coder$CharMap0) {
        return Base64Coder.encode(arr_b, 0, arr_b.length, base64Coder$CharMap0);
    }

    public static char[] encode(byte[] arr_b, char[] arr_c) {
        return Base64Coder.encode(arr_b, 0, arr_b.length, arr_c);
    }

    public static String encodeLines(byte[] arr_b) {
        return Base64Coder.encodeLines(arr_b, 0, arr_b.length, 76, "\n", Base64Coder.regularMap.encodingMap);
    }

    public static String encodeLines(byte[] arr_b, int v, int v1, int v2, String s, CharMap base64Coder$CharMap0) {
        return Base64Coder.encodeLines(arr_b, v, v1, v2, s, base64Coder$CharMap0.encodingMap);
    }

    public static String encodeLines(byte[] arr_b, int v, int v1, int v2, String s, char[] arr_c) {
        int v3 = v2 * 3 / 4;
        if(v3 <= 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder0 = new StringBuilder((v1 + 2) / 3 * 4 + (v1 + v3 - 1) / v3 * s.length());
        for(int v4 = 0; v4 < v1; v4 += v5) {
            int v5 = Math.min(v1 - v4, v3);
            stringBuilder0.append(Base64Coder.encode(arr_b, v + v4, v5, arr_c));
            stringBuilder0.append(s);
        }
        return "";
    }

    public static String encodeString(String s) {
        return Base64Coder.encodeString(s, false);
    }

    public static String encodeString(String s, boolean z) {
        try {
            return new String(Base64Coder.encode(s.getBytes("UTF-8"), (z ? Base64Coder.urlsafeMap : Base64Coder.regularMap).encodingMap));
        }
        catch(UnsupportedEncodingException unused_ex) {
            return "";
        }
    }
}

