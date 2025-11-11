package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;
import java.util.List;

public class BoundingBox implements Serializable {
    private final Vector3 cnt;
    private final Vector3 dim;
    public final Vector3 max;
    public final Vector3 min;
    private static final long serialVersionUID = 0xEE27145417307491L;
    private static final Vector3 tmpVector;

    static {
        BoundingBox.tmpVector = new Vector3();
    }

    public BoundingBox() {
        this.min = new Vector3();
        this.max = new Vector3();
        this.cnt = new Vector3();
        this.dim = new Vector3();
        this.clr();
    }

    public BoundingBox(Vector3 vector30, Vector3 vector31) {
        this.min = new Vector3();
        this.max = new Vector3();
        this.cnt = new Vector3();
        this.dim = new Vector3();
        this.set(vector30, vector31);
    }

    public BoundingBox(BoundingBox boundingBox0) {
        this.min = new Vector3();
        this.max = new Vector3();
        this.cnt = new Vector3();
        this.dim = new Vector3();
        this.set(boundingBox0);
    }

    public BoundingBox clr() {
        return this.set(this.min.set(0.0f, 0.0f, 0.0f), this.max.set(0.0f, 0.0f, 0.0f));
    }

    public boolean contains(Vector3 vector30) {
        return this.min.x <= vector30.x && this.max.x >= vector30.x && this.min.y <= vector30.y && this.max.y >= vector30.y && this.min.z <= vector30.z && this.max.z >= vector30.z;
    }

    public boolean contains(BoundingBox boundingBox0) {
        return !this.isValid() || this.min.x <= boundingBox0.min.x && this.min.y <= boundingBox0.min.y && this.min.z <= boundingBox0.min.z && this.max.x >= boundingBox0.max.x && this.max.y >= boundingBox0.max.y && this.max.z >= boundingBox0.max.z;
    }

    public BoundingBox ext(float f, float f1, float f2) {
        return this.set(this.min.set(BoundingBox.min(this.min.x, f), BoundingBox.min(this.min.y, f1), BoundingBox.min(this.min.z, f2)), this.max.set(BoundingBox.max(this.max.x, f), BoundingBox.max(this.max.y, f1), BoundingBox.max(this.max.z, f2)));
    }

    public BoundingBox ext(Vector3 vector30) {
        return this.set(this.min.set(BoundingBox.min(this.min.x, vector30.x), BoundingBox.min(this.min.y, vector30.y), BoundingBox.min(this.min.z, vector30.z)), this.max.set(Math.max(this.max.x, vector30.x), Math.max(this.max.y, vector30.y), Math.max(this.max.z, vector30.z)));
    }

    public BoundingBox ext(Vector3 vector30, float f) {
        return this.set(this.min.set(BoundingBox.min(this.min.x, vector30.x - f), BoundingBox.min(this.min.y, vector30.y - f), BoundingBox.min(this.min.z, vector30.z - f)), this.max.set(BoundingBox.max(this.max.x, vector30.x + f), BoundingBox.max(this.max.y, vector30.y + f), BoundingBox.max(this.max.z, vector30.z + f)));
    }

    public BoundingBox ext(BoundingBox boundingBox0) {
        return this.set(this.min.set(BoundingBox.min(this.min.x, boundingBox0.min.x), BoundingBox.min(this.min.y, boundingBox0.min.y), BoundingBox.min(this.min.z, boundingBox0.min.z)), this.max.set(BoundingBox.max(this.max.x, boundingBox0.max.x), BoundingBox.max(this.max.y, boundingBox0.max.y), BoundingBox.max(this.max.z, boundingBox0.max.z)));
    }

    public BoundingBox ext(BoundingBox boundingBox0, Matrix4 matrix40) {
        this.ext(BoundingBox.tmpVector.set(boundingBox0.min.x, boundingBox0.min.y, boundingBox0.min.z).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(boundingBox0.min.x, boundingBox0.min.y, boundingBox0.max.z).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(boundingBox0.min.x, boundingBox0.max.y, boundingBox0.min.z).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(boundingBox0.min.x, boundingBox0.max.y, boundingBox0.max.z).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(boundingBox0.max.x, boundingBox0.min.y, boundingBox0.min.z).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(boundingBox0.max.x, boundingBox0.min.y, boundingBox0.max.z).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(boundingBox0.max.x, boundingBox0.max.y, boundingBox0.min.z).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(boundingBox0.max.x, boundingBox0.max.y, boundingBox0.max.z).mul(matrix40));
        return this;
    }

    public Vector3 getCenter(Vector3 vector30) {
        return vector30.set(this.cnt);
    }

    public float getCenterX() {
        return this.cnt.x;
    }

