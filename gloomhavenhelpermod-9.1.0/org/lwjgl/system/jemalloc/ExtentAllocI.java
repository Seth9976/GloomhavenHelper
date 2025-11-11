package org.lwjgl.system.jemalloc;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("extent_alloc_t")
public interface ExtentAllocI extends CallbackI.P {
   String SIGNATURE = "(ppppppi)p";

   @Override
   default String getSignature() {
      return "(ppppppi)p";
   }

   @Override
   default long callback(long args) {
      return this.invoke(
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgInt(args)
      );
   }

   @NativeType("void *")
   long invoke(
      @NativeType("extent_hooks_t *") long var1,
      @NativeType("void *") long var3,
      @NativeType("size_t") long var5,
      @NativeType("size_t") long var7,
      @NativeType("bool *") long var9,
      @NativeType("bool *") long var11,
      @NativeType("unsigned int") int var13
   );
}
