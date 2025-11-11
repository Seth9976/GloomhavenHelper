package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ImmediateModeRenderer20 implements ImmediateModeRenderer {
    private final int colorOffset;
    private final int maxVertices;
    private final Mesh mesh;
    private final int normalOffset;
    private int numSetTexCoords;
    private final int numTexCoords;
    private int numVertices;
    private boolean ownsShader;
    private int primitiveType;
    private final Matrix4 projModelView;
    private ShaderProgram shader;
    private final String[] shaderUniformNames;
    private final int texCoordOffset;
    private int vertexIdx;
    private final int vertexSize;
    private final float[] vertices;

    public ImmediateModeRenderer20(int v, boolean z, boolean z1, int v1) {
        this(v, z, z1, v1, ImmediateModeRenderer20.createDefaultShader(z, z1, v1));
        this.ownsShader = true;
    }

    public ImmediateModeRenderer20(int v, boolean z, boolean z1, int v1, ShaderProgram shaderProgram0) {
        this.projModelView = new Matrix4();
        this.maxVertices = v;
        this.numTexCoords = v1;
        this.shader = shaderProgram0;
        this.mesh = new Mesh(false, v, 0, this.buildVertexAttributes(z, z1, v1));
        this.vertices = new float[v * (this.mesh.getVertexAttributes().vertexSize / 4)];
        this.vertexSize = this.mesh.getVertexAttributes().vertexSize / 4;
        this.normalOffset = this.mesh.getVertexAttribute(8) == null ? 0 : this.mesh.getVertexAttribute(8).offset / 4;
        this.colorOffset = this.mesh.getVertexAttribute(4) == null ? 0 : this.mesh.getVertexAttribute(4).offset / 4;
        this.texCoordOffset = this.mesh.getVertexAttribute(16) == null ? 0 : this.mesh.getVertexAttribute(16).offset / 4;
        this.shaderUniformNames = new String[v1];
        for(int v2 = 0; v2 < v1; ++v2) {
            this.shaderUniformNames[v2] = "u_sampler" + v2;
        }
    }

    public ImmediateModeRenderer20(boolean z, boolean z1, int v) {
        this(5000, z, z1, v, ImmediateModeRenderer20.createDefaultShader(z, z1, v));
        this.ownsShader = true;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void begin(Matrix4 matrix40, int v) {
        this.projModelView.set(matrix40);
        this.primitiveType = v;
    }

    private VertexAttribute[] buildVertexAttributes(boolean z, boolean z1, int v) {
        Array array0 = new Array();
        array0.add(new VertexAttribute(1, 3, "a_position"));
        if(z) {
            array0.add(new VertexAttribute(8, 3, "a_normal"));
        }
        if(z1) {
            array0.add(new VertexAttribute(4, 4, "a_color"));
        }
        for(int v2 = 0; v2 < v; ++v2) {
            array0.add(new VertexAttribute(16, 2, "a_texCoord" + v2));
        }
        VertexAttribute[] arr_vertexAttribute = new VertexAttribute[array0.size];
        for(int v1 = 0; v1 < array0.size; ++v1) {
            arr_vertexAttribute[v1] = (VertexAttribute)array0.get(v1);
        }
        return arr_vertexAttribute;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(float f) {
        this.vertices[this.vertexIdx + this.colorOffset] = f;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(float f, float f1, float f2, float f3) {
        this.vertices[this.vertexIdx + this.colorOffset] = Color.toFloatBits(f, f1, f2, f3);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(Color color0) {
        this.vertices[this.vertexIdx + this.colorOffset] = color0.toFloatBits();
    }

    public static ShaderProgram createDefaultShader(boolean z, boolean z1, int v) {
        ShaderProgram shaderProgram0 = new ShaderProgram(ImmediateModeRenderer20.createVertexShader(z, z1, v), ImmediateModeRenderer20.createFragmentShader(z, z1, v));
        if(!shaderProgram0.isCompiled()) {
            throw new GdxRuntimeException("Error compiling shader: " + shaderProgram0.getLog());
        }
        return shaderProgram0;
    }

    private static String createFragmentShader(boolean z, boolean z1, int v) {
        String s = z1 ? "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_col;\n" : "#ifdef GL_ES\nprecision mediump float;\n#endif\n";
        for(int v2 = 0; v2 < v; ++v2) {
            s = s + "varying vec2 v_tex" + v2 + ";\n" + "uniform sampler2D u_sampler" + v2 + ";\n";
        }
        String s1 = s + "void main() {\n   gl_FragColor = " + (z1 ? "v_col" : "vec4(1, 1, 1, 1)");
        if(v > 0) {
            s1 = s1 + " * ";
        }
        for(int v1 = 0; v1 < v; ++v1) {
            s1 = v1 == v - 1 ? s1 + " texture2D(u_sampler" + v1 + ",  v_tex" + v1 + ")" : s1 + " texture2D(u_sampler" + v1 + ",  v_tex" + v1 + ") *";
        }
        return s1 + ";\n}";
    }

    private static String createVertexShader(boolean z, boolean z1, int v) {
        String s = "attribute vec4 a_position;\n" + (z ? "attribute vec3 a_normal;\n" : "") + (z1 ? "attribute vec4 a_color;\n" : "");
        for(int v2 = 0; v2 < v; ++v2) {
            s = s + "attribute vec2 a_texCoord" + v2 + ";\n";
        }
        String s1 = s + "uniform mat4 u_projModelView;\n" + (z1 ? "varying vec4 v_col;\n" : "");
        for(int v3 = 0; v3 < v; ++v3) {
            s1 = s1 + "varying vec2 v_tex" + v3 + ";\n";
        }
        String s2 = s1 + "void main() {\n   gl_Position = u_projModelView * a_position;\n";
        if(z1) {
            s2 = s2 + "   v_col = a_color;\n   v_col.a *= 255.0 / 254.0;\n";
        }
        for(int v1 = 0; v1 < v; ++v1) {
            s2 = s2 + "   v_tex" + v1 + " = " + "a_texCoord" + v1 + ";\n";
        }
        return s2 + "   gl_PointSize = 1.0;\n}\n";
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void dispose() {
        if(this.ownsShader) {
            ShaderProgram shaderProgram0 = this.shader;
            if(shaderProgram0 != null) {
                shaderProgram0.dispose();
            }
        }
        this.mesh.dispose();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void end() {
        this.flush();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void flush() {
        if(this.numVertices == 0) {
            return;
        }
        this.shader.bind();
        this.shader.setUniformMatrix("u_projModelView", this.projModelView);
        for(int v = 0; v < this.numTexCoords; ++v) {
            this.shader.setUniformi(this.shaderUniformNames[v], v);
        }
        this.mesh.setVertices(this.vertices, 0, this.vertexIdx);
        this.mesh.render(this.shader, this.primitiveType);
        this.numSetTexCoords = 0;
        this.vertexIdx = 0;
        this.numVertices = 0;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public int getMaxVertices() {
        return this.maxVertices;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public int getNumVertices() {
        return this.numVertices;
    }

    public ShaderProgram getShader() {
        return this.shader;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void normal(float f, float f1, float f2) {
        int v = this.vertexIdx + this.normalOffset;
        this.vertices[v] = f;
        this.vertices[v + 1] = f1;
        this.vertices[v + 2] = f2;
    }

    public void setShader(ShaderProgram shaderProgram0) {
        if(this.ownsShader) {
            this.shader.dispose();
        }
        this.shader = shaderProgram0;
        this.ownsShader = false;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void texCoord(float f, float f1) {
        int v = this.vertexIdx + this.texCoordOffset;
        int v1 = this.numSetTexCoords;
        this.vertices[v + v1] = f;
        this.vertices[v + v1 + 1] = f1;
        this.numSetTexCoords = v1 + 2;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void vertex(float f, float f1, float f2) {
        int v = this.vertexIdx;
        this.vertices[v] = f;
        this.vertices[v + 1] = f1;
        this.vertices[v + 2] = f2;
        this.numSetTexCoords = 0;
        this.vertexIdx = v + this.vertexSize;
        ++this.numVertices;
    }
}

