package org.lwjgl.system.linux;

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

public class XMapEvent extends Struct implements NativeResource {
   public static final int SIZEOF;
   public static final int ALIGNOF;
   public static final int TYPE;
   public static final int SERIAL;
   public static final int SEND_EVENT;
   public static final int DISPLAY;
   public static final int EVENT;
   public static final int WINDOW;
   public static final int OVERRIDE_REDIRECT;

   public XMapEvent(ByteBuffer container) {
      super(MemoryUtil.memAddress(container), __checkContainer(container, SIZEOF));
   }

   @Override
   public int sizeof() {
      return SIZEOF;
   }

   public int type() {
      return ntype(this.address());
   }

   @NativeType("unsigned long")
   public long serial() {
      return nserial(this.address());
   }

   @NativeType("Bool")
   public boolean send_event() {
      return nsend_event(this.address()) != 0;
   }

   @NativeType("Display *")
   public long display() {
      return ndisplay(this.address());
   }

   @NativeType("Window")
   public long event() {
      return nevent(this.address());
   }

   @NativeType("Window")
   public long window() {
      return nwindow(this.address());
   }

   public int override_redirect() {
      return noverride_redirect(this.address());
   }

   public XMapEvent type(int value) {
      ntype(this.address(), value);
      return this;
   }

   public XMapEvent serial(@NativeType("unsigned long") long value) {
      nserial(this.address(), value);
      return this;
   }

   public XMapEvent send_event(@NativeType("Bool") boolean value) {
      nsend_event(this.address(), value ? 1 : 0);
      return this;
   }

   public XMapEvent display(@NativeType("Display *") long value) {
      ndisplay(this.address(), value);
      return this;
   }

   public XMapEvent event(@NativeType("Window") long value) {
      nevent(this.address(), value);
      return this;
   }

   public XMapEvent window(@NativeType("Window") long value) {
      nwindow(this.address(), value);
      return this;
   }

   public XMapEvent override_redirect(int value) {
      noverride_redirect(this.address(), value);
      return this;
   }

   public XMapEvent set(int type, long serial, boolean send_event, long display, long event, long window, int override_redirect) {
      this.type(type);
      this.serial(serial);
      this.send_event(send_event);
      this.display(display);
      this.event(event);
      this.window(window);
      this.override_redirect(override_redirect);
      return this;
   }

   public XMapEvent set(XMapEvent src) {
      MemoryUtil.memCopy(src.address(), this.address(), SIZEOF);
      return this;
   }

   public static XMapEvent malloc() {
      return (XMapEvent)wrap(XMapEvent.class, MemoryUtil.nmemAllocChecked(SIZEOF));
   }

   public static XMapEvent calloc() {
      return (XMapEvent)wrap(XMapEvent.class, MemoryUtil.nmemCallocChecked(1L, SIZEOF));
   }

   public static XMapEvent create() {
      ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
      return (XMapEvent)wrap(XMapEvent.class, MemoryUtil.memAddress(container), container);
   }

   public static XMapEvent create(long address) {
      return (XMapEvent)wrap(XMapEvent.class, address);
   }

   @Nullable
   public static XMapEvent createSafe(long address) {
      return address == 0L ? null : (XMapEvent)wrap(XMapEvent.class, address);
   }

   public static XMapEvent.Buffer malloc(int capacity) {
      return (XMapEvent.Buffer)wrap(XMapEvent.Buffer.class, MemoryUtil.nmemAllocChecked(__checkMalloc(capacity, SIZEOF)), capacity);
   }

   public static XMapEvent.Buffer calloc(int capacity) {
      return (XMapEvent.Buffer)wrap(XMapEvent.Buffer.class, MemoryUtil.nmemCallocChecked(capacity, SIZEOF), capacity);
   }

   public static XMapEvent.Buffer create(int capacity) {
      ByteBuffer container = __create(capacity, SIZEOF);
      return (XMapEvent.Buffer)wrap(XMapEvent.Buffer.class, MemoryUtil.memAddress(container), capacity, container);
   }

   public static XMapEvent.Buffer create(long address, int capacity) {
      return (XMapEvent.Buffer)wrap(XMapEvent.Buffer.class, address, capacity);
   }

   @Nullable
   public static XMapEvent.Buffer createSafe(long address, int capacity) {
      return address == 0L ? null : (XMapEvent.Buffer)wrap(XMapEvent.Buffer.class, address, capacity);
   }

   public static XMapEvent mallocStack() {
      return mallocStack(MemoryStack.stackGet());
   }

   public static XMapEvent callocStack() {
      return callocStack(MemoryStack.stackGet());
   }

   public static XMapEvent mallocStack(MemoryStack stack) {
      return (XMapEvent)wrap(XMapEvent.class, stack.nmalloc(ALIGNOF, SIZEOF));
   }

