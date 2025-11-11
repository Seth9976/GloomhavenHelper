package com.badlogic.gdx.graphics;

import com.badlogic.gdx.utils.NumberUtils;

public class Color {
    public static final Color BLACK;
    public static final Color BLUE;
    public static final Color BROWN;
    public static final Color CHARTREUSE;
    public static final Color CLEAR;
    public static final Color CORAL;
    public static final Color CYAN;
    public static final Color DARK_GRAY;
    public static final Color FIREBRICK;
    public static final Color FOREST;
    public static final Color GOLD;
    public static final Color GOLDENROD;
    public static final Color GRAY;
    public static final Color GREEN;
    public static final Color LIGHT_GRAY;
    public static final Color LIME;
    public static final Color MAGENTA;
    public static final Color MAROON;
    public static final Color NAVY;
    public static final Color OLIVE;
    public static final Color ORANGE;
    public static final Color PINK;
    public static final Color PURPLE;
    public static final Color RED;
    public static final Color ROYAL;
    public static final Color SALMON;
    public static final Color SCARLET;
    public static final Color SKY;
    public static final Color SLATE;
    public static final Color TAN;
    public static final Color TEAL;
    public static final Color VIOLET;
    public static final Color WHITE;
    public static final float WHITE_FLOAT_BITS;
    public static final Color YELLOW;
    public float a;
    public float b;
    public float g;
    public float r;

    static {
        Color.WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        Color.LIGHT_GRAY = new Color(0xBFBFBFFF);
        Color.GRAY = new Color(0x7F7F7FFF);
        Color.DARK_GRAY = new Color(0x3F3F3FFF);
        Color.BLACK = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        Color.WHITE_FLOAT_BITS = Color.WHITE.toFloatBits();
        Color.CLEAR = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        Color.BLUE = new Color(0.0f, 0.0f, 1.0f, 1.0f);
        Color.NAVY = new Color(0.0f, 0.0f, 0.5f, 1.0f);
        Color.ROYAL = new Color(0x4169E1FF);
        Color.SLATE = new Color(0x708090FF);
        Color.SKY = new Color(-2016482305);
        Color.CYAN = new Color(0.0f, 1.0f, 1.0f, 1.0f);
        Color.TEAL = new Color(0.0f, 0.5f, 0.5f, 1.0f);
        Color.GREEN = new Color(0xFF00FF);
        Color.CHARTREUSE = new Color(0x7FFF00FF);
        Color.LIME = new Color(0x32CD32FF);
        Color.FOREST = new Color(0x228B22FF);
        Color.OLIVE = new Color(0x6B8E23FF);
        Color.YELLOW = new Color(0xFFFF00FF);
        Color.GOLD = new Color(0xFFD700FF);
        Color.GOLDENROD = new Color(0xDAA520FF);
        Color.ORANGE = new Color(0xFFA500FF);
        Color.BROWN = new Color(0x8B4513FF);
        Color.TAN = new Color(0xD2B48CFF);
        Color.FIREBRICK = new Color(0xB22222FF);
        Color.RED = new Color(0xFF0000FF);
        Color.SCARLET = new Color(0xFF341CFF);
        Color.CORAL = new Color(0xFF7F50FF);
        Color.SALMON = new Color(0xFA8072FF);
        Color.PINK = new Color(0xFF69B4FF);
        Color.MAGENTA = new Color(1.0f, 0.0f, 1.0f, 1.0f);
        Color.PURPLE = new Color(0xA020F0FF);
        Color.VIOLET = new Color(-293409025);
        Color.MAROON = new Color(0xB03060FF);
    }

    public Color() {
    }

    public Color(float f, float f1, float f2, float f3) {
        this.r = f;
        this.g = f1;
        this.b = f2;
        this.a = f3;
        this.clamp();
    }

    public Color(int v) {
        Color.rgba8888ToColor(this, v);
    }

    public Color(Color color0) {
        this.set(color0);
    }

    public static void abgr8888ToColor(Color color0, float f) {
        int v = NumberUtils.floatToIntColor(f);
        color0.a = ((float)((0xFF000000 & v) >>> 24)) / 255.0f;
        color0.b = ((float)((0xFF0000 & v) >>> 16)) / 255.0f;
        color0.g = ((float)((0xFF00 & v) >>> 8)) / 255.0f;
        color0.r = ((float)(v & 0xFF)) / 255.0f;
    }

