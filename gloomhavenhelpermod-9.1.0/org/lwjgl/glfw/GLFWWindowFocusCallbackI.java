package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWwindowfocusfun")
public interface GLFWWindowFocusCallbackI extends CallbackI.V {
   String SIGNATURE = "(pi)v";

   @Override
   default String getSignature() {
      return "(pi)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args) != 0);
   }

   void invoke(@NativeType("GLFWwindow *") long var1, @NativeType("int") boolean var3);
}
