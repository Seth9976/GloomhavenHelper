package com.badlogic.gdx.graphics.g3d.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.CubemapAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DirectionalLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.SpotLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.AmbientCubemap;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DefaultShader extends BaseShader {
    public static class Config {
        public int defaultCullFace;
        public int defaultDepthFunc;
        public String fragmentShader;
        public boolean ignoreUnimplemented;
        public int numBones;
        public int numDirectionalLights;
        public int numPointLights;
        public int numSpotLights;
        public String vertexShader;

        public Config() {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.numDirectionalLights = 2;
            this.numPointLights = 5;
            this.numSpotLights = 0;
            this.numBones = 12;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
        }

        public Config(String s, String s1) {
            this.numDirectionalLights = 2;
            this.numPointLights = 5;
            this.numSpotLights = 0;
            this.numBones = 12;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.vertexShader = s;
            this.fragmentShader = s1;
        }
    }

    public static class Inputs {
        public static final Uniform alphaTest;
        public static final Uniform ambientCube;
        public static final Uniform ambientTexture;
        public static final Uniform ambientUVTransform;
        public static final Uniform bones;
        public static final Uniform cameraDirection;
        public static final Uniform cameraNearFar;
        public static final Uniform cameraPosition;
        public static final Uniform cameraUp;
        public static final Uniform diffuseColor;
        public static final Uniform diffuseTexture;
        public static final Uniform diffuseUVTransform;
        public static final Uniform dirLights;
        public static final Uniform emissiveColor;
        public static final Uniform emissiveTexture;
        public static final Uniform emissiveUVTransform;
        public static final Uniform environmentCubemap;
        public static final Uniform normalMatrix;
        public static final Uniform normalTexture;
        public static final Uniform normalUVTransform;
        public static final Uniform opacity;
        public static final Uniform pointLights;
        public static final Uniform projTrans;
        public static final Uniform projViewTrans;
        public static final Uniform projViewWorldTrans;
        public static final Uniform reflectionColor;
        public static final Uniform reflectionTexture;
        public static final Uniform reflectionUVTransform;
        public static final Uniform shininess;
        public static final Uniform specularColor;
        public static final Uniform specularTexture;
        public static final Uniform specularUVTransform;
        public static final Uniform spotLights;
        public static final Uniform viewTrans;
        public static final Uniform viewWorldTrans;
        public static final Uniform worldTrans;

        static {
            Inputs.projTrans = new Uniform("u_projTrans");
            Inputs.viewTrans = new Uniform("u_viewTrans");
            Inputs.projViewTrans = new Uniform("u_projViewTrans");
            Inputs.cameraPosition = new Uniform("u_cameraPosition");
            Inputs.cameraDirection = new Uniform("u_cameraDirection");
            Inputs.cameraUp = new Uniform("u_cameraUp");
            Inputs.cameraNearFar = new Uniform("u_cameraNearFar");
            Inputs.worldTrans = new Uniform("u_worldTrans");
            Inputs.viewWorldTrans = new Uniform("u_viewWorldTrans");
            Inputs.projViewWorldTrans = new Uniform("u_projViewWorldTrans");
            Inputs.normalMatrix = new Uniform("u_normalMatrix");
            Inputs.bones = new Uniform("u_bones");
            Inputs.shininess = new Uniform("u_shininess", FloatAttribute.Shininess);
            Inputs.opacity = new Uniform("u_opacity", BlendingAttribute.Type);
            Inputs.diffuseColor = new Uniform("u_diffuseColor", ColorAttribute.Diffuse);
            Inputs.diffuseTexture = new Uniform("u_diffuseTexture", TextureAttribute.Diffuse);
            Inputs.diffuseUVTransform = new Uniform("u_diffuseUVTransform", TextureAttribute.Diffuse);
            Inputs.specularColor = new Uniform("u_specularColor", ColorAttribute.Specular);
            Inputs.specularTexture = new Uniform("u_specularTexture", TextureAttribute.Specular);
            Inputs.specularUVTransform = new Uniform("u_specularUVTransform", TextureAttribute.Specular);
            Inputs.emissiveColor = new Uniform("u_emissiveColor", ColorAttribute.Emissive);
            Inputs.emissiveTexture = new Uniform("u_emissiveTexture", TextureAttribute.Emissive);
            Inputs.emissiveUVTransform = new Uniform("u_emissiveUVTransform", TextureAttribute.Emissive);
            Inputs.reflectionColor = new Uniform("u_reflectionColor", ColorAttribute.Reflection);
            Inputs.reflectionTexture = new Uniform("u_reflectionTexture", TextureAttribute.Reflection);
            Inputs.reflectionUVTransform = new Uniform("u_reflectionUVTransform", TextureAttribute.Reflection);
            Inputs.normalTexture = new Uniform("u_normalTexture", TextureAttribute.Normal);
            Inputs.normalUVTransform = new Uniform("u_normalUVTransform", TextureAttribute.Normal);
            Inputs.ambientTexture = new Uniform("u_ambientTexture", TextureAttribute.Ambient);
            Inputs.ambientUVTransform = new Uniform("u_ambientUVTransform", TextureAttribute.Ambient);
            Inputs.alphaTest = new Uniform("u_alphaTest");
            Inputs.ambientCube = new Uniform("u_ambientCubemap");
            Inputs.dirLights = new Uniform("u_dirLights");
            Inputs.pointLights = new Uniform("u_pointLights");
            Inputs.spotLights = new Uniform("u_spotLights");
            Inputs.environmentCubemap = new Uniform("u_environmentCubemap");
        }
    }

    public static class Setters {
        public static class ACubemap extends LocalSetter {
            private final AmbientCubemap cacheAmbientCubemap;
            public final int dirLightsOffset;
            private static final float[] ones;
            public final int pointLightsOffset;
            private static final Vector3 tmpV1;

            static {
                ACubemap.ones = new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
                ACubemap.tmpV1 = new Vector3();
            }

            public ACubemap(int v, int v1) {
                this.cacheAmbientCubemap = new AmbientCubemap();
                this.dirLightsOffset = v;
                this.pointLightsOffset = v1;
            }

            @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
            public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                if(renderable0.environment == null) {
                    baseShader0.program.setUniform3fv(baseShader0.loc(v), ACubemap.ones, 0, ACubemap.ones.length);
                    return;
                }
                renderable0.worldTransform.getTranslation(ACubemap.tmpV1);
                if(attributes0.has(ColorAttribute.AmbientLight)) {
                    Color color0 = ((ColorAttribute)attributes0.get(ColorAttribute.AmbientLight)).color;
                    this.cacheAmbientCubemap.set(color0);
                }
                if(attributes0.has(DirectionalLightsAttribute.Type)) {
                    Array array0 = ((DirectionalLightsAttribute)attributes0.get(DirectionalLightsAttribute.Type)).lights;
                    for(int v1 = this.dirLightsOffset; v1 < array0.size; ++v1) {
                        Color color1 = ((DirectionalLight)array0.get(v1)).color;
                        Vector3 vector30 = ((DirectionalLight)array0.get(v1)).direction;
                        this.cacheAmbientCubemap.add(color1, vector30);
                    }
                }
                if(attributes0.has(PointLightsAttribute.Type)) {
                    Array array1 = ((PointLightsAttribute)attributes0.get(PointLightsAttribute.Type)).lights;
                    for(int v2 = this.pointLightsOffset; v2 < array1.size; ++v2) {
                        Color color2 = ((PointLight)array1.get(v2)).color;
                        Vector3 vector31 = ((PointLight)array1.get(v2)).position;
                        float f = ((PointLight)array1.get(v2)).intensity;
                        this.cacheAmbientCubemap.add(color2, vector31, ACubemap.tmpV1, f);
                    }
                }
                this.cacheAmbientCubemap.clamp();
                baseShader0.program.setUniform3fv(baseShader0.loc(v), this.cacheAmbientCubemap.data, 0, this.cacheAmbientCubemap.data.length);
            }
        }

        public static class Bones extends LocalSetter {
            public final float[] bones;
            private static final Matrix4 idtMatrix;

            static {
                Bones.idtMatrix = new Matrix4();
            }

            public Bones(int v) {
                this.bones = new float[v * 16];
            }

            @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
            public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                for(int v1 = 0; v1 < this.bones.length; v1 += 16) {
                    if(renderable0.bones == null || v1 / 16 >= renderable0.bones.length || renderable0.bones[v1 / 16] == null) {
                        System.arraycopy(Bones.idtMatrix.val, 0, this.bones, v1, 16);
                    }
                    else {
                        System.arraycopy(renderable0.bones[v1 / 16].val, 0, this.bones, v1, 16);
                    }
                }
                baseShader0.program.setUniformMatrix4fv(baseShader0.loc(v), this.bones, 0, this.bones.length);
            }
        }

        public static final Setter ambientTexture;
        public static final Setter ambientUVTransform;
        public static final Setter cameraDirection;
        public static final Setter cameraNearFar;
        public static final Setter cameraPosition;
        public static final Setter cameraUp;
        public static final Setter diffuseColor;
        public static final Setter diffuseTexture;
        public static final Setter diffuseUVTransform;
        public static final Setter emissiveColor;
        public static final Setter emissiveTexture;
        public static final Setter emissiveUVTransform;
        public static final Setter environmentCubemap;
        public static final Setter normalMatrix;
        public static final Setter normalTexture;
        public static final Setter normalUVTransform;
        public static final Setter projTrans;
        public static final Setter projViewTrans;
        public static final Setter projViewWorldTrans;
        public static final Setter reflectionColor;
        public static final Setter reflectionTexture;
        public static final Setter reflectionUVTransform;
        public static final Setter shininess;
        public static final Setter specularColor;
        public static final Setter specularTexture;
        public static final Setter specularUVTransform;
        public static final Setter viewTrans;
        public static final Setter viewWorldTrans;
        public static final Setter worldTrans;

        static {
            Setters.projTrans = new GlobalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.projection);
                }
            };
            Setters.viewTrans = new GlobalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.view);
                }
            };
            Setters.projViewTrans = new GlobalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.combined);
                }
            };
            Setters.cameraPosition = new GlobalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.position.x, baseShader0.camera.position.y, baseShader0.camera.position.z, 1.1881f / (baseShader0.camera.far * baseShader0.camera.far));
                }
            };
            Setters.cameraDirection = new GlobalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.direction);
                }
            };
            Setters.cameraUp = new GlobalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.up);
                }
            };
            Setters.cameraNearFar = new GlobalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.camera.near, baseShader0.camera.far);
                }
            };
            Setters.worldTrans = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, renderable0.worldTransform);
                }
            };
            Setters.viewWorldTrans = new LocalSetter() {
                final Matrix4 temp;

                {
                    this.temp = new Matrix4();
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, this.temp.set(baseShader0.camera.view).mul(renderable0.worldTransform));
                }
            };
            Setters.projViewWorldTrans = new LocalSetter() {
                final Matrix4 temp;

                {
                    this.temp = new Matrix4();
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, this.temp.set(baseShader0.camera.combined).mul(renderable0.worldTransform));
                }
            };
            Setters.normalMatrix = new LocalSetter() {
                private final Matrix3 tmpM;

                {
                    this.tmpM = new Matrix3();
                }

                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, this.tmpM.set(renderable0.worldTransform).inv().transpose());
                }
            };
            Setters.shininess = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ((FloatAttribute)attributes0.get(FloatAttribute.Shininess)).value);
                }
            };
            Setters.diffuseColor = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ((ColorAttribute)attributes0.get(ColorAttribute.Diffuse)).color);
                }
            };
            Setters.diffuseTexture = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.context.textureBinder.bind(((TextureAttribute)attributes0.get(TextureAttribute.Diffuse)).textureDescription));
                }
            };
            Setters.diffuseUVTransform = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    TextureAttribute textureAttribute0 = (TextureAttribute)attributes0.get(TextureAttribute.Diffuse);
                    baseShader0.set(v, textureAttribute0.offsetU, textureAttribute0.offsetV, textureAttribute0.scaleU, textureAttribute0.scaleV);
                }
            };
            Setters.specularColor = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ((ColorAttribute)attributes0.get(ColorAttribute.Specular)).color);
                }
            };
            Setters.specularTexture = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.context.textureBinder.bind(((TextureAttribute)attributes0.get(TextureAttribute.Specular)).textureDescription));
                }
            };
            Setters.specularUVTransform = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    TextureAttribute textureAttribute0 = (TextureAttribute)attributes0.get(TextureAttribute.Specular);
                    baseShader0.set(v, textureAttribute0.offsetU, textureAttribute0.offsetV, textureAttribute0.scaleU, textureAttribute0.scaleV);
                }
            };
            Setters.emissiveColor = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ((ColorAttribute)attributes0.get(ColorAttribute.Emissive)).color);
                }
            };
            Setters.emissiveTexture = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.context.textureBinder.bind(((TextureAttribute)attributes0.get(TextureAttribute.Emissive)).textureDescription));
                }
            };
            Setters.emissiveUVTransform = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    TextureAttribute textureAttribute0 = (TextureAttribute)attributes0.get(TextureAttribute.Emissive);
                    baseShader0.set(v, textureAttribute0.offsetU, textureAttribute0.offsetV, textureAttribute0.scaleU, textureAttribute0.scaleV);
                }
            };
            Setters.reflectionColor = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, ((ColorAttribute)attributes0.get(ColorAttribute.Reflection)).color);
                }
            };
            Setters.reflectionTexture = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.context.textureBinder.bind(((TextureAttribute)attributes0.get(TextureAttribute.Reflection)).textureDescription));
                }
            };
            Setters.reflectionUVTransform = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    TextureAttribute textureAttribute0 = (TextureAttribute)attributes0.get(TextureAttribute.Reflection);
                    baseShader0.set(v, textureAttribute0.offsetU, textureAttribute0.offsetV, textureAttribute0.scaleU, textureAttribute0.scaleV);
                }
            };
            Setters.normalTexture = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.context.textureBinder.bind(((TextureAttribute)attributes0.get(TextureAttribute.Normal)).textureDescription));
                }
            };
            Setters.normalUVTransform = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    TextureAttribute textureAttribute0 = (TextureAttribute)attributes0.get(TextureAttribute.Normal);
                    baseShader0.set(v, textureAttribute0.offsetU, textureAttribute0.offsetV, textureAttribute0.scaleU, textureAttribute0.scaleV);
                }
            };
            Setters.ambientTexture = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    baseShader0.set(v, baseShader0.context.textureBinder.bind(((TextureAttribute)attributes0.get(TextureAttribute.Ambient)).textureDescription));
                }
            };
            Setters.ambientUVTransform = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    TextureAttribute textureAttribute0 = (TextureAttribute)attributes0.get(TextureAttribute.Ambient);
                    baseShader0.set(v, textureAttribute0.offsetU, textureAttribute0.offsetV, textureAttribute0.scaleU, textureAttribute0.scaleV);
                }
            };
            Setters.environmentCubemap = new LocalSetter() {
                @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader$Setter
                public void set(BaseShader baseShader0, int v, Renderable renderable0, Attributes attributes0) {
                    if(attributes0.has(CubemapAttribute.EnvironmentMap)) {
                        baseShader0.set(v, baseShader0.context.textureBinder.bind(((CubemapAttribute)attributes0.get(CubemapAttribute.EnvironmentMap)).textureDescription));
                    }
                }
            };
        }
    }

    protected final AmbientCubemap ambientCubemap;
    protected final long attributesMask;
    protected final Config config;
    @Deprecated
    public static int defaultCullFace;
    @Deprecated
    public static int defaultDepthFunc;
    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    protected int dirLightsColorOffset;
    protected int dirLightsDirectionOffset;
    protected int dirLightsLoc;
    protected int dirLightsSize;
    protected final DirectionalLight[] directionalLights;
    protected final boolean environmentCubemap;
    protected static long implementedFlags;
    protected final boolean lighting;
    private boolean lightsSet;
    private final Matrix3 normalMatrix;
    private static final long optionalAttributes;
    protected final PointLight[] pointLights;
    protected int pointLightsColorOffset;
    protected int pointLightsIntensityOffset;
    protected int pointLightsLoc;
    protected int pointLightsPositionOffset;
    protected int pointLightsSize;
    private Renderable renderable;
    protected final boolean shadowMap;
    protected final SpotLight[] spotLights;
    protected int spotLightsColorOffset;
    protected int spotLightsCutoffAngleOffset;
    protected int spotLightsDirectionOffset;
    protected int spotLightsExponentOffset;
    protected int spotLightsIntensityOffset;
    protected int spotLightsLoc;
    protected int spotLightsPositionOffset;
    protected int spotLightsSize;
    private float time;
    private static final Attributes tmpAttributes;
    private final Vector3 tmpV1;
    public final int u_alphaTest;
    protected final int u_ambientCubemap;
    public final int u_ambientTexture;
    public final int u_ambientUVTransform;
    public final int u_bones;
    public final int u_cameraDirection;
    public final int u_cameraNearFar;
    public final int u_cameraPosition;
    public final int u_cameraUp;
    public final int u_diffuseColor;
    public final int u_diffuseTexture;
    public final int u_diffuseUVTransform;
    protected final int u_dirLights0color;
    protected final int u_dirLights0direction;
    protected final int u_dirLights1color;
    public final int u_emissiveColor;
    public final int u_emissiveTexture;
    public final int u_emissiveUVTransform;
    protected final int u_environmentCubemap;
    protected final int u_fogColor;
    public final int u_normalMatrix;
    public final int u_normalTexture;
    public final int u_normalUVTransform;
    public final int u_opacity;
    protected final int u_pointLights0color;
    protected final int u_pointLights0intensity;
    protected final int u_pointLights0position;
    protected final int u_pointLights1color;
    public final int u_projTrans;
    public final int u_projViewTrans;
    public final int u_projViewWorldTrans;
    public final int u_reflectionColor;
    public final int u_reflectionTexture;
    public final int u_reflectionUVTransform;
    protected final int u_shadowMapProjViewTrans;
    protected final int u_shadowPCFOffset;
    protected final int u_shadowTexture;
    public final int u_shininess;
    public final int u_specularColor;
    public final int u_specularTexture;
    public final int u_specularUVTransform;
    protected final int u_spotLights0color;
    protected final int u_spotLights0cutoffAngle;
    protected final int u_spotLights0direction;
    protected final int u_spotLights0exponent;
    protected final int u_spotLights0intensity;
    protected final int u_spotLights0position;
    protected final int u_spotLights1color;
    public final int u_time;
    public final int u_viewTrans;
    public final int u_viewWorldTrans;
    public final int u_worldTrans;
    private final long vertexMask;

    static {
        DefaultShader.implementedFlags = BlendingAttribute.Type | TextureAttribute.Diffuse | ColorAttribute.Diffuse | ColorAttribute.Specular | FloatAttribute.Shininess;
        DefaultShader.defaultCullFace = 0x405;
        DefaultShader.defaultDepthFunc = 0x203;
        DefaultShader.optionalAttributes = IntAttribute.CullFace | DepthTestAttribute.Type;
        DefaultShader.tmpAttributes = new Attributes();
    }

    public DefaultShader(Renderable renderable0) {
        this(renderable0, new Config());
    }

    public DefaultShader(Renderable renderable0, Config defaultShader$Config0) {
        this(renderable0, defaultShader$Config0, DefaultShader.createPrefix(renderable0, defaultShader$Config0));
    }

    public DefaultShader(Renderable renderable0, Config defaultShader$Config0, ShaderProgram shaderProgram0) {
        int v5;
        int v4;
        this.u_dirLights0color = this.register(new Uniform("u_dirLights[0].color"));
        this.u_dirLights0direction = this.register(new Uniform("u_dirLights[0].direction"));
        this.u_dirLights1color = this.register(new Uniform("u_dirLights[1].color"));
        this.u_pointLights0color = this.register(new Uniform("u_pointLights[0].color"));
        this.u_pointLights0position = this.register(new Uniform("u_pointLights[0].position"));
        this.u_pointLights0intensity = this.register(new Uniform("u_pointLights[0].intensity"));
        this.u_pointLights1color = this.register(new Uniform("u_pointLights[1].color"));
        this.u_spotLights0color = this.register(new Uniform("u_spotLights[0].color"));
        this.u_spotLights0position = this.register(new Uniform("u_spotLights[0].position"));
        this.u_spotLights0intensity = this.register(new Uniform("u_spotLights[0].intensity"));
        this.u_spotLights0direction = this.register(new Uniform("u_spotLights[0].direction"));
        this.u_spotLights0cutoffAngle = this.register(new Uniform("u_spotLights[0].cutoffAngle"));
        this.u_spotLights0exponent = this.register(new Uniform("u_spotLights[0].exponent"));
        this.u_spotLights1color = this.register(new Uniform("u_spotLights[1].color"));
        this.u_fogColor = this.register(new Uniform("u_fogColor"));
        this.u_shadowMapProjViewTrans = this.register(new Uniform("u_shadowMapProjViewTrans"));
        this.u_shadowTexture = this.register(new Uniform("u_shadowTexture"));
        this.u_shadowPCFOffset = this.register(new Uniform("u_shadowPCFOffset"));
        this.ambientCubemap = new AmbientCubemap();
        this.normalMatrix = new Matrix3();
        this.tmpV1 = new Vector3();
        Attributes attributes0 = DefaultShader.combineAttributes(renderable0);
        this.config = defaultShader$Config0;
        this.program = shaderProgram0;
        boolean z = true;
        this.lighting = renderable0.environment != null;
        this.environmentCubemap = attributes0.has(CubemapAttribute.EnvironmentMap) || this.lighting && attributes0.has(CubemapAttribute.EnvironmentMap);
        if(!this.lighting || renderable0.environment.shadowMap == null) {
            z = false;
        }
        this.shadowMap = z;
        this.renderable = renderable0;
        this.attributesMask = attributes0.getMask() | DefaultShader.optionalAttributes;
        this.vertexMask = renderable0.meshPart.mesh.getVertexAttributes().getMaskWithSizePacked();
        this.directionalLights = new DirectionalLight[(!this.lighting || defaultShader$Config0.numDirectionalLights <= 0 ? 0 : defaultShader$Config0.numDirectionalLights)];
        for(int v1 = 0; true; ++v1) {
            DirectionalLight[] arr_directionalLight = this.directionalLights;
            if(v1 >= arr_directionalLight.length) {
                break;
            }
            arr_directionalLight[v1] = new DirectionalLight();
        }
        this.pointLights = new PointLight[(!this.lighting || defaultShader$Config0.numPointLights <= 0 ? 0 : defaultShader$Config0.numPointLights)];
        for(int v2 = 0; true; ++v2) {
            PointLight[] arr_pointLight = this.pointLights;
            if(v2 >= arr_pointLight.length) {
                break;
            }
            arr_pointLight[v2] = new PointLight();
        }
        this.spotLights = new SpotLight[(!this.lighting || defaultShader$Config0.numSpotLights <= 0 ? 0 : defaultShader$Config0.numSpotLights)];
        for(int v = 0; true; ++v) {
            SpotLight[] arr_spotLight = this.spotLights;
            if(v >= arr_spotLight.length) {
                break;
            }
            arr_spotLight[v] = new SpotLight();
        }
        if(!defaultShader$Config0.ignoreUnimplemented && (DefaultShader.implementedFlags & this.attributesMask) != this.attributesMask) {
            throw new GdxRuntimeException("Some attributes not implemented yet (" + this.attributesMask + ")");
        }
        if(renderable0.bones != null && renderable0.bones.length > defaultShader$Config0.numBones) {
            throw new GdxRuntimeException("too many bones: " + renderable0.bones.length + ", max configured: " + defaultShader$Config0.numBones);
        }
        this.u_projTrans = this.register(Inputs.projTrans, Setters.projTrans);
        this.u_viewTrans = this.register(Inputs.viewTrans, Setters.viewTrans);
        this.u_projViewTrans = this.register(Inputs.projViewTrans, Setters.projViewTrans);
        this.u_cameraPosition = this.register(Inputs.cameraPosition, Setters.cameraPosition);
        this.u_cameraDirection = this.register(Inputs.cameraDirection, Setters.cameraDirection);
        this.u_cameraUp = this.register(Inputs.cameraUp, Setters.cameraUp);
        this.u_cameraNearFar = this.register(Inputs.cameraNearFar, Setters.cameraNearFar);
        this.u_time = this.register(new Uniform("u_time"));
        this.u_worldTrans = this.register(Inputs.worldTrans, Setters.worldTrans);
        this.u_viewWorldTrans = this.register(Inputs.viewWorldTrans, Setters.viewWorldTrans);
        this.u_projViewWorldTrans = this.register(Inputs.projViewWorldTrans, Setters.projViewWorldTrans);
        this.u_normalMatrix = this.register(Inputs.normalMatrix, Setters.normalMatrix);
        int v3 = -1;
        if(renderable0.bones == null || defaultShader$Config0.numBones <= 0) {
            v4 = -1;
        }
        else {
            Bones defaultShader$Setters$Bones0 = new Bones(defaultShader$Config0.numBones);
            v4 = this.register(Inputs.bones, defaultShader$Setters$Bones0);
        }
        this.u_bones = v4;
        this.u_shininess = this.register(Inputs.shininess, Setters.shininess);
        this.u_opacity = this.register(Inputs.opacity);
        this.u_diffuseColor = this.register(Inputs.diffuseColor, Setters.diffuseColor);
        this.u_diffuseTexture = this.register(Inputs.diffuseTexture, Setters.diffuseTexture);
        this.u_diffuseUVTransform = this.register(Inputs.diffuseUVTransform, Setters.diffuseUVTransform);
        this.u_specularColor = this.register(Inputs.specularColor, Setters.specularColor);
        this.u_specularTexture = this.register(Inputs.specularTexture, Setters.specularTexture);
        this.u_specularUVTransform = this.register(Inputs.specularUVTransform, Setters.specularUVTransform);
        this.u_emissiveColor = this.register(Inputs.emissiveColor, Setters.emissiveColor);
        this.u_emissiveTexture = this.register(Inputs.emissiveTexture, Setters.emissiveTexture);
        this.u_emissiveUVTransform = this.register(Inputs.emissiveUVTransform, Setters.emissiveUVTransform);
        this.u_reflectionColor = this.register(Inputs.reflectionColor, Setters.reflectionColor);
        this.u_reflectionTexture = this.register(Inputs.reflectionTexture, Setters.reflectionTexture);
        this.u_reflectionUVTransform = this.register(Inputs.reflectionUVTransform, Setters.reflectionUVTransform);
        this.u_normalTexture = this.register(Inputs.normalTexture, Setters.normalTexture);
        this.u_normalUVTransform = this.register(Inputs.normalUVTransform, Setters.normalUVTransform);
        this.u_ambientTexture = this.register(Inputs.ambientTexture, Setters.ambientTexture);
        this.u_ambientUVTransform = this.register(Inputs.ambientUVTransform, Setters.ambientUVTransform);
        this.u_alphaTest = this.register(Inputs.alphaTest);
        if(this.lighting) {
            ACubemap defaultShader$Setters$ACubemap0 = new ACubemap(defaultShader$Config0.numDirectionalLights, defaultShader$Config0.numPointLights);
            v5 = this.register(Inputs.ambientCube, defaultShader$Setters$ACubemap0);
        }
        else {
            v5 = -1;
        }
        this.u_ambientCubemap = v5;
        if(this.environmentCubemap) {
            v3 = this.register(Inputs.environmentCubemap, Setters.environmentCubemap);
        }
        this.u_environmentCubemap = v3;
    }

    public DefaultShader(Renderable renderable0, Config defaultShader$Config0, String s) {
        this(renderable0, defaultShader$Config0, s, (defaultShader$Config0.vertexShader == null ? DefaultShader.getDefaultVertexShader() : defaultShader$Config0.vertexShader), (defaultShader$Config0.fragmentShader == null ? DefaultShader.getDefaultFragmentShader() : defaultShader$Config0.fragmentShader));
    }

    public DefaultShader(Renderable renderable0, Config defaultShader$Config0, String s, String s1, String s2) {
        this(renderable0, defaultShader$Config0, new ShaderProgram(s + s1, s + s2));
    }

    private static final boolean and(long v, long v1) {
        return (v & v1) == v1;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void begin(Camera camera0, RenderContext renderContext0) {
        super.begin(camera0, renderContext0);
        DirectionalLight[] arr_directionalLight = this.directionalLights;
        for(int v = 0; v < arr_directionalLight.length; ++v) {
            arr_directionalLight[v].set(0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f);
        }
        PointLight[] arr_pointLight = this.pointLights;
        for(int v1 = 0; v1 < arr_pointLight.length; ++v1) {
            arr_pointLight[v1].set(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }
        SpotLight[] arr_spotLight = this.spotLights;
        for(int v2 = 0; v2 < arr_spotLight.length; ++v2) {
            arr_spotLight[v2].set(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        }
        this.lightsSet = false;
        if(this.has(this.u_time)) {
            float f = this.time + Gdx.graphics.getDeltaTime();
            this.time = f;
            this.set(this.u_time, f);
        }
    }

    protected void bindLights(Renderable renderable0, Attributes attributes0) {
        Environment environment0 = renderable0.environment;
        DirectionalLightsAttribute directionalLightsAttribute0 = (DirectionalLightsAttribute)attributes0.get(DirectionalLightsAttribute.class, DirectionalLightsAttribute.Type);
        Array array0 = null;
        Array array1 = directionalLightsAttribute0 == null ? null : directionalLightsAttribute0.lights;
        PointLightsAttribute pointLightsAttribute0 = (PointLightsAttribute)attributes0.get(PointLightsAttribute.class, PointLightsAttribute.Type);
        Array array2 = pointLightsAttribute0 == null ? null : pointLightsAttribute0.lights;
        SpotLightsAttribute spotLightsAttribute0 = (SpotLightsAttribute)attributes0.get(SpotLightsAttribute.class, SpotLightsAttribute.Type);
        if(spotLightsAttribute0 != null) {
            array0 = spotLightsAttribute0.lights;
        }
        int v = 0;
        if(this.dirLightsLoc >= 0) {
            int v1 = 0;
            while(v1 < this.directionalLights.length) {
                if(array1 == null || v1 >= array1.size) {
                    if(!this.lightsSet || this.directionalLights[v1].color.r != 0.0f || this.directionalLights[v1].color.g != 0.0f || this.directionalLights[v1].color.b != 0.0f) {
                        this.directionalLights[v1].color.set(0.0f, 0.0f, 0.0f, 1.0f);
                    label_19:
                        int v2 = this.dirLightsLoc + this.dirLightsSize * v1;
                        this.program.setUniformf(this.dirLightsColorOffset + v2, this.directionalLights[v1].color.r, this.directionalLights[v1].color.g, this.directionalLights[v1].color.b);
                        this.program.setUniformf(v2 + this.dirLightsDirectionOffset, this.directionalLights[v1].direction.x, this.directionalLights[v1].direction.y, this.directionalLights[v1].direction.z);
                        if(this.dirLightsSize > 0) {
                            goto label_23;
                        }
                        break;
                    }
                }
                else if(!this.lightsSet || !this.directionalLights[v1].equals(((DirectionalLight)array1.get(v1)))) {
                    this.directionalLights[v1].set(((DirectionalLight)array1.get(v1)));
                    goto label_19;
                }
            label_23:
                ++v1;
            }
        }
        if(this.pointLightsLoc >= 0) {
            int v3 = 0;
            while(v3 < this.pointLights.length) {
                if(array2 == null || v3 >= array2.size) {
                    if(!this.lightsSet || this.pointLights[v3].intensity != 0.0f) {
                        this.pointLights[v3].intensity = 0.0f;
                    label_34:
                        int v4 = this.pointLightsLoc + this.pointLightsSize * v3;
                        this.program.setUniformf(this.pointLightsColorOffset + v4, this.pointLights[v3].color.r * this.pointLights[v3].intensity, this.pointLights[v3].color.g * this.pointLights[v3].intensity, this.pointLights[v3].color.b * this.pointLights[v3].intensity);
                        this.program.setUniformf(this.pointLightsPositionOffset + v4, this.pointLights[v3].position.x, this.pointLights[v3].position.y, this.pointLights[v3].position.z);
                        if(this.pointLightsIntensityOffset >= 0) {
                            this.program.setUniformf(v4 + this.pointLightsIntensityOffset, this.pointLights[v3].intensity);
                        }
                        if(this.pointLightsSize > 0) {
                            goto label_40;
                        }
                        break;
                    }
                }
                else if(!this.lightsSet || !this.pointLights[v3].equals(((PointLight)array2.get(v3)))) {
                    this.pointLights[v3].set(((PointLight)array2.get(v3)));
                    goto label_34;
                }
            label_40:
                ++v3;
            }
        }
        if(this.spotLightsLoc >= 0) {
            while(v < this.spotLights.length) {
                if(array0 == null || v >= array0.size) {
                    if(!this.lightsSet || this.spotLights[v].intensity != 0.0f) {
                        this.spotLights[v].intensity = 0.0f;
                    label_50:
                        int v5 = this.spotLightsLoc + this.spotLightsSize * v;
                        this.program.setUniformf(this.spotLightsColorOffset + v5, this.spotLights[v].color.r * this.spotLights[v].intensity, this.spotLights[v].color.g * this.spotLights[v].intensity, this.spotLights[v].color.b * this.spotLights[v].intensity);
                        this.program.setUniformf(this.spotLightsPositionOffset + v5, this.spotLights[v].position);
                        this.program.setUniformf(this.spotLightsDirectionOffset + v5, this.spotLights[v].direction);
                        this.program.setUniformf(this.spotLightsCutoffAngleOffset + v5, this.spotLights[v].cutoffAngle);
                        this.program.setUniformf(this.spotLightsExponentOffset + v5, this.spotLights[v].exponent);
                        if(this.spotLightsIntensityOffset >= 0) {
                            this.program.setUniformf(v5 + this.spotLightsIntensityOffset, this.spotLights[v].intensity);
                        }
                        if(this.spotLightsSize > 0) {
                            goto label_59;
                        }
                        break;
                    }
                }
                else if(!this.lightsSet || !this.spotLights[v].equals(((SpotLight)array0.get(v)))) {
                    this.spotLights[v].set(((SpotLight)array0.get(v)));
                    goto label_50;
                }
            label_59:
                ++v;
            }
        }
        if(attributes0.has(ColorAttribute.Fog)) {
            Color color0 = ((ColorAttribute)attributes0.get(ColorAttribute.Fog)).color;
            this.set(this.u_fogColor, color0);
        }
        if(environment0 != null && environment0.shadowMap != null) {
            Matrix4 matrix40 = environment0.shadowMap.getProjViewTrans();
            this.set(this.u_shadowMapProjViewTrans, matrix40);
            TextureDescriptor textureDescriptor0 = environment0.shadowMap.getDepthMap();
            this.set(this.u_shadowTexture, textureDescriptor0);
            float f = 1.0f / (((float)environment0.shadowMap.getDepthMap().texture.getWidth()) * 2.0f);
            this.set(this.u_shadowPCFOffset, f);
        }
        this.lightsSet = true;
    }

    protected void bindMaterial(Attributes attributes0) {
        int v = this.config.defaultCullFace == -1 ? DefaultShader.defaultCullFace : this.config.defaultCullFace;
        int v1 = this.config.defaultDepthFunc == -1 ? DefaultShader.defaultDepthFunc : this.config.defaultDepthFunc;
        float f = 0.0f;
        float f1 = 1.0f;
        boolean z = true;
        for(Object object0: attributes0) {
            Attribute attribute0 = (Attribute)object0;
            long v2 = attribute0.type;
            if(BlendingAttribute.is(v2)) {
                this.context.setBlending(true, ((BlendingAttribute)attribute0).sourceFunction, ((BlendingAttribute)attribute0).destFunction);
                this.set(this.u_opacity, ((BlendingAttribute)attribute0).opacity);
            }
            else if((IntAttribute.CullFace & v2) == IntAttribute.CullFace) {
                v = ((IntAttribute)attribute0).value;
            }
            else if((FloatAttribute.AlphaTest & v2) == FloatAttribute.AlphaTest) {
                this.set(this.u_alphaTest, ((FloatAttribute)attribute0).value);
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
        if(renderable0.bones != null && renderable0.bones.length > this.config.numBones) {
            return false;
        }
        long v = DefaultShader.combineAttributeMasks(renderable0);
        if(this.attributesMask == (v | DefaultShader.optionalAttributes)) {
            long v1 = renderable0.meshPart.mesh.getVertexAttributes().getMaskWithSizePacked();
            return this.vertexMask == v1 ? renderable0.environment != null == this.lighting : false;
        }
        return false;
    }

    private static final long combineAttributeMasks(Renderable renderable0) {
        long v = renderable0.environment == null ? 0L : renderable0.environment.getMask();
        return renderable0.material == null ? v : v | renderable0.material.getMask();
    }

    private static final Attributes combineAttributes(Renderable renderable0) {
        DefaultShader.tmpAttributes.clear();
        if(renderable0.environment != null) {
            DefaultShader.tmpAttributes.set(renderable0.environment);
        }
        if(renderable0.material != null) {
            DefaultShader.tmpAttributes.set(renderable0.material);
        }
        return DefaultShader.tmpAttributes;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public int compareTo(Shader shader0) {
        return shader0 == null ? -1 : 0;
    }

    public static String createPrefix(Renderable renderable0, Config defaultShader$Config0) {
        Attributes attributes0 = DefaultShader.combineAttributes(renderable0);
        String s = "";
        long v = attributes0.getMask();
        long v1 = renderable0.meshPart.mesh.getVertexAttributes().getMask();
        if(DefaultShader.and(v1, 1L)) {
            s = "#define positionFlag\n";
        }
        if(DefaultShader.or(v1, 6L)) {
            s = s + "#define colorFlag\n";
        }
        if(DefaultShader.and(v1, 0x100L)) {
            s = s + "#define binormalFlag\n";
        }
        if(DefaultShader.and(v1, 0x80L)) {
            s = s + "#define tangentFlag\n";
        }
        if(DefaultShader.and(v1, 8L)) {
            s = s + "#define normalFlag\n";
        }
        if((DefaultShader.and(v1, 8L) || DefaultShader.and(v1, 0x180L)) && renderable0.environment != null) {
            s = s + "#define lightingFlag\n" + "#define ambientCubemapFlag\n" + "#define numDirectionalLights " + defaultShader$Config0.numDirectionalLights + "\n" + "#define numPointLights " + defaultShader$Config0.numPointLights + "\n" + "#define numSpotLights " + defaultShader$Config0.numSpotLights + "\n";
            s = attributes0.has(ColorAttribute.Fog) ? s + "#define fogFlag\n" : s + "#define lightingFlag\n" + "#define ambientCubemapFlag\n" + "#define numDirectionalLights " + defaultShader$Config0.numDirectionalLights + "\n" + "#define numPointLights " + defaultShader$Config0.numPointLights + "\n" + "#define numSpotLights " + defaultShader$Config0.numSpotLights + "\n";
            if(renderable0.environment.shadowMap != null) {
                s = s + "#define shadowMapFlag\n";
            }
            if(attributes0.has(CubemapAttribute.EnvironmentMap)) {
                s = s + "#define environmentCubemapFlag\n";
            }
        }
        int v2 = renderable0.meshPart.mesh.getVertexAttributes().size();
        for(int v3 = 0; v3 < v2; ++v3) {
            VertexAttribute vertexAttribute0 = renderable0.meshPart.mesh.getVertexAttributes().get(v3);
            if(vertexAttribute0.usage == 0x40) {
                s = s + "#define boneWeight" + vertexAttribute0.unit + "Flag\n";
            }
            else if(vertexAttribute0.usage == 16) {
                s = s + "#define texCoord" + vertexAttribute0.unit + "Flag\n";
            }
        }
        if((BlendingAttribute.Type & v) == BlendingAttribute.Type) {
            s = s + "#define blendedFlag\n";
        }
        if((TextureAttribute.Diffuse & v) == TextureAttribute.Diffuse) {
            s = s + "#define diffuseTextureFlag\n" + "#define diffuseTextureCoord texCoord0\n";
        }
        if((TextureAttribute.Specular & v) == TextureAttribute.Specular) {
            s = s + "#define specularTextureFlag\n" + "#define specularTextureCoord texCoord0\n";
        }
        if((TextureAttribute.Normal & v) == TextureAttribute.Normal) {
            s = s + "#define normalTextureFlag\n" + "#define normalTextureCoord texCoord0\n";
        }
        if((TextureAttribute.Emissive & v) == TextureAttribute.Emissive) {
            s = s + "#define emissiveTextureFlag\n" + "#define emissiveTextureCoord texCoord0\n";
        }
        if((TextureAttribute.Reflection & v) == TextureAttribute.Reflection) {
            s = s + "#define reflectionTextureFlag\n" + "#define reflectionTextureCoord texCoord0\n";
        }
        if((TextureAttribute.Ambient & v) == TextureAttribute.Ambient) {
            s = s + "#define ambientTextureFlag\n" + "#define ambientTextureCoord texCoord0\n";
        }
        if((ColorAttribute.Diffuse & v) == ColorAttribute.Diffuse) {
            s = s + "#define diffuseColorFlag\n";
        }
        if((ColorAttribute.Specular & v) == ColorAttribute.Specular) {
            s = s + "#define specularColorFlag\n";
        }
        if((ColorAttribute.Emissive & v) == ColorAttribute.Emissive) {
            s = s + "#define emissiveColorFlag\n";
        }
        if((ColorAttribute.Reflection & v) == ColorAttribute.Reflection) {
            s = s + "#define reflectionColorFlag\n";
        }
        if((FloatAttribute.Shininess & v) == FloatAttribute.Shininess) {
            s = s + "#define shininessFlag\n";
        }
        if((v & FloatAttribute.AlphaTest) == FloatAttribute.AlphaTest) {
            s = s + "#define alphaTestFlag\n";
        }
        return renderable0.bones == null || defaultShader$Config0.numBones <= 0 ? s : s + "#define numBones " + defaultShader$Config0.numBones + "\n";
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void dispose() {
        this.program.dispose();
        super.dispose();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void end() {
        super.end();
    }

    public boolean equals(DefaultShader defaultShader0) {
        return defaultShader0 == this;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof DefaultShader && this.equals(((DefaultShader)object0));
    }

    public int getDefaultCullFace() {
        return this.config.defaultCullFace == -1 ? DefaultShader.defaultCullFace : this.config.defaultCullFace;
    }

    public int getDefaultDepthFunc() {
        return this.config.defaultDepthFunc == -1 ? DefaultShader.defaultDepthFunc : this.config.defaultDepthFunc;
    }

    public static String getDefaultFragmentShader() {
        if(DefaultShader.defaultFragmentShader == null) {
            DefaultShader.defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.fragment.glsl").readString();
        }
        return DefaultShader.defaultFragmentShader;
    }

    public static String getDefaultVertexShader() {
        if(DefaultShader.defaultVertexShader == null) {
            DefaultShader.defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.vertex.glsl").readString();
        }
        return DefaultShader.defaultVertexShader;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Shader
    public void init() {
        ShaderProgram shaderProgram0 = this.program;
        this.program = null;
        this.init(shaderProgram0, this.renderable);
        this.renderable = null;
        this.dirLightsLoc = this.loc(this.u_dirLights0color);
        this.dirLightsColorOffset = this.loc(this.u_dirLights0color) - this.dirLightsLoc;
        this.dirLightsDirectionOffset = this.loc(this.u_dirLights0direction) - this.dirLightsLoc;
        this.dirLightsSize = this.loc(this.u_dirLights1color) - this.dirLightsLoc;
        if(this.dirLightsSize < 0) {
            this.dirLightsSize = 0;
        }
        this.pointLightsLoc = this.loc(this.u_pointLights0color);
        this.pointLightsColorOffset = this.loc(this.u_pointLights0color) - this.pointLightsLoc;
        this.pointLightsPositionOffset = this.loc(this.u_pointLights0position) - this.pointLightsLoc;
        int v = -1;
        this.pointLightsIntensityOffset = this.has(this.u_pointLights0intensity) ? this.loc(this.u_pointLights0intensity) - this.pointLightsLoc : -1;
        this.pointLightsSize = this.loc(this.u_pointLights1color) - this.pointLightsLoc;
        if(this.pointLightsSize < 0) {
            this.pointLightsSize = 0;
        }
        this.spotLightsLoc = this.loc(this.u_spotLights0color);
        this.spotLightsColorOffset = this.loc(this.u_spotLights0color) - this.spotLightsLoc;
        this.spotLightsPositionOffset = this.loc(this.u_spotLights0position) - this.spotLightsLoc;
        this.spotLightsDirectionOffset = this.loc(this.u_spotLights0direction) - this.spotLightsLoc;
        if(this.has(this.u_spotLights0intensity)) {
            v = this.loc(this.u_spotLights0intensity) - this.spotLightsLoc;
        }
        this.spotLightsIntensityOffset = v;
        this.spotLightsCutoffAngleOffset = this.loc(this.u_spotLights0cutoffAngle) - this.spotLightsLoc;
        this.spotLightsExponentOffset = this.loc(this.u_spotLights0exponent) - this.spotLightsLoc;
        this.spotLightsSize = this.loc(this.u_spotLights1color) - this.spotLightsLoc;
        if(this.spotLightsSize < 0) {
            this.spotLightsSize = 0;
        }
    }

    private static final boolean or(long v, long v1) {
        return (v & v1) != 0L;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.shaders.BaseShader
    public void render(Renderable renderable0, Attributes attributes0) {
        if(!attributes0.has(BlendingAttribute.Type)) {
            this.context.setBlending(false, 770, 0x303);
        }
        this.bindMaterial(attributes0);
        if(this.lighting) {
            this.bindLights(renderable0, attributes0);
        }
        super.render(renderable0, attributes0);
    }

    public void setDefaultCullFace(int v) {
        this.config.defaultCullFace = v;
    }

    public void setDefaultDepthFunc(int v) {
        this.config.defaultDepthFunc = v;
    }
}