    public Color add(float f, float f1, float f2, float f3) {
        this.r += f;
        this.g += f1;
        this.b += f2;
        this.a += f3;
        return this.clamp();
    }

    public Color add(Color color0) {
        this.r += color0.r;
        this.g += color0.g;
        this.b += color0.b;
        this.a += color0.a;
        return this.clamp();
    }

    public static int alpha(float f) {
        return (int)(f * 255.0f);
    }

    public static int argb8888(float f, float f1, float f2, float f3) {
        return ((int)(f * 255.0f)) << 24 | ((int)(f1 * 255.0f)) << 16 | ((int)(f2 * 255.0f)) << 8 | ((int)(f3 * 255.0f));
    }

    public static int argb8888(Color color0) {
        return ((int)(color0.b * 255.0f)) | (((int)(color0.a * 255.0f)) << 24 | ((int)(color0.r * 255.0f)) << 16 | ((int)(color0.g * 255.0f)) << 8);
    }

    public static void argb8888ToColor(Color color0, int v) {
        color0.a = ((float)((0xFF000000 & v) >>> 24)) / 255.0f;
        color0.r = ((float)((0xFF0000 & v) >>> 16)) / 255.0f;
        color0.g = ((float)((0xFF00 & v) >>> 8)) / 255.0f;
        color0.b = ((float)(v & 0xFF)) / 255.0f;
    }

    public Color clamp() {
        float f = this.r;
        if(f < 0.0f) {
            this.r = 0.0f;
        }
        else if(f > 1.0f) {
            this.r = 1.0f;
        }
        float f1 = this.g;
        if(f1 < 0.0f) {
            this.g = 0.0f;
        }
        else if(f1 > 1.0f) {
            this.g = 1.0f;
        }
        float f2 = this.b;
        if(f2 < 0.0f) {
            this.b = 0.0f;
        }
        else if(f2 > 1.0f) {
            this.b = 1.0f;
        }
        float f3 = this.a;
        if(f3 < 0.0f) {
            this.a = 0.0f;
            return this;
        }
        if(f3 > 1.0f) {
            this.a = 1.0f;
        }
        return this;
    }

    public Color cpy() {
        return new Color(this);
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.toIntBits() == ((Color)object0).toIntBits();
    }

    public Color fromHsv(float f, float f1, float f2) {
        float f3 = (f / 60.0f + 6.0f) % 6.0f;
        float f4 = f3 - ((float)(((int)f3)));
        float f5 = (1.0f - f1) * f2;
        float f6 = (1.0f - f1 * f4) * f2;
        float f7 = (1.0f - f1 * (1.0f - f4)) * f2;
        switch(((int)f3)) {
            case 0: {
                this.r = f2;
                this.g = f7;
                this.b = f5;
                return this.clamp();
            }
            case 1: {
                this.r = f6;
                this.g = f2;
                this.b = f5;
                return this.clamp();
            }
            case 2: {
                this.r = f5;
                this.g = f2;
                this.b = f7;
                return this.clamp();
            }
            case 3: {
                this.r = f5;
                this.g = f6;
                this.b = f2;
                return this.clamp();
            }
            case 4: {
                this.r = f7;
                this.g = f5;
                this.b = f2;
                return this.clamp();
            }
            default: {
                this.r = f2;
                this.g = f5;
                this.b = f6;
                return this.clamp();
            }
        }
    }

