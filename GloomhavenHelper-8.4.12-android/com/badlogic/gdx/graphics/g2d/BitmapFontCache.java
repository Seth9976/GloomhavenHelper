package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.Pools;

public class BitmapFontCache {
    private final Color color;
    private float currentTint;
    private final BitmapFont font;
    private int glyphCount;
    private int[] idx;
    private boolean integer;
    private final Array layouts;
    private IntArray[] pageGlyphIndices;
    private float[][] pageVertices;
    private final Array pooledLayouts;
    private static final Color tempColor;
    private int[] tempGlyphCount;
    private float x;
    private float y;

    static {
        BitmapFontCache.tempColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public BitmapFontCache(BitmapFont bitmapFont0) {
        this(bitmapFont0, bitmapFont0.usesIntegerPositions());
    }

    public BitmapFontCache(BitmapFont bitmapFont0, boolean z) {
        this.layouts = new Array();
        this.pooledLayouts = new Array();
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.font = bitmapFont0;
        this.integer = z;
        int v = bitmapFont0.regions.size;
        if(v == 0) {
            throw new IllegalArgumentException("The specified font must contain at least one texture page.");
        }
        this.pageVertices = new float[v][];
        this.idx = new int[v];
        if(v > 1) {
            this.pageGlyphIndices = new IntArray[v];
            for(int v1 = 0; v1 < this.pageGlyphIndices.length; ++v1) {
                IntArray[] arr_intArray = this.pageGlyphIndices;
                arr_intArray[v1] = new IntArray();
            }
        }
        this.tempGlyphCount = new int[v];
    }

    private void addGlyph(Glyph bitmapFont$Glyph0, float f, float f1, float f2) {
        float f3 = f + ((float)bitmapFont$Glyph0.xoffset) * this.font.data.scaleX;
        float f4 = f1 + ((float)bitmapFont$Glyph0.yoffset) * this.font.data.scaleY;
        float f5 = ((float)bitmapFont$Glyph0.width) * this.font.data.scaleX;
        float f6 = ((float)bitmapFont$Glyph0.height) * this.font.data.scaleY;
        float f7 = bitmapFont$Glyph0.u;
        float f8 = bitmapFont$Glyph0.u2;
        float f9 = bitmapFont$Glyph0.v;
        float f10 = bitmapFont$Glyph0.v2;
        if(this.integer) {
            f3 = (float)Math.round(f3);
            f4 = (float)Math.round(f4);
            f5 = (float)Math.round(f5);
            f6 = (float)Math.round(f6);
        }
        float f11 = f5 + f3;
        float f12 = f6 + f4;
        int v = bitmapFont$Glyph0.page;
        int[] arr_v = this.idx;
        int v1 = arr_v[v];
        arr_v[v] += 20;
        IntArray[] arr_intArray = this.pageGlyphIndices;
        if(arr_intArray != null) {
            int v2 = this.glyphCount;
            this.glyphCount = v2 + 1;
            arr_intArray[v].add(v2);
        }
        float[] arr_f = this.pageVertices[v];
        arr_f[v1] = f3;
        arr_f[v1 + 1] = f4;
        arr_f[v1 + 2] = f2;
        arr_f[v1 + 3] = f7;
        arr_f[v1 + 4] = f9;
        arr_f[v1 + 5] = f3;
        arr_f[v1 + 6] = f12;
        arr_f[v1 + 7] = f2;
        arr_f[v1 + 8] = f7;
        arr_f[v1 + 9] = f10;
        arr_f[v1 + 10] = f11;
        arr_f[v1 + 11] = f12;
        arr_f[v1 + 12] = f2;
        arr_f[v1 + 13] = f8;
        arr_f[v1 + 14] = f10;
        arr_f[v1 + 15] = f11;
        arr_f[v1 + 16] = f4;
        arr_f[v1 + 17] = f2;
        arr_f[v1 + 18] = f8;
        arr_f[v1 + 19] = f9;
    }

    public GlyphLayout addText(CharSequence charSequence0, float f, float f1) {
        return this.addText(charSequence0, f, f1, 0, charSequence0.length(), 0.0f, 8, false, null);
    }

    public GlyphLayout addText(CharSequence charSequence0, float f, float f1, float f2, int v, boolean z) {
        return this.addText(charSequence0, f, f1, 0, charSequence0.length(), f2, v, z, null);
    }

    public GlyphLayout addText(CharSequence charSequence0, float f, float f1, int v, int v1, float f2, int v2, boolean z) {
        return this.addText(charSequence0, f, f1, v, v1, f2, v2, z, null);
    }

    public GlyphLayout addText(CharSequence charSequence0, float f, float f1, int v, int v1, float f2, int v2, boolean z, String s) {
        GlyphLayout glyphLayout0 = (GlyphLayout)Pools.obtain(GlyphLayout.class);
        this.pooledLayouts.add(glyphLayout0);
        glyphLayout0.setText(this.font, charSequence0, v, v1, this.color, f2, v2, z, s);
        this.addText(glyphLayout0, f, f1);
        return glyphLayout0;
    }

    public void addText(GlyphLayout glyphLayout0, float f, float f1) {
        this.addToCache(glyphLayout0, f, f1 + this.font.data.ascent);
    }

    private void addToCache(GlyphLayout glyphLayout0, float f, float f1) {
        int v1;
        int v = this.font.regions.size;
        float[][] arr2_f = this.pageVertices;
        if(arr2_f.length < v) {
            float[][] arr2_f1 = new float[v][];
            System.arraycopy(arr2_f, 0, arr2_f1, 0, arr2_f.length);
            this.pageVertices = arr2_f1;
            int[] arr_v = new int[v];
            System.arraycopy(this.idx, 0, arr_v, 0, this.idx.length);
            this.idx = arr_v;
            IntArray[] arr_intArray = new IntArray[v];
            IntArray[] arr_intArray1 = this.pageGlyphIndices;
            if(arr_intArray1 == null) {
                v1 = 0;
            }
            else {
                v1 = arr_intArray1.length;
                System.arraycopy(arr_intArray1, 0, arr_intArray, 0, arr_intArray1.length);
            }
            while(v1 < v) {
                arr_intArray[v1] = new IntArray();
                ++v1;
            }
            this.pageGlyphIndices = arr_intArray;
            this.tempGlyphCount = new int[v];
        }
        this.layouts.add(glyphLayout0);
        this.requireGlyphs(glyphLayout0);
        int v2 = glyphLayout0.runs.size;
        for(int v3 = 0; v3 < v2; ++v3) {
            GlyphRun glyphLayout$GlyphRun0 = (GlyphRun)glyphLayout0.runs.get(v3);
            Array array0 = glyphLayout$GlyphRun0.glyphs;
            FloatArray floatArray0 = glyphLayout$GlyphRun0.xAdvances;
            float f2 = glyphLayout$GlyphRun0.color.toFloatBits();
            float f3 = glyphLayout$GlyphRun0.y + f1;
            int v4 = array0.size;
            float f4 = glyphLayout$GlyphRun0.x + f;
            for(int v5 = 0; v5 < v4; ++v5) {
                Glyph bitmapFont$Glyph0 = (Glyph)array0.get(v5);
                f4 += floatArray0.get(v5);
                this.addGlyph(bitmapFont$Glyph0, f4, f3, f2);
            }
        }
        this.currentTint = Color.WHITE_FLOAT_BITS;
    }

    public void clear() {
        this.x = 0.0f;
        this.y = 0.0f;
        Pools.freeAll(this.pooledLayouts, true);
        this.pooledLayouts.clear();
        this.layouts.clear();
        for(int v = 0; v < this.idx.length; ++v) {
            IntArray[] arr_intArray = this.pageGlyphIndices;
            if(arr_intArray != null) {
                arr_intArray[v].clear();
            }
            this.idx[v] = 0;
        }
    }

    public void draw(Batch batch0) {
        Array array0 = this.font.getRegions();
        for(int v = 0; v < this.pageVertices.length; ++v) {
            if(this.idx[v] > 0) {
                float[] arr_f = this.pageVertices[v];
                batch0.draw(((TextureRegion)array0.get(v)).getTexture(), arr_f, 0, this.idx[v]);
            }
        }
    }

    public void draw(Batch batch0, float f) {
        if(f == 1.0f) {
            this.draw(batch0);
            return;
        }
        Color color0 = this.getColor();
        float f1 = color0.a;
        color0.a *= f;
        this.setColors(color0);
        this.draw(batch0);
        color0.a = f1;
        this.setColors(color0);
    }

    public void draw(Batch batch0, int v, int v1) {
        if(this.pageVertices.length == 1) {
            batch0.draw(this.font.getRegion().getTexture(), this.pageVertices[0], v * 20, (v1 - v) * 20);
            return;
        }
        Array array0 = this.font.getRegions();
        for(int v2 = 0; v2 < this.pageVertices.length; ++v2) {
            IntArray intArray0 = this.pageGlyphIndices[v2];
            int v3 = intArray0.size;
            int v5 = -1;
            int v6 = 0;
            for(int v4 = 0; v4 < v3; ++v4) {
                int v7 = intArray0.get(v4);
                if(v7 >= v1) {
                    break;
                }
                if(v5 == -1 && v7 >= v) {
                    v5 = v4;
                }
                if(v7 >= v) {
                    ++v6;
                }
            }
            if(v5 != -1 && v6 != 0) {
                batch0.draw(((TextureRegion)array0.get(v2)).getTexture(), this.pageVertices[v2], v5 * 20, v6 * 20);
            }
        }
    }

    public Color getColor() {
        return this.color;
    }

    public BitmapFont getFont() {
        return this.font;
    }

    public Array getLayouts() {
        return this.layouts;
    }

    public int getVertexCount(int v) {
        return this.idx[v];
    }

    public float[] getVertices() {
        return this.getVertices(0);
    }

    public float[] getVertices(int v) {
        return this.pageVertices[v];
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    private void requireGlyphs(GlyphLayout glyphLayout0) {
        if(this.pageVertices.length == 1) {
            int v1 = glyphLayout0.runs.size;
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                v3 += ((GlyphRun)glyphLayout0.runs.get(v2)).glyphs.size;
            }
            this.requirePageGlyphs(0, v3);
            return;
        }
        int[] arr_v = this.tempGlyphCount;
        for(int v4 = 0; v4 < arr_v.length; ++v4) {
            arr_v[v4] = 0;
        }
        int v5 = glyphLayout0.runs.size;
        for(int v6 = 0; v6 < v5; ++v6) {
            Array array0 = ((GlyphRun)glyphLayout0.runs.get(v6)).glyphs;
            int v7 = array0.size;
            for(int v8 = 0; v8 < v7; ++v8) {
                int v9 = ((Glyph)array0.get(v8)).page;
                ++arr_v[v9];
            }
        }
        for(int v = 0; v < arr_v.length; ++v) {
            this.requirePageGlyphs(v, arr_v[v]);
        }
    }

    private void requirePageGlyphs(int v, int v1) {
        if(this.pageGlyphIndices != null && v1 > this.pageGlyphIndices[v].items.length) {
            this.pageGlyphIndices[v].ensureCapacity(v1 - this.pageGlyphIndices[v].size);
        }
        int[] arr_v = this.idx;
        int v2 = arr_v[v] + v1 * 20;
        float[][] arr2_f = this.pageVertices;
        float[] arr_f = arr2_f[v];
        if(arr_f == null) {
            arr2_f[v] = new float[v2];
            return;
        }
        if(arr_f.length < v2) {
            float[] arr_f1 = new float[v2];
            System.arraycopy(arr_f, 0, arr_f1, 0, arr_v[v]);
            this.pageVertices[v] = arr_f1;
        }
    }

    public void setAlphas(float f) {
        float f1 = 0.0f;
        int v = 0;
        for(float f2 = 0.0f; v < this.pageVertices.length; f2 = f3) {
            float[] arr_f = this.pageVertices[v];
            int v1 = this.idx[v];
            float f3 = f2;
            float f4 = f1;
            for(int v2 = 2; v2 < v1; v2 += 5) {
                float f5 = arr_f[v2];
                if(f5 != f4 || v2 == 2) {
                    f3 = NumberUtils.intToFloatColor(NumberUtils.floatToIntColor(f5) & 0xFFFFFF | ((int)(f * 254.0f)) << 24);
                    arr_f[v2] = f3;
                    f4 = f5;
                }
                else {
                    arr_f[v2] = f3;
                }
            }
            ++v;
            f1 = f4;
        }
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
    }

    public void setColor(Color color0) {
        this.color.set(color0);
    }

    public void setColors(float f) {
        for(int v = 0; v < this.pageVertices.length; ++v) {
            float[] arr_f = this.pageVertices[v];
            int v2 = this.idx[v];
            for(int v1 = 2; v1 < v2; v1 += 5) {
                arr_f[v1] = f;
            }
        }
    }

    public void setColors(float f, float f1, float f2, float f3) {
        this.setColors(NumberUtils.intToFloatColor(((int)(f * 255.0f)) | (((int)(f1 * 255.0f)) << 8 | (((int)(f2 * 255.0f)) << 16 | ((int)(f3 * 255.0f)) << 24))));
    }

    public void setColors(float f, int v, int v1) {
        float[][] arr2_f = this.pageVertices;
        if(arr2_f.length == 1) {
            float[] arr_f = arr2_f[0];
            int v2 = v * 20 + 2;
            int v3 = Math.min(v1 * 20, this.idx[0]);
            while(v2 < v3) {
                arr_f[v2] = f;
                v2 += 5;
            }
            return;
        }
        for(int v4 = 0; v4 < arr2_f.length; ++v4) {
            float[] arr_f1 = this.pageVertices[v4];
            IntArray intArray0 = this.pageGlyphIndices[v4];
            int v5 = intArray0.size;
            for(int v6 = 0; v6 < v5; ++v6) {
                int v7 = intArray0.items[v6];
                if(v7 >= v1) {
                    break;
                }
                if(v7 >= v) {
                    for(int v8 = 0; v8 < 20; v8 += 5) {
                        arr_f1[v6 * 20 + 2 + v8] = f;
                    }
                }
            }
        }
    }

    public void setColors(Color color0) {
        this.setColors(color0.toFloatBits());
    }

    public void setColors(Color color0, int v, int v1) {
        this.setColors(color0.toFloatBits(), v, v1);
    }

    public void setPosition(float f, float f1) {
        this.translate(f - this.x, f1 - this.y);
    }

    public GlyphLayout setText(CharSequence charSequence0, float f, float f1) {
        this.clear();
        return this.addText(charSequence0, f, f1, 0, charSequence0.length(), 0.0f, 8, false);
    }

    public GlyphLayout setText(CharSequence charSequence0, float f, float f1, float f2, int v, boolean z) {
        this.clear();
        return this.addText(charSequence0, f, f1, 0, charSequence0.length(), f2, v, z);
    }

    public GlyphLayout setText(CharSequence charSequence0, float f, float f1, int v, int v1, float f2, int v2, boolean z) {
        this.clear();
        return this.addText(charSequence0, f, f1, v, v1, f2, v2, z);
    }

    public GlyphLayout setText(CharSequence charSequence0, float f, float f1, int v, int v1, float f2, int v2, boolean z, String s) {
        this.clear();
        return this.addText(charSequence0, f, f1, v, v1, f2, v2, z, s);
    }

    public void setText(GlyphLayout glyphLayout0, float f, float f1) {
        this.clear();
        this.addText(glyphLayout0, f, f1);
    }

    public void setUseIntegerPositions(boolean z) {
        this.integer = z;
    }

    public void tint(Color color0) {
        float f = color0.toFloatBits();
        if(this.currentTint == f) {
            return;
        }
        this.currentTint = f;
        int[] arr_v = this.tempGlyphCount;
        for(int v = 0; v < arr_v.length; ++v) {
            arr_v[v] = 0;
        }
        int v1 = this.layouts.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            GlyphLayout glyphLayout0 = (GlyphLayout)this.layouts.get(v2);
            int v3 = glyphLayout0.runs.size;
            for(int v4 = 0; v4 < v3; ++v4) {
                GlyphRun glyphLayout$GlyphRun0 = (GlyphRun)glyphLayout0.runs.get(v4);
                Array array0 = glyphLayout$GlyphRun0.glyphs;
                float f1 = BitmapFontCache.tempColor.set(glyphLayout$GlyphRun0.color).mul(color0).toFloatBits();
                int v5 = array0.size;
                for(int v6 = 0; v6 < v5; ++v6) {
                    int v7 = ((Glyph)array0.get(v6)).page;
                    int v8 = arr_v[v7] * 20 + 2;
                    ++arr_v[v7];
                    float[] arr_f = this.pageVertices[v7];
                    for(int v9 = 0; v9 < 20; v9 += 5) {
                        arr_f[v8 + v9] = f1;
                    }
                }
            }
        }
    }

    public void translate(float f, float f1) {
        if(f == 0.0f && f1 == 0.0f) {
            return;
        }
        if(this.integer) {
            f = (float)Math.round(f);
            f1 = (float)Math.round(f1);
        }
        this.x += f;
        this.y += f1;
        float[][] arr2_f = this.pageVertices;
        for(int v = 0; v < arr2_f.length; ++v) {
            float[] arr_f = arr2_f[v];
            int v1 = this.idx[v];
            for(int v2 = 0; v2 < v1; v2 += 5) {
                arr_f[v2] += f;
                arr_f[v2 + 1] += f1;
            }
        }
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }
}

