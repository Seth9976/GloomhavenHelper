package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.NumberUtils;

public class Sprite extends TextureRegion {
    static final int SPRITE_SIZE = 20;
    static final int VERTEX_SIZE = 5;
    private Rectangle bounds;
    private final Color color;
    private boolean dirty;
    float height;
    private float originX;
    private float originY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    final float[] vertices;
    float width;
    private float x;
    private float y;

    public Sprite() {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        this.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public Sprite(Texture texture0) {
        this(texture0, 0, 0, texture0.getWidth(), texture0.getHeight());
    }

    public Sprite(Texture texture0, int v, int v1) {
        this(texture0, 0, 0, v, v1);
    }

    public Sprite(Texture texture0, int v, int v1, int v2, int v3) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        if(texture0 == null) {
            throw new IllegalArgumentException("texture cannot be null.");
        }
        this.texture = texture0;
        this.setRegion(v, v1, v2, v3);
        this.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.setSize(((float)Math.abs(v2)), ((float)Math.abs(v3)));
        this.setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public Sprite(Sprite sprite0) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        this.set(sprite0);
    }

    public Sprite(TextureRegion textureRegion0) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        this.setRegion(textureRegion0);
        this.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.setSize(((float)textureRegion0.getRegionWidth()), ((float)textureRegion0.getRegionHeight()));
        this.setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public Sprite(TextureRegion textureRegion0, int v, int v1, int v2, int v3) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        this.setRegion(textureRegion0, v, v1, v2, v3);
        this.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.setSize(((float)Math.abs(v2)), ((float)Math.abs(v3)));
        this.setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public void draw(Batch batch0) {
        batch0.draw(this.texture, this.getVertices(), 0, 20);
    }

