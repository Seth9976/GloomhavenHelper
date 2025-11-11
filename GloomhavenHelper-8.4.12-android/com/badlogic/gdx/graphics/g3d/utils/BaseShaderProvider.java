package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public abstract class BaseShaderProvider implements ShaderProvider {
    protected Array shaders;

    public BaseShaderProvider() {
        this.shaders = new Array();
    }

    protected abstract Shader createShader(Renderable arg1);

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        for(Object object0: this.shaders) {
            ((Shader)object0).dispose();
        }
        this.shaders.clear();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.ShaderProvider
    public Shader getShader(Renderable renderable0) {
        Shader shader0 = renderable0.shader;
        if(shader0 != null && shader0.canRender(renderable0)) {
            return shader0;
        }
        for(Object object0: this.shaders) {
            Shader shader1 = (Shader)object0;
            if(shader1.canRender(renderable0)) {
                return shader1;
            }
            if(false) {
                break;
            }
        }
        Shader shader2 = this.createShader(renderable0);
        if(!shader2.canRender(renderable0)) {
            throw new GdxRuntimeException("unable to provide a shader for this renderable");
        }
        shader2.init();
        this.shaders.add(shader2);
        return shader2;
    }
}