   public static XMapEvent callocStack(MemoryStack stack) {
      return (XMapEvent)wrap(XMapEvent.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
   }

   public static XMapEvent.Buffer mallocStack(int capacity) {
      return mallocStack(capacity, MemoryStack.stackGet());
   }

   public static XMapEvent.Buffer callocStack(int capacity) {
      return callocStack(capacity, MemoryStack.stackGet());
   }

   public static XMapEvent.Buffer mallocStack(int capacity, MemoryStack stack) {
      return (XMapEvent.Buffer)wrap(XMapEvent.Buffer.class, stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
   }

   public static XMapEvent.Buffer callocStack(int capacity, MemoryStack stack) {
      return (XMapEvent.Buffer)wrap(XMapEvent.Buffer.class, stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
   }

   public static int ntype(long struct) {
      return UNSAFE.getInt(null, struct + TYPE);
   }

   public static long nserial(long struct) {
      return MemoryUtil.memGetCLong(struct + SERIAL);
   }

   public static int nsend_event(long struct) {
      return UNSAFE.getInt(null, struct + SEND_EVENT);
   }

   public static long ndisplay(long struct) {
      return MemoryUtil.memGetAddress(struct + DISPLAY);
   }

   public static long nevent(long struct) {
      return MemoryUtil.memGetCLong(struct + EVENT);
   }

   public static long nwindow(long struct) {
      return MemoryUtil.memGetCLong(struct + WINDOW);
   }

   public static int noverride_redirect(long struct) {
      return UNSAFE.getInt(null, struct + OVERRIDE_REDIRECT);
   }

   public static void ntype(long struct, int value) {
      UNSAFE.putInt(null, struct + TYPE, value);
   }

   public static void nserial(long struct, long value) {
      MemoryUtil.memPutCLong(struct + SERIAL, value);
   }

   public static void nsend_event(long struct, int value) {
      UNSAFE.putInt(null, struct + SEND_EVENT, value);
   }

   public static void ndisplay(long struct, long value) {
      MemoryUtil.memPutAddress(struct + DISPLAY, Checks.check(value));
   }

   public static void nevent(long struct, long value) {
      MemoryUtil.memPutCLong(struct + EVENT, value);
   }

   public static void nwindow(long struct, long value) {
      MemoryUtil.memPutCLong(struct + WINDOW, value);
   }

   public static void noverride_redirect(long struct, int value) {
      UNSAFE.putInt(null, struct + OVERRIDE_REDIRECT, value);
   }

   public static void validate(long struct) {
      Checks.check(MemoryUtil.memGetAddress(struct + DISPLAY));
   }

   public static void validate(long array, int count) {
      for (int i = 0; i < count; i++) {
         validate(array + Integer.toUnsignedLong(i) * SIZEOF);
      }
   }

   static {
      Struct.Layout layout = __struct(
         __member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4)
      );
      SIZEOF = layout.getSize();
      ALIGNOF = layout.getAlignment();
      TYPE = layout.offsetof(0);
      SERIAL = layout.offsetof(1);
      SEND_EVENT = layout.offsetof(2);
      DISPLAY = layout.offsetof(3);
      EVENT = layout.offsetof(4);
      WINDOW = layout.offsetof(5);
      OVERRIDE_REDIRECT = layout.offsetof(6);
   }

   public static class Buffer extends StructBuffer implements NativeResource {
      private static final XMapEvent ELEMENT_FACTORY = XMapEvent.create(-1L);

      public Buffer(ByteBuffer container) {
         super(container, container.remaining() / XMapEvent.SIZEOF);
      }

      public Buffer(long address, int cap) {
         super(address, null, -1, 0, cap, cap);
      }

      Buffer(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {
         super(address, container, mark, pos, lim, cap);
      }

      protected XMapEvent.Buffer self() {
         return this;
      }

      protected XMapEvent getElementFactory() {
         return ELEMENT_FACTORY;
      }

      public int type() {
         return XMapEvent.ntype(this.address());
      }

      @NativeType("unsigned long")
      public long serial() {
         return XMapEvent.nserial(this.address());
      }

      @NativeType("Bool")
      public boolean send_event() {
         return XMapEvent.nsend_event(this.address()) != 0;
      }

      @NativeType("Display *")
      public long display() {
         return XMapEvent.ndisplay(this.address());
      }

      @NativeType("Window")
      public long event() {
         return XMapEvent.nevent(this.address());
      }

      @NativeType("Window")
      public long window() {
         return XMapEvent.nwindow(this.address());
      }

      public int override_redirect() {
         return XMapEvent.noverride_redirect(this.address());
      }

      public XMapEvent.Buffer type(int value) {
         XMapEvent.ntype(this.address(), value);
         return this;
      }

      public XMapEvent.Buffer serial(@NativeType("unsigned long") long value) {
         XMapEvent.nserial(this.address(), value);
         return this;
      }

      public XMapEvent.Buffer send_event(@NativeType("Bool") boolean value) {
         XMapEvent.nsend_event(this.address(), value ? 1 : 0);
         return this;
      }

      public XMapEvent.Buffer display(@NativeType("Display *") long value) {
         XMapEvent.ndisplay(this.address(), value);
         return this;
      }

      public XMapEvent.Buffer event(@NativeType("Window") long value) {
         XMapEvent.nevent(this.address(), value);
         return this;
      }

      public XMapEvent.Buffer window(@NativeType("Window") long value) {
         XMapEvent.nwindow(this.address(), value);
         return this;
      }

      public XMapEvent.Buffer override_redirect(int value) {
         XMapEvent.noverride_redirect(this.address(), value);
         return this;
      }
   }
}