    public void draw(Batch batch0, float f) {
        float f1 = this.getColor().a;
        this.setAlpha(f * f1);
        this.draw(batch0);
        this.setAlpha(f1);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void flip(boolean z, boolean z1) {
        super.flip(z, z1);
        float[] arr_f = this.vertices;
        if(z) {
            float f = arr_f[3];
            arr_f[3] = arr_f[13];
            arr_f[13] = f;
            float f1 = arr_f[8];
            arr_f[8] = arr_f[18];
            arr_f[18] = f1;
        }
        if(z1) {
            float f2 = arr_f[4];
            arr_f[4] = arr_f[14];
            arr_f[14] = f2;
            float f3 = arr_f[9];
            arr_f[9] = arr_f[19];
            arr_f[19] = f3;
        }
    }

    public Rectangle getBoundingRectangle() {
        float[] arr_f = this.getVertices();
        float f = arr_f[0];
        float f1 = arr_f[1];
        float f2 = arr_f[0];
        float f3 = arr_f[1];
        if(f > arr_f[5]) {
            f = arr_f[5];
        }
        if(f > arr_f[10]) {
            f = arr_f[10];
        }
        if(f > arr_f[15]) {
            f = arr_f[15];
        }
        if(f2 < arr_f[5]) {
            f2 = arr_f[5];
        }
        if(f2 < arr_f[10]) {
            f2 = arr_f[10];
        }
        if(f2 < arr_f[15]) {
            f2 = arr_f[15];
        }
        if(f1 > arr_f[6]) {
            f1 = arr_f[6];
        }
        if(f1 > arr_f[11]) {
            f1 = arr_f[11];
        }
        if(f1 > arr_f[16]) {
            f1 = arr_f[16];
        }
        if(f3 < arr_f[6]) {
            f3 = arr_f[6];
        }
        if(f3 < arr_f[11]) {
            f3 = arr_f[11];
        }
        if(f3 < arr_f[16]) {
            f3 = arr_f[16];
        }
        if(this.bounds == null) {
            this.bounds = new Rectangle();
        }
        this.bounds.x = f;
        this.bounds.y = f1;
        this.bounds.width = f2 - f;
        this.bounds.height = f3 - f1;
        return this.bounds;
    }

    public Color getColor() {
        int v = NumberUtils.floatToIntColor(this.vertices[2]);
        this.color.r = ((float)(v & 0xFF)) / 255.0f;
        this.color.g = ((float)(v >>> 8 & 0xFF)) / 255.0f;
        this.color.b = ((float)(v >>> 16 & 0xFF)) / 255.0f;
        this.color.a = ((float)(v >>> 24 & 0xFF)) / 255.0f;
        return this.color;
    }

    public float getHeight() {
        return this.height;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public float[] getVertices() {
        if(this.dirty) {
            this.dirty = false;
            float[] arr_f = this.vertices;
            float f = -this.originX;
            float f1 = -this.originY;
            float f2 = this.width + f;
            float f3 = this.height + f1;
            float f4 = this.x - f;
            float f5 = this.y - f1;
            if(this.scaleX != 1.0f || this.scaleY != 1.0f) {
                f *= this.scaleX;
                f1 *= this.scaleY;
                f2 *= this.scaleX;
                f3 *= this.scaleY;
            }
            float f6 = this.rotation;
            if(f6 != 0.0f) {
                float f7 = MathUtils.cosDeg(f6);
                float f8 = MathUtils.sinDeg(this.rotation);
                float f9 = f * f7;
                float f10 = f * f8;
                float f11 = f7 * f3;
                float f12 = f3 * f8;
                float f13 = f9 - f1 * f8 + f4;
                float f14 = f1 * f7 + f10 + f5;
                arr_f[0] = f13;
                arr_f[1] = f14;
                float f15 = f9 - f12 + f4;
                float f16 = f10 + f11 + f5;
                arr_f[5] = f15;
                arr_f[6] = f16;
                float f17 = f2 * f7 - f12 + f4;
                float f18 = f11 + f2 * f8 + f5;
                arr_f[10] = f17;
                arr_f[11] = f18;
                arr_f[15] = f13 + (f17 - f15);
                arr_f[16] = f18 - (f16 - f14);
                return this.vertices;
            }
            float f19 = f + f4;
            float f20 = f1 + f5;
            float f21 = f2 + f4;
            float f22 = f3 + f5;
            arr_f[0] = f19;
            arr_f[1] = f20;
            arr_f[5] = f19;
            arr_f[6] = f22;
            arr_f[10] = f21;
            arr_f[11] = f22;
            arr_f[15] = f21;
            arr_f[16] = f20;
        }
        return this.vertices;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void rotate(float f) {
        if(f == 0.0f) {
            return;
        }
        this.rotation += f;
        this.dirty = true;
    }

    public void rotate90(boolean z) {
        float[] arr_f = this.vertices;
        if(z) {
            float f = arr_f[4];
            arr_f[4] = arr_f[19];
            arr_f[19] = arr_f[14];
            arr_f[14] = arr_f[9];
            arr_f[9] = f;
            float f1 = arr_f[3];
            arr_f[3] = arr_f[18];
            arr_f[18] = arr_f[13];
            arr_f[13] = arr_f[8];
            arr_f[8] = f1;
            return;
        }
        float f2 = arr_f[4];
        arr_f[4] = arr_f[9];
        arr_f[9] = arr_f[14];
        arr_f[14] = arr_f[19];
        arr_f[19] = f2;
        float f3 = arr_f[3];
        arr_f[3] = arr_f[8];
        arr_f[8] = arr_f[13];
        arr_f[13] = arr_f[18];
        arr_f[18] = f3;
    }

    public void scale(float f) {
        this.scaleX += f;
        this.scaleY += f;
        this.dirty = true;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void scroll(float f, float f1) {
        float[] arr_f = this.vertices;
        if(f != 0.0f) {
            float f2 = (arr_f[3] + f) % 1.0f;
            float f3 = this.width / ((float)this.texture.getWidth()) + f2;
            this.u = f2;
            this.u2 = f3;
            arr_f[3] = f2;
            arr_f[8] = f2;
            arr_f[13] = f3;
            arr_f[18] = f3;
        }
        if(f1 != 0.0f) {
            float f4 = (arr_f[9] + f1) % 1.0f;
            float f5 = this.height / ((float)this.texture.getHeight()) + f4;
            this.v = f4;
            this.v2 = f5;
            arr_f[4] = f5;
            arr_f[9] = f4;
            arr_f[14] = f4;
            arr_f[19] = f5;
        }
    }

    public void set(Sprite sprite0) {
        if(sprite0 == null) {
            throw new IllegalArgumentException("sprite cannot be null.");
        }
        System.arraycopy(sprite0.vertices, 0, this.vertices, 0, 20);
        this.texture = sprite0.texture;
        this.u = sprite0.u;
        this.v = sprite0.v;
        this.u2 = sprite0.u2;
        this.v2 = sprite0.v2;
        this.x = sprite0.x;
        this.y = sprite0.y;
        this.width = sprite0.width;
        this.height = sprite0.height;
        this.regionWidth = sprite0.regionWidth;
        this.regionHeight = sprite0.regionHeight;
        this.originX = sprite0.originX;
        this.originY = sprite0.originY;
        this.rotation = sprite0.rotation;
        this.scaleX = sprite0.scaleX;
        this.scaleY = sprite0.scaleY;
        this.color.set(sprite0.color);
        this.dirty = sprite0.dirty;
    }

    public void setAlpha(float f) {
        this.color.a = f;
        float f1 = this.color.toFloatBits();
        this.vertices[2] = f1;
        this.vertices[7] = f1;
        this.vertices[12] = f1;
        this.vertices[17] = f1;
    }

    public void setBounds(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.width = f2;
        this.height = f3;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            float f4 = f2 + f;
            float f5 = f3 + f1;
            this.vertices[0] = f;
            this.vertices[1] = f1;
            this.vertices[5] = f;
            this.vertices[6] = f5;
            this.vertices[10] = f4;
            this.vertices[11] = f5;
            this.vertices[15] = f4;
            this.vertices[16] = f1;
            return;
        }
        this.dirty = true;
    }

    public void setCenter(float f, float f1) {
        this.setPosition(f - this.width / 2.0f, f1 - this.height / 2.0f);
    }

    public void setCenterX(float f) {
        this.setX(f - this.width / 2.0f);
    }

    public void setCenterY(float f) {
        this.setY(f - this.height / 2.0f);
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
        float f4 = this.color.toFloatBits();
        this.vertices[2] = f4;
        this.vertices[7] = f4;
        this.vertices[12] = f4;
        this.vertices[17] = f4;
    }

    public void setColor(Color color0) {
        this.color.set(color0);
        float f = color0.toFloatBits();
        this.vertices[2] = f;
        this.vertices[7] = f;
        this.vertices[12] = f;
        this.vertices[17] = f;
    }

    public void setFlip(boolean z, boolean z1) {
        boolean z2 = true;
        boolean z3 = this.isFlipX() != z;
        if(this.isFlipY() == z1) {
            z2 = false;
        }
        this.flip(z3, z2);
    }

    public void setOrigin(float f, float f1) {
        this.originX = f;
        this.originY = f1;
        this.dirty = true;
    }

    public void setOriginBasedPosition(float f, float f1) {
        this.setPosition(f - this.originX, f1 - this.originY);
    }

    public void setOriginCenter() {
        this.originX = this.width / 2.0f;
        this.originY = this.height / 2.0f;
        this.dirty = true;
    }

    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        this.vertices[2] = f;
        this.vertices[7] = f;
        this.vertices[12] = f;
        this.vertices[17] = f;
    }

    public void setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            float f2 = this.width + f;
            float f3 = this.height + f1;
            this.vertices[0] = f;
            this.vertices[1] = f1;
            this.vertices[5] = f;
            this.vertices[6] = f3;
            this.vertices[10] = f2;
            this.vertices[11] = f3;
            this.vertices[15] = f2;
            this.vertices[16] = f1;
            return;
        }
        this.dirty = true;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setRegion(float f, float f1, float f2, float f3) {
        super.setRegion(f, f1, f2, f3);
        this.vertices[3] = f;
        this.vertices[4] = f3;
        this.vertices[8] = f;
        this.vertices[9] = f1;
        this.vertices[13] = f2;
        this.vertices[14] = f1;
        this.vertices[18] = f2;
        this.vertices[19] = f3;
    }

    public void setRotation(float f) {
        this.rotation = f;
        this.dirty = true;
    }

    public void setScale(float f) {
        this.scaleX = f;
        this.scaleY = f;
        this.dirty = true;
    }

    public void setScale(float f, float f1) {
        this.scaleX = f;
        this.scaleY = f1;
        this.dirty = true;
    }

    public void setSize(float f, float f1) {
        this.width = f;
        this.height = f1;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            float f2 = this.x;
            float f3 = f + f2;
            float f4 = this.y;
            float f5 = f1 + f4;
            this.vertices[0] = f2;
            this.vertices[1] = f4;
            this.vertices[5] = f2;
            this.vertices[6] = f5;
            this.vertices[10] = f3;
            this.vertices[11] = f5;
            this.vertices[15] = f3;
            this.vertices[16] = f4;
            return;
        }
        this.dirty = true;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setU(float f) {
        super.setU(f);
        this.vertices[3] = f;
        this.vertices[8] = f;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setU2(float f) {
        super.setU2(f);
        this.vertices[13] = f;
        this.vertices[18] = f;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setV(float f) {
        super.setV(f);
        this.vertices[9] = f;
        this.vertices[14] = f;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setV2(float f) {
        super.setV2(f);
        this.vertices[4] = f;
        this.vertices[19] = f;
    }

    public void setX(float f) {
        this.x = f;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            float f1 = this.width + f;
            this.vertices[0] = f;
            this.vertices[5] = f;
            this.vertices[10] = f1;
            this.vertices[15] = f1;
            return;
        }
        this.dirty = true;
    }

    public void setY(float f) {
        this.y = f;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            float f1 = this.height + f;
            this.vertices[1] = f;
            this.vertices[6] = f1;
            this.vertices[11] = f1;
            this.vertices[16] = f;
            return;
        }
        this.dirty = true;
    }

    public void translate(float f, float f1) {
        this.x += f;
        this.y += f1;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            this.vertices[0] += f;
            this.vertices[1] += f1;
            this.vertices[5] += f;
            this.vertices[6] += f1;
            this.vertices[10] += f;
            this.vertices[11] += f1;
            this.vertices[15] += f;
            this.vertices[16] += f1;
            return;
        }
        this.dirty = true;
    }

    public void translateX(float f) {
        this.x += f;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            this.vertices[0] += f;
            this.vertices[5] += f;
            this.vertices[10] += f;
            this.vertices[15] += f;
            return;
        }
        this.dirty = true;
    }

    public void translateY(float f) {
        this.y += f;
        if(this.dirty) {
            return;
        }
        if(this.rotation == 0.0f && this.scaleX == 1.0f && this.scaleY == 1.0f) {
            this.vertices[1] += f;
            this.vertices[6] += f;
            this.vertices[11] += f;
            this.vertices[16] += f;
            return;
        }
        this.dirty = true;
    }
}

