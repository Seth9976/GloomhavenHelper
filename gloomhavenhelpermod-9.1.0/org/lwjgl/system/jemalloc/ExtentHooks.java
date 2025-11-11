package org.lwjgl.system.jemalloc;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;

@NativeType("struct extent_hooks_t")
public class ExtentHooks extends Struct implements NativeResource {
   public static final int SIZEOF;
   public static final int ALIGNOF;
   public static final int ALLOC;
   public static final int DALLOC;
   public static final int DESTROY;
   public static final int COMMIT;
   public static final int DECOMMIT;
   public static final int PURGE_LAZY;
   public static final int PURGE_FORCED;
   public static final int SPLIT;
   public static final int MERGE;

   public ExtentHooks(ByteBuffer container) {
      super(MemoryUtil.memAddress(container), __checkContainer(container, SIZEOF));
   }

   @Override
   public int sizeof() {
      return SIZEOF;
   }

   @NativeType("extent_alloc_t")
   public ExtentAlloc alloc() {
      return nalloc(this.address());
   }

   @Nullable
   @NativeType("extent_dalloc_t")
   public ExtentDalloc dalloc() {
      return ndalloc(this.address());
   }

   @Nullable
   @NativeType("extent_destroy_t")
   public ExtentDestroy destroy() {
      return ndestroy(this.address());
   }

   @Nullable
   @NativeType("extent_commit_t")
   public ExtentCommit commit() {
      return ncommit(this.address());
   }

   @Nullable
   @NativeType("extent_decommit_t")
   public ExtentDecommit decommit() {
      return ndecommit(this.address());
   }

   @Nullable
   @NativeType("extent_purge_t")
   public ExtentPurge purge_lazy() {
      return npurge_lazy(this.address());
   }

   @Nullable
   @NativeType("extent_purge_t")
   public ExtentPurge purge_forced() {
      return npurge_forced(this.address());
   }

   @Nullable
   @NativeType("extent_split_t")
   public ExtentSplit split() {
      return nsplit(this.address());
   }

   @Nullable
   @NativeType("extent_merge_t")
   public ExtentMerge merge() {
      return nmerge(this.address());
   }

   public ExtentHooks alloc(@NativeType("extent_alloc_t") ExtentAllocI value) {
      nalloc(this.address(), value);
      return this;
   }

   public ExtentHooks dalloc(@Nullable @NativeType("extent_dalloc_t") ExtentDallocI value) {
      ndalloc(this.address(), value);
      return this;
   }

   public ExtentHooks destroy(@Nullable @NativeType("extent_destroy_t") ExtentDestroyI value) {
      ndestroy(this.address(), value);
      return this;
   }

   public ExtentHooks commit(@Nullable @NativeType("extent_commit_t") ExtentCommitI value) {
      ncommit(this.address(), value);
      return this;
   }

   public ExtentHooks decommit(@Nullable @NativeType("extent_decommit_t") ExtentDecommitI value) {
      ndecommit(this.address(), value);
      return this;
   }

   public ExtentHooks purge_lazy(@Nullable @NativeType("extent_purge_t") ExtentPurgeI value) {
      npurge_lazy(this.address(), value);
      return this;
   }

   public ExtentHooks purge_forced(@Nullable @NativeType("extent_purge_t") ExtentPurgeI value) {
      npurge_forced(this.address(), value);
      return this;
   }

   public ExtentHooks split(@Nullable @NativeType("extent_split_t") ExtentSplitI value) {
      nsplit(this.address(), value);
      return this;
   }

   public ExtentHooks merge(@Nullable @NativeType("extent_merge_t") ExtentMergeI value) {
      nmerge(this.address(), value);
      return this;
   }

   public ExtentHooks set(
      ExtentAllocI alloc,
      ExtentDallocI dalloc,
      ExtentDestroyI destroy,
      ExtentCommitI commit,
      ExtentDecommitI decommit,
      ExtentPurgeI purge_lazy,
      ExtentPurgeI purge_forced,
      ExtentSplitI split,
      ExtentMergeI merge
   ) {
      this.alloc(alloc);
      this.dalloc(dalloc);
      this.destroy(destroy);
      this.commit(commit);
      this.decommit(decommit);
      this.purge_lazy(purge_lazy);
      this.purge_forced(purge_forced);
      this.split(split);
      this.merge(merge);
      return this;
   }

   public ExtentHooks set(ExtentHooks src) {
      MemoryUtil.memCopy(src.address(), this.address(), SIZEOF);
      return this;
   }

   public static ExtentHooks malloc() {
      return (ExtentHooks)wrap(ExtentHooks.class, MemoryUtil.nmemAllocChecked(SIZEOF));
   }

   public static ExtentHooks calloc() {
      return (ExtentHooks)wrap(ExtentHooks.class, MemoryUtil.nmemCallocChecked(1L, SIZEOF));
   }

   public static ExtentHooks create() {
      ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
      return (ExtentHooks)wrap(ExtentHooks.class, MemoryUtil.memAddress(container), container);
   }

   public static ExtentHooks create(long address) {
      return (ExtentHooks)wrap(ExtentHooks.class, address);
   }

   @Nullable
   public static ExtentHooks createSafe(long address) {
      return address == 0L ? null : (ExtentHooks)wrap(ExtentHooks.class, address);
   }

