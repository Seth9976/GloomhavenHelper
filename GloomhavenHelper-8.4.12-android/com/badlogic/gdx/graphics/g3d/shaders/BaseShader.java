package com.badlogic.gdx.graphics.g3d.shaders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntIntMap;

public abstract class BaseShader implements Shader {
    public static abstract class GlobalSetter implements Setter {
        @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
        public boolean isGlobal(BaseShader baseShader0, int v) {
            return true;
        }
    }

    public static abstract class LocalSetter implements Setter {
        @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
        public boolean isGlobal(BaseShader baseShader0, int v) {
            return false;
        }
    }

    public interface Setter {
        boolean isGlobal(BaseShader arg1, int arg2);

        void set(BaseShader arg1, int arg2, Renderable arg3, Attributes arg4);
    }

    public static class Uniform implements Validator {
        public final String alias;
        public final long environmentMask;
        public final long materialMask;
        public final long overallMask;

        public Uniform(String s) {
            this(s, 0L, 0L);
        }

        public Uniform(String s, long v) {
            this(s, 0L, 0L, v);
        }

        public Uniform(String s, long v, long v1) {
            this(s, v, v1, 0L);
        }

        public Uniform(String s, long v, long v1, long v2) {
            this.alias = s;
            this.materialMask = v;
            this.environmentMask = v1;
            this.overallMask = v2;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Validator
        public boolean validate(BaseShader baseShader0, int v, Renderable renderable0) {
            long v1 = 0L;
            long v2 = renderable0 == null || renderable0.material == null ? 0L : renderable0.material.getMask();
            if(renderable0 != null && renderable0.environment != null) {
                v1 = renderable0.environment.getMask();
            }
            return (v2 & this.materialMask) == this.materialMask && (v1 & this.environmentMask) == this.environmentMask && ((v1 | v2) & this.overallMask) == this.overallMask;
        }
    }

    public interface Validator {
        boolean validate(BaseShader arg1, int arg2, Renderable arg3);
    }

    private final IntIntMap attributes;
    public Camera camera;
    private Attributes combinedAttributes;
    public RenderContext context;
    private Mesh currentMesh;
    private final IntArray globalUniforms;
    private final IntArray localUniforms;
    private int[] locations;
    public ShaderProgram program;
    private final Array setters;
    private final IntArray tempArray;
    private final Array uniforms;
    private final Array validators;

    public BaseShader() {
        this.uniforms = new Array();
        this.validators = new Array();
        this.setters = new Array();
        this.globalUniforms = new IntArray();
        this.localUniforms = new IntArray();
        this.attributes = new IntIntMap();
        this.tempArray = new IntArray();
        this.combinedAttributes = new Attributes();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public void begin(Camera camera0, RenderContext renderContext0) {
        this.camera = camera0;
        this.context = renderContext0;
        this.program.bind();
        this.currentMesh = null;
        for(int v = 0; v < this.globalUniforms.size; ++v) {
            int v1 = this.globalUniforms.get(v);
            if(this.setters.get(v1) != null) {
                ((Setter)this.setters.get(v1)).set(this, v1, null, null);
            }
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.program = null;
        this.uniforms.clear();
        this.validators.clear();
        this.setters.clear();
        this.localUniforms.clear();
        this.globalUniforms.clear();
        this.locations = null;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public void end() {
        Mesh mesh0 = this.currentMesh;
        if(mesh0 != null) {
            mesh0.unbind(this.program, this.tempArray.items);
            this.currentMesh = null;
        }
    }

    private final int[] getAttributeLocations(VertexAttributes vertexAttributes0) {
        this.tempArray.clear();
        int v = vertexAttributes0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = vertexAttributes0.get(v1).getKey();
            int v3 = this.attributes.get(v2, -1);
            this.tempArray.add(v3);
        }
        this.tempArray.shrink();
        return this.tempArray.items;
    }

    public String getUniformAlias(int v) {
        return (String)this.uniforms.get(v);
    }

    public int getUniformID(String s) {
        int v = this.uniforms.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((String)this.uniforms.get(v1)).equals(s)) {
                return v1;
            }
        }
        return -1;
    }

    public final boolean has(int v) {
        return v >= 0 && (v < this.locations.length && this.locations[v] >= 0);
    }

    public void init(ShaderProgram shaderProgram0, Renderable renderable0) {
        if(this.locations != null) {
            throw new GdxRuntimeException("Already initialized");
        }
        if(!shaderProgram0.isCompiled()) {
            throw new GdxRuntimeException(shaderProgram0.getLog());
        }
        this.program = shaderProgram0;
        int v = this.uniforms.size;
        this.locations = new int[v];
        for(int v2 = 0; v2 < v; ++v2) {
            String s = (String)this.uniforms.get(v2);
            Validator baseShader$Validator0 = (Validator)this.validators.get(v2);
            Setter baseShader$Setter0 = (Setter)this.setters.get(v2);
            if(baseShader$Validator0 == null || baseShader$Validator0.validate(this, v2, renderable0)) {
                int[] arr_v = this.locations;
                arr_v[v2] = shaderProgram0.fetchUniformLocation(s, false);
                if(this.locations[v2] >= 0 && baseShader$Setter0 != null) {
                    if(baseShader$Setter0.isGlobal(this, v2)) {
                        this.globalUniforms.add(v2);
                    }
                    else {
                        this.localUniforms.add(v2);
                    }
                }
            }
            else {
                this.locations[v2] = -1;
            }
            if(this.locations[v2] < 0) {
                this.validators.set(v2, null);
                this.setters.set(v2, null);
            }
        }
        if(renderable0 != null) {
            VertexAttributes vertexAttributes0 = renderable0.meshPart.mesh.getVertexAttributes();
            int v3 = vertexAttributes0.size();
            for(int v1 = 0; v1 < v3; ++v1) {
                VertexAttribute vertexAttribute0 = vertexAttributes0.get(v1);
                int v4 = shaderProgram0.getAttributeLocation(vertexAttribute0.alias);
                if(v4 >= 0) {
                    this.attributes.put(vertexAttribute0.getKey(), v4);
                }
            }
        }
    }

