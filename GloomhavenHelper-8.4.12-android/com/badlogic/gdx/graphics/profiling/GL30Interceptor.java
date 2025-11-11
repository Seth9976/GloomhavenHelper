package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL30;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public class GL30Interceptor extends GLInterceptor implements GL30 {
    protected final GL30 gl30;

    protected GL30Interceptor(GLProfiler gLProfiler0, GL30 gL300) {
        super(gLProfiler0);
        this.gl30 = gL300;
    }

    private void check() {
        for(int v = this.gl30.glGetError(); v != 0; v = this.gl30.glGetError()) {
            this.glProfiler.getListener().onError(v);
        }
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glActiveTexture(int v) {
        ++this.calls;
        this.gl30.glActiveTexture(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glAttachShader(int v, int v1) {
        ++this.calls;
        this.gl30.glAttachShader(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBeginQuery(int v, int v1) {
        ++this.calls;
        this.gl30.glBeginQuery(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBeginTransformFeedback(int v) {
        ++this.calls;
        this.gl30.glBeginTransformFeedback(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindAttribLocation(int v, int v1, String s) {
        ++this.calls;
        this.gl30.glBindAttribLocation(v, v1, s);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindBuffer(int v, int v1) {
        ++this.calls;
        this.gl30.glBindBuffer(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindBufferBase(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glBindBufferBase(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindBufferRange(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glBindBufferRange(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindFramebuffer(int v, int v1) {
        ++this.calls;
        this.gl30.glBindFramebuffer(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindRenderbuffer(int v, int v1) {
        ++this.calls;
        this.gl30.glBindRenderbuffer(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindSampler(int v, int v1) {
        ++this.calls;
        this.gl30.glBindSampler(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBindTexture(int v, int v1) {
        ++this.textureBindings;
        ++this.calls;
        this.gl30.glBindTexture(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindTransformFeedback(int v, int v1) {
        ++this.calls;
        this.gl30.glBindTransformFeedback(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindVertexArray(int v) {
        ++this.calls;
        this.gl30.glBindVertexArray(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendColor(float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl30.glBlendColor(f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendEquation(int v) {
        ++this.calls;
        this.gl30.glBlendEquation(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendEquationSeparate(int v, int v1) {
        ++this.calls;
        this.gl30.glBlendEquationSeparate(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendFunc(int v, int v1) {
        ++this.calls;
        this.gl30.glBlendFunc(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBlendFuncSeparate(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glBlendFuncSeparate(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBlitFramebuffer(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        ++this.calls;
        this.gl30.glBlitFramebuffer(v, v1, v2, v3, v4, v5, v6, v7, v8, v9);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBufferData(int v, int v1, Buffer buffer0, int v2) {
        ++this.calls;
        this.gl30.glBufferData(v, v1, buffer0, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glBufferSubData(int v, int v1, int v2, Buffer buffer0) {
        ++this.calls;
        this.gl30.glBufferSubData(v, v1, v2, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCheckFramebufferStatus(int v) {
        ++this.calls;
        int v1 = this.gl30.glCheckFramebufferStatus(v);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClear(int v) {
        ++this.calls;
        this.gl30.glClear(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfi(int v, int v1, float f, int v2) {
        ++this.calls;
        this.gl30.glClearBufferfi(v, v1, f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glClearBufferfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glClearBufferiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferuiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glClearBufferuiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearColor(float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl30.glClearColor(f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearDepthf(float f) {
        ++this.calls;
        this.gl30.glClearDepthf(f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glClearStencil(int v) {
        ++this.calls;
        this.gl30.glClearStencil(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glColorMask(boolean z, boolean z1, boolean z2, boolean z3) {
        ++this.calls;
        this.gl30.glColorMask(z, z1, z2, z3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompileShader(int v) {
        ++this.calls;
        this.gl30.glCompileShader(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, Buffer buffer0) {
        ++this.calls;
        this.gl30.glCompressedTexImage2D(v, v1, v2, v3, v4, v5, v6, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCompressedTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        ++this.calls;
        this.gl30.glCompressedTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glCopyBufferSubData(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glCopyBufferSubData(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCopyTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        ++this.calls;
        this.gl30.glCopyTexImage2D(v, v1, v2, v3, v4, v5, v6, v7);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCopyTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        ++this.calls;
        this.gl30.glCopyTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glCopyTexSubImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8) {
        ++this.calls;
        this.gl30.glCopyTexSubImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCreateProgram() {
        ++this.calls;
        int v = this.gl30.glCreateProgram();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glCreateShader(int v) {
        ++this.calls;
        int v1 = this.gl30.glCreateShader(v);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glCullFace(int v) {
        ++this.calls;
        this.gl30.glCullFace(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffer(int v) {
        ++this.calls;
        this.gl30.glDeleteBuffer(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteBuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteBuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffer(int v) {
        ++this.calls;
        this.gl30.glDeleteFramebuffer(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteFramebuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteFramebuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteProgram(int v) {
        ++this.calls;
        this.gl30.glDeleteProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteQueries(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glDeleteQueries(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffer(int v) {
        ++this.calls;
        this.gl30.glDeleteRenderbuffer(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteRenderbuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteRenderbuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteSamplers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glDeleteSamplers(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteShader(int v) {
        ++this.calls;
        this.gl30.glDeleteShader(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteTexture(int v) {
        ++this.calls;
        this.gl30.glDeleteTexture(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDeleteTextures(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteTextures(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteTransformFeedbacks(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glDeleteTransformFeedbacks(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glDeleteVertexArrays(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glDeleteVertexArrays(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthFunc(int v) {
        ++this.calls;
        this.gl30.glDepthFunc(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthMask(boolean z) {
        ++this.calls;
        this.gl30.glDepthMask(z);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDepthRangef(float f, float f1) {
        ++this.calls;
        this.gl30.glDepthRangef(f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDetachShader(int v, int v1) {
        ++this.calls;
        this.gl30.glDetachShader(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDisable(int v) {
        ++this.calls;
        this.gl30.glDisable(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDisableVertexAttribArray(int v) {
        ++this.calls;
        this.gl30.glDisableVertexAttribArray(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawArrays(int v, int v1, int v2) {
        this.vertexCount.put(((float)v2));
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawArrays(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawArraysInstanced(int v, int v1, int v2, int v3) {
        this.vertexCount.put(((float)v2));
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawArraysInstanced(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawBuffers(int v, IntBuffer intBuffer0) {
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawBuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int v, int v1, int v2, int v3) {
        this.vertexCount.put(((float)v1));
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawElements(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glDrawElements(int v, int v1, int v2, Buffer buffer0) {
        this.vertexCount.put(((float)v1));
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawElements(v, v1, v2, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawElementsInstanced(int v, int v1, int v2, int v3, int v4) {
        this.vertexCount.put(((float)v1));
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawElementsInstanced(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int v, int v1, int v2, int v3, int v4, int v5) {
        this.vertexCount.put(((float)v3));
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawRangeElements(v, v1, v2, v3, v4, v5);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int v, int v1, int v2, int v3, int v4, Buffer buffer0) {
        this.vertexCount.put(((float)v3));
        ++this.drawCalls;
        ++this.calls;
        this.gl30.glDrawRangeElements(v, v1, v2, v3, v4, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glEnable(int v) {
        ++this.calls;
        this.gl30.glEnable(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glEnableVertexAttribArray(int v) {
        ++this.calls;
        this.gl30.glEnableVertexAttribArray(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glEndQuery(int v) {
        ++this.calls;
        this.gl30.glEndQuery(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glEndTransformFeedback() {
        ++this.calls;
        this.gl30.glEndTransformFeedback();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFinish() {
        ++this.calls;
        this.gl30.glFinish();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFlush() {
        ++this.calls;
        this.gl30.glFlush();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glFlushMappedBufferRange(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glFlushMappedBufferRange(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFramebufferRenderbuffer(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glFramebufferRenderbuffer(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFramebufferTexture2D(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glFramebufferTexture2D(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glFramebufferTextureLayer(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glFramebufferTextureLayer(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glFrontFace(int v) {
        ++this.calls;
        this.gl30.glFrontFace(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenBuffer() {
        ++this.calls;
        int v = this.gl30.glGenBuffer();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenBuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenBuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenFramebuffer() {
        ++this.calls;
        int v = this.gl30.glGenFramebuffer();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenFramebuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenFramebuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenQueries(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glGenQueries(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenRenderbuffer() {
        ++this.calls;
        int v = this.gl30.glGenRenderbuffer();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenRenderbuffers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenRenderbuffers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenSamplers(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glGenSamplers(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGenTexture() {
        ++this.calls;
        int v = this.gl30.glGenTexture();
        this.check();
        return v;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenTextures(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenTextures(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenTransformFeedbacks(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glGenTransformFeedbacks(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGenVertexArrays(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int v, int[] arr_v, int v1) {
        ++this.calls;
        this.gl30.glGenVertexArrays(v, arr_v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGenerateMipmap(int v) {
        ++this.calls;
        this.gl30.glGenerateMipmap(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetActiveAttrib(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        ++this.calls;
        String s = this.gl30.glGetActiveAttrib(v, v1, intBuffer0, intBuffer1);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetActiveUniform(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        ++this.calls;
        String s = this.gl30.glGetActiveUniform(v, v1, intBuffer0, intBuffer1);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public String glGetActiveUniformBlockName(int v, int v1) {
        ++this.calls;
        String s = this.gl30.glGetActiveUniformBlockName(v, v1);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockName(int v, int v1, Buffer buffer0, Buffer buffer1) {
        ++this.calls;
        this.gl30.glGetActiveUniformBlockName(v, v1, buffer0, buffer1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockiv(int v, int v1, int v2, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetActiveUniformBlockiv(v, v1, v2, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformsiv(int v, int v1, IntBuffer intBuffer0, int v2, IntBuffer intBuffer1) {
        ++this.calls;
        this.gl30.glGetActiveUniformsiv(v, v1, intBuffer0, v2, intBuffer1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetAttachedShaders(int v, int v1, Buffer buffer0, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetAttachedShaders(v, v1, buffer0, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetAttribLocation(int v, String s) {
        ++this.calls;
        int v1 = this.gl30.glGetAttribLocation(v, s);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetBooleanv(int v, Buffer buffer0) {
        ++this.calls;
        this.gl30.glGetBooleanv(v, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetBufferParameteri64v(int v, int v1, LongBuffer longBuffer0) {
        ++this.calls;
        this.gl30.glGetBufferParameteri64v(v, v1, longBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetBufferParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetBufferParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public Buffer glGetBufferPointerv(int v, int v1) {
        ++this.calls;
        Buffer buffer0 = this.gl30.glGetBufferPointerv(v, v1);
        this.check();
        return buffer0;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetError() {
        ++this.calls;
        return this.gl30.glGetError();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetFloatv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glGetFloatv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public int glGetFragDataLocation(int v, String s) {
        ++this.calls;
        int v1 = this.gl30.glGetFragDataLocation(v, s);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetFramebufferAttachmentParameteriv(int v, int v1, int v2, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetFramebufferAttachmentParameteriv(v, v1, v2, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetInteger64v(int v, LongBuffer longBuffer0) {
        ++this.calls;
        this.gl30.glGetInteger64v(v, longBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetIntegerv(int v, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetIntegerv(v, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetProgramInfoLog(int v) {
        ++this.calls;
        String s = this.gl30.glGetProgramInfoLog(v);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetProgramiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetProgramiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetQueryObjectuiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetQueryObjectuiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetQueryiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetQueryiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetRenderbufferParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetRenderbufferParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glGetSamplerParameterfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetSamplerParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetShaderInfoLog(int v) {
        ++this.calls;
        String s = this.gl30.glGetShaderInfoLog(v);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetShaderPrecisionFormat(int v, int v1, IntBuffer intBuffer0, IntBuffer intBuffer1) {
        ++this.calls;
        this.gl30.glGetShaderPrecisionFormat(v, v1, intBuffer0, intBuffer1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetShaderiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetShaderiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public String glGetString(int v) {
        ++this.calls;
        String s = this.gl30.glGetString(v);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public String glGetStringi(int v, int v1) {
        ++this.calls;
        String s = this.gl30.glGetStringi(v, v1);
        this.check();
        return s;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glGetTexParameterfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetTexParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetTexParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public int glGetUniformBlockIndex(int v, String s) {
        ++this.calls;
        int v1 = this.gl30.glGetUniformBlockIndex(v, s);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetUniformIndices(int v, String[] arr_s, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetUniformIndices(v, arr_s, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public int glGetUniformLocation(int v, String s) {
        ++this.calls;
        int v1 = this.gl30.glGetUniformLocation(v, s);
        this.check();
        return v1;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetUniformfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glGetUniformfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetUniformiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetUniformiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetUniformuiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetUniformuiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetVertexAttribIiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIuiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetVertexAttribIuiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribPointerv(int v, int v1, Buffer buffer0) {
        ++this.calls;
        this.gl30.glGetVertexAttribPointerv(v, v1, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glGetVertexAttribfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glGetVertexAttribiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glGetVertexAttribiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glHint(int v, int v1) {
        ++this.calls;
        this.gl30.glHint(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glInvalidateFramebuffer(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glInvalidateFramebuffer(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glInvalidateSubFramebuffer(int v, int v1, IntBuffer intBuffer0, int v2, int v3, int v4, int v5) {
        ++this.calls;
        this.gl30.glInvalidateSubFramebuffer(v, v1, intBuffer0, v2, v3, v4, v5);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsBuffer(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsBuffer(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsEnabled(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsEnabled(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsFramebuffer(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsFramebuffer(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsProgram(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsProgram(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsQuery(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsQuery(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsRenderbuffer(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsRenderbuffer(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsSampler(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsSampler(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsShader(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsShader(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public boolean glIsTexture(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsTexture(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsTransformFeedback(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsTransformFeedback(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsVertexArray(int v) {
        ++this.calls;
        boolean z = this.gl30.glIsVertexArray(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glLineWidth(float f) {
        ++this.calls;
        this.gl30.glLineWidth(f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glLinkProgram(int v) {
        ++this.calls;
        this.gl30.glLinkProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public Buffer glMapBufferRange(int v, int v1, int v2, int v3) {
        ++this.calls;
        Buffer buffer0 = this.gl30.glMapBufferRange(v, v1, v2, v3);
        this.check();
        return buffer0;
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glPauseTransformFeedback() {
        ++this.calls;
        this.gl30.glPauseTransformFeedback();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glPixelStorei(int v, int v1) {
        ++this.calls;
        this.gl30.glPixelStorei(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glPolygonOffset(float f, float f1) {
        ++this.calls;
        this.gl30.glPolygonOffset(f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glProgramParameteri(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glProgramParameteri(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glReadBuffer(int v) {
        ++this.calls;
        this.gl30.glReadBuffer(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glReadPixels(int v, int v1, int v2, int v3, int v4, int v5, Buffer buffer0) {
        ++this.calls;
        this.gl30.glReadPixels(v, v1, v2, v3, v4, v5, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glReleaseShaderCompiler() {
        ++this.calls;
        this.gl30.glReleaseShaderCompiler();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glRenderbufferStorage(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glRenderbufferStorage(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glRenderbufferStorageMultisample(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glRenderbufferStorageMultisample(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glResumeTransformFeedback() {
        ++this.calls;
        this.gl30.glResumeTransformFeedback();
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glSampleCoverage(float f, boolean z) {
        ++this.calls;
        this.gl30.glSampleCoverage(f, z);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterf(int v, int v1, float f) {
        ++this.calls;
        this.gl30.glSamplerParameterf(v, v1, f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glSamplerParameterfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteri(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glSamplerParameteri(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glSamplerParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glScissor(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glScissor(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glShaderBinary(int v, IntBuffer intBuffer0, int v1, Buffer buffer0, int v2) {
        ++this.calls;
        this.gl30.glShaderBinary(v, intBuffer0, v1, buffer0, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glShaderSource(int v, String s) {
        ++this.calls;
        this.gl30.glShaderSource(v, s);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilFunc(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glStencilFunc(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilFuncSeparate(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glStencilFuncSeparate(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilMask(int v) {
        ++this.calls;
        this.gl30.glStencilMask(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilMaskSeparate(int v, int v1) {
        ++this.calls;
        this.gl30.glStencilMaskSeparate(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilOp(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glStencilOp(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glStencilOpSeparate(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glStencilOpSeparate(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        ++this.calls;
        this.gl30.glTexImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        ++this.calls;
        this.gl30.glTexImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, v9);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, Buffer buffer0) {
        ++this.calls;
        this.gl30.glTexImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameterf(int v, int v1, float f) {
        ++this.calls;
        this.gl30.glTexParameterf(v, v1, f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glTexParameterfv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameteri(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glTexParameteri(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexParameteriv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glTexParameteriv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glTexSubImage2D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, Buffer buffer0) {
        ++this.calls;
        this.gl30.glTexSubImage2D(v, v1, v2, v3, v4, v5, v6, v7, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9, int v10) {
        ++this.calls;
        this.gl30.glTexSubImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9, Buffer buffer0) {
        ++this.calls;
        this.gl30.glTexSubImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, v9, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTransformFeedbackVaryings(int v, String[] arr_s, int v1) {
        ++this.calls;
        this.gl30.glTransformFeedbackVaryings(v, arr_s, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1f(int v, float f) {
        ++this.calls;
        this.gl30.glUniform1f(v, f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniform1fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl30.glUniform1fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1i(int v, int v1) {
        ++this.calls;
        this.gl30.glUniform1i(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glUniform1iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform1iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl30.glUniform1iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniform1uiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glUniform1uiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2f(int v, float f, float f1) {
        ++this.calls;
        this.gl30.glUniform2f(v, f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniform2fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl30.glUniform2fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2i(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glUniform2i(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glUniform2iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform2iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl30.glUniform2iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3f(int v, float f, float f1, float f2) {
        ++this.calls;
        this.gl30.glUniform3f(v, f, f1, f2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniform3fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl30.glUniform3fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3i(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glUniform3i(v, v1, v2, v3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glUniform3iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform3iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl30.glUniform3iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniform3uiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glUniform3uiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4f(int v, float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl30.glUniform4f(v, f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int v, int v1, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniform4fv(v, v1, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4fv(int v, int v1, float[] arr_f, int v2) {
        ++this.calls;
        this.gl30.glUniform4fv(v, v1, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4i(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glUniform4i(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glUniform4iv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniform4iv(int v, int v1, int[] arr_v, int v2) {
        ++this.calls;
        this.gl30.glUniform4iv(v, v1, arr_v, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniform4uiv(int v, int v1, IntBuffer intBuffer0) {
        ++this.calls;
        this.gl30.glUniform4uiv(v, v1, intBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformBlockBinding(int v, int v1, int v2) {
        ++this.calls;
        this.gl30.glUniformBlockBinding(v, v1, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix2fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix2fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        ++this.calls;
        this.gl30.glUniformMatrix2fv(v, v1, z, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x3fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix2x3fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x4fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix2x4fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix3fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix3fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        ++this.calls;
        this.gl30.glUniformMatrix3fv(v, v1, z, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x2fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix3x2fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x4fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix3x4fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix4fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUniformMatrix4fv(int v, int v1, boolean z, float[] arr_f, int v2) {
        ++this.calls;
        this.gl30.glUniformMatrix4fv(v, v1, z, arr_f, v2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x2fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix4x2fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x3fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glUniformMatrix4x3fv(v, v1, z, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glUnmapBuffer(int v) {
        ++this.calls;
        boolean z = this.gl30.glUnmapBuffer(v);
        this.check();
        return z;
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glUseProgram(int v) {
        ++this.shaderSwitches;
        ++this.calls;
        this.gl30.glUseProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glValidateProgram(int v) {
        ++this.calls;
        this.gl30.glValidateProgram(v);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1f(int v, float f) {
        ++this.calls;
        this.gl30.glVertexAttrib1f(v, f);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib1fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glVertexAttrib1fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2f(int v, float f, float f1) {
        ++this.calls;
        this.gl30.glVertexAttrib2f(v, f, f1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib2fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glVertexAttrib2fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3f(int v, float f, float f1, float f2) {
        ++this.calls;
        this.gl30.glVertexAttrib3f(v, f, f1, f2);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib3fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glVertexAttrib3fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4f(int v, float f, float f1, float f2, float f3) {
        ++this.calls;
        this.gl30.glVertexAttrib4f(v, f, f1, f2, f3);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttrib4fv(int v, FloatBuffer floatBuffer0) {
        ++this.calls;
        this.gl30.glVertexAttrib4fv(v, floatBuffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribDivisor(int v, int v1) {
        ++this.calls;
        this.gl30.glVertexAttribDivisor(v, v1);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4i(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glVertexAttribI4i(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4ui(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glVertexAttribI4ui(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribIPointer(int v, int v1, int v2, int v3, int v4) {
        ++this.calls;
        this.gl30.glVertexAttribIPointer(v, v1, v2, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int v, int v1, int v2, boolean z, int v3, int v4) {
        ++this.calls;
        this.gl30.glVertexAttribPointer(v, v1, v2, z, v3, v4);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL30, com.badlogic.gdx.graphics.GL20
    public void glVertexAttribPointer(int v, int v1, int v2, boolean z, int v3, Buffer buffer0) {
        ++this.calls;
        this.gl30.glVertexAttribPointer(v, v1, v2, z, v3, buffer0);
        this.check();
    }

    @Override  // com.badlogic.gdx.graphics.GL20
    public void glViewport(int v, int v1, int v2, int v3) {
        ++this.calls;
        this.gl30.glViewport(v, v1, v2, v3);
        this.check();
    }
}

