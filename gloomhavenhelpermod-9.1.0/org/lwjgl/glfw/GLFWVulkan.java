package org.lwjgl.glfw;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import javax.annotation.Nullable;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.vulkan.VK;
import org.lwjgl.vulkan.VkAllocationCallbacks;
import org.lwjgl.vulkan.VkInstance;
import org.lwjgl.vulkan.VkPhysicalDevice;

public class GLFWVulkan {
   protected GLFWVulkan() {
      throw new UnsupportedOperationException();
   }

   @NativeType("int")
   public static boolean glfwVulkanSupported() {
      long __functionAddress = GLFWVulkan.Functions.VulkanSupported;
      return JNI.invokeI(__functionAddress) != 0;
   }

   public static long nglfwGetRequiredInstanceExtensions(long count) {
      long __functionAddress = GLFWVulkan.Functions.GetRequiredInstanceExtensions;
      return JNI.invokePP(count, __functionAddress);
   }

   @Nullable
   @NativeType("char const **")
   public static PointerBuffer glfwGetRequiredInstanceExtensions() {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();
      IntBuffer count = stack.callocInt(1);

      PointerBuffer var5;
      try {
         long __result = nglfwGetRequiredInstanceExtensions(MemoryUtil.memAddress(count));
         var5 = MemoryUtil.memPointerBufferSafe(__result, count.get(0));
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   public static long nglfwGetInstanceProcAddress(long instance, long procname) {
      long __functionAddress = GLFWVulkan.Functions.GetInstanceProcAddress;
      return JNI.invokePPP(instance, procname, __functionAddress);
   }

   @NativeType("GLFWvkproc")
   public static long glfwGetInstanceProcAddress(@Nullable VkInstance instance, @NativeType("char const *") ByteBuffer procname) {
      if (Checks.CHECKS) {
         Checks.checkNT1(procname);
      }

      return nglfwGetInstanceProcAddress(MemoryUtil.memAddressSafe(instance), MemoryUtil.memAddress(procname));
   }

   @NativeType("GLFWvkproc")
   public static long glfwGetInstanceProcAddress(@Nullable VkInstance instance, @NativeType("char const *") CharSequence procname) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var6;
      try {
         stack.nASCII(procname, true);
         long procnameEncoded = stack.getPointerAddress();
         var6 = nglfwGetInstanceProcAddress(MemoryUtil.memAddressSafe(instance), procnameEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var6;
   }

   @NativeType("int")
   public static boolean glfwGetPhysicalDevicePresentationSupport(VkInstance instance, VkPhysicalDevice device, @NativeType("uint32_t") int queuefamily) {
      long __functionAddress = GLFWVulkan.Functions.GetPhysicalDevicePresentationSupport;
      return JNI.invokePPI(instance.address(), device.address(), queuefamily, __functionAddress) != 0;
   }

   public static int nglfwCreateWindowSurface(long instance, long window, long allocator, long surface) {
      long __functionAddress = GLFWVulkan.Functions.CreateWindowSurface;
      if (Checks.CHECKS) {
         Checks.check(window);
         if (allocator != 0L) {
            VkAllocationCallbacks.validate(allocator);
         }
      }

      return JNI.invokePPPPI(instance, window, allocator, surface, __functionAddress);
   }

   @NativeType("VkResult")
   public static int glfwCreateWindowSurface(
      VkInstance instance,
      @NativeType("GLFWwindow *") long window,
      @Nullable @NativeType("VkAllocationCallbacks const *") VkAllocationCallbacks allocator,
      @NativeType("VkSurfaceKHR *") LongBuffer surface
   ) {
      if (Checks.CHECKS) {
         Checks.check(surface, 1);
      }

      return nglfwCreateWindowSurface(instance.address(), window, MemoryUtil.memAddressSafe(allocator), MemoryUtil.memAddress(surface));
   }

   @NativeType("VkResult")
   public static int glfwCreateWindowSurface(
      VkInstance instance,
      @NativeType("GLFWwindow *") long window,
      @Nullable @NativeType("VkAllocationCallbacks const *") VkAllocationCallbacks allocator,
      @NativeType("VkSurfaceKHR *") long[] surface
   ) {
      long __functionAddress = GLFWVulkan.Functions.CreateWindowSurface;
      if (Checks.CHECKS) {
         Checks.check(window);
         Checks.check(surface, 1);
         if (allocator != null) {
            VkAllocationCallbacks.validate(allocator.address());
         }
      }

      return JNI.invokePPPPI(instance.address(), window, MemoryUtil.memAddressSafe(allocator), surface, __functionAddress);
   }

   static {
      if (Platform.get() == Platform.MACOSX) {
         FunctionProvider fp = VK.getFunctionProvider();
         if (fp instanceof SharedLibrary) {
            String path = ((SharedLibrary)fp).getPath();
            if (path != null) {
               try (MemoryStack stack = MemoryStack.stackPush()) {
                  long _glfw_vulkan_library = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "_glfw_vulkan_library");
                  MemoryUtil.memPutAddress(_glfw_vulkan_library, MemoryUtil.memAddress(stack.UTF8(path)));
                  glfwVulkanSupported();
                  MemoryUtil.memPutAddress(_glfw_vulkan_library, 0L);
               }
            }
         }
      }
   }

   public static final class Functions {
      public static final long VulkanSupported = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwVulkanSupported");
      public static final long GetRequiredInstanceExtensions = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetRequiredInstanceExtensions");
      public static final long GetInstanceProcAddress = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetInstanceProcAddress");
      public static final long GetPhysicalDevicePresentationSupport = APIUtil.apiGetFunctionAddress(
         GLFW.getLibrary(), "glfwGetPhysicalDevicePresentationSupport"
      );
      public static final long CreateWindowSurface = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwCreateWindowSurface");

      private Functions() {
      }
   }
}
