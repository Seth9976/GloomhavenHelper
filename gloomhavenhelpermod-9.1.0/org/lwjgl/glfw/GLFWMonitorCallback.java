package org.lwjgl.glfw;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class GLFWMonitorCallback extends Callback implements GLFWMonitorCallbackI {
   public static GLFWMonitorCallback create(long functionPointer) {
      GLFWMonitorCallbackI instance = (GLFWMonitorCallbackI)Callback.get(functionPointer);
      return (GLFWMonitorCallback)(instance instanceof GLFWMonitorCallback
         ? (GLFWMonitorCallback)instance
         : new GLFWMonitorCallback.Container(functionPointer, instance));
   }

   @Nullable
   public static GLFWMonitorCallback createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static GLFWMonitorCallback create(GLFWMonitorCallbackI instance) {
      return (GLFWMonitorCallback)(instance instanceof GLFWMonitorCallback
         ? (GLFWMonitorCallback)instance
         : new GLFWMonitorCallback.Container(instance.address(), instance));
   }

   protected GLFWMonitorCallback() {
      super("(pi)v");
   }

   GLFWMonitorCallback(long functionPointer) {
      super(functionPointer);
   }

   public GLFWMonitorCallback set() {
      GLFW.glfwSetMonitorCallback(this);
      return this;
   }

   private static final class Container extends GLFWMonitorCallback {
      private final GLFWMonitorCallbackI delegate;

      Container(long functionPointer, GLFWMonitorCallbackI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long monitor, int event) {
         this.delegate.invoke(monitor, event);
      }
   }
}
