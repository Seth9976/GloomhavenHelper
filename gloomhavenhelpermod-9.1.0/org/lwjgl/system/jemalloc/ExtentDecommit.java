package org.lwjgl.system.jemalloc;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class ExtentDecommit extends Callback implements ExtentDecommitI {
   public static ExtentDecommit create(long functionPointer) {
      ExtentDecommitI instance = (ExtentDecommitI)Callback.get(functionPointer);
      return (ExtentDecommit)(instance instanceof ExtentDecommit ? (ExtentDecommit)instance : new ExtentDecommit.Container(functionPointer, instance));
   }

   @Nullable
   public static ExtentDecommit createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static ExtentDecommit create(ExtentDecommitI instance) {
      return (ExtentDecommit)(instance instanceof ExtentDecommit ? (ExtentDecommit)instance : new ExtentDecommit.Container(instance.address(), instance));
   }

   protected ExtentDecommit() {
      super("(pppppi)B");
   }

   ExtentDecommit(long functionPointer) {
      super(functionPointer);
   }

   private static final class Container extends ExtentDecommit {
      private final ExtentDecommitI delegate;

      Container(long functionPointer, ExtentDecommitI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public boolean invoke(long extent_hooks, long addr, long size, long offset, long length, int arena_ind) {
         return this.delegate.invoke(extent_hooks, addr, size, offset, length, arena_ind);
      }
   }
}
