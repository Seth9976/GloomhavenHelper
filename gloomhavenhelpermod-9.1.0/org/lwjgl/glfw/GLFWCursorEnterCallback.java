package org.lwjgl.glfw;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class GLFWCursorEnterCallback extends Callback implements GLFWCursorEnterCallbackI {
   public static GLFWCursorEnterCallback create(long functionPointer) {
      GLFWCursorEnterCallbackI instance = (GLFWCursorEnterCallbackI)Callback.get(functionPointer);
      return (GLFWCursorEnterCallback)(instance instanceof GLFWCursorEnterCallback
         ? (GLFWCursorEnterCallback)instance
         : new GLFWCursorEnterCallback.Container(functionPointer, instance));
   }

   @Nullable
   public static GLFWCursorEnterCallback createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static GLFWCursorEnterCallback create(GLFWCursorEnterCallbackI instance) {
      return (GLFWCursorEnterCallback)(instance instanceof GLFWCursorEnterCallback
         ? (GLFWCursorEnterCallback)instance
         : new GLFWCursorEnterCallback.Container(instance.address(), instance));
   }

   protected GLFWCursorEnterCallback() {
      super("(pi)v");
   }

   GLFWCursorEnterCallback(long functionPointer) {
      super(functionPointer);
   }

   public GLFWCursorEnterCallback set(long window) {
      GLFW.glfwSetCursorEnterCallback(window, this);
      return this;
   }

   private static final class Container extends GLFWCursorEnterCallback {
      private final GLFWCursorEnterCallbackI delegate;

      Container(long functionPointer, GLFWCursorEnterCallbackI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long window, boolean entered) {
         this.delegate.invoke(window, entered);
      }
   }
}
