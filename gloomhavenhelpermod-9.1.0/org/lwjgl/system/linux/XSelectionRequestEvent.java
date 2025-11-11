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

public class XSelectionRequestEvent extends Struct implements NativeResource {
   public static final int SIZEOF;
   public static final int ALIGNOF;
   public static final int TYPE;
   public static final int SERIAL;
   public static final int SEND_EVENT;
   public static final int DISPLAY;
   public static final int OWNER;
   public static final int REQUESTOR;
   public static final int SELECTION;
   public static final int TARGET;
   public static final int PROPERTY;
   public static final int TIME;

   public XSelectionRequestEvent(ByteBuffer container) {
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
   public long owner() {
      return nowner(this.address());
   }

   @NativeType("Window")
   public long requestor() {
      return nrequestor(this.address());
   }

   @NativeType("Atom")
   public long selection() {
      return nselection(this.address());
   }

   @NativeType("Atom")
   public long target() {
      return ntarget(this.address());
   }

   @NativeType("Atom")
   public long property() {
      return nproperty(this.address());
   }

   @NativeType("Time")
   public long time() {
      return ntime(this.address());
   }

   public XSelectionRequestEvent type(int value) {
      ntype(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent serial(@NativeType("unsigned long") long value) {
      nserial(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent send_event(@NativeType("Bool") boolean value) {
      nsend_event(this.address(), value ? 1 : 0);
      return this;
   }

   public XSelectionRequestEvent display(@NativeType("Display *") long value) {
      ndisplay(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent owner(@NativeType("Window") long value) {
      nowner(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent requestor(@NativeType("Window") long value) {
      nrequestor(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent selection(@NativeType("Atom") long value) {
      nselection(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent target(@NativeType("Atom") long value) {
      ntarget(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent property(@NativeType("Atom") long value) {
      nproperty(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent time(@NativeType("Time") long value) {
      ntime(this.address(), value);
      return this;
   }

   public XSelectionRequestEvent set(
      int type, long serial, boolean send_event, long display, long owner, long requestor, long selection, long target, long property, long time
   ) {
      this.type(type);
      this.serial(serial);
      this.send_event(send_event);
      this.display(display);
      this.owner(owner);
      this.requestor(requestor);
      this.selection(selection);
      this.target(target);
      this.property(property);
      this.time(time);
      return this;
   }

   public XSelectionRequestEvent set(XSelectionRequestEvent src) {
      MemoryUtil.memCopy(src.address(), this.address(), SIZEOF);
      return this;
   }

   public static XSelectionRequestEvent malloc() {
      return (XSelectionRequestEvent)wrap(XSelectionRequestEvent.class, MemoryUtil.nmemAllocChecked(SIZEOF));
   }

   public static XSelectionRequestEvent calloc() {
      return (XSelectionRequestEvent)wrap(XSelectionRequestEvent.class, MemoryUtil.nmemCallocChecked(1L, SIZEOF));
   }

   public static XSelectionRequestEvent create() {
      ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
      return (XSelectionRequestEvent)wrap(XSelectionRequestEvent.class, MemoryUtil.memAddress(container), container);
   }

   public static XSelectionRequestEvent create(long address) {
      return (XSelectionRequestEvent)wrap(XSelectionRequestEvent.class, address);
   }

   @Nullable
   public static XSelectionRequestEvent createSafe(long address) {
      return address == 0L ? null : (XSelectionRequestEvent)wrap(XSelectionRequestEvent.class, address);
   }

   public static XSelectionRequestEvent.Buffer malloc(int capacity) {
      return (XSelectionRequestEvent.Buffer)wrap(XSelectionRequestEvent.Buffer.class, MemoryUtil.nmemAllocChecked(__checkMalloc(capacity, SIZEOF)), capacity);
   }

   public static XSelectionRequestEvent.Buffer calloc(int capacity) {
      return (XSelectionRequestEvent.Buffer)wrap(XSelectionRequestEvent.Buffer.class, MemoryUtil.nmemCallocChecked(capacity, SIZEOF), capacity);
   }

   public static XSelectionRequestEvent.Buffer create(int capacity) {
      ByteBuffer container = __create(capacity, SIZEOF);
      return (XSelectionRequestEvent.Buffer)wrap(XSelectionRequestEvent.Buffer.class, MemoryUtil.memAddress(container), capacity, container);
   }

   public static XSelectionRequestEvent.Buffer create(long address, int capacity) {
      return (XSelectionRequestEvent.Buffer)wrap(XSelectionRequestEvent.Buffer.class, address, capacity);
   }

   @Nullable
   public static XSelectionRequestEvent.Buffer createSafe(long address, int capacity) {
      return address == 0L ? null : (XSelectionRequestEvent.Buffer)wrap(XSelectionRequestEvent.Buffer.class, address, capacity);
   }

   public static XSelectionRequestEvent mallocStack() {
      return mallocStack(MemoryStack.stackGet());
   }

   public static XSelectionRequestEvent callocStack() {
      return callocStack(MemoryStack.stackGet());
   }

   public static XSelectionRequestEvent mallocStack(MemoryStack stack) {
      return (XSelectionRequestEvent)wrap(XSelectionRequestEvent.class, stack.nmalloc(ALIGNOF, SIZEOF));
   }

   public static XSelectionRequestEvent callocStack(MemoryStack stack) {
      return (XSelectionRequestEvent)wrap(XSelectionRequestEvent.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
   }

   public static XSelectionRequestEvent.Buffer mallocStack(int capacity) {
      return mallocStack(capacity, MemoryStack.stackGet());
   }

   public static XSelectionRequestEvent.Buffer callocStack(int capacity) {
      return callocStack(capacity, MemoryStack.stackGet());
   }

   public static XSelectionRequestEvent.Buffer mallocStack(int capacity, MemoryStack stack) {
      return (XSelectionRequestEvent.Buffer)wrap(XSelectionRequestEvent.Buffer.class, stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
   }

   public static XSelectionRequestEvent.Buffer callocStack(int capacity, MemoryStack stack) {
      return (XSelectionRequestEvent.Buffer)wrap(XSelectionRequestEvent.Buffer.class, stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
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

   public static long nowner(long struct) {
      return MemoryUtil.memGetCLong(struct + OWNER);
   }

   public static long nrequestor(long struct) {
      return MemoryUtil.memGetCLong(struct + REQUESTOR);
   }

   public static long nselection(long struct) {
      return MemoryUtil.memGetCLong(struct + SELECTION);
   }

   public static long ntarget(long struct) {
      return MemoryUtil.memGetCLong(struct + TARGET);
   }

   public static long nproperty(long struct) {
      return MemoryUtil.memGetCLong(struct + PROPERTY);
   }

   public static long ntime(long struct) {
      return MemoryUtil.memGetCLong(struct + TIME);
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

   public static void nowner(long struct, long value) {
      MemoryUtil.memPutCLong(struct + OWNER, value);
   }

   public static void nrequestor(long struct, long value) {
      MemoryUtil.memPutCLong(struct + REQUESTOR, value);
   }

   public static void nselection(long struct, long value) {
      MemoryUtil.memPutCLong(struct + SELECTION, value);
   }

   public static void ntarget(long struct, long value) {
      MemoryUtil.memPutCLong(struct + TARGET, value);
   }

   public static void nproperty(long struct, long value) {
      MemoryUtil.memPutCLong(struct + PROPERTY, value);
   }

   public static void ntime(long struct, long value) {
      MemoryUtil.memPutCLong(struct + TIME, value);
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
         __member(4),
         __member(CLONG_SIZE),
         __member(4),
         __member(POINTER_SIZE),
         __member(CLONG_SIZE),
         __member(CLONG_SIZE),
         __member(CLONG_SIZE),
         __member(CLONG_SIZE),
         __member(CLONG_SIZE),
         __member(CLONG_SIZE)
      );
      SIZEOF = layout.getSize();
      ALIGNOF = layout.getAlignment();
      TYPE = layout.offsetof(0);
      SERIAL = layout.offsetof(1);
      SEND_EVENT = layout.offsetof(2);
      DISPLAY = layout.offsetof(3);
      OWNER = layout.offsetof(4);
      REQUESTOR = layout.offsetof(5);
      SELECTION = layout.offsetof(6);
      TARGET = layout.offsetof(7);
      PROPERTY = layout.offsetof(8);
      TIME = layout.offsetof(9);
   }

   public static class Buffer extends StructBuffer implements NativeResource {
      private static final XSelectionRequestEvent ELEMENT_FACTORY = XSelectionRequestEvent.create(-1L);

      public Buffer(ByteBuffer container) {
         super(container, container.remaining() / XSelectionRequestEvent.SIZEOF);
      }

      public Buffer(long address, int cap) {
         super(address, null, -1, 0, cap, cap);
      }

      Buffer(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {
         super(address, container, mark, pos, lim, cap);
      }

      protected XSelectionRequestEvent.Buffer self() {
         return this;
      }

      protected XSelectionRequestEvent getElementFactory() {
         return ELEMENT_FACTORY;
      }

      public int type() {
         return XSelectionRequestEvent.ntype(this.address());
      }

      @NativeType("unsigned long")
      public long serial() {
         return XSelectionRequestEvent.nserial(this.address());
      }

      @NativeType("Bool")
      public boolean send_event() {
         return XSelectionRequestEvent.nsend_event(this.address()) != 0;
      }

      @NativeType("Display *")
      public long display() {
         return XSelectionRequestEvent.ndisplay(this.address());
      }

      @NativeType("Window")
      public long owner() {
         return XSelectionRequestEvent.nowner(this.address());
      }

      @NativeType("Window")
      public long requestor() {
         return XSelectionRequestEvent.nrequestor(this.address());
      }

      @NativeType("Atom")
      public long selection() {
         return XSelectionRequestEvent.nselection(this.address());
      }

      @NativeType("Atom")
      public long target() {
         return XSelectionRequestEvent.ntarget(this.address());
      }

      @NativeType("Atom")
      public long property() {
         return XSelectionRequestEvent.nproperty(this.address());
      }

      @NativeType("Time")
      public long time() {
         return XSelectionRequestEvent.ntime(this.address());
      }

      public XSelectionRequestEvent.Buffer type(int value) {
         XSelectionRequestEvent.ntype(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer serial(@NativeType("unsigned long") long value) {
         XSelectionRequestEvent.nserial(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer send_event(@NativeType("Bool") boolean value) {
         XSelectionRequestEvent.nsend_event(this.address(), value ? 1 : 0);
         return this;
      }

      public XSelectionRequestEvent.Buffer display(@NativeType("Display *") long value) {
         XSelectionRequestEvent.ndisplay(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer owner(@NativeType("Window") long value) {
         XSelectionRequestEvent.nowner(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer requestor(@NativeType("Window") long value) {
         XSelectionRequestEvent.nrequestor(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer selection(@NativeType("Atom") long value) {
         XSelectionRequestEvent.nselection(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer target(@NativeType("Atom") long value) {
         XSelectionRequestEvent.ntarget(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer property(@NativeType("Atom") long value) {
         XSelectionRequestEvent.nproperty(this.address(), value);
         return this;
      }

      public XSelectionRequestEvent.Buffer time(@NativeType("Time") long value) {
         XSelectionRequestEvent.ntime(this.address(), value);
         return this;
      }
   }
}
