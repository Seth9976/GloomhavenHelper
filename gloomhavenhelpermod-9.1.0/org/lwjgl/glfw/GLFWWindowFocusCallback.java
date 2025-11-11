package org.lwjgl.glfw;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class GLFWWindowFocusCallback extends Callback implements GLFWWindowFocusCallbackI {
   public static GLFWWindowFocusCallback create(long functionPointer) {
      GLFWWindowFocusCallbackI instance = (GLFWWindowFocusCallbackI)Callback.get(functionPointer);
      return (GLFWWindowFocusCallback)(instance instanceof GLFWWindowFocusCallback
         ? (GLFWWindowFocusCallback)instance
         : new GLFWWindowFocusCallback.Container(functionPointer, instance));
   }

   @Nullable
   public static GLFWWindowFocusCallback createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static GLFWWindowFocusCallback create(GLFWWindowFocusCallbackI instance) {
      return (GLFWWindowFocusCallback)(instance instanceof GLFWWindowFocusCallback
         ? (GLFWWindowFocusCallback)instance
         : new GLFWWindowFocusCallback.Container(instance.address(), instance));
   }

   protected GLFWWindowFocusCallback() {
      super("(pi)v");
   }

   GLFWWindowFocusCallback(long functionPointer) {
      super(functionPointer);
   }

   public GLFWWindowFocusCallback set(long window) {
      GLFW.glfwSetWindowFocusCallback(window, this);
      return this;
   }

   private static final class Container extends GLFWWindowFocusCallback {
      private final GLFWWindowFocusCallbackI delegate;

      Container(long functionPointer, GLFWWindowFocusCallbackI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long window, boolean focused) {
         this.delegate.invoke(window, focused);
      }
   }
}