    public final int loc(int v) {
        if(v >= 0) {
            return v >= this.locations.length ? -1 : this.locations[v];
        }
        return -1;
    }

    public int register(Uniform baseShader$Uniform0) {
        return this.register(baseShader$Uniform0, null);
    }

    public int register(Uniform baseShader$Uniform0, Setter baseShader$Setter0) {
        return this.register(baseShader$Uniform0.alias, baseShader$Uniform0, baseShader$Setter0);
    }

    public int register(String s) {
        return this.register(s, null, null);
    }

    public int register(String s, Setter baseShader$Setter0) {
        return this.register(s, null, baseShader$Setter0);
    }

    public int register(String s, Validator baseShader$Validator0) {
        return this.register(s, baseShader$Validator0, null);
    }

    public int register(String s, Validator baseShader$Validator0, Setter baseShader$Setter0) {
        if(this.locations != null) {
            throw new GdxRuntimeException("Cannot register an uniform after initialization");
        }
        int v = this.getUniformID(s);
        if(v >= 0) {
            this.validators.set(v, baseShader$Validator0);
            this.setters.set(v, baseShader$Setter0);
            return v;
        }
        this.uniforms.add(s);
        this.validators.add(baseShader$Validator0);
        this.setters.add(baseShader$Setter0);
        return this.uniforms.size - 1;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public void render(Renderable renderable0) {
        if(renderable0.worldTransform.det3x3() == 0.0f) {
            return;
        }
        this.combinedAttributes.clear();
        if(renderable0.environment != null) {
            this.combinedAttributes.set(renderable0.environment);
        }
        if(renderable0.material != null) {
            this.combinedAttributes.set(renderable0.material);
        }
        this.render(renderable0, this.combinedAttributes);
    }

    public void render(Renderable renderable0, Attributes attributes0) {
        for(int v = 0; v < this.localUniforms.size; ++v) {
            int v1 = this.localUniforms.get(v);
            if(this.setters.get(v1) != null) {
                ((Setter)this.setters.get(v1)).set(this, v1, renderable0, attributes0);
            }
        }
        if(this.currentMesh != renderable0.meshPart.mesh) {
            Mesh mesh0 = this.currentMesh;
            if(mesh0 != null) {
                mesh0.unbind(this.program, this.tempArray.items);
            }
            this.currentMesh = renderable0.meshPart.mesh;
            this.currentMesh.bind(this.program, this.getAttributeLocations(renderable0.meshPart.mesh.getVertexAttributes()));
        }
        renderable0.meshPart.render(this.program, false);
    }

    public final boolean set(int v, float f) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformf(arr_v[v], f);
        return true;
    }

    public final boolean set(int v, float f, float f1) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformf(arr_v[v], f, f1);
        return true;
    }

    public final boolean set(int v, float f, float f1, float f2) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformf(arr_v[v], f, f1, f2);
        return true;
    }

    public final boolean set(int v, float f, float f1, float f2, float f3) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformf(arr_v[v], f, f1, f2, f3);
        return true;
    }

    public final boolean set(int v, int v1) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformi(arr_v[v], v1);
        return true;
    }

    public final boolean set(int v, int v1, int v2) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformi(arr_v[v], v1, v2);
        return true;
    }

    public final boolean set(int v, int v1, int v2, int v3) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformi(arr_v[v], v1, v2, v3);
        return true;
    }

    public final boolean set(int v, int v1, int v2, int v3, int v4) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformi(arr_v[v], v1, v2, v3, v4);
        return true;
    }

    public final boolean set(int v, Color color0) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformf(arr_v[v], color0);
        return true;
    }

    public final boolean set(int v, GLTexture gLTexture0) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformi(arr_v[v], this.context.textureBinder.bind(gLTexture0));
        return true;
    }

    public final boolean set(int v, TextureDescriptor textureDescriptor0) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformi(arr_v[v], this.context.textureBinder.bind(textureDescriptor0));
        return true;
    }

    public final boolean set(int v, Matrix3 matrix30) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformMatrix(arr_v[v], matrix30);
        return true;
    }

    public final boolean set(int v, Matrix4 matrix40) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformMatrix(arr_v[v], matrix40);
        return true;
    }

    public final boolean set(int v, Vector2 vector20) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformf(arr_v[v], vector20);
        return true;
    }

    public final boolean set(int v, Vector3 vector30) {
        int[] arr_v = this.locations;
        if(arr_v[v] < 0) {
            return false;
        }
        this.program.setUniformf(arr_v[v], vector30);
        return true;
    }
}

