package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class NinePatch {
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_RIGHT = 8;
    public static final int MIDDLE_CENTER = 4;
    public static final int MIDDLE_LEFT = 3;
    public static final int MIDDLE_RIGHT = 5;
    public static final int TOP_CENTER = 1;
    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 2;
    private int bottomCenter;
    private float bottomHeight;
    private int bottomLeft;
    private int bottomRight;
    private final Color color;
    private int idx;
    private float leftWidth;
    private int middleCenter;
    private float middleHeight;
    private int middleLeft;
    private int middleRight;
    private float middleWidth;
    private float padBottom;
    private float padLeft;
    private float padRight;
    private float padTop;
    private float rightWidth;
    private Texture texture;
    private static final Color tmpDrawColor;
    private int topCenter;
    private float topHeight;
    private int topLeft;
    private int topRight;
    private float[] vertices;

    static {
        NinePatch.tmpDrawColor = new Color();
    }

    public NinePatch(Texture texture0) {
        this(new TextureRegion(texture0));
    }

    public NinePatch(Texture texture0, int v, int v1, int v2, int v3) {
        this(new TextureRegion(texture0), v, v1, v2, v3);
    }

    public NinePatch(Texture texture0, Color color0) {
        this(texture0);
        this.setColor(color0);
    }

    public NinePatch(NinePatch ninePatch0) {
        this(ninePatch0, ninePatch0.color);
    }

    public NinePatch(NinePatch ninePatch0, Color color0) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.texture = ninePatch0.texture;
        this.bottomLeft = ninePatch0.bottomLeft;
        this.bottomCenter = ninePatch0.bottomCenter;
        this.bottomRight = ninePatch0.bottomRight;
        this.middleLeft = ninePatch0.middleLeft;
        this.middleCenter = ninePatch0.middleCenter;
        this.middleRight = ninePatch0.middleRight;
        this.topLeft = ninePatch0.topLeft;
        this.topCenter = ninePatch0.topCenter;
        this.topRight = ninePatch0.topRight;
        this.leftWidth = ninePatch0.leftWidth;
        this.rightWidth = ninePatch0.rightWidth;
        this.middleWidth = ninePatch0.middleWidth;
        this.middleHeight = ninePatch0.middleHeight;
        this.topHeight = ninePatch0.topHeight;
        this.bottomHeight = ninePatch0.bottomHeight;
        this.padLeft = ninePatch0.padLeft;
        this.padTop = ninePatch0.padTop;
        this.padBottom = ninePatch0.padBottom;
        this.padRight = ninePatch0.padRight;
        this.vertices = new float[ninePatch0.vertices.length];
        System.arraycopy(ninePatch0.vertices, 0, this.vertices, 0, ninePatch0.vertices.length);
        this.idx = ninePatch0.idx;
        this.color.set(color0);
    }

    public NinePatch(TextureRegion textureRegion0) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        this.load(new TextureRegion[]{null, null, null, null, textureRegion0, null, null, null, null});
    }

    public NinePatch(TextureRegion textureRegion0, int v, int v1, int v2, int v3) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        if(textureRegion0 == null) {
            throw new IllegalArgumentException("region cannot be null.");
        }
        int v4 = textureRegion0.getRegionWidth() - v - v1;
        int v5 = textureRegion0.getRegionHeight() - v2 - v3;
        TextureRegion[] arr_textureRegion = new TextureRegion[9];
        if(v2 > 0) {
            if(v > 0) {
                arr_textureRegion[0] = new TextureRegion(textureRegion0, 0, 0, v, v2);
            }
            if(v4 > 0) {
                arr_textureRegion[1] = new TextureRegion(textureRegion0, v, 0, v4, v2);
            }
            if(v1 > 0) {
                arr_textureRegion[2] = new TextureRegion(textureRegion0, v + v4, 0, v1, v2);
            }
        }
        if(v5 > 0) {
            if(v > 0) {
                arr_textureRegion[3] = new TextureRegion(textureRegion0, 0, v2, v, v5);
            }
            if(v4 > 0) {
                arr_textureRegion[4] = new TextureRegion(textureRegion0, v, v2, v4, v5);
            }
            if(v1 > 0) {
                arr_textureRegion[5] = new TextureRegion(textureRegion0, v + v4, v2, v1, v5);
            }
        }
        if(v3 > 0) {
            if(v > 0) {
                arr_textureRegion[6] = new TextureRegion(textureRegion0, 0, v2 + v5, v, v3);
            }
            if(v4 > 0) {
                arr_textureRegion[7] = new TextureRegion(textureRegion0, v, v2 + v5, v4, v3);
            }
            if(v1 > 0) {
                arr_textureRegion[8] = new TextureRegion(textureRegion0, v + v4, v2 + v5, v1, v3);
            }
        }
        if(v == 0 && v4 == 0) {
            arr_textureRegion[1] = arr_textureRegion[2];
            arr_textureRegion[4] = arr_textureRegion[5];
            arr_textureRegion[7] = arr_textureRegion[8];
            arr_textureRegion[2] = null;
            arr_textureRegion[5] = null;
            arr_textureRegion[8] = null;
        }
        if(v2 == 0 && v5 == 0) {
            arr_textureRegion[3] = arr_textureRegion[6];
            arr_textureRegion[4] = arr_textureRegion[7];
            arr_textureRegion[5] = arr_textureRegion[8];
            arr_textureRegion[6] = null;
            arr_textureRegion[7] = null;
            arr_textureRegion[8] = null;
        }
        this.load(arr_textureRegion);
    }

    public NinePatch(TextureRegion textureRegion0, Color color0) {
        this(textureRegion0);
        this.setColor(color0);
    }

    public NinePatch(TextureRegion[] arr_textureRegion) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        if(arr_textureRegion == null || arr_textureRegion.length != 9) {
            throw new IllegalArgumentException("NinePatch needs nine TextureRegions");
        }
        this.load(arr_textureRegion);
        if(arr_textureRegion[0] != null && ((float)arr_textureRegion[0].getRegionWidth()) != this.leftWidth || arr_textureRegion[3] != null && ((float)arr_textureRegion[3].getRegionWidth()) != this.leftWidth || arr_textureRegion[6] != null && ((float)arr_textureRegion[6].getRegionWidth()) != this.leftWidth) {
            throw new GdxRuntimeException("Left side patches must have the same width");
        }
        if(arr_textureRegion[2] != null && ((float)arr_textureRegion[2].getRegionWidth()) != this.rightWidth || arr_textureRegion[5] != null && ((float)arr_textureRegion[5].getRegionWidth()) != this.rightWidth || arr_textureRegion[8] != null && ((float)arr_textureRegion[8].getRegionWidth()) != this.rightWidth) {
            throw new GdxRuntimeException("Right side patches must have the same width");
        }
        if(arr_textureRegion[6] != null && ((float)arr_textureRegion[6].getRegionHeight()) != this.bottomHeight || arr_textureRegion[7] != null && ((float)arr_textureRegion[7].getRegionHeight()) != this.bottomHeight || arr_textureRegion[8] != null && ((float)arr_textureRegion[8].getRegionHeight()) != this.bottomHeight) {
            throw new GdxRuntimeException("Bottom side patches must have the same height");
        }
        if(arr_textureRegion[0] != null && ((float)arr_textureRegion[0].getRegionHeight()) != this.topHeight || arr_textureRegion[1] != null && ((float)arr_textureRegion[1].getRegionHeight()) != this.topHeight || arr_textureRegion[2] != null && ((float)arr_textureRegion[2].getRegionHeight()) != this.topHeight) {
            throw new GdxRuntimeException("Top side patches must have the same height");
        }
    }

    private int add(TextureRegion textureRegion0, boolean z, boolean z1) {
        Texture texture0 = this.texture;
        if(texture0 == null) {
            this.texture = textureRegion0.getTexture();
        }
        else if(texture0 == textureRegion0.getTexture()) {
        }
        else {
            throw new IllegalArgumentException("All regions must be from the same texture.");
        }
        float f = textureRegion0.u;
        float f1 = textureRegion0.v2;
        float f2 = textureRegion0.u2;
        float f3 = textureRegion0.v;
        if(this.texture.getMagFilter() == TextureFilter.Linear || this.texture.getMinFilter() == TextureFilter.Linear) {
            if(z) {
                float f4 = 0.5f / ((float)this.texture.getWidth());
                f += f4;
                f2 -= f4;
            }
            if(z1) {
                float f5 = 0.5f / ((float)this.texture.getHeight());
                f1 -= f5;
                f3 += f5;
            }
        }
        float[] arr_f = this.vertices;
        int v = this.idx;
        arr_f[v + 3] = f;
        arr_f[v + 4] = f1;
        arr_f[v + 8] = f;
        arr_f[v + 9] = f3;
        arr_f[v + 13] = f2;
        arr_f[v + 14] = f3;
        arr_f[v + 18] = f2;
        arr_f[v + 19] = f1;
        this.idx = v + 20;
        return v;
    }

    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        this.prepareVertices(batch0, f, f1, f2, f3);
        batch0.draw(this.texture, this.vertices, 0, this.idx);
    }

    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.prepareVertices(batch0, f, f1, f4, f5);
        float f9 = f + f2;
        float f10 = f1 + f3;
        int v = this.idx;
        float[] arr_f = this.vertices;
        if(f8 != 0.0f) {
            for(int v1 = 0; v1 < v; v1 += 5) {
                float f11 = (arr_f[v1] - f9) * f6;
                float f12 = (arr_f[v1 + 1] - f10) * f7;
                float f13 = MathUtils.cosDeg(f8);
                float f14 = MathUtils.sinDeg(f8);
                arr_f[v1] = f13 * f11 - f14 * f12 + f9;
                arr_f[v1 + 1] = f14 * f11 + f13 * f12 + f10;
            }
        }
        else if(f6 != 1.0f || f7 != 1.0f) {
            for(int v2 = 0; v2 < v; v2 += 5) {
                arr_f[v2] = (arr_f[v2] - f9) * f6 + f9;
                arr_f[v2 + 1] = (arr_f[v2 + 1] - f10) * f7 + f10;
            }
        }
        batch0.draw(this.texture, arr_f, 0, v);
    }

    public float getBottomHeight() {
        return this.bottomHeight;
    }

    public Color getColor() {
        return this.color;
    }

    public float getLeftWidth() {
        return this.leftWidth;
    }

    public float getMiddleHeight() {
        return this.middleHeight;
    }

    public float getMiddleWidth() {
        return this.middleWidth;
    }

    public float getPadBottom() {
        return this.padBottom == -1.0f ? this.getBottomHeight() : this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft == -1.0f ? this.getLeftWidth() : this.padLeft;
    }

    public float getPadRight() {
        return this.padRight == -1.0f ? this.getRightWidth() : this.padRight;
    }

    public float getPadTop() {
        return this.padTop == -1.0f ? this.getTopHeight() : this.padTop;
    }

    public float getRightWidth() {
        return this.rightWidth;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public float getTopHeight() {
        return this.topHeight;
    }

    public float getTotalHeight() {
        return this.topHeight + this.middleHeight + this.bottomHeight;
    }

    public float getTotalWidth() {
        return this.leftWidth + this.middleWidth + this.rightWidth;
    }

    private void load(TextureRegion[] arr_textureRegion) {
        if(arr_textureRegion[6] == null) {
            this.bottomLeft = -1;
        }
        else {
            this.bottomLeft = this.add(arr_textureRegion[6], false, false);
            this.leftWidth = (float)arr_textureRegion[6].getRegionWidth();
            this.bottomHeight = (float)arr_textureRegion[6].getRegionHeight();
        }
        if(arr_textureRegion[7] == null) {
            this.bottomCenter = -1;
        }
        else {
            this.bottomCenter = this.add(arr_textureRegion[7], arr_textureRegion[6] != null || arr_textureRegion[8] != null, false);
            this.middleWidth = Math.max(this.middleWidth, arr_textureRegion[7].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, arr_textureRegion[7].getRegionHeight());
        }
        if(arr_textureRegion[8] == null) {
            this.bottomRight = -1;
        }
        else {
            this.bottomRight = this.add(arr_textureRegion[8], false, false);
            this.rightWidth = Math.max(this.rightWidth, arr_textureRegion[8].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, arr_textureRegion[8].getRegionHeight());
        }
        if(arr_textureRegion[3] == null) {
            this.middleLeft = -1;
        }
        else {
            this.middleLeft = this.add(arr_textureRegion[3], false, arr_textureRegion[0] != null || arr_textureRegion[6] != null);
            this.leftWidth = Math.max(this.leftWidth, arr_textureRegion[3].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, arr_textureRegion[3].getRegionHeight());
        }
        if(arr_textureRegion[4] == null) {
            this.middleCenter = -1;
        }
        else {
            this.middleCenter = this.add(arr_textureRegion[4], arr_textureRegion[3] != null || arr_textureRegion[5] != null, arr_textureRegion[1] != null || arr_textureRegion[7] != null);
            this.middleWidth = Math.max(this.middleWidth, arr_textureRegion[4].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, arr_textureRegion[4].getRegionHeight());
        }
        if(arr_textureRegion[5] == null) {
            this.middleRight = -1;
        }
        else {
            this.middleRight = this.add(arr_textureRegion[5], false, arr_textureRegion[2] != null || arr_textureRegion[8] != null);
            this.rightWidth = Math.max(this.rightWidth, arr_textureRegion[5].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, arr_textureRegion[5].getRegionHeight());
        }
        if(arr_textureRegion[0] == null) {
            this.topLeft = -1;
        }
        else {
            this.topLeft = this.add(arr_textureRegion[0], false, false);
            this.leftWidth = Math.max(this.leftWidth, arr_textureRegion[0].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, arr_textureRegion[0].getRegionHeight());
        }
        if(arr_textureRegion[1] == null) {
            this.topCenter = -1;
        }
        else {
            this.topCenter = this.add(arr_textureRegion[1], arr_textureRegion[0] != null || arr_textureRegion[2] != null, false);
            this.middleWidth = Math.max(this.middleWidth, arr_textureRegion[1].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, arr_textureRegion[1].getRegionHeight());
        }
        if(arr_textureRegion[2] == null) {
            this.topRight = -1;
        }
        else {
            this.topRight = this.add(arr_textureRegion[2], false, false);
            this.rightWidth = Math.max(this.rightWidth, arr_textureRegion[2].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, arr_textureRegion[2].getRegionHeight());
        }
        int v = this.idx;
        float[] arr_f = this.vertices;
        if(v < arr_f.length) {
            float[] arr_f1 = new float[v];
            System.arraycopy(arr_f, 0, arr_f1, 0, v);
            this.vertices = arr_f1;
        }
    }

    private void prepareVertices(Batch batch0, float f, float f1, float f2, float f3) {
        float f4 = f + this.leftWidth;
        float f5 = f1 + this.bottomHeight;
        float f6 = f2 - this.rightWidth - this.leftWidth;
        float f7 = f3 - this.topHeight - this.bottomHeight;
        float f8 = f + f2 - this.rightWidth;
        float f9 = f1 + f3 - this.topHeight;
        float f10 = NinePatch.tmpDrawColor.set(this.color).mul(batch0.getColor()).toFloatBits();
        int v = this.bottomLeft;
        if(v != -1) {
            this.set(v, f, f1, this.leftWidth, this.bottomHeight, f10);
        }
        int v1 = this.bottomCenter;
        if(v1 != -1) {
            this.set(v1, f4, f1, f6, this.bottomHeight, f10);
        }
        int v2 = this.bottomRight;
        if(v2 != -1) {
            this.set(v2, f8, f1, this.rightWidth, this.bottomHeight, f10);
        }
        int v3 = this.middleLeft;
        if(v3 != -1) {
            this.set(v3, f, f5, this.leftWidth, f7, f10);
        }
        int v4 = this.middleCenter;
        if(v4 != -1) {
            this.set(v4, f4, f5, f6, f7, f10);
        }
        int v5 = this.middleRight;
        if(v5 != -1) {
            this.set(v5, f8, f5, this.rightWidth, f7, f10);
        }
        int v6 = this.topLeft;
        if(v6 != -1) {
            this.set(v6, f, f9, this.leftWidth, this.topHeight, f10);
        }
        int v7 = this.topCenter;
        if(v7 != -1) {
            this.set(v7, f4, f9, f6, this.topHeight, f10);
        }
        int v8 = this.topRight;
        if(v8 != -1) {
            this.set(v8, f8, f9, this.rightWidth, this.topHeight, f10);
        }
    }

    public void scale(float f, float f1) {
        this.leftWidth *= f;
        this.rightWidth *= f;
        this.topHeight *= f1;
        this.bottomHeight *= f1;
        this.middleWidth *= f;
        this.middleHeight *= f1;
        float f2 = this.padLeft;
        if(f2 != -1.0f) {
            this.padLeft = f2 * f;
        }
        float f3 = this.padRight;
        if(f3 != -1.0f) {
            this.padRight = f3 * f;
        }
        float f4 = this.padTop;
        if(f4 != -1.0f) {
            this.padTop = f4 * f1;
        }
        float f5 = this.padBottom;
        if(f5 != -1.0f) {
            this.padBottom = f5 * f1;
        }
    }

    private void set(int v, float f, float f1, float f2, float f3, float f4) {
        float f5 = f2 + f;
        float f6 = f3 + f1;
        float[] arr_f = this.vertices;
        arr_f[v] = f;
        arr_f[v + 1] = f1;
        arr_f[v + 2] = f4;
        arr_f[v + 5] = f;
        arr_f[v + 6] = f6;
        arr_f[v + 7] = f4;
        arr_f[v + 10] = f5;
        arr_f[v + 11] = f6;
        arr_f[v + 12] = f4;
        arr_f[v + 15] = f5;
        arr_f[v + 16] = f1;
        arr_f[v + 17] = f4;
    }

    public void setBottomHeight(float f) {
        this.bottomHeight = f;
    }

    public void setColor(Color color0) {
        this.color.set(color0);
    }

    public void setLeftWidth(float f) {
        this.leftWidth = f;
    }

    public void setMiddleHeight(float f) {
        this.middleHeight = f;
    }

    public void setMiddleWidth(float f) {
        this.middleWidth = f;
    }

    public void setPadBottom(float f) {
        this.padBottom = f;
    }

    public void setPadLeft(float f) {
        this.padLeft = f;
    }

    public void setPadRight(float f) {
        this.padRight = f;
    }

    public void setPadTop(float f) {
        this.padTop = f;
    }

    public void setPadding(float f, float f1, float f2, float f3) {
        this.padLeft = f;
        this.padRight = f1;
        this.padTop = f2;
        this.padBottom = f3;
    }

    public void setRightWidth(float f) {
        this.rightWidth = f;
    }

    public void setTopHeight(float f) {
        this.topHeight = f;
    }
}

