package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public abstract class ParticleSorter {
    public static class Distance extends ParticleSorter {
        private int currentSize;
        private float[] distances;
        private int[] particleIndices;
        private int[] particleOffsets;

        public Distance() {
            this.currentSize = 0;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public void ensureCapacity(int v) {
            if(this.currentSize < v) {
                this.distances = new float[v];
                this.particleIndices = new int[v];
                this.particleOffsets = new int[v];
                this.currentSize = v;
            }
        }

        public void qsort(int v, int v1) {
            if(v < v1) {
                if(v1 - v <= 8) {
                    for(int v2 = v; v2 <= v1; ++v2) {
                        for(int v3 = v2; v3 > v; --v3) {
                            float[] arr_f = this.distances;
                            if(arr_f[v3 - 1] <= arr_f[v3]) {
                                break;
                            }
                            float f = arr_f[v3];
                            arr_f[v3] = arr_f[v3 - 1];
                            arr_f[v3 - 1] = f;
                            int[] arr_v = this.particleIndices;
                            int v4 = arr_v[v3];
                            arr_v[v3] = arr_v[v3 - 1];
                            arr_v[v3 - 1] = v4;
                        }
                    }
                    return;
                }
                float f1 = this.distances[v];
                int v5 = v + 1;
                int v6 = this.particleIndices[v];
                int v7 = v5;
                while(v5 <= v1) {
                    float[] arr_f1 = this.distances;
                    if(f1 > arr_f1[v5]) {
                        if(v5 > v7) {
                            float f2 = arr_f1[v5];
                            arr_f1[v5] = arr_f1[v7];
                            arr_f1[v7] = f2;
                            int[] arr_v1 = this.particleIndices;
                            int v8 = arr_v1[v5];
                            arr_v1[v5] = arr_v1[v7];
                            arr_v1[v7] = v8;
                        }
                        ++v7;
                    }
                    ++v5;
                }
                float[] arr_f2 = this.distances;
                arr_f2[v] = arr_f2[v7 - 1];
                arr_f2[v7 - 1] = f1;
                int[] arr_v2 = this.particleIndices;
                arr_v2[v] = arr_v2[v7 - 1];
                arr_v2[v7 - 1] = v6;
                this.qsort(v, v7 - 2);
                this.qsort(v7, v1);
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public int[] sort(Array array0) {
            float[] arr_f = this.camera.view.val;
            float f = arr_f[2];
            float f1 = arr_f[6];
            float f2 = arr_f[10];
            int v1 = 0;
            int v2 = 0;
            for(Object object0: array0) {
                ParticleControllerRenderData particleControllerRenderData0 = (ParticleControllerRenderData)object0;
                int v3 = particleControllerRenderData0.controller.particles.size + v2;
                for(int v4 = 0; v2 < v3; v4 += particleControllerRenderData0.positionChannel.strideSize) {
                    this.distances[v2] = particleControllerRenderData0.positionChannel.data[v4] * f + particleControllerRenderData0.positionChannel.data[v4 + 1] * f1 + particleControllerRenderData0.positionChannel.data[v4 + 2] * f2;
                    this.particleIndices[v2] = v2;
                    ++v2;
                }
                v1 += particleControllerRenderData0.controller.particles.size;
            }
            this.qsort(0, v1 - 1);
            for(int v = 0; v < v1; ++v) {
                this.particleOffsets[this.particleIndices[v]] = v;
            }
            return this.particleOffsets;
        }
    }

    public static class None extends ParticleSorter {
        int currentCapacity;
        int[] indices;

        public None() {
            this.currentCapacity = 0;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public void ensureCapacity(int v) {
            if(this.currentCapacity < v) {
                this.indices = new int[v];
                for(int v1 = 0; v1 < v; ++v1) {
                    this.indices[v1] = v1;
                }
                this.currentCapacity = v;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public int[] sort(Array array0) {
            return this.indices;
        }
    }

    static final Vector3 TMP_V1;
    protected Camera camera;

    static {
        ParticleSorter.TMP_V1 = new Vector3();
    }

    public void ensureCapacity(int v) {
    }

    public void setCamera(Camera camera0) {
        this.camera = camera0;
    }

    public abstract int[] sort(Array arg1);
}

