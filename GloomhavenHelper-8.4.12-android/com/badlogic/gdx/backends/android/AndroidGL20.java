package com.badlogic.gdx.backends.android;

import android.opengl.GLES20;
import com.badlogic.gdx.graphics.GL20;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class AndroidGL20 implements GL20 {
    private byte[] buffer;
    private int[] ints;
    private int[] ints2;
    private int[] ints3;

    public AndroidGL20() {
        this.ints = new int[1];
        this.ints2 = new int[1];
        this.ints3 = new int[1];
        this.buffer = new byte[0x200];
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int v) {
        GLES20.glActiveTexture(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int v, int v1) {
        GLES20.glAttachShader(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int v, int v1, String s) {
        GLES20.glBindAttribLocation(v, v1, s);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int v, int v1) {
        GLES20.glBindBuffer(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int v, int v1) {
        GLES20.glBindFramebuffer(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int v, int v1) {
        GLES20.glBindRenderbuffer(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int v, int v1) {
        GLES20.glBindTexture(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f, float f1, float f2, float f3) {
        GLES20.glBlendColor(f, f1, f2, f3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int v) {
        GLES20.glBlendEquation(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int v, int v1) {
        GLES20.glBlendEquationSeparate(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int v, int v1) {
        GLES20.glBlendFunc(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int v, int v1, int v2, int v3) {
        GLES20.glBlendFuncSeparate(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int v, int v1, Buffer buffer0, int v2) {
        GLES20.glBufferData(v, v1, buffer0, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int v, int v1, int v2, Buffer buffer0) {
        GLES20.glBufferSubData(v, v1, v2, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int v) {
        return GLES20.glCheckFramebufferStatus(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClear(int v) {
        GLES20.glClear(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f, float f1, float f2, float f3) {
        GLES20.glClearColor(f, f1, f2, f3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f) {
        GLES20.glClearDepthf(f);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int v) {
        GLES20.glClearStencil(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z, boolean z1, boolean z2, boolean z3) {
        GLES20.glColorMask(z, z1, z2, z3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int v) {
        GLES20.glCompileShader(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, Buffer buffer0) {
        GLES20.glCompressedTexImage2D(v, v1, v2, v3, v4, v5, v6, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        GLES20.glCompressedTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        GLES20.glCopyTexImage2D(v, v1, v2, v3, v4, v5, v6, v7);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        GLES20.glCopyTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        return GLES20.glCreateProgram();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int v) {
        return GLES20.glCreateShader(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int v) {
        GLES20.glCullFace(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int v) {
        int[] arr_v = this.ints;
        arr_v[0] = v;
        GLES20.glDeleteBuffers(1, arr_v, 0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int v, IntBuffer intBuffer0) {
        GLES20.glDeleteBuffers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int v) {
        int[] arr_v = this.ints;
        arr_v[0] = v;
        GLES20.glDeleteFramebuffers(1, arr_v, 0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int v, IntBuffer intBuffer0) {
        GLES20.glDeleteFramebuffers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int v) {
        GLES20.glDeleteProgram(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int v) {
        int[] arr_v = this.ints;
        arr_v[0] = v;
        GLES20.glDeleteRenderbuffers(1, arr_v, 0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int v, IntBuffer intBuffer0) {
        GLES20.glDeleteRenderbuffers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int v) {
        GLES20.glDeleteShader(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int v) {
        int[] arr_v = this.ints;
        arr_v[0] = v;
        GLES20.glDeleteTextures(1, arr_v, 0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int v, IntBuffer intBuffer0) {
        GLES20.glDeleteTextures(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int v) {
        GLES20.glDepthFunc(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z) {
        GLES20.glDepthMask(z);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f, float f1) {
        GLES20.glDepthRangef(f, f1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int v, int v1) {
        GLES20.glDetachShader(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDisable(int v) {
        GLES20.glDisable(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int v) {
        GLES20.glDisableVertexAttribArray(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int v, int v1, int v2) {
        GLES20.glDrawArrays(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int v, int v1, int v2, int v3) {
        GLES20.glDrawElements(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int v, int v1, int v2, Buffer buffer0) {
        GLES20.glDrawElements(v, v1, v2, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glEnable(int v) {
        GLES20.glEnable(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int v) {
        GLES20.glEnableVertexAttribArray(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        GLES20.glFinish();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        GLES20.glFlush();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int v, int v1, int v2, int v3) {
        GLES20.glFramebufferRenderbuffer(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int v, int v1, int v2, int v3, int v4) {
        GLES20.glFramebufferTexture2D(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int v) {
        GLES20.glFrontFace(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        GLES20.glGenBuffers(1, this.ints, 0);
        return this.ints[0];
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int v, IntBuffer intBuffer0) {
        GLES20.glGenBuffers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        GLES20.glGenFramebuffers(1, this.ints, 0);
        return this.ints[0];
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int v, IntBuffer intBuffer0) {
        GLES20.glGenFramebuffers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        GLES20.glGenRenderbuffers(1, this.ints, 0);
        return this.ints[0];
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int v, IntBuffer intBuffer0) {
        GLES20.glGenRenderbuffers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        GLES20.glGenTextures(1, this.ints, 0);
        return this.ints[0];
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int v, IntBuffer intBuffer0) {
        GLES20.glGenTextures(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int v) {
        GLES20.glGenerateMipmap(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        GLES20.glGetActiveAttrib(v, v1, this.buffer.length, this.ints, 0, this.ints2, 0, this.ints3, 0, this.buffer, 0);
        intBuffer0.put(this.ints2[0]);
        intBuffer1.put(this.ints3[0]);
        return new String(this.buffer, 0, this.ints[0]);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        GLES20.glGetActiveUniform(v, v1, this.buffer.length, this.ints, 0, this.ints2, 0, this.ints3, 0, this.buffer, 0);
        intBuffer0.put(this.ints2[0]);
        intBuffer1.put(this.ints3[0]);
        return new String(this.buffer, 0, this.ints[0]);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int v, int v1, Buffer buffer0, IntBuffer intBuffer0) {
        GLES20.glGetAttachedShaders(v, v1, ((IntBuffer)buffer0), intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int v, String s) {
        return GLES20.glGetAttribLocation(v, s);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int v, Buffer buffer0) {
        GLES20.glGetBooleanv(v, ((IntBuffer)buffer0));
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glGetBufferParameteriv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        return GLES20.glGetError();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int v, FloatBuffer floatBuffer0) {
        GLES20.glGetFloatv(v, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int v, int v1, int v2, IntBuffer intBuffer0) {
        GLES20.glGetFramebufferAttachmentParameteriv(v, v1, v2, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int v, IntBuffer intBuffer0) {
        GLES20.glGetIntegerv(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int v) {
        return GLES20.glGetProgramInfoLog(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glGetProgramiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glGetRenderbufferParameteriv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int v) {
        return GLES20.glGetShaderInfoLog(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        GLES20.glGetShaderPrecisionFormat(v, v1, intBuffer0, intBuffer1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glGetShaderiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetString(int v) {
        return GLES20.glGetString(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glGetTexParameterfv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glGetTexParameteriv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int v, String s) {
        return GLES20.glGetUniformLocation(v, s);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glGetUniformfv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glGetUniformiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int v, int v1, Buffer buffer0) {
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glGetVertexAttribfv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glGetVertexAttribiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glHint(int v, int v1) {
        GLES20.glHint(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int v) {
        return GLES20.glIsBuffer(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int v) {
        return GLES20.glIsEnabled(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int v) {
        return GLES20.glIsFramebuffer(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int v) {
        return GLES20.glIsProgram(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int v) {
        return GLES20.glIsRenderbuffer(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int v) {
        return GLES20.glIsShader(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int v) {
        return GLES20.glIsTexture(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f) {
        GLES20.glLineWidth(f);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int v) {
        GLES20.glLinkProgram(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int v, int v1) {
        GLES20.glPixelStorei(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f, float f1) {
        GLES20.glPolygonOffset(f, f1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int v, int v1, int v2, int v3, int v4, int v5, Buffer buffer0) {
        GLES20.glReadPixels(v, v1, v2, v3, v4, v5, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
        GLES20.glReleaseShaderCompiler();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int v, int v1, int v2, int v3) {
        GLES20.glRenderbufferStorage(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f, boolean z) {
        GLES20.glSampleCoverage(f, z);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glScissor(int v, int v1, int v2, int v3) {
        GLES20.glScissor(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int v, IntBuffer intBuffer0, int v1, Buffer buffer0, int v2) {
        GLES20.glShaderBinary(v, intBuffer0, v1, buffer0, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int v, String s) {
        GLES20.glShaderSource(v, s);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int v, int v1, int v2) {
        GLES20.glStencilFunc(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int v, int v1, int v2, int v3) {
        GLES20.glStencilFuncSeparate(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int v) {
        GLES20.glStencilMask(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int v, int v1) {
        GLES20.glStencilMaskSeparate(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int v, int v1, int v2) {
        GLES20.glStencilOp(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int v, int v1, int v2, int v3) {
        GLES20.glStencilOpSeparate(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        GLES20.glTexImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int v, int v1, float f) {
        GLES20.glTexParameterf(v, v1, f);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glTexParameterfv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int v, int v1, int v2) {
        GLES20.glTexParameteri(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glTexParameteriv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        GLES20.glTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int v, float f) {
        GLES20.glUniform1f(v, f);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glUniform1fv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int v, int v1, float[] arr_f, int v2) {
        GLES20.glUniform1fv(v, v1, arr_f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int v, int v1) {
        GLES20.glUniform1i(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glUniform1iv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int v, int v1, int[] arr_v, int v2) {
        GLES20.glUniform1iv(v, v1, arr_v, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int v, float f, float f1) {
        GLES20.glUniform2f(v, f, f1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glUniform2fv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int v, int v1, float[] arr_f, int v2) {
        GLES20.glUniform2fv(v, v1, arr_f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int v, int v1, int v2) {
        GLES20.glUniform2i(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glUniform2iv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int v, int v1, int[] arr_v, int v2) {
        GLES20.glUniform2iv(v, v1, arr_v, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int v, float f, float f1, float f2) {
        GLES20.glUniform3f(v, f, f1, f2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glUniform3fv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int v, int v1, float[] arr_f, int v2) {
        GLES20.glUniform3fv(v, v1, arr_f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int v, int v1, int v2, int v3) {
        GLES20.glUniform3i(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glUniform3iv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int v, int v1, int[] arr_v, int v2) {
        GLES20.glUniform3iv(v, v1, arr_v, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int v, float f, float f1, float f2, float f3) {
        GLES20.glUniform4f(v, f, f1, f2, f3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES20.glUniform4fv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int v, int v1, float[] arr_f, int v2) {
        GLES20.glUniform4fv(v, v1, arr_f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int v, int v1, int v2, int v3, int v4) {
        GLES20.glUniform4i(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int v, int v1, IntBuffer intBuffer0) {
        GLES20.glUniform4iv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int v, int v1, int[] arr_v, int v2) {
        GLES20.glUniform4iv(v, v1, arr_v, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES20.glUniformMatrix2fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        GLES20.glUniformMatrix2fv(v, v1, z, arr_f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES20.glUniformMatrix3fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        GLES20.glUniformMatrix3fv(v, v1, z, arr_f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES20.glUniformMatrix4fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        GLES20.glUniformMatrix4fv(v, v1, z, arr_f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int v) {
        GLES20.glUseProgram(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int v) {
        GLES20.glValidateProgram(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int v, float f) {
        GLES20.glVertexAttrib1f(v, f);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int v, FloatBuffer floatBuffer0) {
        GLES20.glVertexAttrib1fv(v, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int v, float f, float f1) {
        GLES20.glVertexAttrib2f(v, f, f1);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int v, FloatBuffer floatBuffer0) {
        GLES20.glVertexAttrib2fv(v, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int v, float f, float f1, float f2) {
        GLES20.glVertexAttrib3f(v, f, f1, f2);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int v, FloatBuffer floatBuffer0) {
        GLES20.glVertexAttrib3fv(v, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int v, float f, float f1, float f2, float f3) {
        GLES20.glVertexAttrib4f(v, f, f1, f2, f3);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int v, FloatBuffer floatBuffer0) {
        GLES20.glVertexAttrib4fv(v, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int v, int v1, int v2, boolean z, int v3, int v4) {
        GLES20.glVertexAttribPointer(v, v1, v2, z, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int v, int v1, int v2, boolean z, int v3, Buffer buffer0) {
        GLES20.glVertexAttribPointer(v, v1, v2, z, v3, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glViewport(int v, int v1, int v2, int v3) {
        GLES20.glViewport(v, v1, v2, v3);
    }
}

