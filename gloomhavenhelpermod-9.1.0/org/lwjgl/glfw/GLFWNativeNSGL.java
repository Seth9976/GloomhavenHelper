package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

public class GLFWNativeNSGL {
   protected GLFWNativeNSGL() {
      throw new UnsupportedOperationException();
   }

   @NativeType("id")
   public static long glfwGetNSGLContext(@NativeType("GLFWwindow *") long window) {
      long __functionAddress = GLFWNativeNSGL.Functions.GetNSGLContext;
      if (Checks.CHECKS) {
         Checks.check(window);
      }

      return JNI.invokePP(window, __functionAddress);
   }

   public static final class Functions {
      public static final long GetNSGLContext = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetNSGLContext");

      private Functions() {
      }
   }
}
