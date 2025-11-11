package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

public class GLXSGIMakeCurrentRead {
   protected GLXSGIMakeCurrentRead() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLXCapabilities caps) {
      return Checks.checkFunctions(caps.glXMakeCurrentReadSGI, caps.glXGetCurrentReadDrawableSGI);
   }

   @NativeType("Bool")
   public static boolean glXMakeCurrentReadSGI(
      @NativeType("Display *") long display, @NativeType("GLXDrawable") long draw, @NativeType("GLXDrawable") long read, @NativeType("GLXContext") long ctx
   ) {
      long __functionAddress = GL.getCapabilitiesGLXClient().glXMakeCurrentReadSGI;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(display);
      }

      return JNI.callPPPPI(display, draw, read, ctx, __functionAddress) != 0;
   }

   @NativeType("GLXDrawable")
   public static long glXGetCurrentReadDrawableSGI() {
      long __functionAddress = GL.getCapabilitiesGLXClient().glXGetCurrentReadDrawableSGI;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      return JNI.callP(__functionAddress);
   }
}
