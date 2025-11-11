package com.esotericsoftware.spine.vertexeffects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer.VertexEffect;

public class JitterEffect implements VertexEffect {
    private float x;
    private float y;

    public JitterEffect(float f, float f1) {
        this.x = f;
        this.y = f1;
    }

    @Override  // com.esotericsoftware.spine.SkeletonRenderer$VertexEffect
    public void begin(Skeleton skeleton0) {
    }

    @Override  // com.esotericsoftware.spine.SkeletonRenderer$VertexEffect
    public void end() {
    }

    public void setJitter(float f, float f1) {
        this.x = f;
        this.y = f1;
    }

    public void setJitterX(float f) {
        this.x = f;
    }

    public void setJitterY(float f) {
        this.y = f;
    }

    @Override  // com.esotericsoftware.spine.SkeletonRenderer$VertexEffect
    public void transform(Vector2 vector20, Vector2 vector21, Color color0, Color color1) {
        vector20.x += MathUtils.randomTriangular(-this.x, this.y);
        vector20.y += MathUtils.randomTriangular(-this.x, this.y);
    }
}

