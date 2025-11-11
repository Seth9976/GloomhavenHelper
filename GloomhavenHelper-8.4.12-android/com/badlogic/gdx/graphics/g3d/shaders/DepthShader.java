package com.badlogic.gdx.graphics.g3d.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DepthShader extends DefaultShader {
    public static class Config extends com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Config {
        public float defaultAlphaTest;
        public boolean depthBufferOnly;

        public Config() {
            this.depthBufferOnly = false;
            this.defaultAlphaTest = 0.5f;
            this.defaultCullFace = 0x404;
        }

        public Config(String s, String s1) {
            super(s, s1);
            this.depthBufferOnly = false;
            this.defaultAlphaTest = 0.5f;
        }
    }

    private final FloatAttribute alphaTestAttribute;
    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    public final int numBones;
    private static final Attributes tmpAttributes;
    public final int weights;

    static {
        DepthShader.tmpAttributes = new Attributes();
    }

    public DepthShader(Renderable renderable0) {
        this(renderable0, new Config());
    }

    public DepthShader(Renderable renderable0, Config depthShader$Config0) {
        this(renderable0, depthShader$Config0, DepthShader.createPrefix(renderable0, depthShader$Config0));
    }

    public DepthShader(Renderable renderable0, Config depthShader$Config0, ShaderProgram shaderProgram0) {
        super(renderable0, depthShader$Config0, shaderProgram0);
        DepthShader.combineAttributes(renderable0);
        if(renderable0.bones != null && renderable0.bones.length > depthShader$Config0.numBones) {
            throw new GdxRuntimeException("too many bones: " + renderable0.bones.length + ", max configured: " + depthShader$Config0.numBones);
        }
        this.numBones = renderable0.bones == null ? 0 : depthShader$Config0.numBones;
        int v1 = renderable0.meshPart.mesh.getVertexAttributes().size();
        int v2 = 0;
        for(int v = 0; v < v1; ++v) {
            VertexAttribute vertexAttribute0 = renderable0.meshPart.mesh.getVertexAttributes().get(v);
            if(vertexAttribute0.usage == 0x40) {
                v2 |= 1 << vertexAttribute0.unit;
            }
        }
        this.weights = v2;
        this.alphaTestAttribute = new FloatAttribute(FloatAttribute.AlphaTest, depthShader$Config0.defaultAlphaTest);
    }

    public DepthShader(Renderable renderable0, Config depthShader$Config0, String s) {
        this(renderable0, depthShader$Config0, s, (depthShader$Config0.vertexShader == null ? DepthShader.getDefaultVertexShader() : depthShader$Config0.vertexShader), (depthShader$Config0.fragmentShader == null ? DepthShader.getDefaultFragmentShader() : depthShader$Config0.fragmentShader));
    }

    public DepthShader(Renderable renderable0, Config depthShader$Config0, String s, String s1, String s2) {
        this(renderable0, depthShader$Config0, new ShaderProgram(s + s1, s + s2));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader
    public void begin(Camera camera0, RenderContext renderContext0) {
        super.begin(camera0, renderContext0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader
    public boolean canRender(Renderable renderable0) {
        if(renderable0.bones != null && renderable0.bones.length > this.numBones) {
            return false;
        }
        Attributes attributes0 = DepthShader.combineAttributes(renderable0);
        if(attributes0.has(BlendingAttribute.Type)) {
            if((this.attributesMask & BlendingAttribute.Type) != BlendingAttribute.Type) {
                return false;
            }
            return attributes0.has(TextureAttribute.Diffuse) == ((this.attributesMask & TextureAttribute.Diffuse) == TextureAttribute.Diffuse) ? ((renderable0.meshPart.mesh.getVertexAttributes().getMask() & 0x40L) == 0x40L ? 1 : 0) == (this.weights <= 0 ? 0 : 1) : false;
        }
        return ((renderable0.meshPart.mesh.getVertexAttributes().getMask() & 0x40L) == 0x40L ? 1 : 0) == (this.weights <= 0 ? 0 : 1);
    }

    private static final Attributes combineAttributes(Renderable renderable0) {
        DepthShader.tmpAttributes.clear();
        if(renderable0.environment != null) {
            DepthShader.tmpAttributes.set(renderable0.environment);
        }
        if(renderable0.material != null) {
            DepthShader.tmpAttributes.set(renderable0.material);
        }
        return DepthShader.tmpAttributes;
    }

    public static String createPrefix(Renderable renderable0, Config depthShader$Config0) {
        String s = DefaultShader.createPrefix(renderable0, depthShader$Config0);
        return depthShader$Config0.depthBufferOnly ? s : s + "#define PackedDepthFlag\n";
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader
    public void end() {
        super.end();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader
    public static final String getDefaultFragmentShader() {
        if(DepthShader.defaultFragmentShader == null) {
            DepthShader.defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/depth.fragment.glsl").readString();
        }
        return DepthShader.defaultFragmentShader;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader
    public static final String getDefaultVertexShader() {
        if(DepthShader.defaultVertexShader == null) {
            DepthShader.defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/depth.vertex.glsl").readString();
        }
        return DepthShader.defaultVertexShader;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.DefaultShader
    public void render(Renderable renderable0, Attributes attributes0) {
        if(attributes0.has(BlendingAttribute.Type)) {
            BlendingAttribute blendingAttribute0 = (BlendingAttribute)attributes0.get(BlendingAttribute.Type);
            attributes0.remove(BlendingAttribute.Type);
            boolean z = attributes0.has(FloatAttribute.AlphaTest);
            if(!z) {
                attributes0.set(this.alphaTestAttribute);
            }
            if(blendingAttribute0.opacity >= ((FloatAttribute)attributes0.get(FloatAttribute.AlphaTest)).value) {
                super.render(renderable0, attributes0);
            }
            if(!z) {
                attributes0.remove(FloatAttribute.AlphaTest);
            }
            attributes0.set(blendingAttribute0);
            return;
        }
        super.render(renderable0, attributes0);
    }
}

