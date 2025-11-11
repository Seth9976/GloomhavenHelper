package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.shaders.DepthShader.Config;
import com.badlogic.gdx.graphics.g3d.shaders.DepthShader;

public class DepthShaderProvider extends BaseShaderProvider {
    public final Config config;

    public DepthShaderProvider() {
        this(null);
    }

    public DepthShaderProvider(FileHandle fileHandle0, FileHandle fileHandle1) {
        this(fileHandle0.readString(), fileHandle1.readString());
    }

    public DepthShaderProvider(Config depthShader$Config0) {
        if(depthShader$Config0 == null) {
            depthShader$Config0 = new Config();
        }
        this.config = depthShader$Config0;
    }

    public DepthShaderProvider(String s, String s1) {
        this(new Config(s, s1));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.BaseShaderProvider
    protected Shader createShader(Renderable renderable0) {
        return new DepthShader(renderable0, this.config);
    }
}

