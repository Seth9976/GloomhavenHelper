package com.hm.gloomhavenhelper.util;

import java.io.IOException;
import java.io.OutputStream;

public class Output extends OutputStream {
   protected int maxCapacity;
   protected long total;
   protected int position;
   protected int capacity;
   protected byte[] buffer;
   protected OutputStream outputStream;

   public Output() {
   }

   public Output(int bufferSize) {
      this(bufferSize, bufferSize);
   }

   public Output(int bufferSize, int maxBufferSize) {
      if (bufferSize > maxBufferSize && maxBufferSize != -1) {
         throw new IllegalArgumentException("bufferSize: " + bufferSize + " cannot be greater than maxBufferSize: " + maxBufferSize);
      } else if (maxBufferSize < -1) {
         throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + maxBufferSize);
      } else {
         this.capacity = bufferSize;
         this.maxCapacity = maxBufferSize == -1 ? 2147483639 : maxBufferSize;
         this.buffer = new byte[bufferSize];
      }
   }

   public Output(byte[] buffer) {
      this(buffer, buffer.length);
   }

   public Output(byte[] buffer, int maxBufferSize) {
      if (buffer == null) {
         throw new IllegalArgumentException("buffer cannot be null.");
      } else {
         this.setBuffer(buffer, maxBufferSize);
      }
   }

   public Output(OutputStream outputStream) {
      this(4096, 4096);
      if (outputStream == null) {
         throw new IllegalArgumentException("outputStream cannot be null.");
      } else {
         this.outputStream = outputStream;
      }
   }

   public Output(OutputStream outputStream, int bufferSize) {
      this(bufferSize, bufferSize);
      if (outputStream == null) {
         throw new IllegalArgumentException("outputStream cannot be null.");
      } else {
         this.outputStream = outputStream;
      }
   }

   public OutputStream getOutputStream() {
      return this.outputStream;
   }

   public void setOutputStream(OutputStream outputStream) {
      this.outputStream = outputStream;
      this.position = 0;
      this.total = 0L;
   }

   public void setBuffer(byte[] buffer) {
      this.setBuffer(buffer, buffer.length);
   }

   public void setBuffer(byte[] buffer, int maxBufferSize) {
      if (buffer == null) {
         throw new IllegalArgumentException("buffer cannot be null.");
      } else if (buffer.length > maxBufferSize && maxBufferSize != -1) {
         throw new IllegalArgumentException("buffer has length: " + buffer.length + " cannot be greater than maxBufferSize: " + maxBufferSize);
      } else if (maxBufferSize < -1) {
         throw new IllegalArgumentException("maxBufferSize cannot be < -1: " + maxBufferSize);
      } else {
         this.buffer = buffer;
         this.maxCapacity = maxBufferSize == -1 ? 2147483639 : maxBufferSize;
         this.capacity = buffer.length;
         this.position = 0;
         this.total = 0L;
         this.outputStream = null;
      }
   }

   public byte[] getBuffer() {
      return this.buffer;
   }

   public byte[] toBytes() {
      byte[] newBuffer = new byte[this.position];
      System.arraycopy(this.buffer, 0, newBuffer, 0, this.position);
      return newBuffer;
   }

   public int position() {
      return this.position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public long total() {
      return this.total + this.position;
   }

   public void clear() {
      this.position = 0;
      this.total = 0L;
   }

   protected boolean require(int required) {
      if (this.capacity - this.position >= required) {
         return false;
      } else if (required > this.maxCapacity) {
         throw new RuntimeException("Buffer overflow. Max capacity: " + this.maxCapacity + ", required: " + required);
      } else {
         this.flush();

         while (this.capacity - this.position < required) {
            if (this.capacity == this.maxCapacity) {
               throw new RuntimeException("Buffer overflow. Available: " + (this.capacity - this.position) + ", required: " + required);
            }

            if (this.capacity == 0) {
               this.capacity = 1;
            }

            this.capacity = Math.min(this.capacity * 2, this.maxCapacity);
            if (this.capacity < 0) {
               this.capacity = this.maxCapacity;
            }

            byte[] newBuffer = new byte[this.capacity];
            System.arraycopy(this.buffer, 0, newBuffer, 0, this.position);
            this.buffer = newBuffer;
         }

         return true;
      }
   }

   @Override
   public void flush() {
      if (this.outputStream != null) {
         try {
            this.outputStream.write(this.buffer, 0, this.position);
            this.outputStream.flush();
         } catch (IOException var2) {
            throw new RuntimeException(var2);
         }

         this.total = this.total + this.position;
         this.position = 0;
      }
   }

   @Override
   public void close() {
      this.flush();
      if (this.outputStream != null) {
         try {
            this.outputStream.close();
         } catch (IOException var2) {
         }
      }
   }

   @Override
   public void write(int value) {
      if (this.position == this.capacity) {
         this.require(1);
      }

      this.buffer[this.position++] = (byte)value;
   }

   @Override
   public void write(byte[] bytes) {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         this.writeBytes(bytes, 0, bytes.length);
      }
   }

   @Override
   public void write(byte[] bytes, int offset, int length) {
      this.writeBytes(bytes, offset, length);
   }

   public void writeByte(byte value) {
      if (this.position == this.capacity) {
         this.require(1);
      }

      this.buffer[this.position++] = value;
   }

   public void writeByte(int value) {
      if (this.position == this.capacity) {
         this.require(1);
      }

      this.buffer[this.position++] = (byte)value;
   }

   public void writeBytes(byte[] bytes) {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         this.writeBytes(bytes, 0, bytes.length);
      }
   }

   public void writeBytes(byte[] bytes, int offset, int count) {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         int copyCount = Math.min(this.capacity - this.position, count);

         while (true) {
            System.arraycopy(bytes, offset, this.buffer, this.position, copyCount);
            this.position += copyCount;
            count -= copyCount;
            if (count == 0) {
               return;
            }

            offset += copyCount;
            copyCount = Math.min(this.capacity, count);
            this.require(copyCount);
         }
      }
   }

   public void writeInt(int value) {
      this.require(4);
      byte[] buffer = this.buffer;
      buffer[this.position++] = (byte)(value >> 24);
      buffer[this.position++] = (byte)(value >> 16);
      buffer[this.position++] = (byte)(value >> 8);
      buffer[this.position++] = (byte)value;
   }

   public int writeInt(int value, boolean optimizePositive) {
      if (!optimizePositive) {
         value = value << 1 ^ value >> 31;
      }

      if (value >>> 7 == 0) {
         this.require(1);
         this.buffer[this.position++] = (byte)value;
         return 1;
      } else if (value >>> 14 == 0) {
         this.require(2);
         this.buffer[this.position++] = (byte)(value & 127 | 128);
         this.buffer[this.position++] = (byte)(value >>> 7);
         return 2;
      } else if (value >>> 21 == 0) {
         this.require(3);
         this.buffer[this.position++] = (byte)(value & 127 | 128);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128);
         this.buffer[this.position++] = (byte)(value >>> 14);
         return 3;
      } else if (value >>> 28 == 0) {
         this.require(4);
         this.buffer[this.position++] = (byte)(value & 127 | 128);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128);
         this.buffer[this.position++] = (byte)(value >>> 21);
         return 4;
      } else {
         this.require(5);
         this.buffer[this.position++] = (byte)(value & 127 | 128);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128);
         this.buffer[this.position++] = (byte)(value >>> 21 | 128);
         this.buffer[this.position++] = (byte)(value >>> 28);
         return 5;
      }
   }

   public void writeString(String value) {
      if (value == null) {
         this.writeByte(128);
      } else {
         int charCount = value.length();
         if (charCount == 0) {
            this.writeByte(129);
         } else {
            boolean ascii = false;
            if (charCount > 1 && charCount < 64) {
               ascii = true;

               for (int i = 0; i < charCount; i++) {
                  int c = value.charAt(i);
                  if (c > 127) {
                     ascii = false;
                     break;
                  }
               }
            }

            if (ascii) {
               if (this.capacity - this.position < charCount) {
                  this.writeAscii_slow(value, charCount);
               } else {
                  this.getBytes(value, 0, charCount, this.buffer, this.position);
                  this.position += charCount;
               }

               this.buffer[this.position - 1] = (byte)(this.buffer[this.position - 1] | 128);
            } else {
               this.writeUtf8Length(charCount + 1);
               int charIndex = 0;
               if (this.capacity - this.position >= charCount) {
                  byte[] buffer = this.buffer;

                  int position;
                  for (position = this.position; charIndex < charCount; charIndex++) {
                     int c = value.charAt(charIndex);
                     if (c > 127) {
                        break;
                     }

                     buffer[position++] = (byte)c;
                  }

                  this.position = position;
               }

               if (charIndex < charCount) {
                  this.writeString_slow(value, charCount, charIndex);
               }
            }
         }
      }
   }

   private void getBytes(String value, int start, int end, byte[] bytes, int offset) {
      while (start < end) {
         bytes[offset++] = (byte)value.charAt(start++);
      }
   }

   public void writeString(CharSequence value) {
      if (value == null) {
         this.writeByte(128);
      } else {
         int charCount = value.length();
         if (charCount == 0) {
            this.writeByte(129);
         } else {
            this.writeUtf8Length(charCount + 1);
            int charIndex = 0;
            if (this.capacity - this.position >= charCount) {
               byte[] buffer = this.buffer;

               int position;
               for (position = this.position; charIndex < charCount; charIndex++) {
                  int c = value.charAt(charIndex);
                  if (c > 127) {
                     break;
                  }

                  buffer[position++] = (byte)c;
               }

               this.position = position;
            }

            if (charIndex < charCount) {
               this.writeString_slow(value, charCount, charIndex);
            }
         }
      }
   }

   public void writeAscii(String value) {
      if (value == null) {
         this.writeByte(128);
      } else {
         int charCount = value.length();
         switch (charCount) {
            case 0:
               this.writeByte(129);
               return;
            case 1:
               this.writeByte(130);
               this.writeByte(value.charAt(0));
               return;
            default:
               if (this.capacity - this.position < charCount) {
                  this.writeAscii_slow(value, charCount);
               } else {
                  this.getBytes(value, 0, charCount, this.buffer, this.position);
                  this.position += charCount;
               }

               this.buffer[this.position - 1] = (byte)(this.buffer[this.position - 1] | 128);
         }
      }
   }

   private void writeUtf8Length(int value) {
      if (value >>> 6 == 0) {
         this.require(1);
         this.buffer[this.position++] = (byte)(value | 128);
      } else if (value >>> 13 == 0) {
         this.require(2);
         byte[] buffer = this.buffer;
         buffer[this.position++] = (byte)(value | 64 | 128);
         buffer[this.position++] = (byte)(value >>> 6);
      } else if (value >>> 20 == 0) {
         this.require(3);
         byte[] buffer = this.buffer;
         buffer[this.position++] = (byte)(value | 64 | 128);
         buffer[this.position++] = (byte)(value >>> 6 | 128);
         buffer[this.position++] = (byte)(value >>> 13);
      } else if (value >>> 27 == 0) {
         this.require(4);
         byte[] buffer = this.buffer;
         buffer[this.position++] = (byte)(value | 64 | 128);
         buffer[this.position++] = (byte)(value >>> 6 | 128);
         buffer[this.position++] = (byte)(value >>> 13 | 128);
         buffer[this.position++] = (byte)(value >>> 20);
      } else {
         this.require(5);
         byte[] buffer = this.buffer;
         buffer[this.position++] = (byte)(value | 64 | 128);
         buffer[this.position++] = (byte)(value >>> 6 | 128);
         buffer[this.position++] = (byte)(value >>> 13 | 128);
         buffer[this.position++] = (byte)(value >>> 20 | 128);
         buffer[this.position++] = (byte)(value >>> 27);
      }
   }

   private void writeString_slow(CharSequence value, int charCount, int charIndex) {
      while (charIndex < charCount) {
         if (this.position == this.capacity) {
            this.require(Math.min(this.capacity, charCount - charIndex));
         }

         int c = value.charAt(charIndex);
         if (c <= 127) {
            this.buffer[this.position++] = (byte)c;
         } else if (c > 2047) {
            this.buffer[this.position++] = (byte)(224 | c >> 12 & 15);
            this.require(2);
            this.buffer[this.position++] = (byte)(128 | c >> 6 & 63);
            this.buffer[this.position++] = (byte)(128 | c & 63);
         } else {
            this.buffer[this.position++] = (byte)(192 | c >> 6 & 31);
            this.require(1);
            this.buffer[this.position++] = (byte)(128 | c & 63);
         }

         charIndex++;
      }
   }

   private void writeAscii_slow(String value, int charCount) {
      if (charCount != 0) {
         if (this.capacity == 0) {
            this.require(1);
         }

         int charIndex = 0;
         byte[] buffer = this.buffer;
         int charsToWrite = Math.min(charCount, this.capacity - this.position);

         while (charIndex < charCount) {
            this.getBytes(value, charIndex, charIndex + charsToWrite, buffer, this.position);
            charIndex += charsToWrite;
            this.position += charsToWrite;
            charsToWrite = Math.min(charCount - charIndex, this.capacity);
            if (this.require(charsToWrite)) {
               buffer = this.buffer;
            }
         }
      }
   }

   public void writeFloat(float value) {
      this.writeInt(Float.floatToIntBits(value));
   }

   public int writeFloat(float value, float precision, boolean optimizePositive) {
      return this.writeInt((int)(value * precision), optimizePositive);
   }

   public void writeShort(int value) {
      this.require(2);
      this.buffer[this.position++] = (byte)(value >>> 8);
      this.buffer[this.position++] = (byte)value;
   }

   public void writeLong(long value) {
      this.require(8);
      byte[] buffer = this.buffer;
      buffer[this.position++] = (byte)(value >>> 56);
      buffer[this.position++] = (byte)(value >>> 48);
      buffer[this.position++] = (byte)(value >>> 40);
      buffer[this.position++] = (byte)(value >>> 32);
      buffer[this.position++] = (byte)(value >>> 24);
      buffer[this.position++] = (byte)(value >>> 16);
      buffer[this.position++] = (byte)(value >>> 8);
      buffer[this.position++] = (byte)value;
   }

   public int writeLong(long value, boolean optimizePositive) {
      if (!optimizePositive) {
         value = value << 1 ^ value >> 63;
      }

      if (value >>> 7 == 0L) {
         this.require(1);
         this.buffer[this.position++] = (byte)value;
         return 1;
      } else if (value >>> 14 == 0L) {
         this.require(2);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7);
         return 2;
      } else if (value >>> 21 == 0L) {
         this.require(3);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 14);
         return 3;
      } else if (value >>> 28 == 0L) {
         this.require(4);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 21);
         return 4;
      } else if (value >>> 35 == 0L) {
         this.require(5);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 21 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 28);
         return 5;
      } else if (value >>> 42 == 0L) {
         this.require(6);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 21 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 28 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 35);
         return 6;
      } else if (value >>> 49 == 0L) {
         this.require(7);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 21 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 28 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 35 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 42);
         return 7;
      } else if (value >>> 56 == 0L) {
         this.require(8);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 21 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 28 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 35 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 42 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 49);
         return 8;
      } else {
         this.require(9);
         this.buffer[this.position++] = (byte)(value & 127L | 128L);
         this.buffer[this.position++] = (byte)(value >>> 7 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 14 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 21 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 28 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 35 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 42 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 49 | 128L);
         this.buffer[this.position++] = (byte)(value >>> 56);
         return 9;
      }
   }

   public void writeBoolean(boolean value) {
      if (this.position == this.capacity) {
         this.require(1);
      }

      this.buffer[this.position++] = (byte)(value ? 1 : 0);
   }

   public void writeChar(char value) {
      this.require(2);
      this.buffer[this.position++] = (byte)(value >>> '\b');
      this.buffer[this.position++] = (byte)value;
   }

   public void writeDouble(double value) {
      this.writeLong(Double.doubleToLongBits(value));
   }

   public int writeDouble(double value, double precision, boolean optimizePositive) {
      return this.writeLong((long)(value * precision), optimizePositive);
   }

   public void writeInts(int[] object, boolean optimizePositive) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeInt(object[i], optimizePositive);
      }
   }

   public void writeLongs(long[] object, boolean optimizePositive) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeLong(object[i], optimizePositive);
      }
   }

   public void writeInts(int[] object) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeInt(object[i]);
      }
   }

   public void writeLongs(long[] object) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeLong(object[i]);
      }
   }

   public void writeFloats(float[] object) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeFloat(object[i]);
      }
   }

   public void writeShorts(short[] object) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeShort(object[i]);
      }
   }

   public void writeChars(char[] object) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeChar(object[i]);
      }
   }

   public void writeDoubles(double[] object) {
      int i = 0;

      for (int n = object.length; i < n; i++) {
         this.writeDouble(object[i]);
      }
   }

   public static int intLength(int value, boolean optimizePositive) {
      if (!optimizePositive) {
         value = value << 1 ^ value >> 31;
      }

      if (value >>> 7 == 0) {
         return 1;
      } else if (value >>> 14 == 0) {
         return 2;
      } else if (value >>> 21 == 0) {
         return 3;
      } else {
         return value >>> 28 == 0 ? 4 : 5;
      }
   }

   public static int longLength(long value, boolean optimizePositive) {
      if (!optimizePositive) {
         value = value << 1 ^ value >> 63;
      }

      if (value >>> 7 == 0L) {
         return 1;
      } else if (value >>> 14 == 0L) {
         return 2;
      } else if (value >>> 21 == 0L) {
         return 3;
      } else if (value >>> 28 == 0L) {
         return 4;
      } else if (value >>> 35 == 0L) {
         return 5;
      } else if (value >>> 42 == 0L) {
         return 6;
      } else if (value >>> 49 == 0L) {
         return 7;
      } else {
         return value >>> 56 == 0L ? 8 : 9;
      }
   }

   public static void writeInt(byte[] buffer, int position, int value) {
      buffer[position++] = (byte)(value >> 24);
      buffer[position++] = (byte)(value >> 16);
      buffer[position++] = (byte)(value >> 8);
      buffer[position] = (byte)value;
   }
}
