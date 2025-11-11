package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;

public class Decal {
    public static final int C1 = 3;
    public static final int C2 = 9;
    public static final int C3 = 15;
    public static final int C4 = 21;
    public static final int SIZE = 24;
    public static final int U1 = 4;
    public static final int U2 = 10;
    public static final int U3 = 16;
    public static final int U4 = 22;
    public static final int V1 = 5;
    public static final int V2 = 11;
    public static final int V3 = 17;
    public static final int V4 = 23;
    private static final int VERTEX_SIZE = 6;
    public static final int X1 = 0;
    public static final int X2 = 6;
    public static final int X3 = 12;
    public static final int X4 = 18;
    public static final int Y1 = 1;
    public static final int Y2 = 7;
    public static final int Y3 = 13;
    public static final int Y4 = 19;
    public static final int Z1 = 2;
    public static final int Z2 = 8;
    public static final int Z3 = 14;
    public static final int Z4 = 20;
    protected Color color;
    protected Vector2 dimensions;
    static final Vector3 dir;
    protected DecalMaterial material;
    protected Vector3 position;
    protected Quaternion rotation;
    protected static Quaternion rotator;
    protected Vector2 scale;
    private static Vector3 tmp;
    private static Vector3 tmp2;
    public Vector2 transformationOffset;
    protected boolean updated;
    public int value;
    protected float[] vertices;

