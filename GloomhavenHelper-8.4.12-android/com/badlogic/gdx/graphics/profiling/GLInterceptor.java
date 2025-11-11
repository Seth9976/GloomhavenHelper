package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.FloatCounter;

public abstract class GLInterceptor implements GL20 {
    protected int calls;
    protected int drawCalls;
    protected GLProfiler glProfiler;
    protected int shaderSwitches;
    protected int textureBindings;
    protected final FloatCounter vertexCount;

    protected GLInterceptor(GLProfiler gLProfiler0) {
        this.vertexCount = new FloatCounter(0);
        this.glProfiler = gLProfiler0;
    }

    public int getCalls() {
        return this.calls;
    }

    public int getDrawCalls() {
        return this.drawCalls;
    }

    public int getShaderSwitches() {
        return this.shaderSwitches;
    }

    public int getTextureBindings() {
        return this.textureBindings;
    }

    public FloatCounter getVertexCount() {
        return this.vertexCount;
    }

    public void reset() {
        this.calls = 0;
        this.textureBindings = 0;
        this.drawCalls = 0;
        this.shaderSwitches = 0;
        this.vertexCount.reset();
    }

    public static String resolveErrorNumber(int v) {
        switch(v) {
            case 0x500: {
                return "GL_INVALID_ENUM";
            }
            case 0x501: {
                return "GL_INVALID_VALUE";
            }
            case 0x502: {
                return "GL_INVALID_OPERATION";
            }
            case 0x505: {
                return "GL_OUT_OF_MEMORY";
            }
            case 0x506: {
                return "GL_INVALID_FRAMEBUFFER_OPERATION";
            }
            default: {
                return "number " + v;
            }
        }
    }
}

