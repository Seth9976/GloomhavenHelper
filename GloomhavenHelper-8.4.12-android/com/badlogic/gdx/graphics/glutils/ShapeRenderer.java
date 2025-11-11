package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class ShapeRenderer implements Disposable {
    public static enum ShapeType {
        Point(0),
        Line(1),
        Filled(4);

        private final int glType;

        private ShapeType(int v1) {
            this.glType = v1;
        }

        public int getGlType() {
            return this.glType;
        }
    }

    private boolean autoShapeType;
    private final Color color;
    private final Matrix4 combinedMatrix;
    private float defaultRectLineWidth;
    private boolean matrixDirty;
    private final Matrix4 projectionMatrix;
    private final ImmediateModeRenderer renderer;
    private ShapeType shapeType;
    private final Vector2 tmp;
    private final Matrix4 transformMatrix;

    public ShapeRenderer() {
        this(5000);
    }

    public ShapeRenderer(int v) {
        this(v, null);
    }

    public ShapeRenderer(int v, ShaderProgram shaderProgram0) {
        this.matrixDirty = false;
        this.projectionMatrix = new Matrix4();
        this.transformMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.tmp = new Vector2();
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.defaultRectLineWidth = 0.75f;
        this.renderer = shaderProgram0 == null ? new ImmediateModeRenderer20(v, false, true, 0) : new ImmediateModeRenderer20(v, false, true, 0, shaderProgram0);
        float f = (float)Gdx.graphics.getWidth();
        float f1 = (float)Gdx.graphics.getHeight();
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, f, f1);
        this.matrixDirty = true;
    }

    public void arc(float f, float f1, float f2, float f3, float f4) {
        this.arc(f, f1, f2, f3, f4, Math.max(1, ((int)(((float)Math.cbrt(f2)) * 6.0f * (f4 / 360.0f)))));
    }

    public void arc(float f, float f1, float f2, float f3, float f4, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        float f5 = this.color.toFloatBits();
        float f6 = f4 / 360.0f * 6.283185f / ((float)v);
        float f7 = MathUtils.cos(f6);
        float f8 = MathUtils.sin(f6);
        float f9 = MathUtils.cos(f3 * 0.017453f) * f2;
        float f10 = f2 * MathUtils.sin(f3 * 0.017453f);
        int v1 = 0;
        if(this.shapeType == ShapeType.Line) {
            this.check(ShapeType.Line, ShapeType.Filled, v * 2 + 2);
            this.renderer.color(f5);
            this.renderer.vertex(f, f1, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f + f9, f1 + f10, 0.0f);
            while(v1 < v) {
                this.renderer.color(f5);
                this.renderer.vertex(f + f9, f1 + f10, 0.0f);
                float f11 = f7 * f9 - f8 * f10;
                f10 = f10 * f7 + f9 * f8;
                this.renderer.color(f5);
                this.renderer.vertex(f + f11, f1 + f10, 0.0f);
                ++v1;
                f9 = f11;
            }
        }
        else {
            this.check(ShapeType.Line, ShapeType.Filled, v * 3 + 3);
            while(v1 < v) {
                this.renderer.color(f5);
                this.renderer.vertex(f, f1, 0.0f);
                this.renderer.color(f5);
                this.renderer.vertex(f + f9, f1 + f10, 0.0f);
                float f12 = f7 * f9 - f8 * f10;
                f10 = f10 * f7 + f9 * f8;
                this.renderer.color(f5);
                this.renderer.vertex(f + f12, f1 + f10, 0.0f);
                ++v1;
                f9 = f12;
            }
            this.renderer.color(f5);
            this.renderer.vertex(f, f1, 0.0f);
        }
        this.renderer.color(f5);
        this.renderer.vertex(f9 + f, f10 + f1, 0.0f);
        this.renderer.color(f5);
        this.renderer.vertex(f + 0.0f, f1 + 0.0f, 0.0f);
    }

    public void begin() {
        if(!this.autoShapeType) {
            throw new IllegalStateException("autoShapeType must be true to use this method.");
        }
        this.begin(ShapeType.Line);
    }

    public void begin(ShapeType shapeRenderer$ShapeType0) {
        if(this.shapeType != null) {
            throw new IllegalStateException("Call end() before beginning a new shape batch.");
        }
        this.shapeType = shapeRenderer$ShapeType0;
        if(this.matrixDirty) {
            this.combinedMatrix.set(this.projectionMatrix);
            Matrix4.mul(this.combinedMatrix.val, this.transformMatrix.val);
            this.matrixDirty = false;
        }
        this.renderer.begin(this.combinedMatrix, this.shapeType.getGlType());
    }

    public void box(float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = this.color.toFloatBits();
        if(this.shapeType == ShapeType.Line) {
            this.check(ShapeType.Line, ShapeType.Filled, 24);
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, f2);
            this.renderer.color(f6);
            float f7 = f3 + f;
            this.renderer.vertex(f7, f1, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f1, f2);
            this.renderer.color(f6);
            float f8 = f2 - f5;
            this.renderer.vertex(f7, f1, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f1, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, f2);
            this.renderer.color(f6);
            float f9 = f4 + f1;
            this.renderer.vertex(f, f9, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f, f9, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f9, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f9, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f9, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f9, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f9, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f9, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f9, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f1, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f9, f2);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f1, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f7, f9, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, f8);
            this.renderer.color(f6);
            this.renderer.vertex(f, f9, f8);
            return;
        }
        this.check(ShapeType.Line, ShapeType.Filled, 36);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f2);
        this.renderer.color(f6);
        float f10 = f3 + f;
        this.renderer.vertex(f10, f1, f2);
        this.renderer.color(f6);
        float f11 = f4 + f1;
        this.renderer.vertex(f10, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f2);
        this.renderer.color(f6);
        float f12 = f2 - f5;
        this.renderer.vertex(f10, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f1, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f1, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f11, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f1, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f12);
        this.renderer.color(f6);
        this.renderer.vertex(f10, f1, f2);
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, f2);
    }

    private void check(ShapeType shapeRenderer$ShapeType0, ShapeType shapeRenderer$ShapeType1, int v) {
        ShapeType shapeRenderer$ShapeType2 = this.shapeType;
        if(shapeRenderer$ShapeType2 == null) {
            throw new IllegalStateException("begin must be called first.");
        }
        if(shapeRenderer$ShapeType2 != shapeRenderer$ShapeType0 && shapeRenderer$ShapeType2 != shapeRenderer$ShapeType1) {
            if(!this.autoShapeType) {
                throw shapeRenderer$ShapeType1 == null ? new IllegalStateException("Must call begin(ShapeType." + shapeRenderer$ShapeType0 + ").") : new IllegalStateException("Must call begin(ShapeType." + shapeRenderer$ShapeType0 + ") or begin(ShapeType." + shapeRenderer$ShapeType1 + ").");
            }
            this.end();
            this.begin(shapeRenderer$ShapeType0);
            return;
        }
        if(this.matrixDirty) {
            ShapeType shapeRenderer$ShapeType3 = this.shapeType;
            this.end();
            this.begin(shapeRenderer$ShapeType3);
            return;
        }
        if(this.renderer.getMaxVertices() - this.renderer.getNumVertices() < v) {
            ShapeType shapeRenderer$ShapeType4 = this.shapeType;
            this.end();
            this.begin(shapeRenderer$ShapeType4);
        }
    }

    public void circle(float f, float f1, float f2) {
        this.circle(f, f1, f2, Math.max(1, ((int)(((float)Math.cbrt(f2)) * 6.0f))));
    }

    public void circle(float f, float f1, float f2, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        float f3 = this.color.toFloatBits();
        float f4 = MathUtils.cos(6.283185f / ((float)v));
        float f5 = MathUtils.sin(6.283185f / ((float)v));
        int v1 = 0;
        if(this.shapeType == ShapeType.Line) {
            this.check(ShapeType.Line, ShapeType.Filled, v * 2 + 2);
            float f6 = f2;
            float f7 = 0.0f;
            while(v1 < v) {
                this.renderer.color(f3);
                this.renderer.vertex(f + f6, f1 + f7, 0.0f);
                float f8 = f4 * f6 - f5 * f7;
                f7 = f7 * f4 + f6 * f5;
                this.renderer.color(f3);
                this.renderer.vertex(f + f8, f1 + f7, 0.0f);
                ++v1;
                f6 = f8;
            }
            this.renderer.color(f3);
            this.renderer.vertex(f6 + f, f7 + f1, 0.0f);
        }
        else {
            this.check(ShapeType.Line, ShapeType.Filled, v * 3 + 3);
            float f9 = f2;
            float f10 = 0.0f;
            while(v1 < v - 1) {
                this.renderer.color(f3);
                this.renderer.vertex(f, f1, 0.0f);
                this.renderer.color(f3);
                this.renderer.vertex(f + f9, f1 + f10, 0.0f);
                float f11 = f4 * f9 - f5 * f10;
                f10 = f10 * f4 + f9 * f5;
                this.renderer.color(f3);
                this.renderer.vertex(f + f11, f1 + f10, 0.0f);
                ++v1;
                f9 = f11;
            }
            this.renderer.color(f3);
            this.renderer.vertex(f, f1, 0.0f);
            this.renderer.color(f3);
            this.renderer.vertex(f9 + f, f10 + f1, 0.0f);
        }
        this.renderer.color(f3);
        this.renderer.vertex(f + f2, f1 + 0.0f, 0.0f);
    }

    public void cone(float f, float f1, float f2, float f3, float f4) {
        this.cone(f, f1, f2, f3, f4, Math.max(1, ((int)(((float)Math.sqrt(f3)) * 4.0f))));
    }

    public void cone(float f, float f1, float f2, float f3, float f4, int v) {
        float f9;
        float f8;
        if(v <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        this.check(ShapeType.Line, ShapeType.Filled, v * 4 + 2);
        float f5 = this.color.toFloatBits();
        float f6 = MathUtils.cos(6.283185f / ((float)v));
        float f7 = MathUtils.sin(6.283185f / ((float)v));
        int v1 = 0;
        if(this.shapeType == ShapeType.Line) {
            f8 = f3;
            f9 = 0.0f;
            while(v1 < v) {
                this.renderer.color(f5);
                float f10 = f + f8;
                float f11 = f1 + f9;
                this.renderer.vertex(f10, f11, f2);
                this.renderer.color(f5);
                this.renderer.vertex(f, f1, f2 + f4);
                this.renderer.color(f5);
                this.renderer.vertex(f10, f11, f2);
                float f12 = f6 * f8 - f7 * f9;
                f9 = f9 * f6 + f8 * f7;
                this.renderer.color(f5);
                this.renderer.vertex(f + f12, f1 + f9, f2);
                ++v1;
                f8 = f12;
            }
        }
        else {
            f8 = f3;
            f9 = 0.0f;
            while(v1 < v - 1) {
                this.renderer.color(f5);
                this.renderer.vertex(f, f1, f2);
                this.renderer.color(f5);
                float f13 = f + f8;
                float f14 = f1 + f9;
                this.renderer.vertex(f13, f14, f2);
                float f15 = f6 * f8 - f7 * f9;
                f9 = f9 * f6 + f8 * f7;
                this.renderer.color(f5);
                float f16 = f + f15;
                float f17 = f1 + f9;
                this.renderer.vertex(f16, f17, f2);
                this.renderer.color(f5);
                this.renderer.vertex(f13, f14, f2);
                this.renderer.color(f5);
                this.renderer.vertex(f16, f17, f2);
                this.renderer.color(f5);
                this.renderer.vertex(f, f1, f2 + f4);
                ++v1;
                f8 = f15;
            }
            this.renderer.color(f5);
            this.renderer.vertex(f, f1, f2);
        }
        this.renderer.color(f5);
        this.renderer.vertex(f + f8, f1 + f9, f2);
        this.renderer.color(f5);
        float f18 = f + f3;
        this.renderer.vertex(f18, f1 + 0.0f, f2);
        if(this.shapeType != ShapeType.Line) {
            this.renderer.color(f5);
            this.renderer.vertex(f8 + f, f9 + f1, f2);
            this.renderer.color(f5);
            this.renderer.vertex(f18, f1 + 0.0f, f2);
            this.renderer.color(f5);
            this.renderer.vertex(f, f1, f2 + f4);
        }
    }

    public void curve(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, int v) {
        this.check(ShapeType.Line, null, v * 2 + 2);
        float f8 = this.color.toFloatBits();
        float f9 = 1.0f / ((float)v);
        float f10 = f9 * f9;
        float f11 = f10 * f9;
        float f12 = f - f2 * 2.0f + f4;
        float f13 = f1 - 2.0f * f3 + f5;
        float f14 = (f2 - f4) * 3.0f - f + f6;
        float f15 = (f3 - f5) * 3.0f - f1 + f7;
        float f16 = (f2 - f) * (f9 * 3.0f) + f12 * (f10 * 3.0f) + f14 * f11;
        float f17 = (f3 - f1) * (f9 * 3.0f) + f10 * 3.0f * f13 + f11 * f15;
        float f18 = f14 * (6.0f * f11);
        float f19 = f12 * (f10 * 6.0f) + f18;
        float f20 = f15 * (6.0f * f11);
        float f21 = f13 * (f10 * 6.0f) + f20;
        float f22 = f;
        float f23 = f1;
        while(v > 0) {
            this.renderer.color(f8);
            this.renderer.vertex(f22, f23, 0.0f);
            f22 += f16;
            f23 += f17;
            f16 += f19;
            f17 += f21;
            f19 += f18;
            f21 += f20;
            this.renderer.color(f8);
            this.renderer.vertex(f22, f23, 0.0f);
            --v;
        }
        this.renderer.color(f8);
        this.renderer.vertex(f22, f23, 0.0f);
        this.renderer.color(f8);
        this.renderer.vertex(f6, f7, 0.0f);
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.renderer.dispose();
    }

    public void ellipse(float f, float f1, float f2, float f3) {
        this.ellipse(f, f1, f2, f3, Math.max(1, ((int)(((float)Math.cbrt(Math.max(f2 * 0.5f, 0.5f * f3))) * 12.0f))));
    }

    public void ellipse(float f, float f1, float f2, float f3, float f4) {
        this.ellipse(f, f1, f2, f3, f4, Math.max(1, ((int)(((float)Math.cbrt(Math.max(f2 * 0.5f, 0.5f * f3))) * 12.0f))));
    }

    public void ellipse(float f, float f1, float f2, float f3, float f4, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        this.check(ShapeType.Line, ShapeType.Filled, v * 3);
        float f5 = this.color.toFloatBits();
        float f6 = 3.141593f * f4 / 180.0f;
        float f7 = MathUtils.sin(f6);
        float f8 = MathUtils.cos(f6);
        float f9 = f + f2 / 2.0f;
        float f10 = f1 + f3 / 2.0f;
        int v1 = 0;
        if(this.shapeType == ShapeType.Line) {
            float f11 = f2 * 0.5f;
            float f12 = 0.0f;
            while(v1 < v) {
                this.renderer.color(f5);
                this.renderer.vertex(f8 * f11 + f9 - f7 * f12, f11 * f7 + f10 + f12 * f8, 0.0f);
                ++v1;
                float f13 = ((float)v1) * (6.283185f / ((float)v));
                float f14 = MathUtils.cos(f13) * (f2 * 0.5f);
                float f15 = MathUtils.sin(f13) * (f3 * 0.5f);
                this.renderer.color(f5);
                this.renderer.vertex(f8 * f14 + f9 - f7 * f15, f10 + f7 * f14 + f8 * f15, 0.0f);
                f12 = f15;
                f11 = f14;
            }
            return;
        }
        float f16 = f2 * 0.5f;
        float f17 = 0.0f;
        while(v1 < v) {
            this.renderer.color(f5);
            this.renderer.vertex(f8 * f16 + f9 - f7 * f17, f16 * f7 + f10 + f17 * f8, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f9, f10, 0.0f);
            ++v1;
            float f18 = ((float)v1) * (6.283185f / ((float)v));
            float f19 = MathUtils.cos(f18) * (f2 * 0.5f);
            float f20 = MathUtils.sin(f18) * (f3 * 0.5f);
            this.renderer.color(f5);
            this.renderer.vertex(f8 * f19 + f9 - f7 * f20, f10 + f7 * f19 + f8 * f20, 0.0f);
            f17 = f20;
            f16 = f19;
        }
    }

    public void ellipse(float f, float f1, float f2, float f3, int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }
        this.check(ShapeType.Line, ShapeType.Filled, v * 3);
        float f4 = this.color.toFloatBits();
        float f5 = f + f2 / 2.0f;
        float f6 = f1 + f3 / 2.0f;
        int v1 = 0;
        if(this.shapeType == ShapeType.Line) {
            while(v1 < v) {
                this.renderer.color(f4);
                float f7 = ((float)v1) * (6.283185f / ((float)v));
                float f8 = MathUtils.cos(f7);
                float f9 = MathUtils.sin(f7);
                this.renderer.vertex(f8 * (f2 * 0.5f) + f5, f9 * (f3 * 0.5f) + f6, 0.0f);
                this.renderer.color(f4);
                ++v1;
                float f10 = ((float)v1) * (6.283185f / ((float)v));
                float f11 = MathUtils.cos(f10);
                float f12 = MathUtils.sin(f10);
                this.renderer.vertex(f2 * 0.5f * f11 + f5, f3 * 0.5f * f12 + f6, 0.0f);
            }
            return;
        }
        while(v1 < v) {
            this.renderer.color(f4);
            float f13 = ((float)v1) * (6.283185f / ((float)v));
            float f14 = MathUtils.cos(f13);
            float f15 = MathUtils.sin(f13);
            this.renderer.vertex(f14 * (f2 * 0.5f) + f5, f15 * (f3 * 0.5f) + f6, 0.0f);
            this.renderer.color(f4);
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(f4);
            ++v1;
            float f16 = ((float)v1) * (6.283185f / ((float)v));
            float f17 = MathUtils.cos(f16);
            float f18 = MathUtils.sin(f16);
            this.renderer.vertex(f2 * 0.5f * f17 + f5, f3 * 0.5f * f18 + f6, 0.0f);
        }
    }

    public void end() {
        this.renderer.end();
        this.shapeType = null;
    }

    public void flush() {
        ShapeType shapeRenderer$ShapeType0 = this.shapeType;
        if(shapeRenderer$ShapeType0 == null) {
            return;
        }
        this.end();
        this.begin(shapeRenderer$ShapeType0);
    }

    public Color getColor() {
        return this.color;
    }

    public ShapeType getCurrentType() {
        return this.shapeType;
    }

    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public ImmediateModeRenderer getRenderer() {
        return this.renderer;
    }

    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    public void identity() {
        this.transformMatrix.idt();
        this.matrixDirty = true;
    }

    public boolean isDrawing() {
        return this.shapeType != null;
    }

    public final void line(float f, float f1, float f2, float f3) {
        this.line(f, f1, 0.0f, f2, f3, 0.0f, this.color, this.color);
    }

    public final void line(float f, float f1, float f2, float f3, float f4, float f5) {
        this.line(f, f1, f2, f3, f4, f5, this.color, this.color);
    }

    public void line(float f, float f1, float f2, float f3, float f4, float f5, Color color0, Color color1) {
        if(this.shapeType == ShapeType.Filled) {
            this.rectLine(f, f1, f3, f4, this.defaultRectLineWidth, color0, color1);
            return;
        }
        this.check(ShapeType.Line, null, 2);
        this.renderer.color(color0.r, color0.g, color0.b, color0.a);
        this.renderer.vertex(f, f1, f2);
        this.renderer.color(color1.r, color1.g, color1.b, color1.a);
        this.renderer.vertex(f3, f4, f5);
    }

    public final void line(float f, float f1, float f2, float f3, Color color0, Color color1) {
        this.line(f, f1, 0.0f, f2, f3, 0.0f, color0, color1);
    }

    public final void line(Vector2 vector20, Vector2 vector21) {
        this.line(vector20.x, vector20.y, 0.0f, vector21.x, vector21.y, 0.0f, this.color, this.color);
    }

    public final void line(Vector3 vector30, Vector3 vector31) {
        this.line(vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, this.color, this.color);
    }

    public void point(float f, float f1, float f2) {
        if(this.shapeType == ShapeType.Line) {
            this.line(f - this.defaultRectLineWidth * 0.5f, f1 - this.defaultRectLineWidth * 0.5f, f2, f + this.defaultRectLineWidth * 0.5f, f1 + this.defaultRectLineWidth * 0.5f, f2);
            return;
        }
        if(this.shapeType == ShapeType.Filled) {
            this.box(f - 0.5f * this.defaultRectLineWidth, f1 - 0.5f * this.defaultRectLineWidth, f2 - 0.5f * this.defaultRectLineWidth, this.defaultRectLineWidth, this.defaultRectLineWidth, this.defaultRectLineWidth);
            return;
        }
        this.check(ShapeType.Point, null, 1);
        this.renderer.color(this.color);
        this.renderer.vertex(f, f1, f2);
    }

    public void polygon(float[] arr_f) {
        this.polygon(arr_f, 0, arr_f.length);
    }

    public void polygon(float[] arr_f, int v, int v1) {
        float f6;
        float f5;
        if(v1 < 6) {
            throw new IllegalArgumentException("Polygons must contain at least 3 points.");
        }
        if(v1 % 2 != 0) {
            throw new IllegalArgumentException("Polygons must have an even number of vertices.");
        }
        this.check(ShapeType.Line, null, v1);
        float f = this.color.toFloatBits();
        float f1 = arr_f[0];
        float f2 = arr_f[1];
        int v2 = v + v1;
        while(v < v2) {
            float f3 = arr_f[v];
            float f4 = arr_f[v + 1];
            if(v + 2 >= v1) {
                f5 = f1;
                f6 = f2;
            }
            else {
                f5 = arr_f[v + 2];
                f6 = arr_f[v + 3];
            }
            this.renderer.color(f);
            this.renderer.vertex(f3, f4, 0.0f);
            this.renderer.color(f);
            this.renderer.vertex(f5, f6, 0.0f);
            v += 2;
        }
    }

    public void polyline(float[] arr_f) {
        this.polyline(arr_f, 0, arr_f.length);
    }

    public void polyline(float[] arr_f, int v, int v1) {
        if(v1 < 4) {
            throw new IllegalArgumentException("Polylines must contain at least 2 points.");
        }
        if(v1 % 2 != 0) {
            throw new IllegalArgumentException("Polylines must have an even number of vertices.");
        }
        this.check(ShapeType.Line, null, v1);
        float f = this.color.toFloatBits();
        int v2 = v1 + v - 2;
        while(v < v2) {
            float f1 = arr_f[v];
            float f2 = arr_f[v + 1];
            float f3 = arr_f[v + 2];
            float f4 = arr_f[v + 3];
            this.renderer.color(f);
            this.renderer.vertex(f1, f2, 0.0f);
            this.renderer.color(f);
            this.renderer.vertex(f3, f4, 0.0f);
            v += 2;
        }
    }

    public void rect(float f, float f1, float f2, float f3) {
        this.check(ShapeType.Line, ShapeType.Filled, 8);
        float f4 = this.color.toFloatBits();
        if(this.shapeType == ShapeType.Line) {
            this.renderer.color(f4);
            this.renderer.vertex(f, f1, 0.0f);
            this.renderer.color(f4);
            float f5 = f2 + f;
            this.renderer.vertex(f5, f1, 0.0f);
            this.renderer.color(f4);
            this.renderer.vertex(f5, f1, 0.0f);
            this.renderer.color(f4);
            float f6 = f3 + f1;
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(f4);
            this.renderer.vertex(f5, f6, 0.0f);
            this.renderer.color(f4);
            this.renderer.vertex(f, f6, 0.0f);
            this.renderer.color(f4);
            this.renderer.vertex(f, f6, 0.0f);
            this.renderer.color(f4);
            this.renderer.vertex(f, f1, 0.0f);
            return;
        }
        this.renderer.color(f4);
        this.renderer.vertex(f, f1, 0.0f);
        this.renderer.color(f4);
        float f7 = f2 + f;
        this.renderer.vertex(f7, f1, 0.0f);
        this.renderer.color(f4);
        float f8 = f3 + f1;
        this.renderer.vertex(f7, f8, 0.0f);
        this.renderer.color(f4);
        this.renderer.vertex(f7, f8, 0.0f);
        this.renderer.color(f4);
        this.renderer.vertex(f, f8, 0.0f);
        this.renderer.color(f4);
        this.renderer.vertex(f, f1, 0.0f);
    }

    public void rect(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.rect(f, f1, f2, f3, f4, f5, f6, f7, f8, this.color, this.color, this.color, this.color);
    }

    public void rect(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Color color0, Color color1, Color color2, Color color3) {
        this.check(ShapeType.Line, ShapeType.Filled, 8);
        float f9 = MathUtils.cosDeg(f8);
        float f10 = MathUtils.sinDeg(f8);
        float f11 = -f2;
        float f12 = -f3;
        float f13 = f4 - f2;
        float f14 = f5 - f3;
        if(f6 != 1.0f || f7 != 1.0f) {
            f11 *= f6;
            f12 *= f7;
            f13 *= f6;
            f14 *= f7;
        }
        float f15 = f + f2;
        float f16 = f1 + f3;
        float f17 = f10 * f12;
        float f18 = f9 * f11 - f17 + f15;
        float f19 = f12 * f9;
        float f20 = f11 * f10 + f19 + f16;
        float f21 = f9 * f13;
        float f22 = f21 - f17 + f15;
        float f23 = f13 * f10;
        float f24 = f19 + f23 + f16;
        float f25 = f21 - f10 * f14 + f15;
        float f26 = f23 + f9 * f14 + f16;
        float f27 = f25 - f22 + f18;
        float f28 = f26 - (f24 - f20);
        if(this.shapeType == ShapeType.Line) {
            this.renderer.color(color0.r, color0.g, color0.b, color0.a);
            this.renderer.vertex(f18, f20, 0.0f);
            this.renderer.color(color1.r, color1.g, color1.b, color1.a);
            this.renderer.vertex(f22, f24, 0.0f);
            this.renderer.color(color1.r, color1.g, color1.b, color1.a);
            this.renderer.vertex(f22, f24, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.b, color2.a);
            this.renderer.vertex(f25, f26, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.b, color2.a);
            this.renderer.vertex(f25, f26, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.b, color3.a);
            this.renderer.vertex(f27, f28, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.b, color3.a);
            this.renderer.vertex(f27, f28, 0.0f);
            this.renderer.color(color0.r, color0.g, color0.b, color0.a);
            this.renderer.vertex(f18, f20, 0.0f);
            return;
        }
        this.renderer.color(color0.r, color0.g, color0.b, color0.a);
        this.renderer.vertex(f18, f20, 0.0f);
        this.renderer.color(color1.r, color1.g, color1.b, color1.a);
        this.renderer.vertex(f22, f24, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.b, color2.a);
        this.renderer.vertex(f25, f26, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.b, color2.a);
        this.renderer.vertex(f25, f26, 0.0f);
        this.renderer.color(color3.r, color3.g, color3.b, color3.a);
        this.renderer.vertex(f27, f28, 0.0f);
        this.renderer.color(color0.r, color0.g, color0.b, color0.a);
        this.renderer.vertex(f18, f20, 0.0f);
    }

    public void rect(float f, float f1, float f2, float f3, Color color0, Color color1, Color color2, Color color3) {
        this.check(ShapeType.Line, ShapeType.Filled, 8);
        if(this.shapeType == ShapeType.Line) {
            this.renderer.color(color0.r, color0.g, color0.b, color0.a);
            this.renderer.vertex(f, f1, 0.0f);
            this.renderer.color(color1.r, color1.g, color1.b, color1.a);
            float f4 = f2 + f;
            this.renderer.vertex(f4, f1, 0.0f);
            this.renderer.color(color1.r, color1.g, color1.b, color1.a);
            this.renderer.vertex(f4, f1, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.b, color2.a);
            float f5 = f3 + f1;
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.b, color2.a);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.b, color3.a);
            this.renderer.vertex(f, f5, 0.0f);
            this.renderer.color(color3.r, color3.g, color3.b, color3.a);
            this.renderer.vertex(f, f5, 0.0f);
            this.renderer.color(color0.r, color0.g, color0.b, color0.a);
            this.renderer.vertex(f, f1, 0.0f);
            return;
        }
        this.renderer.color(color0.r, color0.g, color0.b, color0.a);
        this.renderer.vertex(f, f1, 0.0f);
        this.renderer.color(color1.r, color1.g, color1.b, color1.a);
        float f6 = f2 + f;
        this.renderer.vertex(f6, f1, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.b, color2.a);
        float f7 = f3 + f1;
        this.renderer.vertex(f6, f7, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.b, color2.a);
        this.renderer.vertex(f6, f7, 0.0f);
        this.renderer.color(color3.r, color3.g, color3.b, color3.a);
        this.renderer.vertex(f, f7, 0.0f);
        this.renderer.color(color0.r, color0.g, color0.b, color0.a);
        this.renderer.vertex(f, f1, 0.0f);
    }

    public void rectLine(float f, float f1, float f2, float f3, float f4) {
        this.check(ShapeType.Line, ShapeType.Filled, 8);
        float f5 = this.color.toFloatBits();
        Vector2 vector20 = this.tmp.set(f3 - f1, f - f2).nor();
        float f6 = vector20.x * (f4 * 0.5f);
        float f7 = vector20.y * (f4 * 0.5f);
        if(this.shapeType == ShapeType.Line) {
            this.renderer.color(f5);
            float f8 = f + f6;
            float f9 = f1 + f7;
            this.renderer.vertex(f8, f9, 0.0f);
            this.renderer.color(f5);
            float f10 = f - f6;
            float f11 = f1 - f7;
            this.renderer.vertex(f10, f11, 0.0f);
            this.renderer.color(f5);
            float f12 = f2 + f6;
            float f13 = f3 + f7;
            this.renderer.vertex(f12, f13, 0.0f);
            this.renderer.color(f5);
            float f14 = f2 - f6;
            float f15 = f3 - f7;
            this.renderer.vertex(f14, f15, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f12, f13, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f8, f9, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f14, f15, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f10, f11, 0.0f);
            return;
        }
        this.renderer.color(f5);
        this.renderer.vertex(f + f6, f1 + f7, 0.0f);
        this.renderer.color(f5);
        float f16 = f - f6;
        float f17 = f1 - f7;
        this.renderer.vertex(f16, f17, 0.0f);
        this.renderer.color(f5);
        float f18 = f2 + f6;
        float f19 = f3 + f7;
        this.renderer.vertex(f18, f19, 0.0f);
        this.renderer.color(f5);
        this.renderer.vertex(f2 - f6, f3 - f7, 0.0f);
        this.renderer.color(f5);
        this.renderer.vertex(f18, f19, 0.0f);
        this.renderer.color(f5);
        this.renderer.vertex(f16, f17, 0.0f);
    }

    public void rectLine(float f, float f1, float f2, float f3, float f4, Color color0, Color color1) {
        this.check(ShapeType.Line, ShapeType.Filled, 8);
        float f5 = color0.toFloatBits();
        float f6 = color1.toFloatBits();
        Vector2 vector20 = this.tmp.set(f3 - f1, f - f2).nor();
        float f7 = vector20.x * (f4 * 0.5f);
        float f8 = vector20.y * (f4 * 0.5f);
        if(this.shapeType == ShapeType.Line) {
            this.renderer.color(f5);
            float f9 = f + f7;
            float f10 = f1 + f8;
            this.renderer.vertex(f9, f10, 0.0f);
            this.renderer.color(f5);
            float f11 = f - f7;
            float f12 = f1 - f8;
            this.renderer.vertex(f11, f12, 0.0f);
            this.renderer.color(f6);
            float f13 = f2 + f7;
            float f14 = f3 + f8;
            this.renderer.vertex(f13, f14, 0.0f);
            this.renderer.color(f6);
            float f15 = f2 - f7;
            float f16 = f3 - f8;
            this.renderer.vertex(f15, f16, 0.0f);
            this.renderer.color(f6);
            this.renderer.vertex(f13, f14, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f9, f10, 0.0f);
            this.renderer.color(f6);
            this.renderer.vertex(f15, f16, 0.0f);
            this.renderer.color(f5);
            this.renderer.vertex(f11, f12, 0.0f);
            return;
        }
        this.renderer.color(f5);
        this.renderer.vertex(f + f7, f1 + f8, 0.0f);
        this.renderer.color(f5);
        float f17 = f - f7;
        float f18 = f1 - f8;
        this.renderer.vertex(f17, f18, 0.0f);
        this.renderer.color(f6);
        float f19 = f2 + f7;
        float f20 = f3 + f8;
        this.renderer.vertex(f19, f20, 0.0f);
        this.renderer.color(f6);
        this.renderer.vertex(f2 - f7, f3 - f8, 0.0f);
        this.renderer.color(f6);
        this.renderer.vertex(f19, f20, 0.0f);
        this.renderer.color(f5);
        this.renderer.vertex(f17, f18, 0.0f);
    }

    public void rectLine(Vector2 vector20, Vector2 vector21, float f) {
        this.rectLine(vector20.x, vector20.y, vector21.x, vector21.y, f);
    }

    public void rotate(float f, float f1, float f2, float f3) {
        this.transformMatrix.rotate(f, f1, f2, f3);
        this.matrixDirty = true;
    }

    public void scale(float f, float f1, float f2) {
        this.transformMatrix.scale(f, f1, f2);
        this.matrixDirty = true;
    }

    public void set(ShapeType shapeRenderer$ShapeType0) {
        ShapeType shapeRenderer$ShapeType1 = this.shapeType;
        if(shapeRenderer$ShapeType1 == shapeRenderer$ShapeType0) {
            return;
        }
        if(shapeRenderer$ShapeType1 == null) {
            throw new IllegalStateException("begin must be called first.");
        }
        if(!this.autoShapeType) {
            throw new IllegalStateException("autoShapeType must be enabled.");
        }
        this.end();
        this.begin(shapeRenderer$ShapeType0);
    }

    public void setAutoShapeType(boolean z) {
        this.autoShapeType = z;
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
    }

    public void setColor(Color color0) {
        this.color.set(color0);
    }

    public void setProjectionMatrix(Matrix4 matrix40) {
        this.projectionMatrix.set(matrix40);
        this.matrixDirty = true;
    }

    public void setTransformMatrix(Matrix4 matrix40) {
        this.transformMatrix.set(matrix40);
        this.matrixDirty = true;
    }

    public void translate(float f, float f1, float f2) {
        this.transformMatrix.translate(f, f1, f2);
        this.matrixDirty = true;
    }

    public void triangle(float f, float f1, float f2, float f3, float f4, float f5) {
        this.check(ShapeType.Line, ShapeType.Filled, 6);
        float f6 = this.color.toFloatBits();
        if(this.shapeType == ShapeType.Line) {
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, 0.0f);
            this.renderer.color(f6);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(f6);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(f6);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(f6);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(f6);
            this.renderer.vertex(f, f1, 0.0f);
            return;
        }
        this.renderer.color(f6);
        this.renderer.vertex(f, f1, 0.0f);
        this.renderer.color(f6);
        this.renderer.vertex(f2, f3, 0.0f);
        this.renderer.color(f6);
        this.renderer.vertex(f4, f5, 0.0f);
    }

    public void triangle(float f, float f1, float f2, float f3, float f4, float f5, Color color0, Color color1, Color color2) {
        this.check(ShapeType.Line, ShapeType.Filled, 6);
        if(this.shapeType == ShapeType.Line) {
            this.renderer.color(color0.r, color0.g, color0.b, color0.a);
            this.renderer.vertex(f, f1, 0.0f);
            this.renderer.color(color1.r, color1.g, color1.b, color1.a);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(color1.r, color1.g, color1.b, color1.a);
            this.renderer.vertex(f2, f3, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.b, color2.a);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(color2.r, color2.g, color2.b, color2.a);
            this.renderer.vertex(f4, f5, 0.0f);
            this.renderer.color(color0.r, color0.g, color0.b, color0.a);
            this.renderer.vertex(f, f1, 0.0f);
            return;
        }
        this.renderer.color(color0.r, color0.g, color0.b, color0.a);
        this.renderer.vertex(f, f1, 0.0f);
        this.renderer.color(color1.r, color1.g, color1.b, color1.a);
        this.renderer.vertex(f2, f3, 0.0f);
        this.renderer.color(color2.r, color2.g, color2.b, color2.a);
        this.renderer.vertex(f4, f5, 0.0f);
    }

    public void updateMatrices() {
        this.matrixDirty = true;
    }

    public void x(float f, float f1, float f2) {
        float f3 = f - f2;
        float f4 = f1 - f2;
        float f5 = f + f2;
        float f6 = f1 + f2;
        this.line(f3, f4, f5, f6);
        this.line(f3, f6, f5, f4);
    }

    public void x(Vector2 vector20, float f) {
        this.x(vector20.x, vector20.y, f);
    }
}

