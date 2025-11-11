package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class EXTX11SyncObject {
   public static final int GL_SYNC_X11_FENCE_EXT = 37089;

   protected EXTX11SyncObject() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glImportSyncEXT);
   }

   @NativeType("GLsync")
   public static native long glImportSyncEXT(@NativeType("GLenum") int var0, @NativeType("GLintptr") long var1, @NativeType("GLbitfield") int var3);

   static {
      GL.initialize();
   }
}
