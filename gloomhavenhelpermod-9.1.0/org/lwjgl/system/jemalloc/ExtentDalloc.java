package org.lwjgl.system.jemalloc;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class ExtentDalloc extends Callback implements ExtentDallocI {
   public static ExtentDalloc create(long functionPointer) {
      ExtentDallocI instance = (ExtentDallocI)Callback.get(functionPointer);
      return (ExtentDalloc)(instance instanceof ExtentDalloc ? (ExtentDalloc)instance : new ExtentDalloc.Container(functionPointer, instance));
   }

   @Nullable
   public static ExtentDalloc createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static ExtentDalloc create(ExtentDallocI instance) {
      return (ExtentDalloc)(instance instanceof ExtentDalloc ? (ExtentDalloc)instance : new ExtentDalloc.Container(instance.address(), instance));
   }

   protected ExtentDalloc() {
      super("(pppBi)B");
   }

   ExtentDalloc(long functionPointer) {
      super(functionPointer);
   }

   private static final class Container extends ExtentDalloc {
      private final ExtentDallocI delegate;

      Container(long functionPointer, ExtentDallocI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public boolean invoke(long extent_hooks, long addr, long size, boolean committed, int arena_ind) {
         return this.delegate.invoke(extent_hooks, addr, size, committed, arena_ind);
      }
   }
}
