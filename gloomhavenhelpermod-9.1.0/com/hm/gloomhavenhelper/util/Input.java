package com.hm.gloomhavenhelper.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Input extends InputStream {
   static final int maxArraySize = 2147483639;
   protected byte[] buffer;
   protected int position;
   protected int capacity;
   protected int limit;
   protected long total;
   protected char[] chars = new char[32];
   protected InputStream inputStream;

   public Input() {
   }

   public Input(int bufferSize) {
      this.capacity = bufferSize;
      this.buffer = new byte[bufferSize];
   }

   public Input(byte[] buffer) {
      this.setBuffer(buffer, 0, buffer.length);
   }

   public Input(byte[] buffer, int offset, int count) {
      this.setBuffer(buffer, offset, count);
   }

   public Input(InputStream inputStream) {
      this(4096);
      if (inputStream == null) {
         throw new IllegalArgumentException("inputStream cannot be null.");
      } else {
         this.inputStream = inputStream;
      }
   }

   public Input(InputStream inputStream, int bufferSize) {
      this(bufferSize);
      if (inputStream == null) {
         throw new IllegalArgumentException("inputStream cannot be null.");
      } else {
         this.inputStream = inputStream;
      }
   }

   public void setBuffer(byte[] bytes) {
      this.setBuffer(bytes, 0, bytes.length);
   }

   public void setBuffer(byte[] bytes, int offset, int count) {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         this.buffer = bytes;
         this.position = offset;
         this.limit = offset + count;
         this.capacity = bytes.length;
         this.total = 0L;
         this.inputStream = null;
      }
   }

   public byte[] getBuffer() {
      return this.buffer;
   }

   public InputStream getInputStream() {
      return this.inputStream;
   }

   public void setInputStream(InputStream inputStream) {
      this.inputStream = inputStream;
      this.limit = 0;
      this.rewind();
   }

   public long total() {
      return this.total + this.position;
   }

   public void setTotal(long total) {
      this.total = total;
   }

   public int position() {
      return this.position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public int limit() {
      return this.limit;
   }

   public void setLimit(int limit) {
      this.limit = limit;
   }

   public void rewind() {
      this.position = 0;
      this.total = 0L;
   }

   public void skip(int count) {
      int skipCount = Math.min(this.limit - this.position, count);

      while (true) {
         this.position += skipCount;
         count -= skipCount;
         if (count == 0) {
            return;
         }

         skipCount = Math.min(count, this.capacity);
         this.require(skipCount);
      }
   }

   protected int fill(byte[] buffer, int offset, int count) {
      if (this.inputStream == null) {
         return -1;
      } else {
         try {
            return this.inputStream.read(buffer, offset, count);
         } catch (IOException var5) {
            throw new RuntimeException(var5);
         }
      }
   }

   protected int require(int required) {
      int remaining = this.limit - this.position;
      if (remaining >= required) {
         return remaining;
      } else if (required > this.capacity) {
         throw new RuntimeException("Buffer too small: capacity: " + this.capacity + ", required: " + required);
      } else {
         if (remaining > 0) {
            int count = this.fill(this.buffer, this.limit, this.capacity - this.limit);
            if (count == -1) {
               throw new RuntimeException("Buffer underflow.");
            }

            remaining += count;
            if (remaining >= required) {
               this.limit += count;
               return remaining;
            }
         }

         System.arraycopy(this.buffer, this.position, this.buffer, 0, remaining);
         this.total = this.total + this.position;
         this.position = 0;

         do {
            int countx = this.fill(this.buffer, remaining, this.capacity - remaining);
            if (countx == -1) {
               if (remaining < required) {
                  throw new RuntimeException("Buffer underflow.");
               }
               break;
            }

            remaining += countx;
         } while (remaining < required);

         this.limit = remaining;
         return remaining;
      }
   }

   private int optional(int optional) {
      int remaining = this.limit - this.position;
      if (remaining >= optional) {
         return optional;
      } else {
         optional = Math.min(optional, this.capacity);
         int count = this.fill(this.buffer, this.limit, this.capacity - this.limit);
         if (count == -1) {
            return remaining == 0 ? -1 : Math.min(remaining, optional);
         } else {
            remaining += count;
            if (remaining >= optional) {
               this.limit += count;
               return optional;
            } else {
               System.arraycopy(this.buffer, this.position, this.buffer, 0, remaining);
               this.total = this.total + this.position;
               this.position = 0;

               do {
                  count = this.fill(this.buffer, remaining, this.capacity - remaining);
                  if (count == -1) {
                     break;
                  }

                  remaining += count;
               } while (remaining < optional);

               this.limit = remaining;
               return remaining == 0 ? -1 : Math.min(remaining, optional);
            }
         }
      }
   }

   public boolean end() {
      return this.optional(1) <= 0;
   }

   @Override
   public int available() throws IOException {
      return this.limit - this.position + (this.inputStream != null ? this.inputStream.available() : 0);
   }

   @Override
   public int read() {
      return this.optional(1) <= 0 ? -1 : this.buffer[this.position++] & 0xFF;
   }

   @Override
   public int read(byte[] bytes) {
      return this.read(bytes, 0, bytes.length);
   }

   @Override
   public int read(byte[] bytes, int offset, int count) {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         int startingCount = count;
         int copyCount = Math.min(this.limit - this.position, count);

         do {
            System.arraycopy(this.buffer, this.position, bytes, offset, copyCount);
            this.position += copyCount;
            count -= copyCount;
            if (count == 0) {
               break;
            }

            offset += copyCount;
            copyCount = this.optional(count);
            if (copyCount == -1) {
               if (startingCount == count) {
                  return -1;
               }
               break;
            }
         } while (this.position != this.limit);

         return startingCount - count;
      }
   }

   @Override
   public long skip(long count) {
      long remaining = count;

      while (remaining > 0L) {
         int skip = (int)Math.min(2147483639L, remaining);
         this.skip(skip);
         remaining -= skip;
      }

      return count;
   }

   @Override
   public void close() {
      if (this.inputStream != null) {
         try {
            this.inputStream.close();
         } catch (IOException var2) {
         }
      }
   }

   public byte readByte() {
      this.require(1);
      return this.buffer[this.position++];
   }

   public int readByteUnsigned() {
      this.require(1);
      return this.buffer[this.position++] & 0xFF;
   }

   public byte[] readBytes(int length) {
      byte[] bytes = new byte[length];
      this.readBytes(bytes, 0, length);
      return bytes;
   }

   public void readBytes(byte[] bytes) {
      this.readBytes(bytes, 0, bytes.length);
   }

   public void readBytes(byte[] bytes, int offset, int count) {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null.");
      } else {
         int copyCount = Math.min(this.limit - this.position, count);

         while (true) {
            System.arraycopy(this.buffer, this.position, bytes, offset, copyCount);
            this.position += copyCount;
            count -= copyCount;
            if (count == 0) {
               return;
            }

            offset += copyCount;
            copyCount = Math.min(count, this.capacity);
            this.require(copyCount);
         }
      }
   }

   public int readInt() {
      this.require(4);
      byte[] buffer = this.buffer;
      int position = this.position;
      this.position = position + 4;
      return (buffer[position] & 0xFF) << 24 | (buffer[position + 1] & 0xFF) << 16 | (buffer[position + 2] & 0xFF) << 8 | buffer[position + 3] & 0xFF;
   }

   public int readInt(boolean optimizePositive) {
      if (this.require(1) < 5) {
         return this.readInt_slow(optimizePositive);
      } else {
         int b = this.buffer[this.position++];
         int result = b & 127;
         if ((b & 128) != 0) {
            byte[] buffer = this.buffer;
            int var5 = buffer[this.position++];
            result |= (var5 & 127) << 7;
            if ((var5 & 128) != 0) {
               var5 = buffer[this.position++];
               result |= (var5 & 127) << 14;
               if ((var5 & 128) != 0) {
                  var5 = buffer[this.position++];
                  result |= (var5 & 127) << 21;
                  if ((var5 & 128) != 0) {
                     var5 = buffer[this.position++];
                     result |= (var5 & 127) << 28;
                  }
               }
            }
         }

         return optimizePositive ? result : result >>> 1 ^ -(result & 1);
      }
   }

   private int readInt_slow(boolean optimizePositive) {
      int b = this.buffer[this.position++];
      int result = b & 127;
      if ((b & 128) != 0) {
         this.require(1);
         byte[] buffer = this.buffer;
         int var5 = buffer[this.position++];
         result |= (var5 & 127) << 7;
         if ((var5 & 128) != 0) {
            this.require(1);
            var5 = buffer[this.position++];
            result |= (var5 & 127) << 14;
            if ((var5 & 128) != 0) {
               this.require(1);
               var5 = buffer[this.position++];
               result |= (var5 & 127) << 21;
               if ((var5 & 128) != 0) {
                  this.require(1);
                  var5 = buffer[this.position++];
                  result |= (var5 & 127) << 28;
               }
            }
         }
      }

      return optimizePositive ? result : result >>> 1 ^ -(result & 1);
   }

   public boolean canReadInt() {
      if (this.limit - this.position >= 5) {
         return true;
      } else if (this.optional(5) <= 0) {
         return false;
      } else {
         int p = this.position;
         if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else {
            return (this.buffer[p++] & 128) == 0 ? true : p != this.limit;
         }
      }
   }

   public boolean canReadLong() {
      if (this.limit - this.position >= 9) {
         return true;
      } else if (this.optional(5) <= 0) {
         return false;
      } else {
         int p = this.position;
         if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else if ((this.buffer[p++] & 128) == 0) {
            return true;
         } else if (p == this.limit) {
            return false;
         } else {
            return (this.buffer[p++] & 128) == 0 ? true : p != this.limit;
         }
      }
   }

   public String readString() {
      int available = this.require(1);
      int b = this.buffer[this.position++];
      if ((b & 128) == 0) {
         return this.readAscii();
      } else {
         int charCount = available >= 5 ? this.readUtf8Length(b) : this.readUtf8Length_slow(b);
         switch (charCount) {
            case 0:
               return null;
            case 1:
               return "";
            default:
               if (this.chars.length < --charCount) {
                  this.chars = new char[charCount];
               }

               this.readUtf8(charCount);
               return new String(this.chars, 0, charCount);
         }
      }
   }

   private int readUtf8Length(int b) {
      int result = b & 63;
      if ((b & 64) != 0) {
         byte[] buffer = this.buffer;
         int var4 = buffer[this.position++];
         result |= (var4 & 127) << 6;
         if ((var4 & 128) != 0) {
            var4 = buffer[this.position++];
            result |= (var4 & 127) << 13;
            if ((var4 & 128) != 0) {
               var4 = buffer[this.position++];
               result |= (var4 & 127) << 20;
               if ((var4 & 128) != 0) {
                  var4 = buffer[this.position++];
                  result |= (var4 & 127) << 27;
               }
            }
         }
      }

      return result;
   }

   private int readUtf8Length_slow(int b) {
      int result = b & 63;
      if ((b & 64) != 0) {
         this.require(1);
         byte[] buffer = this.buffer;
         int var4 = buffer[this.position++];
         result |= (var4 & 127) << 6;
         if ((var4 & 128) != 0) {
            this.require(1);
            var4 = buffer[this.position++];
            result |= (var4 & 127) << 13;
            if ((var4 & 128) != 0) {
               this.require(1);
               var4 = buffer[this.position++];
               result |= (var4 & 127) << 20;
               if ((var4 & 128) != 0) {
                  this.require(1);
                  var4 = buffer[this.position++];
                  result |= (var4 & 127) << 27;
               }
            }
         }
      }

      return result;
   }

   private void readUtf8(int charCount) {
      byte[] buffer = this.buffer;
      char[] chars = this.chars;
      int charIndex = 0;
      int count = Math.min(this.require(1), charCount);
      int position = this.position;

      while (charIndex < count) {
         int b = buffer[position++];
         if (b < 0) {
            position--;
            break;
         }

         chars[charIndex++] = (char)b;
      }

      this.position = position;
      if (charIndex < charCount) {
         this.readUtf8_slow(charCount, charIndex);
      }
   }

   private void readUtf8_slow(int charCount, int charIndex) {
      char[] chars = this.chars;
      byte[] buffer = this.buffer;

      while (charIndex < charCount) {
         if (this.position == this.limit) {
            this.require(1);
         }

         int b = buffer[this.position++] & 255;
         switch (b >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
               chars[charIndex] = (char)b;
            case 8:
            case 9:
            case 10:
            case 11:
            default:
               break;
            case 12:
            case 13:
               if (this.position == this.limit) {
                  this.require(1);
               }

               chars[charIndex] = (char)((b & 31) << 6 | buffer[this.position++] & 63);
               break;
            case 14:
               this.require(2);
               chars[charIndex] = (char)((b & 15) << 12 | (buffer[this.position++] & 63) << 6 | buffer[this.position++] & 63);
         }

         charIndex++;
      }
   }

   private String readAscii() {
      byte[] buffer = this.buffer;
      int end = this.position;
      int start = end - 1;
      int limit = this.limit;

      while (end != limit) {
         int b = buffer[end++];
         if ((b & 128) != 0) {
            int n = end - 1;
            buffer[n] = (byte)(buffer[n] & 127);

            try {
               String value = new String(buffer, start, end - start, "ASCII");
               int n2 = end - 1;
               buffer[n2] |= -128;
               this.position = end;
               return value;
            } catch (UnsupportedEncodingException var11) {
               throw new RuntimeException();
            }
         }
      }

      return this.readAscii_slow();
   }

   private String readAscii_slow() {
      this.position--;
      int charCount = this.limit - this.position;
      if (charCount > this.chars.length) {
         this.chars = new char[charCount * 2];
      }

      char[] chars = this.chars;
      byte[] buffer = this.buffer;
      int i = this.position;
      int ii = 0;

      for (int n = this.limit; i < n; ii++) {
         chars[ii] = (char)buffer[i];
         i++;
      }

      this.position = this.limit;

      while (true) {
         this.require(1);
         i = buffer[this.position++];
         if (charCount == chars.length) {
            char[] newChars = new char[charCount * 2];
            System.arraycopy(chars, 0, newChars, 0, charCount);
            chars = newChars;
            this.chars = newChars;
         }

         if ((i & 128) == 128) {
            chars[charCount++] = (char)(i & 127);
            return new String(chars, 0, charCount);
         }

         chars[charCount++] = (char)i;
      }
   }

   public StringBuilder readStringBuilder() {
      int available = this.require(1);
      int b = this.buffer[this.position++];
      if ((b & 128) == 0) {
         return new StringBuilder(this.readAscii());
      } else {
         int charCount = available >= 5 ? this.readUtf8Length(b) : this.readUtf8Length_slow(b);
         switch (charCount) {
            case 0:
               return null;
            case 1:
               return new StringBuilder("");
            default:
               if (this.chars.length < --charCount) {
                  this.chars = new char[charCount];
               }

               this.readUtf8(charCount);
               StringBuilder builder = new StringBuilder(charCount);
               builder.append(this.chars, 0, charCount);
               return builder;
         }
      }
   }

   public float readFloat() {
      return Float.intBitsToFloat(this.readInt());
   }

   public float readFloat(float precision, boolean optimizePositive) {
      return this.readInt(optimizePositive) / precision;
   }

   public short readShort() {
      this.require(2);
      return (short)((this.buffer[this.position++] & 255) << 8 | this.buffer[this.position++] & 255);
   }

   public int readShortUnsigned() {
      this.require(2);
      return (this.buffer[this.position++] & 0xFF) << 8 | this.buffer[this.position++] & 0xFF;
   }

   public long readLong() {
      this.require(8);
      byte[] buffer = this.buffer;
      return buffer[this.position++] << 56
         | (buffer[this.position++] & 255) << 48
         | (buffer[this.position++] & 255) << 40
         | (buffer[this.position++] & 255) << 32
         | (buffer[this.position++] & 255) << 24
         | (buffer[this.position++] & 255) << 16
         | (buffer[this.position++] & 255) << 8
         | buffer[this.position++] & 255;
   }

   public long readLong(boolean optimizePositive) {
      if (this.require(1) < 9) {
         return this.readLong_slow(optimizePositive);
      } else {
         int b = this.buffer[this.position++];
         long result = b & 127;
         if ((b & 128) != 0) {
            byte[] buffer = this.buffer;
            int var6 = buffer[this.position++];
            result |= (var6 & 127) << 7;
            if ((var6 & 128) != 0) {
               var6 = buffer[this.position++];
               result |= (var6 & 127) << 14;
               if ((var6 & 128) != 0) {
                  var6 = buffer[this.position++];
                  result |= (var6 & 127) << 21;
                  if ((var6 & 128) != 0) {
                     var6 = buffer[this.position++];
                     result |= (var6 & 127) << 28;
                     if ((var6 & 128) != 0) {
                        var6 = buffer[this.position++];
                        result |= (var6 & 127) << 35;
                        if ((var6 & 128) != 0) {
                           var6 = buffer[this.position++];
                           result |= (var6 & 127) << 42;
                           if ((var6 & 128) != 0) {
                              var6 = buffer[this.position++];
                              result |= (var6 & 127) << 49;
                              if ((var6 & 128) != 0) {
                                 var6 = buffer[this.position++];
                                 result |= var6 << 56;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (!optimizePositive) {
            result = result >>> 1 ^ -(result & 1L);
         }

         return result;
      }
   }

   private long readLong_slow(boolean optimizePositive) {
      int b = this.buffer[this.position++];
      long result = b & 127;
      if ((b & 128) != 0) {
         this.require(1);
         byte[] buffer = this.buffer;
         int var6 = buffer[this.position++];
         result |= (var6 & 127) << 7;
         if ((var6 & 128) != 0) {
            this.require(1);
            var6 = buffer[this.position++];
            result |= (var6 & 127) << 14;
            if ((var6 & 128) != 0) {
               this.require(1);
               var6 = buffer[this.position++];
               result |= (var6 & 127) << 21;
               if ((var6 & 128) != 0) {
                  this.require(1);
                  var6 = buffer[this.position++];
                  result |= (var6 & 127) << 28;
                  if ((var6 & 128) != 0) {
                     this.require(1);
                     var6 = buffer[this.position++];
                     result |= (var6 & 127) << 35;
                     if ((var6 & 128) != 0) {
                        this.require(1);
                        var6 = buffer[this.position++];
                        result |= (var6 & 127) << 42;
                        if ((var6 & 128) != 0) {
                           this.require(1);
                           var6 = buffer[this.position++];
                           result |= (var6 & 127) << 49;
                           if ((var6 & 128) != 0) {
                              this.require(1);
                              var6 = buffer[this.position++];
                              result |= var6 << 56;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (!optimizePositive) {
         result = result >>> 1 ^ -(result & 1L);
      }

      return result;
   }

   public boolean readBoolean() {
      this.require(1);
      return this.buffer[this.position++] == 1;
   }

   public char readChar() {
      this.require(2);
      return (char)((this.buffer[this.position++] & 255) << 8 | this.buffer[this.position++] & 255);
   }

   public double readDouble() {
      return Double.longBitsToDouble(this.readLong());
   }

   public double readDouble(double precision, boolean optimizePositive) {
      return this.readLong(optimizePositive) / precision;
   }

   public int[] readInts(int length, boolean optimizePositive) {
      int[] array = new int[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readInt(optimizePositive);
      }

      return array;
   }

   public long[] readLongs(int length, boolean optimizePositive) {
      long[] array = new long[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readLong(optimizePositive);
      }

      return array;
   }

   public int[] readInts(int length) {
      int[] array = new int[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readInt();
      }

      return array;
   }

   public long[] readLongs(int length) {
      long[] array = new long[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readLong();
      }

      return array;
   }

   public float[] readFloats(int length) {
      float[] array = new float[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readFloat();
      }

      return array;
   }

   public short[] readShorts(int length) {
      short[] array = new short[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readShort();
      }

      return array;
   }

   public char[] readChars(int length) {
      char[] array = new char[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readChar();
      }

      return array;
   }

   public double[] readDoubles(int length) {
      double[] array = new double[length];

      for (int i = 0; i < length; i++) {
         array[i] = this.readDouble();
      }

      return array;
   }

   public static int readInt(byte[] buffer, int position) {
      return (buffer[position] & 0xFF) << 24 | (buffer[position + 1] & 0xFF) << 16 | (buffer[position + 2] & 0xFF) << 8 | buffer[position + 3] & 0xFF;
   }
}
