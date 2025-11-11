package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWmonitorfun")
public interface GLFWMonitorCallbackI extends CallbackI.V {
   String SIGNATURE = "(pi)v";

   @Override
   default String getSignature() {
      return "(pi)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args));
   }

   void invoke(@NativeType("GLFWmonitor *") long var1, int var3);
}
