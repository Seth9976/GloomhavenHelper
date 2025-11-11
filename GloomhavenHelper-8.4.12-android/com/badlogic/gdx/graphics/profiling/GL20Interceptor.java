package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL20;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GL20Interceptor extends GLInterceptor implements GL20 {
    protected final GL20 gl20;

    protected GL20Interceptor(GLProfiler gLProfiler0, GL20 gL200) {
        super(gLProfiler0);
        this.gl20 = gL200;
    }

    private void check() {
        for(int v = this.gl20.glGetError(); v != 0; v = this.gl20.glGetError()) {
            this.glProfiler.getListener().onError(v);
        }
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int v) {
        ++this.calls;
        this.gl20.glActiveTexture(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int v, int v1) {
        ++this.calls;
        this.gl20.glAttachShader(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int v, int v1, String s) {
        ++this.calls;
        this.gl20.glBindAttribLocation(v, v1, s);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int v, int v1) {
        ++this.calls;
        this.gl20.glBindBuffer(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int v, int v1) {
        ++this.calls;
        this.gl20.glBindFramebuffer(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int v, int v1) {
        ++this.calls;
        this.gl20.glBindRenderbuffer(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int v, int v1) {
        ++this.textureBindings;
        ++this.calls;
        this.gl20.glBindTexture(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl20.glBlendColor(f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int v) {
        ++this.calls;
        this.gl20.glBlendEquation(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int v, int v1) {
        ++this.calls;
        this.gl20.glBlendEquationSeparate(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int v, int v1) {
        ++this.calls;
        this.gl20.glBlendFunc(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glBlendFuncSeparate(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int v, int v1, Buffer buffer0, int v2) {
        ++this.calls;
        this.gl20.glBufferData(v, v1, buffer0, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int v, int v1, int v2, Buffer buffer0) {
        ++this.calls;
        this.gl20.glBufferSubData(v, v1, v2, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int v) {
        ++this.calls;
        int v1 = this.gl20.glCheckFramebufferStatus(v);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClear(int v) {
        ++this.calls;
        this.gl20.glClear(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl20.glClearColor(f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f) {
        ++this.calls;
        this.gl20.glClearDepthf(f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int v) {
        ++this.calls;
        this.gl20.glClearStencil(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z, boolean z1, boolean z2, boolean z3) {
        ++this.calls;
        this.gl20.glColorMask(z, z1, z2, z3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int v) {
        ++this.calls;
        this.gl20.glCompileShader(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, Buffer buffer0) {
        ++this.calls;
        this.gl20.glCompressedTexImage2D(v, v1, v2, v3, v4, v5, v6, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        ++this.calls;
        this.gl20.glCompressedTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        ++this.calls;
        this.gl20.glCopyTexImage2D(v, v1, v2, v3, v4, v5, v6, v7);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        ++this.calls;
        this.gl20.glCopyTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        ++this.calls;
        int v = this.gl20.glCreateProgram();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int v) {
        ++this.calls;
        int v1 = this.gl20.glCreateShader(v);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int v) {
        ++this.calls;
        this.gl20.glCullFace(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int v) {
        ++this.calls;
        this.gl20.glDeleteBuffer(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glDeleteBuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int v) {
        ++this.calls;
        this.gl20.glDeleteFramebuffer(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glDeleteFramebuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int v) {
        ++this.calls;
        this.gl20.glDeleteProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int v) {
        ++this.calls;
        this.gl20.glDeleteRenderbuffer(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glDeleteRenderbuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int v) {
        ++this.calls;
        this.gl20.glDeleteShader(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int v) {
        ++this.calls;
        this.gl20.glDeleteTexture(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glDeleteTextures(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int v) {
        ++this.calls;
        this.gl20.glDepthFunc(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z) {
        ++this.calls;
        this.gl20.glDepthMask(z);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f, float f1) {
        ++this.calls;
        this.gl20.glDepthRangef(f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int v, int v1) {
        ++this.calls;
        this.gl20.glDetachShader(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDisable(int v) {
        ++this.calls;
        this.gl20.glDisable(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int v) {
        ++this.calls;
        this.gl20.glDisableVertexAttribArray(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int v, int v1, int v2) {
        this.vertexCount.put(((float)v2));
        ++this.drawCalls;
        ++this.calls;
        this.gl20.glDrawArrays(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int v, int v1, int v2, int v3) {
        this.vertexCount.put(((float)v1));
        ++this.drawCalls;
        ++this.calls;
        this.gl20.glDrawElements(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int v, int v1, int v2, Buffer buffer0) {
        this.vertexCount.put(((float)v1));
        ++this.drawCalls;
        ++this.calls;
        this.gl20.glDrawElements(v, v1, v2, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glEnable(int v) {
        ++this.calls;
        this.gl20.glEnable(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int v) {
        ++this.calls;
        this.gl20.glEnableVertexAttribArray(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        ++this.calls;
        this.gl20.glFinish();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        ++this.calls;
        this.gl20.glFlush();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glFramebufferRenderbuffer(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl20.glFramebufferTexture2D(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int v) {
        ++this.calls;
        this.gl20.glFrontFace(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        ++this.calls;
        int v = this.gl20.glGenBuffer();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGenBuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        ++this.calls;
        int v = this.gl20.glGenFramebuffer();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGenFramebuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        ++this.calls;
        int v = this.gl20.glGenRenderbuffer();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGenRenderbuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        ++this.calls;
        int v = this.gl20.glGenTexture();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGenTextures(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int v) {
        ++this.calls;
        this.gl20.glGenerateMipmap(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        ++this.calls;
        String s = this.gl20.glGetActiveAttrib(v, v1, intBuffer0, intBuffer1);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        ++this.calls;
        String s = this.gl20.glGetActiveUniform(v, v1, intBuffer0, intBuffer1);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int v, int v1, Buffer buffer0, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetAttachedShaders(v, v1, buffer0, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int v, String s) {
        ++this.calls;
        int v1 = this.gl20.glGetAttribLocation(v, s);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int v, Buffer buffer0) {
        ++this.calls;
        this.gl20.glGetBooleanv(v, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetBufferParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        ++this.calls;
        return this.gl20.glGetError();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glGetFloatv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int v, int v1, int v2, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetFramebufferAttachmentParameteriv(v, v1, v2, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetIntegerv(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int v) {
        ++this.calls;
        String s = this.gl20.glGetProgramInfoLog(v);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetProgramiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetRenderbufferParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int v) {
        ++this.calls;
        String s = this.gl20.glGetShaderInfoLog(v);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        ++this.calls;
        this.gl20.glGetShaderPrecisionFormat(v, v1, intBuffer0, intBuffer1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetShaderiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetString(int v) {
        ++this.calls;
        String s = this.gl20.glGetString(v);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glGetTexParameterfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetTexParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int v, String s) {
        ++this.calls;
        int v1 = this.gl20.glGetUniformLocation(v, s);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glGetUniformfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetUniformiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int v, int v1, Buffer buffer0) {
        ++this.calls;
        this.gl20.glGetVertexAttribPointerv(v, v1, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glGetVertexAttribfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glGetVertexAttribiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glHint(int v, int v1) {
        ++this.calls;
        this.gl20.glHint(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int v) {
        ++this.calls;
        boolean z = this.gl20.glIsBuffer(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int v) {
        ++this.calls;
        boolean z = this.gl20.glIsEnabled(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int v) {
        ++this.calls;
        boolean z = this.gl20.glIsFramebuffer(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int v) {
        ++this.calls;
        boolean z = this.gl20.glIsProgram(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int v) {
        ++this.calls;
        boolean z = this.gl20.glIsRenderbuffer(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int v) {
        ++this.calls;
        boolean z = this.gl20.glIsShader(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int v) {
        ++this.calls;
        boolean z = this.gl20.glIsTexture(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f) {
        ++this.calls;
        this.gl20.glLineWidth(f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int v) {
        ++this.calls;
        this.gl20.glLinkProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int v, int v1) {
        ++this.calls;
        this.gl20.glPixelStorei(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f, float f1) {
        ++this.calls;
        this.gl20.glPolygonOffset(f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int v, int v1, int v2, int v3, int v4, int v5, Buffer buffer0) {
        ++this.calls;
        this.gl20.glReadPixels(v, v1, v2, v3, v4, v5, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
        ++this.calls;
        this.gl20.glReleaseShaderCompiler();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glRenderbufferStorage(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f, boolean z) {
        ++this.calls;
        this.gl20.glSampleCoverage(f, z);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glScissor(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glScissor(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int v, IntBuffer intBuffer0, int v1, Buffer buffer0, int v2) {
        ++this.calls;
        this.gl20.glShaderBinary(v, intBuffer0, v1, buffer0, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int v, String s) {
        ++this.calls;
        this.gl20.glShaderSource(v, s);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int v, int v1, int v2) {
        ++this.calls;
        this.gl20.glStencilFunc(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glStencilFuncSeparate(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int v) {
        ++this.calls;
        this.gl20.glStencilMask(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int v, int v1) {
        ++this.calls;
        this.gl20.glStencilMaskSeparate(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int v, int v1, int v2) {
        ++this.calls;
        this.gl20.glStencilOp(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glStencilOpSeparate(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        ++this.calls;
        this.gl20.glTexImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int v, int v1, float f) {
        ++this.calls;
        this.gl20.glTexParameterf(v, v1, f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glTexParameterfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int v, int v1, int v2) {
        ++this.calls;
        this.gl20.glTexParameteri(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glTexParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        ++this.calls;
        this.gl20.glTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int v, float f) {
        ++this.calls;
        this.gl20.glUniform1f(v, f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glUniform1fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl20.glUniform1fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int v, int v1) {
        ++this.calls;
        this.gl20.glUniform1i(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glUniform1iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl20.glUniform1iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int v, float f, float f1) {
        ++this.calls;
        this.gl20.glUniform2f(v, f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glUniform2fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl20.glUniform2fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int v, int v1, int v2) {
        ++this.calls;
        this.gl20.glUniform2i(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glUniform2iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl20.glUniform2iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int v, float f, float f1, float f2) {
        ++this.calls;
        this.gl20.glUniform3f(v, f, f1, f2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glUniform3fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl20.glUniform3fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glUniform3i(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glUniform3iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl20.glUniform3iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int v, float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl20.glUniform4f(v, f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glUniform4fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl20.glUniform4fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl20.glUniform4i(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl20.glUniform4iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl20.glUniform4iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glUniformMatrix2fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        ++this.calls;
        this.gl20.glUniformMatrix2fv(v, v1, z, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glUniformMatrix3fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        ++this.calls;
        this.gl20.glUniformMatrix3fv(v, v1, z, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glUniformMatrix4fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        ++this.calls;
        this.gl20.glUniformMatrix4fv(v, v1, z, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int v) {
        ++this.shaderSwitches;
        ++this.calls;
        this.gl20.glUseProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int v) {
        ++this.calls;
        this.gl20.glValidateProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int v, float f) {
        ++this.calls;
        this.gl20.glVertexAttrib1f(v, f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glVertexAttrib1fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int v, float f, float f1) {
        ++this.calls;
        this.gl20.glVertexAttrib2f(v, f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glVertexAttrib2fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int v, float f, float f1, float f2) {
        ++this.calls;
        this.gl20.glVertexAttrib3f(v, f, f1, f2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glVertexAttrib3fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int v, float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl20.glVertexAttrib4f(v, f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl20.glVertexAttrib4fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int v, int v1, int v2, boolean z, int v3, int v4) {
        ++this.calls;
        this.gl20.glVertexAttribPointer(v, v1, v2, z, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int v, int v1, int v2, boolean z, int v3, Buffer buffer0) {
        ++this.calls;
        this.gl20.glVertexAttribPointer(v, v1, v2, z, v3, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glViewport(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl20.glViewport(v, v1, v2, v3);
        this.check();
    }
}