   public static ExtentHooks mallocStack() {
      return mallocStack(MemoryStack.stackGet());
   }

   public static ExtentHooks callocStack() {
      return callocStack(MemoryStack.stackGet());
   }

   public static ExtentHooks mallocStack(MemoryStack stack) {
      return (ExtentHooks)wrap(ExtentHooks.class, stack.nmalloc(ALIGNOF, SIZEOF));
   }

   public static ExtentHooks callocStack(MemoryStack stack) {
      return (ExtentHooks)wrap(ExtentHooks.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
   }

   public static ExtentAlloc nalloc(long struct) {
      return ExtentAlloc.create(MemoryUtil.memGetAddress(struct + ALLOC));
   }

   @Nullable
   public static ExtentDalloc ndalloc(long struct) {
      return ExtentDalloc.createSafe(MemoryUtil.memGetAddress(struct + DALLOC));
   }

   @Nullable
   public static ExtentDestroy ndestroy(long struct) {
      return ExtentDestroy.createSafe(MemoryUtil.memGetAddress(struct + DESTROY));
   }

   @Nullable
   public static ExtentCommit ncommit(long struct) {
      return ExtentCommit.createSafe(MemoryUtil.memGetAddress(struct + COMMIT));
   }

   @Nullable
   public static ExtentDecommit ndecommit(long struct) {
      return ExtentDecommit.createSafe(MemoryUtil.memGetAddress(struct + DECOMMIT));
   }

   @Nullable
   public static ExtentPurge npurge_lazy(long struct) {
      return ExtentPurge.createSafe(MemoryUtil.memGetAddress(struct + PURGE_LAZY));
   }

   @Nullable
   public static ExtentPurge npurge_forced(long struct) {
      return ExtentPurge.createSafe(MemoryUtil.memGetAddress(struct + PURGE_FORCED));
   }

   @Nullable
   public static ExtentSplit nsplit(long struct) {
      return ExtentSplit.createSafe(MemoryUtil.memGetAddress(struct + SPLIT));
   }

   @Nullable
   public static ExtentMerge nmerge(long struct) {
      return ExtentMerge.createSafe(MemoryUtil.memGetAddress(struct + MERGE));
   }

   public static void nalloc(long struct, ExtentAllocI value) {
      MemoryUtil.memPutAddress(struct + ALLOC, value.address());
   }

   public static void ndalloc(long struct, @Nullable ExtentDallocI value) {
      MemoryUtil.memPutAddress(struct + DALLOC, MemoryUtil.memAddressSafe(value));
   }

   public static void ndestroy(long struct, @Nullable ExtentDestroyI value) {
      MemoryUtil.memPutAddress(struct + DESTROY, MemoryUtil.memAddressSafe(value));
   }

   public static void ncommit(long struct, @Nullable ExtentCommitI value) {
      MemoryUtil.memPutAddress(struct + COMMIT, MemoryUtil.memAddressSafe(value));
   }

   public static void ndecommit(long struct, @Nullable ExtentDecommitI value) {
      MemoryUtil.memPutAddress(struct + DECOMMIT, MemoryUtil.memAddressSafe(value));
   }

   public static void npurge_lazy(long struct, @Nullable ExtentPurgeI value) {
      MemoryUtil.memPutAddress(struct + PURGE_LAZY, MemoryUtil.memAddressSafe(value));
   }

   public static void npurge_forced(long struct, @Nullable ExtentPurgeI value) {
      MemoryUtil.memPutAddress(struct + PURGE_FORCED, MemoryUtil.memAddressSafe(value));
   }

   public static void nsplit(long struct, @Nullable ExtentSplitI value) {
      MemoryUtil.memPutAddress(struct + SPLIT, MemoryUtil.memAddressSafe(value));
   }

   public static void nmerge(long struct, @Nullable ExtentMergeI value) {
      MemoryUtil.memPutAddress(struct + MERGE, MemoryUtil.memAddressSafe(value));
   }

   public static void validate(long struct) {
      Checks.check(MemoryUtil.memGetAddress(struct + ALLOC));
   }

   public static void validate(long array, int count) {
      for (int i = 0; i < count; i++) {
         validate(array + Integer.toUnsignedLong(i) * SIZEOF);
      }
   }

   static {
      Struct.Layout layout = __struct(
         __member(POINTER_SIZE),
         __member(POINTER_SIZE),
         __member(POINTER_SIZE),
         __member(POINTER_SIZE),
         __member(POINTER_SIZE),
         __member(POINTER_SIZE),
         __member(POINTER_SIZE),
         __member(POINTER_SIZE),
         __member(POINTER_SIZE)
      );
      SIZEOF = layout.getSize();
      ALIGNOF = layout.getAlignment();
      ALLOC = layout.offsetof(0);
      DALLOC = layout.offsetof(1);
      DESTROY = layout.offsetof(2);
      COMMIT = layout.offsetof(3);
      DECOMMIT = layout.offsetof(4);
      PURGE_LAZY = layout.offsetof(5);
      PURGE_FORCED = layout.offsetof(6);
      SPLIT = layout.offsetof(7);
      MERGE = layout.offsetof(8);
   }
}
