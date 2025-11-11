package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

public class ShaderProgramLoader extends AsynchronousAssetLoader {
    public static class ShaderProgramParameter extends AssetLoaderParameters {
        public String fragmentFile;
        public boolean logOnCompileFailure;
        public String prependFragmentCode;
        public String prependVertexCode;
        public String vertexFile;

        public ShaderProgramParameter() {
            this.logOnCompileFailure = true;
        }
    }

    private String fragmentFileSuffix;
    private String vertexFileSuffix;

    public ShaderProgramLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.vertexFileSuffix = ".vert";
        this.fragmentFileSuffix = ".frag";
    }

    public ShaderProgramLoader(FileHandleResolver fileHandleResolver0, String s, String s1) {
        super(fileHandleResolver0);
        this.vertexFileSuffix = s;
        this.fragmentFileSuffix = s1;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((ShaderProgramParameter)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, ShaderProgramParameter shaderProgramLoader$ShaderProgramParameter0) {
        return null;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, ShaderProgramParameter shaderProgramLoader$ShaderProgramParameter0) {
    }

    public ShaderProgram loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, ShaderProgramParameter shaderProgramLoader$ShaderProgramParameter0) {
        String s2;
        String s1 = null;
        if(shaderProgramLoader$ShaderProgramParameter0 == null) {
            s2 = null;
        }
        else {
            s2 = shaderProgramLoader$ShaderProgramParameter0.vertexFile == null ? null : shaderProgramLoader$ShaderProgramParameter0.vertexFile;
            if(shaderProgramLoader$ShaderProgramParameter0.fragmentFile != null) {
                s1 = shaderProgramLoader$ShaderProgramParameter0.fragmentFile;
            }
        }
        if(s2 == null && s.endsWith(this.fragmentFileSuffix)) {
            s2 = s.substring(0, s.length() - this.fragmentFileSuffix.length()) + this.vertexFileSuffix;
        }
        if(s1 == null && s.endsWith(this.vertexFileSuffix)) {
            s1 = s.substring(0, s.length() - this.vertexFileSuffix.length()) + this.fragmentFileSuffix;
        }
        FileHandle fileHandle1 = s2 == null ? fileHandle0 : this.resolve(s2);
        if(s1 != null) {
            fileHandle0 = this.resolve(s1);
        }
        String s3 = fileHandle1.readString();
        String s4 = fileHandle1.equals(fileHandle0) ? s3 : fileHandle0.readString();
        if(shaderProgramLoader$ShaderProgramParameter0 != null) {
            if(shaderProgramLoader$ShaderProgramParameter0.prependVertexCode != null) {
                s3 = shaderProgramLoader$ShaderProgramParameter0.prependVertexCode + s3;
            }
            if(shaderProgramLoader$ShaderProgramParameter0.prependFragmentCode != null) {
                s4 = shaderProgramLoader$ShaderProgramParameter0.prependFragmentCode + s4;
            }
        }
        ShaderProgram shaderProgram0 = new ShaderProgram(s3, s4);
        if((shaderProgramLoader$ShaderProgramParameter0 == null || shaderProgramLoader$ShaderProgramParameter0.logOnCompileFailure) && !shaderProgram0.isCompiled()) {
            assetManager0.getLogger().error("ShaderProgram " + s + " failed to compile:\n" + shaderProgram0.getLog());
        }
        return shaderProgram0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((ShaderProgramParameter)assetLoaderParameters0));
    }
}

