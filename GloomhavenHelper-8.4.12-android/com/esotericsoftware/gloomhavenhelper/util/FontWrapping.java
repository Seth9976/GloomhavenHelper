package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;
import com.badlogic.gdx.utils.Array;

public class FontWrapping {
    public static class Japanese {
        private static boolean doNotSplit(int v) {
            if(v < 0x7F) {
                return true;
            }
            switch(v) {
                case 0x2014: 
                case 0x2025: 
                case 8230: 
                case 0x3033: 
                case 0x3034: 
                case 0x3035: 
                case 0xFF05: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        public static int getWrapIndex(Array array0, int v) {
            for(int v1 = v - 1; v1 >= 1; --v1) {
                int v2 = ((Glyph)array0.get(v1)).id;
                if(Japanese.legalAtStart(v2)) {
                    int v3 = ((Glyph)array0.get(v1 - 1)).id;
                    if(Japanese.legalAtEnd(v3) && (!Japanese.doNotSplit(v2) || !Japanese.doNotSplit(v3) || Character.isWhitespace(v2))) {
                        return v1;
                    }
                }
            }
            return v;
        }

        private static boolean legalAtEnd(int v) {
            switch(v) {
                case 34: 
                case 40: 
                case 91: 
                case 0xAB: 
                case 0x2018: 
                case 0x3008: 
                case 0x300A: 
                case 0x300C: 
                case 0x300E: 
                case 0x3010: 
                case 0x3014: 
                case 0x3016: 
                case 0x3018: 
                case 0x301D: 
                case 0xFF5B: 
                case 0xFF5F: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }

        private static boolean legalAtStart(int v) {
            if(v != 33 && v != 34 && (v != 58 && v != 59) && (v != 0x2047 && v != 0x2048 && v != 0x2049) && (v != 0x3001 && v != 0x3002) && (v != 0x3095 && v != 0x3096) && (v != 0x30A0 && v != 0x30A1) && (v != 0x30F5 && v != 0x30F6) && (v != 0x30FB && v != 0x30FC && v != 0x30FD && v != 0x30FE)) {
                switch(v) {
                    case 41: 
                    case 44: 
                    case 46: 
                    case 0x3F: 
                    case 93: 
                    case 0xBB: 
                    case 0x2010: 
                    case 0x2013: 
                    case 0x2019: 
                    case 0x203C: 
                    case 0x3005: 
                    case 0x3009: 
                    case 0x300B: 
                    case 0x300D: 
                    case 0x300F: 
                    case 0x3011: 
                    case 0x3015: 
                    case 0x3017: 
                    case 0x3019: 
                    case 0x301C: 
                    case 0x301F: 
                    case 0x303B: 
                    case 0x3041: 
                    case 0x3043: 
                    case 0x3045: 
                    case 0x3047: 
                    case 0x3049: 
                    case 0x3063: 
                    case 0x3083: 
                    case 0x3085: 
                    case 0x3087: 
                    case 0x308E: 
                    case 0x30A3: 
                    case 0x30A5: 
                    case 0x30A7: 
                    case 0x30A9: 
                    case 0x30C3: 
                    case 0x30E3: 
                    case 0x30E5: 
                    case 0x30E7: 
                    case 0x30EE: 
                    case 0x31F0: 
                    case 0x31F1: 
                    case 0x31F2: 
                    case 0x31F3: 
                    case 0x31F4: 
                    case 0x31F5: 
                    case 0x31F6: 
                    case 0x31F7: 
                    case 0x31F8: 
                    case 0x31F9: 
                    case 0x31FA: 
                    case 0x31FB: 
                    case 0x31FC: 
                    case 0x31FD: 
                    case 0x31FE: 
                    case 0x31FF: 
                    case 0xFF5D: 
                    case 0xFF60: {
                        break;
                    }
                    default: {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class Korean {
        public static int getWrapIndex(Array array0, int v) {
            for(int v1 = v - 1; v1 >= 1; --v1) {
                int v2 = ((Glyph)array0.get(v1)).id;
                if(Korean.legalAtStart(v2)) {
                    int v3 = ((Glyph)array0.get(v1 - 1)).id;
                    if(Korean.legalAtEnd(v3) && (v2 >= 0x7F || v3 >= 0x7F || Character.isWhitespace(v2))) {
                        return v1;
                    }
                }
            }
            return v;
        }

        private static boolean legalAtEnd(int v) {
            switch(v) {
                case 0x20: 
                case 34: 
                case 35: 
                case 36: 
                case 40: 
                case 91: 
                case 92: 
                case 0x7B: 
                case 0xA3: 
                case 0xA5: 
                case 0x2018: 
                case 0x3005: 
                case 0x3007: 
                case 0x3009: 
                case 0x300B: 
                case 0x300D: 
                case 0x3014: 
                case 0xFF04: 
                case 0xFF08: 
                case 0xFF3B: 
                case 0xFF5B: 
                case 0xFF60: 
                case 0xFFE5: 
                case 0xFFE6: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }

        private static boolean legalAtStart(int v) {
            switch(v) {
                case 33: 
                case 34: 
                case 37: 
                case 41: 
                case 44: 
                case 46: 
                case 58: 
                case 59: 
                case 0x3F: 
                case 93: 
                case 0x7D: 
                case 0xA2: 
                case 0xB0: 
                case 0x2019: 
                case 0x2020: 
                case 0x2021: 
                case 0x2103: 
                case 0x3006: 
                case 0x3008: 
                case 0x300A: 
                case 0x300C: 
                case 0x300E: 
                case 0x3015: 
                case 0xFF01: 
                case 0xFF05: 
                case 0xFF09: 
                case 0xFF0C: 
                case 0xFF0E: 
                case 0xFF1A: 
                case 0xFF1B: 
                case 0xFF1F: 
                case 0xFF3D: 
                case 0xFF5D: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }
    }

}

