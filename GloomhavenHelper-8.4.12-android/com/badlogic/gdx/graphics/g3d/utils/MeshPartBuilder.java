package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public interface MeshPartBuilder {
    public static class VertexInfo implements Poolable {
        public final Color color;
        public boolean hasColor;
        public boolean hasNormal;
        public boolean hasPosition;
        public boolean hasUV;
        public final Vector3 normal;
        public final Vector3 position;
        public final Vector2 uv;

        public VertexInfo() {
            this.position = new Vector3();
            this.normal = new Vector3(0.0f, 1.0f, 0.0f);
            this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.uv = new Vector2();
        }

        public VertexInfo lerp(VertexInfo meshPartBuilder$VertexInfo0, float f) {
            if(this.hasPosition && meshPartBuilder$VertexInfo0.hasPosition) {
                this.position.lerp(meshPartBuilder$VertexInfo0.position, f);
            }
            if(this.hasNormal && meshPartBuilder$VertexInfo0.hasNormal) {
                this.normal.lerp(meshPartBuilder$VertexInfo0.normal, f);
            }
            if(this.hasColor && meshPartBuilder$VertexInfo0.hasColor) {
                this.color.lerp(meshPartBuilder$VertexInfo0.color, f);
            }
            if(this.hasUV && meshPartBuilder$VertexInfo0.hasUV) {
                this.uv.lerp(meshPartBuilder$VertexInfo0.uv, f);
            }
            return this;
        }

        @Override  // com.badlogic.gdx.utils.Pool$Poolable
        public void reset() {
            this.position.set(0.0f, 0.0f, 0.0f);
            this.normal.set(0.0f, 1.0f, 0.0f);
            this.color.set(1.0f, 1.0f, 1.0f, 1.0f);
            this.uv.set(0.0f, 0.0f);
        }

        public VertexInfo set(VertexInfo meshPartBuilder$VertexInfo0) {
            if(meshPartBuilder$VertexInfo0 == null) {
                return this.set(null, null, null, null);
            }
            this.hasPosition = meshPartBuilder$VertexInfo0.hasPosition;
            this.position.set(meshPartBuilder$VertexInfo0.position);
            this.hasNormal = meshPartBuilder$VertexInfo0.hasNormal;
            this.normal.set(meshPartBuilder$VertexInfo0.normal);
            this.hasColor = meshPartBuilder$VertexInfo0.hasColor;
            this.color.set(meshPartBuilder$VertexInfo0.color);
            this.hasUV = meshPartBuilder$VertexInfo0.hasUV;
            this.uv.set(meshPartBuilder$VertexInfo0.uv);
            return this;
        }

        public VertexInfo set(Vector3 vector30, Vector3 vector31, Color color0, Vector2 vector20) {
            this.reset();
            boolean z = true;
            this.hasPosition = vector30 != null;
            if(this.hasPosition) {
                this.position.set(vector30);
            }
            this.hasNormal = vector31 != null;
            if(this.hasNormal) {
                this.normal.set(vector31);
            }
            this.hasColor = color0 != null;
            if(this.hasColor) {
                this.color.set(color0);
            }
            if(vector20 == null) {
                z = false;
            }
            this.hasUV = z;
            if(this.hasUV) {
                this.uv.set(vector20);
            }
            return this;
        }

        public VertexInfo setCol(float f, float f1, float f2, float f3) {
            this.color.set(f, f1, f2, f3);
            this.hasColor = true;
            return this;
        }

        public VertexInfo setCol(Color color0) {
            this.hasColor = color0 != null;
            if(this.hasColor) {
                this.color.set(color0);
            }
            return this;
        }

        public VertexInfo setNor(float f, float f1, float f2) {
            this.normal.set(f, f1, f2);
            this.hasNormal = true;
            return this;
        }

        public VertexInfo setNor(Vector3 vector30) {
            this.hasNormal = vector30 != null;
            if(this.hasNormal) {
                this.normal.set(vector30);
            }
            return this;
        }

        public VertexInfo setPos(float f, float f1, float f2) {
            this.position.set(f, f1, f2);
            this.hasPosition = true;
            return this;
        }

        public VertexInfo setPos(Vector3 vector30) {
            this.hasPosition = vector30 != null;
            if(this.hasPosition) {
                this.position.set(vector30);
            }
            return this;
        }

        public VertexInfo setUV(float f, float f1) {
            this.uv.set(f, f1);
            this.hasUV = true;
            return this;
        }

        public VertexInfo setUV(Vector2 vector20) {
            this.hasUV = vector20 != null;
            if(this.hasUV) {
                this.uv.set(vector20);
            }
            return this;
        }
    }

    void addMesh(Mesh arg1);

    void addMesh(Mesh arg1, int arg2, int arg3);

    void addMesh(MeshPart arg1);

    void addMesh(float[] arg1, short[] arg2);

    void addMesh(float[] arg1, short[] arg2, int arg3, int arg4);

    @Deprecated
    void arrow(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, int arg9);

    @Deprecated
    void box(float arg1, float arg2, float arg3);

    @Deprecated
    void box(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6);

    @Deprecated
    void box(VertexInfo arg1, VertexInfo arg2, VertexInfo arg3, VertexInfo arg4, VertexInfo arg5, VertexInfo arg6, VertexInfo arg7, VertexInfo arg8);

    @Deprecated
    void box(Matrix4 arg1);

    @Deprecated
    void box(Vector3 arg1, Vector3 arg2, Vector3 arg3, Vector3 arg4, Vector3 arg5, Vector3 arg6, Vector3 arg7, Vector3 arg8);

    @Deprecated
    void capsule(float arg1, float arg2, int arg3);

    @Deprecated
    void circle(float arg1, int arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8);

    @Deprecated
    void circle(float arg1, int arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10);

    @Deprecated
    void circle(float arg1, int arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14);

    @Deprecated
    void circle(float arg1, int arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14, float arg15, float arg16);

    @Deprecated
    void circle(float arg1, int arg2, Vector3 arg3, Vector3 arg4);

    @Deprecated
    void circle(float arg1, int arg2, Vector3 arg3, Vector3 arg4, float arg5, float arg6);

    @Deprecated
    void circle(float arg1, int arg2, Vector3 arg3, Vector3 arg4, Vector3 arg5, Vector3 arg6);

    @Deprecated
    void circle(float arg1, int arg2, Vector3 arg3, Vector3 arg4, Vector3 arg5, Vector3 arg6, float arg7, float arg8);

    @Deprecated
    void cone(float arg1, float arg2, float arg3, int arg4);

    @Deprecated
    void cone(float arg1, float arg2, float arg3, int arg4, float arg5, float arg6);

    @Deprecated
    void cylinder(float arg1, float arg2, float arg3, int arg4);

    @Deprecated
    void cylinder(float arg1, float arg2, float arg3, int arg4, float arg5, float arg6);

    @Deprecated
    void cylinder(float arg1, float arg2, float arg3, int arg4, float arg5, float arg6, boolean arg7);

    @Deprecated
    void ellipse(float arg1, float arg2, float arg3, float arg4, int arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11);

    @Deprecated
    void ellipse(float arg1, float arg2, float arg3, float arg4, int arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13);

    @Deprecated
    void ellipse(float arg1, float arg2, float arg3, float arg4, int arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14, float arg15, float arg16, float arg17, float arg18, float arg19);

    @Deprecated
    void ellipse(float arg1, float arg2, float arg3, float arg4, int arg5, Vector3 arg6, Vector3 arg7);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14, float arg15);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14, float arg15, float arg16, float arg17);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, Vector3 arg4, Vector3 arg5);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, Vector3 arg4, Vector3 arg5, float arg6, float arg7);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, Vector3 arg4, Vector3 arg5, Vector3 arg6, Vector3 arg7);

    @Deprecated
    void ellipse(float arg1, float arg2, int arg3, Vector3 arg4, Vector3 arg5, Vector3 arg6, Vector3 arg7, float arg8, float arg9);

    void ensureCapacity(int arg1, int arg2);

    void ensureIndices(int arg1);

    void ensureRectangleIndices(int arg1);

    void ensureTriangleIndices(int arg1);

    void ensureVertices(int arg1);

    VertexAttributes getAttributes();

    MeshPart getMeshPart();

    int getPrimitiveType();

    Matrix4 getVertexTransform(Matrix4 arg1);

    void index(short arg1);

    void index(short arg1, short arg2);

    void index(short arg1, short arg2, short arg3);

    void index(short arg1, short arg2, short arg3, short arg4);

    void index(short arg1, short arg2, short arg3, short arg4, short arg5, short arg6);

    void index(short arg1, short arg2, short arg3, short arg4, short arg5, short arg6, short arg7, short arg8);

    boolean isVertexTransformationEnabled();

    short lastIndex();

    void line(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6);

    void line(VertexInfo arg1, VertexInfo arg2);

    void line(Vector3 arg1, Color arg2, Vector3 arg3, Color arg4);

    void line(Vector3 arg1, Vector3 arg2);

    void line(short arg1, short arg2);

    @Deprecated
    void patch(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14, float arg15, int arg16, int arg17);

    @Deprecated
    void patch(VertexInfo arg1, VertexInfo arg2, VertexInfo arg3, VertexInfo arg4, int arg5, int arg6);

    @Deprecated
    void patch(Vector3 arg1, Vector3 arg2, Vector3 arg3, Vector3 arg4, Vector3 arg5, int arg6, int arg7);

    void rect(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14, float arg15);

    void rect(VertexInfo arg1, VertexInfo arg2, VertexInfo arg3, VertexInfo arg4);

    void rect(Vector3 arg1, Vector3 arg2, Vector3 arg3, Vector3 arg4, Vector3 arg5);

    void rect(short arg1, short arg2, short arg3, short arg4);

    void setColor(float arg1, float arg2, float arg3, float arg4);

    void setColor(Color arg1);

    void setUVRange(float arg1, float arg2, float arg3, float arg4);

    void setUVRange(TextureRegion arg1);

    void setVertexTransform(Matrix4 arg1);

    void setVertexTransformationEnabled(boolean arg1);

    @Deprecated
    void sphere(float arg1, float arg2, float arg3, int arg4, int arg5);

    @Deprecated
    void sphere(float arg1, float arg2, float arg3, int arg4, int arg5, float arg6, float arg7, float arg8, float arg9);

    @Deprecated
    void sphere(Matrix4 arg1, float arg2, float arg3, float arg4, int arg5, int arg6);

    @Deprecated
    void sphere(Matrix4 arg1, float arg2, float arg3, float arg4, int arg5, int arg6, float arg7, float arg8, float arg9, float arg10);

    void triangle(VertexInfo arg1, VertexInfo arg2, VertexInfo arg3);

    void triangle(Vector3 arg1, Color arg2, Vector3 arg3, Color arg4, Vector3 arg5, Color arg6);

    void triangle(Vector3 arg1, Vector3 arg2, Vector3 arg3);

    void triangle(short arg1, short arg2, short arg3);

    short vertex(VertexInfo arg1);

    short vertex(Vector3 arg1, Vector3 arg2, Color arg3, Vector2 arg4);

    short vertex(float[] arg1);
}

