package org.lwjgl.system;

import java.util.function.Function;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public enum Platform {
   LINUX("Linux", "linux") {
      private final Pattern SO = Pattern.compile("(?:^|/)lib\\w+[.]so(?:[.]\\d+)*$");

      @Override
      String mapLibraryName(String name) {
         return this.SO.matcher(name).find() ? name : System.mapLibraryName(name);
      }
   },
   MACOSX("macOS", "macos") {
      private final Pattern DYLIB = Pattern.compile("(?:^|/)lib\\w+(?:[.]\\d+)*[.]dylib$");

      @Override
      String mapLibraryName(String name) {
         return this.DYLIB.matcher(name).find() ? name : System.mapLibraryName(name);
      }
   },
   WINDOWS("Windows", "windows") {
      @Override
      String mapLibraryName(String name) {
         return name.endsWith(".dll") ? name : System.mapLibraryName(name);
      }
   };

   private static final Platform current;
   private static final Function bundledLibraryNameMapper;
   private static final Function bundledLibraryPathMapper;
   private final String name;
   private final String nativePath;

   private Platform(String name, String nativePath) {
      this.name = name;
      this.nativePath = nativePath;
   }

   public String getName() {
      return this.name;
   }

   abstract String mapLibraryName(String var1);

   public static Platform get() {
      return current;
   }

   public static Platform.Architecture getArchitecture() {
      return Platform.Architecture.current;
   }

   public static String mapLibraryNameBundled(String name) {
      return (String)bundledLibraryNameMapper.apply(name);
   }

   static String mapLibraryPathBundled(String name) {
      return (String)bundledLibraryPathMapper.apply(name);
   }

   private static Function getMapper(@Nullable Object mapper, Function defaultMapper, Function legacyMapper) {
      if (mapper == null || "default".equals(mapper)) {
         return defaultMapper;
      } else if ("legacy".equals(mapper)) {
         return legacyMapper;
      } else if (mapper instanceof Function) {
         return (Function)mapper;
      } else {
         String className = mapper.toString();

         try {
            return (Function)Class.forName(className).getConstructor().newInstance();
         } catch (Throwable var5) {
            if (Checks.DEBUG) {
               var5.printStackTrace(APIUtil.DEBUG_STREAM);
            }

            APIUtil.apiLog(String.format("Warning: Failed to instantiate bundled library mapper: %s. Using the default.", className));
            return defaultMapper;
         }
      }
   }

   static {
      String osName = System.getProperty("os.name");
      if (osName.startsWith("Windows")) {
         current = WINDOWS;
      } else if (!osName.startsWith("Linux") && !osName.startsWith("FreeBSD") && !osName.startsWith("SunOS") && !osName.startsWith("Unix")) {
         if (!osName.startsWith("Mac OS X") && !osName.startsWith("Darwin")) {
            throw new LinkageError("Unknown platform: " + osName);
         }

         current = MACOSX;
      } else {
         current = LINUX;
      }

      bundledLibraryNameMapper = getMapper(
         Configuration.BUNDLED_LIBRARY_NAME_MAPPER.get("default"), name -> name, name -> Platform.Architecture.current.is64Bit ? name : name + "32"
      );
      bundledLibraryPathMapper = getMapper(
         Configuration.BUNDLED_LIBRARY_PATH_MAPPER.get("default"),
         name -> current.nativePath + "/" + Platform.Architecture.current.name().toLowerCase() + "/" + name,
         name -> name.substring(name.lastIndexOf(47))
      );
   }

   public static enum Architecture {
      X64(true),
      X86(false),
      ARM64(true),
      ARM32(false);

      static final Platform.Architecture current;
      final boolean is64Bit;

      private Architecture(boolean is64Bit) {
         this.is64Bit = is64Bit;
      }

      static {
         String osArch = System.getProperty("os.arch");
         boolean is64Bit = osArch.contains("64") || osArch.startsWith("armv8");
         current = !osArch.startsWith("arm") && !osArch.startsWith("aarch64") ? (is64Bit ? X64 : X86) : (is64Bit ? ARM64 : ARM32);
      }
   }
}