    static {
        Decal.tmp = new Vector3();
        Decal.tmp2 = new Vector3();
        Decal.dir = new Vector3();
        Decal.rotator = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public Decal() {
        this.vertices = new float[24];
        this.position = new Vector3();
        this.rotation = new Quaternion();
        this.scale = new Vector2(1.0f, 1.0f);
        this.color = new Color();
        this.transformationOffset = null;
        this.dimensions = new Vector2();
        this.updated = false;
        this.material = new DecalMaterial();
    }

    public Decal(DecalMaterial decalMaterial0) {
        this.vertices = new float[24];
        this.position = new Vector3();
        this.rotation = new Quaternion();
        this.scale = new Vector2(1.0f, 1.0f);
        this.color = new Color();
        this.transformationOffset = null;
        this.dimensions = new Vector2();
        this.updated = false;
        this.material = decalMaterial0;
    }

    public Color getColor() {
        return this.color;
    }

    public float getHeight() {
        return this.dimensions.y;
    }

    public DecalMaterial getMaterial() {
        return this.material;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public Quaternion getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scale.x;
    }

    public float getScaleY() {
        return this.scale.y;
    }

    public TextureRegion getTextureRegion() {
        return this.material.textureRegion;
    }

    public float[] getVertices() {
        this.update();
        return this.vertices;
    }

    public float getWidth() {
        return this.dimensions.x;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public float getZ() {
        return this.position.z;
    }

    public void lookAt(Vector3 vector30, Vector3 vector31) {
        Decal.dir.set(vector30).sub(this.position).nor();
        this.setRotation(Decal.dir, vector31);
    }

    public static Decal newDecal(float f, float f1, TextureRegion textureRegion0) {
        return Decal.newDecal(f, f1, textureRegion0, -1, -1);
    }

    public static Decal newDecal(float f, float f1, TextureRegion textureRegion0, int v, int v1) {
        Decal decal0 = new Decal();
        decal0.setTextureRegion(textureRegion0);
        decal0.setBlending(v, v1);
        decal0.dimensions.x = f;
        decal0.dimensions.y = f1;
        decal0.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        return decal0;
    }

    public static Decal newDecal(float f, float f1, TextureRegion textureRegion0, int v, int v1, DecalMaterial decalMaterial0) {
        Decal decal0 = new Decal(decalMaterial0);
        decal0.setTextureRegion(textureRegion0);
        decal0.setBlending(v, v1);
        decal0.dimensions.x = f;
        decal0.dimensions.y = f1;
        decal0.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        return decal0;
    }

    // 去混淆评级： 低(40)
    public static Decal newDecal(float f, float f1, TextureRegion textureRegion0, boolean z) {
        return Decal.newDecal(f, f1, textureRegion0, (z ? 770 : -1), (z ? 0x303 : -1));
    }

    public static Decal newDecal(TextureRegion textureRegion0) {
        return Decal.newDecal(textureRegion0.getRegionWidth(), textureRegion0.getRegionHeight(), textureRegion0, -1, -1);
    }

    // 去混淆评级： 低(40)
    public static Decal newDecal(TextureRegion textureRegion0, boolean z) {
        return Decal.newDecal(textureRegion0.getRegionWidth(), textureRegion0.getRegionHeight(), textureRegion0, (z ? 770 : -1), (z ? 0x303 : -1));
    }

    protected void resetVertices() {
        float f = -this.dimensions.x / 2.0f;
        float f1 = this.dimensions.x + f;
        float f2 = this.dimensions.y / 2.0f;
        float f3 = f2 - this.dimensions.y;
        float[] arr_f = this.vertices;
        arr_f[0] = f;
        arr_f[1] = f2;
        arr_f[2] = 0.0f;
        arr_f[6] = f1;
        arr_f[7] = f2;
        arr_f[8] = 0.0f;
        arr_f[12] = f;
        arr_f[13] = f3;
        arr_f[14] = 0.0f;
        arr_f[18] = f1;
        arr_f[19] = f3;
        arr_f[20] = 0.0f;
        this.updated = false;
    }

    public void rotateX(float f) {
        Decal.rotator.set(Vector3.X, f);
        this.rotation.mul(Decal.rotator);
        this.updated = false;
    }

    public void rotateY(float f) {
        Decal.rotator.set(Vector3.Y, f);
        this.rotation.mul(Decal.rotator);
        this.updated = false;
    }

    public void rotateZ(float f) {
        Decal.rotator.set(Vector3.Z, f);
        this.rotation.mul(Decal.rotator);
        this.updated = false;
    }

    public void setBlending(int v, int v1) {
        this.material.srcBlendFactor = v;
        this.material.dstBlendFactor = v1;
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
        float f4 = NumberUtils.intToFloatColor(((int)(f * 255.0f)) | (((int)(f1 * 255.0f)) << 8 | (((int)(f2 * 255.0f)) << 16 | ((int)(f3 * 255.0f)) << 24)));
        float[] arr_f = this.vertices;
        arr_f[3] = f4;
        arr_f[9] = f4;
        arr_f[15] = f4;
        arr_f[21] = f4;
    }

    public void setColor(Color color0) {
        this.color.set(color0);
        float f = color0.toFloatBits();
        float[] arr_f = this.vertices;
        arr_f[3] = f;
        arr_f[9] = f;
        arr_f[15] = f;
        arr_f[21] = f;
    }

    public void setDimensions(float f, float f1) {
        this.dimensions.set(f, f1);
        this.updated = false;
    }

    public void setHeight(float f) {
        this.dimensions.y = f;
        this.updated = false;
    }

    public void setMaterial(DecalMaterial decalMaterial0) {
        this.material = decalMaterial0;
    }

    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        float[] arr_f = this.vertices;
        arr_f[3] = f;
        arr_f[9] = f;
        arr_f[15] = f;
        arr_f[21] = f;
    }

    public void setPosition(float f, float f1, float f2) {
        this.position.set(f, f1, f2);
        this.updated = false;
    }

    public void setPosition(Vector3 vector30) {
        this.position.set(vector30);
        this.updated = false;
    }

    public void setRotation(float f, float f1, float f2) {
        this.rotation.setEulerAngles(f, f1, f2);
        this.updated = false;
    }

    public void setRotation(Quaternion quaternion0) {
        this.rotation.set(quaternion0);
        this.updated = false;
    }

    public void setRotation(Vector3 vector30, Vector3 vector31) {
        Decal.tmp.set(vector31).crs(vector30).nor();
        Decal.tmp2.set(vector30).crs(Decal.tmp).nor();
        this.rotation.setFromAxes(Decal.tmp.x, Decal.tmp2.x, vector30.x, Decal.tmp.y, Decal.tmp2.y, vector30.y, Decal.tmp.z, Decal.tmp2.z, vector30.z);
        this.updated = false;
    }

    public void setRotationX(float f) {
        this.rotation.set(Vector3.X, f);
        this.updated = false;
    }

    public void setRotationY(float f) {
        this.rotation.set(Vector3.Y, f);
        this.updated = false;
    }

    public void setRotationZ(float f) {
        this.rotation.set(Vector3.Z, f);
        this.updated = false;
    }

    public void setScale(float f) {
        this.scale.set(f, f);
        this.updated = false;
    }

    public void setScale(float f, float f1) {
        this.scale.set(f, f1);
        this.updated = false;
    }

    public void setScaleX(float f) {
        this.scale.x = f;
        this.updated = false;
    }

    public void setScaleY(float f) {
        this.scale.y = f;
        this.updated = false;
    }

    public void setTextureRegion(TextureRegion textureRegion0) {
        this.material.textureRegion = textureRegion0;
        this.updateUVs();
    }

    public void setWidth(float f) {
        this.dimensions.x = f;
        this.updated = false;
    }

    public void setX(float f) {
        this.position.x = f;
        this.updated = false;
    }

    public void setY(float f) {
        this.position.y = f;
        this.updated = false;
    }

    public void setZ(float f) {
        this.position.z = f;
        this.updated = false;
    }

    protected void transformVertices() {
        float f1;
        float f;
        Vector2 vector20 = this.transformationOffset;
        if(vector20 == null) {
            f = 0.0f;
            f1 = 0.0f;
        }
        else {
            f = -vector20.x;
            f1 = -this.transformationOffset.y;
        }
        float f2 = (this.vertices[0] + f) * this.scale.x;
        float f3 = (this.vertices[1] + f1) * this.scale.y;
        float[] arr_f = this.vertices;
        float f4 = arr_f[2];
        arr_f[0] = this.rotation.w * f2 + this.rotation.y * f4 - this.rotation.z * f3;
        this.vertices[1] = this.rotation.w * f3 + this.rotation.z * f2 - this.rotation.x * f4;
        this.vertices[2] = this.rotation.w * f4 + this.rotation.x * f3 - this.rotation.y * f2;
        float f5 = -this.rotation.x * f2 - this.rotation.y * f3 - this.rotation.z * f4;
        this.rotation.conjugate();
        float[] arr_f1 = this.vertices;
        float f6 = arr_f1[0];
        float f7 = arr_f1[1];
        float f8 = arr_f1[2];
        arr_f1[0] = this.rotation.x * f5 + this.rotation.w * f6 + this.rotation.z * f7 - this.rotation.y * f8;
        this.vertices[1] = this.rotation.y * f5 + this.rotation.w * f7 + this.rotation.x * f8 - this.rotation.z * f6;
        this.vertices[2] = f5 * this.rotation.z + f8 * this.rotation.w + f6 * this.rotation.y - f7 * this.rotation.x;
        this.rotation.conjugate();
        this.vertices[0] += this.position.x - f;
        this.vertices[1] += this.position.y - f1;
        this.vertices[2] += this.position.z;
        float f9 = (this.vertices[6] + f) * this.scale.x;
        float f10 = (this.vertices[7] + f1) * this.scale.y;
        float[] arr_f2 = this.vertices;
        float f11 = arr_f2[8];
        arr_f2[6] = this.rotation.w * f9 + this.rotation.y * f11 - this.rotation.z * f10;
        this.vertices[7] = this.rotation.w * f10 + this.rotation.z * f9 - this.rotation.x * f11;
        this.vertices[8] = this.rotation.w * f11 + this.rotation.x * f10 - this.rotation.y * f9;
        float f12 = -this.rotation.x * f9 - this.rotation.y * f10 - this.rotation.z * f11;
        this.rotation.conjugate();
        float[] arr_f3 = this.vertices;
        float f13 = arr_f3[6];
        float f14 = arr_f3[7];
        float f15 = arr_f3[8];
        arr_f3[6] = this.rotation.x * f12 + this.rotation.w * f13 + this.rotation.z * f14 - this.rotation.y * f15;
        this.vertices[7] = this.rotation.y * f12 + this.rotation.w * f14 + this.rotation.x * f15 - this.rotation.z * f13;
        this.vertices[8] = f12 * this.rotation.z + f15 * this.rotation.w + f13 * this.rotation.y - f14 * this.rotation.x;
        this.rotation.conjugate();
        this.vertices[6] += this.position.x - f;
        this.vertices[7] += this.position.y - f1;
        this.vertices[8] += this.position.z;
        float f16 = (this.vertices[12] + f) * this.scale.x;
        float f17 = (this.vertices[13] + f1) * this.scale.y;
        float[] arr_f4 = this.vertices;
        float f18 = arr_f4[14];
        arr_f4[12] = this.rotation.w * f16 + this.rotation.y * f18 - this.rotation.z * f17;
        this.vertices[13] = this.rotation.w * f17 + this.rotation.z * f16 - this.rotation.x * f18;
        this.vertices[14] = this.rotation.w * f18 + this.rotation.x * f17 - this.rotation.y * f16;
        float f19 = -this.rotation.x * f16 - this.rotation.y * f17 - this.rotation.z * f18;
        this.rotation.conjugate();
        float[] arr_f5 = this.vertices;
        float f20 = arr_f5[12];
        float f21 = arr_f5[13];
        float f22 = arr_f5[14];
        arr_f5[12] = this.rotation.x * f19 + this.rotation.w * f20 + this.rotation.z * f21 - this.rotation.y * f22;
        this.vertices[13] = this.rotation.y * f19 + this.rotation.w * f21 + this.rotation.x * f22 - this.rotation.z * f20;
        this.vertices[14] = f19 * this.rotation.z + f22 * this.rotation.w + f20 * this.rotation.y - f21 * this.rotation.x;
        this.rotation.conjugate();
        this.vertices[12] += this.position.x - f;
        this.vertices[13] += this.position.y - f1;
        this.vertices[14] += this.position.z;
        float f23 = (this.vertices[18] + f) * this.scale.x;
        float f24 = (this.vertices[19] + f1) * this.scale.y;
        float[] arr_f6 = this.vertices;
        float f25 = arr_f6[20];
        arr_f6[18] = this.rotation.w * f23 + this.rotation.y * f25 - this.rotation.z * f24;
        this.vertices[19] = this.rotation.w * f24 + this.rotation.z * f23 - this.rotation.x * f25;
        this.vertices[20] = this.rotation.w * f25 + this.rotation.x * f24 - this.rotation.y * f23;
        float f26 = -this.rotation.x * f23 - this.rotation.y * f24 - this.rotation.z * f25;
        this.rotation.conjugate();
        float[] arr_f7 = this.vertices;
        float f27 = arr_f7[18];
        float f28 = arr_f7[19];
        float f29 = arr_f7[20];
        arr_f7[18] = this.rotation.x * f26 + this.rotation.w * f27 + this.rotation.z * f28 - this.rotation.y * f29;
        this.vertices[19] = this.rotation.y * f26 + this.rotation.w * f28 + this.rotation.x * f29 - this.rotation.z * f27;
        this.vertices[20] = f26 * this.rotation.z + f29 * this.rotation.w + f27 * this.rotation.y - f28 * this.rotation.x;
        this.rotation.conjugate();
        this.vertices[18] += this.position.x - f;
        this.vertices[19] += this.position.y - f1;
        this.vertices[20] += this.position.z;
        this.updated = true;
    }

    public void translate(float f, float f1, float f2) {
        this.position.add(f, f1, f2);
        this.updated = false;
    }

    public void translate(Vector3 vector30) {
        this.position.add(vector30);
        this.updated = false;
    }

    public void translateX(float f) {
        this.position.x += f;
        this.updated = false;
    }

    public void translateY(float f) {
        this.position.y += f;
        this.updated = false;
    }

    public void translateZ(float f) {
        this.position.z += f;
        this.updated = false;
    }

    protected void update() {
        if(!this.updated) {
            this.resetVertices();
            this.transformVertices();
        }
    }

    protected void updateUVs() {
        TextureRegion textureRegion0 = this.material.textureRegion;
        this.vertices[4] = textureRegion0.getU();
        this.vertices[5] = textureRegion0.getV();
        this.vertices[10] = textureRegion0.getU2();
        this.vertices[11] = textureRegion0.getV();
        this.vertices[16] = textureRegion0.getU();
        this.vertices[17] = textureRegion0.getV2();
        this.vertices[22] = textureRegion0.getU2();
        this.vertices[23] = textureRegion0.getV2();
    }
}

