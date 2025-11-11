package org.lwjgl.openal;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

public class SOFTPauseDevice {
   protected SOFTPauseDevice() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCCapabilities caps) {
      return Checks.checkFunctions(caps.alcDevicePauseSOFT, caps.alcDeviceResumeSOFT);
   }

   @NativeType("ALCvoid")
   public static void alcDevicePauseSOFT(@NativeType("ALCdevice *") long device) {
      long __functionAddress = ALC.getICD().alcDevicePauseSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePV(device, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcDeviceResumeSOFT(@NativeType("ALCdevice *") long device) {
      long __functionAddress = ALC.getICD().alcDeviceResumeSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePV(device, __functionAddress);
   }
}
