package org.lwjgl.glfw;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Pointer;

public abstract class GLFWDropCallback extends Callback implements GLFWDropCallbackI {
   public static GLFWDropCallback create(long functionPointer) {
      GLFWDropCallbackI instance = (GLFWDropCallbackI)Callback.get(functionPointer);
      return (GLFWDropCallback)(instance instanceof GLFWDropCallback ? (GLFWDropCallback)instance : new GLFWDropCallback.Container(functionPointer, instance));
   }

   @Nullable
   public static GLFWDropCallback createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static GLFWDropCallback create(GLFWDropCallbackI instance) {
      return (GLFWDropCallback)(instance instanceof GLFWDropCallback
         ? (GLFWDropCallback)instance
         : new GLFWDropCallback.Container(instance.address(), instance));
   }

   protected GLFWDropCallback() {
      super("(pip)v");
   }

   GLFWDropCallback(long functionPointer) {
      super(functionPointer);
   }

   public static String getName(long names, int index) {
      return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(names + Pointer.POINTER_SIZE * index));
   }

   public GLFWDropCallback set(long window) {
      GLFW.glfwSetDropCallback(window, this);
      return this;
   }

   private static final class Container extends GLFWDropCallback {
      private final GLFWDropCallbackI delegate;

      Container(long functionPointer, GLFWDropCallbackI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long window, int count, long names) {
         this.delegate.invoke(window, count, names);
      }
   }
}
