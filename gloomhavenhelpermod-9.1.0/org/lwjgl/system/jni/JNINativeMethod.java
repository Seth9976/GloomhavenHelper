package org.lwjgl.system.jni;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

public class JNINativeMethod extends Struct implements NativeResource {
   public static final int SIZEOF;
   public static final int ALIGNOF;
   public static final int NAME;
   public static final int SIGNATURE;
   public static final int FNPTR;

   public JNINativeMethod(ByteBuffer container) {
      super(MemoryUtil.memAddress(container), __checkContainer(container, SIZEOF));
   }

   @Override
   public int sizeof() {
      return SIZEOF;
   }

   @NativeType("char *")
   public ByteBuffer name() {
      return nname(this.address());
   }

   @NativeType("char *")
   public String nameString() {
      return nnameString(this.address());
   }

   @NativeType("char *")
   public ByteBuffer signature() {
      return nsignature(this.address());
   }

   @NativeType("char *")
   public String signatureString() {
      return nsignatureString(this.address());
   }

   @NativeType("void *")
   public long fnPtr() {
      return nfnPtr(this.address());
   }

   public JNINativeMethod name(@NativeType("char *") ByteBuffer value) {
      nname(this.address(), value);
      return this;
   }

   public JNINativeMethod signature(@NativeType("char *") ByteBuffer value) {
      nsignature(this.address(), value);
      return this;
   }

   public JNINativeMethod fnPtr(@NativeType("void *") long value) {
      nfnPtr(this.address(), value);
      return this;
   }

   public JNINativeMethod set(ByteBuffer name, ByteBuffer signature, long fnPtr) {
      this.name(name);
      this.signature(signature);
      this.fnPtr(fnPtr);
      return this;
   }

   public JNINativeMethod set(JNINativeMethod src) {
      MemoryUtil.memCopy(src.address(), this.address(), SIZEOF);
      return this;
   }

   public static JNINativeMethod malloc() {
      return (JNINativeMethod)wrap(JNINativeMethod.class, MemoryUtil.nmemAllocChecked(SIZEOF));
   }

   public static JNINativeMethod calloc() {
      return (JNINativeMethod)wrap(JNINativeMethod.class, MemoryUtil.nmemCallocChecked(1L, SIZEOF));
   }

   public static JNINativeMethod create() {
      ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
      return (JNINativeMethod)wrap(JNINativeMethod.class, MemoryUtil.memAddress(container), container);
   }

   public static JNINativeMethod create(long address) {
      return (JNINativeMethod)wrap(JNINativeMethod.class, address);
   }

   @Nullable
   public static JNINativeMethod createSafe(long address) {
      return address == 0L ? null : (JNINativeMethod)wrap(JNINativeMethod.class, address);
   }

   public static JNINativeMethod.Buffer malloc(int capacity) {
      return (JNINativeMethod.Buffer)wrap(JNINativeMethod.Buffer.class, MemoryUtil.nmemAllocChecked(__checkMalloc(capacity, SIZEOF)), capacity);
   }

   public static JNINativeMethod.Buffer calloc(int capacity) {
      return (JNINativeMethod.Buffer)wrap(JNINativeMethod.Buffer.class, MemoryUtil.nmemCallocChecked(capacity, SIZEOF), capacity);
   }

   public static JNINativeMethod.Buffer create(int capacity) {
      ByteBuffer container = __create(capacity, SIZEOF);
      return (JNINativeMethod.Buffer)wrap(JNINativeMethod.Buffer.class, MemoryUtil.memAddress(container), capacity, container);
   }

   public static JNINativeMethod.Buffer create(long address, int capacity) {
      return (JNINativeMethod.Buffer)wrap(JNINativeMethod.Buffer.class, address, capacity);
   }

   @Nullable
   public static JNINativeMethod.Buffer createSafe(long address, int capacity) {
      return address == 0L ? null : (JNINativeMethod.Buffer)wrap(JNINativeMethod.Buffer.class, address, capacity);
   }

   public static JNINativeMethod mallocStack() {
      return mallocStack(MemoryStack.stackGet());
   }

   public static JNINativeMethod callocStack() {
      return callocStack(MemoryStack.stackGet());
   }

   public static JNINativeMethod mallocStack(MemoryStack stack) {
      return (JNINativeMethod)wrap(JNINativeMethod.class, stack.nmalloc(ALIGNOF, SIZEOF));
   }

