package org.lwjgl.opengl;

import java.util.Set;
import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class OVRMultiview {
   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_NUM_VIEWS_OVR = 38448;
   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_BASE_VIEW_INDEX_OVR = 38450;
   public static final int GL_MAX_VIEWS_OVR = 38449;
   public static final int GL_FRAMEBUFFER_INCOMPLETE_VIEW_TARGETS_OVR = 38451;

   protected OVRMultiview() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps, Set ext) {
      return Checks.checkFunctions(caps.glFramebufferTextureMultiviewOVR, caps.hasDSA(ext) ? caps.glNamedFramebufferTextureMultiviewOVR : -1L);
   }

   public static native void glFramebufferTextureMultiviewOVR(
      @NativeType("GLenum") int var0,
      @NativeType("GLenum") int var1,
      @NativeType("GLuint") int var2,
      @NativeType("GLint") int var3,
      @NativeType("GLint") int var4,
      @NativeType("GLsizei") int var5
   );

   public static native void glNamedFramebufferTextureMultiviewOVR(
      @NativeType("GLuint") int var0,
      @NativeType("GLenum") int var1,
      @NativeType("GLuint") int var2,
      @NativeType("GLint") int var3,
      @NativeType("GLint") int var4,
      @NativeType("GLsizei") int var5
   );

   static {
      GL.initialize();
   }
}
