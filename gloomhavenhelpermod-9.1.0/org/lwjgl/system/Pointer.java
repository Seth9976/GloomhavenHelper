package org.lwjgl.system;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import sun.misc.Unsafe;

public interface Pointer {
   int POINTER_SIZE = MemoryAccessJNI.getPointerSize();
   int POINTER_SHIFT = POINTER_SIZE == 8 ? 3 : 2;
   int CLONG_SIZE = POINTER_SIZE == 8 && Platform.get() == Platform.WINDOWS ? 4 : POINTER_SIZE;
   int CLONG_SHIFT = CLONG_SIZE == 8 ? 3 : 2;
   boolean BITS32 = POINTER_SIZE * 8 == 32;
   boolean BITS64 = POINTER_SIZE * 8 == 64;

   long address();

   public abstract static class Default implements Pointer {
      protected static final Unsafe UNSAFE = MemoryUtil.UNSAFE;
      protected static final long ADDRESS;
      protected static final long BUFFER_CONTAINER;
      protected static final long BUFFER_MARK;
      protected static final long BUFFER_POSITION;
      protected static final long BUFFER_LIMIT;
      protected static final long BUFFER_CAPACITY;
      protected long address;

      protected Default(long address) {
         if (Checks.CHECKS && address == 0L) {
            throw new NullPointerException();
         } else {
            this.address = address;
         }
      }

      @Override
      public long address() {
         return this.address;
      }

      @Override
      public boolean equals(@Nullable Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof Pointer)) {
            return false;
         } else {
            Pointer that = (Pointer)o;
            return this.address == that.address();
         }
      }

      @Override
      public int hashCode() {
         return (int)(this.address ^ this.address >>> 32);
      }

      @Override
      public String toString() {
         return String.format("%s pointer [0x%X]", this.getClass().getSimpleName(), this.address);
      }

      protected static CustomBuffer wrap(Class clazz, long address, int capacity) {
         CustomBuffer buffer;
         try {
            buffer = (T)((CustomBuffer)UNSAFE.allocateInstance(clazz));
         } catch (InstantiationException var6) {
            throw new UnsupportedOperationException(var6);
         }

         UNSAFE.putLong(buffer, ADDRESS, address);
         UNSAFE.putInt(buffer, BUFFER_MARK, -1);
         UNSAFE.putInt(buffer, BUFFER_LIMIT, capacity);
         UNSAFE.putInt(buffer, BUFFER_CAPACITY, capacity);
         return buffer;
      }

      protected static CustomBuffer wrap(Class clazz, long address, int capacity, ByteBuffer container) {
         CustomBuffer buffer;
         try {
            buffer = (T)((CustomBuffer)UNSAFE.allocateInstance(clazz));
         } catch (InstantiationException var7) {
            throw new UnsupportedOperationException(var7);
         }

         UNSAFE.putLong(buffer, ADDRESS, address);
         UNSAFE.putInt(buffer, BUFFER_MARK, -1);
         UNSAFE.putInt(buffer, BUFFER_LIMIT, capacity);
         UNSAFE.putInt(buffer, BUFFER_CAPACITY, capacity);
         UNSAFE.putObject(buffer, BUFFER_CONTAINER, container);
         return buffer;
      }

      static {
         try {
            ADDRESS = UNSAFE.objectFieldOffset(Pointer.Default.class.getDeclaredField("address"));
            BUFFER_CONTAINER = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("container"));
            BUFFER_MARK = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("mark"));
            BUFFER_POSITION = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("position"));
            BUFFER_LIMIT = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("limit"));
            BUFFER_CAPACITY = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("capacity"));
         } catch (Throwable var1) {
            throw new UnsupportedOperationException(var1);
         }
      }
   }
}
