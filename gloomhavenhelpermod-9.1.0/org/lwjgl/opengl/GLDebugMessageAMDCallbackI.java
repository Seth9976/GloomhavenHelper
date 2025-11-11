package org.lwjgl.opengl;

import org.lwjgl.system.Callback;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLDEBUGPROCAMD")
public interface GLDebugMessageAMDCallbackI extends CallbackI.V {
   String SIGNATURE = Callback.__stdcall("(iiiipp)v");

   @Override
   default String getSignature() {
      return SIGNATURE;
   }

   @Override
   default void callback(long args) {
      this.invoke(
         DynCallback.dcbArgInt(args),
         DynCallback.dcbArgInt(args),
         DynCallback.dcbArgInt(args),
         DynCallback.dcbArgInt(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args)
      );
   }

   void invoke(
      @NativeType("GLuint") int var1,
      @NativeType("GLenum") int var2,
      @NativeType("GLenum") int var3,
      @NativeType("GLsizei") int var4,
      @NativeType("GLchar const *") long var5,
      @NativeType("void *") long var7
   );
}
