package org.lwjgl.system.jemalloc;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class ExtentSplit extends Callback implements ExtentSplitI {
   public static ExtentSplit create(long functionPointer) {
      ExtentSplitI instance = (ExtentSplitI)Callback.get(functionPointer);
      return (ExtentSplit)(instance instanceof ExtentSplit ? (ExtentSplit)instance : new ExtentSplit.Container(functionPointer, instance));
   }

   @Nullable
   public static ExtentSplit createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static ExtentSplit create(ExtentSplitI instance) {
      return (ExtentSplit)(instance instanceof ExtentSplit ? (ExtentSplit)instance : new ExtentSplit.Container(instance.address(), instance));
   }

   protected ExtentSplit() {
      super("(pppppBi)B");
   }

   ExtentSplit(long functionPointer) {
      super(functionPointer);
   }

   private static final class Container extends ExtentSplit {
      private final ExtentSplitI delegate;

      Container(long functionPointer, ExtentSplitI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public boolean invoke(long extent_hooks, long addr, long size, long size_a, long size_b, boolean committed, int arena_ind) {
         return this.delegate.invoke(extent_hooks, addr, size, size_a, size_b, committed, arena_ind);
      }
   }
}
