package org.lwjgl.glfw;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class GLFWScrollCallback extends Callback implements GLFWScrollCallbackI {
   public static GLFWScrollCallback create(long functionPointer) {
      GLFWScrollCallbackI instance = (GLFWScrollCallbackI)Callback.get(functionPointer);
      return (GLFWScrollCallback)(instance instanceof GLFWScrollCallback
         ? (GLFWScrollCallback)instance
         : new GLFWScrollCallback.Container(functionPointer, instance));
   }

   @Nullable
   public static GLFWScrollCallback createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static GLFWScrollCallback create(GLFWScrollCallbackI instance) {
      return (GLFWScrollCallback)(instance instanceof GLFWScrollCallback
         ? (GLFWScrollCallback)instance
         : new GLFWScrollCallback.Container(instance.address(), instance));
   }

   protected GLFWScrollCallback() {
      super("(pdd)v");
   }

   GLFWScrollCallback(long functionPointer) {
      super(functionPointer);
   }

   public GLFWScrollCallback set(long window) {
      GLFW.glfwSetScrollCallback(window, this);
      return this;
   }

   private static final class Container extends GLFWScrollCallback {
      private final GLFWScrollCallbackI delegate;

      Container(long functionPointer, GLFWScrollCallbackI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long window, double xoffset, double yoffset) {
         this.delegate.invoke(window, xoffset, yoffset);
      }
   }
}
