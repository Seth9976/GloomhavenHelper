package com.badlogic.gdx.backends.android;

import android.annotation.TargetApi;
import android.opengl.GLES30;
import com.badlogic.gdx.graphics.GL30;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

@TargetApi(18)
public class AndroidGL30 extends AndroidGL20 implements GL30 {
    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBeginQuery(int v, int v1) {
        GLES30.glBeginQuery(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBeginTransformFeedback(int v) {
        GLES30.glBeginTransformFeedback(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindBufferBase(int v, int v1, int v2) {
        GLES30.glBindBufferBase(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindBufferRange(int v, int v1, int v2, int v3, int v4) {
        GLES30.glBindBufferRange(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindSampler(int v, int v1) {
        GLES30.glBindSampler(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindTransformFeedback(int v, int v1) {
        GLES30.glBindTransformFeedback(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBindVertexArray(int v) {
        GLES30.glBindVertexArray(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glBlitFramebuffer(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        GLES30.glBlitFramebuffer(v, v1, v2, v3, v4, v5, v6, v7, v8, v9);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfi(int v, int v1, float f, int v2) {
        GLES30.glClearBufferfi(v, v1, f, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferfv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES30.glClearBufferfv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glClearBufferiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glClearBufferuiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glClearBufferuiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glCopyBufferSubData(int v, int v1, int v2, int v3, int v4) {
        GLES30.glCopyBufferSubData(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glCopyTexSubImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8) {
        GLES30.glCopyTexSubImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int v, IntBuffer intBuffer0) {
        GLES30.glDeleteQueries(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteQueries(int v, int[] arr_v, int v1) {
        GLES30.glDeleteQueries(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int v, IntBuffer intBuffer0) {
        GLES30.glDeleteSamplers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteSamplers(int v, int[] arr_v, int v1) {
        GLES30.glDeleteSamplers(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int v, IntBuffer intBuffer0) {
        GLES30.glDeleteTransformFeedbacks(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteTransformFeedbacks(int v, int[] arr_v, int v1) {
        GLES30.glDeleteTransformFeedbacks(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int v, IntBuffer intBuffer0) {
        GLES30.glDeleteVertexArrays(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDeleteVertexArrays(int v, int[] arr_v, int v1) {
        GLES30.glDeleteVertexArrays(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawArraysInstanced(int v, int v1, int v2, int v3) {
        GLES30.glDrawArraysInstanced(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawBuffers(int v, IntBuffer intBuffer0) {
        GLES30.glDrawBuffers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawElementsInstanced(int v, int v1, int v2, int v3, int v4) {
        GLES30.glDrawElementsInstanced(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int v, int v1, int v2, int v3, int v4, int v5) {
        GLES30.glDrawRangeElements(v, v1, v2, v3, v4, v5);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glDrawRangeElements(int v, int v1, int v2, int v3, int v4, Buffer buffer0) {
        GLES30.glDrawRangeElements(v, v1, v2, v3, v4, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glEndQuery(int v) {
        GLES30.glEndQuery(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glEndTransformFeedback() {
        GLES30.glEndTransformFeedback();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glFlushMappedBufferRange(int v, int v1, int v2) {
        GLES30.glFlushMappedBufferRange(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glFramebufferTextureLayer(int v, int v1, int v2, int v3, int v4) {
        GLES30.glFramebufferTextureLayer(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int v, IntBuffer intBuffer0) {
        GLES30.glGenQueries(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenQueries(int v, int[] arr_v, int v1) {
        GLES30.glGenQueries(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int v, IntBuffer intBuffer0) {
        GLES30.glGenSamplers(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenSamplers(int v, int[] arr_v, int v1) {
        GLES30.glGenSamplers(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int v, IntBuffer intBuffer0) {
        GLES30.glGenTransformFeedbacks(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenTransformFeedbacks(int v, int[] arr_v, int v1) {
        GLES30.glGenTransformFeedbacks(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int v, IntBuffer intBuffer0) {
        GLES30.glGenVertexArrays(v, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGenVertexArrays(int v, int[] arr_v, int v1) {
        GLES30.glGenVertexArrays(v, arr_v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public String glGetActiveUniformBlockName(int v, int v1) {
        return GLES30.glGetActiveUniformBlockName(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockName(int v, int v1, Buffer buffer0, Buffer buffer1) {
        GLES30.glGetActiveUniformBlockName(v, v1, buffer0, buffer1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformBlockiv(int v, int v1, int v2, IntBuffer intBuffer0) {
        GLES30.glGetActiveUniformBlockiv(v, v1, v2, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetActiveUniformsiv(int v, int v1, IntBuffer intBuffer0, int v2, IntBuffer intBuffer1) {
        GLES30.glGetActiveUniformsiv(v, v1, intBuffer0, v2, intBuffer1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetBufferParameteri64v(int v, int v1, LongBuffer longBuffer0) {
        GLES30.glGetBufferParameteri64v(v, v1, longBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public Buffer glGetBufferPointerv(int v, int v1) {
        return GLES30.glGetBufferPointerv(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public int glGetFragDataLocation(int v, String s) {
        return GLES30.glGetFragDataLocation(v, s);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetInteger64v(int v, LongBuffer longBuffer0) {
        GLES30.glGetInteger64v(v, longBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetQueryObjectuiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glGetQueryObjectuiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetQueryiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glGetQueryiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES30.glGetSamplerParameterfv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetSamplerParameteriv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glGetSamplerParameteriv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public String glGetStringi(int v, int v1) {
        return GLES30.glGetStringi(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public int glGetUniformBlockIndex(int v, String s) {
        return GLES30.glGetUniformBlockIndex(v, s);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetUniformIndices(int v, String[] arr_s, IntBuffer intBuffer0) {
        GLES30.glGetUniformIndices(v, arr_s, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetUniformuiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glGetUniformuiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glGetVertexAttribIiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glGetVertexAttribIuiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glGetVertexAttribIuiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glInvalidateFramebuffer(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glInvalidateFramebuffer(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glInvalidateSubFramebuffer(int v, int v1, IntBuffer intBuffer0, int v2, int v3, int v4, int v5) {
        GLES30.glInvalidateSubFramebuffer(v, v1, intBuffer0, v2, v3, v4, v5);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsQuery(int v) {
        return GLES30.glIsQuery(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsSampler(int v) {
        return GLES30.glIsSampler(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsTransformFeedback(int v) {
        return GLES30.glIsTransformFeedback(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glIsVertexArray(int v) {
        return GLES30.glIsVertexArray(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public Buffer glMapBufferRange(int v, int v1, int v2, int v3) {
        return GLES30.glMapBufferRange(v, v1, v2, v3);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glPauseTransformFeedback() {
        GLES30.glPauseTransformFeedback();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glProgramParameteri(int v, int v1, int v2) {
        GLES30.glProgramParameteri(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glReadBuffer(int v) {
        GLES30.glReadBuffer(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glRenderbufferStorageMultisample(int v, int v1, int v2, int v3, int v4) {
        GLES30.glRenderbufferStorageMultisample(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glResumeTransformFeedback() {
        GLES30.glResumeTransformFeedback();
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterf(int v, int v1, float f) {
        GLES30.glSamplerParameterf(v, v1, f);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameterfv(int v, int v1, FloatBuffer floatBuffer0) {
        GLES30.glSamplerParameterfv(v, v1, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteri(int v, int v1, int v2) {
        GLES30.glSamplerParameteri(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glSamplerParameteriv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glSamplerParameteriv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        GLES30.glTexImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, v9);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, Buffer buffer0) {
        if(buffer0 == null) {
            GLES30.glTexImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, 0);
            return;
        }
        GLES30.glTexImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9, int v10) {
        GLES30.glTexSubImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTexSubImage3D(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9, Buffer buffer0) {
        GLES30.glTexSubImage3D(v, v1, v2, v3, v4, v5, v6, v7, v8, v9, buffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glTransformFeedbackVaryings(int v, String[] arr_s, int v1) {
        GLES30.glTransformFeedbackVaryings(v, arr_s, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniform1uiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glUniform1uiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniform3uiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glUniform3uiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniform4uiv(int v, int v1, IntBuffer intBuffer0) {
        GLES30.glUniform4uiv(v, v1, intBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformBlockBinding(int v, int v1, int v2) {
        GLES30.glUniformBlockBinding(v, v1, v2);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x3fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES30.glUniformMatrix2x3fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix2x4fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES30.glUniformMatrix2x4fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x2fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES30.glUniformMatrix3x2fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix3x4fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES30.glUniformMatrix3x4fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x2fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES30.glUniformMatrix4x2fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glUniformMatrix4x3fv(int v, int v1, boolean z, FloatBuffer floatBuffer0) {
        GLES30.glUniformMatrix4x3fv(v, v1, z, floatBuffer0);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public boolean glUnmapBuffer(int v) {
        return GLES30.glUnmapBuffer(v);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribDivisor(int v, int v1) {
        GLES30.glVertexAttribDivisor(v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4i(int v, int v1, int v2, int v3, int v4) {
        GLES30.glVertexAttribI4i(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribI4ui(int v, int v1, int v2, int v3, int v4) {
        GLES30.glVertexAttribI4ui(v, v1, v2, v3, v4);
    }

    @Override  // com.badlogic.gdx.graphics.GL30
    public void glVertexAttribIPointer(int v, int v1, int v2, int v3, int v4) {
        GLES30.glVertexAttribIPointer(v, v1, v2, v3, v4);
    }
}

