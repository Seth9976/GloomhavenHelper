package org.lwjgl.system;

import javax.annotation.Nullable;
import org.lwjgl.system.dyncall.DynLoad;

public interface SharedLibrary extends FunctionProvider, NativeResource, Pointer {
   String getName();

   @Nullable
   String getPath();

   public abstract static class Default extends Pointer.Default implements SharedLibrary {
      private final String name;

      protected Default(String name, long handle) {
         super(handle);
         this.name = name;
      }

      @Override
      public String getName() {
         return this.name;
      }

      @Nullable
      @Override
      public String getPath() {
         String path = DynLoad.dlGetLibraryPath(this.address(), 256);
         return path.isEmpty() ? null : path;
      }
   }

   public abstract static class Delegate implements SharedLibrary {
      protected final SharedLibrary library;

      protected Delegate(SharedLibrary library) {
         this.library = library;
      }

      @Override
      public String getName() {
         return this.library.getName();
      }

      @Nullable
      @Override
      public String getPath() {
         return this.library.getPath();
      }

      @Override
      public long address() {
         return this.library.address();
      }

      @Override
      public void free() {
         this.library.free();
      }
   }
}
