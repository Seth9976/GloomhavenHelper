package org.lwjgl.opengl;

import org.lwjgl.system.Callback;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLDEBUGPROCARB")
public interface GLDebugMessageARBCallbackI extends CallbackI.V {
   String SIGNATURE = Callback.__stdcall("(iiiiipp)v");

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
         DynCallback.dcbArgInt(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args)
      );
   }

   void invoke(
      @NativeType("GLenum") int var1,
      @NativeType("GLenum") int var2,
      @NativeType("GLuint") int var3,
      @NativeType("GLenum") int var4,
      @NativeType("GLsizei") int var5,
      @NativeType("GLchar const *") long var6,
      @NativeType("void const *") long var8
   );
}
