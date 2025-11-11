package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWwindowrefreshfun")
public interface GLFWWindowRefreshCallbackI extends CallbackI.V {
   String SIGNATURE = "(p)v";

   @Override
   default String getSignature() {
      return "(p)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args));
   }

   void invoke(@NativeType("GLFWwindow *") long var1);
}
