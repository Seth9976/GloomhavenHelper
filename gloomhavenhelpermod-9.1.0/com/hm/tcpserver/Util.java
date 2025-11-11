package com.hm.tcpserver;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
   public static void closeQuietly(Closeable closeable) {
      if (closeable != null) {
         try {
            closeable.close();
         } catch (Throwable var2) {
         }
      }
   }

   public static void writeVarint(int value, OutputStream output) throws IOException {
      if (value >>> 7 == 0) {
         output.write(value);
      } else if (value >>> 14 == 0) {
         output.write(value & 127 | 128);
         output.write(value >>> 7);
      } else if (value >>> 21 == 0) {
         output.write(value & 127 | 128);
         output.write(value >>> 7 | 128);
         output.write(value >>> 14);
      } else if (value >>> 28 == 0) {
         output.write(value & 127 | 128);
         output.write(value >>> 7 | 128);
         output.write(value >>> 14 | 128);
         output.write(value >>> 21);
      } else {
         output.write(value & 127 | 128);
         output.write(value >>> 7 | 128);
         output.write(value >>> 14 | 128);
         output.write(value >>> 21 | 128);
         output.write(value >>> 28);
      }
   }

   public static int readVarint(InputStream input) throws IOException {
      int b = input.read();
      int result = b & 127;
      if ((b & 128) == 0) {
         return result;
      } else {
         b = input.read();
         result |= (b & 127) << 7;
         if ((b & 128) == 0) {
            return result;
         } else {
            b = input.read();
            result |= (b & 127) << 14;
            if ((b & 128) == 0) {
               return result;
            } else {
               b = input.read();
               result |= (b & 127) << 21;
               if ((b & 128) == 0) {
                  return result;
               } else {
                  b = input.read();
                  return result | (b & 127) << 28;
               }
            }
         }
      }
   }
}
