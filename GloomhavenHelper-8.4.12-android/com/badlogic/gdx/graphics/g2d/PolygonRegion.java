package com.badlogic.gdx.graphics.g2d;

public class PolygonRegion {
    final TextureRegion region;
    final float[] textureCoords;
    final short[] triangles;
    final float[] vertices;

    public PolygonRegion(TextureRegion textureRegion0, float[] arr_f, short[] arr_v) {
        this.region = textureRegion0;
        this.vertices = arr_f;
        this.triangles = arr_v;
        float[] arr_f1 = new float[arr_f.length];
        this.textureCoords = arr_f1;
        float f = textureRegion0.u;
        float f1 = textureRegion0.v;
        float f2 = textureRegion0.u2 - f;
        float f3 = textureRegion0.v2 - f1;
        int v = textureRegion0.regionWidth;
        int v1 = textureRegion0.regionHeight;
        for(int v2 = 0; v2 < arr_f.length; v2 += 2) {
            arr_f1[v2] = arr_f[v2] / ((float)v) * f2 + f;
            arr_f1[v2 + 1] = (1.0f - arr_f[v2 + 1] / ((float)v1)) * f3 + f1;
        }
    }

    public TextureRegion getRegion() {
        return this.region;
    }

    public float[] getTextureCoords() {
        return this.textureCoords;
    }

    public short[] getTriangles() {
        return this.triangles;
    }

    public float[] getVertices() {
        return this.vertices;
    }
}

