package org.lwjgl.system;

import java.util.function.Function;
import javax.annotation.Nullable;

public class Configuration {
   public static final Configuration LIBRARY_PATH = new Configuration("org.lwjgl.librarypath", Configuration.StateInit.STRING);
   public static final Configuration BUNDLED_LIBRARY_NAME_MAPPER = new Configuration(
      "org.lwjgl.system.bundledLibrary.nameMapper", Configuration.StateInit.STRING
   );
   public static final Configuration BUNDLED_LIBRARY_PATH_MAPPER = new Configuration(
      "org.lwjgl.system.bundledLibrary.pathMapper", Configuration.StateInit.STRING
   );
   public static final Configuration SHARED_LIBRARY_EXTRACT_DIRECTORY = new Configuration(
      "org.lwjgl.system.SharedLibraryExtractDirectory", Configuration.StateInit.STRING
   );
   public static final Configuration SHARED_LIBRARY_EXTRACT_PATH = new Configuration(
      "org.lwjgl.system.SharedLibraryExtractPath", Configuration.StateInit.STRING
   );
   public static final Configuration EMULATE_SYSTEM_LOADLIBRARY = new Configuration(
      "org.lwjgl.system.EmulateSystemLoadLibrary", Configuration.StateInit.BOOLEAN
   );
   public static final Configuration LIBRARY_NAME = new Configuration("org.lwjgl.libname", Configuration.StateInit.STRING);
   public static final Configuration MEMORY_ALLOCATOR = new Configuration("org.lwjgl.system.allocator", Configuration.StateInit.STRING);
   public static final Configuration STACK_SIZE = new Configuration("org.lwjgl.system.stackSize", Configuration.StateInit.INT);
   public static final Configuration ARRAY_TLC_SIZE = new Configuration("org.lwjgl.system.arrayTLCSize", Configuration.StateInit.INT);
   public static final Configuration DISABLE_CHECKS = new Configuration("org.lwjgl.util.NoChecks", Configuration.StateInit.BOOLEAN);
   public static final Configuration DISABLE_FUNCTION_CHECKS = new Configuration("org.lwjgl.util.NoFunctionChecks", Configuration.StateInit.BOOLEAN);
   public static final Configuration DEBUG = new Configuration("org.lwjgl.util.Debug", Configuration.StateInit.BOOLEAN);
   public static final Configuration DEBUG_LOADER = new Configuration("org.lwjgl.util.DebugLoader", Configuration.StateInit.BOOLEAN);
   public static final Configuration DEBUG_STREAM = new Configuration("org.lwjgl.util.DebugStream", Configuration.StateInit.STRING);
   public static final Configuration DEBUG_MEMORY_ALLOCATOR = new Configuration("org.lwjgl.util.DebugAllocator", Configuration.StateInit.BOOLEAN);
   public static final Configuration DEBUG_MEMORY_ALLOCATOR_INTERNAL = new Configuration(
      "org.lwjgl.util.DebugAllocator.internal", Configuration.StateInit.BOOLEAN
   );
   public static final Configuration DEBUG_STACK = new Configuration("org.lwjgl.util.DebugStack", Configuration.StateInit.BOOLEAN);
   public static final Configuration DEBUG_FUNCTIONS = new Configuration("org.lwjgl.util.DebugFunctions", Configuration.StateInit.BOOLEAN);
   public static final Configuration ASSIMP_LIBRARY_NAME = new Configuration("org.lwjgl.assimp.libname", Configuration.StateInit.STRING);
   public static final Configuration BGFX_LIBRARY_NAME = new Configuration("org.lwjgl.bgfx.libname", Configuration.StateInit.STRING);
   public static final Configuration CUDA_LIBRARY_NAME = new Configuration("org.lwjgl.cuda.libname", Configuration.StateInit.STRING);
   public static final Configuration CUDA_TOOLKIT_VERSION = new Configuration("org.lwjgl.cuda.toolkit.version", Configuration.StateInit.STRING);
   public static final Configuration CUDA_TOOLKIT_PATH = new Configuration("org.lwjgl.cuda.toolkit.path", Configuration.StateInit.STRING);
   public static final Configuration CUDA_NVRTC_LIBRARY_NAME = new Configuration("org.lwjgl.cuda.nvrtc.libname", Configuration.StateInit.STRING);
   public static final Configuration CUDA_NVRTC_BUILTINS_LIBRARY_NAME = new Configuration(
      "org.lwjgl.cuda.nvrtc-builtins.libname", Configuration.StateInit.STRING
   );
   public static final Configuration CUDA_API_PER_THREAD_DEFAULT_STREAM = new Configuration("org.lwjgl.cuda.ptds", Configuration.StateInit.BOOLEAN);
   public static final Configuration EGL_EXPLICIT_INIT = new Configuration("org.lwjgl.egl.explicitInit", Configuration.StateInit.BOOLEAN);
   public static final Configuration EGL_LIBRARY_NAME = new Configuration("org.lwjgl.egl.libname", Configuration.StateInit.STRING);
   public static final Configuration GLFW_LIBRARY_NAME = new Configuration("org.lwjgl.glfw.libname", Configuration.StateInit.STRING);
   public static final Configuration GLFW_CHECK_THREAD0 = new Configuration("org.lwjgl.glfw.checkThread0", Configuration.StateInit.BOOLEAN);
   public static final Configuration JAWT_LIBRARY_NAME = new Configuration("org.lwjgl.system.jawt.libname", Configuration.StateInit.STRING);
   public static final Configuration JEMALLOC_LIBRARY_NAME = new Configuration("org.lwjgl.system.jemalloc.libname", Configuration.StateInit.STRING);
   public static final Configuration LLVM_LIBRARY_NAME = new Configuration("org.lwjgl.llvm.libname", Configuration.StateInit.STRING);
   public static final Configuration LLVM_CLANG_LIBRARY_NAME = new Configuration("org.lwjgl.llvm.clang.libname", Configuration.StateInit.STRING);
   public static final Configuration LLVM_LTO_LIBRARY_NAME = new Configuration("org.lwjgl.llvm.lto.libname", Configuration.StateInit.STRING);
   public static final Configuration ODBC_LIBRARY_NAME = new Configuration("org.lwjgl.odbc.libname", Configuration.StateInit.STRING);
   public static final Configuration OPENAL_EXPLICIT_INIT = new Configuration("org.lwjgl.openal.explicitInit", Configuration.StateInit.BOOLEAN);
   public static final Configuration OPENAL_LIBRARY_NAME = new Configuration("org.lwjgl.openal.libname", Configuration.StateInit.STRING);
   public static final Configuration OPENCL_EXPLICIT_INIT = new Configuration("org.lwjgl.opencl.explicitInit", Configuration.StateInit.BOOLEAN);
   public static final Configuration OPENCL_LIBRARY_NAME = new Configuration("org.lwjgl.opencl.libname", Configuration.StateInit.STRING);
   public static final Configuration OPENGL_EXPLICIT_INIT = new Configuration("org.lwjgl.opengl.explicitInit", Configuration.StateInit.BOOLEAN);
   public static final Configuration OPENGL_LIBRARY_NAME = new Configuration("org.lwjgl.opengl.libname", Configuration.StateInit.STRING);
   public static final Configuration OPENGL_MAXVERSION = new Configuration("org.lwjgl.opengl.maxVersion", Configuration.StateInit.STRING);
   public static final Configuration OPENGLES_EXPLICIT_INIT = new Configuration("org.lwjgl.opengles.explicitInit", Configuration.StateInit.BOOLEAN);
   public static final Configuration OPENGLES_LIBRARY_NAME = new Configuration("org.lwjgl.opengles.libname", Configuration.StateInit.STRING);
   public static final Configuration OPENGLES_MAXVERSION = new Configuration("org.lwjgl.opengles.maxVersion", Configuration.StateInit.STRING);
   public static final Configuration OPENVR_LIBRARY_NAME = new Configuration("org.lwjgl.openvr.libname", Configuration.StateInit.STRING);
   public static final Configuration OPUS_LIBRARY_NAME = new Configuration("org.lwjgl.opus.libname", Configuration.StateInit.STRING);
   public static final Configuration SHADERC_LIBRARY_NAME = new Configuration("org.lwjgl.shaderc.libname", Configuration.StateInit.STRING);
   public static final Configuration SHADERC_SPVC_LIBRARY_NAME = new Configuration("org.lwjgl.shaderc.spvc.libname", Configuration.StateInit.STRING);
   public static final Configuration VULKAN_EXPLICIT_INIT = new Configuration("org.lwjgl.vulkan.explicitInit", Configuration.StateInit.BOOLEAN);
   public static final Configuration VULKAN_LIBRARY_NAME = new Configuration("org.lwjgl.vulkan.libname", Configuration.StateInit.STRING);
   private final String property;
   @Nullable
   private Object state;

   Configuration(String property, Configuration.StateInit init) {
      this.property = property;
      this.state = init.apply(property);
   }

   public String getProperty() {
      return this.property;
   }

   public void set(@Nullable Object value) {
      this.state = value;
   }

   @Nullable
   public Object get() {
      return this.state;
   }

   public Object get(Object defaultValue) {
      Object state = (T)this.state;
      if (state == null) {
         state = defaultValue;
      }

      return state;
   }

   private interface StateInit extends Function {
      Configuration.StateInit BOOLEAN = property -> {
         String value = System.getProperty(property);
         return value == null ? null : Boolean.parseBoolean(value);
      };
      Configuration.StateInit INT = Integer::getInteger;
      Configuration.StateInit STRING = System::getProperty;
   }
}
