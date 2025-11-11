package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Uniform;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ParticleShader extends BaseShader {
    public static enum AlignMode {
        Screen,
        ViewPoint;

    }

    public static class Config {
        public AlignMode align;
        public int defaultCullFace;
        public int defaultDepthFunc;
        public String fragmentShader;
        public boolean ignoreUnimplemented;
        public ParticleType type;
        public String vertexShader;

        public Config() {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
        }

        public Config(AlignMode particleShader$AlignMode0) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.type = ParticleType.Billboard;
            this.align = particleShader$AlignMode0;
        }

        public Config(AlignMode particleShader$AlignMode0, ParticleType particleShader$ParticleType0) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = particleShader$AlignMode0;
            this.type = particleShader$ParticleType0;
        }

        public Config(ParticleType particleShader$ParticleType0) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = particleShader$ParticleType0;
        }

        public Config(String s, String s1) {
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
            this.vertexShader = s;
            this.fragmentShader = s1;
        }
    }

    public static class Inputs {
        public static final Uniform cameraInvDirection;
        public static final Uniform cameraRight;
        public static final Uniform regionSize;
        public static final Uniform screenWidth;

        static {
            Inputs.cameraRight = new Uniform("u_cameraRight");
            Inputs.cameraInvDirection = new Uniform("u_cameraInvDirection");
            Inputs.screenWidth = new Uniform("u_screenWidth");
            Inputs.regionSize = new Uniform("u_regionSize");
        }
    }

    public static enum ParticleType {
        Billboard,
        Point;

    }

    public static class Setters {
        public static final Setter cameraInvDirection;
        public static final Setter cameraPosition;
        public static final Setter cameraRight;
        public static final Setter cameraUp;
        public static final Setter screenWidth;
        public static final Setter worldViewTrans;

        static {
            Setters.cameraRight = new Setter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public boolean isGlobal(BaseShader baseShader0, int v) {
                    return true;
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ParticleShader.TMP_VECTOR3.set(baseShader0.camera.direction).crs(baseShader0.camera.up).nor());
                }
            };
            Setters.cameraUp = new Setter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public boolean isGlobal(BaseShader baseShader0, int v) {
                    return true;
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ParticleShader.TMP_VECTOR3.set(baseShader0.camera.up).nor());
                }
            };
            Setters.cameraInvDirection = new Setter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public boolean isGlobal(BaseShader baseShader0, int v) {
                    return true;
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ParticleShader.TMP_VECTOR3.set(-baseShader0.camera.direction.x, -baseShader0.camera.direction.y, -baseShader0.camera.direction.z).nor());
                }
            };
            Setters.cameraPosition = new Setter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public boolean isGlobal(BaseShader baseShader0, int v) {
                    return true;
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.position);
                }
            };
            Setters.screenWidth = new Setter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public boolean isGlobal(BaseShader baseShader0, int v) {
                    return true;
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ((float)Gdx.graphics.getWidth()));
                }
            };
            Setters.worldViewTrans = new Setter() {
                final Matrix4 temp;

                {
                    this.temp = new Matrix4();
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public boolean isGlobal(BaseShader baseShader0, int v) {
                    return false;
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, this.temp.set(baseShader0.camera.view).mul(renderable0.worldTransform));
                }
            };
        }
    }

    static final Vector3 TMP_VECTOR3;
    protected final Config config;
    Material currentMaterial;
    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    protected static long implementedFlags;
    private long materialMask;
    private static final long optionalAttributes;
    private Renderable renderable;
    private long vertexMask;

    static {
        ParticleShader.implementedFlags = BlendingAttribute.Type | TextureAttribute.Diffuse;
        ParticleShader.TMP_VECTOR3 = new Vector3();
        ParticleShader.optionalAttributes = IntAttribute.CullFace | DepthTestAttribute.Type;
    }

    public ParticleShader(Renderable renderable0) {
        this(renderable0, new Config());
    }

    public ParticleShader(Renderable renderable0, Config particleShader$Config0) {
        this(renderable0, particleShader$Config0, ParticleShader.createPrefix(renderable0, particleShader$Config0));
    }

    public ParticleShader(Renderable renderable0, Config particleShader$Config0, ShaderProgram shaderProgram0) {
        this.config = particleShader$Config0;
        this.program = shaderProgram0;
        this.renderable = renderable0;
        this.materialMask = renderable0.material.getMask() | ParticleShader.optionalAttributes;
        this.vertexMask = renderable0.meshPart.mesh.getVertexAttributes().getMask();
        if(!particleShader$Config0.ignoreUnimplemented && (ParticleShader.implementedFlags & this.materialMask) != this.materialMask) {
            throw new GdxRuntimeException("Some attributes not implemented yet (" + this.materialMask + ")");
        }
        this.register(com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Inputs.viewTrans, com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.viewTrans);
        this.register(com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Inputs.projViewTrans, com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.projViewTrans);
        this.register(com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Inputs.projTrans, com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.projTrans);
        this.register(Inputs.screenWidth, Setters.screenWidth);
        this.register(com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Inputs.cameraUp, Setters.cameraUp);
        this.register(Inputs.cameraRight, Setters.cameraRight);
        this.register(Inputs.cameraInvDirection, Setters.cameraInvDirection);
        this.register(com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Inputs.cameraPosition, Setters.cameraPosition);
        this.register(com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Inputs.diffuseTexture, com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.diffuseTexture);
    }

    public ParticleShader(Renderable renderable0, Config particleShader$Config0, String s) {
        this(renderable0, particleShader$Config0, s, (particleShader$Config0.vertexShader == null ? ParticleShader.getDefaultVertexShader() : particleShader$Config0.vertexShader), (particleShader$Config0.fragmentShader == null ? ParticleShader.getDefaultFragmentShader() : particleShader$Config0.fragmentShader));
    }

    public ParticleShader(Renderable renderable0, Config particleShader$Config0, String s, String s1, String s2) {
        this(renderable0, particleShader$Config0, new ShaderProgram(s + s1, s + s2));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void begin(Camera camera0, RenderContext renderContext0) {
        super.begin(camera0, renderContext0);
    }

    protected void bindMaterial(Renderable renderable0) {
        if(this.currentMaterial == renderable0.material) {
            return;
        }
        int v = this.config.defaultCullFace == -1 ? 0x405 : this.config.defaultCullFace;
        int v1 = this.config.defaultDepthFunc == -1 ? 0x203 : this.config.defaultDepthFunc;
        float f = 0.0f;
        float f1 = 1.0f;
        this.currentMaterial = renderable0.material;
        boolean z = true;
        for(Object object0: this.currentMaterial) {
            Attribute attribute0 = (Attribute)object0;
            long v2 = attribute0.type;
            if(BlendingAttribute.is(v2)) {
                this.context.setBlending(true, ((BlendingAttribute)attribute0).sourceFunction, ((BlendingAttribute)attribute0).destFunction);
            }
            else if((v2 & DepthTestAttribute.Type) == DepthTestAttribute.Type) {
                v1 = ((DepthTestAttribute)attribute0).depthFunc;
                f = ((DepthTestAttribute)attribute0).depthRangeNear;
                f1 = ((DepthTestAttribute)attribute0).depthRangeFar;
                z = ((DepthTestAttribute)attribute0).depthMask;
            }
            else {
                if(!this.config.ignoreUnimplemented) {
                    throw new GdxRuntimeException("Unknown material attribute: " + attribute0.toString());
                }
                if(false) {
                    break;
                }
            }
        }
        this.context.setCullFace(v);
        this.context.setDepthTest(v1, f, f1);
        this.context.setDepthMask(z);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public boolean canRender(Renderable renderable0) {
        return this.materialMask == (renderable0.material.getMask() | ParticleShader.optionalAttributes) && this.vertexMask == renderable0.meshPart.mesh.getVertexAttributes().getMask();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public int compareTo(Shader shader0) {
        return shader0 == null ? -1 : 0;
    }

    public static String createPrefix(Renderable renderable0, Config particleShader$Config0) {
        String s = Gdx.app.getType() == ApplicationType.Desktop ? "#version 120\n" : "#version 100\n";
        if(particleShader$Config0.type == ParticleType.Billboard) {
            s = s + "#define billboard\n";
            if(particleShader$Config0.align == AlignMode.Screen) {
                return s + "#define screenFacing\n";
            }
            return particleShader$Config0.align == AlignMode.ViewPoint ? s + "#define viewPointFacing\n" : s;
        }
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void dispose() {
        this.program.dispose();
        super.dispose();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void end() {
        this.currentMaterial = null;
        super.end();
    }

    public boolean equals(ParticleShader particleShader0) {
        return particleShader0 == this;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ParticleShader && this.equals(((ParticleShader)object0));
    }

    public int getDefaultCullFace() {
        return this.config.defaultCullFace == -1 ? 0x405 : this.config.defaultCullFace;
    }

    public int getDefaultDepthFunc() {
        return this.config.defaultDepthFunc == -1 ? 0x203 : this.config.defaultDepthFunc;
    }

    public static String getDefaultFragmentShader() {
        if(ParticleShader.defaultFragmentShader == null) {
            ParticleShader.defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/particles/particles.fragment.glsl").readString();
        }
        return ParticleShader.defaultFragmentShader;
    }

    public static String getDefaultVertexShader() {
        if(ParticleShader.defaultVertexShader == null) {
            ParticleShader.defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/particles/particles.vertex.glsl").readString();
        }
        return ParticleShader.defaultVertexShader;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public void init() {
        ShaderProgram shaderProgram0 = this.program;
        this.program = null;
        this.init(shaderProgram0, this.renderable);
        this.renderable = null;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void render(Renderable renderable0) {
        if(!renderable0.material.has(BlendingAttribute.Type)) {
            this.context.setBlending(false, 770, 0x303);
        }
        this.bindMaterial(renderable0);
        super.render(renderable0);
    }

    public void setDefaultCullFace(int v) {
        this.config.defaultCullFace = v;
    }

    public void setDefaultDepthFunc(int v) {
        this.config.defaultDepthFunc = v;
    }
}

