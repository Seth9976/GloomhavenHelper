package org.lwjgl.glfw;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class GLFWCharModsCallback extends Callback implements GLFWCharModsCallbackI {
   public static GLFWCharModsCallback create(long functionPointer) {
      GLFWCharModsCallbackI instance = (GLFWCharModsCallbackI)Callback.get(functionPointer);
      return (GLFWCharModsCallback)(instance instanceof GLFWCharModsCallback
         ? (GLFWCharModsCallback)instance
         : new GLFWCharModsCallback.Container(functionPointer, instance));
   }

   @Nullable
   public static GLFWCharModsCallback createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static GLFWCharModsCallback create(GLFWCharModsCallbackI instance) {
      return (GLFWCharModsCallback)(instance instanceof GLFWCharModsCallback
         ? (GLFWCharModsCallback)instance
         : new GLFWCharModsCallback.Container(instance.address(), instance));
   }

   protected GLFWCharModsCallback() {
      super("(pii)v");
   }

   GLFWCharModsCallback(long functionPointer) {
      super(functionPointer);
   }

   public GLFWCharModsCallback set(long window) {
      GLFW.glfwSetCharModsCallback(window, this);
      return this;
   }

   private static final class Container extends GLFWCharModsCallback {
      private final GLFWCharModsCallbackI delegate;

      Container(long functionPointer, GLFWCharModsCallbackI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long window, int codepoint, int mods) {
         this.delegate.invoke(window, codepoint, mods);
      }
   }
}
