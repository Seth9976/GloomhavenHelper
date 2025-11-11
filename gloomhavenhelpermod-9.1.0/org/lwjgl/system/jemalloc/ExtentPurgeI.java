package org.lwjgl.system.jemalloc;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("extent_purge_t")
public interface ExtentPurgeI extends CallbackI.Z {
   String SIGNATURE = "(pppppi)B";

   @Override
   default String getSignature() {
      return "(pppppi)B";
   }

   @Override
   default boolean callback(long args) {
      return this.invoke(
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgInt(args)
      );
   }

   @NativeType("bool")
   boolean invoke(
      @NativeType("extent_hooks_t *") long var1,
      @NativeType("void *") long var3,
      @NativeType("size_t") long var5,
      @NativeType("size_t") long var7,
      @NativeType("size_t") long var9,
      @NativeType("unsigned int") int var11
   );
}
