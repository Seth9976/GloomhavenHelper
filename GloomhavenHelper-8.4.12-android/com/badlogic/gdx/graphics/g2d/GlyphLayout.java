package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class GlyphLayout implements Poolable {
    public static class GlyphRun implements Poolable {
        public final Color color;
        public Array glyphs;
        public float width;
        public float x;
        public FloatArray xAdvances;
        public float y;

        public GlyphRun() {
            this.glyphs = new Array();
            this.xAdvances = new FloatArray();
            this.color = new Color();
        }

        @Override  // com.badlogic.gdx.utils.Pool$Poolable
        public void reset() {
            this.glyphs.clear();
            this.xAdvances.clear();
            this.width = 0.0f;
        }

        @Override
        public String toString() [...] // 潜在的解密器
    }

    private static final Pool colorPool = null;
    private static final Array colorStack = null;
    private static final float epsilon = 0.0001f;
    private static final Pool glyphRunPool;
    public float height;
    public final Array runs;
    public float width;

    static {
        GlyphLayout.glyphRunPool = Pools.get(GlyphRun.class);
        GlyphLayout.colorPool = Pools.get(Color.class);
        GlyphLayout.colorStack = new Array(4);
    }

    public GlyphLayout() {
        this.runs = new Array(1);
    }

    public GlyphLayout(BitmapFont bitmapFont0, CharSequence charSequence0) {
        this.runs = new Array(1);
        this.setText(bitmapFont0, charSequence0);
    }

    public GlyphLayout(BitmapFont bitmapFont0, CharSequence charSequence0, int v, int v1, Color color0, float f, int v2, boolean z, String s) {
        this.runs = new Array(1);
        this.setText(bitmapFont0, charSequence0, v, v1, color0, f, v2, z, s);
    }

    public GlyphLayout(BitmapFont bitmapFont0, CharSequence charSequence0, Color color0, float f, int v, boolean z) {
        this.runs = new Array(1);
        this.setText(bitmapFont0, charSequence0, color0, f, v, z);
    }

    private void adjustLastGlyph(BitmapFontData bitmapFont$BitmapFontData0, GlyphRun glyphLayout$GlyphRun0) {
        Glyph bitmapFont$Glyph0 = (Glyph)glyphLayout$GlyphRun0.glyphs.peek();
        if(bitmapFont$Glyph0.fixedWidth) {
            return;
        }
        glyphLayout$GlyphRun0.xAdvances.items[glyphLayout$GlyphRun0.xAdvances.size - 1] = ((float)(bitmapFont$Glyph0.width + bitmapFont$Glyph0.xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padRight;
    }

    private int parseColorMarkup(CharSequence charSequence0, int v, int v1, Pool pool0) {
        if(v == v1) {
            return -1;
        }
        int v2 = charSequence0.charAt(v);
        if(v2 != 35) {
            switch(v2) {
                case 91: {
                    return -2;
                }
                case 93: {
                    if(GlyphLayout.colorStack.size > 1) {
                        pool0.free(GlyphLayout.colorStack.pop());
                    }
                    return 0;
                }
                default: {
                    int v4 = v + 1;
                    while(v4 < v1) {
                        if(charSequence0.charAt(v4) != 93) {
                            ++v4;
                            continue;
                        }
                        Color color0 = Colors.get(charSequence0.subSequence(v, v4).toString());
                        if(color0 == null) {
                            return -1;
                        }
                        Color color1 = (Color)pool0.obtain();
                        GlyphLayout.colorStack.add(color1);
                        color1.set(color0);
                        return v4 - v;
                    }
                    return -1;
                }
            }
        }
        int v5 = v + 1;
        int v6 = 0;
        while(v5 < v1) {
            int v7 = charSequence0.charAt(v5);
            if(v7 == 93) {
                if(v5 < v + 2 || v5 > v + 9) {
                    break;
                }
                int v8 = v5 - v;
                if(v8 <= 7) {
                    for(int v3 = 0; v3 < 9 - v8; ++v3) {
                        v6 <<= 4;
                    }
                    v6 |= 0xFF;
                }
                Color color2 = (Color)pool0.obtain();
                GlyphLayout.colorStack.add(color2);
                Color.rgba8888ToColor(color2, v6);
                return v8;
            }
            if(v7 >= 0x30 && v7 <= 57) {
                v6 = v6 * 16 + (v7 - 0x30);
            }
            else if(v7 < 97 || v7 > 102) {
                if(v7 < 65 || v7 > 70) {
                    break;
                }
                v6 = v6 * 16 + (v7 - 55);
            }
            else {
                v6 = v6 * 16 + (v7 - 87);
            }
            ++v5;
        }
        return -1;
    }

    @Override  // com.badlogic.gdx.utils.Pool$Poolable
    public void reset() {
        Pools.get(GlyphRun.class).freeAll(this.runs);
        this.runs.clear();
        this.width = 0.0f;
        this.height = 0.0f;
    }

    public void setText(BitmapFont bitmapFont0, CharSequence charSequence0) {
        this.setText(bitmapFont0, charSequence0, 0, charSequence0.length(), bitmapFont0.getColor(), 0.0f, 8, false, null);
    }

    public void setText(BitmapFont bitmapFont0, CharSequence charSequence0, int v, int v1, Color color0, float f, int v2, boolean z, @Null String s) {
        int v27;
        float f16;
        float[] arr_f1;
        Glyph bitmapFont$Glyph3;
        Glyph bitmapFont$Glyph1;
        float f5;
        int v11;
        int v10;
        float f4;
        boolean z3;
        Color color3;
        int v8;
        int v7;
        Color color1;
        boolean z1;
        Array array0 = this.runs;
        GlyphLayout.glyphRunPool.freeAll(array0);
        array0.clear();
        BitmapFontData bitmapFont$BitmapFontData0 = bitmapFont0.data;
        int v3 = v;
        if(v3 == v1) {
            this.width = 0.0f;
            this.height = bitmapFont$BitmapFontData0.capHeight;
            return;
        }
        if(s == null) {
            z1 = f <= bitmapFont$BitmapFontData0.spaceXadvance * 3.0f ? false : z;
        }
        else {
            z1 = true;
        }
        boolean z2 = bitmapFont$BitmapFontData0.markupEnabled;
        if(z2) {
            int v4 = GlyphLayout.colorStack.size;
            for(int v5 = 1; v5 < v4; ++v5) {
                Object object0 = GlyphLayout.colorStack.get(v5);
                GlyphLayout.colorPool.free(object0);
            }
            GlyphLayout.colorStack.clear();
            color1 = color0;
            GlyphLayout.colorStack.add(color1);
        }
        else {
            color1 = color0;
        }
        float f1 = bitmapFont$BitmapFontData0.down;
        int v6 = v3;
        Color color2 = color1;
        Glyph bitmapFont$Glyph0 = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while(true) {
            if(v3 == v1) {
                if(v6 == v1) {
                    goto label_96;
                }
                v7 = v3;
                v8 = v1;
                color3 = color2;
                z3 = false;
            }
            else {
                v7 = v3 + 1;
                switch(charSequence0.charAt(v3)) {
                    case 10: {
                        v8 = v7 - 1;
                        color3 = color2;
                        z3 = true;
                        break;
                    }
                    case 91: {
                        if(z2) {
                            int v9 = this.parseColorMarkup(charSequence0, v7, v1, GlyphLayout.colorPool);
                            if(v9 >= 0) {
                                v8 = v7 - 1;
                                v7 += v9 + 1;
                                color3 = (Color)GlyphLayout.colorStack.peek();
                                z3 = false;
                                break;
                            }
                            else if(v9 == -2) {
                                v3 = v7 + 1;
                                continue;
                            }
                        }
                        color3 = color2;
                        z3 = false;
                        v8 = -1;
                        break;
                    }
                    default: {
                        color3 = color2;
                        z3 = false;
                        v8 = -1;
                        break;
                    }
                }
            }
            if(v8 == -1) {
                f5 = f1;
                v10 = v7;
                v27 = v6;
            }
            else {
                if(v8 == v6) {
                label_211:
                    f4 = f2;
                    f5 = f1;
                    v10 = v7;
                    v11 = v6;
                }
                else {
                    GlyphRun glyphLayout$GlyphRun0 = (GlyphRun)GlyphLayout.glyphRunPool.obtain();
                    glyphLayout$GlyphRun0.color.set(color1);
                    f4 = f2;
                    v10 = v7;
                    v11 = v6;
                    f5 = f1;
                    bitmapFont$BitmapFontData0.getGlyphs(glyphLayout$GlyphRun0, charSequence0, v6, v8, bitmapFont$Glyph0);
                    if(glyphLayout$GlyphRun0.glyphs.size == 0) {
                        GlyphLayout.glyphRunPool.free(glyphLayout$GlyphRun0);
                        goto label_215;
                    }
                    else {
                        float f6 = bitmapFont$Glyph0 == null ? f3 : f3 - (bitmapFont$Glyph0.fixedWidth ? ((float)bitmapFont$Glyph0.xadvance) * bitmapFont$BitmapFontData0.scaleX : ((float)(bitmapFont$Glyph0.width + bitmapFont$Glyph0.xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padRight);
                        bitmapFont$Glyph1 = (Glyph)glyphLayout$GlyphRun0.glyphs.peek();
                        glyphLayout$GlyphRun0.x = f6;
                        glyphLayout$GlyphRun0.y = f4;
                        if(z3 || v8 == v1) {
                            this.adjustLastGlyph(bitmapFont$BitmapFontData0, glyphLayout$GlyphRun0);
                        }
                        array0.add(glyphLayout$GlyphRun0);
                        int v12 = glyphLayout$GlyphRun0.xAdvances.size;
                        float[] arr_f = glyphLayout$GlyphRun0.xAdvances.items;
                        if(z1 && v12 != 0) {
                            float f7 = f6 + (arr_f[0] + arr_f[1]);
                            float f8 = f4;
                            int v13 = 2;
                            while(v13 < v12) {
                                Glyph bitmapFont$Glyph2 = (Glyph)glyphLayout$GlyphRun0.glyphs.get(v13 - 1);
                                if(((float)(bitmapFont$Glyph2.width + bitmapFont$Glyph2.xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padRight + f7 - 0.0001f <= f) {
                                    f7 += arr_f[v13];
                                    bitmapFont$Glyph3 = bitmapFont$Glyph1;
                                    arr_f1 = arr_f;
                                    goto label_194;
                                }
                                else if(s == null) {
                                    goto label_150;
                                }
                                else {
                                    this.truncate(bitmapFont$BitmapFontData0, glyphLayout$GlyphRun0, f, s, v13, GlyphLayout.glyphRunPool);
                                    f2 = f8;
                                }
                            label_96:
                                this.height = bitmapFont$BitmapFontData0.capHeight + Math.abs(f2);
                                Object[] arr_object = array0.items;
                                int v14 = array0.size;
                                float f9 = 0.0f;
                                for(int v15 = 0; v15 < v14; ++v15) {
                                    GlyphRun glyphLayout$GlyphRun1 = (GlyphRun)arr_object[v15];
                                    float[] arr_f2 = glyphLayout$GlyphRun1.xAdvances.items;
                                    Object[] arr_object1 = glyphLayout$GlyphRun1.glyphs.items;
                                    int v16 = glyphLayout$GlyphRun1.glyphs.size;
                                    float f10 = arr_f2[0];
                                    int v17 = 0;
                                    float f11 = 0.0f;
                                    while(v17 < v16) {
                                        Glyph bitmapFont$Glyph4 = (Glyph)arr_object1[v17];
                                        f11 = Math.max(f11, ((float)(bitmapFont$Glyph4.width + bitmapFont$Glyph4.xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padRight + f10);
                                        ++v17;
                                        f10 += arr_f2[v17];
                                    }
                                    glyphLayout$GlyphRun1.width = Math.max(f10, f11);
                                    f9 = Math.max(f9, glyphLayout$GlyphRun1.x + glyphLayout$GlyphRun1.width);
                                }
                                this.width = f9;
                                if((v2 & 8) == 0) {
                                    boolean z4 = (v2 & 1) != 0;
                                    float f12 = 0.0f;
                                    float f13 = -2147483648.0f;
                                    int v19 = 0;
                                    for(int v18 = 0; v18 < v14; ++v18) {
                                        GlyphRun glyphLayout$GlyphRun2 = (GlyphRun)arr_object[v18];
                                        if(glyphLayout$GlyphRun2.y == f13) {
                                            f12 = Math.max(f12, glyphLayout$GlyphRun2.x + glyphLayout$GlyphRun2.width);
                                        }
                                        else {
                                            f13 = glyphLayout$GlyphRun2.y;
                                            float f14 = z4 ? (f - f12) / 2.0f : f - f12;
                                            int v20;
                                            for(v20 = v19; v20 < v18; ++v20) {
                                                GlyphRun glyphLayout$GlyphRun3 = (GlyphRun)arr_object[v20];
                                                glyphLayout$GlyphRun3.x += f14;
                                            }
                                            v19 = v20;
                                            f12 = glyphLayout$GlyphRun2.x + glyphLayout$GlyphRun2.width;
                                        }
                                    }
                                    for(int v21 = v19; v21 < v14; ++v21) {
                                        GlyphRun glyphLayout$GlyphRun4 = (GlyphRun)arr_object[v21];
                                        glyphLayout$GlyphRun4.x += (z4 ? (f - f12) / 2.0f : f - f12);
                                    }
                                }
                                return;
                            label_150:
                                float f15 = f8 + f5;
                                int v22 = bitmapFont$BitmapFontData0.getWrapIndex(glyphLayout$GlyphRun0.glyphs, v13);
                                if(v22 == 0 && glyphLayout$GlyphRun0.x == 0.0f || v22 >= glyphLayout$GlyphRun0.glyphs.size) {
                                    v22 = v13 - 1;
                                }
                                if(v22 == 0) {
                                    int v23 = glyphLayout$GlyphRun0.glyphs.size;
                                    while(v22 < v23 && bitmapFont$BitmapFontData0.isWhitespace(((char)((Glyph)glyphLayout$GlyphRun0.glyphs.get(v22)).id))) {
                                        ++v22;
                                    }
                                    if(v22 > 0) {
                                        glyphLayout$GlyphRun0.glyphs.removeRange(0, v22 - 1);
                                        glyphLayout$GlyphRun0.xAdvances.removeRange(1, v22);
                                    }
                                    arr_f[0] = ((float)(-((Glyph)glyphLayout$GlyphRun0.glyphs.first()).xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padLeft;
                                    if(array0.size > 1) {
                                        GlyphRun glyphLayout$GlyphRun5 = (GlyphRun)array0.get(array0.size - 2);
                                        int v24;
                                        for(v24 = glyphLayout$GlyphRun5.glyphs.size - 1; v24 > 0 && bitmapFont$BitmapFontData0.isWhitespace(((char)((Glyph)glyphLayout$GlyphRun5.glyphs.get(v24)).id)); --v24) {
                                        }
                                        glyphLayout$GlyphRun5.glyphs.truncate(v24 + 1);
                                        glyphLayout$GlyphRun5.xAdvances.truncate(v24 + 2);
                                        this.adjustLastGlyph(bitmapFont$BitmapFontData0, glyphLayout$GlyphRun5);
                                    }
                                }
                                else {
                                    GlyphRun glyphLayout$GlyphRun6 = this.wrap(bitmapFont$BitmapFontData0, glyphLayout$GlyphRun0, v22, v13);
                                    if(glyphLayout$GlyphRun6 == null) {
                                        f4 = f15;
                                        bitmapFont$Glyph1 = null;
                                        f16 = 0.0f;
                                        goto label_217;
                                    }
                                    else {
                                        array0.add(glyphLayout$GlyphRun6);
                                        glyphLayout$GlyphRun0 = glyphLayout$GlyphRun6;
                                    }
                                }
                                int v25 = glyphLayout$GlyphRun0.xAdvances.size;
                                float[] arr_f3 = glyphLayout$GlyphRun0.xAdvances.items;
                                float f17 = arr_f3[0];
                                if(v25 > 1) {
                                    f17 += arr_f3[1];
                                }
                                glyphLayout$GlyphRun0.x = 0.0f;
                                glyphLayout$GlyphRun0.y = f15;
                                f8 = f15;
                                arr_f1 = arr_f3;
                                bitmapFont$Glyph3 = null;
                                v12 = v25;
                                f7 = f17;
                                v13 = 1;
                            label_194:
                                ++v13;
                                arr_f = arr_f1;
                                bitmapFont$Glyph1 = bitmapFont$Glyph3;
                            }
                            f16 = f7;
                            f4 = f8;
                        }
                        else if(z2) {
                            f16 = f6;
                            for(int v26 = 0; v26 < v12; ++v26) {
                                f16 += arr_f[v26];
                            }
                        }
                        else {
                            f16 = f6;
                        }
                        goto label_217;
                    }
                    goto label_211;
                }
            label_215:
                bitmapFont$Glyph1 = bitmapFont$Glyph0;
                f16 = f3;
            label_217:
                if(z3) {
                    f4 = v8 == v11 ? f4 + bitmapFont$BitmapFontData0.blankLineScale * f5 : f4 + f5;
                    bitmapFont$Glyph0 = null;
                    f16 = 0.0f;
                }
                else {
                    bitmapFont$Glyph0 = bitmapFont$Glyph1;
                }
                v27 = v10;
                f2 = f4;
                f3 = f16;
                color1 = color3;
            }
            v3 = v10;
            v6 = v27;
            color2 = color3;
            f1 = f5;
        }
    }

    public void setText(BitmapFont bitmapFont0, CharSequence charSequence0, Color color0, float f, int v, boolean z) {
        this.setText(bitmapFont0, charSequence0, 0, charSequence0.length(), color0, f, v, z, null);
    }

    @Override
    public String toString() {
        if(this.runs.size == 0) {
            return "";
        }
        StringBuilder stringBuilder0 = new StringBuilder(0x80);
        stringBuilder0.append(this.width);
        stringBuilder0.append('x');
        stringBuilder0.append(this.height);
        stringBuilder0.append('\n');
        int v1 = this.runs.size;
        for(int v = 0; v < v1; ++v) {
            stringBuilder0.append(", #00000000, 0.0, 0.0, 0.0");
            stringBuilder0.append('\n');
        }
        stringBuilder0.setLength(stringBuilder0.length() - 1);
        return stringBuilder0.toString();
    }

    private void truncate(BitmapFontData bitmapFont$BitmapFontData0, GlyphRun glyphLayout$GlyphRun0, float f, String s, int v, Pool pool0) {
        GlyphRun glyphLayout$GlyphRun1 = (GlyphRun)pool0.obtain();
        bitmapFont$BitmapFontData0.getGlyphs(glyphLayout$GlyphRun1, s, 0, s.length(), null);
        float f1 = 0.0f;
        if(glyphLayout$GlyphRun1.xAdvances.size > 0) {
            this.adjustLastGlyph(bitmapFont$BitmapFontData0, glyphLayout$GlyphRun1);
            float[] arr_f = glyphLayout$GlyphRun1.xAdvances.items;
            int v1 = glyphLayout$GlyphRun1.xAdvances.size;
            float f2 = 0.0f;
            for(int v2 = 1; v2 < v1; ++v2) {
                f2 += arr_f[v2];
            }
            f1 = f2;
        }
        int v3 = 0;
        float f3 = glyphLayout$GlyphRun0.x;
        float[] arr_f1 = glyphLayout$GlyphRun0.xAdvances.items;
        while(v3 < glyphLayout$GlyphRun0.xAdvances.size) {
            f3 += arr_f1[v3];
            if(f3 > f - f1) {
                break;
            }
            ++v3;
        }
        if(v3 > 1) {
            glyphLayout$GlyphRun0.glyphs.truncate(v3 - 1);
            glyphLayout$GlyphRun0.xAdvances.truncate(v3);
            this.adjustLastGlyph(bitmapFont$BitmapFontData0, glyphLayout$GlyphRun0);
            if(glyphLayout$GlyphRun1.xAdvances.size > 0) {
                glyphLayout$GlyphRun0.xAdvances.addAll(glyphLayout$GlyphRun1.xAdvances, 1, glyphLayout$GlyphRun1.xAdvances.size - 1);
            }
        }
        else {
            glyphLayout$GlyphRun0.glyphs.clear();
            glyphLayout$GlyphRun0.xAdvances.clear();
            glyphLayout$GlyphRun0.xAdvances.addAll(glyphLayout$GlyphRun1.xAdvances);
        }
        glyphLayout$GlyphRun0.glyphs.addAll(glyphLayout$GlyphRun1.glyphs);
        pool0.free(glyphLayout$GlyphRun1);
    }

    private GlyphRun wrap(BitmapFontData bitmapFont$BitmapFontData0, GlyphRun glyphLayout$GlyphRun0, int v, int v1) {
        GlyphRun glyphLayout$GlyphRun1;
        Array array0 = glyphLayout$GlyphRun0.glyphs;
        int v2 = glyphLayout$GlyphRun0.glyphs.size;
        FloatArray floatArray0 = glyphLayout$GlyphRun0.xAdvances;
        int v3;
        for(v3 = v; v3 > 0 && bitmapFont$BitmapFontData0.isWhitespace(((char)((Glyph)array0.get(v3 - 1)).id)); --v3) {
        }
        while(true) {
            glyphLayout$GlyphRun1 = null;
            if(v >= v2 || !bitmapFont$BitmapFontData0.isWhitespace(((char)((Glyph)array0.get(v)).id))) {
                break;
            }
            ++v;
        }
        if(v < v2) {
            glyphLayout$GlyphRun1 = (GlyphRun)GlyphLayout.glyphRunPool.obtain();
            glyphLayout$GlyphRun1.color.set(glyphLayout$GlyphRun0.color);
            Array array1 = glyphLayout$GlyphRun1.glyphs;
            array1.addAll(array0, 0, v3);
            array0.removeRange(0, v - 1);
            glyphLayout$GlyphRun0.glyphs = array1;
            glyphLayout$GlyphRun1.glyphs = array0;
            FloatArray floatArray1 = glyphLayout$GlyphRun1.xAdvances;
            floatArray1.addAll(floatArray0, 0, v3 + 1);
            floatArray0.removeRange(1, v);
            float[] arr_f = floatArray0.items;
            arr_f[0] = ((float)(-((Glyph)array0.first()).xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padLeft;
            glyphLayout$GlyphRun0.xAdvances = floatArray1;
            glyphLayout$GlyphRun1.xAdvances = floatArray0;
        }
        else {
            array0.truncate(v3);
            floatArray0.truncate(v3 + 1);
        }
        if(v3 == 0) {
            GlyphLayout.glyphRunPool.free(glyphLayout$GlyphRun0);
            this.runs.pop();
            return glyphLayout$GlyphRun1;
        }
        this.adjustLastGlyph(bitmapFont$BitmapFontData0, glyphLayout$GlyphRun0);
        return glyphLayout$GlyphRun1;
    }
}

