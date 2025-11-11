package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Config;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;

public class DefaultShaderProvider extends BaseShaderProvider {
    public final Config config;

    public DefaultShaderProvider() {
        this(null);
    }

    public DefaultShaderProvider(FileHandle fileHandle0, FileHandle fileHandle1) {
        this(fileHandle0.readString(), fileHandle1.readString());
    }

    public DefaultShaderProvider(Config defaultShader$Config0) {
        if(defaultShader$Config0 == null) {
            defaultShader$Config0 = new Config();
        }
        this.config = defaultShader$Config0;
    }

    public DefaultShaderProvider(String s, String s1) {
        this(new Config(s, s1));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.utils.BaseShaderProvider
    protected Shader createShader(Renderable renderable0) {
        return new DefaultShader(renderable0, this.config);
    }
}

