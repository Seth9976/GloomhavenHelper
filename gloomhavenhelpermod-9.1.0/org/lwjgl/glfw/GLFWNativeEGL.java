package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

public class GLFWNativeEGL {
   protected GLFWNativeEGL() {
      throw new UnsupportedOperationException();
   }

   @NativeType("EGLDisplay")
   public static long glfwGetEGLDisplay() {
      long __functionAddress = GLFWNativeEGL.Functions.GetEGLDisplay;
      return JNI.invokeP(__functionAddress);
   }

   @NativeType("EGLContext")
   public static long glfwGetEGLContext(@NativeType("GLFWwindow *") long window) {
      long __functionAddress = GLFWNativeEGL.Functions.GetEGLContext;
      if (Checks.CHECKS) {
         Checks.check(window);
      }

      return JNI.invokePP(window, __functionAddress);
   }

   @NativeType("EGLSurface")
   public static long glfwGetEGLSurface(@NativeType("GLFWwindow *") long window) {
      long __functionAddress = GLFWNativeEGL.Functions.GetEGLSurface;
      if (Checks.CHECKS) {
         Checks.check(window);
      }

      return JNI.invokePP(window, __functionAddress);
   }

   public static final class Functions {
      public static final long GetEGLDisplay = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetEGLDisplay");
      public static final long GetEGLContext = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetEGLContext");
      public static final long GetEGLSurface = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetEGLSurface");

      private Functions() {
      }
   }
}