    public float getCenterY() {
        return this.cnt.y;
    }

    public float getCenterZ() {
        return this.cnt.z;
    }

    public Vector3 getCorner000(Vector3 vector30) {
        return vector30.set(this.min.x, this.min.y, this.min.z);
    }

    public Vector3 getCorner001(Vector3 vector30) {
        return vector30.set(this.min.x, this.min.y, this.max.z);
    }

    public Vector3 getCorner010(Vector3 vector30) {
        return vector30.set(this.min.x, this.max.y, this.min.z);
    }

    public Vector3 getCorner011(Vector3 vector30) {
        return vector30.set(this.min.x, this.max.y, this.max.z);
    }

    public Vector3 getCorner100(Vector3 vector30) {
        return vector30.set(this.max.x, this.min.y, this.min.z);
    }

    public Vector3 getCorner101(Vector3 vector30) {
        return vector30.set(this.max.x, this.min.y, this.max.z);
    }

    public Vector3 getCorner110(Vector3 vector30) {
        return vector30.set(this.max.x, this.max.y, this.min.z);
    }

    public Vector3 getCorner111(Vector3 vector30) {
        return vector30.set(this.max.x, this.max.y, this.max.z);
    }

    public float getDepth() {
        return this.dim.z;
    }

    public Vector3 getDimensions(Vector3 vector30) {
        return vector30.set(this.dim);
    }

    public float getHeight() {
        return this.dim.y;
    }

    public Vector3 getMax(Vector3 vector30) {
        return vector30.set(this.max);
    }

    public Vector3 getMin(Vector3 vector30) {
        return vector30.set(this.min);
    }

    public float getWidth() {
        return this.dim.x;
    }

    public BoundingBox inf() {
        this.min.set(Infinityf, Infinityf, Infinityf);
        this.max.set(-Infinityf, -Infinityf, -Infinityf);
        this.cnt.set(0.0f, 0.0f, 0.0f);
        this.dim.set(0.0f, 0.0f, 0.0f);
        return this;
    }

    public boolean intersects(BoundingBox boundingBox0) {
        return this.isValid() ? Math.abs(this.cnt.x - boundingBox0.cnt.x) <= this.dim.x / 2.0f + boundingBox0.dim.x / 2.0f && Math.abs(this.cnt.y - boundingBox0.cnt.y) <= this.dim.y / 2.0f + boundingBox0.dim.y / 2.0f && Math.abs(this.cnt.z - boundingBox0.cnt.z) <= this.dim.z / 2.0f + boundingBox0.dim.z / 2.0f : false;
    }

    public boolean isValid() {
        return this.min.x <= this.max.x && this.min.y <= this.max.y && this.min.z <= this.max.z;
    }

    static final float max(float f, float f1) {
        return f > f1 ? f : f1;
    }

    static final float min(float f, float f1) {
        return f > f1 ? f1 : f;
    }

    public BoundingBox mul(Matrix4 matrix40) {
        float f = this.min.x;
        float f1 = this.min.y;
        float f2 = this.min.z;
        float f3 = this.max.x;
        float f4 = this.max.y;
        float f5 = this.max.z;
        this.inf();
        this.ext(BoundingBox.tmpVector.set(f, f1, f2).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(f, f1, f5).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(f, f4, f2).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(f, f4, f5).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(f3, f1, f2).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(f3, f1, f5).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(f3, f4, f2).mul(matrix40));
        this.ext(BoundingBox.tmpVector.set(f3, f4, f5).mul(matrix40));
        return this;
    }

    public BoundingBox set(Vector3 vector30, Vector3 vector31) {
        this.min.set((vector30.x < vector31.x ? vector30.x : vector31.x), (vector30.y < vector31.y ? vector30.y : vector31.y), (vector30.z < vector31.z ? vector30.z : vector31.z));
        this.max.set((vector30.x > vector31.x ? vector30.x : vector31.x), (vector30.y > vector31.y ? vector30.y : vector31.y), (vector30.z > vector31.z ? vector30.z : vector31.z));
        this.cnt.set(this.min).add(this.max).scl(0.5f);
        this.dim.set(this.max).sub(this.min);
        return this;
    }

    public BoundingBox set(BoundingBox boundingBox0) {
        return this.set(boundingBox0.min, boundingBox0.max);
    }

    public BoundingBox set(List list0) {
        this.inf();
        for(Object object0: list0) {
            this.ext(((Vector3)object0));
        }
        return this;
    }

    public BoundingBox set(Vector3[] arr_vector3) {
        this.inf();
        for(int v = 0; v < arr_vector3.length; ++v) {
            this.ext(arr_vector3[v]);
        }
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.min + "|" + this.max + "]";
    }
}

