package org.lwjgl.openal;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

public class EXTThreadLocalContext {
   protected EXTThreadLocalContext() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCCapabilities caps) {
      return Checks.checkFunctions(caps.alcSetThreadContext, caps.alcGetThreadContext);
   }

   @NativeType("ALCboolean")
   public static boolean alcSetThreadContext(@NativeType("ALCcontext *") long context) {
      long __functionAddress = ALC.getICD().alcSetThreadContext;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      return JNI.invokePZ(context, __functionAddress);
   }

   @NativeType("ALCcontext *")
   public static long alcGetThreadContext() {
      long __functionAddress = ALC.getICD().alcGetThreadContext;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      return JNI.invokeP(__functionAddress);
   }
}
