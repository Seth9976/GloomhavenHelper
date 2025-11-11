package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWwindowsizefun")
public interface GLFWWindowSizeCallbackI extends CallbackI.V {
   String SIGNATURE = "(pii)v";

   @Override
   default String getSignature() {
      return "(pii)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args), DynCallback.dcbArgInt(args));
   }

   void invoke(@NativeType("GLFWwindow *") long var1, int var3, int var4);
}
