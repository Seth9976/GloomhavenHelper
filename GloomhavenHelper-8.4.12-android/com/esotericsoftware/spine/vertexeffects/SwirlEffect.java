package com.esotericsoftware.spine.vertexeffects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer.VertexEffect;
import com.esotericsoftware.spine.utils.SpineUtils;

public class SwirlEffect implements VertexEffect {
    private float angle;
    private float centerX;
    private float centerY;
    private Interpolation interpolation;
    private float radius;
    private float worldX;
    private float worldY;

    public SwirlEffect(float f) {
        this.interpolation = Interpolation.pow2Out;
        this.radius = f;
    }

    @Override  // com.esotericsoftware.spine.SkeletonRenderer$VertexEffect
    public void begin(Skeleton skeleton0) {
        this.worldX = skeleton0.getX() + this.centerX;
        this.worldY = skeleton0.getY() + this.centerY;
    }

    @Override  // com.esotericsoftware.spine.SkeletonRenderer$VertexEffect
    public void end() {
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public void setAngle(float f) {
        this.angle = f * 0.017453f;
    }

    public void setCenter(float f, float f1) {
        this.centerX = f;
        this.centerY = f1;
    }

    public void setCenterX(float f) {
        this.centerX = f;
    }

    public void setCenterY(float f) {
        this.centerY = f;
    }

    public void setInterpolation(Interpolation interpolation0) {
        this.interpolation = interpolation0;
    }

    public void setRadius(float f) {
        this.radius = f;
    }

    @Override  // com.esotericsoftware.spine.SkeletonRenderer$VertexEffect
    public void transform(Vector2 vector20, Vector2 vector21, Color color0, Color color1) {
        float f = vector20.x - this.worldX;
        float f1 = vector20.y - this.worldY;
        float f2 = (float)Math.sqrt(f * f + f1 * f1);
        float f3 = this.radius;
        if(f2 < f3) {
            float f4 = this.interpolation.apply(0.0f, this.angle, (f3 - f2) / f3);
            float f5 = SpineUtils.cos(f4);
            float f6 = SpineUtils.sin(f4);
            vector20.x = f5 * f - f6 * f1 + this.worldX;
            vector20.y = f6 * f + f5 * f1 + this.worldY;
        }
    }
}

