package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class HexDumpUtils {
    @KeepForSdk
    public static String dump(byte[] arr_b, int v, int v1, boolean z) {
        int v2 = 57;
        if(arr_b != null && arr_b.length != 0 && v >= 0 && v1 > 0 && v + v1 <= arr_b.length) {
            if(z) {
                v2 = 75;
            }
            StringBuilder stringBuilder0 = new StringBuilder(v2 * ((v1 + 15) / 16));
            int v3 = v;
            int v4 = v1;
            int v5 = 0;
            int v6 = 0;
            while(v4 > 0) {
                switch(v5) {
                    case 0: {
                        if(v1 < 0x10000) {
                            stringBuilder0.append(String.format("%04X:", v3));
                        }
                        else {
                            stringBuilder0.append(String.format("%08X:", v3));
                        }
                        v6 = v3;
                        break;
                    }
                    case 8: {
                        stringBuilder0.append(" -");
                    }
                }
                stringBuilder0.append(String.format(" %02X", ((int)(arr_b[v3] & 0xFF))));
                --v4;
                ++v5;
                if(z && (v5 == 16 || v4 == 0)) {
                    int v7 = 16 - v5;
                    if(v7 > 0) {
                        for(int v8 = 0; v8 < v7; ++v8) {
                            stringBuilder0.append("   ");
                        }
                    }
                    if(v7 >= 8) {
                        stringBuilder0.append("  ");
                    }
                    stringBuilder0.append("  ");
                    for(int v9 = 0; v9 < v5; ++v9) {
                        int v10 = (char)arr_b[v6 + v9];
                        if(v10 < 0x20 || v10 > 0x7E) {
                            v10 = 46;
                        }
                        stringBuilder0.append(((char)v10));
                    }
                }
                if(v5 == 16 || v4 == 0) {
                    stringBuilder0.append('\n');
                    v5 = 0;
                }
                ++v3;
            }
            return stringBuilder0.toString();
        }
        return null;
    }
}

