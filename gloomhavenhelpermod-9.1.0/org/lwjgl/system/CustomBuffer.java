package org.lwjgl.system;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import javax.annotation.Nullable;

public abstract class CustomBuffer extends Pointer.Default {
   @Nullable
   protected ByteBuffer container;
   protected int mark;
   protected int position;
   protected int limit;
   protected int capacity;

   protected CustomBuffer(long address, @Nullable ByteBuffer container, int mark, int position, int limit, int capacity) {
      super(address);
      this.container = container;
      this.mark = mark;
      this.position = position;
      this.limit = limit;
      this.capacity = capacity;
   }

   public abstract int sizeof();

   public long address0() {
      return this.address;
   }

   @Override
   public long address() {
      return this.address + Integer.toUnsignedLong(this.position) * this.sizeof();
   }

   public long address(int position) {
      return this.address + Integer.toUnsignedLong(position) * this.sizeof();
   }

   public void free() {
      MemoryUtil.nmemFree(this.address);
   }

   public int capacity() {
      return this.capacity;
   }

   public int position() {
      return this.position;
   }

   public CustomBuffer position(int position) {
      if (position >= 0 && this.limit >= position) {
         this.position = position;
         if (position < this.mark) {
            this.mark = -1;
         }

         return this.self();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public int limit() {
      return this.limit;
   }

   public CustomBuffer limit(int limit) {
      if (limit >= 0 && this.capacity >= limit) {
         this.limit = limit;
         if (limit < this.position) {
            this.position = limit;
         }

         if (limit < this.mark) {
            this.mark = -1;
         }

         return this.self();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public CustomBuffer mark() {
      this.mark = this.position;
      return this.self();
   }

   public CustomBuffer reset() {
      int m = this.mark;
      if (m < 0) {
         throw new InvalidMarkException();
      } else {
         this.position = m;
         return this.self();
      }
   }

   public CustomBuffer clear() {
      this.position = 0;
      this.limit = this.capacity;
      this.mark = -1;
      return this.self();
   }

   public CustomBuffer flip() {
      this.limit = this.position;
      this.position = 0;
      this.mark = -1;
      return this.self();
   }

   public CustomBuffer rewind() {
      this.position = 0;
      this.mark = -1;
      return this.self();
   }

   public int remaining() {
      return this.limit - this.position;
   }

   public boolean hasRemaining() {
      return this.position < this.limit;
   }

   public CustomBuffer slice() {
      CustomBuffer slice;
      try {
         slice = (SELF)((CustomBuffer)UNSAFE.allocateInstance(this.getClass()));
      } catch (InstantiationException var3) {
         throw new UnsupportedOperationException(var3);
      }

      UNSAFE.putLong(slice, ADDRESS, this.address + Integer.toUnsignedLong(this.position) * this.sizeof());
      UNSAFE.putInt(slice, BUFFER_MARK, -1);
      UNSAFE.putInt(slice, BUFFER_LIMIT, this.remaining());
      UNSAFE.putInt(slice, BUFFER_CAPACITY, this.remaining());
      UNSAFE.putObject(slice, BUFFER_CONTAINER, this.container);
      return slice;
   }

   public CustomBuffer slice(int offset, int capacity) {
      int position = this.position + offset;
      if (offset >= 0 && this.limit >= offset) {
         if (capacity >= 0 && this.capacity - position >= capacity) {
            CustomBuffer slice;
            try {
               slice = (SELF)((CustomBuffer)UNSAFE.allocateInstance(this.getClass()));
            } catch (InstantiationException var6) {
               throw new UnsupportedOperationException(var6);
            }

            UNSAFE.putLong(slice, ADDRESS, this.address + Integer.toUnsignedLong(position) * this.sizeof());
            UNSAFE.putInt(slice, BUFFER_MARK, -1);
            UNSAFE.putInt(slice, BUFFER_LIMIT, capacity);
            UNSAFE.putInt(slice, BUFFER_CAPACITY, capacity);
            UNSAFE.putObject(slice, BUFFER_CONTAINER, this.container);
            return slice;
         } else {
            throw new IllegalArgumentException();
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public CustomBuffer duplicate() {
      CustomBuffer dup;
      try {
         dup = (SELF)((CustomBuffer)UNSAFE.allocateInstance(this.getClass()));
      } catch (InstantiationException var3) {
         throw new UnsupportedOperationException(var3);
      }

      UNSAFE.putLong(dup, ADDRESS, this.address);
      UNSAFE.putInt(dup, BUFFER_MARK, this.mark);
      UNSAFE.putInt(dup, BUFFER_POSITION, this.position);
      UNSAFE.putInt(dup, BUFFER_LIMIT, this.limit);
      UNSAFE.putInt(dup, BUFFER_CAPACITY, this.capacity);
      UNSAFE.putObject(dup, BUFFER_CONTAINER, this.container);
      return dup;
   }

   public CustomBuffer put(CustomBuffer src) {
      if (src == this) {
         throw new IllegalArgumentException();
      } else {
         int n = src.remaining();
         if (this.remaining() < n) {
            throw new BufferOverflowException();
         } else {
            MemoryUtil.memCopy(src.address(), this.address(), Integer.toUnsignedLong(n) * this.sizeof());
            this.position += n;
            return this.self();
         }
      }
   }

   public CustomBuffer compact() {
      MemoryUtil.memCopy(this.address(), this.address, Integer.toUnsignedLong(this.remaining()) * this.sizeof());
      this.position(this.remaining());
      this.limit(this.capacity());
      this.mark = -1;
      return this.self();
   }

   @Override
   public String toString() {
      return this.getClass().getName() + "[pos=" + this.position() + " lim=" + this.limit() + " cap=" + this.capacity() + "]";
   }

   protected abstract CustomBuffer self();

   protected final int nextGetIndex() {
      if (this.position < this.limit) {
         return this.position++;
      } else {
         throw new BufferUnderflowException();
      }
   }

   protected final int nextPutIndex() {
      if (this.position < this.limit) {
         return this.position++;
      } else {
         throw new BufferOverflowException();
      }
   }
}