    public Color fromHsv(float[] arr_f) {
        return this.fromHsv(arr_f[0], arr_f[1], arr_f[2]);
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.r == 0.0f ? 0 : NumberUtils.floatToIntBits(this.r);
        int v2 = this.g == 0.0f ? 0 : NumberUtils.floatToIntBits(this.g);
        int v3 = this.b == 0.0f ? 0 : NumberUtils.floatToIntBits(this.b);
        float f = this.a;
        if(f != 0.0f) {
            v = NumberUtils.floatToIntBits(f);
        }
        return ((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v;
    }

    public Color lerp(float f, float f1, float f2, float f3, float f4) {
        this.r += (f - this.r) * f4;
        this.g += (f1 - this.g) * f4;
        this.b += (f2 - this.b) * f4;
        this.a += f4 * (f3 - this.a);
        return this.clamp();
    }

    public Color lerp(Color color0, float f) {
        this.r += (color0.r - this.r) * f;
        this.g += (color0.g - this.g) * f;
        this.b += (color0.b - this.b) * f;
        this.a += f * (color0.a - this.a);
        return this.clamp();
    }

    public static int luminanceAlpha(float f, float f1) {
        return ((int)(f * 255.0f)) << 8 | ((int)(f1 * 255.0f));
    }

    public Color mul(float f) {
        this.r *= f;
        this.g *= f;
        this.b *= f;
        this.a *= f;
        return this.clamp();
    }

    public Color mul(float f, float f1, float f2, float f3) {
        this.r *= f;
        this.g *= f1;
        this.b *= f2;
        this.a *= f3;
        return this.clamp();
    }

    public Color mul(Color color0) {
        this.r *= color0.r;
        this.g *= color0.g;
        this.b *= color0.b;
        this.a *= color0.a;
        return this.clamp();
    }

    public Color premultiplyAlpha() {
        this.r *= this.a;
        this.g *= this.a;
        this.b *= this.a;
        return this;
    }

    public static int rgb565(float f, float f1, float f2) {
        return ((int)(f * 31.0f)) << 11 | ((int)(f1 * 63.0f)) << 5 | ((int)(f2 * 31.0f));
    }

    public static int rgb565(Color color0) {
        return ((int)(color0.b * 31.0f)) | (((int)(color0.r * 31.0f)) << 11 | ((int)(color0.g * 63.0f)) << 5);
    }

    public static void rgb565ToColor(Color color0, int v) {
        color0.r = ((float)((0xF800 & v) >>> 11)) / 31.0f;
        color0.g = ((float)((v & 0x7E0) >>> 5)) / 63.0f;
        color0.b = ((float)(v & 0x1F)) / 31.0f;
    }

    public static int rgb888(float f, float f1, float f2) {
        return ((int)(f * 255.0f)) << 16 | ((int)(f1 * 255.0f)) << 8 | ((int)(f2 * 255.0f));
    }

    public static int rgb888(Color color0) {
        return ((int)(color0.b * 255.0f)) | (((int)(color0.r * 255.0f)) << 16 | ((int)(color0.g * 255.0f)) << 8);
    }

    public static void rgb888ToColor(Color color0, int v) {
        color0.r = ((float)((0xFF0000 & v) >>> 16)) / 255.0f;
        color0.g = ((float)((0xFF00 & v) >>> 8)) / 255.0f;
        color0.b = ((float)(v & 0xFF)) / 255.0f;
    }

    public static int rgba4444(float f, float f1, float f2, float f3) {
        return ((int)(f * 15.0f)) << 12 | ((int)(f1 * 15.0f)) << 8 | ((int)(f2 * 15.0f)) << 4 | ((int)(f3 * 15.0f));
    }

    public static int rgba4444(Color color0) {
        return ((int)(color0.a * 15.0f)) | (((int)(color0.r * 15.0f)) << 12 | ((int)(color0.g * 15.0f)) << 8 | ((int)(color0.b * 15.0f)) << 4);
    }

    public static void rgba4444ToColor(Color color0, int v) {
        color0.r = ((float)((0xF000 & v) >>> 12)) / 15.0f;
        color0.g = ((float)((v & 0xF00) >>> 8)) / 15.0f;
        color0.b = ((float)((v & 0xF0) >>> 4)) / 15.0f;
        color0.a = ((float)(v & 15)) / 15.0f;
    }

    public static int rgba8888(float f, float f1, float f2, float f3) {
        return ((int)(f * 255.0f)) << 24 | ((int)(f1 * 255.0f)) << 16 | ((int)(f2 * 255.0f)) << 8 | ((int)(f3 * 255.0f));
    }

    public static int rgba8888(Color color0) {
        return ((int)(color0.a * 255.0f)) | (((int)(color0.r * 255.0f)) << 24 | ((int)(color0.g * 255.0f)) << 16 | ((int)(color0.b * 255.0f)) << 8);
    }

    public static void rgba8888ToColor(Color color0, int v) {
        color0.r = ((float)((0xFF000000 & v) >>> 24)) / 255.0f;
        color0.g = ((float)((0xFF0000 & v) >>> 16)) / 255.0f;
        color0.b = ((float)((0xFF00 & v) >>> 8)) / 255.0f;
        color0.a = ((float)(v & 0xFF)) / 255.0f;
    }

    public Color set(float f, float f1, float f2, float f3) {
        this.r = f;
        this.g = f1;
        this.b = f2;
        this.a = f3;
        return this.clamp();
    }

    public Color set(int v) {
        Color.rgba8888ToColor(this, v);
        return this;
    }

    public Color set(Color color0) {
        this.r = color0.r;
        this.g = color0.g;
        this.b = color0.b;
        this.a = color0.a;
        return this;
    }

    public Color sub(float f, float f1, float f2, float f3) {
        this.r -= f;
        this.g -= f1;
        this.b -= f2;
        this.a -= f3;
        return this.clamp();
    }

    public Color sub(Color color0) {
        this.r -= color0.r;
        this.g -= color0.g;
        this.b -= color0.b;
        this.a -= color0.a;
        return this.clamp();
    }

    public static float toFloatBits(float f, float f1, float f2, float f3) {
        return NumberUtils.intToFloatColor(((int)(f * 255.0f)) | (((int)(f1 * 255.0f)) << 8 | (((int)(f2 * 255.0f)) << 16 | ((int)(f3 * 255.0f)) << 24)));
    }

    public static float toFloatBits(int v, int v1, int v2, int v3) {
        return NumberUtils.intToFloatColor(v | (v1 << 8 | (v2 << 16 | v3 << 24)));
    }

    public float toFloatBits() {
        return NumberUtils.intToFloatColor(((int)(this.a * 255.0f)) << 24 | ((int)(this.b * 255.0f)) << 16 | ((int)(this.g * 255.0f)) << 8 | ((int)(this.r * 255.0f)));
    }

    public float[] toHsv(float[] arr_f) {
        float f = Math.max(Math.max(this.r, this.g), this.b);
        float f1 = Math.min(Math.min(this.r, this.g), this.b);
        float f2 = f - f1;
        if(f2 == 0.0f) {
            arr_f[0] = 0.0f;
        }
        else {
            float f3 = this.r;
            if(f == f3) {
                arr_f[0] = ((this.g - this.b) * 60.0f / f2 + 360.0f) % 360.0f;
            }
            else {
                float f4 = this.g;
                arr_f[0] = f == f4 ? (this.b - f3) * 60.0f / f2 + 120.0f : (f3 - f4) * 60.0f / f2 + 240.0f;
            }
        }
        arr_f[1] = f > 0.0f ? 1.0f - f1 / f : 0.0f;
        arr_f[2] = f;
        return arr_f;
    }

    public static int toIntBits(int v, int v1, int v2, int v3) {
        return v | (v1 << 8 | (v2 << 16 | v3 << 24));
    }

    public int toIntBits() {
        return ((int)(this.a * 255.0f)) << 24 | ((int)(this.b * 255.0f)) << 16 | ((int)(this.g * 255.0f)) << 8 | ((int)(this.r * 255.0f));
    }

    @Override
    public String toString() {
        String s;
        for(s = Integer.toHexString(((int)(this.r * 255.0f)) << 24 | ((int)(this.g * 255.0f)) << 16 | ((int)(this.b * 255.0f)) << 8 | ((int)(this.a * 255.0f))); s.length() < 8; s = "0" + s) {
        }
        return s;
    }

    public static Color valueOf(String s) {
        return Color.valueOf(s, new Color());
    }

    public static Color valueOf(String s, Color color0) {
        if(s.charAt(0) == 35) {
            s = s.substring(1);
        }
        color0.r = ((float)Integer.parseInt(s.substring(0, 2), 16)) / 255.0f;
        color0.g = ((float)Integer.parseInt(s.substring(2, 4), 16)) / 255.0f;
        color0.b = ((float)Integer.parseInt(s.substring(4, 6), 16)) / 255.0f;
        color0.a = s.length() == 8 ? ((float)Integer.parseInt(s.substring(6, 8), 16)) / 255.0f : 1.0f;
        return color0;
    }
}