   public static JNINativeMethod callocStack(MemoryStack stack) {
      return (JNINativeMethod)wrap(JNINativeMethod.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
   }

   public static JNINativeMethod.Buffer mallocStack(int capacity) {
      return mallocStack(capacity, MemoryStack.stackGet());
   }

   public static JNINativeMethod.Buffer callocStack(int capacity) {
      return callocStack(capacity, MemoryStack.stackGet());
   }

   public static JNINativeMethod.Buffer mallocStack(int capacity, MemoryStack stack) {
      return (JNINativeMethod.Buffer)wrap(JNINativeMethod.Buffer.class, stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
   }

   public static JNINativeMethod.Buffer callocStack(int capacity, MemoryStack stack) {
      return (JNINativeMethod.Buffer)wrap(JNINativeMethod.Buffer.class, stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
   }

   public static ByteBuffer nname(long struct) {
      return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(struct + NAME));
   }

   public static String nnameString(long struct) {
      return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(struct + NAME));
   }

   public static ByteBuffer nsignature(long struct) {
      return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(struct + SIGNATURE));
   }

   public static String nsignatureString(long struct) {
      return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(struct + SIGNATURE));
   }

   public static long nfnPtr(long struct) {
      return MemoryUtil.memGetAddress(struct + FNPTR);
   }

   public static void nname(long struct, ByteBuffer value) {
      if (Checks.CHECKS) {
         Checks.checkNT1(value);
      }

      MemoryUtil.memPutAddress(struct + NAME, MemoryUtil.memAddress(value));
   }

   public static void nsignature(long struct, ByteBuffer value) {
      if (Checks.CHECKS) {
         Checks.checkNT1(value);
      }

      MemoryUtil.memPutAddress(struct + SIGNATURE, MemoryUtil.memAddress(value));
   }

   public static void nfnPtr(long struct, long value) {
      MemoryUtil.memPutAddress(struct + FNPTR, Checks.check(value));
   }

   public static void validate(long struct) {
      Checks.check(MemoryUtil.memGetAddress(struct + NAME));
      Checks.check(MemoryUtil.memGetAddress(struct + SIGNATURE));
      Checks.check(MemoryUtil.memGetAddress(struct + FNPTR));
   }

   public static void validate(long array, int count) {
      for (int i = 0; i < count; i++) {
         validate(array + Integer.toUnsignedLong(i) * SIZEOF);
      }
   }

   static {
      Struct.Layout layout = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE));
      SIZEOF = layout.getSize();
      ALIGNOF = layout.getAlignment();
      NAME = layout.offsetof(0);
      SIGNATURE = layout.offsetof(1);
      FNPTR = layout.offsetof(2);
   }

   public static class Buffer extends StructBuffer implements NativeResource {
      private static final JNINativeMethod ELEMENT_FACTORY = JNINativeMethod.create(-1L);

      public Buffer(ByteBuffer container) {
         super(container, container.remaining() / JNINativeMethod.SIZEOF);
      }

      public Buffer(long address, int cap) {
         super(address, null, -1, 0, cap, cap);
      }

      Buffer(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {
         super(address, container, mark, pos, lim, cap);
      }

      protected JNINativeMethod.Buffer self() {
         return this;
      }

      protected JNINativeMethod getElementFactory() {
         return ELEMENT_FACTORY;
      }

      @NativeType("char *")
      public ByteBuffer name() {
         return JNINativeMethod.nname(this.address());
      }

      @NativeType("char *")
      public String nameString() {
         return JNINativeMethod.nnameString(this.address());
      }

      @NativeType("char *")
      public ByteBuffer signature() {
         return JNINativeMethod.nsignature(this.address());
      }

      @NativeType("char *")
      public String signatureString() {
         return JNINativeMethod.nsignatureString(this.address());
      }

      @NativeType("void *")
      public long fnPtr() {
         return JNINativeMethod.nfnPtr(this.address());
      }

      public JNINativeMethod.Buffer name(@NativeType("char *") ByteBuffer value) {
         JNINativeMethod.nname(this.address(), value);
         return this;
      }

      public JNINativeMethod.Buffer signature(@NativeType("char *") ByteBuffer value) {
         JNINativeMethod.nsignature(this.address(), value);
         return this;
      }

      public JNINativeMethod.Buffer fnPtr(@NativeType("void *") long value) {
         JNINativeMethod.nfnPtr(this.address(), value);
         return this;
      }
   }
}
