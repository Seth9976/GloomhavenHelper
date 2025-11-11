package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.lwjgl.system.MemoryUtil;

public final class ALUtil {
   private ALUtil() {
   }

   @Nullable
   public static List getStringList(long deviceHandle, int token) {
      long __result = ALC10.nalcGetString(deviceHandle, token);
      if (__result == 0L) {
         return null;
      } else {
         ByteBuffer buffer = MemoryUtil.memByteBuffer(__result, Integer.MAX_VALUE);
         List strings = new ArrayList();
         int offset = 0;

         while (true) {
            while (buffer.get() != 0) {
            }

            int limit = buffer.position() - 1;
            if (limit == offset) {
               return strings;
            }

            strings.add(MemoryUtil.memUTF8(buffer, limit - offset, offset));
            offset = buffer.position();
         }
      }
   }
}
