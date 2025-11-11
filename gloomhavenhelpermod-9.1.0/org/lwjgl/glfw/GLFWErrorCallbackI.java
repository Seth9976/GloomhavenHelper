package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWerrorfun")
public interface GLFWErrorCallbackI extends CallbackI.V {
   String SIGNATURE = "(ip)v";

   @Override
   default String getSignature() {
      return "(ip)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgInt(args), DynCallback.dcbArgPointer(args));
   }

   void invoke(int var1, @NativeType("char *") long var2);
}
