package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

public class GLFWNativeGLX {
   protected GLFWNativeGLX() {
      throw new UnsupportedOperationException();
   }

   @NativeType("GLXContext")
   public static long glfwGetGLXContext(@NativeType("GLFWwindow *") long window) {
      long __functionAddress = GLFWNativeGLX.Functions.GetGLXContext;
      if (Checks.CHECKS) {
         Checks.check(window);
      }

      return JNI.invokePP(window, __functionAddress);
   }

   @NativeType("GLXWindow")
   public static long glfwGetGLXWindow(@NativeType("GLFWwindow *") long window) {
      long __functionAddress = GLFWNativeGLX.Functions.GetGLXWindow;
      if (Checks.CHECKS) {
         Checks.check(window);
      }

      return JNI.invokePP(window, __functionAddress);
   }

   public static final class Functions {
      public static final long GetGLXContext = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetGLXContext");
      public static final long GetGLXWindow = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetGLXWindow");

      private Functions() {
      }
   }
}
