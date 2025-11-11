package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWdropfun")
public interface GLFWDropCallbackI extends CallbackI.V {
   String SIGNATURE = "(pip)v";

   @Override
   default String getSignature() {
      return "(pip)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args), DynCallback.dcbArgPointer(args));
   }

   void invoke(@NativeType("GLFWwindow *") long var1, int var3, @NativeType("char const **") long var4);
}
