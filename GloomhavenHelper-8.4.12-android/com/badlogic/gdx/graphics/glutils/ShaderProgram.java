package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class ShaderProgram implements Disposable {
    public static final String BINORMAL_ATTRIBUTE = "a_binormal";
    public static final String BONEWEIGHT_ATTRIBUTE = "a_boneWeight";
    public static final String COLOR_ATTRIBUTE = "a_color";
    public static final String NORMAL_ATTRIBUTE = "a_normal";
    public static final String POSITION_ATTRIBUTE = "a_position";
    public static final String TANGENT_ATTRIBUTE = "a_tangent";
    public static final String TEXCOORD_ATTRIBUTE = "a_texCoord";
    private String[] attributeNames;
    private final ObjectIntMap attributeSizes;
    private final ObjectIntMap attributeTypes;
    private final ObjectIntMap attributes;
    private int fragmentShaderHandle;
    private final String fragmentShaderSource;
    static final IntBuffer intbuf = null;
    private boolean invalidated;
    private boolean isCompiled;
    private String log;
    private final FloatBuffer matrix;
    IntBuffer params;
    public static boolean pedantic = true;
    public static String prependFragmentCode = "";
    public static String prependVertexCode = "";
    private int program;
    private int refCount;
    private static final ObjectMap shaders;
    IntBuffer type;
    private String[] uniformNames;
    private final ObjectIntMap uniformSizes;
    private final ObjectIntMap uniformTypes;
    private final ObjectIntMap uniforms;
    private int vertexShaderHandle;
    private final String vertexShaderSource;

    static {
        ShaderProgram.shaders = new ObjectMap();
        ShaderProgram.intbuf = BufferUtils.newIntBuffer(1);
    }

    public ShaderProgram(FileHandle fileHandle0, FileHandle fileHandle1) {
        this(fileHandle0.readString(), fileHandle1.readString());
    }

    public ShaderProgram(String s, String s1) {
        this.log = "";
        this.uniforms = new ObjectIntMap();
        this.uniformTypes = new ObjectIntMap();
        this.uniformSizes = new ObjectIntMap();
        this.attributes = new ObjectIntMap();
        this.attributeTypes = new ObjectIntMap();
        this.attributeSizes = new ObjectIntMap();
        this.refCount = 0;
        this.params = BufferUtils.newIntBuffer(1);
        this.type = BufferUtils.newIntBuffer(1);
        if(s == null) {
            throw new IllegalArgumentException("vertex shader must not be null");
        }
        if(s1 == null) {
            throw new IllegalArgumentException("fragment shader must not be null");
        }
        this.vertexShaderSource = s;
        this.fragmentShaderSource = s1;
        this.matrix = BufferUtils.newFloatBuffer(16);
        this.compileShaders(s, s1);
        if(this.isCompiled()) {
            this.fetchAttributes();
            this.fetchUniforms();
            this.addManagedShader(Gdx.app, this);
        }
    }

    private void addManagedShader(Application application0, ShaderProgram shaderProgram0) {
        Array array0 = (Array)ShaderProgram.shaders.get(application0);
        if(array0 == null) {
            array0 = new Array();
        }
        array0.add(shaderProgram0);
        ShaderProgram.shaders.put(application0, array0);
    }

    @Deprecated
    public void begin() {
        this.bind();
    }

    public void bind() {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUseProgram(this.program);
    }

    private void checkManaged() {
        if(this.invalidated) {
            this.compileShaders(this.vertexShaderSource, this.fragmentShaderSource);
            this.invalidated = false;
        }
    }

    public static void clearAllShaderPrograms(Application application0) {
        ShaderProgram.shaders.remove(application0);
    }

    private void compileShaders(String s, String s1) {
        this.vertexShaderHandle = this.loadShader(0x8B31, s);
        this.fragmentShaderHandle = this.loadShader(0x8B30, s1);
        if(this.vertexShaderHandle != -1 && this.fragmentShaderHandle != -1) {
            this.program = this.linkProgram(this.createProgram());
            if(this.program == -1) {
                this.isCompiled = false;
                return;
            }
            this.isCompiled = true;
            return;
        }
        this.isCompiled = false;
    }

    protected int createProgram() {
        int v = Gdx.gl20.glCreateProgram();
        return v == 0 ? -1 : v;
    }

    public void disableVertexAttribute(int v) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glDisableVertexAttribArray(v);
    }

    public void disableVertexAttribute(String s) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        int v = this.fetchAttributeLocation(s);
        if(v == -1) {
            return;
        }
        gL200.glDisableVertexAttribArray(v);
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        GL20 gL200 = Gdx.gl20;
        gL200.glUseProgram(0);
        gL200.glDeleteShader(this.vertexShaderHandle);
        gL200.glDeleteShader(this.fragmentShaderHandle);
        gL200.glDeleteProgram(this.program);
        if(ShaderProgram.shaders.get(Gdx.app) != null) {
            ((Array)ShaderProgram.shaders.get(Gdx.app)).removeValue(this, true);
        }
    }

    public void enableVertexAttribute(int v) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glEnableVertexAttribArray(v);
    }

    public void enableVertexAttribute(String s) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        int v = this.fetchAttributeLocation(s);
        if(v == -1) {
            return;
        }
        gL200.glEnableVertexAttribArray(v);
    }

    @Deprecated
    public void end() {
    }

    private int fetchAttributeLocation(String s) {
        GL20 gL200 = Gdx.gl20;
        int v = this.attributes.get(s, -2);
        if(v == -2) {
            v = gL200.glGetAttribLocation(this.program, s);
            this.attributes.put(s, v);
        }
        return v;
    }

    private void fetchAttributes() {
        this.params.clear();
        Gdx.gl20.glGetProgramiv(this.program, 0x8B89, this.params);
        int v = this.params.get(0);
        this.attributeNames = new String[v];
        for(int v1 = 0; v1 < v; ++v1) {
            this.params.clear();
            this.params.put(0, 1);
            this.type.clear();
            String s = Gdx.gl20.glGetActiveAttrib(this.program, v1, this.params, this.type);
            int v2 = Gdx.gl20.glGetAttribLocation(this.program, s);
            this.attributes.put(s, v2);
            int v3 = this.type.get(0);
            this.attributeTypes.put(s, v3);
            int v4 = this.params.get(0);
            this.attributeSizes.put(s, v4);
            this.attributeNames[v1] = s;
        }
    }

    private int fetchUniformLocation(String s) {
        return this.fetchUniformLocation(s, ShaderProgram.pedantic);
    }

    public int fetchUniformLocation(String s, boolean z) {
        int v = this.uniforms.get(s, -2);
        if(v == -2) {
            v = Gdx.gl20.glGetUniformLocation(this.program, s);
            if(v == -1 && z) {
                if(this.isCompiled) {
                    throw new IllegalArgumentException("No uniform with name \'" + s + "\' in shader");
                }
                throw new IllegalStateException("An attempted fetch uniform from uncompiled shader \n" + this.getLog());
            }
            this.uniforms.put(s, v);
        }
        return v;
    }

    private void fetchUniforms() {
        this.params.clear();
        Gdx.gl20.glGetProgramiv(this.program, 0x8B86, this.params);
        int v = this.params.get(0);
        this.uniformNames = new String[v];
        for(int v1 = 0; v1 < v; ++v1) {
            this.params.clear();
            this.params.put(0, 1);
            this.type.clear();
            String s = Gdx.gl20.glGetActiveUniform(this.program, v1, this.params, this.type);
            int v2 = Gdx.gl20.glGetUniformLocation(this.program, s);
            this.uniforms.put(s, v2);
            int v3 = this.type.get(0);
            this.uniformTypes.put(s, v3);
            int v4 = this.params.get(0);
            this.uniformSizes.put(s, v4);
            this.uniformNames[v1] = s;
        }
    }

    public int getAttributeLocation(String s) {
        return this.attributes.get(s, -1);
    }

    public int getAttributeSize(String s) {
        return this.attributeSizes.get(s, 0);
    }

    public int getAttributeType(String s) {
        return this.attributeTypes.get(s, 0);
    }

    public String[] getAttributes() {
        return this.attributeNames;
    }

    public String getFragmentShaderSource() {
        return this.fragmentShaderSource;
    }

    public int getHandle() {
        return this.program;
    }

    public String getLog() {
        if(this.isCompiled) {
            this.log = Gdx.gl20.glGetProgramInfoLog(this.program);
            return this.log;
        }
        return this.log;
    }

    public static String getManagedStatus() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("Managed shaders/app: { ");
        for(Object object0: ShaderProgram.shaders.keys()) {
            stringBuilder0.append(((Array)ShaderProgram.shaders.get(((Application)object0))).size);
            stringBuilder0.append(" ");
        }
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    public static int getNumManagedShaderPrograms() {
        return ((Array)ShaderProgram.shaders.get(Gdx.app)).size;
    }

    public int getUniformLocation(String s) {
        return this.uniforms.get(s, -1);
    }

    public int getUniformSize(String s) {
        return this.uniformSizes.get(s, 0);
    }

    public int getUniformType(String s) {
        return this.uniformTypes.get(s, 0);
    }

    public String[] getUniforms() {
        return this.uniformNames;
    }

    public String getVertexShaderSource() {
        return this.vertexShaderSource;
    }

    public boolean hasAttribute(String s) {
        return this.attributes.containsKey(s);
    }

    public boolean hasUniform(String s) {
        return this.uniforms.containsKey(s);
    }

    public static void invalidateAllShaderPrograms(Application application0) {
        if(Gdx.gl20 == null) {
            return;
        }
        Array array0 = (Array)ShaderProgram.shaders.get(application0);
        if(array0 == null) {
            return;
        }
        for(int v = 0; v < array0.size; ++v) {
            ((ShaderProgram)array0.get(v)).invalidated = true;
            ((ShaderProgram)array0.get(v)).checkManaged();
        }
    }

    public boolean isCompiled() {
        return this.isCompiled;
    }

    private int linkProgram(int v) {
        GL20 gL200 = Gdx.gl20;
        if(v == -1) {
            return -1;
        }
        gL200.glAttachShader(v, this.vertexShaderHandle);
        gL200.glAttachShader(v, this.fragmentShaderHandle);
        gL200.glLinkProgram(v);
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(4);
        byteBuffer0.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer0 = byteBuffer0.asIntBuffer();
        gL200.glGetProgramiv(v, 0x8B82, intBuffer0);
        if(intBuffer0.get(0) == 0) {
            this.log = Gdx.gl20.glGetProgramInfoLog(v);
            return -1;
        }
        return v;
    }

    private int loadShader(int v, String s) {
        GL20 gL200 = Gdx.gl20;
        IntBuffer intBuffer0 = BufferUtils.newIntBuffer(1);
        int v1 = gL200.glCreateShader(v);
        if(v1 == 0) {
            return -1;
        }
        gL200.glShaderSource(v1, s);
        gL200.glCompileShader(v1);
        gL200.glGetShaderiv(v1, 0x8B81, intBuffer0);
        if(intBuffer0.get(0) == 0) {
            String s1 = gL200.glGetShaderInfoLog(v1);
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append(this.log);
            stringBuilder0.append((v == 0x8B31 ? "Vertex shader\n" : "Fragment shader:\n"));
            this.log = this.log + s1;
            return -1;
        }
        return v1;
    }

    public void setAttributef(String s, float f, float f1, float f2, float f3) {
        Gdx.gl20.glVertexAttrib4f(this.fetchAttributeLocation(s), f, f1, f2, f3);
    }

    public void setUniform1fv(int v, float[] arr_f, int v1, int v2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform1fv(v, v2, arr_f, v1);
    }

    public void setUniform1fv(String s, float[] arr_f, int v, int v1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform1fv(this.fetchUniformLocation(s), v1, arr_f, v);
    }

    public void setUniform2fv(int v, float[] arr_f, int v1, int v2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform2fv(v, v2 / 2, arr_f, v1);
    }

    public void setUniform2fv(String s, float[] arr_f, int v, int v1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform2fv(this.fetchUniformLocation(s), v1 / 2, arr_f, v);
    }

    public void setUniform3fv(int v, float[] arr_f, int v1, int v2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform3fv(v, v2 / 3, arr_f, v1);
    }

    public void setUniform3fv(String s, float[] arr_f, int v, int v1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform3fv(this.fetchUniformLocation(s), v1 / 3, arr_f, v);
    }

    public void setUniform4fv(int v, float[] arr_f, int v1, int v2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform4fv(v, v2 / 4, arr_f, v1);
    }

    public void setUniform4fv(String s, float[] arr_f, int v, int v1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform4fv(this.fetchUniformLocation(s), v1 / 4, arr_f, v);
    }

    public void setUniformMatrix(int v, Matrix3 matrix30) {
        this.setUniformMatrix(v, matrix30, false);
    }

    public void setUniformMatrix(int v, Matrix3 matrix30, boolean z) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniformMatrix3fv(v, 1, z, matrix30.val, 0);
    }

    public void setUniformMatrix(int v, Matrix4 matrix40) {
        this.setUniformMatrix(v, matrix40, false);
    }

    public void setUniformMatrix(int v, Matrix4 matrix40, boolean z) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniformMatrix4fv(v, 1, z, matrix40.val, 0);
    }

    public void setUniformMatrix(String s, Matrix3 matrix30) {
        this.setUniformMatrix(s, matrix30, false);
    }

    public void setUniformMatrix(String s, Matrix3 matrix30, boolean z) {
        this.setUniformMatrix(this.fetchUniformLocation(s), matrix30, z);
    }

    public void setUniformMatrix(String s, Matrix4 matrix40) {
        this.setUniformMatrix(s, matrix40, false);
    }

    public void setUniformMatrix(String s, Matrix4 matrix40, boolean z) {
        this.setUniformMatrix(this.fetchUniformLocation(s), matrix40, z);
    }

    public void setUniformMatrix3fv(String s, FloatBuffer floatBuffer0, int v, boolean z) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        floatBuffer0.position(0);
        gL200.glUniformMatrix3fv(this.fetchUniformLocation(s), v, z, floatBuffer0);
    }

    public void setUniformMatrix4fv(int v, float[] arr_f, int v1, int v2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniformMatrix4fv(v, v2 / 16, false, arr_f, v1);
    }

    public void setUniformMatrix4fv(String s, FloatBuffer floatBuffer0, int v, boolean z) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        floatBuffer0.position(0);
        gL200.glUniformMatrix4fv(this.fetchUniformLocation(s), v, z, floatBuffer0);
    }

    public void setUniformMatrix4fv(String s, float[] arr_f, int v, int v1) {
        this.setUniformMatrix4fv(this.fetchUniformLocation(s), arr_f, v, v1);
    }

    public void setUniformf(int v, float f) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform1f(v, f);
    }

    public void setUniformf(int v, float f, float f1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform2f(v, f, f1);
    }

    public void setUniformf(int v, float f, float f1, float f2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform3f(v, f, f1, f2);
    }

    public void setUniformf(int v, float f, float f1, float f2, float f3) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform4f(v, f, f1, f2, f3);
    }

    public void setUniformf(int v, Color color0) {
        this.setUniformf(v, color0.r, color0.g, color0.b, color0.a);
    }

    public void setUniformf(int v, Vector2 vector20) {
        this.setUniformf(v, vector20.x, vector20.y);
    }

    public void setUniformf(int v, Vector3 vector30) {
        this.setUniformf(v, vector30.x, vector30.y, vector30.z);
    }

    public void setUniformf(String s, float f) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform1f(this.fetchUniformLocation(s), f);
    }

    public void setUniformf(String s, float f, float f1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform2f(this.fetchUniformLocation(s), f, f1);
    }

    public void setUniformf(String s, float f, float f1, float f2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform3f(this.fetchUniformLocation(s), f, f1, f2);
    }

    public void setUniformf(String s, float f, float f1, float f2, float f3) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform4f(this.fetchUniformLocation(s), f, f1, f2, f3);
    }

    public void setUniformf(String s, Color color0) {
        this.setUniformf(s, color0.r, color0.g, color0.b, color0.a);
    }

    public void setUniformf(String s, Vector2 vector20) {
        this.setUniformf(s, vector20.x, vector20.y);
    }

    public void setUniformf(String s, Vector3 vector30) {
        this.setUniformf(s, vector30.x, vector30.y, vector30.z);
    }

    public void setUniformi(int v, int v1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform1i(v, v1);
    }

    public void setUniformi(int v, int v1, int v2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform2i(v, v1, v2);
    }

    public void setUniformi(int v, int v1, int v2, int v3) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform3i(v, v1, v2, v3);
    }

    public void setUniformi(int v, int v1, int v2, int v3, int v4) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform4i(v, v1, v2, v3, v4);
    }

    public void setUniformi(String s, int v) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform1i(this.fetchUniformLocation(s), v);
    }

    public void setUniformi(String s, int v, int v1) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform2i(this.fetchUniformLocation(s), v, v1);
    }

    public void setUniformi(String s, int v, int v1, int v2) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform3i(this.fetchUniformLocation(s), v, v1, v2);
    }

    public void setUniformi(String s, int v, int v1, int v2, int v3) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glUniform4i(this.fetchUniformLocation(s), v, v1, v2, v3);
    }

    public void setVertexAttribute(int v, int v1, int v2, boolean z, int v3, int v4) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glVertexAttribPointer(v, v1, v2, z, v3, v4);
    }

    public void setVertexAttribute(int v, int v1, int v2, boolean z, int v3, Buffer buffer0) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        gL200.glVertexAttribPointer(v, v1, v2, z, v3, buffer0);
    }

    public void setVertexAttribute(String s, int v, int v1, boolean z, int v2, int v3) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        int v4 = this.fetchAttributeLocation(s);
        if(v4 == -1) {
            return;
        }
        gL200.glVertexAttribPointer(v4, v, v1, z, v2, v3);
    }

    public void setVertexAttribute(String s, int v, int v1, boolean z, int v2, Buffer buffer0) {
        GL20 gL200 = Gdx.gl20;
        this.checkManaged();
        int v3 = this.fetchAttributeLocation(s);
        if(v3 == -1) {
            return;
        }
        gL200.glVertexAttribPointer(v3, v, v1, z, v2, buffer0);
    }
}

